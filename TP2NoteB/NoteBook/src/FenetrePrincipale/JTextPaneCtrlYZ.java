package FenetrePrincipale;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.undo.UndoManager;

/**
 * Classe de base pour JTextPane avec fonctionnalités
 *  d'annulation et de rétablissement. Cette classe étend 
 * JTextPane et ajoute la gestion des actions d'annulation
 * et de rétablissement via les raccourcis clavier Ctrl+Z 
 * (annulation) et Ctrl+Y (rétablissement).
 */
public class JTextPaneCtrlYZ extends JTextPane {

    /** Gestionnaire d'annulation pour gérer les opérations d'annulation et de rétablissement. */
    protected UndoManager undoManager;

    /**
     * Constructeur par défaut de la classe JTextPaneCtrlYZ.
     * Initialise un nouvel objet UndoManager et configure les actions d'annulation et de rétablissement.
     */
    public JTextPaneCtrlYZ() {
        undoManager = new UndoManager();
        getDocument().addUndoableEditListener(undoManager);
        configurerAnnulationRetablissement();
    }

    /**
     * Configure les actions d'annulation et de rétablissement.
     * <p>
     * Associe les raccourcis clavier Ctrl+Z à l'action d'annulation et Ctrl+Y à l'action de rétablissement.
     * Les actions sont ajoutées à la map des actions de JTextPane.
     * </p>
     */
    private void configurerAnnulationRetablissement() {

        getInputMap().put(KeyStroke.getKeyStroke("control Z"), "undo");

        getActionMap().put("undo", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (undoManager.canUndo()) {

                    undoManager.undo();
                }
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("control Y"), "redo");

        getActionMap().put("redo", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (undoManager.canRedo()) {

                    undoManager.redo();

                }
            }
        });
    }
}