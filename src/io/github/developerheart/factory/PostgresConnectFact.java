package io.github.developerheart.factory;

import javax.swing.*;
import java.sql.*;
import javax.sql.DataSource;

public class PostgresConnectFact {
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/treinamento";

    public Connection buildConnection() {

        try {
            Class.forName(DRIVER);
            Connection con;
            return (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.err.print(ex.getMessage() + " Classe não encontrada");
        } catch (SQLException e) {
            tratarErrprSQL(e);
        }
        return null;
    }

    /***
     * tratar error
     * @param  exception {@link SQLException} parametro de excessao
     */

    private void tratarErrprSQL(SQLException exception) {
        switch (exception.getSQLState()) {
            case "3D000":
                JOptionPane.showMessageDialog(null, exception.getMessage());
                break;
            case "28P01":
                System.err.println("DIGITOU A SENHA ERRADA PO");
                break;
            default: {
                System.err.println(exception.getMessage() + " DEU RUIM");
                break;
            }

        }
    }
}

