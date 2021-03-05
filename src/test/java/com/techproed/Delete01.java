package com.techproed;

import TestBase.TestBaseDummy;
import TestData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Delete01 extends TestBaseDummy {

    /*
	 	When
	 		I send DELETE Request to the Url http://dummy.restapiexample.com/api/v1/delete/2
	 	Then
		 	Status code is 200
		 	And Response body is {
								    "status": "success",
								    "data": "2",
								    "message": "Successfully! Record has been deleted"
								 }
	*/

    @Test
    public void delete(){
        // Url
        spec03.pathParams("deletePath","delete","id",2);

        // ReqBody olusturalım
        DummyTestData expectedObj = new DummyTestData();
        Map<String,Object> expectedDataMap =expectedObj.setUpExpectedDeleteDataByUsingMap();
        System.out.println(expectedDataMap);

        // Request gonder
        Response response = given().
                spec(spec03).
                when().
                delete("/{deletePath}/{id}");
        response.prettyPrint();

        // 1. yol Body
        response.then().
                assertThat().statusCode(200).
                body("status", equalTo(expectedDataMap.get("status")),
                        "data",equalTo(expectedDataMap.get("data")),
                        "message",equalTo(expectedDataMap.get("message")));

        // 2.yol JsonPath - expected Data Hard Assert
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("statuscode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),jsonPath.getString("status"));
        Assert.assertEquals(expectedDataMap.get("data"),jsonPath.getString("data"));
        Assert.assertEquals(expectedDataMap.get("message"),jsonPath.getString("message"));

        //3. yol GSon Expected Data SoftAssert
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("data"),actualDataMap.get("data"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));


    }
}
