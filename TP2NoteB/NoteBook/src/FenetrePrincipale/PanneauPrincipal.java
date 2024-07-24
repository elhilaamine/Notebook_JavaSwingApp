package FenetrePrincipale;

import java.awt.*;
import javax.swing.*;

/**
 * Panneau principal contenant la zone de texte et la barre d'état.
 */
public class PanneauPrincipal extends JPanel {

    private JTextPane zoneDeTexte;
    private JPanel barreDEtat;

    public PanneauPrincipal() {

        setLayout(new BorderLayout());
        
        ajouterZoneDeTexte();
        ajouterBarreDEtat();
    }

    /**
     * Ajoute la zone de texte au panneau principal.
     */
    private void ajouterZoneDeTexte() {

        zoneDeTexte = new JTextPane();

        JScrollPane panneauDeDefilement = new JScrollPane(zoneDeTexte);

        add(panneauDeDefilement, BorderLayout.CENTER);
    }

    /**
     * Ajoute la barre d'état au panneau principal.
     */
    private void ajouterBarreDEtat() {

        barreDEtat = new JPanel(new BorderLayout());

        JLabel labelEtat = new JLabel("Prêt");

        barreDEtat.add(labelEtat, BorderLayout.WEST);

        add(barreDEtat, BorderLayout.SOUTH);
    }

    public JTextPane getZoneDeTexte() {

        return zoneDeTexte;
    }

    public JPanel getBarreDEtat() {

        return barreDEtat;
    }

    public void mettreAJourBarreDEtat(String message) {

        ((JLabel) barreDEtat.getComponent(0)).setText(message);
    }
}
