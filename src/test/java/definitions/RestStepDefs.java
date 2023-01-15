package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.Booking;
import support.RestClient;
import support.TestContext;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;
import static support.TestContext.getBooking;

public class RestStepDefs {

    RestClient restClient = new RestClient();

    @Given("I login POST via REST API as {string}")
    public void iLoginPOSTViaRESTAPIAs(String role) {
        Map<String, String> credentials = TestContext.getDataByFileName(role);
        restClient.login(credentials);
    }
    @When("I create POST via REST API {string} order")
    public void iCreatePOSTViaRESTAPIOrder(String type) {
        Booking booking = getBooking(type);
        restClient.createBooking(booking);
    }
    @Then("I verify GET via REST API new order is in the list")
    public void iVerifyGETViaRESTAPINewOrderIsInTheList() {
        int createdBookingId = TestContext.getTestDataInteger("bookingid");
        List<Booking> list = restClient.getBooking();
        boolean isFound = false;
        for (Booking booking : list) {
            int bookingId = booking.getBookingid();
            if (bookingId == createdBookingId) {
                isFound = true;
                break;
            }
        }
        assertThat(isFound).isTrue();
    }
    @When("I update PUT via REST API new {string} order")
    public void iUpdatePUTViaRESTAPINewOrder(String type) {
        Booking updatedBooking = getBooking(type);
        int createdBookingId = TestContext.getTestDataInteger("bookingid");
        restClient.updateBooking(createdBookingId, updatedBooking);
    }
    @Then("I verify GET via REST API new {string} order is updated")
    public void iVerifyGETViaRESTAPINewOrderIsUpdated(String type) {
        int updatedBookingId = TestContext.getTestDataInteger("bookingid");
        Booking actualBooking = restClient.getBooking(updatedBookingId);
        Booking expectedBooking = getBooking(type);
        assertThat(expectedBooking.getFirstname()).isEqualTo(actualBooking.getFirstname());
        assertThat(expectedBooking.getLastname()).isEqualTo(actualBooking.getLastname());
        assertThat(expectedBooking.getTotalprice()).isEqualTo(actualBooking.getTotalprice());
        assertThat(expectedBooking.isDepositpaid()).isTrue();
        String expectedCheckin = expectedBooking.getBookingdates().getCheckin();
        String actualCheckin = actualBooking.getBookingdates().getCheckin();
        assertThat(expectedCheckin).isEqualTo(actualCheckin);
        String expectedCheckout = expectedBooking.getBookingdates().getCheckout();
        String actualCheckout = actualBooking.getBookingdates().getCheckout();
        assertThat(expectedCheckout).isEqualTo(actualCheckout);
        assertThat(expectedBooking.getAdditionalneeds()).isEqualTo(actualBooking.getAdditionalneeds());
    }
    @When("I delete DELETE via REST API new order")
    public void iDeleteDELETEViaRESTAPINewOrder() {
        int updatedBookingId = TestContext.getTestDataInteger("bookingid");
        restClient.deleteBooking(updatedBookingId);
    }
    @Then("I verify GET via REST API new order is deleted")
    public void iVerifyGETViaRESTAPINewOrderIsDeleted() {
        int deletedBookingId = TestContext.getTestDataInteger("bookingid");
        List<Booking> list = restClient.getBooking();
        boolean isFound = false;
        for (Booking booking : list) {
            int bookingId = booking.getBookingid();
            if (bookingId == deletedBookingId) {
                isFound = true;
                break;
            }
        }
        assertThat(isFound).isFalse();
    }
}
