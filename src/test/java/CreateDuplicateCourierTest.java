import static ru.service.practicum.scooter.qa.Constants.*;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.service.practicum.scooter.qa.client.Courier;
import ru.service.practicum.scooter.qa.client.Credentials;
import ru.service.practicum.scooter.qa.client.ScooterServiceClient;

public class CreateDuplicateCourierTest {
  private final ScooterServiceClient client = new ScooterServiceClient();
  Courier courier =
      new Courier(COURIER_DEFAULT_ID, COURIER_DEFAULT_PASSWORD, COURIER_DEFAULT_FIRST_NAME);

  @Before
  public void createCourier() {
    client.createCourier(courier);
  }

  @After
  public void cleanUp() {
    ValidatableResponse response = client.loginCourier(Credentials.fromCourier(courier));
    String courierId = response.extract().jsonPath().getString("id");
    client.deleteCourier(courierId);
  }

  @Test
  public void createNewCourierWithDuplicateLogin() {
    ValidatableResponse response = client.createCourier(courier);
    response.assertThat().onFailMessage("Этот логин уже используется").and().statusCode(409);
  }
}
