package com.example.avaliacaojava;

public class Conta {

    private Integer conta;

    private String titular;

    private Double saldo;



    public Conta(Integer conta, String titular) {
        this.conta = conta;
        this.titular = titular;
        this.saldo = 0.0;

    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }


    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "conta=" + conta +
                ", titular='" + titular + '\'' +
                ", saldo=" + saldo + System.lineSeparator();

    }

    public void depositar(Double valor) {
        this.saldo = this.saldo + valor;
    }

    public Boolean sacar(Double valor) {
        if (valor <= this.saldo) {
            this.saldo = this.saldo - valor;
            return true;
        } else {
            return false;
        }
    }
}
