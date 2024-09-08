import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.service.practicum.scooter.qa.client.ScooterServiceClient;

public class GetOrdersTest {
    ScooterServiceClient client = new ScooterServiceClient();
    @Test
    public void getOrders(){
        ValidatableResponse response = client.getOrders();
        response.assertThat().statusCode(200).body(matchesJsonSchemaInClasspath("OrdersSchema.json"));

    }
}
