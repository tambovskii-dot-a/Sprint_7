package ru.service.practicum.scooter.qa.client;

public class Credentials {
  private final String login;
  private final String password;

  private Credentials(String login, String password) {
    this.password = password;
    this.login = login;
  }

  public static Credentials fromCourier(Courier courier) {
    return new Credentials(courier.getLogin(), courier.getPassword());
  }
}
