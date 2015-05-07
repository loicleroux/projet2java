/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modele.dao.DaoAdresse;
import modele.dao.DaoVille;
import modele.dao.Jdbc;
import modele.metier.Adresse;
import modele.metier.Ville;

/**
 *
 * @author btssio
 */
public class VueAdresse_init extends javax.swing.JFrame {

    private int etat; // 1 affichage, 2 ajout, 3 suppression, 4 modification, 
    // 5 recherche, possibilité d'utiliser enum
    private List<Adresse> lesAdresses;  // la liste des adresses
    private int indiceAdresseCourante;  // l'indice de l'adresse courante dans la liste
    private Adresse adresseCourante;    // l'adresse courante
    public javax.swing.JFrame VueAjout;  //initialisation de la jFrame VueAjout
    private String cdp;                 //codes postales d'une ville
    private String[] CodePostale;   //liste des codes postale

    /**
     * Creates new form VueAdresse
     */
    public VueAdresse_init() {
        initComponents();
        Jdbc.creer("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:", "@localhost:1521:XE", "", "btssio", "btssio");

        modeAffichage();
        try {
            Jdbc.getInstance().connecter();
            adresseCourante = DaoAdresse.selectFirst();
            afficherAdresse(adresseCourante);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Pilote absent");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Echec de Connexion");
        }
        try {
            lesAdresses = DaoAdresse.selectAll(" ORDER BY ID ", "ASC");
        } catch (SQLException ex) {
            Logger.getLogger(VueAdresse_init.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * permet de sélectionner les valeurs de VueAjout
     *
     * @param value ( <-- nom à changer )
     */
    public void valueChange(String ville) {
        afficherVille(ville);
    }

    public void afficherVille(String ville) {
        Ville uneVille = null;
        try {
            uneVille = DaoVille.selectOne(ville);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "selectOne ne marche pas");
        }

        cdp = uneVille.getCdp();
        CodePostale = cdp.split("-");

        this.jTextFieldVille.setText(uneVille.getVille());
        String cdp = uneVille.getCdp();
        if (cdp.length() > 5) {
            String VilleSplit[] = cdp.split("-");

            JFrame frame = new JFrame("Ville");

            String favoriteVille = (String) JOptionPane.showInputDialog(frame,
                    "Choisir un code postal de la ville?",
                    "Ville",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    VilleSplit,
                    VilleSplit[1]);
            cdp = favoriteVille;
            this.jTextFieldCdp.setText(cdp);
        } else {
            this.jTextFieldCdp.setText(uneVille.getCdp());
        }
    }

    /**
     * rend editable les elements correspondant au bouton afficher la variable
     * etat est mise a 1
     */
    private void modeAffichage() {
        etat = 1;
        jTextFieldId.setEnabled(false);
        jTextFieldRue.setEnabled(false);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(true);
        jButtonModifier.setEnabled(true);
        jButtonSupprimer.setEnabled(true);
        jButtonRechercher.setEnabled(true);
        jButtonAnnuler.setEnabled(false);
        jButtonValider.setEnabled(false);

        jButtonSelectionner.setVisible(false);
        jButtonSelectionner.setEnabled(false);

        jLabel7.setVisible(true);
        jComboBoxTri.setVisible(true);
        jCheckBoxCroissant.setVisible(true);
        jButtonSuivant.setVisible(true);
        jButtonPrecedent.setVisible(true);
    }

    /**
     * rend editable les elements correspondant au bouton ajouter la variable
     * etat est mise a 2
     */
    private void modeAjout() {
        etat = 2;
        jTextFieldId.setEnabled(false);
        jTextFieldRue.setEnabled(true);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(true);
        jButtonValider.setEnabled(true);


        jButtonSelectionner.setVisible(true);
        jButtonSelectionner.setEnabled(true);

        id();

        jLabel7.setVisible(false);
        jComboBoxTri.setVisible(false);
        jCheckBoxCroissant.setVisible(false);
        jButtonSuivant.setVisible(false);
        jButtonPrecedent.setVisible(false);
    }

    private void id() {
        try {
            int nb = DaoAdresse.nbDonnees();
            nb = nb + 1;
            String id = String.valueOf(nb);
            this.jTextFieldId.setText(id.toString());
            this.jTextFieldRue.setText("");
            this.jTextFieldCdp.setText("");
            this.jTextFieldVille.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "ECHEC de connexion");
        }
    }

