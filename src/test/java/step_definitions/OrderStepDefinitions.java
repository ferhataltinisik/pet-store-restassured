package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Order;
import utilities.*;

import static org.hamcrest.Matchers.equalTo;


public class OrderStepDefinitions {
    BaseUtils baseUtils;
    OrderUtils orderUtils;
    Response response = null;



    @Given("the endpoint is up")
    public void the_endpoint_is_up() {
        String url = baseUtils.getBaseUri("swagger.json");
        RestAssured.when().get(url).then().statusCode(baseUtils.SUCCESS_STATUS_CODE);
    }

    @When("i post an order for a pet by orderId {int}")
    public void  i_post_an_order_for_a_pet_by_order_id(Integer orderId) {
        Order order = new Order(orderId, 1, 1, "placed", true, "2019-02-05T14:11:44.922Z");
        response= orderUtils.placeOrder( order);
    }

    @Then("the status code returns {int}")
    public void the_status_code_returns(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("pet order is completed and status set as {string} successfully")
    public void pet_order_is_completed_and_status_set_as_successfully(String orderStatus) {
        response.then().assertThat().body("status", equalTo(orderStatus));
    }

    @When("i try to get order details by orderId {int}")
    public void iTryToGetOrderDetailsByOrderId(int orderId) {
        response = orderUtils.getOrderDetails( orderId);
    }

    @Then("order details should be able to displayed for orderId {int} successfully")
    public void order_details_should_be_able_to_displayed_for_order_id_successfully(Integer orderId) {
        orderUtils.verifyOrderIsAsExpected(response, orderId);
    }


    @When("i try to delete order details by orderId {int}")
    public void iTryToDeleteOrderDetailsByOrderId(int orderId) {
        response = orderUtils.deleteOrder(orderId);
    }

    @Then("order should be deleted successfully")
    public void orderShouldBeDeletedSuccessfully() {
        response.then().statusCode(baseUtils.SUCCESS_STATUS_CODE);
    }

    @Then("error message should be received {string}")
    public void errorMessageShouldBeReceived(String errorMessage) {
        orderUtils.verifyMessage(response, errorMessage);

    }

    @Then("verify unavailable order")
    public void verifyUnavailableOrder() {
            response.then().assertThat().body("type", equalTo("unknown"))
            .and().statusCode(404);
    }

}
