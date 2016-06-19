/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.ConexaoBD;
import Model.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author Tiago
 */
public class UsuarioDb {
// usa uma function do banco que retorna o ID do usuario caso o usuario e senha estejam corretos, caso estiver incorreto retorna 0

    public int autenticarUsuario(Usuario u) {
        int retorno = 0;
        String sql = "SELECT autenticar( '" + u.getLogin() + "', '" + u.getSenha() + "')"; // RS

        System.out.println(sql);
        try {
            
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                System.out.println("veio um resultado");
                retorno = resultado.getInt("autenticar");
                System.out.println("o retorno é "+retorno);
            }

        } catch (Exception ex) {
            System.out.println("Erro ao efetuar login, erro: = " + ex);
            return 0;
        }
        return retorno;
    }
}
