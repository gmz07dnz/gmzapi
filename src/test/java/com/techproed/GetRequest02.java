package com.techproed;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetRequest02 {


    /*
	 Positive Scenario:
	 when() Bir GET Request asagida verilen Endpoint'e yollanir
	        https://restful-booker.herokuapp.com/booking
	 and()  Accept Type'i "application/json" dir.
	 then() status code 200'dur
	 and()  content type  "application/json" dir.
	*/
        /*
         Negative Scenario:
         when() Bir GET Request asagida verilen Endpoint'e yollanir
                https://restful-booker.herokuapp.com/booking/1001
         and()  Accept Type'i "application/json" dir.
         then() status code 404'dur.
         */
    /*
	 Negative Scenario:
	 when() Bir GET Request asagida verilen Endpoint'e yollanir
	        https://restful-booker.herokuapp.com/booking/1001
	 then() status code 404'dur
	 and()  Response body'de "Not Found" var
	 and()  Response body'de "API" yok
	 */

    Response response;
    @Test
    public void test(){
        // urlyi al
        String url="https://restful-booker.herokuapp.com/booking";
        //Datayi olusturuyorum
        // request gonder
        response= given().
                   accept("application/json").
                   when().
                   get(url);

        response.prettyPrint();

        //then() status code 200'dur
        response.then().assertThat().statusCode(200);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.statusCode(),200);
        //and()  content type  "application/json" dir.
        response.then().assertThat().contentType("application/json");
        System.out.println(response.header("Content-Type"));
        System.out.println("Headers : "+response.getHeaders());
    }


    @Test
    public void test1(){
         /*
         Negative Scenario:
         when() Bir GET Request asagida verilen Endpoint'e yollanir
                https://restful-booker.herokuapp.com/booking/1001
         and()  Accept Type'i "application/json" dir.
         then() status code 404'dur.
         */
        // urlyi al
        String url ="https://restful-booker.herokuapp.com/booking/1001";

        // Datayi olustur
        //Request gonder
        response=given().
                 accept("application/json").
                 when().
                 get(url);
        response.prettyPrint();

        //then() status code 404'dur.
        response.then().assertThat().statusCode(404);
        //content type  yazdır
        System.out.println("Content-Type: "+response.getContentType()); //Content-Type: text/plain; charset=utf-8 ->404 oldugu ıcın text formatında


    }

    @Test
    public void test2(){

	    // Negative Scenario:
        //	 when() Bir GET Request asagida verilen Endpoint'e yollanir
        //	        https://restful-booker.herokuapp.com/booking/1001
        String url="https://restful-booker.herokuapp.com/booking/1001";
        response=given().
                 accept("application/json").
                 when().
                 get(url);
        response.prettyPrint();

        //	 then() status code 404'dur
        response.then().
                assertThat().
                statusCode(404);

        //	 and()  Response body'de "Not Found" var
        Assert.assertTrue(response.asString().contains("Not Found"));
        //	 and()  Response body'de "API" yok
        Assert.assertFalse(response.asString().contains("API"));


    }
}
