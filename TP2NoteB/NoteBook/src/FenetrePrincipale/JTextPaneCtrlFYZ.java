package FenetrePrincipale;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Classe étendue pour JTextPane avec fonctionnalités d'annulation, rétablissement et recherche.
 */
public class JTextPaneCtrlFYZ extends JTextPaneCtrlF {

    /**
     * Constructeur par défaut de la classe JTextPaneCtrlFYZ.
     * <p>
     * Initialise un nouvel objet UndoManager et configure les actions d'annulation, de rétablissement et de recherche.
     * </p>
     */
    public JTextPaneCtrlFYZ() {
        super();
        configurerRecherche();
    }

    /**
     * Configure l'action de recherche (Ctrl+F).
     */
    private void configurerRecherche() {
        getInputMap().put(KeyStroke.getKeyStroke("control F"), "find");
        getActionMap().put("find", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = JOptionPane.showInputDialog(JTextPaneCtrlFYZ.this, "Entrez le texte à rechercher:");
                if (text != null) {
                    int caseSensitiveOption = JOptionPane.showConfirmDialog(JTextPaneCtrlFYZ.this,
                     "La recherche doit-elle être sensible à la casse ?", "Sensibilité à la casse", JOptionPane.YES_NO_OPTION);
                    boolean caseSensitive = (caseSensitiveOption == JOptionPane.YES_OPTION);
                    rechercher(text, caseSensitive);
                }
            }
        });
    }
}