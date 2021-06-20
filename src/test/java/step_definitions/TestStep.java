package step_definitions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import utilities.BaseUtils;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestStep {

    Response response;

    @Test
    public void createRepoTest() throws IOException {
        String endPoint = BaseUtils.getBaseUri("store/order/10");
//        String requestPayload = payloadGenerator.generateStringPayload("createRepo.json");
//        Order order = new Order();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(order);
        System.out.println(endPoint);

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
//        response = BaseUtils.sendRequest(requestSpecification, BaseUtils.GET, endPoint, null);
//        response = BaseClass.getRequest(endPoint);
        assertThat(response.getStatusCode(), is(200));


    }
}
