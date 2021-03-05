package com.techproed;

import TestBase.TestBaseHerokuApp;
import TestData.HerokuappTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetRequest13 extends TestBaseHerokuApp {
    /*
	 	When
	 		I send GET Request to https://restful-booker.herokuapp.com/booking/1
	 	Then
	 		Response body should be like that;
	 		{
			    "firstname": "Eric",
			    "lastname": "Smith",
			    "totalprice": 555,
			    "depositpaid": false,
			    "bookingdates": {
			        "checkin": "2016-09-09",
			        "checkout": "2017-09-21"
			     }
			}
	*/

    @Test
    public void test(){
        // Url al
        spec02.pathParams("id",1);

        // expected data olustur
        HerokuappTestData expectedDataObj = new HerokuappTestData();
        Map<String,Object> expectedDataMap = expectedDataObj.setUp();
        System.out.println(expectedDataMap);

        // response olustur
        Response response = given().
                            spec(spec02).
                            when().
                            get("/{id}");
         response.prettyPrint();

        // Assert De-Sterilization ile

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(expectedDataMap,actualDataMap);
        // Tek tek Assert de eebiliriz

        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),(((Map)actualDataMap.get("bookingdates"))
                .get("checkin")));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),(((Map)actualDataMap.get("bookingdates"))
                .get("checkout")));

    }
}
