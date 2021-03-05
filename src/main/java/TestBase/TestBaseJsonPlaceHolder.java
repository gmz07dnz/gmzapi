package TestBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBaseJsonPlaceHolder {

   protected RequestSpecification spec01;

    @Before  // her methoddan once calıssın ıstedigim icin bu annotation'ı kullanıyoruz
    public void setUp01(){
      spec01 =new RequestSpecBuilder().
              setBaseUri("https://jsonplaceholder.typicode.com").
              build();
    }
}
