package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    @FindBy(id = "txtUsername")
    public WebElement usernameTextBox;


    @FindBy(id = "txtPassword")
    public WebElement passwordTextBox;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;
    // Page Factory Model

    @FindBy(id = "welcome")
    public WebElement welcomeIcon;

    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logoutLink;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }



}
