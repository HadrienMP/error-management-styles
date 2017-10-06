package fr.hadrienmp.error_management_styles.support_classes;

public class ResponseBuilder {
    private String redirectUrl;
    private String message;

    public static ResponseBuilder redirectTo(String url) {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        responseBuilder.redirectUrl = url;
        return responseBuilder;
    }

    public ResponseBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public Response response() {
        return new Response(redirectUrl, message);
    }
}
