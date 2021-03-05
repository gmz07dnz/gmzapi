package com.techproed;

import TestBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest08 extends TestBaseDummy {
    /*
    	Url: "http://dummy.restapiexample.com/api/v1/employees
    	1) Butun calisanlarin isimlerini consola yazdır
    	2) 3. calisan kisinin ismini konsola yazdır
    	3) Ilk 5 calisanin adini konsola yazdir
    	4) En son calisanin adini konsola yazdir.

	 */

    @Test
    public void test(){
        //url olusturmak
        spec03.pathParam("name","employees");
        //request olusturuyoruz
        Response response=given().
                           spec(spec03).
                            when().
                            get("/{name}");
        response.prettyPrint();
        JsonPath jsonPath =response.jsonPath();
        System.out.println("Employee name: "+jsonPath.getString("data.employee_name"));
        System.out.println("Employee Salary: " +jsonPath.getString("employee_salary"));
        System.out.println("3.Calisanin Ismi: "+ jsonPath.getString("data.employee_name[2]"));

        Assert.assertEquals("Ashton Cox",jsonPath.getString("data.employee_name[2]"));
        for(int i =0; i<5; i++){
            System.out.println(jsonPath.getString("data.employee_name["+ i+ "]"));
        }

        // System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));

       System.out.println("Son Calisanin Ismi: "+ jsonPath.getString("data.employee_name[-1]"));
      //System.out.println(json.getString("data[-1].employee_name"));

    }
}
