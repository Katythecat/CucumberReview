package steps;

import Pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
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

        sendText(login.usernameTextBox, ConfigReader.getValueOfProp("username"));
        sendText(login.passwordTextBox,ConfigReader.getValueOfProp("password"));

    }
    @When("clicks on Login Button")
    public void clicks_on_login_button() {

        doClick(login.loginBtn);
    }
    @Then("the user is logged in")
    public void the_user_is_logged_in() {

        String actualMsg=dashboard.welcomeText.getText();
        String expected="Welcome Admin";

        Assert.assertEquals(actualMsg,expected);

        //we have only hard assert in cucumber
        //.assertEquals and .assertTrue

    }

    @When("user enters a username {string} and password {string}")
    public void user_enters_a_username_and_password(String username, String password) {
        WebElement usernameTextBox = driver.findElement(By.id("txtUsername"));
        WebElement passwordTextBox = driver.findElement(By.id("txtPassword"));
        sendText(usernameTextBox,username);
        sendText(passwordTextBox,password);
    }

    @Then("the user is not logged in")
    public void the_user_is_not_logged_in() {
        System.out.println("user is not logged in");
    }

    @When("user enters the {string} ane {string}")
    public void user_enters_the_ane(String username, String password) {
        sendText(login.usernameTextBox,username );
        sendText(login.passwordTextBox,password);

    }
    @Then("user see a message {string}")
    public void user_see_a_message(String expectedErrorMsg) throws InterruptedException {
        Thread.sleep(5000);
        String actualError=login.errorLogin.getText();
        Assert.assertEquals(actualError,expectedErrorMsg);





    }




}
