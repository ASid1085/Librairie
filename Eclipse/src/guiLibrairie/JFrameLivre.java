
package guiLibrairie;

import daoLibrairie.LivreDAO;
import entitiesLibrairie.Auteur;
import entitiesLibrairie.Editeur;
import entitiesLibrairie.Livre;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class JFrameLivre extends javax.swing.JFrame {

    private LivreDAO ldao= new LivreDAO();
    Vector<Vector> livreLignes= ldao.findAll();
    Vector tableVector = ldao.findAll();
    public JFrameLivre() {
        initComponents();
        
        // affichage à l'ouverture de la frame (1er element de la jtable) 
        jTableLivre.setModel(iniDefaultTableModel());
        jTableLivre.getSelectionModel().setSelectionInterval(0, 0);
        jTextResume.setText((String) livreLignes.get(0).get(11));
       // Auteur auteur =  (Auteur) livreLignes.get(0).get(12);
        //jTextAuteur.setText(auteur.getNom()+" "+ auteur.getPrenom());
       // Editeur editeur = (Editeur)livreLignes.get(0). get(13);
       // jTextEditeur.setText(editeur.getNom());   
        float prixHT = (float) livreLignes.get(jTableLivre.getSelectedRow()).get(3);
        
		String valeur = livreLignes.get(jTableLivre.getSelectedRow()).get(4).toString();
		float tauxTVA= Float.parseFloat(valeur);
        float prixTTC= prixHT + prixHT*(tauxTVA/100);
        jTextPrixTTC.setText(String.valueOf(prixTTC));
        
    }
    private DefaultTableModel iniDefaultTableModel() {
        
        Vector titresColonnes= new Vector();// vecteur pour creation des titres des colonnes
        titresColonnes.add("ISBN");
        titresColonnes.add("TITRE");
        titresColonnes.add("SOUS TITRE");
        titresColonnes.add("PRIX HT"); 
        titresColonnes.add("TVA");
        titresColonnes.add("DATE EDITION");
        titresColonnes.add("IMAGE");
        titresColonnes.add("NBR PAGES");
        titresColonnes.add("STOCK");
        titresColonnes.add("COMMENT");
        titresColonnes.add("STATUT");
        return new DefaultTableModel(tableVector, titresColonnes); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jTextRecherche = new javax.swing.JTextField();
        jComboBoxLivre = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLivre = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextResume = new javax.swing.JTextArea();
        jTextAuteur = new javax.swing.JTextField();
        jTextEditeur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonModifierLivre = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();
        jButtonAjouterLivre = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextPrixTTC = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextRechercheActionPerformed(evt);
            }
        });

        jComboBoxLivre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TITRE", "ISBN", "AUTEUR", "EDITEUR", "THEME", "GENRE", "MOT CLE", "STOCK", "PRIX HT", "PRIX TTC" }));
        jComboBoxLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLivreActionPerformed(evt);
            }
        });

        jTableLivre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
        jTableLivre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLivreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLivre);

        jTextResume.setColumns(20);
        jTextResume.setRows(5);
        jScrollPane2.setViewportView(jTextResume);

        jTextAuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextAuteurActionPerformed(evt);
            }
        });

        jLabel1.setText("AUTEUR");

        jLabel2.setText("EDITEUR");

        jButtonModifierLivre.setText("Modifier");
        jButtonModifierLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierLivreActionPerformed(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        jButtonAnnuler.setText("Annuler");
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnulerActionPerformed(evt);
            }
        });

        jButtonAjouterLivre.setText("Ajouter");
        jButtonAjouterLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterLivreActionPerformed(evt);
            }
        });

        jLabel3.setText("PRIX TTC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jComboBoxLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                                    .addComponent(jTextRecherche))
                                .addGap(30, 30, 30)
                                .addComponent(jButtonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextEditeur, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextPrixTTC, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jButtonAjouterLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210)
                .addComponent(jButtonModifierLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAnnuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextEditeur, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextPrixTTC, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAjouterLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModifierLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 1215, 618);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableLivreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLivreMouseClicked
       
        jTextResume.setText((String) livreLignes.get(jTableLivre.getSelectedRow()).get(11));
        Auteur auteur =  (Auteur) livreLignes.get(jTableLivre.getSelectedRow()).get(12);
        jTextAuteur.setText(auteur.getNom()+" "+ auteur.getPrenom());
        Editeur editeur = (Editeur)livreLignes.get(jTableLivre.getSelectedRow()). get(13);
        jTextEditeur.setText(editeur.getNom());
        float prixHT = (float) livreLignes.get(jTableLivre.getSelectedRow()).get(3);
        float tauxTVA= (float) livreLignes.get(jTableLivre.getSelectedRow()).get(4);
        float prixTTC= prixHT + prixHT*(tauxTVA/100);
        jTextPrixTTC.setText(String.valueOf(prixTTC));
        
    }//GEN-LAST:event_jTableLivreMouseClicked

    private void jTextAuteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextAuteurActionPerformed
       
    }//GEN-LAST:event_jTextAuteurActionPerformed

    private void jTextRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextRechercheActionPerformed
      String rechercheValue= jTextRecherche.getText();
      String rechercheChamps= (String) jComboBoxLivre.getSelectedItem();
     
     if (rechercheChamps.equals("PRIX TTC")){
         System.out.println(livreLignes.get(jTableLivre.getSelectedRow()).get(4));
         float prixHT= Float.valueOf(rechercheValue)/ (1 +(float) livreLignes.get(jTableLivre.getSelectedRow()).get(4)/100);
         rechercheValue =String.valueOf(prixHT);
         rechercheChamps= "PRIX HT";
     } 
    
     livreLignes = ldao.findByParameter(rechercheChamps, rechercheValue);
     tableVector = ldao.findByParameter(rechercheChamps, rechercheValue);
      jTableLivre.setModel(iniDefaultTableModel());
        jTableLivre.getSelectionModel().setSelectionInterval(0, 0);
        if(!livreLignes.isEmpty()){
            jTextResume.setText((String) livreLignes.get(0).get(11));
            Auteur auteur =  (Auteur) livreLignes.get(0).get(12); // c'est pas une instanciation mais juste une recuperation de mon objet auteur qui a été inctancié auparavant.
            jTextAuteur.setText(auteur.getNom()+" "+ auteur.getPrenom());
            Editeur editeur = (Editeur)livreLignes.get(0). get(13);
            jTextEditeur.setText(editeur.getNom());
        }
        else{
            JOptionPane.showMessageDialog(null, "La liste demandée est vide");
        }
        
        
    }//GEN-LAST:event_jTextRechercheActionPerformed

    private void jComboBoxLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLivreActionPerformed
        
    }//GEN-LAST:event_jComboBoxLivreActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnulerActionPerformed
        livreLignes = ldao.findAll();
        tableVector = ldao.findAll();
        jTableLivre.setModel(iniDefaultTableModel());
        jTableLivre.getSelectionModel().setSelectionInterval(0, 0);
        jTextResume.setText((String) livreLignes.get(0).get(11));
        Auteur auteur =  (Auteur) livreLignes.get(0).get(12);
        jTextAuteur.setText(auteur.getNom()+" "+ auteur.getPrenom());
        Editeur editeur = (Editeur)livreLignes.get(0). get(13);
        jTextEditeur.setText(editeur.getNom());        
      
    
    }//GEN-LAST:event_jButtonAnnulerActionPerformed

    private void jButtonModifierLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierLivreActionPerformed

        for (int ligne =0; ligne< jTableLivre.getRowCount(); ligne++){
        if (jTableLivre.isRowSelected(ligne)){
            Vector res= livreLignes.get(ligne);
            JFrameAjouter modif= new JFrameAjouter(res);
            modif.setVisible(true);
            this.dispose();

        }  
    }//GEN-LAST:event_jButtonModifierLivreActionPerformed
    }
    
    private void jButtonAjouterLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterLivreActionPerformed
JFrameAjouter ajouter= new  JFrameAjouter();  
ajouter.setVisible(true);
dispose();
    }//GEN-LAST:event_jButtonAjouterLivreActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameLivre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouterLivre;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonModifierLivre;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JComboBox<String> jComboBoxLivre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable jTableLivre;
    private javax.swing.JTextField jTextAuteur;
    private javax.swing.JTextField jTextEditeur;
    private javax.swing.JTextField jTextPrixTTC;
    private javax.swing.JTextField jTextRecherche;
    private javax.swing.JTextArea jTextResume;
    // End of variables declaration//GEN-END:variables
}
