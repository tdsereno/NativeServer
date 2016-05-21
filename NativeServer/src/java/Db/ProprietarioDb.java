/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.Cidade;
import Model.ConexaoBD;
import Model.Proprietario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class ProprietarioDb {

    public ArrayList<Proprietario> consultarTodas() {
        ArrayList<Proprietario> propietarios = new ArrayList<>();

        String sql = "select * from proprietario ORDER BY idproprietario";
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Proprietario prop = new Proprietario();
                prop.setId(resultado.getInt("idproprietario"));
                prop.setNome(resultado.getString("nome"));
                prop.setIdentificacao(resultado.getString("identificacao"));
                Cidade c = new Cidade();
                c.setId(resultado.getInt("cidade_idcidade"));
                prop.setCidade(c);
                prop.setEnderecoRua(resultado.getString("end_rua"));

                propietarios.add(prop);
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar proprietarios = " + e);
            return null;
        }
        return propietarios;
    }

    public Proprietario consultarProprietario(Proprietario p) {

        String sql = "select * from proprietario where idproprietario = " + p.getId() + " ORDER BY idproprietario"; // RS
        System.out.println(sql);
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            if (resultado.next()) {
                Proprietario prop = new Proprietario();
                prop.setId(resultado.getInt("idproprietario"));
                prop.setNome(resultado.getString("nome"));
                prop.setIdentificacao(resultado.getString("identificacao"));
                Cidade c = new Cidade();
                c.setId(resultado.getInt("cidade_idcidade"));
                prop.setCidade(c);
                prop.setEnderecoRua(resultado.getString("end_rua"));

                return prop;
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar proprietarios = " + e);
            return null;
        }
        return null;
    }

}
