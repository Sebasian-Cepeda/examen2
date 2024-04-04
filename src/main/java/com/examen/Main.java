package com.examen;

import static spark.Spark.*;

public class Main {
  public static void main(String... args) {
    port(getPort());
    // staticFileLocation("public");
    get("/primos", (req, res) -> Service.primos(req.queryParams("value")));
    get("/factores", (req, res) -> Service.factores(req.queryParams("value")));
  }

  public static String primo(String value) {
    return null;
  }

  private static int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 4567;
  }
}
