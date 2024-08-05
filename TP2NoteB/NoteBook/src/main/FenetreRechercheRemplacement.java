package main;

import FenetrePrincipale.JTextPaneCtrlF;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
    
    // Champs de texte pour la recherche et le remplacement
    private JTextField champRecherche;
    private JTextField champRemplacement;
    
    // Liste déroulante pour les recherches précédentes
    private JComboBox<String> recherchePrecedente;
    
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
        setSize(400, 200); 

        // Positionne la boîte de dialogue au centre de zoneDeTexte
        setLocationRelativeTo(zoneDeTexte); 

        // Crée et configure les boutons
        creerBoutons(zoneDeTexte); 

        // Crée et configure les champs de texte
        creerZoneTexte(); 

        // Crée et configure la liste déroulante
        creerListeDeroulante(); 

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

        // Ajoute un label pour la recherche
        panneauRechercheRemplacement.add(new JLabel("Recherche :")); 

        // Ajoute le champ de texte pour la recherche
        panneauRechercheRemplacement.add(champRecherche); 

        // Ajoute un label pour le remplacement
        panneauRechercheRemplacement.add(new JLabel("Remplacement :")); 

         // Ajoute le champ de texte pour le remplacement
        panneauRechercheRemplacement.add(champRemplacement);

        // Ajoute le bouton "Suivant"
        panneauRechercheRemplacement.add(boutonSuivant); 

        // Ajoute le bouton "Précédent"
        panneauRechercheRemplacement.add(boutonPrecedent); 

        // Ajoute le bouton "Remplacer"
        panneauRechercheRemplacement.add(boutonRemplacer); 

        // Ajoute le bouton "Remplacer tout"
        panneauRechercheRemplacement.add(boutonRemplacerTout); 

        // Ajoute un label pour les recherches précédentes
        panneauRechercheRemplacement.add(new
         JLabel("Recherches précédentes :")); 

        // Ajoute la liste déroulante pour les recherches précédentes
        panneauRechercheRemplacement.add(recherchePrecedente); 
    }

    /**
     * Crée et configure le panneau principal.
     */
    private void creerPanneau() {

        // Crée un panneau avec une grille 5x2
        panneauRechercheRemplacement = new JPanel(new GridLayout(5, 2)); 
    }

    /**
     * Crée et configure la liste déroulante pour 
     * les recherches précédentes.
     */
    private void creerListeDeroulante() {

        // Initialise la liste déroulante pour les recherches précédentes
        recherchePrecedente = new JComboBox<>(); 
    }

    /**
     * Crée et configure les champs de texte pour 
     * la recherche et le remplacement.
     */
    private void creerZoneTexte() {

        // Initialise le champ de texte pour la recherche 
        //avec une largeur de 20 colonnes
        champRecherche = new JTextField(20); 


        // Initialise le champ de texte pour le remplacement
        // avec une largeur de 20 colonnes
        champRemplacement = new JTextField(20); 
    }

    /**
     * Crée et configure les boutons.
     *
     * @param zoneDeTexte la zone de texte sur laquelle effectuer 
     * les recherches et remplacements
     */
    private void creerBoutons(JTextPaneCtrlF zoneDeTexte) {

        // Initialise le bouton "Suivant"
        boutonSuivant = new JButton("Suivant"); 
        boutonSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Action lors du clic sur le bouton "Suivant"
                zoneDeTexte.rechercherSuivant(champRecherche.getText(), true); 
            }
        });

        // Initialise le bouton "Précédent"
        boutonPrecedent = new JButton("Précédent"); 
        boutonPrecedent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Action lors du clic sur le bouton "Précédent"
                zoneDeTexte.rechercher(champRecherche.getText(), true); 
            }
        });


        // Initialise le bouton "Remplacer"
        boutonRemplacer = new JButton("Remplacer"); 
        boutonRemplacer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Action lors du clic sur le bouton "Remplacer"
                zoneDeTexte.remplacer(champRecherche.getText(), 
                champRemplacement.getText(), true); 
            }
        });

        // Initialise le bouton "Remplacer tout"
        boutonRemplacerTout = new JButton("Remplacer tout"); 
        boutonRemplacerTout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Action lors du clic sur le bouton "Remplacer tout"
                zoneDeTexte.remplacerTout(champRecherche.getText(), 
                champRemplacement.getText(), true); 
            }
        });
    }
}
