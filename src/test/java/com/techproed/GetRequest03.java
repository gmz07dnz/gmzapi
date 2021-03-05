package com.techproed;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class GetRequest03 {
    /*
	 Positive Scenario:
	 When Asagidaki Endpoint'e bir GET request yolladim
	 https://restful-booker.herokuapp.com/booking/7
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Sally"
     And lastname "Jackson"
     And checkin date "2017-04-19"
     And checkout date "2020-03-22"
	*/

    @Test
    public void test(){
        //When Asagidaki Endpoint'e bir GET request yolladim
        //	 https://restful-booker.herokuapp.com/booking/7
        String url ="https://restful-booker.herokuapp.com/booking/7";
        //     And Accept type “application/json” dir
        Response response=given().
                          accept("application/json").
                          when().
                          get(url);
        response.prettyPrint();
        //     Then
        //     HTTP Status Code 200
        //     And Response format "application/json"
        //     And firstname "Mary"
        //     And lastname "Ericsson"
        //     And checkin date "2015-12-18"
        //     And checkout date "2018-07-06"

       response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

      /* 1.yol
       response.then().assertThat().
                      body("firstname", Matchers.equalTo("Mary")).
                      body("lastname",Matchers.equalTo("Ericsson")).
                      body("bookingdates.checkin",Matchers.equalTo("2015-12-18")).
                      body("bookingdates.checkout",Matchers.equalTo("2018-07-06"));

       */



        //2.yol
        response.then().
                assertThat().
                body("firstname",equalTo("Jim"),
                        "lastname",equalTo("Brown"),
                        "bookingdates.checkin",equalTo("2017-11-26"),
                        "bookingdates.checkout",equalTo("2018-11-20")
                        );

    }
}
