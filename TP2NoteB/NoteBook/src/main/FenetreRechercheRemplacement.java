package main;

import FenetrePrincipale.JTextPaneCtrlF;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * FenetreRechercheRemplacement est une boîte de dialogue pour rechercher et remplacer du texte
 * dans un JTextPaneCtrlF.
 */
public class FenetreRechercheRemplacement extends JDialog {

    // Déclaration des boutons
    private JButton boutonSuivant;
    private JButton boutonPrecedent;
    private JButton boutonRemplacer;
    private JButton boutonRemplacerTout;
    private JButton boutonFermer;
    
    // Champs de texte pour la recherche et le remplacement
    private JTextField champRecherche;
    private JTextField champRemplacement;
    
    // Liste déroulante pour les recherches précédentes
    private JComboBox<String> recherchePrecedente;
    
    // Case à cocher pour la sensibilité à la casse
    private JCheckBox caseSensibilite;
    
    // Panneau principal de la boîte de dialogue
    private JPanel panneauRechercheRemplacement;

    /**
     * Constructeur de la classe FenetreRechercheRemplacement.
     *
     * @param zoneDeTexte la zone de texte sur laquelle effectuer les recherches et remplacements
     */
    public FenetreRechercheRemplacement(JTextPaneCtrlF zoneDeTexte) {
        
        // Appelle le constructeur de JDialog
        super(); 

        // Définit la taille de la boîte de dialogue
        setSize(400, 250); // Augmenté pour ajouter un bouton Fermer

        // Positionne la boîte de dialogue au centre de zoneDeTexte
        setLocationRelativeTo(zoneDeTexte); 

        // Crée et configure les boutons
        creerBoutons(zoneDeTexte); 

        // Crée et configure les champs de texte
        creerZoneTexte(); 

        // Crée et configure la liste déroulante
        creerListeDeroulante(); 

        // Crée et configure la case à cocher
        caseSensibilite = new JCheckBox("Sensible à la casse");
        
        // Crée et configure le panneau principal
        creerPanneau(); 

        // Ajoute les composants au panneau
        ajoutComposantsPanneau();

        add(panneauRechercheRemplacement); // Ajoute le panneau à la boîte de dialogue
    }

    /**
     * Ajoute les composants au panneau principal.
     */
    private void ajoutComposantsPanneau() {

        panneauRechercheRemplacement.add(new JLabel("Recherche :")); 
        panneauRechercheRemplacement.add(champRecherche); 
        panneauRechercheRemplacement.add(new JLabel("Remplacement :")); 
        panneauRechercheRemplacement.add(champRemplacement);
        panneauRechercheRemplacement.add(boutonSuivant); 
        panneauRechercheRemplacement.add(boutonPrecedent); 
        panneauRechercheRemplacement.add(boutonRemplacer); 
        panneauRechercheRemplacement.add(boutonRemplacerTout); 
        panneauRechercheRemplacement.add(new JLabel("Recherches précédentes :")); 
        panneauRechercheRemplacement.add(recherchePrecedente); 
        panneauRechercheRemplacement.add(caseSensibilite);
    }

    /**
     * Crée et configure le panneau principal.
     */
    private void creerPanneau() {
        // Crée un panneau avec une grille 5x2 
        //(ajout d'une ligne pour le bouton Fermer)
        panneauRechercheRemplacement = new JPanel(new GridLayout(7, 2)); 
    }

    /**
     * Crée et configure la liste déroulante pour 
     * les recherches précédentes.
     */
    private void creerListeDeroulante() {
        recherchePrecedente = new JComboBox<>(); 
    }

    /**
     * Crée et configure les champs de texte pour 
     * la recherche et le remplacement.
     */
    private void creerZoneTexte() {
        champRecherche = new JTextField(20); 
        champRemplacement = new JTextField(20); 
    }

    /**
     * Crée et configure les boutons.
     *
     * @param zoneDeTexte la zone de texte sur laquelle effectuer 
     * les recherches et remplacements
     */
    // Définition de la méthode `creerBoutons` 
    //qui prend un objet `JTextPaneCtrlF` comme paramètre.
private void creerBoutons(JTextPaneCtrlF zoneDeTexte) {

    // Création du bouton "Suivant".
    boutonSuivant = new JButton("Suivant");

    // Ajout d'un ActionListener pour le bouton "Suivant".
    boutonSuivant.addActionListener(new ActionListener() {

        // Redéfinition de la méthode `actionPerformed`, 
        //qui est appelée lorsque le bouton "Suivant" est cliqué.
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Recherche du texte suivant dans `zoneDeTexte` 
            //avec la sensibilité à la casse si sélectionnée.
            zoneDeTexte.rechercherSuivant(champRecherche.getText(), 
            caseSensibilite.isSelected());
        }
    });

    // Création du bouton "Précédent".
    boutonPrecedent = new JButton("Précédent");

    // Ajout d'un ActionListener pour le bouton "Précédent".
    boutonPrecedent.addActionListener(new ActionListener() {

        // Redéfinition de la méthode `actionPerformed`, 
        //qui est appelée lorsque le bouton "Précédent" est cliqué.
        @Override
        public void actionPerformed(ActionEvent e) {

            // Recherche du texte précédent dans `zoneDeTexte`
            // avec la sensibilité à la casse si sélectionnée.
            zoneDeTexte.rechercher(champRecherche.getText(), 
            caseSensibilite.isSelected());
        }
    });

    // Création du bouton "Remplacer".
    boutonRemplacer = new JButton("Remplacer");

    // Ajout d'un ActionListener pour le bouton "Remplacer".
    boutonRemplacer.addActionListener(new ActionListener() {

        // Redéfinition de la méthode `actionPerformed`, 
        //qui est appelée lorsque le bouton "Remplacer" est cliqué.
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // Remplace la première occurrence du texte recherché 
            //par le texte de remplacement dans `zoneDeTexte`.
            zoneDeTexte.remplacer(champRecherche.getText(),
             champRemplacement.getText(), caseSensibilite.isSelected());
        }
    });

    // Création du bouton "Remplacer tout".
    boutonRemplacerTout = new JButton("Remplacer tout");

    // Ajout d'un ActionListener pour le bouton "Remplacer tout".
    boutonRemplacerTout.addActionListener(new ActionListener() {

        // Redéfinition de la méthode `actionPerformed`,
        // qui est appelée lorsque le bouton "Remplacer tout" est cliqué.
        @Override
        public void actionPerformed(ActionEvent e) {

            // Remplace toutes les occurrences du texte recherché 
            //par le texte de remplacement dans `zoneDeTexte`.
            zoneDeTexte.remplacerTout(champRecherche.getText(),
             champRemplacement.getText(), caseSensibilite.isSelected());
        }
    });
}
}
