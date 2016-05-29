/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.ConexaoBD;
import Model.Especie;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class EspecieDb {

    public ArrayList<Especie> consultarTodas() {
        ArrayList<Especie> especies = new ArrayList<>();

        String sql = "select * from especie ORDER BY idespecie";
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Especie e = new Especie();
                e.setId(resultado.getInt("idespecie"));
                e.setNome(resultado.getString("nome"));
                e.setDscricao(resultado.getString("descricao"));
                especies.add(e);
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar especies = " + e);
            return null;
        }
        return especies;
    }
   public Especie consultarEspecie(Especie e) {

        String sql = "select * from especie where idespecie = " + e.getId() + " ORDER BY idespecie"; // RS
        System.out.println(sql);
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                Especie esp = new Especie();
                esp.setId(resultado.getInt("idespecie"));
                esp.setNome(resultado.getString("nome"));
                
                return esp;
            }

        } catch (Exception ex) {
            System.out.println("Erro 0002 ao consultar especie = " + ex);
            return null;
        }
        return null;
    }
}
