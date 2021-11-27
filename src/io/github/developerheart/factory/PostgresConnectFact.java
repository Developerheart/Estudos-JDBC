package io.github.developerheart.factory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectFact {
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/treinamento";

    public Connection buildConnection(){

        try {
            Class.forName(DRIVER);
            Connection con;
            return (Connection) DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException ex) {
            System.err.print(ex.getMessage() + " Classe não encontrada");
            return null;
        } catch (SQLException e) {
            System.err.print(e.getMessage() + " ERRO AO SE CONECTAR");
            return null;
        }
    };


}

