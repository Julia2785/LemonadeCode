package lemonadeCode;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("disable-infobars");
        options.addArguments("--enable-web-rtc");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--ignore-gpu-blacklist");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

//        WebDriver driver = new ChromeDriver();

        driver.get("https://www.lemonade.com");

        Thread.sleep(10000);
        ////cloud flare
//        driver.findElement(By.cssSelector("#PYMIw2 > div > div > div")).click();

        Thread.sleep(8000);


        //******Excel Input *******
        //Excel report Input
        String filePath = "./src/test/resources/LEMONADE.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String accountSelection = sheet.getRow(1).getCell(0).toString();
        String insuranceType = sheet.getRow(1).getCell(1).toString();
        String firstName = sheet.getRow(1).getCell(2).toString();
        String lastName = sheet.getRow(1).getCell(3).toString();
        String homeAddress = sheet.getRow(1).getCell(4).toString();
        String apt = sheet.getRow(1).getCell(5).toString();
        String homeSafety = sheet.getRow(1).getCell(6).toString();
        System.out.println(accountSelection + homeSafety);

        //******Page 1(Home Page) *******
        Thread.sleep(3000);
        //Check lemonade page is loaded
        driver.findElement(By.cssSelector("div.sc-dlfnbm.sc-hKgILt.sc-cbDGPM.cWMrUO.dpSTLb.eTEuVf > a > svg > g")).isDisplayed();
        //check our prices button
        driver.findElement(By.cssSelector("#gtm_button_main_check_prices")).click();


        //******Page 2 *******
        //Check lemonade page Maya Assistant is loaded(Account selection)
        Thread.sleep(6000);
        driver.findElement(By.cssSelector("#questions_form > div > h3 > p:nth-child(1)")).isDisplayed();
        //None Account option button
        String accountSelectionElement = "//div[@class='question-form']/label/div//h5[text()='" + accountSelection + "']";
        driver.findElement(By.xpath(accountSelectionElement)).click();
        Thread.sleep(2000);
        //Next button
        WebElement nextButton = driver.findElement(By.cssSelector("#submit-btn > button"));
        nextButton.click();

        //******Page 3 *******
        //lemonade page Maya Assistant(Type of Insurance)
        Thread.sleep(10000);
        //Type of Insurance option button
        String insuranceTypeElement = "//*[@id='questions_form']/div/div/div[1]/div[2]/label/div//h5[text()='" + insuranceType + "']";
          
        driver.findElement(By.xpath(insuranceTypeElement)).click();
        //Next button
        driver.findElement(By.cssSelector("#questions_form > div > button")).click();

        //******Page 4 *******
        //lemonade page Maya Assistant(First Name and Last Name details)
        Thread.sleep(2000);
        //FirstName Input
       // WebElement firstNameEle = driver.findElement(By.xpath("//*[@id='1722247992467']"));
        
        WebElement firstNameEle = driver.findElement(By.xpath("//*[@name='user_first_name']"));
        firstNameEle.click();
        firstNameEle.sendKeys(firstName);
        //LastName Input
        WebElement lastNameEle = driver.findElement(By.xpath("//*[@name='user_last_name']"));
        lastNameEle.click();
        lastNameEle.sendKeys(lastName);
        Thread.sleep(2000);
        //"Let's do this" button
        driver.findElement(By.xpath("//*[@id='submit-btn']/button")).click();

        //******Page 5 *******
        //lemonade page Maya Assistant(Home Address)
        Thread.sleep(15000);
        //Address Input
     
        Thread.sleep(8000);       
        WebElement homeAddressEle = driver.findElement(By.xpath("//div[@class='geosuggest__input-wrapper']/input"));
        // homeAddressEle.click(); 
        Thread.sleep(10000);
        homeAddressEle.sendKeys(homeAddress,Keys.ENTER);
        Thread.sleep(15000);
        
        //Selecting home address options
        driver.findElement(By.xpath("//ul[@class='geosuggest__suggests']/li[1]")).click();
        //Thread.sleep(8000);
        
        driver.findElement(By.xpath("//div[@class='input-container  text']/input")).sendKeys(apt);
        
        Thread.sleep(8000);
        
        //Next button():
        
        driver.findElement(By.xpath("//div[@id='submit-btn']/button[contains(text(),'Next')]")).click();
       


        //******Page 6 *******
        //lemonade page Maya Assistant(HOME SAFETY)
         //Selecting Home Safety option
        
        Thread.sleep(8000);
        String homeSafetyElement = "//*[@id='questions_form']/div/label[1]/div/div[3]//h5[text()='" + homeSafety + "']";
        
        Thread.sleep(12000);
        driver.findElement(By.xpath(homeSafetyElement)).click();
        Thread.sleep(1000);
        //Next button
        driver.findElement(By.xpath("//div[@id='submit-btn']/button[contains(text(),'Next')]")).click();
    }

}
