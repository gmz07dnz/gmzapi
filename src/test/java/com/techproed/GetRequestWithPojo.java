package com.techproed;

import TestBase.TestBaseDummy;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.EmployeesExpectedPojo;

import static io.restassured.RestAssured.*;

public class GetRequestWithPojo extends TestBaseDummy {

    /*
		 	When
		 		I send GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
		 	Then
		 		Status code is 200
		 		And response body is like {
										    "status": "success",
										    "data": {
										        "id": 1,
										        "employee_name": "Tiger Nixon",
										        "employee_salary": 320800,
										        "employee_age": 61,
										        "profile_image": ""
										    },
										    "message": "Successfully! Record has been fetched."
										   }
}
*/

    @Test
    public void getRequest(){
        spec03.pathParams("employeePath","employee","id",1);

        Data data = new Data(1,"Tiger Nixon",320800,61,"");
        EmployeesExpectedPojo expectedDataPojo = new EmployeesExpectedPojo("success",data,"Successfully! Record has been fetched.");

        Response response = given().
                spec(spec03).
                when().
                get("/{employeePath}/{id}");
        response.prettyPrint();

        EmployeesExpectedPojo actualDataPojo = response.as(EmployeesExpectedPojo.class);
        System.out.println(actualDataPojo);

        Assert.assertEquals(expectedDataPojo.getStatus(),actualDataPojo.getStatus());
        Assert.assertEquals(expectedDataPojo.getData().getId(),actualDataPojo.getData().getId());
        Assert.assertEquals(expectedDataPojo.getData().getEmployee_name(),actualDataPojo.getData().getEmployee_name());
        Assert.assertEquals(expectedDataPojo.getData().getEmployee_salary(),actualDataPojo.getData().getEmployee_salary());
        Assert.assertEquals(expectedDataPojo.getData().getEmployee_age(),actualDataPojo.getData().getEmployee_age());
        Assert.assertEquals(expectedDataPojo.getData().getProfile_image(),actualDataPojo.getData().getProfile_image());

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedDataPojo.getData().getEmployee_name(),jsonPath.getString("data.employee_name"));
        Assert.assertEquals(expectedDataPojo.getData().getEmployee_age(),((Integer)jsonPath.getInt("data.employee_age")));

    // Serilization islemi GSON ile nasil yapilir --
        //1. GSon Object olusturyrz -- String olarak doner
        Gson gson = new Gson();

        // 2. adim toJSon methodunu kullanicagiz --  Javadan Json'a ceviriyoruz
        String jsonFromJavaObject = gson.toJson(expectedDataPojo);
        System.out.println(jsonFromJavaObject);



    }

}