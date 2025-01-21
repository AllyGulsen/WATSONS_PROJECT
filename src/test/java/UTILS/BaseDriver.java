package UTILS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseDriver {

    public WebDriver driver;

    @BeforeTest
    public void setUpDriverMethod(){
        //CHROME
        DesiredCapabilities desiredCap = new DesiredCapabilities();
        ChromeOptions options= new ChromeOptions();

        //BİLDİRİMLERİ HANDLE ETME
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-notifications");
        options.addArguments("--acceptInsecureCerts");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("chrome.switches","--disable-extentions");
        options.setExperimentalOption("useAutomationExtension",false);
        options.merge(desiredCap);


        driver= new ChromeDriver(options);



    }

/*
    @AfterTest
     public void quitDriverMethod(){

        driver.quit();



    }

 */



}
