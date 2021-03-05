package com.techproed;

import TestBase.TestBaseHerokuApp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.*;

public class PostRequestWithPojo02 extends TestBaseHerokuApp {

    /*
	 	When
	 		I send POST Request to the Url https://restful-booker.herokuapp.com/booking
	 		with the request body {
								    "firstname": "Eric",
								    "lastname": "Ericson",
								    "totalprice": 900,
								    "depositpaid": false,
								    "bookingdates": {
								        "checkin": "2020-09-09",
								        "checkout": "2020-09-21"
								     }
								  }
	 	Then
	 		Status code is 200
	 		And response body should be like {
											    "bookingid": 11,
											    "booking": {
											        "firstname": "Eric",
											        "lastname": "Ericson",
											        "totalprice": 900,
											        "depositpaid": false,
											        "bookingdates": {
											            "checkin": "2020-09-09",
											            "checkout": "2020-09-21"
											        }
											    }
											 }
	 */

    @Test
    public void postPojo01(){
        // url olusutr

        // expected Data - Request Body olustur
        BookingDatesPojo bookingDates = new BookingDatesPojo("2020-09-09","2020-09-09");
        BookingPojo expectedBookingPojo = new BookingPojo("Eric","Ericson",900,false,bookingDates);

        // Request gonder
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                body(expectedBookingPojo).
                when().
                post();
        response.prettyPrint();

        // 1. yol body ve pojo

        response.then().
                statusCode(200).
                body("booking.firstname",equalTo(expectedBookingPojo.getFirstname()),
                        "booking.lastname",equalTo(expectedBookingPojo.getLastname()),
                        "booking.totalprice",equalTo(expectedBookingPojo.getTotalprice()),
                        "booking.depositpaid",equalTo(expectedBookingPojo.isDepositpaid()),
                        "booking.bookingdates.checkin",equalTo(expectedBookingPojo.getBookingdates().getCheckin()),
                        "booking.bookingdates.checkout",equalTo(expectedBookingPojo.getBookingdates().getCheckout()));
        //  2. yol JsonPATH ve pojo
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedBookingPojo.getFirstname(),jsonPath.getString("booking.firstname"));
        Assert.assertEquals(expectedBookingPojo.getLastname(),jsonPath.getString("booking.lastname"));
        Assert.assertEquals(expectedBookingPojo.getTotalprice(),jsonPath.getInt("booking.totalprice"));
        Assert.assertEquals(expectedBookingPojo.isDepositpaid(),jsonPath.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedBookingPojo.getBookingdates().getCheckin(),jsonPath.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedBookingPojo.getBookingdates().getCheckout(),jsonPath.getString("booking.bookingdates.checkout"));


        // 3.yol Gson - pojo
        // Bookingpojo formatı gelen response'dan farklı, ya Gson kullanmayacagız-- jsonpath ve body
        // ya da yeni bir pojo class olusturup response tan gelenler
        // BookingPojo actualBooking = response.as(BookingPojo);
        // Failure---------------------------------

       BookingResponsePojo actualBooking = response.as(BookingResponsePojo.class);
        System.out.println(actualBooking);
        Assert.assertEquals(expectedBookingPojo.getFirstname(),actualBooking.getBooking().getFirstname());
        Assert.assertEquals(expectedBookingPojo.getLastname(),actualBooking.getBooking().getLastname());
        Assert.assertEquals(expectedBookingPojo.isDepositpaid(),actualBooking.getBooking().isDepositpaid());
        Assert.assertEquals(expectedBookingPojo.getTotalprice(),actualBooking.getBooking().getTotalprice());
        Assert.assertEquals(expectedBookingPojo.getBookingdates().getCheckout(),actualBooking.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(expectedBookingPojo.getBookingdates().getCheckout(),actualBooking.getBooking().getBookingdates().getCheckout());






    }



}
