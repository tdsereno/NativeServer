/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.Cidade;
import Model.ConexaoBD;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class CidadeDb {

    public ArrayList<Cidade> consultarTodas() {
        ArrayList<Cidade> cidades = new ArrayList<>();

        String sql = "select c.idcidade, c.nome as nomecidade, e.nome as estado from cidade c, estado e where c.estado_idestado = e.idestado";
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Cidade c = new Cidade();
                c.setId(resultado.getInt("idcidade"));
                String cidade = resultado.getString("nomecidade");
                String estado = resultado.getString("estado");
                c.setNome(cidade+" - "+estado);
                cidades.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar cidade = " + e);
            return null;
        }
        return cidades;
    }

    public Cidade consultarCidade(Cidade ci) {

        String sql = "select c.idcidade, c.nome as nomecidade, e.nome as estado from cidade c, estado e where c.estado_idestado = e.idestado and c.idcidade = "+ci.getId()+" "; // RS
        System.out.println(sql);
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                Cidade c = new Cidade();
                c.setId(resultado.getInt("idcidade"));
                String cidade = resultado.getString("nomecidade");
                String estado = resultado.getString("estado");
                c.setNome(cidade+" - "+estado);
                return c;
            }

        } catch (Exception ex) {
            System.out.println("Erro 0002 ao consultar cidaed = " + ex);
            return null;
        }
        return null;
    }

}
