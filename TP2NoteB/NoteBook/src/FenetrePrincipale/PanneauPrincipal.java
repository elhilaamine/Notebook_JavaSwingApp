package FenetrePrincipale;

import java.awt.*;
import javax.swing.*;

public class PanneauPrincipal extends JPanel {

    private JTextPane zoneDeTexte;
    private JPanel barreDEtat;

    public PanneauPrincipal() {
        setLayout(new BorderLayout());
        ajouterZoneDeTexte();
        ajouterBarreDEtat();
    }

    private void ajouterZoneDeTexte() {
        zoneDeTexte = new JTextPane();
        JScrollPane panneauDeDefilement = new JScrollPane(zoneDeTexte);
        add(panneauDeDefilement, BorderLayout.CENTER);
    }

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
