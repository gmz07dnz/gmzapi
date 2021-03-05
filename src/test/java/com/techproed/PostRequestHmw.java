package com.techproed;

import TestBase.TestBaseHerokuApp;
import TestData.HerokuappTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequestHmw extends TestBaseHerokuApp {

    	/*
	 	When
	 		I send POST Request to the Url https://restful-booker.herokuapp.com/booking
	 		with the request body {
								    "firstname": "Selim",
								    "lastname": "Ak",
								    "totalprice": 11111,
								    "depositpaid": true,
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
											        "firstname": "Selim",
											        "lastname": "Ak",
											        "totalprice": 11111,
											        "depositpaid": true,
											        "bookingdates": {
											            "checkin": "2020-09-09",
											            "checkout": "2020-09-21"
											        }
											    }
											 }
	 */


    @Test
    public void getPost(){
        // Url git
        HerokuappTestData expectedObj = new HerokuappTestData();
        JSONObject expectedBodyData = expectedObj.setUpDataJSONObject();
        System.out.println(expectedBodyData);
        // Request gonder
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().
                basic("admin","password123").
                //JSonObject ile body gonderirken toString kullanmak gerekir.
                        body(expectedBodyData.toString()).
                        when().
                        post();
        response.prettyPrint();


        // Assertion
        // 1.Body ile
        response.then().assertThat().statusCode(200).
                body("booking.firstname",equalTo(expectedBodyData.getString("firstname")),
                        "booking.lastname",equalTo(expectedBodyData.getString("lastname")),
                        "booking.totalprice",equalTo(expectedBodyData.getInt("totalprice")),
                        "booking.depositpaid",equalTo(expectedBodyData.getBoolean("depositpaid")),
                        "booking.bookingdates.checkin",equalTo(expectedBodyData.getJSONObject("bookingdates").getString("checkin")),
                        "booking.bookingdates.checkout",equalTo(expectedBodyData.getJSONObject("bookingdates").getString("checkout")));

        // 2.yol Hard Assert JSonObject ve Jsonpath

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedBodyData.getString("firstname"),jsonPath.getString("booking.firstname"));
        Assert.assertEquals(expectedBodyData.getString("lastname"),jsonPath.getString("booking.lastname"));
        Assert.assertEquals(expectedBodyData.getInt("totalprice"),jsonPath.getInt("booking.totalprice"));
        Assert.assertEquals(expectedBodyData.getBoolean("depositpaid"),jsonPath.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedBodyData.getJSONObject("bookingdates").getString("checkin"),jsonPath.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedBodyData.getJSONObject("bookingdates").getString("checkout"),jsonPath.getString("booking.bookingdates.checkout"));

        // 3.yol SofAssert JsonObject ve Gson ( De-Sterilization -> gelen datayı java objesine donusturuyor as metodu ile)
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("firstname"),expectedBodyData.getString("firstname"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("lastname"),expectedBodyData.getString("lastname"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("totalprice"),expectedBodyData.getInt("totalprice"));
        softAssert.assertEquals(((Map)actualDataMap.get("booking")).get("depositpaid"),expectedBodyData.getBoolean("depositpaid"));
        softAssert.assertEquals( ((Map)(((Map)actualDataMap.get("booking")).get("bookingdates"))).get("checkin"),expectedBodyData.getJSONObject("bookingdates").getString("checkin"));
        softAssert.assertEquals( ((Map)(((Map)actualDataMap.get("booking")).get("bookingdates"))).get("checkout"),expectedBodyData.getJSONObject("bookingdates").getString("checkout"));



        softAssert.assertAll();

    }
}
