
package com.techproed;

import TestBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Test;


import static io.restassured.RestAssured.*;

public class GetRequest06 extends TestBaseJsonPlaceHolder {
                  /*
                       1)When I send a GET request to REST API URL
						 https://jsonplaceholder.typicode.com/todos/123
					     Then HTTP Status Code should be 200
					     And "Server" in Headers should be "cloudflare"
					     And response content type is “application/JSON”
					     And "userId" should be 7,
					     And "title" should be "esse et quis iste est earum aut impedit"
						 And "completed" should be false
					*/

    @Test
    public void test01(){
        // url olustur
        //data olusturmak -- sonra
        // request gonder
        // Assert yap
        spec01.pathParams("name","todos","id",123);

        // Test Body -- Bu kismi sabit tutmaya calisiyoruz
        Response response=given().
                          spec(spec01).
                          when().
                          get("/{name}/{id}");
        response.prettyPrint();
        response.then().
                assertThat().
                statusCode(200).
                header("Server",equalTo("cloudflare")).
                contentType(ContentType.JSON).
                body("userId",equalTo(7),
                        "title",equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));





    }




}
