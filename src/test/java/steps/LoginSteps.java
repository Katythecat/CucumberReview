package steps;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;



public class LoginSteps extends CommonMethods   {


    @Given("the user navigates to the url")
    public void the_user_navigates_to_the_url(){
        openBrowserAndLaunchApplication();

    }
    @When("user enters a valid email and password")
    public void user_enters_a_valid_email_and_password() {

        //driver.findElement(By.id("txtUsername")).sendKeys(ConfigReader.getValueOfProp("username"));

        sentText(login.usernameTextBox, ConfigReader.getValueOfProp("username"));

        //driver.findElement(By.id("txtPassword")).sendKeys(ConfigReader.getValueOfProp("password"));

        sentText(login.passwordTextBox,ConfigReader.getValueOfProp("password"));

    }
    @When("clicks on Login Button")
    public void clicks_on_login_button() {

        doClick(login.loginBtn);
    }
    @Then("the user is logged in")
    public void the_user_is_logged_in() {
        //assertion that u are logged in
        System.out.println("logged in");


    }

    @When("user enters a username {string} and password {string}")
    public void user_enters_a_username_and_password(String username, String password) {
        WebElement usernameTextBox = driver.findElement(By.id("txtUsername"));
        WebElement passwordTextBox = driver.findElement(By.id("txtPassword"));
        sentText(usernameTextBox,username);
        sentText(passwordTextBox,password);
    }

    @Then("the user is not logged in")
    public void the_user_is_not_logged_in() {
        System.out.println("user is not logged in");
    }

    @When("user enters the {string} ane {string}")
    public void user_enters_the_ane(String username, String password) {
        sentText(login.usernameTextBox,username );
        sentText(login.passwordTextBox,password);

    }
    @Then("user see a message {string}")
    public void user_see_a_message(String errorMsg) {
        System.out.println(errorMsg);

    }




}
