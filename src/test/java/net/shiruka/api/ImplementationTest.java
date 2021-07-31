package net.shiruka.api;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.llorllale.cactoos.matchers.Throws;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final class ImplementationTest {

  private static final Server SERVER = new MockServer();

  @Test
  @Order(1)
  void getServer() {
    MatcherAssert.assertThat(
      "Server set somewhere!",
      Implementation::getServer,
      new Throws<>(NullPointerException.class));
  }

  @Test
  @Order(2)
  void setServer() {
    Implementation.setServer(ImplementationTest.SERVER);
    MatcherAssert.assertThat(
      "Server couldn't set!",
      () -> {
        Implementation.setServer(ImplementationTest.SERVER);
        return "null";
      },
      new Throws<>(IllegalArgumentException.class));
  }
}
