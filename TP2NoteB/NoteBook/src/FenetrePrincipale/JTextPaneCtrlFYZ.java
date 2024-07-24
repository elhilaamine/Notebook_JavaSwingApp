/**
 * Classe pour JTextPane avec fonctionnalités d'annulation, de rétablissement et de recherche.
 */
public class JTextPaneCtrlFYZ extends JTextPaneCtrlYZ {

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
                new FenetreRecherche(JTextPaneCtrlFYZ.this).setVisible(true);
            }
        });
    }
}
