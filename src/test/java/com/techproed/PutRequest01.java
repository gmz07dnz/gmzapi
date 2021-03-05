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

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PutRequest01 extends TestBaseJsonPlaceHolder {

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
    public void putRequest(){
        // url
   spec01.pathParams("todosPath","todos","id",198);

   // expected requestBody
        JsonPlaceHolderTestData expectedRequest = new JsonPlaceHolderTestData();
        JSONObject expectedReqJSon = expectedRequest.setUpPutRequestByJSONObject();
        System.out.println(expectedReqJSon);

   // Request olusturma

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                body(expectedReqJSon.toString()).
                when().put("/{todosPath}/{id}");

        response.prettyPrint();

  // Assertion
  // 1. yol Body
        response.then().
                assertThat().
                statusCode(200).
                body("completed",equalTo(expectedReqJSon.getBoolean("completed")),
                        "title",equalTo(expectedReqJSon.getString("title")),
                        "userId",equalTo(expectedReqJSon.getInt("userId"))
                        );

   // 2. yol  JsonPath- JSonObject
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedReqJSon.getBoolean("completed"),jsonPath.getBoolean("completed"));
        Assert.assertEquals(expectedReqJSon.getString("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedReqJSon.getInt("userId"),jsonPath.getInt("userId"));

   // 3.yol GSon ve JsonObject

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(expectedReqJSon.getBoolean("completed"),actualDataMap.get("completed"));
        Assert.assertEquals(expectedReqJSon.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedReqJSon.getInt("userId"),actualDataMap.get("userId"));

   // 4.yol GSon
   Map<String,Object> expectedDataMap = expectedRequest.setUpPutRequestMap();
        System.out.println(expectedDataMap);
   Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
   Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
   Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));

    }


}
