package support;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class RestClient {



    public void login(Map<String, String> credentials) {
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("username", credentials.get("username"));
        loginBody.put("password", credentials.get("password"));

        RequestSpecification request = RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(loginBody)
                .log().all();
        Response response = request
                .when()
                .post("/auth");
        Map<String, Object> serverPayload = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");
        assertThat((Boolean) serverPayload.containsKey("token")).isTrue();
    }
    public void createBooking(Booking booking) {
        RequestSpecification request = RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(booking)
                .log().all();
        Response response = request
                .when()
                .post("/booking");
        Booking bookingResponse = response
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .as(Booking.class);
        int bookingId = bookingResponse.getBookingid();
        TestContext.saveData("bookingid", bookingId);
    }
    public List<Booking> getBooking() {
        RequestSpecification request = RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .log().all();
        Response response = request
                .when()
                .get("/booking");
        return response
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("", Booking.class);
    }
    public void updateBooking(int bookingId, Booking updatedBooking) {
        Map<String, String> credentials = TestContext.getDataByFileName("username_password");
        RequestSpecification request = RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .auth().preemptive().basic(credentials.get("username"), credentials.get("password"))
                .header("Content-Type", "application/json")
                .body(updatedBooking)
                .log().all();
        Response response = request
                .when()
                .put("/booking/" + bookingId);
        response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Booking.class);
        System.out.println("Booking ID: " + bookingId);
    }
    public Booking getBooking(int bookingId) {
        RequestSpecification request = RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .log().all();
        Response response = request
                .when()
                .get("/booking/" + bookingId);
        return response
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .as(Booking.class);
    }
    public void deleteBooking(int bookingId) {
        Map<String, String> credentials = TestContext.getDataByFileName("username_password");
        RequestSpecification request = RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .auth().preemptive().basic(credentials.get("username"), credentials.get("password"))
                .log().all();
        Response response = request
                .when()
                .delete("/booking/" + bookingId);
        response
                .then()
                .log()
                .all()
                .statusCode(201);
    }
}