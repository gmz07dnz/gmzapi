package TestBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.testng.annotations.Test;

public class TestBaseHerokuApp {
    protected RequestSpecification spec02;
    @Before
    public void setUp02() {
        spec02 = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com/booking").
                build();

    }
}
