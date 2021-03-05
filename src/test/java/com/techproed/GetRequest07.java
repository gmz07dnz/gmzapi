package com.techproed;

import TestBase.TestBaseHerokuApp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest07 extends TestBaseHerokuApp {

    /*
     * When I send a GET request to REST API URL
     * https://restful-booker.herokuapp.com/booking/5
     * Then HTTP Status Code should be 200
     * And response content type is “application/JSON”
     * And response body should be like;
     * {"firstname": "Sally",
     *   "lastname": "Ericsson",
     *   "totalprice": 111,
     *   "depositpaid": false,
     *   "bookingdates": { "checkin": "2017-05-23",
     *                     "checkout":"2019-07-02" }
     * }
     */

    @Test
    public void test(){
        // url olusturma
        spec02.pathParam("bookingid",5);
        //request olusturuyoruz
        Response response = given().
                           spec(spec02).
                            when().
                            get("/{bookingid}");
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Susan"),
                      "lastname",equalTo("Brown"),
                       "bookingdates.checkin",equalTo("2017-07-09") );

        // Json path- Response icinde hareket edebilmemizi ve degerlere ulasabilmemizi saglar
        JsonPath jsonPath = response.jsonPath();
        System.out.println("firstname: "+jsonPath.getString("firstname"));
        Assert.assertEquals("firstname istenilen gibi degil","Sally",jsonPath.getString("firstname"));
        System.out.println(jsonPath.getString("lastname"));
        Assert.assertEquals("lastname istenilen gibi degil","Ericson",jsonPath.getString("lastname"));
        System.out.println(jsonPath.getInt("totalprice"));
        Assert.assertEquals("totalprice istenilen gibi degil",111,jsonPath.getInt("totalprice"));
        System.out.println("DEposit paid "+ jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("Deposit paid beklenen gibi degil ",true,jsonPath.getBoolean("depositpaid"));
        System.out.println("Check in tarihi "+ jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("Check in tarihi istenilen gibi degil ","2019-10-20",jsonPath.getString("bookingdates.checkin"));
        System.out.println("Check out tarihi "+ jsonPath.getString("bookingdates.checkout"));
        Assert.assertEquals("Check out tarihi istenilen gibi degil ","2020-08-25",jsonPath.getString("bookingdates.checkout"));
    }
}
