package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmploySteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        doClick(dashboard.pimTab);

    }
    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        doClick(addEmp.addEmpBtn);

    }
    @When("user starts adding the employee")
    public void user_starts_adding_the_employee(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> employeeNames = dataTable.asMaps();
        for (Map<String, String> employName : employeeNames) {
            String fName = employName.get("firstName");
            String mName = employName.get("middleName");
            String lName = employName.get("lastName");

            //after this we need to perform selenium operation
            sendText(addEmp.firstNameTextBox, fName);
            sendText(addEmp.middleNameTextBox, mName);
            sendText(addEmp.lastNameTextBox, lName);
            doClick(addEmp.saveBtn);

            Thread.sleep(5000);
            doClick(addEmp.addEmpBtn);
        }
    }


    @When("user adds multiple employee from excel using {string} and verify it")
    public void user_adds_multiple_employee_from_excel_using_and_verify_it(String sheetName) throws InterruptedException {
        List<Map<String, String>> empFromExcel = ExcelReader.excelListIntoMap(Constants.TESTDATA_FILEPATH, sheetName);


        //it returns one map from list of maps
        Iterator<Map<String, String>> itr = empFromExcel.iterator();
        while (itr.hasNext()) {
            //it returns the key and value for employee from excel
            Map<String, String> mapNewEmp = itr.next();

            sendText(addEmp.firstNameTextBox, mapNewEmp.get("firstName"));
            sendText(addEmp.middleNameTextBox, mapNewEmp.get("middleName"));
            sendText(addEmp.lastNameTextBox, mapNewEmp.get("lastName"));
            String empIdValue = addEmp.empIdLocator.getAttribute("value");
            sendText(addEmp.photograph, mapNewEmp.get("photograph"));
            if (!addEmp.checkBox.isSelected()) {
                doClick(addEmp.checkBox);
            }
            sendText(addEmp.createusernameField, mapNewEmp.get("username"));
            sendText(addEmp.createpasswordField, mapNewEmp.get("password"));
            sendText(addEmp.confirmpasswordField, mapNewEmp.get("confirmPassword"));

            doClick(addEmp.saveButton);
            System.out.println("click taken on save button");
            //verification is in home-work
            Thread.sleep(3000);

            doClick(dashboard.empListOption);
            Thread.sleep(2000);
            System.out.println("click taken on emp list option");

            //to search the employee, we use emp id what we captured from attribute
            sendText(employeeList.empSearchIdField, empIdValue);
            doClick(employeeList.searchButton);

            //verifying the employee added from the excel file

            List<WebElement> rowData = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));


            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop and worried about josh");
                //getting the text of every element from here and storing it into string
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName") + " " + mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                //verifying the exact details  of the employee
                Assert.assertEquals(expectedData, rowText);

            }

            doClick(dashboard.addEmployeeOption);
            Thread.sleep(2000);
        }
    }


}












