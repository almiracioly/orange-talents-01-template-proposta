package br.com.zup.propostas.shared.controlleradvice.requestvalidation;

public class RequestFieldErrorOutput {
    private String field;
    private String message;

    public RequestFieldErrorOutput(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
