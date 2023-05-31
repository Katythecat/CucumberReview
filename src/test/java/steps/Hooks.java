package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

    @Before
    public static void preConditions(){
        openBrowserAndLaunchApplication();
    }

    @After
    public static void postConditions(){

        closeBrowser();

    }
}
