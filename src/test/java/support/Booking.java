package support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Booking {

    private int bookingid;
    private Object booking;
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @JsonIgnore
    @JsonGetter
    public int getBookingid() {
        return bookingid;
    }
    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }
    @JsonIgnore
    @JsonGetter
    public Object getBooking() {
        return booking;
    }
    public void setBooking(Object booking) {
        this.booking = booking;
    }
    @JsonGetter
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    @JsonGetter
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public boolean isDepositpaid() {
        return depositpaid;
    }
    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }
    public String getAdditionalneeds() {
        return additionalneeds;
    }
    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
    @JsonGetter
    public int getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
    @JsonGetter
    public BookingDates getBookingdates() {
        return bookingdates;
    }
    @JsonSetter
    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }
    public static class BookingDates {
        public String checkin;
        public String checkout;
        @JsonGetter
        public String getCheckin() {
            return checkin;
        }
        @JsonSetter
        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }
        @JsonGetter
        public String getCheckout() {
            return checkout;
        }
        @JsonSetter
        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }
    }
}