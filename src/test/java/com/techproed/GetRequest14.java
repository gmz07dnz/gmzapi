package com.techproed;

import TestBase.TestBaseDummy;
import TestData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestBaseDummy {

     	/*
	 	When
	 		I send a request to http://dummy.restapiexample.com/api/v1/employees
	 	Then
	 		Status code is 200
	 		And the highest salary is 725000
	 		And the minimum age is 19
	 		And the second lowest salary is 675000
	*/

    @Test
    public void test(){
       // Url
        spec03.pathParams("employee","employees");

       // expexted Data
        DummyTestData dummyTestData2Obj = new DummyTestData();
        Map<String,Integer> expectedDataMap = dummyTestData2Obj.setUp2();
        System.out.println(expectedDataMap);

       // response
        Response response =given().
                           spec(spec03).
                            when().
                            get("/{employee}");

       //  response.prettyPrint();

        // 1 yol JSonPath ile
        JsonPath jsonPath = response.jsonPath();
      Assert.assertEquals((int)expectedDataMap.get("Status code"),response.getStatusCode());
      List<String> salaryList = jsonPath.getList("data.employee_salary" );
      System.out.println(salaryList);
      List<Integer> salaryListInt = new ArrayList<>();

      for(String w: salaryList){
          salaryListInt.add(Integer.valueOf(w));
      }
     // salaryListInt.stream().forEach(t->salaryListInt.add(Integer.valueOf(t)));
      Collections.sort(salaryListInt);
        System.out.println(salaryListInt);
      Assert.assertEquals(expectedDataMap.get("highest salary"),salaryListInt.get(salaryListInt.size()-1));
      List<String> age =  jsonPath.getList("data.employee_age");
      List<Integer> ageInt = new ArrayList<>();
      for (String w : age){
          ageInt.add(Integer.valueOf(w));
      }
      Collections.sort(ageInt);
      Assert.assertEquals(expectedDataMap.get("minimum age"),ageInt.get(0));
     Assert.assertEquals(expectedDataMap.get("second highest salary"),salaryListInt.get(salaryListInt.size()-2));


     // 2. yol De-Sterilization

        Map<String ,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        Assert.assertEquals((int)expectedDataMap.get("Status code"),response.getStatusCode());

        List<Integer> salary = new ArrayList<>();
        int size = ((List)(actualDataMap.get("data"))).size();
        for(int i = 0; i<size; i++){
            salary.add(Integer.valueOf((String) ( (Map) (((List)(actualDataMap.get("data"))).get(i))).get("employee_salary")));
        };
        System.out.println(salary);
        Collections.sort(salary);
        Assert.assertEquals(expectedDataMap.get("highest salary"),salary.get(salary.size()-1));
        Assert.assertEquals(expectedDataMap.get("second highest salary"),salary.get(salary.size()-2));

        List<Integer> ageList = new ArrayList<>();
        for (int i= 0; i<size; i++){
            ageList.add( Integer.valueOf((String) ((Map) ((List)actualDataMap.get("data")).get(i)).get("employee_age")));
        }
        Collections.sort(ageList);
        Assert.assertEquals(expectedDataMap.get( "minimum age"),ageList.get(0));





    }

}