    /**
     * rend editable les elements correspondant au bouton supprimer la variable
     * etat est mise a 3
     */
    private void modeSuppression() {
        etat = 3;
        jTextFieldId.setEnabled(false);
        jTextFieldRue.setEnabled(false);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(true);
        jButtonValider.setEnabled(true);

        jButtonSelectionner.setVisible(false);
        jButtonSelectionner.setEnabled(false);

        jLabel7.setVisible(false);
        jComboBoxTri.setVisible(false);
        jCheckBoxCroissant.setVisible(false);
        jButtonSuivant.setVisible(false);
        jButtonPrecedent.setVisible(false);
    }

    /**
     * rend editable les elements correspondant au bouton modifier la variable
     * etat est mise a 4
     */
    private void modeModification() {
        etat = 4;
        jTextFieldId.setEnabled(false);
        jTextFieldRue.setEnabled(true);
        jTextFieldCdp.setEnabled(true);
        jTextFieldVille.setEnabled(true);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(true);
        jButtonValider.setEnabled(true);

        jButtonSelectionner.setVisible(false);
        jButtonSelectionner.setEnabled(false);

        jLabel7.setVisible(false);
        jComboBoxTri.setVisible(false);
        jCheckBoxCroissant.setVisible(false);
        jButtonSuivant.setVisible(false);
        jButtonPrecedent.setVisible(false);
    }

    /**
     * rend editable les elements correspondant a la recherche d'une adresse a
     * partir de l'ID la variable etat est mise a 5
     */
    private void modeRecherche() {
        etat = 5;
        jTextFieldId.setEnabled(true);
        jTextFieldRue.setEnabled(false);
        jTextFieldCdp.setEnabled(false);
        jTextFieldVille.setEnabled(false);
        jButtonAjouter.setEnabled(false);
        jButtonModifier.setEnabled(false);
        jButtonSupprimer.setEnabled(false);
        jButtonRechercher.setEnabled(false);
        jButtonAnnuler.setEnabled(true);
        jButtonValider.setEnabled(true);

        jButtonSelectionner.setVisible(false);
        jButtonSelectionner.setEnabled(false);

        jLabel7.setVisible(false);
        jComboBoxTri.setVisible(false);
        jCheckBoxCroissant.setVisible(false);
        jButtonSuivant.setVisible(false);
        jButtonPrecedent.setVisible(false);
    }

    /**
     * Donne une valeur aux JTextField à partir de la JDBC
     *
     */
    private void afficherAdresse(Adresse uneAdresse) {
        this.jTextFieldId.setText(Integer.toString(uneAdresse.getId()));
        this.jTextFieldRue.setText(uneAdresse.getRue());
        this.jTextFieldCdp.setText(uneAdresse.getCp());
        this.jTextFieldVille.setText(uneAdresse.getVille());
    }

