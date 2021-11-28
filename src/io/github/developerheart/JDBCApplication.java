package io.github.developerheart;

import io.github.developerheart.dao.AutorDAO;
import io.github.developerheart.factory.PostgresConnectFact;
import io.github.developerheart.model.Autor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

public class JDBCApplication {

    public static void main(String[] args) throws SQLException {
        AutorDAO dao = new AutorDAO();
        dao.buscaAlunos().forEach(System.out::println);
//        System.out.println(dao.buscaPorId(2));
//        Autor autor = dao.buscaPorId(2);
//        autor.setComentario("FOI ATUALIZADO PELO METODO");
//        System.out.println(dao.editarAutor(autor, 2));
//        System.out.println("editado");
//        System.out.println(dao.buscaPorId(2));
        dao.removerPorId(1);
        dao.buscaAlunos().forEach(System.out::println);
        dao.removerTodos();
        dao.buscaAlunos().forEach(System.out::println);

//        System.out.println(dao.procurarPorNome("FRAMEN"));

//        dao.salvar(new Autor(3, "PAO", LocalDate.of(1000, 4, 3), 6612,"PAOZINHA NA CHAPA"));
//        PostgresConnectFact postgresConnectFact = new PostgresConnectFact();
//        Connection connection = postgresConnectFact.buildConnection();
//        if (connection!=null){
//            connection.close();
//            JOptionPane.showMessageDialog(null, "deu certo eu acho");
//        }else {
//            JOptionPane.showMessageDialog(null, "não foi possivel ");
//        }
    }

}
