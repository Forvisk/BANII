/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import clienteban2.tabelas.Hotel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
public class Gerenciador {

    private LinkedHashMap<Integer, Hotel> hoteis;
    public static final ArrayList<String> colunasHotel =
            new ArrayList(Arrays.asList("Código", "Nome", "Cidade", "Estado", "Endereço"));

    private static Gerenciador INSTANCIA;

    /**
     * Get the value of INSTANCIA
     *
     * @return the value of INSTANCIA
     */
    public static Gerenciador getInstancia() {
        return INSTANCIA;
    }

    public static void iniciateGerenciador() {
        if (INSTANCIA == null) {
            INSTANCIA = new Gerenciador();
        }
    }

    private Gerenciador() {
        this.hoteis = new LinkedHashMap<>();
        pushHoteis();
    }
    
    public void refresh(){
        pushHoteis();
    }

    private void pushHoteis() {
        try {
            ResultadoQuery conteudo = ClienteBan2.getInstance().conexao.RequestSelect("SELECT * FROM hotel;");
            conteudo.getDados().forEach((entry) -> {
                int codigo = Integer.parseInt(entry.get("hot_pk_codigo"));
                int estrelas = Integer.parseInt(entry.get("hot_in_estrelas"));
                String nome = entry.get("hot_st_nome");
                String cnpj = entry.get("hot_st_cnpj");
                String endereco = entry.get("hot_st_endereco");
                String cidade = entry.get("hot_st_cidade");
                String uf = entry.get("hot_st_uf");
                
                if (this.hoteis.containsKey(codigo)){
                    // Tem, entao vamos atualizar em vez de criar
                    Hotel hotel = this.hoteis.get(codigo);
                    
                    hotel.setCidade(cidade);
                    hotel.setCnpj(cnpj);
                    hotel.setEndereco(endereco);
                    hotel.setEstrelas(estrelas);
                    hotel.setNome(nome);
                    hotel.setUF(uf);
                } else {
                    try {
                        // Nao tem, entao vamos criar um novo
                        Hotel hotel = Hotel.criarHotel(codigo, nome, cnpj, cidade, uf, endereco, estrelas);
                        this.hoteis.put(codigo, hotel);
                        System.out.println("Hotel criado: " + hotel);
                    } catch (Exception ex) {
                        Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            });

        } catch (Exception ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addHotel(Hotel h) {
        hoteis.put(h.getCodigo(), h);
    }

    public LinkedHashMap<Integer, Hotel> getHoteis() {
        return hoteis;
    }

}
