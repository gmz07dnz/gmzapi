package com.techproed;

import TestBase.TestBaseJsonPlaceHolder;
import TestData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PostRequest03 extends TestBaseJsonPlaceHolder {

    /*
	 	When
	 		I send PUT Requst to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "userId": 21,
										    "title": "Wash the dishes",
										    "completed": false
										   }
	   Then
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false,
									    "id": 198
									  }
	 */


    @Test
    public void postRequest(){
        // Url
        spec01.pathParam("todos","todos");
        // ReqBody i olustur
        JsonPlaceHolderTestData expectedObj = new JsonPlaceHolderTestData();
        JSONObject exptectedDataJSON = expectedObj.setUpPostRequestByJSONObject();
        System.out.println(exptectedDataJSON);
        // Request i gonder
        Response response = given().contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(exptectedDataJSON.toString()).
                when().
                post("/{todos}");
        response.prettyPrint();

        // Assertion
        // 1.Body ile (Matcher)

        //response.then().assertThat().statusCode(200).
        //                body("completed",equalTo(exptectedDataJSON.getBoolean("completed")),
        //                        "id",equalTo(exptectedDataJSON.getInt("id")),
        //                        "title",equalTo(exptectedDataJSON.getString("title")),
        //                        "userId",equalTo(exptectedDataJSON.getInt("userId")));


        // HardAssert  JSonObject - JsonPath
        JsonPath jsonPath = response.jsonPath();
         Assert.assertEquals(expectedObj.statusCode,response.getStatusCode());
        Assert.assertEquals(exptectedDataJSON.getBoolean("completed"),jsonPath.getBoolean("completed"));
        Assert.assertEquals(exptectedDataJSON.getInt("id"),jsonPath.getInt("id"));
        Assert.assertEquals(exptectedDataJSON.getString("title"),jsonPath.getString("title"));
        Assert.assertEquals(exptectedDataJSON.getInt("userId"),jsonPath.getInt("userId"));

        // SoftAssert JsonObject - GSon ( DeSterilization)

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDataMap.get("completed"),exptectedDataJSON.getBoolean("completed"));
        softAssert.assertEquals(actualDataMap.get("id"),exptectedDataJSON.getInt("id"));
        softAssert.assertEquals(actualDataMap.get("title"),exptectedDataJSON.getString("title"));
        softAssert.assertEquals(actualDataMap.get("userId"),exptectedDataJSON.getInt("userId"));




        softAssert.assertAll();





    }
}
