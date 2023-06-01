package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification generateTokenRequest =
                given().header(APIConstants.HEADER_KEY_CONTENT_TYPE,APIConstants.HEADER_VALUE_CONTENT_TYPE).
                        body("{\n" +
                                "  \"email\": \"katyisacat1@test.com\",\n" +
                                "  \"password\": \"test123\"\n" +
                                "}");
        // hitting the endpoint
        Response response = generateTokenRequest.when().post(APIConstants.GENERATE_TOKEN_URI);

        //storing the token in global variable
        token="Bearer "+response.jsonPath().getString("token");
        System.out.println(token);
    }
}
