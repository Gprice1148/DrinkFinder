package com.gordon.Dhelve.util;

import com.gordon.Dhelve.exception.Dhelve;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String readResponse(HttpURLConnection connection)
        throws Dhelve, IOException {

        String inputStreamAsString = new BufferedReader(
            new InputStreamReader(connection.getInputStream()))
            .lines()
            .collect(Collectors.joining("\n"));

        if (inputStreamAsString.isEmpty()) {
            logger.info("Response is empty");
            throw new Dhelve("Response is empty");
        }

        return inputStreamAsString;
    }

}
