package fbaccs;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Accaunts {
	
	public boolean newInterface ;
	public boolean accBlock ;
	
	private static WebDriver driver;
	String [] email = { "vodozodigega@hotmail.com","hubojohisyty@hotmail.com","xejyfysutozo@hotmail.com","jiqiqekagaj@hotmail.com","nipocycagiqi@hotmail.com","jadurylifumy@hotmail.com","rytigizemyz@hotmail.com","dofytusajep@hotmail.com","fapekyhenira@hotmail.com","fezoqaceqyge@hotmail.com","xywenaqigur@hotmail.com","deceqyxamaxa@hotmail.com","goninuwewal@hotmail.com"};
	String [] pass = {       "eu2oBegpVn",                "b3lDjlioR",            "fxk2zchIuO",             "YyhGbhai9e",          "g3mxxNsshS",               "pbF4tqfts",                 "bnh8rtYAiv",           "enNxV2fphszrg",         "khycrvFZset7q",          "loqgxw5bbuwF",             "egfjzwiq7AvtK",           "phFcg2Tnepk",           "dlaKjB4xkrc"      };
	 
	
	public void setUp () throws Exception {
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}
	
	
	@Test	
	public void FakeVotes () throws Exception {
	for (int i =0; i<=email.length-1; i++) {
		
	/*Запускаем браузер*/
	setUp();
	
	/*Логинимся*/
	driver.get("http://facebook.com");
	WebElement emailField = driver.findElement(By.name("email"));
	WebElement passwordField = driver.findElement(By.name("pass"));
	emailField.clear();
	emailField.sendKeys(email[i].toString());
	passwordField.sendKeys(pass[i].toString());
	driver.findElement(By.xpath("//*[@type='submit']")).click();
	System.out.println("Юзер: "+email[i]+" "+pass[i]);
	
	try{
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//*[@name='submit[Continue]']"));
	accBlock = true;
	System.out.println("Этот аккаунт заблокирован "+email[i]+" "+pass[i]+accBlock);
	System.out.println("===================================================================");
	} catch(NoSuchElementException e) {
		accBlock = false;
	}
	
	/*Выполняем действия с аккаунтом*/
    if(accBlock == false) {
    	/*Переходим на страничку группы*/
    	driver.get("https://www.facebook.com/musicnaij?hc_location=stream");
    	likeGroup();
    	likePost();
    	//sharePost();
    	System.out.println("===================================================================");
    }
    
    /*Выходим из драйвера*/
	driver.quit();
	}
	}
	
	public void likePost() throws Exception {
		Thread.sleep(3000);
		 List<WebElement> list1=driver.findElements(By.cssSelector("div:nth-child(7) > div:nth-child(1) > span:nth-child(2) > span:nth-child(1) > a:nth-child(1)"));
	      WebElement like = list1.get(0);
	      WebElement like2 = list1.get(1);
		if(like.getText().equals("Like")) {
			like.click();
			System.out.println("Лакнул пост1: Да");
		} else {
			System.out.println("Лайкнул пост1: Нет, пост уже лайкнут");
		}
		if(like2.getText().equals("Like")) {
			like2.click();
			System.out.println("Лакнул пост2: Да");
		} else {
			System.out.println("Лайкнул пост2: Нет, пост уже лайкнут");
		}
	}
	
	public void sharePost () throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		try{
			
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(7) > div:nth-child(1) > span:nth-child(2) > a:nth-child(3)"))).click();
		                                                                 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='uiSelectorButton uiButton uiButtonSuppressed']"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-label='Public']"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='_42ft _42fu layerConfirm uiOverlayButton selected _42g- _42gy']"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='pam _13']")));
	    
		System.out.println("Разшарил пост: Да");
		
		} catch (NoSuchElementException e) {
			System.out.println("При выполнении *sharePost* не был найден елемент");
		}
	}
	
	
	public void likeGroup() {
		/*Если юзер не лайкнул групу, ставим лайк*/
		try{
		    driver.findElement(By.xpath("//*[@value='Liked']"));
		    System.out.println("Группа уже лайкнута");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@id='timelineHeadlineLikeButton']")).click();
			System.out.println("Добавлен лайк группы");
			driver.navigate().refresh();
		}
	}
	
	@AfterTest
	public void exit() {
		driver.quit();
	}
}