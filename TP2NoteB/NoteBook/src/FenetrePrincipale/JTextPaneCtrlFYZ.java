package FenetrePrincipale;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.undo.UndoManager;

public class JTextPaneCtrlFYZ extends JTextPane {

    private UndoManager undoManager;

    public JTextPaneCtrlFYZ() {
        undoManager = new UndoManager();
        getDocument().addUndoableEditListener(undoManager);

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

        getInputMap().put(KeyStroke.getKeyStroke("control F"), "find");
        getActionMap().put("find", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FenetreRecherche(JTextPaneCtrlFYZ.this).setVisible(true);
            }
        });
    }
}
