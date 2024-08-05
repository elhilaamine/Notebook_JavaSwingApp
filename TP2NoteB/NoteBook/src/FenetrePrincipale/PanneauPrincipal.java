package FenetrePrincipale;

import java.awt.*;
import javax.swing.*;

/**
 * Panneau principal contenant la zone de texte et la barre d'état.
 */
public class PanneauPrincipal extends JPanel {

    // La zone de texte principale.
    private JTextPane zoneDeTexte;

    // La barre d'état pour afficher les messages d'état.
    private JPanel barreDEtat;

    /**
     * Constructeur pour initialiser le panneau principal.
     * Configure la disposition et ajoute les composants principaux.
     */
    public PanneauPrincipal() {
        
        // Définit la disposition du panneau principal.
        setLayout(new BorderLayout());
        
        // Ajoute la zone de texte au panneau principal.
        ajouterZoneDeTexte();
        
        // Ajoute la barre d'état au panneau principal.
        ajouterBarreDEtat();
    }

    /**
     * Ajoute la zone de texte au panneau principal.
     * La zone de texte est enveloppée dans un JScrollPane pour permettre le défilement.
     */
    private void ajouterZoneDeTexte() {

        // Initialise la zone de texte.
        zoneDeTexte = new JTextPane();
        
        // Enveloppe la zone de texte dans un panneau de défilement.
        JScrollPane panneauDeDefilement = new JScrollPane(zoneDeTexte);

        // Ajoute le panneau de défilement au centre du panneau principal.
        add(panneauDeDefilement, BorderLayout.CENTER);
    }

    /**
     * Ajoute la barre d'état au panneau principal.
     * La barre d'état contient un JLabel pour afficher des messages d'état.
     */
    private void ajouterBarreDEtat() {

        // Initialise la barre d'état avec une disposition BorderLayout.
        barreDEtat = new JPanel(new BorderLayout());
        
        // Crée un label d'état avec le message "Prêt".
        JLabel labelEtat = new JLabel("Prêt");

        // Ajoute le label d'état à la barre d'état, aligné à gauche.
        barreDEtat.add(labelEtat, BorderLayout.WEST);
        
        // Ajoute la barre d'état au bas du panneau principal.
        add(barreDEtat, BorderLayout.SOUTH);
    }

    /**
     * Obtient la zone de texte.
     * 
     * @return la zone de texte (JTextPane)
     */
    public JTextPane getZoneDeTexte() {
        return zoneDeTexte;
    }

    /**
     * Obtient la barre d'état.
     * 
     * @return la barre d'état (JPanel)
     */
    public JPanel getBarreDEtat() {
        return barreDEtat;
    }

    /**
     * Met à jour le message affiché dans la barre d'état.
     * 
     * @param message le nouveau message à afficher dans la barre d'état
     */
    public void mettreAJourBarreDEtat(String message) {

        // Met à jour le texte du label d'état dans la barre d'état.
        ((JLabel) barreDEtat.getComponent(0)).setText(message);
    }
}