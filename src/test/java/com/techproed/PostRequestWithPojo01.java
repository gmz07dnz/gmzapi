package com.techproed;

import TestBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.TodosPojo;

import static io.restassured.RestAssured.*;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder {

    /*
      Plain of java objects
      Pojo classları kullanmak için izlenecek adımlar
      1) Java dataya bakılarak her bir key icin bir private degisken olusturulması
         Polimorphism - WebDriver interface , constructor subclass'tan
         Encapsulation - Pojolarda
         Inheritance - TestBase de , obje olusturmaya gerek kalmadan
         Abstraction - extend edip tum methodları kullanmak istiyorsak bu sekilde kullanabiliriz.
      2) Private degiskenlere erisebilmek için getter ve setter'ları olusturmak
      3) Default parametresiz Constructor olusturmak
      4) Parametreli constructor olusturmak -- nesne olusturma da kullanacagız
      5) toString() methodu kullanacagız actual veya expected data'yı gorebilmek için
     */


    /*
	 	When
	 		I send POST Request to the URL https://jsonplaceholder.typicode.com/todos
	 		with Post Request body  {
									    "userId": 21,
									    "id": 201,
									    "title": "Tidy your room",
									    "completed": false
									  }
	 	Then
	 		Status code is 201
	 		And response body is like {
									    "userId": 21,
									    "id": 201,
									    "title": "Tidy your room",
									    "completed": false
									  }

	 */

    @Test
    public void postPojo01(){

            // Url i olustur
            spec01.pathParam("todosPath","todos");
            // ExpectedDatayı olustur
            TodosPojo expectedPojoData = new TodosPojo(21,201,"Tidy your room",false);
            // Requesti gonder
            Response response = given().
                    contentType(ContentType.JSON).
                    spec(spec01).
                    body(expectedPojoData).
                    when().
                    post("/{todosPath}");
            response.prettyPrint();
    // Assertion - body -pojo
        response.then().
                assertThat().
                statusCode(201).
                body("userId",equalTo(expectedPojoData.getUserId()),
                        "id",equalTo(expectedPojoData.getId()),
                        "title",equalTo(expectedPojoData.getTitle()),
                        "completed",equalTo(expectedPojoData.isCompleted()));

   // Assertion jsonpath ve pojo
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("userId"),expectedPojoData.getUserId());
        Assert.assertEquals(jsonPath.getInt("id"),expectedPojoData.getId());
        Assert.assertEquals(jsonPath.getString("title"),expectedPojoData.getTitle());
        Assert.assertEquals(jsonPath.getBoolean("completed"),expectedPojoData.isCompleted());

    // Assertion GSon ve pojo

    TodosPojo actualTodosData = response.as(TodosPojo.class);
        System.out.println(actualTodosData);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTodosData.getUserId(),expectedPojoData.getUserId());
        softAssert.assertEquals(actualTodosData.getId(),expectedPojoData.getId());
        softAssert.assertEquals(actualTodosData.getTitle(),expectedPojoData.getTitle());
        softAssert.assertEquals(actualTodosData.toString(),expectedPojoData.isCompleted());



    }
















}
