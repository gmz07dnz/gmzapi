package com.techproed;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetRequest01 {

    /*
       Given: Baslangıc icin gereklilikleri ifade eder.
       When: Kullanicinin aksiyonunu ifade eder.
       Then: Ciktilari ifade eder-- Assert ler genelde burada yapilir
       And: Herhangi iki coklu adim arasinda kullanilabilir.

        Positive Scenario
     When I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/3
     Then
     HTTP Status code should be 200
     And  Content type should be Json
     And  Status Line should be HTTP/1.1 200 OK
    */

    @Test
    public void getRequest(){
        // url'ye git
     String url ="https://restful-booker.herokuapp.com/booking/3";

     // Beklenen datayı olusturalım

        // request gonderiyoruz
        Response  response =given().
                            accept("application/json").
                            when().
                            get(url);

        response.prettyPrint();
        //HTTP Status code should be 200
        System.out.println("HTTP Status code: "+response.getStatusCode());
        response.then().assertThat().statusCode(200);

        // And  Content type should be Json
        System.out.println("Content type: "+ response.getContentType());
        response.then().assertThat().contentType(ContentType.JSON);

        // And  Status Line should be HTTP/1.1 200 OK
        System.out.println("Status Line: "+ response.getStatusLine());
        response.then().assertThat().statusLine("HTTP/1.1 200 OK");

        // tek satırda da yazabiliriz
        response.then().assertThat().
                        statusCode(200).
                        contentType(ContentType.JSON).
                        statusLine("HTTP/1.1 200 OK");

        // Baslıkları gorelim
        System.out.println(response.getHeaders());
        System.out.println(response.getHeader("X-Powered-By"));

    }
}
