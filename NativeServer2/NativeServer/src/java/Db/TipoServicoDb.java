/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.ConexaoBD;
import Model.Proprietario;
import Model.Solicitacao;
import Model.TipoServico;
import Model.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class TipoServicoDb {
      public ArrayList<TipoServico> consultarTodas() {
        ArrayList<TipoServico> tiposervico = new ArrayList<>();

        String sql = "select * from tipo_servico ORDER BY idtiposervico";
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                TipoServico tp = new TipoServico();
                tp.setId(resultado.getInt("idtiposervico"));
                tp.setNome(resultado.getString("nome"));
                tp.setDescricao(resultado.getString("descricao"));
                tiposervico.add(tp);
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar tipos servico = " + e);
            return null;
        }
        return tiposervico;
    }
}
