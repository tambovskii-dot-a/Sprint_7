import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.service.practicum.scooter.qa.client.Order;
import ru.service.practicum.scooter.qa.client.ScooterServiceClient;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(Parameterized.class)
public class CreateOrderParamTest {
    private final List<String> colors;
    ScooterServiceClient client = new ScooterServiceClient();

    public CreateOrderParamTest(List<String> colors) {
        this.colors = colors;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {Arrays.asList()}
        });
      }
      @Test
    public void orderColorTest(){
          Order order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", colors);
          ValidatableResponse response = client.createOrder(order);
          response.assertThat().statusCode(201).body("track",not(emptyOrNullString()));

      }
}
