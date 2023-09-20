package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import steps.PageInitializers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializers {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication()  {
        ConfigReader.readProperties();

        String browserType = ConfigReader.getValueOfProp("browserType");
        switch (browserType) {
            case "Chrome":
                ChromeOptions ops = new ChromeOptions();
                //ops.addArguments("--no-sandbox");
               // ops.addArguments("--remote-allow-origins=*");
                //if(ConfigReader.getValueOfProp("Headless").equals("true")){
                    //ops.addArguments("--headless=new");
               //}
                //driver = new ChromeDriver(ops);
                //break;
                ops.addArguments("--headless");
                driver = new ChromeDriver(ops);
                break;


            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
        }
        driver.get(ConfigReader.getValueOfProp("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));

        initializePageObjects();

        //test webhook
        //bravo!!!

    }
    public static void doClick(WebElement element){
        element.click();
    }


    public static void sendText(WebElement element,String text){
        element.clear();
        element.sendKeys(text);
    }

    public static void closeBrowser(){
        if (driver != null) {
            driver.quit();
        }
    }

    public static byte[] takeScreenshot(String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
        //we write this line because cucumber accepts array of byte for screenshot
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File screenShot = ts.getScreenshotAs(OutputType.FILE);
        //in case if it doesn't find file name or path it will throw an exception

        try{
            FileUtils.copyFile(screenShot,
                    new File(Constants.SCREENSHOT_FILEPATH + fileName+" "
                            +getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern){
        //it returns the current date and time in java
        Date date = new Date();
        //this function sdf used to format the date as per the pattern we are passing
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //this line is going to return the formatted date
        return sdf.format(date);
    }





}
