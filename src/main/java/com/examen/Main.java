package com.examen;

import static spark.Spark.*;

import java.util.List;

public class Main {
  public static void main(String... args) {
    port(getPort());
    // staticFileLocation("public");
    get("/primos", (req, res) -> {
      String value = req.queryParams("value");
      List<String> output = Service.primos(value);
      res.type("application/json");
      if (output.isEmpty()) {
        return "{\"operation\":\"primes\", \"input\":" + Integer.parseInt(value) + ", \"output\":\"No es primo\"}";
      } else {
        return toJson("primes", Integer.parseInt(value), output);
      }
    });
    get("/factores", (req, res) -> {
      String value = req.queryParams("value");
      List<String> output = Service.factores(value);
      res.type("application/json");
      return toJson("factors", Integer.parseInt(value), output);
    });
  }

  private static int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 4567;
  }

  private static String toJson(String operation, int input, List<String> output) {
    StringBuilder outputStr = new StringBuilder();
    for (int i = 0; i < output.size() - 1; i++) {
      outputStr.append(output.get(i)).append(",");
    }
    outputStr.append(output.get(output.size() - 1));
    return "{\"operation\":\"" + operation + "\", \"input\":" + input + ", \"output\":\"" + outputStr.toString()
        + "\"}";
  }
}
