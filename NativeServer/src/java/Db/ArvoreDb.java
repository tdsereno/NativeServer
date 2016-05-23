/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.Arvore;
import Model.ConexaoBD;
import Model.Especie;
import Model.Proprietario;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Tiago
 */
public class ArvoreDb {

    public Boolean salvar(Arvore arvore) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "";

            if (arvore.getId() == 0) {

                sql = "INSERT INTO arvore VALUES"
                        + "(DEFAULT, "
                        + "'" + arvore.getEspecie().getId() + "',"
                        + "'" + arvore.getIdade() + "',"
                        + "'" + arvore.getLatitude() + "',"
                        + "'" + arvore.getLongitude() + "',"
                        + "'" + arvore.getEnderecoGeoCode() + "',"
                        + "'" + arvore.getPropietario().getId() + "',"
                        + "'" + arvore.getUsuario().getId() + "',"
                        + "'" + arvore.getStatus() + "')";
            } else {
                // update

                sql = "update arvore set especie_idespecie = "
                        + "'" + arvore.getEspecie().getId() + "',"
                        + "idade = '" + arvore.getIdade() + "',"
                        + "lat = '" + arvore.getLatitude() + "',"
                        + "long = '" + arvore.getLongitude() + "',"
                        + "geocode= '" + arvore.getEnderecoGeoCode() + "',"
                        + "proprietario_idproprietario = '" + arvore.getPropietario().getId() + "',"
                        + "usuario_idusuario = '" + arvore.getUsuario().getId() + "',"
                        + "status ='" + arvore.getStatus() + "' where idarvore = " + arvore.getId();
            }
            System.out.println("SQL = " + sql);

            return !st.execute(sql);

        } catch (Exception e) {
            System.out.println("Erro 0001 ao salvar arvore = " + e);
            return false;
        }
    }

    public ArrayList<Arvore> consultarTodas() {
        ArrayList<Arvore> arvores = new ArrayList<>();

        String sql = "select * from arvore ORDER BY idarvore";
        System.out.println("sql "+sql);
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultado.next()) {
                Arvore v = new Arvore();
                v.setEnderecoGeoCode(resultado.getString("geocode"));
                Especie e = new Especie();
                e.setId(resultado.getInt("especie_idespecie"));
                v.setEspecie(e);
                v.setId(resultado.getInt("idarvore"));
                v.setIdade(resultado.getInt("idade"));
                v.setLatitude(resultado.getString("lat"));
                v.setLongitude(resultado.getString("long"));
                Proprietario p = new Proprietario();
                p.setId(resultado.getInt("proprietario_idproprietario"));
                v.setPropietario(p);
                v.setStatus(resultado.getString("status"));
                Usuario u = new Usuario();
                u.setId(resultado.getInt("usuario_idusuario"));
                v.setUsuario(u);
                arvores.add(v);
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar arvores = " + e);
            System.out.println("Erro 0002 ao consultar arvores error =  " + e.toString());
            return null;
        }
        return arvores;
    }

    public Arvore consultarArvore(Arvore arvore) {

        String sql = "select * from arvore where idarvore = " + arvore.getId() + " ORDER BY idarvore"; // RS
        try {
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            Arvore v = new Arvore();
            if (resultado.next()) {

                v.setEnderecoGeoCode(resultado.getString("geocode"));
                Especie e = new Especie();
                e.setId(resultado.getInt("especie_idespecie"));
                v.setEspecie(e);
                v.setId(resultado.getInt("idarvore"));
                v.setIdade(resultado.getInt("idade"));
                v.setLatitude(resultado.getString("lat"));
                v.setLongitude(resultado.getString("long"));
                Proprietario p = new Proprietario();
                p.setId(resultado.getInt("proprietario_idproprietario"));
                v.setPropietario(p);
                v.setStatus(resultado.getString("status"));
                Usuario u = new Usuario();
                u.setId(resultado.getInt("usuario_idusuario"));
                v.setUsuario(u);
                return v;
            }

        } catch (Exception e) {
            System.out.println("Erro 0002 ao consultar arvores = " + e);
            return null;
        }
        return null;
    }

}
