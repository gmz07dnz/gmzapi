package TestData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    HashMap<String, Object> expectedDataMap;

    public int statusCode=201;
    public int userId = 10;
    public boolean completed=true;

    public HashMap<String, Object> setUpData() {
        expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("Status Code", 200);
        expectedDataMap.put("userId", 1);
        expectedDataMap.put("title", "quis ut nam facilis et officia qui");
        expectedDataMap.put("completed", false);
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");
        return expectedDataMap;
    }

    public JSONObject setUpPostRequestByJSONObject(){
        JSONObject expectedDataJSonObject = new JSONObject();
        expectedDataJSonObject.put("userId",21);
        expectedDataJSonObject.put( "title","Wash the dishes");
        expectedDataJSonObject.put( "completed",false);
        expectedDataJSonObject.put("id",201);
        return expectedDataJSonObject;
    }

    public JSONObject setUpPutRequestByJSONObject(){
        JSONObject expectedDataJSonObject = new JSONObject();
        expectedDataJSonObject.put("userId",21);
        expectedDataJSonObject.put( "title","Wash the dishes");
        expectedDataJSonObject.put( "completed",false);
        return expectedDataJSonObject;
    }

    public Map<String,Object> setUpPutRequestMap(){
        Map<String,Object> expectedReqMap = new HashMap<>();
        expectedReqMap.put("userId",21);
        expectedReqMap.put( "title","Wash the dishes");
        expectedReqMap.put( "completed",false);
        return expectedReqMap;
    }

    public JSONObject setUpPatchJSONObjct(){
        JSONObject expectedJSONObject= new JSONObject();
        expectedJSONObject.put("title","Tidy your room");
        expectedJSONObject.put("userId",10);
        expectedJSONObject.put("id",198);
        expectedJSONObject.put("completed",true);
        return expectedJSONObject;
    }

    public Map<String,Object> setUpPatchMap(){
        Map<String,Object> expextedPatchMap = new HashMap<>();
        expextedPatchMap.put("title","Tidy your room");
        return expextedPatchMap;
    }


}
