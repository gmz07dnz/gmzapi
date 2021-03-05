package com.techproed;

import TestBase.TestBaseJsonPlaceHolder;
import TestData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PatchRequest01 extends TestBaseJsonPlaceHolder {
    /*
	   When
	 		I send PATCH Request to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "title": "Tidy your room",
										   }
	   Then
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 10,
									    "title": "Tidy your room",
									    "completed": true,
									    "id": 198
									  }
   */

    @Test
    public void patchRequest(){
        // Url
        spec01.pathParams("todosPath","todos","id",198);

        // Expected Data
        JsonPlaceHolderTestData expectedobj = new JsonPlaceHolderTestData();
        Map<String,Object> expextedPatchMap = expectedobj.setUpPatchMap();
        System.out.println(expextedPatchMap);

        // Request gonder
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                body(expextedPatchMap).
                when().
                patch("/{todosPath}/{id}");
        response.prettyPrint();

        //Assertion
        // 1. yol body expectedPatchMap
        response.then().
                assertThat().
                body("userId",equalTo(expectedobj.userId),
                        "title",equalTo(expextedPatchMap.get("title")),
                        "completed",equalTo(expectedobj.completed));

        // jsonpth ve expectedDataMap
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedobj.userId,jsonPath.getInt("userId"));
        Assert.assertEquals(expextedPatchMap.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedobj.completed,jsonPath.getBoolean("completed"));

        //3. yol Gson
        Map <String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(expextedPatchMap.get( "title"),actualDataMap.get( "title"));
        Assert.assertEquals(expectedobj.userId,actualDataMap.get("userId"));
        Assert.assertEquals(expectedobj.completed,actualDataMap.get("completed"));


    }
}
