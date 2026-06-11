package Applications.Menu;

import Entities.Aluno.Aluno;
import Entities.Biblioteca.Biblioteca;
import Entities.Enums.Avisos;
import Entities.Livro.Livro;
import Entities.Enums.MenuOpcao;
import Entities.Enums.StatusRetorno;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    Biblioteca biblioteca = new Biblioteca();

    // Menu principal da biblioteca
    public void menuPrincipal() {
        MenuOpcao opcao; // Uso do Enum MenuOpcao
        boolean confirmacao = true;
        do {
            try {
                System.out.println("===============================");
                System.out.println(Avisos.BOAS_VINDAS.getMensagemUsuario());
                System.out.println(
                        "==============================="+
                                "\nDigite a opção que gostaria de realizar"+
                                "\n[1]- Pegar livro emprestado"+
                                "\n[2]- Sair"+
                                "\n==============================="
                );
                char resp = sc.next().charAt(0);
                opcao = MenuOpcao.deCodigo(resp);

                switch (opcao) {
                    case PEGAR_EMPRESTADO:
                        System.out.println("Informe o número de matricula do aluno:");
                        int matricula = sc.nextInt();

                        if (biblioteca.verificarMatricula(matricula)) {
                            Aluno aluno = biblioteca.getAlunoAtual();

                            if (!aluno.verificarMultas()) {
                                if (!aluno.verificarEmprestimos()) {
                                    menuEmprestimo(aluno);
                                } else {
                                    System.out.println(StatusRetorno.LIMITE_ATINGIDO.getMensagem());
                                }
                            } else {
                                System.out.println(StatusRetorno.BLOQUEADO_MULTA.getMensagem());
                            }
                        } else {
                            System.out.println(StatusRetorno.ALUNO_NAO_ENCONTRADO.getMensagem());
                        }
                        break;

                    case SAIR:
                        System.out.println(Avisos.SAINDO.getMensagemUsuario());
                        break;

                    default:
                        System.out.println(Avisos.ATENCAO.getMensagemUsuario());
                        confirmacao = false;
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println(Avisos.ERRO.getMensagemUsuario());
                sc.nextLine();
            }


        } while (confirmacao);

        sc.close();
    }

    // Menu de realização e confirmação de emprestimos
    private void menuEmprestimo(Aluno aluno) {
        boolean confirmacao = true;
        System.out.println("Informe o código do livro:");
        int codLivro = sc.nextInt();

        if (biblioteca.verificarLivro(codLivro)) {
            Livro livro = biblioteca.getLivroAtual();

            if (livro.verificarDisponivel()) {
                // Uso do Enum ConfirmacaoOpcao

                do {
                    try {
                        System.out.println(
                                "===============================" +
                                        "\nVocê deseja pegar este livro emprestado?:" +
                                        "\n" + livro.resumoLivro() +
                                        "\n[1]- Sim" +
                                        "\n[2]- Não" +
                                        "\n==============================="
                        );
                        char respConfirmacao = sc.next().charAt(0);

                        switch (respConfirmacao) {
                            case '1':
                                System.out.println(StatusRetorno.SUCESSO.getMensagem());
                                aluno.adicionarEmprestimo();
                                livro.tirarDisponibilidade();
                                System.out.println(Avisos.SAINDO.getMensagemUsuario());
                                confirmacao = false;
                                break;
                            case '2':
                                System.out.println(StatusRetorno.CANCELADO.getMensagem());
                                break;
                            default:
                                System.out.println(Avisos.ATENCAO.getMensagemUsuario());
                                break;
                        }
                    }catch (InputMismatchException e){
                        System.out.println(Avisos.ERRO.getMensagemUsuario());
                    }
                } while (confirmacao);
            } else {
                System.out.println(StatusRetorno.LIVRO_INDISPONIVEL.getMensagem());
            }
        } else {
            System.out.println(StatusRetorno.LIVRO_NAO_ENCONTRADO.getMensagem());
        }
    }
}