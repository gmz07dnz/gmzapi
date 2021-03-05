package TestData;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyTestData {
/*
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
 */
   public List<Map<String,Object>> expectedDataList = new ArrayList<>();

    public List<Map<String,Object>> setUpData(){

        HashMap<String ,Object> expectedMap1 = new HashMap<>();
        HashMap<String ,Object> expectedMap2 = new HashMap<>();
        HashMap<String ,Object> expectedMap3 = new HashMap<>();
        HashMap<String ,Object> expectedMap4 = new HashMap<>();
        HashMap<String ,Object> expectedMap5 = new HashMap<>();
        HashMap<String ,Object> expectedMap6 = new HashMap<>();

        expectedMap1.put("Status Code",200);
        expectedDataList.add(expectedMap1);

        expectedMap2.put("selectedEmployeeName","Airi Satou");
        expectedDataList.add(expectedMap2);
        expectedMap3.put("numOfEmployee",24);
        expectedDataList.add(expectedMap3);
        expectedMap4.put("SelectedSalary","106450");
        expectedDataList.add(expectedMap4);
        List<String> age = new ArrayList<>();
        age.add("40");
        age.add("21");
        age.add("19");
        expectedMap5.put("ageOfEmployee",age);
        expectedDataList.add(expectedMap5);

        Map<String,String> empDetailsMap = new HashMap<>();
        empDetailsMap.put("id","11");
        empDetailsMap.put("employee_name","Jena Gaines");
        empDetailsMap.put("employee_salary","90560");
        empDetailsMap.put("employee_age","30");
        empDetailsMap.put("profile_image","");
        expectedMap6.put("allDetailsAboutEmployee",empDetailsMap);
        expectedDataList.add(expectedMap6);

        return expectedDataList;
    }


    public Map<String,Integer> setUp2(){
        Map<String,Integer> expectedDataMap = new HashMap<>();
        expectedDataMap.put("Status code",200);
        expectedDataMap.put("highest salary",725000);
        expectedDataMap.put("minimum age",19);
        expectedDataMap.put("second highest salary",675000);
        return expectedDataMap;

    }




    public Map<String,String > setUpData3(){
        Map<String,String > requestDataMap = new HashMap<>();
        requestDataMap.put("name","Ahmet Aksoy");
        requestDataMap.put("salary","1000");
        requestDataMap.put("age","18");
        requestDataMap.put("profile_image","");
        return requestDataMap;
    }


    public Map<String, String> setUpMessageDataByUsingMap(){

        Map<String, String> massageMap = new HashMap<String, String>();
        massageMap.put("status","success");
        massageMap.put("message","Successfully! Record has been added.");
        return massageMap;

    }

    public JSONObject setUpPostRequestBodyByUsingJSonObject(){
        JSONObject reqBodyjsonObject = new JSONObject();
        reqBodyjsonObject.put("name","Ahmet Aksoy");
        reqBodyjsonObject.put("salary","1000");
        reqBodyjsonObject.put("age","18");
        reqBodyjsonObject.put("profile_image","");
        return reqBodyjsonObject;
    }

    public JSONObject setUpMessageDataJSONObject() {

        JSONObject massageMapjsonObject = new JSONObject();
        massageMapjsonObject.put("status", "success");
        massageMapjsonObject.put("message", "Successfully! Record has been added.");
        return massageMapjsonObject;
    }

    public Map<String,Object> setUpExpectedDeleteDataByUsingMap(){
        Map<String,Object> expectedDeleteDataByUsingMap = new HashMap<>();
        expectedDeleteDataByUsingMap.put("statuscode",200);
        expectedDeleteDataByUsingMap.put("status", "success");
        expectedDeleteDataByUsingMap.put("data","2");
        expectedDeleteDataByUsingMap.put("message","Successfully! Record has been deleted");
        return  expectedDeleteDataByUsingMap;

    }






}
