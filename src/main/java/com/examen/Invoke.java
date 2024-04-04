package com.examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author Juan Cepeda
 */
public class Invoke {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final List<String> DOMAINS = Arrays.asList("ec2-23-22-207-186.compute-1.amazonaws.com:5000",
            "ec2-54-236-119-146.compute-1.amazonaws.com:5001");
    private static final List<String> LOG_SERVICE_URLS = generateUrls();
    private static final Logger LOGGER = LoggerFactory.getLogger(Invoke.class);
    private static int currentService = 0;

    /**
     * constructor
     */
    private Invoke() {
    }

    /**
     * Invokes a GET request to a specified URL
     *
     * @return The response from the GET request.
     * @throws IOException
     */
    public static String invoke(String message, String value) throws IOException {
        URL logService = getUrl(message, value);
        LOGGER.info("GET {}", logService);
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) logService.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int res = con.getResponseCode();
            LOGGER.info("GET Response Code :: {}", res);

            StringBuilder response = new StringBuilder();
            if (res == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                LOGGER.info("Response {}", response);
            } else {
                LOGGER.info("GET request ERROR");
            }
            return response.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
            LOGGER.info("GET FINISHED");
        }
    }

    /**
     * get the URL for the logService.
     *
     * @return the URL of the logService
     * @throws MalformedURLException
     */
    private static URL getUrl(String message, String value) throws MalformedURLException {
        String getUrl = LOG_SERVICE_URLS.get(currentService);
        currentService = (currentService + 1) % LOG_SERVICE_URLS.size();
        return new URL(getUrl + message + "?value=" + value);
    }

    /**
     * Generates a list of URLs based on the given list of ports.
     *
     * @param ports the list of ports to generate URLs for
     * @return a list of URLs generated from the given ports
     */
    private static List<String> generateUrls() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < DOMAINS.size(); i++) {
            urls.add("http://" + DOMAINS.get(i));
        }
        return urls;
    }

}
