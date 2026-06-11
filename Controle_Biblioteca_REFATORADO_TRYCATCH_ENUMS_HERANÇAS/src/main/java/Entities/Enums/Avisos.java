package Entities.Enums;

public enum Avisos {
    ERRO ("Erro... tente novamente"),
    SAINDO ("Saindo do sistema..."),
    ATENCAO ("Digite uma opção válida !!!"),
    BOAS_VINDAS ("Bem vindo ao sistema de biblioteca!");


    private final String mensagemUsuario;
    Avisos (String mensagemUsuario) {
        this.mensagemUsuario = mensagemUsuario;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }
}
