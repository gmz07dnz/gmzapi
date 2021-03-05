package com.techproed;

import TestBase.TestBaseDummy;
import TestData.DummyTestData;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetRequest12 extends TestBaseDummy {

    /*
  When
  I send get request to yje URL http://dummy.restapiexample.com/api/v1/employees
  Then
  Status Code 200
  5. calisanin ismi "Airi Satou"
  Calisan sayisi 24
  Sondan ikinci calisanin maasi "106450"
  40,21 ve 19 yaslarinda calisanlar olup olmadıgı
  11. calisanin bilgileri { "id": "11",
							            "employee_name": "Jena Gaines",
							            "employee_salary": "90560",
							            "employee_age": "30",
							            "profile_image": "" }
							            seklinde mi
							            Assert edelim.

 */

    @Test
    public void test(){
        //URL olustur
        spec03.pathParams("employeePath","employees");

        // expected data olustur
        DummyTestData expectedDataObj = new DummyTestData();
        List<Map<String ,Object>> expectedDataList = expectedDataObj.setUpData();
        System.out.println(expectedDataList);

        // Request
        Response response = given().
                             spec(spec03).
                              when().
                              get("/{employeePath}");
      //  response.prettyPrint();

        // 1. yol body ile

        response.then().
                assertThat().
                statusCode((int)expectedDataList.get(0).get("Status Code")).
                body("data.employee_name[4]",equalTo(expectedDataList.get(1).get("selectedEmployeeName")),
                       "data.id",hasSize((int)expectedDataList.get(2).get("numOfEmployee")),
                        "data[-2].employee_salary",equalTo(expectedDataList.get(3).get("SelectedSalary")),
                        "data.employee_age",hasItems(((List)expectedDataList.get(4).get("ageOfEmployee")).get(0),
                                           ((List)expectedDataList.get(4).get("ageOfEmployee")).get(1),
                                            ((List)expectedDataList.get(4).get("ageOfEmployee")).get(2)),
                        "data.id[10]",equalTo(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("id")),
                        "data.employee_name[10]",equalTo(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("employee_name")),
                        "data.employee_salary[10]",equalTo(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("employee_salary")),
                        "data.employee_age[10]",equalTo(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("employee_age")),
                        "data.profile_image[10]",equalTo(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("profile_image")));


        // 1. yol  DE-Serialization ile

        HashMap<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        //Status Code 200
        Assert.assertEquals(expectedDataList.get(0).get("Status Code"),response.getStatusCode());

        //  5. calisanin ismi "Airi Satou"
        Assert.assertEquals(expectedDataList.get(1).get("selectedEmployeeName"), ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));

        //  Calisan sayisi 24
        Assert.assertEquals(expectedDataList.get(2).get("numOfEmployee"),((List)actualDataMap.get("data")).size());

        //  Sondan ikinci calisanin maasi "106450"
        Assert.assertEquals(expectedDataList.get(3).get("SelectedSalary"),((Map)((List)actualDataMap.get("data")).get(((List)actualDataMap.get("data")).size()-2)).get("employee_salary"));

        //  40,21 ve 19 yaslarinda calisanlar olup olmadıgı
        List<String> ages = new ArrayList<>();  // response da age String olarak tanımlandıgı için bu sekilde aldık
        int size = ((List)(actualDataMap.get("data"))).size();
        for(int i =0; i< size;i++) {
           ages.add((String)( ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age")) );
        }
        Assert.assertTrue(ages.containsAll((List)(expectedDataList.get(4).get("ageOfEmployee"))));
        //  11. calisanin bilgileri { "id": "11",
        //							            "employee_name": "Jena Gaines",
        //							            "employee_salary": "90560",
        //							            "employee_age": "30",
        //							            "profile_image": "" }
        //							            seklinde mi
        //							            Assert edelim.

        Assert.assertEquals(expectedDataList.get(5).get("allDetailsAboutEmployee"),((Map)((List) actualDataMap.get("data")).get(10)));
        Assert.assertEquals(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("employee_name"),(((Map)((List) actualDataMap.get("data")).get(10)).get("employee_name")));
       Assert.assertEquals(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("employee_age"),(((Map)((List) actualDataMap.get("data")).get(10)).get("employee_age")));
       Assert.assertEquals(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("employee_salary"),(((Map)((List) actualDataMap.get("data")).get(10)).get("employee_salary")));
      Assert.assertEquals(((Map)expectedDataList.get(5).get("allDetailsAboutEmployee")).get("profile_image"),(((Map)((List) actualDataMap.get("data")).get(10)).get("profile_image")));








    }
}
