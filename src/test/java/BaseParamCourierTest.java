import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import ru.service.practicum.scooter.qa.client.Courier;
import ru.service.practicum.scooter.qa.client.Credentials;
import ru.service.practicum.scooter.qa.client.ScooterServiceClient;

import static ru.service.practicum.scooter.qa.Constants.*;

public class BaseParamCourierTest {
    Courier courier =
            new Courier(COURIER_DEFAULT_ID, COURIER_DEFAULT_PASSWORD, COURIER_DEFAULT_FIRST_NAME);
    private final ScooterServiceClient client = new ScooterServiceClient();
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
}
