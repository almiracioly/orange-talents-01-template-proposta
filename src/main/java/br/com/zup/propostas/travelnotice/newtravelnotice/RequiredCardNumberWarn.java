package br.com.zup.propostas.travelnotice.newtravelnotice;

public class RequiredCardNumberWarn {
    private final String message = "O numero do cartão é obrigatório";

    static RequiredCardNumberWarn build(){
        return new RequiredCardNumberWarn();
    }

    public String getMessage() {
        return message;
    }
}
