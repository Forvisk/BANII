/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import clienteban2.tabelas.Cliente;
import clienteban2.tabelas.Estadia;
import clienteban2.tabelas.Hotel;
import clienteban2.tabelas.Quarto;
import clienteban2.tabelas.Reserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author gustavo
 */
public class JanelaAdicionaEstadia extends javax.swing.JFrame {

    Hotel hotel = null;
    Quarto quarto = null;
    Cliente cliente = null;
    Reserva reserva = null;

    boolean isChecked = false;

    /**
     * Creates new form JanelaAdicionaEstadia
     */
    public JanelaAdicionaEstadia() {
        initComponents();
        for (Hotel hot : Gerenciador.getInstancia().getHoteis().values()) {
            if (hotel == null) {
                hotel = hot;
            }
            jComboBox1.addItem(hot.getNome());
        }
        for (Quarto hot : hotel.getQuartos().values()) {
            if (quarto == null) {
                quarto = hot;
            }
            jComboBox2.addItem(hot.getLocal() + "");
        }
        for (Cliente cli : Gerenciador.getInstancia().getClientes().values()) {
            if (cliente == null) {
                cliente = cli;
            }
            jComboBox3.addItem(cli.getNome());
        }
        for (Reserva cli : Gerenciador.getInstancia().getReservas().values()) {
            if (cli.getCliente() != cliente || cli.getQuarto() != quarto) {
                continue;
            }
            if (reserva == null) {
                reserva = cli;
            }
            jComboBox4.addItem(cli.getCodigo() + " com check in: " + cli.getCheckIn());
        }
        jComboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jComboBox4.removeAllItems();
                int index = jComboBox3.getSelectedIndex() + 1;
                String nome = jComboBox3.getSelectedItem().toString();

                if (Gerenciador.getInstancia().getClientes().containsKey(index)) {
                    if (Gerenciador.getInstancia().getClientes().get(index).getNome().equals(nome)) {
                        cliente = Gerenciador.getInstancia().getClientes().get(index);
                    }
                }
                if (cliente == null) {
                    for (Cliente hot : Gerenciador.getInstancia().getClientes().values()) {
                        if (hot.getNome().equals(nome)) {
                            cliente = hot;
                            break;
                        }
                    }
                }
                for (Reserva hot : Gerenciador.getInstancia().getReservas().values()) {
                    if (hot.getCliente() == cliente && quarto == hot.getQuarto()) {
                        jComboBox4.addItem(hot.getCodigo() + " com check in: " + hot.getCheckIn());
                    }
                }
            }
        });
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jComboBox2.removeAllItems();
                int index = jComboBox1.getSelectedIndex() + 1;
                String nome = jComboBox1.getSelectedItem().toString();

                if (Gerenciador.getInstancia().getHoteis().containsKey(index)) {
                    if (Gerenciador.getInstancia().getHoteis().get(index).getNome().equals(nome)) {
                        hotel = Gerenciador.getInstancia().getHoteis().get(index);
                    }
                }
                if (hotel == null) {
                    for (Hotel hot : Gerenciador.getInstancia().getHoteis().values()) {
                        if (hot.getNome().equals(nome)) {
                            hotel = hot;
                            break;
                        }
                    }
                }
                for (Quarto hot : hotel.getQuartos().values()) {
                    jComboBox2.addItem(hot.getLocal() + "");
                }
                jComboBox4.removeAllItems();
                for (Reserva hot : Gerenciador.getInstancia().getReservas().values()) {
                    if (hot.getCliente() == cliente && quarto == hot.getQuarto()) {
                        jComboBox4.addItem(hot.getCodigo() + " com check in: " + hot.getCheckIn());
                    }
                }
            }
        });

        jComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = jComboBox2.getSelectedIndex() + 1;

                quarto = hotel.getQuartos().get(index);
                jComboBox4.removeAllItems();
                for (Reserva hot : Gerenciador.getInstancia().getReservas().values()) {
                    if (hot.getCliente() == cliente && quarto == hot.getQuarto()) {
                        jComboBox4.addItem(hot.getCodigo() + " com check in: " + hot.getCheckIn());
                    }
                }
            }
        });

        jCheckBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    isChecked = true;
                } else {//checkbox has been deselected
                    isChecked = false;
                };
                jComboBox4.setEnabled(isChecked);
                jLabel5.setEnabled(isChecked);
            }

        });
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
        jLabel5 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Por favor, insira os dados para incluir uma nova estadia:");

        jLabel2.setText("Hotel:");

        jLabel3.setText("Quarto:");

        jLabel4.setText("Cliente:");

        jLabel5.setText("Reserva:");
        jLabel5.setEnabled(false);

        jButton1.setText("Criar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox4.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            int idCliente = jComboBox2.getSelectedIndex() + 1;
            Cliente cliente = Gerenciador.getInstancia().getClientes().get(idCliente);

            SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);

            if (reserva == null && isChecked) {

                int index = jComboBox4.getSelectedIndex() + 1;
                if (Gerenciador.getInstancia().getReservas().containsKey(index)) {
                    reserva = Gerenciador.getInstancia().getReservas().get(index);
                }
            }

            int indexq = jComboBox2.getSelectedIndex() + 1;
            quarto = hotel.getQuartos().get(indexq);
            
            
            int indexc = jComboBox3.getSelectedIndex() + 1;
            cliente = Gerenciador.getInstancia().getClientes().get(indexc);

            if (!isChecked) {
                reserva = null;
            }

            Estadia estadia = Estadia.criarEstadia(quarto, cliente, reserva, strDate, null);
            System.out.println("Reservaaa: " + estadia.getReserva());
            ConnectDB.getInstance().RequestChange(estadia.insertQuery());
            Gerenciador.getInstancia().addEstadia(estadia);
            JanelaPrincipal.getInstancia().setupTabela();
            JOptionPane.showMessageDialog(this, "Estadia feita!", "Estadia feita!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Estadia falhou!", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
