package br.com.zup.propostas.newcardlock;

public class AlreadyLockedCardResponse {
    private String message = "Este cartão já encontra-se bloqueado";

    public AlreadyLockedCardResponse() {

    }

    public AlreadyLockedCardResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
