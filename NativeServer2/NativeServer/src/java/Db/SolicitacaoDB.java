/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.ConexaoBD;
import Model.Proprietario;
import Model.Solicitacao;
import Model.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class SolicitacaoDB {
        
    public ArrayList<Solicitacao> consultarTodas() {
        ArrayList<Solicitacao> solicitacoes = new ArrayList<>();

        String sql = "select * from solicitacao ORDER BY idsolicitacao";
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Solicitacao s = new Solicitacao();
                s.setId(resultado.getInt("idsolicitacao"));
                int idu = resultado.getInt("usuario_idusuario");
                int idp = resultado.getInt("proprietario_idproprietario");
                Usuario u = new Usuario();
                u.setId(idu);
                s.setUsuario(u);
                Proprietario p = new Proprietario();
                p.setId(idp);                
                s.setProprietario(p);
                s.setDataSolicitacao(resultado.getDate("data_solicitacao"));
                s.setDataEncerramento(resultado.getDate("data_encerramento"));
                s.setDescricao(resultado.getString("descricao"));
                solicitacoes.add(s);
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar solicitacaoes = " + e);
            return null;
        }
        return solicitacoes;
    }
}


