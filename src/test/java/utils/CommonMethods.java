package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import steps.PageInitializers;

import java.io.IOException;
import java.time.Duration;

public class CommonMethods extends PageInitializers {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication()  {
        ConfigReader.readProperties();

        String browserType = ConfigReader.getValueOfProp("browserType");
        switch (browserType) {
            case "Chrome":
                driver = new ChromeDriver();
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

    }
    public static void doClick(WebElement element){
        element.click();
    }


    public static void sendText(WebElement element,String text){
        element.clear();
        element.sendKeys(text);
    }

    public static void closeBrowser(){
        driver.close();
    }
}