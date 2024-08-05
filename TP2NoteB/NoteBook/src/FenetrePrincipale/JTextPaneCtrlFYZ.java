package FenetrePrincipale;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Classe étendue pour JTextPane avec fonctionnalités 
 * d'annulation, rétablissement et recherche.
 */
public class JTextPaneCtrlFYZ extends JTextPaneCtrlF {

    /**
     * Constructeur par défaut de la classe JTextPaneCtrlFYZ.
     * Initialise un nouvel objet UndoManager et configure 
     * les actions d'annulation, de rétablissement et de recherche.
     */
    public JTextPaneCtrlFYZ() {
        
        // Appelle le constructeur de la classe parent.
        super();
        
        // Configure l'action de recherche.
        configurerRecherche();
    }

    /**
     * Configure l'action de recherche (Ctrl+F).
     */
    private void configurerRecherche() {

        // Associe Ctrl+F à l'action "find".
        getInputMap().put(KeyStroke.getKeyStroke("control F"), "find");

        // Définie l'action "find".
        getActionMap().put("find", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Affiche une boîte de dialogue pour entrer le texte à rechercher.
                String text = JOptionPane.showInputDialog(JTextPaneCtrlFYZ.this,
                        "Entrez le texte à rechercher:");
                
                if (text != null) {

                    // Affiche une boîte de dialogue pour demander
                    // la sensibilité à la casse.
                    int caseSensitiveOption = JOptionPane.
                    showConfirmDialog(JTextPaneCtrlFYZ.this,
                            "La recherche doit-elle être sensible à la casse ?", 
                            "Sensibilité à la casse", JOptionPane.YES_NO_OPTION);

                    // Détermine si la recherche est sensible à la casse.
                    boolean caseSensitive = (caseSensitiveOption == JOptionPane.YES_OPTION);

                    // Effectue la recherche avec ou sans sensibilité à la casse.
                    rechercher(text, caseSensitive);
                }
            }
        });
    }
}
