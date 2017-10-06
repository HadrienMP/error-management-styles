package fr.hadrienmp.error_management_styles;

import fr.hadrienmp.error_management_styles.support_classes.Request;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {

    private final Map<String, String> params = new HashMap<>();

    public RequestBuilder withField(String key, String value) {
        params.put(key, value);
        return this;
    }

    public Request build() {
        return new Request(params);
    }
}
