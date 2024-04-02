package com.examen;

import static spark.Spark.*;

/**
 * 
 *
 * @author Juan Cepeda
 */
public class LogServerFacade {
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/logServerFacade", (req, res) -> Invoke.invoke(req.queryParams("message")));
    }

    /**
     * Retrieves the port from the environment variable or uses the default port
     * 8080.
     *
     * @return the port to use
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

}