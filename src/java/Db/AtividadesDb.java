/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.Atividades;
import Model.ConexaoBD;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class AtividadesDb {
    
    public ArrayList<Atividades> consultarTodas() {
        ArrayList<Atividades> especies = new ArrayList<>();

        String sql = "select * from atividades ORDER BY idatividades";
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Atividades e = new Atividades();
                e.setId(resultado.getInt("idatividades"));
                e.setNome(resultado.getString("nome"));
                e.setDescricao(resultado.getString("descricao"));
                especies.add(e);
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar atividades = " + e);
            return null;
        }
        return especies;
    }
}
