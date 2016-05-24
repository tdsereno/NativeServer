package Model;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fabricio.pretto
 */
import java.sql.*;
import java.io.*;
import java.util.*;

public class ConexaoBD {

    private static ConexaoBD instancia = null;
    private Connection conexao = null;

    public ConexaoBD() {
        System.out.println("inicio construtor");
        try {
          
            // Carrega informações do arquivo de propriedades
            Properties prop = new Properties();
            //prop.load(new FileInputStream("DB.propertie s"));
            String dbdriver = "org.postgresql.Driver";
            String dburl = "jdbc:postgresql://localhost:5432/native";
            String dbuser = "postgres";
            String dbsenha = "postgres";
         


            // Carrega Driver do Banco de Dados
            System.out.println("carregando driver bd");
            Class.forName(dbdriver);
            System.out.println("carregado");
            if (dbuser.length() != 0) // conexão COM usuário e senha
            {
                conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
            } else // conexão SEM usuário e senha
            {
                conexao = DriverManager.getConnection(dburl);
            }

        } catch (Exception e) {
            System.out.println("erro ao criar conex "+e);
            System.err.println(e);
        }
    }

    // Retorna instância
    public static ConexaoBD getInstance() {
        System.out.println("instanciando");
        if (instancia == null) {
            System.out.println("constructor");
            instancia = new ConexaoBD();
            System.out.println("done");
        }
        return instancia;
    }

    // Retorna conexão
    public Connection getConnection() {
        System.out.println("get conection");
        if (conexao == null) {
            System.out.println("errror cx nul");
            throw new RuntimeException("conexao==null");
        }
        System.out.println("return");
        return conexao;
    }

    // Efetua fechamento da conexão
    public void shutDown() {
        try {
            conexao.close();
            instancia = null;
            conexao = null;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

