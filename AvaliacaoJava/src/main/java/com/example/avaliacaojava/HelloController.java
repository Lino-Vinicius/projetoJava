package com.example.avaliacaojava;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label lblSaldo;

    @FXML
    private RadioButton rbCorrente;

    @FXML
    private RadioButton rbEspecial;

    @FXML
    private RadioButton rbPoupanca;

    @FXML
    private ToggleGroup tgConta;

    @FXML
    private TextArea txtAreaDados;

    @FXML
    private TextField txtConta;

    @FXML
    private TextField txtLimite;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private TextField txtTitular;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtVencimento;

    private Button btnDeposito;

    private Button btnSacar;

    private Conta cont;
    private List<Conta> listaContas = new ArrayList<>();


    @FXML
    protected void onSelecionarTipo() {
        if (rbCorrente.isSelected()) {
            txtLimite.setDisable(true);
            txtVencimento.setDisable(true);
        } else if (rbEspecial.isSelected()) {
            txtLimite.setDisable(false);
            txtVencimento.setDisable(true);
        } else {
            txtLimite.setDisable(true);
            txtVencimento.setDisable(false);
        }
    }

    @FXML
    protected void onCadastrarConta() {
        if (rbCorrente.isSelected()) {
            Conta conta = new Conta(Integer.parseInt(txtConta.getText()), txtTitular.getText());
            listaContas.add(conta);
        } else if (rbEspecial.isSelected()) {
            Especial contaEspecial = new Especial(Integer.parseInt(txtConta.getText()), txtTitular.getText(), Double.parseDouble(txtLimite.getText()));
            listaContas.add(contaEspecial);
        } else {
            Poupanca contaPoupanca = new Poupanca(Integer.parseInt(txtConta.getText()), txtTitular.getText(), Integer.parseInt(txtVencimento.getText()));
            listaContas.add(contaPoupanca);
        }
        //listaContas.add(cont);
        txtAreaDados.setText(listaContas.toString());
        limpaCampos();
    }

    private void limpaCampos() {
        txtConta.setText("");
        txtTitular.setText("");
        rbCorrente.setSelected(false);
        rbEspecial.setSelected(false);
        rbPoupanca.setSelected(false);
        txtLimite.setText("");
        txtVencimento.setText("");
        txtConta.requestFocus();
    }

    @FXML
    protected void onPesquisar() {
        Integer conta = Integer.parseInt(txtPesquisar.getText());
        limpaCampos();

        for (int i = 0; i < listaContas.size(); i++) {
            if (listaContas.get(i).getConta() == conta) {
                txtConta.setText(listaContas.get(i).getConta().toString());
                txtTitular.setText(listaContas.get(i).getTitular());
                lblSaldo.setText(listaContas.get(i).getSaldo().toString());

            if (listaContas.get(i).getClass().getSimpleName().equals("Conta")) {
                rbCorrente.setSelected(true);
                rbEspecial.setSelected(false);
                rbPoupanca.setSelected(false);
                txtLimite.setDisable(true);
                txtVencimento.setDisable(true);
            } else if (listaContas.get(i).getClass().getSimpleName().equals("Especial")) {
                rbCorrente.setSelected(false);
                rbEspecial.setSelected(true);
                rbPoupanca.setSelected(false);
                txtLimite.setDisable(false);
                txtVencimento.setDisable(true);
                Especial contaEspecial = (Especial) listaContas.get(i);
                txtLimite.setText(contaEspecial.getLimite().toString());
            } else {
                rbCorrente.setSelected(false);
                rbEspecial.setSelected(false);
                rbPoupanca.setSelected(true);
                txtLimite.setDisable(true);
                txtVencimento.setDisable(false);
                Poupanca contaPoupanca = (Poupanca) listaContas.get(i);
                txtVencimento.setText(contaPoupanca.getVencimento().toString());
            }
            }

        } //aqui termina o for
    }


    private Double saldo = 0.0;

    @FXML
    protected void onDepostar() {
        try {
            double valorDeposito = Double.parseDouble(txtValor.getText());

            if(valorDeposito > 0) {
                saldo += valorDeposito;
                atualizarSaldo();
                txtValor.clear();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro de Depósito!");
                alerta.setHeaderText("Depósito Invalido");
                alerta.setContentText("Insira um valor maior que 0 para depositar");
                alerta.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Formato!");
            alerta.setHeaderText("Valor numérico Invalido");
            alerta.setContentText("Por favor. Insira um valor numérico válido");
            alerta.showAndWait();
        }
    }

    @FXML
    protected void onSacar() {
        try {
            double valor = Double.parseDouble(txtValor.getText());

            if (valor > 0 && valor <= saldo) {
                saldo -= valor;
                atualizarSaldo();
                txtValor.clear();
            } else if(valor > saldo) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro de Saque!");
                alerta.setHeaderText("Saldo Insuficiente");
                alerta.setContentText("Você não tem saldo suficiente para sacar essa quantia");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro de Saque!");
                alerta.setHeaderText("Saque Invalido");
                alerta.setContentText("Insira um valor maior que 0 para sacar");
                alerta.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro de Formato");
            alerta.setHeaderText("Valor numérico inválido");
            alerta.setContentText("Por favor. Insira um valor numérico válido");
            alerta.showAndWait();
        }
    }

    private void atualizarSaldo() {
        lblSaldo.setText(String.format("%.2f", saldo));
    }
}