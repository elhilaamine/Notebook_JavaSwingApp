package FenetrePrincipale;

import java.awt.Color;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

/**
 * Classe de base pour JTextPane avec fonctionnalité de recherche.
 */
public class JTextPaneCtrlF extends JTextPaneCtrlYZ {

    /** Highlighter pour marquer les résultats de recherche. */
    private Highlighter highlighter;
    private Highlighter.HighlightPainter painter;

    /**
     * Constructeur par défaut de la classe JTextPaneCtrlF.
     * <p>
     * Initialise le highlighter pour marquer les résultats de recherche.
     * </p>
     */
    public JTextPaneCtrlF() {
        super();
        highlighter = new DefaultHighlighter();
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        setHighlighter(highlighter);
    }

    /**
     * Recherche un texte dans le document avec une option de sensibilité à la casse.
     * <p>
     * Cette méthode recherche toutes les occurrences du texte donné dans le document
     * et les met en surbrillance.
     * </p>
     * @param text Le texte à rechercher.
     * @param caseSensitive Si true, la recherche est sensible à la casse.
     */
    public void rechercher(String text, boolean caseSensitive) {
        highlighter.removeAllHighlights();

        if (text == null || text.isEmpty()) {
            return;
        }

        try {
            Document doc = getDocument();
            String content = doc.getText(0, doc.getLength());
            if (!caseSensitive) {
                content = content.toLowerCase();
                text = text.toLowerCase();
            }

            int index = 0;
            while ((index = content.indexOf(text, index)) >= 0) {
                int end = index + text.length();
                highlighter.addHighlight(index, end, painter);
                index = end;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}