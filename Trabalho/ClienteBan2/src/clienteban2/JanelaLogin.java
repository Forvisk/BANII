package clienteban2;

import java.awt.Color;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gustavo
 */
public class JanelaLogin extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public JanelaLogin() {
        initComponents();
    }

    public void connected(boolean foi) {
        lConexao.setForeground(foi ? Color.GREEN : Color.RED);
        lConexao.setText(foi ? "Conectado" : "Não conectado");
        bLogin.setEnabled(foi);
        sUser.setEnabled(foi);
        sPw.setEnabled(foi);

        if (foi) {
            sUser.requestFocus();
        }
    }

    private boolean checkLogin() {
        String user = sUser.getText().trim();
        if (user.isEmpty()){
            return false;
        }
        String pw = (new String(sPw.getPassword())).trim();
        if (pw.isEmpty()){
            return false;
        }
        try {
            return ClienteBan2.getInstance().conexao.RequestAdminLogin(new String(sPw.getPassword()), sUser.getText());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sUser = new javax.swing.JTextField();
        bLogin = new javax.swing.JButton();
        sPw = new javax.swing.JPasswordField();
        bConecta = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lConexao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Por favor, preencha os dados abaixo para se conectar com a plataforma:");

        jLabel2.setText("Bem vindo ao sistema de gerenciamento de hoteis da VivaBem!");

        jLabel3.setText("Login:");

        jLabel4.setText("Senha:");

        sUser.setText("admin");
        sUser.setEnabled(false);

        bLogin.setText("Login");
        bLogin.setEnabled(false);
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        sPw.setText("admin");
        sPw.setEnabled(false);

        bConecta.setText("Conectar com o servidor");
        bConecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConectaActionPerformed(evt);
            }
        });

        jLabel5.setText("Servidor:");

        lConexao.setForeground(new java.awt.Color(255, 51, 0));
        lConexao.setText("Não conectado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bLogin)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(sUser)
                                .addComponent(sPw, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bConecta)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lConexao))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lConexao)
                    .addComponent(sUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bConecta)
                    .addComponent(sPw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bLogin))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
        // TODO add your handling code here:
        boolean temAcesso = checkLogin();

        if (temAcesso) {
            Gerenciador.iniciateGerenciador();
            JanelaPrincipal.iniciateJanelaPrincipal();
            JanelaPrincipal.getInstancia().setLocationRelativeTo(null);
            JanelaPrincipal.getInstancia().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, verifique seu usuario e sua senha!", "Senha ou Usuario errados!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bLoginActionPerformed

    private void bConectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConectaActionPerformed
        // TODO add your handling code here:
        JanelaConectar cs = new JanelaConectar(this);
        cs.setLocationRelativeTo(null);
        cs.setVisible(true);
    }//GEN-LAST:event_bConectaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConecta;
    private javax.swing.JButton bLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lConexao;
    private javax.swing.JPasswordField sPw;
    private javax.swing.JTextField sUser;
    // End of variables declaration//GEN-END:variables
}
