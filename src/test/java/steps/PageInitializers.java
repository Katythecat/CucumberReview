package steps;

import Pages.AddEmployPage;
import Pages.DashboardPage;
import Pages.EmployeeListPage;
import Pages.LoginPage;



public class PageInitializers  {

    public static LoginPage login;
    public static AddEmployPage addEmp;
    public static DashboardPage dashboard;
    public static EmployeeListPage employeeList;

    public static void initializePageObjects(){
        login = new LoginPage();
        addEmp=new AddEmployPage();
        dashboard=new DashboardPage();
        employeeList=new EmployeeListPage();
    }
}
