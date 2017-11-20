/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteban2;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    DefaultTableModel modeloTabela1;
    Tabela teste;
    int tabela1PKColumnIndex;
    private LinkedHashMap<Coluna, JTextField> tabela1Campos;

    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() {
        initComponents();

        modeloTabela1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        tTabela1.setModel(modeloTabela1);
        tabela1Campos = new LinkedHashMap<>();
        pDados.setLayout(new GridLayout(0, 2));
        pScrollPane1.getVerticalScrollBar()
                .setUnitIncrement(16);

        setupTabela();

    }

    public void setupTabela() {
        setTabela1();
    }

    private void setTabela1() {
        modeloTabela1.setRowCount(0);
        modeloTabela1.setColumnCount(0);
        try {

            teste = new Tabela("hotel");
            teste.refreshResults();

            teste.getColunas().forEach((coluna) -> {
                System.out.println(coluna.getNome());
                modeloTabela1.addColumn(coluna.getNome().toUpperCase());
            });

            teste.getConteudo().getDados().stream().map((LinkedHashMap<String, String> elemento) -> {
                ArrayList<String> row = new ArrayList<>();
                elemento.entrySet().forEach((linha) -> {
                    row.add(linha.getValue());
                });
                return row;
            }).forEachOrdered((row) -> {
                modeloTabela1.addRow(row.toArray());
            });

        } catch (Exception ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        tTabela1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        ListSelectionModel selectionModel = tTabela1.getSelectionModel();

        selectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            pDados.removeAll();
            tabela1Campos.clear();
            try {
                int selectedRow = tTabela1.getSelectedRowCount();
                if (selectedRow == 1) {
                    for (int i = 0; i < teste.getColunas().size(); i++) {

                        JLabel label = new JLabel(teste.getColunas().get(i).getNome().toUpperCase() + ":");
                        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
                        label.setMinimumSize(new Dimension(50, 50));
                        pDados.add(label);

                        JTextField text = new JTextField(tTabela1.getValueAt(tTabela1.getSelectedRow(), i).toString());
                        text.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
                        text.setMinimumSize(new Dimension(50, 50));
                        pDados.add(text);

                        tabela1Campos.put(teste.getColunas().get(i), text);

                        //pDados.add(panel);
                    }

                    bDeletar1.setEnabled(true);
                    bDeletar1.setToolTipText("Clique para deletar!");

                    bAtualizar1.setEnabled(true);
                    bAtualizar1.setToolTipText("Clique para salvar os dados!");

                } else if (selectedRow > 1) {
                    bDeletar1.setEnabled(true);
                    bDeletar1.setToolTipText("Clique para deletar essas " + selectedRow + " linhas!");

                    bAtualizar1.setEnabled(false);
                    bAtualizar1.setToolTipText("Não é possivel salvar mais de um por vez!");

                    // 0 selected
                } else {
                    bAtualizar1.setEnabled(false);
                    bAtualizar1.setToolTipText("Selecione uma linha!");

                    bDeletar1.setEnabled(false);
                    bDeletar1.setToolTipText("Selecione uma linha!");
                }
            } catch (Exception ex) {

            }
            pDados.revalidate();
            pDados.repaint();
        });

        for (int i = 0; i < tTabela1.getColumnCount(); i++) {
            if (modeloTabela1.getColumnName(i).equals(teste.getPrimaryKey())) {
                tabela1PKColumnIndex = i;
                break;
            }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tTabela1 = new javax.swing.JTable();
        bCriar1 = new javax.swing.JButton();
        bAtualizar1 = new javax.swing.JButton();
        bDeletar1 = new javax.swing.JButton();
        pScrollPane1 = new javax.swing.JScrollPane();
        pDados = new javax.swing.JPanel();
        bReload1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tTabela1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tTabela1);

        bCriar1.setText("Criar");
        bCriar1.setToolTipText("Abre uma janela para acrescentar novos dados");
        bCriar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCriar1ActionPerformed(evt);
            }
        });

        bAtualizar1.setText("Salvar mudanças");
        bAtualizar1.setToolTipText("Clique para salvar!");
        bAtualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtualizar1ActionPerformed(evt);
            }
        });

        bDeletar1.setText("Deletar");
        bDeletar1.setToolTipText("Clique para deletar!");
        bDeletar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeletar1ActionPerformed(evt);
            }
        });

        pScrollPane1.setBackground(new java.awt.Color(238, 238, 238));
        pScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        pScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout pDadosLayout = new javax.swing.GroupLayout(pDados);
        pDados.setLayout(pDadosLayout);
        pDadosLayout.setHorizontalGroup(
            pDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );
        pDadosLayout.setVerticalGroup(
            pDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        pScrollPane1.setViewportView(pDados);

        bReload1.setText("Atualiza dados");
        bReload1.setToolTipText("Faz uma requisição de atualização para o Banco de Dados");
        bReload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReload1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pScrollPane1)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bReload1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCriar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bAtualizar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDeletar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bDeletar1)
                    .addComponent(bAtualizar1)
                    .addComponent(bCriar1)
                    .addComponent(bReload1)))
        );

        jTabbedPane1.addTab("Hoteis", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab3", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab4", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCriar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCriar1ActionPerformed
        // TODO add your handling code here:
        JanelaCriar jc = new JanelaCriar(this, teste);
        jc.setLocationRelativeTo(null);
        jc.setVisible(true);
    }//GEN-LAST:event_bCriar1ActionPerformed

    private void bReload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReload1ActionPerformed
        // TODO add your handling code here:
        try {
            setupTabela();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_bReload1ActionPerformed

    private void bDeletar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeletar1ActionPerformed
        // TODO add your handling code here:
        StringBuilder valores = new StringBuilder();
        valores.append("DELETE FROM ");
        valores.append(teste.getName());
        valores.append(" WHERE ");
        valores.append(teste.getPrimaryKey());
        valores.append(" IN (");
        boolean primeiro = true;
        for (int i : tTabela1.getSelectedRows()) {
            if (primeiro) {
                primeiro = false;
            } else {
                valores.append(", ");
            }
            valores.append(tTabela1.getValueAt(i, tabela1PKColumnIndex));
        }
        valores.append(")");
        try {
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor(es) removido(s) com sucsso!", "Remoção executada", JOptionPane.INFORMATION_MESSAGE);
            this.setupTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problema ao remover valores!", "Remoção falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bDeletar1ActionPerformed

    private void bAtualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtualizar1ActionPerformed
        // TODO add your handling code here:
        StringBuilder valores = new StringBuilder();
        valores.append("UPDATE ");
        valores.append(teste.getName());
        valores.append(" SET ");
        int rowSelecionada = tTabela1.getSelectedRow();
        boolean primeiro = true;
        for (int i = 0; i < teste.getColunas().size(); i++) {
            Coluna col = teste.getColunas().get(i);
            JTextField text = tabela1Campos.get(col);

            if (primeiro) {
                primeiro = false;
            } else {
                valores.append(", ");
            }

            valores.append(col.getNome());
            valores.append(" = ");

            if (col.getTipo().equals("string")) {
                valores.append('\'');
            }
            valores.append(text.getText());
            if (col.getTipo().equals("string")) {
                valores.append('\'');
            }
        }
        valores.append(" WHERE ");
        valores.append(teste.getPrimaryKey());
        valores.append(" = ");
        valores.append(tTabela1.getValueAt(rowSelecionada, tabela1PKColumnIndex));
        

        valores.append(';');
        System.out.println(valores.toString());
        try {
            ClienteBan2.getInstance().conexao.RequestChange(valores.toString());
            JOptionPane.showMessageDialog(this, "Valor alterado com sucsso!", "Alteração executada", JOptionPane.INFORMATION_MESSAGE);
            this.setupTabela();
            tTabela1.setRowSelectionInterval(rowSelecionada, rowSelecionada);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problema ao atualizar campos valores!", "Alteração falhou", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bAtualizar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAtualizar1;
    private javax.swing.JButton bCriar1;
    private javax.swing.JButton bDeletar1;
    private javax.swing.JButton bReload1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pDados;
    private javax.swing.JScrollPane pScrollPane1;
    private javax.swing.JTable tTabela1;
    // End of variables declaration//GEN-END:variables
}
