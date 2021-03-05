package com.techproed;

import TestBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest09 extends TestBaseDummy {

    /*
    Warm Up (10 Minutes)
		 1)Create a class and name it as GetRequest09
		 2)When
    I send a GET Request to http://dummy.restapiexample.com/api/v1/employees
    Then
    status code is 200
    And the name of the 5th employee is "Airi Satou"
    And the salary of the 6th employee is "372000"
    And there are "24" employees
    And "Rhona Davidson" is one of the employees
    And "21", "23", "61" are among the employee ages
		 3)Do the assertions by using Hard Assertion
	*/


    @Test
    public void test(){
        //1. adim URL
     spec03.pathParams("employeePath","employees");

     //2.adim test datalarini oluşturma

       // 3.adim response olusturma
     Response response = given().
                         spec(spec03).
                         when().
                         get("/{employeePath}"); // key olani yaziyoruz
     response.prettyPrint();

     // 1. yol Matchers ile
     response.then().
             assertThat().
             statusCode(200).
             body("data.employee_name[4]",equalTo("Airi Satou"),
                     "data.employee_salary[5]",equalTo("372000"),
             "data.id",hasSize(24),
             "data.employee_name",hasItem("Rhona Davidson"),
             "data.employee_age",hasItems("21","23","61"));

     // 2. Yol jsonPath ile

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("Airi Satou",jsonPath.getString("data.employee_name[4]"));
        Assert.assertEquals("372000",jsonPath.getString("data.employee_salary[5]"));
        Assert.assertEquals(24,jsonPath.getList("data.id").size());
        Assert.assertTrue(jsonPath.getString("data.employee_name").contains("Rhona Davidson"));

        List<String> age = new ArrayList<>();
        age.add("21");
        age.add("23");
        age.add("61");

        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(age));

    }




}
