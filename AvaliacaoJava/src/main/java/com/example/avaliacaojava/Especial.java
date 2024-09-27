package com.example.avaliacaojava;

public class Especial extends  Conta{
    private Double limite;

    public Especial(Integer conta, String titular, Double limite) {
        super(conta, titular);
        this.limite = limite;
    }

    @Override
    public Boolean sacar(Double valor) {
        if (valor <= (this.getSaldo() + this.limite)) {
            this.setSaldo(this.getSaldo() - valor);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "conta=" + this.getConta() +
                ", titular='" + this.getTitular() + '\'' +
                ", saldo=" + this.getSaldo() +
                ", limite= " + limite + System.lineSeparator();
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }
}
