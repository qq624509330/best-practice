package io.spldeolin.bestpractice.util;

import java.text.SimpleDateFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -8909209092708797621L;

    public JacksonObjectMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
    }

}
