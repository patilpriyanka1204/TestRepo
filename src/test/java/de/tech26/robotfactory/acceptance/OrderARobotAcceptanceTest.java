package de.tech26.robotfactory.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import org.hamcrest.CoreMatchers.equalTo;
//import org.hamcrest.CoreMatchers.notNullValue;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderARobotAcceptanceTest {

    @LocalServerPort
    private int port;

    @Test
    public void shouldOrderARobot() throws JSONException {

        JSONObject json = new JSONObject("{ \n" +
                "                        \"component\": [\"I\",\"A\",\"D\",\"F\"]\n" +
                        "                    }");

        postOrder(
                json.toString()
        ).then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .body("order_id", org.hamcrest.CoreMatchers.notNullValue())
            .body("total", org.hamcrest.CoreMatchers.equalTo(160.11f));
    }


    @Test
    public void shouldNotAllowInvalidBody() {
        //String body = "{\"components\": \"BENDER\"}";
        postOrder(
                "{\"component\": \"BENDER\"}"
        ).then()
            .assertThat()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldNotAllowInvalidRobotConfiguration() {

        postOrder(
                "{\n" +
                        "                        \"component\": [\"A\", \"C\", \"I\", \"D\"]\n" +
                        "                    }"
        ).then()
            .assertThat()
            .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    private Response postOrder(String body) {//fun postOrder(body: String) = RestAssured
       return RestAssured.given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/orders");
    }
}
