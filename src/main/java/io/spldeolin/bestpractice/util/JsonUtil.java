package io.spldeolin.bestpractice.util;

import java.text.SimpleDateFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper om;

    static {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
    }

    // TODO

}
