/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modele.metier.Ville;
import modele.dao.DaoVille;
import modele.dao.Jdbc;

/**
 *
 * @author btssio
 */
public class VueAjout extends javax.swing.JFrame {

//    public javax.swing.JFrame VueAdresse_init;
//    private VueAdresse_init Vueadresse_init;
    private VueAdresse_init vueAdresse_init;

//    public VueAjout(VueAdresse_init VueAdresse_init){
//        this.Vueadresse_init = VueAdresse_init;
//    }
    /**
     * Creates new form VueRecherche
     */
    public VueAjout(VueAdresse_init vueAdresse_init) {
        this.vueAdresse_init = vueAdresse_init;
        initComponents();
        try {
            connexion();
        } catch (SQLException ex) {
            System.out.println("probleme SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Probleme classe non trouvé");
        }

        jButtonRechercher.setEnabled(false);
        jButtonValider.setEnabled(false);

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
        jTextFieldCdp = new javax.swing.JTextField();
        jComboBoxVille = new javax.swing.JComboBox();
        jButtonValider = new javax.swing.JButton();
        jButtonQuitter = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jButtonRechercher = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selection ville");

        jLabel1.setText("Code postal");

        jLabel2.setText("Ville");

        jTextFieldCdp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCdpKeyReleased(evt);
            }
        });

        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jButtonQuitter.setText("Quitter");
        jButtonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitterActionPerformed(evt);
            }
        });

        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerActionPerformed(evt);
            }
        });

        jButtonRechercher.setText("Rechercher");
        jButtonRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRechercherActionPerformed(evt);
            }
        });

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxVille, 0, 110, Short.MAX_VALUE)
                            .addComponent(jTextFieldCdp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonRechercher)
                            .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonValider)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAnnuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addComponent(jButtonQuitter)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRechercher))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonValider)
                    .addComponent(jButtonQuitter)
                    .addComponent(jButtonAnnuler))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        valider();
    }//GEN-LAST:event_jButtonValiderActionPerformed

    private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
//        jTextFieldCdp.setText("");
        this.setVisible(false);
    }//GEN-LAST:event_jButtonAnnulerActionPerformed

    private void jButtonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonQuitterActionPerformed

    private void jButtonRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRechercherActionPerformed
        rechercher();
    }//GEN-LAST:event_jButtonRechercherActionPerformed

    private void jTextFieldCdpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCdpKeyReleased
        String value = jTextFieldCdp.getText();
        int l = value.length();
        if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')
                || (evt.getKeyCode() == KeyEvent.VK_ENTER)
                || (evt.getKeyCode() == KeyEvent.VK_DELETE)
                || (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                || (evt.getKeyCode() == KeyEvent.VK_LEFT)
                || (evt.getKeyCode() == KeyEvent.VK_RIGHT)
                || (evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            jButtonRechercher.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Les lettres ne sont pas acceptés");
            jTextFieldCdp.setText("");
            jComboBoxVille.removeAllItems();
            jButtonRechercher.setEnabled(false);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTextFieldCdp.setText("");
            jButtonRechercher.setEnabled(false);
            jComboBoxVille.removeAllItems();
        }
        if (l > 5) {
            JOptionPane.showMessageDialog(null, "Un code postal est composé de 5 chiffres");
            jTextFieldCdp.setText("");
            jComboBoxVille.removeAllItems();
            jButtonRechercher.setEnabled(false);
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            rechercher();
        }


    }//GEN-LAST:event_jTextFieldCdpKeyReleased

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        jTextFieldCdp.setText("");
        jComboBoxVille.removeAllItems();

    }//GEN-LAST:event_jButtonResetActionPerformed

    private void valider() {
        this.setVisible(false);
        String ville = jComboBoxVille.getSelectedItem().toString();
        vueAdresse_init.valueChange(ville);

    }

    private static void connexion() throws ClassNotFoundException, SQLException {
//        Jdbc.creer("com.mysql.jdbc.Driver", "jdbc:mysql://", "localhost/", "equipe6", "root", "joliverie");
        Jdbc.creer("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:", "@localhost:1521:XE", "", "btssio", "btssio");
        Jdbc.getInstance().connecter();
        Connection cnx = Jdbc.getInstance().getConnexion();
    }

    private void rechercher() {
        jComboBoxVille.removeAllItems();
        List<Ville> listeVilles = new ArrayList();
        String cdp = jTextFieldCdp.getText();
        if (!cdp.equals("")) {
//            System.out.println("coucou");
//            if (Double.isNaN(Integer.valueOf(jTextFieldCdp.getText()))) {
            try {
                listeVilles = DaoVille.selectOne2(cdp);
                if (listeVilles.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Il n'y a pas de ville pour ce code postale");
                    jTextFieldCdp.requestFocus();
                } else {
                    for (int i = 0; i < listeVilles.size(); i++) {
                        jComboBoxVille.addItem(listeVilles.get(i).getVille());
                    }
                }
                jButtonValider.setEnabled(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Code postal invalide");               
                this.setVisible(false);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(VueAjout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(VueAjout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(VueAjout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(VueAjout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new VueAjout().setVisible(true);
//            }
//        });
//    }
    private void afficherVille(Ville uneVille) {
        this.jTextFieldCdp.setText(uneVille.getCdp());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonQuitter;
    private javax.swing.JButton jButtonRechercher;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JComboBox jComboBoxVille;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextFieldCdp;
    // End of variables declaration//GEN-END:variables
}