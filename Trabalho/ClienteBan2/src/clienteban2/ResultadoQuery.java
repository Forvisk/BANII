/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class ResultadoQuery {

    private ArrayList<LinkedHashMap<String, String>> dados;
    private LinkedHashMap<String,String> colunas;

    public boolean sucesso;

    ResultadoQuery(ResultSet res, boolean suc) {
        this.dados = new ArrayList<>();
        this.colunas = new LinkedHashMap<>();

        ResultSet resultado = res;

        if (suc) {
            try {
                ResultSetMetaData metaDados = res.getMetaData();
                for (int i = 1; i <= metaDados.getColumnCount(); i++){
                    
                    //colunas.put(metaDados.getColumnName(i));
                }
                while (resultado.next()) {
                    LinkedHashMap<String, String> elemento = new LinkedHashMap<>();
                    for (int i = 1; i <= metaDados.getColumnCount(); i++) {
                        elemento.put(metaDados.getColumnName(i), resultado.getString(i));
                    }
                    this.dados.add(elemento);
                }

            } catch (SQLException ex) {
                Logger.getLogger(ResultadoQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.sucesso = suc;
    }

    public LinkedHashMap<String,String> getColunas() {
        return this.colunas;
    }

    public ArrayList<LinkedHashMap<String, String>> getDados() {
        return dados;
    }

}
