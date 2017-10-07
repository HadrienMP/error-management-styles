package fr.hadrienmp.error_management_styles.support_classes;

import lombok.Data;

@Data
public class Response {
    private final String redirectUrl;
    private final String message;
}