    /**
     * Efface ce que contient les JTextField
     *
     */
    private void effacerAdresse() {
        this.jTextFieldId.setText("");
        this.jTextFieldRue.setText("");
        this.jTextFieldCdp.setText("");
        this.jTextFieldVille.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRue = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCdp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldVille = new javax.swing.JTextField();
        jButtonAjouter = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jButtonRechercher = new javax.swing.JButton();
        jButtonModifier = new javax.swing.JButton();
        jButtonValider = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jButtonQuitter = new javax.swing.JButton();
        jButtonSuivant = new javax.swing.JButton();
        jButtonPrecedent = new javax.swing.JButton();
        jComboBoxTri = new javax.swing.JComboBox();
        jCheckBoxCroissant = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jButtonSelectionner = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Adresse");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Id : ");
        jLabel2.setToolTipText("");

        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });
        jTextFieldId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldIdKeyPressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Rue : ");
        jLabel3.setToolTipText("");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cdp :");
        jLabel4.setToolTipText("");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Ville : ");
        jLabel5.setToolTipText("");

        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterActionPerformed(evt);
            }
        });
        jButtonAjouter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonAjouterKeyPressed(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        jButtonRechercher.setText("Rechercher");
        jButtonRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRechercherActionPerformed(evt);
            }
        });

        jButtonModifier.setText("Modifier");
        jButtonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierActionPerformed(evt);
            }
        });

        jButtonValider.setText("Valider");
        jButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValiderActionPerformed(evt);
            }
        });

        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerActionPerformed(evt);
            }
        });

        jButtonQuitter.setText("Quitter");
        jButtonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitterActionPerformed(evt);
            }
        });
        jButtonQuitter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonQuitterKeyPressed(evt);
            }
        });

        jButtonSuivant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vue/images/fleche_droite.png"))); // NOI18N
        jButtonSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuivantActionPerformed(evt);
            }
        });
        jButtonSuivant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonSuivantKeyPressed(evt);
            }
        });

        jButtonPrecedent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vue/images/fleche_gauche.png"))); // NOI18N
        jButtonPrecedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrecedentActionPerformed(evt);
            }
        });
        jButtonPrecedent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonPrecedentKeyPressed(evt);
            }
        });

        jComboBoxTri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Identifiant", "Par Ajout", "Nom" }));
        jComboBoxTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTriActionPerformed(evt);
            }
        });
        jComboBoxTri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxTriKeyPressed(evt);
            }
        });

        jCheckBoxCroissant.setText("Décroissant");
        jCheckBoxCroissant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCroissantActionPerformed(evt);
            }
        });
        jCheckBoxCroissant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCheckBoxCroissantKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tri");

        jButtonSelectionner.setText("Selectionner");
        jButtonSelectionner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectionnerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButtonPrecedent)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAjouter)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSupprimer)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonModifier)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonRechercher))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jButtonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonValider, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonQuitter)))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSuivant))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3))
                                .addComponent(jLabel4))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldVille)
                                .addComponent(jTextFieldRue, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                .addComponent(jTextFieldCdp, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxCroissant)
                            .addComponent(jButtonSelectionner)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTri, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldRue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBoxCroissant))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldCdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSelectionner))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPrecedent, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAjouter)
                            .addComponent(jButtonSupprimer)
                            .addComponent(jButtonRechercher)
                            .addComponent(jButtonModifier))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonValider)
                            .addComponent(jButtonAnnuler)
                            .addComponent(jButtonQuitter)))
                    .addComponent(jButtonSuivant, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * permet au bouton de faire l'action de recherche
     *
     * @param evt
     */
    private void jButtonRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRechercherActionPerformed
        modeRecherche();
        jTextFieldId.requestFocus();
        jTextFieldId.selectAll();
        
        effacerAdresse();

    }//GEN-LAST:event_jButtonRechercherActionPerformed
    /**
     * permet au bouton de faire l'action de modification
     *
     * @param evt
     */
    private void jButtonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierActionPerformed
        modeModification();
        jTextFieldRue.requestFocus();

    }//GEN-LAST:event_jButtonModifierActionPerformed
    /**
     * permet au bouton de faire l'action d'ajout
     *
     * @param evt
     */
    private void jButtonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterActionPerformed
        modeAjout();
        jTextFieldId.requestFocus();
    }//GEN-LAST:event_jButtonAjouterActionPerformed
    /**
     * permet au bouton de faire l'action de suppression
     *
     * @param evt
     */
    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        modeSuppression();
    }//GEN-LAST:event_jButtonSupprimerActionPerformed
    /**
     * permet au bouton de faire l'action valider et de mettre à jour la
     * collection
     *
     * @param evt
     */
    private void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValiderActionPerformed
        valider();
