/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;


/**
 *
 * @author gustavo
 */
public class ClienteBan2 {
    
    // Globais:
    public ConnectDB conexao;
    // Fim Globais
    
    private static ClienteBan2 instance;
    
    public static ClienteBan2 getInstance(){
        return instance;
    }
    
    private ClienteBan2(){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClienteBan2.instance = new ClienteBan2();
        
        JanelaLogin login = new JanelaLogin();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        

    }

}
