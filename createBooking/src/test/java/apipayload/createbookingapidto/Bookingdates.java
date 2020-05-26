package apipayload.createbookingapidto;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;



public class Bookingdates {

@JsonProperty("checkin")
private String checkin;
@JsonProperty("checkout")
private String checkout;


@JsonProperty("checkin")
public String getCheckin() {
return checkin;
}

@JsonProperty("checkin")
public void setCheckin(String checkin) {
this.checkin = checkin;
}

@JsonProperty("checkout")
public String getCheckout() {
return checkout;
}

@JsonProperty("checkout")
public void setCheckout(String checkout) {
this.checkout = checkout;
}

}