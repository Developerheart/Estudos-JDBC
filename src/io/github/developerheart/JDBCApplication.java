package io.github.developerheart;

import io.github.developerheart.factory.PostgresConnectFact;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCApplication {

    public static void main(String[] args) throws SQLException {

        PostgresConnectFact postgresConnectFact = new PostgresConnectFact();
        Connection connection = postgresConnectFact.buildConnection();
        JOptionPane.showMessageDialog(null, "Connectado com sucesso");
        connection.close();
    }

}
