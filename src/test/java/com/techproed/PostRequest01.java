package com.techproed;

import TestBase.TestBaseDummy;
import TestData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PostRequest01 extends TestBaseDummy {
    /*
	 	 When
	 	  	I send a POST Request to the Url http://dummy.restapiexample.com/api/v1/create
	 	  	by using the following Request Body {
												    "name":"Ahmet Aksoy",
												    "salary":"1000",
												    "age":"18",
												    "profile_image": ""
												}
	 	 Then
	 	  	Status code is 200
	 	  	And response body should be like {
											    "status": "success",
											    "data": {
											        "name": "Ahmet Aksoy",
											        "salary": "1000",
											        "age": "18",
											        "profile_image": null
											    },
											    "message": "Successfully! Record has been added."
											 }
	*/

    // Genel olarak authorization(yetki) isterler -- post islemi icin
    @Test
    public void post01(){
        // url olustur
        spec03.pathParam("createPath","create");

        // 2. ReqBody olusturucam
        DummyTestData expectedObj = new DummyTestData();
        Map<String,String> reqBodyMap = expectedObj.setUpData3();
        System.out.println(reqBodyMap);

        // Request i gonder
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec03).
                auth().
                basic("admin","password123").
                body(reqBodyMap).
                when().
                post("/{createPath}");
      //  response.prettyPrint();

        // 4. Assertion
        // 1. yol jsonPath

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(reqBodyMap.get("name"),jsonPath.getString("data.name"));
        Assert.assertEquals(reqBodyMap.get("salary"),jsonPath.getString("data.salary"));
        Assert.assertEquals(reqBodyMap.get("age"),jsonPath.getString("data.age"));
        if(reqBodyMap.get("profile_image").equals("")){
            reqBodyMap.put("profile_image",null);
        }

        Assert.assertEquals(reqBodyMap.get("profile_image"),jsonPath.getString("data.profile_image"));

        Map<String, String> expectedMessagePath = expectedObj.setUpMessageDataByUsingMap();
        System.out.println(expectedMessagePath);
        Assert.assertEquals(expectedMessagePath.get("status"),jsonPath.getString("status"));
        Assert.assertEquals(expectedMessagePath.get("message"),jsonPath.getString("message"));

        // 2. yol De-Sterilization

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals(reqBodyMap.get("name"),((Map)actualDataMap.get("data")).get("name"));
        Assert.assertEquals(reqBodyMap.get("salary"),((Map)actualDataMap.get("data")).get("salary"));
        Assert.assertEquals(reqBodyMap.get("age"),((Map)actualDataMap.get("data")).get("age"));

         /*
       if (reqBodyMap.get("profile_image").equals("")){
            reqBodyMap.put("profile_image",null);
        }
        YUkarıda if statement ı kullandıgımız icin tekrara gerek yok
        Cunku aynı scope aynı--- aynı metodtayız--
        */
        Assert.assertEquals(reqBodyMap.get("profile_image"),((Map)actualDataMap.get("data")).get("profile_image"));
        Assert.assertEquals(expectedMessagePath.get("message"),actualDataMap.get("message"));
        Assert.assertEquals(expectedMessagePath.get("status"),actualDataMap.get("status"));

        // 3. yol JSonObject , jsonpath
        SoftAssert softAssert = new SoftAssert();
        JSONObject expectedJSonObject = expectedObj.setUpPostRequestBodyByUsingJSonObject();
        JSONObject expectedMessageJSonObject = expectedObj.setUpMessageDataJSONObject();
        softAssert.assertEquals(jsonPath.getString("data.name"),expectedJSonObject.getString("name"));
        softAssert.assertEquals(jsonPath.getString("data.salary"),expectedJSonObject.getString("salary"));
        softAssert.assertEquals(jsonPath.getString("data.age"),expectedJSonObject.getString("age"));
        softAssert.assertEquals(jsonPath.getString("message"),expectedMessageJSonObject.getString("message"));
        softAssert.assertEquals(jsonPath.getString("status"),expectedMessageJSonObject.getString("status"));
        softAssert.assertAll();







    }



}
