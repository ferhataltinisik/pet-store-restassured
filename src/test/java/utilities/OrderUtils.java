package utilities;

import io.restassured.response.Response;
import model.Order;

import static org.hamcrest.Matchers.equalTo;

public class OrderUtils extends BaseUtils{

    public static Response placeOrder(Order order) {
        String url = ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point");
        String payload = createJSONPayload(order).toString();
        return sendRequest( BaseUtils.POST, url, payload);
    }

    public static Response getOrderDetails(int orderId) {
        String url = ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point") + orderId;
        return sendRequest(BaseUtils.GET, url, null);
    }

    public static Response deleteOrder(int orderId) {
        String url = ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point") + orderId;
        return sendRequest(BaseUtils.DELETE, url, null);
    }


    public static void verifyOrderIsAsExpected(Response response, Integer orderId) {
        response.then().assertThat().body("complete", equalTo(true)).
                and().body("status", equalTo("placed")).
                and().body("complete", equalTo(true));
    }

    public static void verifyMessage(Response response, String errorMessage) {
        response.then().assertThat().body("message", equalTo(errorMessage));
    }

}
