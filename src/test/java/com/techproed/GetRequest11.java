package com.techproed;

import TestBase.TestBaseJsonPlaceHolder;
import TestData.JsonPlaceHolderTestData;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class GetRequest11 extends TestBaseJsonPlaceHolder {

    /*
	When
	 I send GET Request to jsonplaceholder api/todos/2
	 Status code: 200
	 "completed": is false
	 "title="quis ut nam facilis et officia qui"
	 "userId = 1
	 header "via"
	 header "Server"
	 */
    /*
    DE-Serialization: JSON datasini Java Objelerine (MAp,Lİst,Lİst Of Map, Set)
                       cevirme islemidir.
    gson dependency kullanarak De-Serialization ve Serialization yapilabilir.
     */

    @Test
    public void test(){
        // url olustur
        spec01.pathParams("todos","todos","id","2");

        // expected data olusturalim
        JsonPlaceHolderTestData expectedobj = new JsonPlaceHolderTestData();
        HashMap<String,Object> expectedDataMap= expectedobj.setUpData();


        // request olusturalim
        Response response = given().
                            spec(spec01).
                            when().
                            get("/{todos}/{id}");

    response.prettyPrint();

    response.then().
             assertThat().
             statusCode((int) expectedDataMap.get("Status Code")).
             assertThat().
            body("userId", equalTo(expectedDataMap.get("userId")),
                    "title",equalTo(expectedDataMap.get("title")),
                    "completed",equalTo(expectedDataMap.get("completed"))).
            headers("Via",equalTo(expectedDataMap.get("Via")),
                    "Server",equalTo(expectedDataMap.get("Server")));

    // 2. yol DE-Serialization ile
    HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(expectedDataMap.get("Status Code"),response.statusCode());
        Assert.assertEquals(expectedobj.setUpData().get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("Via"),response.header("Via"));
        Assert.assertEquals(expectedDataMap.get("Server"),response.header("Server"));


        // 3. yol SoftAssert
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(response.statusCode(), expectedDataMap.get("Status Code"));
        softAssert.assertEquals(actualDataMap.get("userId"), expectedDataMap.get("userId"));
        softAssert.assertEquals(actualDataMap.get("title"), expectedDataMap.get("title"));
        softAssert.assertEquals(actualDataMap.get("completed"), expectedDataMap.get("completed"));
        softAssert.assertEquals(response.getHeader("Via"), expectedDataMap.get("Via"));
        softAssert.assertEquals(response.getHeader("Server"), expectedDataMap.get("Server"));



        softAssert.assertAll();
    
    }
}
