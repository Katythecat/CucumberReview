package steps;

import Pages.LoginPage;
import utils.CommonMethods;

public class PageInitializers  {

    public static LoginPage login;

    public static void initializePageObjects(){
        login = new LoginPage();
    }
}
