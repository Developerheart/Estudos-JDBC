package io.github.developerheart.dao;

import io.github.developerheart.factory.PostgresConnectFact;
import io.github.developerheart.model.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public static final String SQL = "INSERT INTO public.autor (id, nome, idade, nascimento, comentario) VALUES(?, ?, ?, ?, ?)";
    public static final String SQL_BUSCA_TODOS = "SELECT * FROM autor";
    public static final String SQL_BUSCA_POR_ID = "SELECT * FROM autor WHERE id = ?";
    public static final String SQL_BUSCA_POR_NOME = "SELECT * FROM autor WHERE nome like ?";
    public static final String SQL_UPDATE = "UPDATE autor SET nome = ?, idade = ?, nascimento = ?, comentario = ? where id  = ?";
    public static final String SQL_DELETE_BY_ID = "DELETE FROM autor WHERE id = ?";
    public static final String SQL_DELETE_ALL = "DELETE FROM autor";



    public boolean salvar(Autor autor) {
        Connection conn = new PostgresConnectFact().buildConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, autor.getId());
            preparedStatement.setString(2, autor.getNome());
            preparedStatement.setInt(3, autor.getIdade());
            preparedStatement.setDate(4, Date.valueOf(autor.getNascimento()));
            preparedStatement.setString(5, autor.getComentario());
            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
            System.out.println(autor + " autor salvo com sucesso");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public List<Autor> buscaAlunos() {
        Connection conn = new PostgresConnectFact().buildConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(SQL_BUSCA_TODOS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Autor> autors = new ArrayList<>();
            while (resultSet.next()) {
                Autor autor = new Autor();
                autor.setId(resultSet.getInt("id"));
                autor.setNome(resultSet.getString("nome"));
                autor.setComentario(resultSet.getString("comentario"));
                autor.setIdade(resultSet.getInt("idade"));
                autor.setNascimento(resultSet.getDate("nascimento").toLocalDate());
                autors.add(autor);
            }
            conn.close();
            preparedStatement.close();
            resultSet.close();
            return autors;
        } catch (SQLException e) {
            System.err.println("DEU RUIM " + e.getMessage());
            return null;
        }
    }

    public Autor buscaPorId(Integer id) {
        Connection conn = new PostgresConnectFact().buildConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(SQL_BUSCA_POR_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Autor autor = new Autor();
            try {
                if (resultSet.next()) {
                    autor.setId(resultSet.getInt("id"));
                    autor.setNome(resultSet.getString("nome"));
                    autor.setComentario(resultSet.getString("comentario"));
                    autor.setIdade(resultSet.getInt("idade"));
                    autor.setNascimento(resultSet.getDate("nascimento").toLocalDate());
                }
                conn.close();
                preparedStatement.close();
                resultSet.close();
                return autor;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Autor procurarPorNome(String nome){

        try {
            Connection connection = new PostgresConnectFact().buildConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_BUSCA_POR_NOME);
            ps.setString(1, "%".concat(nome).concat("%"));
            ResultSet resultSet  = ps.executeQuery();

            Autor autor = new Autor();
            if (resultSet.next()) {
                autor.setId(resultSet.getInt("id"));
                autor.setNome(resultSet.getString("nome"));
                autor.setComentario(resultSet.getString("comentario"));
                autor.setIdade(resultSet.getInt("idade"));
                autor.setNascimento(resultSet.getDate("nascimento").toLocalDate());
            }
            ps.close();
            connection.close();
            resultSet.close();
            return autor;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String editarAutor(Autor autor, Integer id){
        try {
            Connection connection = new PostgresConnectFact().buildConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, autor.getNome());
            preparedStatement.setInt(2, autor.getIdade());
            preparedStatement.setDate(3, Date.valueOf(autor.getNascimento()));
            preparedStatement.setString(4, autor.getComentario());
            preparedStatement.setInt(5, autor.getId());
            preparedStatement.execute();
            preparedStatement.close();

            return String.format("O autor de id %d" + autor, id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String removerPorId(Integer id){

        try {
            Connection connection = new PostgresConnectFact().buildConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            connection.close();
            preparedStatement.close();
            return  String.format("Autor de ID=%d excluido com sucesso", id);



        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean removerTodos(){
        try {
            Connection connection = new PostgresConnectFact().buildConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

}
