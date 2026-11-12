module org.example.ejerciciointegradorclase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.ejerciciointegradorclase to javafx.fxml;
    opens org.example.ejerciciointegradorclase.controller to javafx.fxml;


    opens org.example.ejerciciointegradorclase.model to javafx.base;


    exports org.example.ejerciciointegradorclase;
    exports org.example.ejerciciointegradorclase.controller;
    exports org.example.ejerciciointegradorclase.model;
    exports org.example.ejerciciointegradorclase.dao;
    exports org.example.ejerciciointegradorclase.navigation;
}