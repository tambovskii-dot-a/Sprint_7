import static ru.service.practicum.scooter.qa.Constants.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.service.practicum.scooter.qa.client.Courier;
import ru.service.practicum.scooter.qa.client.Credentials;
import ru.service.practicum.scooter.qa.client.ScooterServiceClient;

@RunWith(Parameterized.class)
public class LoginCourierWrongDataParamCourierTest extends BaseParamCourierTest {
  private final String login;
  private final String password;
  private final String firstName;
  private final ScooterServiceClient client = new ScooterServiceClient();

  public LoginCourierWrongDataParamCourierTest(String login, String password, String firstName) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
  }

  @Parameterized.Parameters
  public static Object[][] data() {
    return new Object[][] {
      {COURIER_DEFAULT_PASSWORD, COURIER_DEFAULT_PASSWORD, COURIER_DEFAULT_FIRST_NAME},
      {COURIER_DEFAULT_ID, COURIER_DEFAULT_ID, COURIER_DEFAULT_FIRST_NAME},
    };
  }

  @Test
  public void loginCourierTest() {
    Courier courier = new Courier(login, password, firstName);
    ValidatableResponse response = client.loginCourier(Credentials.fromCourier(courier));
    response.assertThat().onFailMessage("Учетная запись не найдена").and().statusCode(404);
  }
}
