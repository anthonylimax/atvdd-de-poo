import java.util.ArrayList;

class Conta {
    private String numero;
    private double saldo;
    private String nomeCliente;

    public Conta(String numero, double saldo, String nomeCliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.nomeCliente = nomeCliente;
    }

    // Getters e Setters omitidos para brevidade

    // Método equals para comparar contas pelo número
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Conta outraConta = (Conta) obj;
        return numero.equals(outraConta.numero);
    }

    @Override
    public String toString() {
        String text = "";
        text += "Titular da conta: " + this.nomeCliente + "\n";
        text += "Numero da conta " + this.numero + "\n";
        text += "Saldo: " + this.saldo+ "\n";
        return text;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado. Novo saldo: " + saldo);
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado. Novo saldo: " + saldo);
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
}

class Banco {
    private ArrayList<Conta> contas;

    public Banco() {
        contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
        System.out.println("Conta adicionada com sucesso.");
    }

    public void removerConta(Conta conta) {
        contas.remove(conta);
        System.out.println("Conta removida com sucesso.");
    }

    public void depositarEmConta(Conta conta, double valor) {
        conta.depositar(valor);
    }

    public void sacarDeConta(Conta conta, double valor) {
        conta.sacar(valor);
    }

    // Método para buscar conta pelo número
    public void buscarContaPorNumero(String numeroConta) throws ContaNaoEncontradaException {
        boolean contains = false;
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                System.out.println(conta.toString());;
                contains = true;
            }
        }
        if(!contains){
            throw new ContaNaoEncontradaException("Conta não encontrada.");
        }

    }

    // Método para buscar conta utilizando equals
    public void buscarConta(Conta c) throws ContaNaoEncontradaException {
        if (contas.contains(c)) {
            System.out.println(c.toString());
        }
        else{
            throw new ContaNaoEncontradaException("Conta não encontrada.");
        }
    }
}

class ContaNaoEncontradaException extends Exception {
    public ContaNaoEncontradaException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        Conta conta1 = new Conta("123", 1000.0, "Cliente1");
        Conta conta2 = new Conta("124", 2000.0, "Cliente2");
        Conta conta3 = new Conta("125", 1500.0, "Cliente3");

        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);
        banco.adicionarConta(conta3);

        try {
            banco.depositarEmConta(conta1, 500.0);
            banco.sacarDeConta(conta2, 1000.0);
            System.out.println("\n");
            banco.removerConta(conta3);
            banco.buscarContaPorNumero("123");
            banco.buscarConta(conta3);

        } catch (ContaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }
}