//        maj();
    }//GEN-LAST:event_jButtonValiderActionPerformed
    /**
     * permet au bouton de faire l'action annuler
     *
     * @param evt
     */
    private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
        annuler();
    }//GEN-LAST:event_jButtonAnnulerActionPerformed

    private void jButtonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonQuitterActionPerformed

    private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed
    }//GEN-LAST:event_jTextFieldIdActionPerformed

    private void jTextFieldIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            valider();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            annuler();
        }
    }//GEN-LAST:event_jTextFieldIdKeyPressed
    /**
     * permet au bouton de faire l'action suivant
     *
     * @param evt
     */
    private void jButtonSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuivantActionPerformed
        try {
            suivant();
        } catch (SQLException ex) {
            Logger.getLogger(VueAdresse_init.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSuivantActionPerformed
    /**
     * permet au bouton de faire l'action precedent
     *
     * @param evt
     */
    private void jButtonPrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrecedentActionPerformed
        try {
            precedent();
        } catch (SQLException ex) {
            Logger.getLogger(VueAdresse_init.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPrecedentActionPerformed
    /**
     * Sélection du tri d'affichage
     *
     * @param evt
     */
    private void jComboBoxTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTriActionPerformed
        maj();
    }//GEN-LAST:event_jComboBoxTriActionPerformed
    /**
     * Choix de l'ordre (Croissant/ Décroissant)
     *
     * @param evt
     */
    private void jCheckBoxCroissantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCroissantActionPerformed
        maj();
    }//GEN-LAST:event_jCheckBoxCroissantActionPerformed

    private void jButtonSelectionnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectionnerActionPerformed
        afficheVueAjout();
    }//GEN-LAST:event_jButtonSelectionnerActionPerformed

    private void jButtonAjouterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonAjouterKeyPressed
        leftRight(evt);
    }//GEN-LAST:event_jButtonAjouterKeyPressed

    private void jButtonQuitterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonQuitterKeyPressed
        leftRight(evt);
    }//GEN-LAST:event_jButtonQuitterKeyPressed

    private void jButtonPrecedentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonPrecedentKeyPressed
        leftRight(evt);
    }//GEN-LAST:event_jButtonPrecedentKeyPressed

    private void jButtonSuivantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSuivantKeyPressed
        leftRight(evt);
    }//GEN-LAST:event_jButtonSuivantKeyPressed

    private void jComboBoxTriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxTriKeyPressed
        leftRight(evt);
    }//GEN-LAST:event_jComboBoxTriKeyPressed

    private void jCheckBoxCroissantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBoxCroissantKeyPressed
        leftRight(evt);
    }//GEN-LAST:event_jCheckBoxCroissantKeyPressed

    private void leftRight(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                suivant();
            } catch (SQLException ex) {
                Logger.getLogger(VueAdresse_init.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            try {
                precedent();
            } catch (SQLException ex) {
                Logger.getLogger(VueAdresse_init.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void afficheVueAjout() {
        VueAjout = new VueAjout(this);
        VueAjout.setVisible(true);

    }

    /**
     * Méthode suivant
     *
     * @throws SQLException
     */
    private void suivant() throws SQLException {
        int idAdresse = Integer.valueOf(jTextFieldId.getText());
        adresseCourante = DaoAdresse.selectOne(idAdresse);
        /**
         * Boucle pour parcourir la liste
         */
        for (int i = 0; i < lesAdresses.size(); i++) {
            /**
             * Condition pour trouver l'adresse courante dans la liste
             */
            if (lesAdresses.get(i).equals(adresseCourante)) {
                /**
                 * Condition pour aller à l'adresse suivante Si condition
                 * remplie retour à l'id de début sinon aller à l'adresse avec
                 * l'id supérieur
                 */
                if (i >= lesAdresses.size() - 1) {
                    indiceAdresseCourante = 0;
                } else {
                    indiceAdresseCourante = i + 1;
                }
            }
            /**
             * Affichage de l'adresse suivante
             */
            afficherAdresse(lesAdresses.get(indiceAdresseCourante));

        }
    }

    /**
     * Méthode précédent
     *
     * @throws SQLException
     */
    private void precedent() throws SQLException {
        int idMax;
        int idAdresse = Integer.valueOf(jTextFieldId.getText());
        adresseCourante = DaoAdresse.selectOne(idAdresse);
        idMax = lesAdresses.size() - 1;
        /**
         * Boucle pour parcourir la liste
         */
        for (int i = 0; i < lesAdresses.size(); i++) {
            /**
             * Condition pour trouver l'adresse courante dans la liste
             */
            if (lesAdresses.get(i).equals(adresseCourante)) {
                /**
                 * Condition pour aller à l'adresse précédente Si condition
                 * remplie retour à l'id de fin sinon aller à l'adresse avec
                 * l'id inférieur
                 */
                if (i <= 0) {
                    indiceAdresseCourante = idMax;
                } else {
                    indiceAdresseCourante = i - 1;
                }

            }
        }
        /**
         * Affichage de l'adresse précédente
         */
        afficherAdresse(lesAdresses.get(indiceAdresseCourante));

    }

    /**
     * Permet de mettre à jour la collection lesAdresses lors de la validation
     *
     */
    private void maj() {
        if (jComboBoxTri.getSelectedIndex() == 1) {
            jCheckBoxCroissant.setEnabled(false);
        } else {
            jCheckBoxCroissant.setEnabled(true);
        }
        try {
            String tri = "";
            String ordre = "";
            if (jComboBoxTri.getSelectedIndex() == 0) {
                tri = " ORDER BY ID ";
                if (jCheckBoxCroissant.isSelected()) {
                    ordre = "DESC";
                }
            }
            if (jComboBoxTri.getSelectedIndex() == 1) {
                tri = "";
            }
            if (jComboBoxTri.getSelectedIndex() == 2) {
                tri = " ORDER BY VILLE ";
                if (jCheckBoxCroissant.isSelected()) {
                    ordre = "DESC";
                }
            }
            lesAdresses = DaoAdresse.selectAll(tri, ordre);
        } catch (SQLException ex) {
            Logger.getLogger(VueAdresse_init.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void valider() {
        switch (etat) {
            case 1:
                break;
            case 2:
                ajout();
                break;
            case 3:
                suppression();
                break;
            case 4:
                modification();
                break;
            case 5:
                recherche();
//                recherche2();
                break;
            default:
        }
    }

    private void recherche2() {
        try {
            Adresse uneAdresse = DaoAdresse.selectOne(jTextFieldVille.getText());
            
            jTextFieldId.setText(String.valueOf(uneAdresse.getId()));
            jTextFieldCdp.setText(uneAdresse.getCp());
            jTextFieldRue.setText(uneAdresse.getRue());
            jTextFieldVille.setText(uneAdresse.getVille());
        } catch (SQLException ex) {
            System.out.println("erreur requete");
        }
    }
    private void annuler() {
        switch (etat) {
            case 1:
                break;
            case 2:     // annulation ajout
                try {
                    Jdbc.getInstance().connecter();
                    adresseCourante = DaoAdresse.selectFirst();
                    afficherAdresse(adresseCourante);
                    modeAffichage();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Pilote absent");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Echec de connexion");
                }

                break;
            case 3:     // annulation suppression
                try {
                    Jdbc.getInstance().connecter();
                    adresseCourante = DaoAdresse.selectFirst();
                    afficherAdresse(adresseCourante);
                    modeAffichage();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Pilote absent");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Echec de connexion");
                }
                modeAffichage();
                break;
            case 4:     // annulation modification
                afficherAdresse(adresseCourante);
                modeAffichage();
                break;
            case 5:     // annulation recherche
                try {
                    Jdbc.getInstance().connecter();
                    adresseCourante = DaoAdresse.selectFirst();
                    afficherAdresse(adresseCourante);
                    modeAffichage();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Pilote absent");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Echec de connexion");
                }
                modeAffichage();
                break;
            default:
        }
    }

    /**
     * permet de faire la recherche dans la base de donnée
     */
    private void recherche() {
        Adresse cetteAdresse = null;
        if (!jTextFieldId.getText().equals("")) {
            int idAdresse = Integer.valueOf(jTextFieldId.getText());
            try {
                Jdbc.getInstance().connecter();
                cetteAdresse = DaoAdresse.selectOne(idAdresse);
                if (cetteAdresse != null) {
                    adresseCourante = cetteAdresse;
                    afficherAdresse(adresseCourante);
                    modeAffichage();
                } else {
                    JOptionPane.showMessageDialog(this, "Identifiant inconnu");
                    jTextFieldId.selectAll();
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Pilote absent");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Echec de connexion");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Le champ Id est vide");
            jTextFieldId.requestFocus();
        }

    }

    /**
     * permet de modifier les données
     */
    private void modification() {
        Adresse cetteAdresse = null;
        if (!jTextFieldId.getText().equals("")) {
            int idAdresse = Integer.valueOf(jTextFieldId.getText());
            try {
                Jdbc.getInstance().connecter();
                cetteAdresse = DaoAdresse.selectOne(idAdresse);
                if (cetteAdresse != null) {
                    cetteAdresse.setRue(this.jTextFieldRue.getText());
                    cetteAdresse.setCp(this.jTextFieldCdp.getText());
                    cetteAdresse.setVille(this.jTextFieldVille.getText());
                    DaoAdresse.update(idAdresse, cetteAdresse);
                    JOptionPane.showMessageDialog(this, "Modification effectuée");
                    adresseCourante = cetteAdresse;
                } else {
                    JOptionPane.showMessageDialog(this, "Identifiant inconnu");
                    jTextFieldId.selectAll();
                }
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Pilote absent");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Echec de connexion");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Le champ Id est vide");
            jTextFieldId.requestFocus();
        }
        modeAffichage();
    }

    /**
     * méthode permettant d'ajouter des données à la BDD
     */
    private void ajout() {
        boolean erreur = true;
        if (!jTextFieldId.getText().equals("")) {
            int idAdresse = Integer.valueOf(jTextFieldId.getText());
            Adresse cetteAdresse;
            try {
                cetteAdresse = DaoAdresse.selectOne(idAdresse);
                if (cetteAdresse == null) {
                    cetteAdresse = new Adresse(idAdresse, jTextFieldRue.getText(), jTextFieldCdp.getText(), jTextFieldVille.getText());
                    DaoAdresse.insert(idAdresse, cetteAdresse);
                    adresseCourante = cetteAdresse;
                    afficherAdresse(adresseCourante);
                    JOptionPane.showMessageDialog(this, "Ajout effectué");
                    erreur = false;
                } else {
                    JOptionPane.showMessageDialog(this, "L'identifiant existe déjà");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Echec d'ajout SQL");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Le champ Id est vide");
        }
        if (erreur) {
            try {
                Jdbc.getInstance().connecter();
                adresseCourante = DaoAdresse.selectFirst();
                afficherAdresse(adresseCourante);
                modeAffichage();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Pilote absent");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Echec de connexion");
            }
        }
		new VueAdresse_init().setVisible(true);
        modeAffichage();
    }

    /**
     * méthode supprimant des données de la BDD
     */
    private void suppression() {
        int idAdresse = Integer.valueOf(jTextFieldId.getText());
        if (!jTextFieldId.getText().equals("")) {
            Adresse cetteAdresse;
            try {
                cetteAdresse = DaoAdresse.selectOne(idAdresse);
                if (cetteAdresse != null) {
                    int rep = JOptionPane.showConfirmDialog(this, "Etes-vous sûr(e) ?", "Suppression", JOptionPane.YES_NO_OPTION);
                    if (rep == JOptionPane.YES_OPTION) {
                        DaoAdresse.delete(idAdresse);
                        effacerAdresse();
                        JOptionPane.showMessageDialog(this, "Suppression effectuée");
                        adresseCourante = DaoAdresse.selectFirst();
                        afficherAdresse(adresseCourante);
                    }
                } else {
                    effacerAdresse();
                    JOptionPane.showMessageDialog(this, "Identifiant inconnu");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Echec de suppression SQL");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Le champ Id est vide");
        }
        modeAffichage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueAdresse_init.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VueAdresse_init().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonPrecedent;
    private javax.swing.JButton jButtonQuitter;
    private javax.swing.JButton jButtonRechercher;
    private javax.swing.JButton jButtonSelectionner;
    private javax.swing.JButton jButtonSuivant;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JButton jButtonValider;
    private javax.swing.JCheckBox jCheckBoxCroissant;
    private javax.swing.JComboBox jComboBoxTri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextFieldCdp;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldRue;
    private javax.swing.JTextField jTextFieldVille;
    // End of variables declaration//GEN-END:variables
}