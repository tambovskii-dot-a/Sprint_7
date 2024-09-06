package ru.service.practicum.scooter.qa.client;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class ScooterServiceClient {
  private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

  @Step("create Courier /api/v1/courier")
  public ValidatableResponse createCourier(Courier courier) {
    return given()
        .baseUri(BASE_URL)
        .header("Content-Type", "application/json")
        .body(courier)
        .post("/api/v1/courier")
        .then();
  }

  @Step("login Courier /api/v1/courier/login")
  public ValidatableResponse loginCourier(Credentials credentials) {
    return given()
        .baseUri(BASE_URL)
        .header("Content-Type", "application/json")
        .body(credentials)
        .post("/api/v1/courier/login")
        .then();
  }

  @Step("delete Courier /api/v1/courier/")
  public void deleteCourier(String courierId) {
    given()
        .baseUri(BASE_URL)
        .header("Content-Type", "application/json")
        .delete("/api/v1/courier/" + courierId)
        .then()
        .assertThat()
        .statusCode(200)
        .and()
        .body("ok", is(true));
  }

  @Step("create Order /api/v1/orders")
  public ValidatableResponse createOrder(Order order) {
    return given()
        .baseUri(BASE_URL)
        .header("Content-Type", "application/json")
        .body(order)
        .post("/api/v1/orders")
        .then();
  }

  @Step("Get Orders /api/v1/orders")
  public ValidatableResponse getOrders() {
    return given()
        .baseUri(BASE_URL)
        .header("Content-Type", "application/json")
        .get("/api/v1/orders")
        .then();
  }
}
