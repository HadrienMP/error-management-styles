package fr.hadrienmp.error_management_styles.support_classes;

import java.net.URL;
import java.util.Map;
import java.util.Optional;

public class Request {
    public final Map<String, String> params;

    public Request(Map<String, String> params) {
        this.params = params;
    }

    public URL referer() {
        return null;
    }

    public Optional<String> formField(String address) {
        return Optional.ofNullable(params.get(address));
    }
}
