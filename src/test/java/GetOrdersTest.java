import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.service.practicum.scooter.qa.client.ScooterServiceClient;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {
    ScooterServiceClient client = new ScooterServiceClient();
    @Test
    public void getOrders(){
        ValidatableResponse response = client.getOrders();
        response.assertThat().statusCode(200).body("orders",is(notNullValue()));

    }
}
