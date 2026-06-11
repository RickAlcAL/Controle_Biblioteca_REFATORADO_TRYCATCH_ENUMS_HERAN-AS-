package Entities.Aluno;

public class Aluno {
    protected int numeroMatricula;
    protected int multasPendentes;
    protected int emprestimosAtivos;

    public Aluno(int numeroMatricula, int multasPendentes,  int emprestimosAtivos) {
        this.numeroMatricula= numeroMatricula;
        this.multasPendentes= multasPendentes;
        this.emprestimosAtivos= emprestimosAtivos;
    }

    public Aluno() {

    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    // Metodo para verificar a existencia de multas para este aluno
    public boolean verificarMultas() {
        boolean temMulta= false;

        if (multasPendentes > 0) {
            temMulta = true;
        }
        return temMulta;
    }

    // Metodo para verificar o numero de emprestimos ativos para este aluno
    public boolean verificarEmprestimos() {
        boolean limiteAtingido = false;

        if (emprestimosAtivos >= 3) {
            limiteAtingido = true;
        }
        return limiteAtingido;
    }

    // Metodo para registrar um emprestimo para o aluno
    public void adicionarEmprestimo() {
        emprestimosAtivos++;
    }
}
