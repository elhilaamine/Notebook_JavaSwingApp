package FenetrePrincipale;

import java.awt.*;
import javax.swing.*;

/**
 * Panneau principal contenant la zone de texte et la barre d'état.
 */
public class PanneauPrincipal extends JPanel {

    private JTextPane zoneDeTexte;
    private JPanel barreDEtat;

    /**
     * Constructeur pour initialiser le panneau principal.
     * Configure la disposition et ajoute les composants principaux.
     */
    public PanneauPrincipal() {
        
        setLayout(new BorderLayout());
        ajouterZoneDeTexte();
        ajouterBarreDEtat();
    }

    /**
     * Ajoute la zone de texte au panneau principal.
     * La zone de texte est enveloppée dans un JScrollPane pour permettre le défilement.
     */
    private void ajouterZoneDeTexte() {

        zoneDeTexte = new JTextPane();
        JScrollPane panneauDeDefilement = new JScrollPane(zoneDeTexte);

        add(panneauDeDefilement, BorderLayout.CENTER);
    }

    /**
     * Ajoute la barre d'état au panneau principal.
     * La barre d'état contient un JLabel pour afficher des messages d'état.
     */
    private void ajouterBarreDEtat() {

        barreDEtat = new JPanel(new BorderLayout());
        JLabel labelEtat = new JLabel("Prêt");

        barreDEtat.add(labelEtat, BorderLayout.WEST);
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

        ((JLabel) barreDEtat.getComponent(0)).setText(message);
    }
}
