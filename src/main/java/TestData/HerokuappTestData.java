package TestData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HerokuappTestData {
    /*
     "firstname": "Eric",
			    "lastname": "Smith",
			    "totalprice": 555,
			    "depositpaid": false,
			    "bookingdates": {
			        "checkin": "2016-09-09",
			        "checkout": "2017-09-21"
     */

    Map<String, Object> expectedDataMap;

    public Map<String, Object> setUp() {
        expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "Mark");
        expectedDataMap.put("lastname", "Brown");
        expectedDataMap.put("totalprice", 382);
        expectedDataMap.put("depositpaid", true);
        //  expectedDataMap.put("additionalneeds","Breakfast");

        Map<String, Object> bookingdates = new HashMap<String, Object>();
        bookingdates.put("checkin", "2018-03-14");
        bookingdates.put("checkout", "2020-08-13");

        expectedDataMap.put("bookingdates", bookingdates);
        return expectedDataMap;
    }


    public JSONObject setUpDataJSONObject(){
        JSONObject bookingDatesJSONObject = new JSONObject();
        bookingDatesJSONObject.put("checkin","2020-09-09");
        bookingDatesJSONObject.put("checkout","2020-09-21");

        JSONObject bookingDetailsJSONObject = new JSONObject();
        bookingDetailsJSONObject.put("firstname","Selim");
        bookingDetailsJSONObject.put("lastname","Ak");
        bookingDetailsJSONObject.put("totalprice",11111);
        bookingDetailsJSONObject.put("depositpaid",true);
        bookingDetailsJSONObject.put("bookingdates",bookingDatesJSONObject);
        return  bookingDetailsJSONObject;
    }

}

