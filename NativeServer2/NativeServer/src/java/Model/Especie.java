/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Tiago
 */
public class Especie {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDscricao() {
        return dscricao;
    }

    public void setDscricao(String dscricao) {
        this.dscricao = dscricao;
    }
    
    int id;
    String nome, dscricao;
    
    
    
}
