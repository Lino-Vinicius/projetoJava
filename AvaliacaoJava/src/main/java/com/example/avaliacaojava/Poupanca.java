package com.example.avaliacaojava;

public class Poupanca extends Conta{
    private Integer vencimento;

    public Poupanca(Integer numero, String titular, Integer vencimento) {
        super(numero, titular);
        this.vencimento = vencimento;
    }

    @Override
    public String toString() {
        return "conta=" + this.getConta() +
                ", titular='" + this.getTitular() + '\'' +
                ", saldo=" + this.getSaldo() +
                ", vencimento= "+ vencimento + System.lineSeparator();


    }

    public Integer getVencimento() {
        return vencimento;
    }

    public void setVencimento(Integer vencimento) {
        this.vencimento = vencimento;
    }
}
