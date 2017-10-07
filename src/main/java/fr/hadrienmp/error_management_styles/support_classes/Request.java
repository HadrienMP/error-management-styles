package fr.hadrienmp.error_management_styles.support_classes;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class Request {
    private final Map<String, String> params;

    Optional<String> formField(String paramName) {
        return Optional.ofNullable(params.get(paramName));
    }
}
