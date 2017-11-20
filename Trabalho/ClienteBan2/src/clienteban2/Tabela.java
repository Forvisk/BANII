/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author gustavo
 */
public class Tabela {

    private String name;
    private ArrayList<Coluna> colunas;
    private ResultadoQuery conteudo;
    private String primaryKey;
    
    private String insertionFunction;

    public Tabela(String name) {
        this.name = name;
        this.colunas = new ArrayList<>();
        insertionFunction = null;
    }

    public void refreshResults() throws Exception {

        this.conteudo = ClienteBan2.getInstance().conexao.RequestSelect("SELECT * FROM " + this.name + ";");
        
        ResultSet pkRes = ClienteBan2.getInstance().conexao.RequestRaw("SELECT a.attname\n" +
                "FROM   pg_index i\n" +
                "JOIN   pg_attribute a ON a.attrelid = i.indrelid AND a.attnum = ANY(i.indkey)\n" +
                "WHERE  i.indrelid = '" + this.name + "'::regclass AND i.indisprimary;");
        
        
        
        if (pkRes.next()){
            primaryKey = pkRes.getString(1);
        }
        pkRes.close();

        ResultSet resColunas = ClienteBan2.getInstance().conexao.RequestRaw("SELECT "
                + "column_name, data_type FROM information_schema.columns WHERE "
                + "table_name='" + this.name + "';");

        if (resColunas != null) {
            this.colunas.clear();
            while (resColunas.next()) {
                String nome1 = resColunas.getString(1);
                String nome2 = resColunas.getString(2);
                if (nome2.equals("character varying")) {
                    nome2 = "string";
                }
                
                boolean isPK = nome1.equals(primaryKey);
                
                this.colunas.add(new Coluna(nome1, nome2, isPK));

            }
        }

        System.out.println("Colunas:");
        System.out.println(colunas.toString());

    }
    
    public String getPrimaryKey(){
        return this.primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultadoQuery getConteudo() {
        return conteudo;
    }

    public void setConteudo(ResultadoQuery conteudo) {
        this.conteudo = conteudo;
    }

    public ArrayList<Coluna> getColunas() {
        return colunas;
    }

    public void setColunas(ArrayList<Coluna> listaColunas) {
        this.colunas = listaColunas;
    }

    boolean hasInsertionFunction() {
        return (insertionFunction == null ? false : !insertionFunction.isEmpty());
    }

    String getInsertionFunctionName() {
        return insertionFunction;
    }
}
