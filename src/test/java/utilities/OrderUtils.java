package utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Order;
import org.junit.Assert;

import static org.hamcrest.Matchers.equalTo;

public class OrderUtils extends BaseUtils{

    private World world;
    public OrderUtils(World world) {
        this.world = world;
    }

    private Order defaultOrder;



//    public static String getOrderEndPoint() {
//        return ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point");
//    }


    public static Response placeOrder(Order order) {
        String url = ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point");
        String payload = createJSONPayload(order).toString();
        return postRequest(  url, payload);
    }

    public static Response getOrderDetails(int orderId) {
        String url = ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point") + orderId;
        return getRequest(url);
    }

    public static Response deleteOrder(int orderId) {
        String url = ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point") + orderId;
        return deleteRequest(url);
    }


//    public Response giveOrder( Order order) {
//
//        String url = ConfigurationReader.get("baseUri") + ConfigurationReader.get("order_end_point");
//        return sendRequest( BaseUtils.POST, url, order);
//    }
    public static void veryOrderDeleted(){

    }
    public static void veryOrderDeleted(Response response, Integer orderId) {

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
