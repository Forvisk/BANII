/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gustavo
 */
class ConnectDB {

    public static ConnectDB getInstance() throws Exception {
        if (instancia == null) {
            System.err.println("[ ConnectDB ] -> Execute ConnectDB.CreateDB() antes!");
            throw new Exception("Precisa usar a funcao CreateDB() antes");
        }
        return instancia;
    }

    public static ConnectDB CreateDB(String url, String user, String pw) {
        instancia = new ConnectDB(url, user, pw);
        return instancia;
    }

    private static ConnectDB instancia = null;

    private String sURL;
    private String sUserName;
    private String sPassword;

    private Connection dataBase;
    private Statement st;

    private ConnectDB() {

    }

    private ConnectDB(String url, String user, String pw) {
        this.sURL = url;
        this.sPassword = pw;
        this.sUserName = user;
    }

    public boolean Connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("[ ConnectDB ] -> Classe não encontrada no sistema!");
            return false;
        }

        try {
            dataBase = DriverManager.getConnection(this.sURL, this.sUserName, this.sPassword);
            st = dataBase.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            System.out.println("[ ConnectDB ] -> Conexão criada com sucesso!");

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public boolean RequestAdminLogin(String nome, String pw) throws Exception {
        if (dataBase == null) {
            System.err.println("[ ConnectDB ] -> Execute Connect() antes!");
            throw new Exception("Precisa usar a funcao Connect() antes");
        }
        
        String query = "SELECT 1 AS sucesso FROM administrador WHERE adm_login = ? AND adm_pw = ?";
        
        try {
            
            PreparedStatement ps = dataBase.prepareStatement(query);
            
            ps.setString(1, nome);
            ps.setString(2, pw);
            
            System.out.println(ps.toString());
            
            ResultSet result = ps.executeQuery();
            
            if (result.next()) {

                int res = result.getInt("sucesso");

                return res == 1;
            }
            return false;

        } catch (SQLException ex) {

            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean RequestBoolean(String query) throws Exception {
        if (dataBase == null) {
            System.err.println("[ ConnectDB ] -> Execute Connect() antes!");
            throw new Exception("Precisa usar a funcao Connect() antes");
        }

        try {
            ResultSet result = st.executeQuery(query);
            if (result.next()) {

                int res = result.getInt("sucesso");

                return res == 1;
            }
            return false;

        } catch (SQLException ex) {

            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ResultSet RequestRaw(String query) throws Exception {
        if (dataBase == null) {
            System.err.println("[ ConnectDB ] -> Execute Connect() antes!");
            throw new Exception("Precisa usar a funcao Connect() antes");
        }

        try {
            return st.executeQuery(query);

        } catch (SQLException ex) {

            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ResultadoQuery RequestSelect(String query) throws Exception {
        if (dataBase == null) {
            System.err.println("[ ConnectDB ] -> Execute Connect() antes!");
            throw new Exception("Precisa usar a funcao Connect() antes");
        }

        try {
            ResultSet result = st.executeQuery(query);
            return new ResultadoQuery(result, true);

        } catch (SQLException ex) {

            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResultadoQuery(null, false);
    }

    public void RequestChange(String query) throws Exception {
        if (dataBase == null) {
            System.err.println("[ ConnectDB ] -> Execute Connect() antes!");
            throw new Exception("Precisa usar a funcao Connect() antes");
        }
        try {
            int result = st.executeUpdate(query);
            System.out.println("[ ConnectDB ] -> Execução retornou: " + result);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
