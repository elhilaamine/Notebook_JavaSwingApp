package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import FenetrePrincipale.JTextPaneCtrlF;

public class FenetreRechercheRemplacement extends JDialog {

    private JButton boutonSuivant;
    private JButton boutonPrecedent;
    private JButton boutonRemplacer;
    private JButton boutonRemplacerTout;
    private JTextField champRecherche;
    private JTextField champRemplacement;
    private JComboBox<String> recherchePrecedente;
    private JPanel panneauRechercheRemplacement;

    public FenetreRechercheRemplacement(JTextPaneCtrlF zoneDeTexte) {
        super();
        setSize(400, 200);
        setLocationRelativeTo(zoneDeTexte);

        creerBoutons(zoneDeTexte);
        creerZoneTexte();
        creerListeDeroulante();
        creerPanneau();
        ajoutComposantsPanneau();
        add(panneauRechercheRemplacement);
    }

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
    }

    private void creerPanneau() {
        panneauRechercheRemplacement = new JPanel(new GridLayout(5, 2));
    }

    private void creerListeDeroulante() {
        recherchePrecedente = new JComboBox<>();
    }

    private void creerZoneTexte() {
        champRecherche = new JTextField(20);
        champRemplacement = new JTextField(20);
    }

    private void creerBoutons(JTextPaneCtrlF zoneDeTexte) {
        boutonSuivant = new JButton("Suivant");
        boutonSuivant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDeTexte.rechercherSuivant(champRecherche.getText(), true);
            }
        });

        boutonPrecedent = new JButton("Précédent");
        // Ajoutez l'action pour le bouton précédent ici

        boutonRemplacer = new JButton("Remplacer");
        boutonRemplacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDeTexte.remplacer(champRecherche.getText(), champRemplacement.getText(), true);
            }
        });

        boutonRemplacerTout = new JButton("Remplacer tout");
        boutonRemplacerTout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDeTexte.remplacerTout(champRecherche.getText(), champRemplacement.getText(), true);
            }
        });
    }
}
