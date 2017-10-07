package fr.hadrienmp.error_management_styles.support_classes;

public class BusinessException extends RuntimeException {
    public BusinessException(String errors) {
        super(errors);
    }

    public String errors() {
        return getMessage();
    }
}
