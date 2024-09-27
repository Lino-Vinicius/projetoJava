module com.example.avaliacaojava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.avaliacaojava to javafx.fxml;
    exports com.example.avaliacaojava;
}