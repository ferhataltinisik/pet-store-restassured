package utilities;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BaseUtils {
    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int BAD_REQUEST_STATUS_CODE = 400;

    public static final int POST = 1;
    public static final int GET = 2;
    public static final int DELETE = 3;
    public static final int PUT = 4;

    public static String getBaseUri(){
        return ConfigurationReader.get("baseUri");

    }

    public static String getBaseUri(String resourcePath){
        return ConfigurationReader.get("baseUri") + resourcePath;
    }


    public static Response getRequest(String requestUri){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        return requestSpecification.get(requestUri);
    }

    public static Response postRequest(String requestUri, String payLoad){
        RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.post(requestUri);
        return response;
    }

    public static Response deleteRequest(String requestUri){
//        RequestSpecification requestSpecification = RestAssured.given();
////        requestSpecification.contentType(ContentType.JSON);
//        Response response = requestSpecification.delete(requestUri);

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        return request.delete(requestUri);

    }
//Token
//    public static Response postRequest(String requestUri, String payLoad, String bearerToken){
//        RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
//        requestSpecification.contentType(ContentType.JSON);
//        requestSpecification.head("Authorization", "Bearer" + bearerToken);
//        Response response = requestSpecification.post(requestUri);
//        return response;
//    }

    public static Response putRequest(String requestUri, String payLoad){
        RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.put(requestUri);
        return response;
    }

    public static Response deleteRequestWithPayload(String requestUri, String payLoad){
        RequestSpecification requestSpecification = RestAssured.given().body(payLoad);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.delete(requestUri);
        return response;
    }


    public void verifyResponseStatusCode(Response response, int expectedCode) {
        assertThat(response.getStatusCode(), is(expectedCode));
    }

// ************************************************************

    public String editDate(String dateString) {
        return dateString.replace("Z", "+0000");
    }

    public RequestSpecification createHeaders() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.header("accept", "application/json");
        return requestSpecification;
    }
    public static JSONObject createJSONPayload(Object object) {
        return new JSONObject(object);
    }

    public static Response sendRequest(  int httpRequestType, String url, Object order) {
        RequestSpecification requestSpecification = null;

//        String payload = createJSONPayload(order).toString();
        if (order != null ) {
            String payload = createJSONPayload(order).toString();
            requestSpecification.body(payload);
        }

        Response response = null;
        RestAssured.when().get(url);
//        requestSpecification = RestAssured.given().body(payload);
        requestSpecification.contentType(ContentType.JSON);

//        RequestSpecification requestSpecification = RestAssured.given();
//        requestSpecification.contentType(ContentType.JSON);

        // Add the Json to the body of the request
//        if (object != null ) {
//            String payload = createJSONPayload(object).toString();
//            requestSpecification.body(payload);
//        }

        // need to add a switch based on request type
        switch (httpRequestType) {
            case BaseUtils.POST:

                response = requestSpecification.post(url);
                break;
            case BaseUtils.GET:
                response = requestSpecification.get(url);
                break;
//            case BaseUtils.DELETE:
//                    response = requestSpecification.delete(url);
//                break;
//            case BaseUtils.PUT:
//                    response = requestSpecification.put(url);
//                break;
//            default:
//                response = requestSpecification.post(url);
//                break;
        }
        return response;
    }
}
