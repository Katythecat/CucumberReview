package API;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APIdemo {

    String baseURI=RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODU0NjIzMTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTUwNTUxOSwidXNlcklkIjoiNTUzMiJ9.QP_lc_lkT8cwFhq3Kyt0prSEc1ujA57dZr_th-rVaMc";
    static String employee_id;

    @Test
    public void acreateEmployee(){
        //prepare the request
        RequestSpecification preparedRequest= given().
                header("Content-Type","application/json").
                header("Authorization",token).
                body("{\n" +
                        "  \"emp_firstname\": \"Sohel\",\n" +
                        "  \"emp_lastname\": \"Swagger\",\n" +
                        "  \"emp_middle_name\": \"SH\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1990-05-30\",\n" +
                        "  \"emp_status\": \"Full-Time\",\n" +
                        "  \"emp_job_title\": \"Manager\"\n" +
                        "}");

        //hitting the endpoint/send the request
        Response response=preparedRequest.when().post("/createEmployee.php");

        //verifying the assertion/get response
        response.then().assertThat().statusCode(201);
        response.prettyPrint();

        //we have to use jsonPath() to capture the employee id from the response
        employee_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        //verifying the firstname in the response body
        //by using hamcrest matchers
        response.then().assertThat().
                body("Employee.emp_firstname",equalTo("Sohel")).
                body("Employee.emp_lastname",equalTo("Swagger"));

        //verifying the response header
        response.then().assertThat().header("Content-Type","application/json");
        System.out.println("My test case is passed");
    }

    @Test
    public void bgetCreateEmployee(){
        RequestSpecification preparedRequest=given().
                header("Content-Type","application/json").
                header("Authorization",token).
                queryParam("employee_id",employee_id);

        Response response=preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        String tempEmpId=response.jsonPath().getString("employee.employee_id");

        //we have 2 employee id, 1 is global and 2 is local
        // how can we verify
        Assert.assertEquals(employee_id,tempEmpId); // cucumber assertion

    }
    @Test
    public void cupdateEmployee(){
        RequestSpecification preparedRequest=given().
                header("Content-type","application/json").
                header("Authorization",token).
                body("{\n" +
                        "    \"employee_id\": \""+employee_id+"\",\n" +
                        "    \"emp_firstname\": \"Christian\",\n" +
                        "    \"emp_lastname\": \"Miami\",\n" +
                        "    \"emp_middle_name\": \"M\",\n" +
                        "    \"emp_gender\": \"M\",\n" +
                        "    \"emp_birthday\": \"1980-05-20\",\n" +
                        "    \"emp_status\": \"Part-time\",\n" +
                        "    \"emp_job_title\": \"Boss\"\n" +
                        "}");

        Response response=preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        //verify with ham
        response.then().assertThat().body("Message",equalTo("Employee record Updated"));

    }
}
