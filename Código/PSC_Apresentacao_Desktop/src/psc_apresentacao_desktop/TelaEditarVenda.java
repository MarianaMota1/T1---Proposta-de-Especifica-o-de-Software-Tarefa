/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psc_apresentacao_desktop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import psc_aplicacao.Cliente;
import psc_aplicacao.ClienteRepositorio;
import psc_aplicacao.FormaPagamento;
import psc_aplicacao.MeioPagamento;
import psc_aplicacao.Produto;
import psc_aplicacao.ProdutoRepositorio;
import psc_aplicacao.Venda;
import psc_aplicacao.VendaItem;
import psc_aplicacao.VendaRepositorio;

/**
 *
 * @author Mary
 */
public class TelaEditarVenda extends javax.swing.JInternalFrame {

    Venda entidade;
    VendaRepositorio dao;
    TelaListarVenda listagem;

    ClienteRepositorio daoCliente = GerenciadorReferencias.getCliente();
    ProdutoRepositorio daoProduto = GerenciadorReferencias.getProduto();

    VendaItem itemSelecionado;

    Calendar calendar = GregorianCalendar.getInstance();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form TelaEditarCliente
     */
    public TelaEditarVenda() {
        initComponents();
        dao = GerenciadorReferencias.getVenda();
        daoCliente = GerenciadorReferencias.getCliente();
        daoProduto = GerenciadorReferencias.getProduto();

        ComboBoxModel clientes = new DefaultComboBoxModel(daoCliente.Buscar(null).toArray());
        cmbCliente.setModel(clientes);

        ComboBoxModel produtos = new DefaultComboBoxModel(daoProduto.Buscar(null).toArray());
        cmbProduto.setModel(produtos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        txtData = new javax.swing.JTextField();
        lblNome1 = new javax.swing.JLabel();
        lblNome2 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmbProduto = new javax.swing.JComboBox<>();
        lblNome3 = new javax.swing.JLabel();
        lblNome4 = new javax.swing.JLabel();
        txtQtd = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        cmbFormaPagamento = new javax.swing.JComboBox<>();
        lblNome5 = new javax.swing.JLabel();
        cmbMeioPagamento = new javax.swing.JComboBox<>();
        lblNome6 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Venda");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNome.setText("Cliente:");

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblNome1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNome1.setText("Data");

        lblNome2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNome2.setText("Total:");

        lblTotal.setText("jLabel1");

        cmbProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProdutoActionPerformed(evt);
            }
        });

        lblNome3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNome3.setText("Produto:");

        lblNome4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNome4.setText("Qtd.:");

        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNome3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNome4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdicionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jScrollPane1)
                    .addGap(18, 18, 18)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome3)
                    .addComponent(cmbProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome4)
                    .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnRemover))
                .addContainerGap(221, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );

        cmbFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Vista", "A Prazo" }));

        lblNome5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNome5.setText("Forma de Pagamento:");

        cmbMeioPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Boleto", "Cartão", "Cheque" }));

        lblNome6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNome6.setText("Meio de Pagamento:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblNome5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNome6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMeioPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNome1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome)
                            .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome1)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNome2)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome5)
                    .addComponent(cmbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome6)
                    .addComponent(cmbMeioPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja salvar as alterações?", "Confirmação", JOptionPane.YES_NO_OPTION) == 0) {
                preencheObjeto();
                if (dao.Salvar(entidade)) {
                    JOptionPane.showMessageDialog(rootPane, "Operação concluída com sucesso!");
                    btnApagar.setVisible(true);
                    preencheCampos();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro!");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Operação cancelada!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listagem.carregar();
        listagem.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Tem certeza que deseja apagar o Cliente?", "Confirmação", JOptionPane.YES_NO_OPTION) == 0) {
            if (dao.Apagar(entidade)) {
                JOptionPane.showMessageDialog(rootPane, "Operação concluída com sucesso!");
                entidade = new Venda(0, null, null, null);
                preencheCampoTabela();
                btnApagar.setVisible(false);
                preencheCampos();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro!");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Operação Cancelada!");
        }
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        Produto produto = (Produto) cmbProduto.getSelectedItem();
        int qtd = Integer.parseInt(txtQtd.getText());
        if (produto.getQtd() > qtd) {
            VendaItem item = new VendaItem(entidade, produto, qtd);
            entidade.addItem(item);
            preencheCampoTabela();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Quantidade inválida!");
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja realmente remover o item?", "Confirmação", JOptionPane.YES_NO_OPTION) == 0) {
            if (itemSelecionado != null) {
                entidade.removeItem(itemSelecionado);
                preencheCampoTabela();
                JOptionPane.showMessageDialog(rootPane, "Item removido com sucesso!");
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        int linha = tblProduto.getSelectedRow();
        itemSelecionado = entidade.getItens().get(linha);  //(VendaItem) tblItens.getModel().getValueAt(linha, 3);
        preencheCamposItem();
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void cmbProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbFormaPagamento;
    private javax.swing.JComboBox<String> cmbMeioPagamento;
    private javax.swing.JComboBox<String> cmbProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblNome3;
    private javax.swing.JLabel lblNome4;
    private javax.swing.JLabel lblNome5;
    private javax.swing.JLabel lblNome6;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtQtd;
    // End of variables declaration//GEN-END:variables

    public Venda getEntidade() {
        return entidade;
    }

    public void setEntidade(Venda entidade) {
        this.entidade = entidade;
        if (entidade.getCodigo() == 0) {
            btnApagar.setVisible(false);
        }
        preencheCampos();
    }

    private void preencheObjeto() throws ParseException {
        entidade.setCliente((Cliente) cmbCliente.getSelectedItem());
        java.sql.Date dataSql = new java.sql.Date(df.parse(txtData.getText()).getTime());
        entidade.setData(dataSql);
        entidade.setFormaPagamento(new FormaPagamento(0, (String) cmbFormaPagamento.getSelectedItem()));
        entidade.setMeioPagamento(new MeioPagamento(0, (String) cmbMeioPagamento.getSelectedItem()));
    }

    private void preencheCamposItem() {
        cmbProduto.setSelectedItem(itemSelecionado.getProduto());
        txtQtd.setText(Integer.toString(itemSelecionado.getQuantidade()));
    }

    private void preencheCampos() {
        cmbCliente.setSelectedItem(entidade.getCliente());
        txtData.setText(df.format(entidade.getData()));
        cmbFormaPagamento.setSelectedItem(entidade.getFormaPagamento()/*.getTipoPagamento()*/);
        cmbMeioPagamento.setSelectedItem(entidade.getMeioPagamento()/*.getMeioPagamento()*/);
        lblTotal.setText(entidade.getValorTotal().toString());
        preencheCampoTabela();
    }

    private void preencheCampoTabela() {
        DefaultTableModel itens = new DefaultTableModel();

        itens.addColumn("Produto");
        itens.addColumn("Preço");
        itens.addColumn("Qtd");

        for (VendaItem i : entidade.getItens()) {
            Vector valor = new Vector();
            valor.add(i.getProduto().getNome());
            valor.add(i.getProduto().getPrecoUnitario().toString());
            valor.add(i.getQuantidade());
            valor.add(i);
            itens.addRow(valor);
        }
        tblProduto.setModel(itens);
        lblTotal.setText(entidade.getValorTotal().toString());
    }

    public TelaListarVenda getListagem() {
        return listagem;
    }

    public void setListagem(TelaListarVenda listagem) {
        this.listagem = listagem;
    }

}
