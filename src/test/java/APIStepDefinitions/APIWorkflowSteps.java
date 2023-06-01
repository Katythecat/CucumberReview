package APIStepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkflowSteps {
    RequestSpecification preparedRequest;
    Response response;
    public static String employee_id;

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
         preparedRequest=given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                        APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION,
                        GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response=preparedRequest.when().post(APIConstants.CREATE_EMPLOYEE_URI);
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        response.prettyPrint();
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee contains key {string} and value {string}")
    public void the_employee_contains_key_and_value(String string, String string2) {
        response.then().assertThat().body(string,equalTo(string2));
    }

    @Then("the employee id {string} is stored as a global variable to be used for other call")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_call(String string) {
        employee_id=response.jsonPath().getString(string);
        System.out.println(employee_id);
    }

    @Given("a request is prepared to create an employee using json payload")
    public void a_request_is_prepared_to_create_an_employee_using_json_payload() {
        preparedRequest=given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                        APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION,
                        GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayloadJson());
    }

    @Given("a request is prepared to get the create an employee")
    public void a_request_is_prepared_to_get_the_create_an_employee() {
        preparedRequest = given().header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION,GenerateTokenSteps.token).
                queryParam("employee_id",employee_id);
    }

    @When("a GET call is made to get the employee")
    public void a_get_call_is_made_to_get_the_employee() {
        response=preparedRequest.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee data we get having id {string} much match with global store")
    public void the_employee_data_we_get_having_id_much_match_with_global_store(String string) {
        String tempEmpID=response.jsonPath().getString(string);
        Assert.assertEquals(tempEmpID,employee_id);
    }

    @Then("the retrieved data at {string} object mathces with the data of create employee")
    public void the_retrieved_data_at_object_mathces_with_the_data_of_create_employee(String empObject, DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class,String.class);
        Map<String,String> actualData=response.body().jsonPath().get(empObject);
        for(Map<String,String> map : expectedData){
            Set<String> keys = map.keySet();
            for(String key : keys){
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue,actualValue);
            }
        }
    }

    @Given("a request is prepared to create an employee with dynamic data {string},{string},{string},{string},{string},{string},{string}")
    public void a_request_is_prepared_to_create_an_employee_with_dynamic_data(
            String string,String string2, String string3, String string4,
            String string5, String string6, String string7) {
        preparedRequest=given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                        APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION,
                        GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayloadDynamic(
                        string,string2,string3,string4,string5,string6,string7));
    }

    @Given("a request is prepared to update an employee")
    public void a_request_is_prepared_to_update_an_employee() {
        preparedRequest=given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE,
                        APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION,GenerateTokenSteps.token).
                body(APIPayloadConstants.updateEmployeePayloadJson());

    }
    @When("a PUT call is made to update an employee")
    public void a_put_call_is_made_to_update_an_employee() {
        response=preparedRequest.when().put(APIConstants.UPDATE_EMPLOYEE_URI);
    }

    @Then("the status code of updated employee is {int}")
    public void the_status_code_of_updated_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1);

    }

}
