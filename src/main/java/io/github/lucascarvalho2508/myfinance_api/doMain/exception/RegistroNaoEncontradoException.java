package io.github.lucascarvalho2508.myfinance_api.doMain.exception;

public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException() {
        super("Registro não encontrado");
    }
}
