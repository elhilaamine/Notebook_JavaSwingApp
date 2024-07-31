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

    /** Index du dernier résultat trouvé. */
    private int lastIndex = -1;

    /**
     * Constructeur par défaut de la classe JTextPaneCtrlF
     * Initialise le highlighter pour marquer les résultats de recherche
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

    /**
     * Recherche la prochaine occurrence du texte donné dans le document.
     * @param text Le texte à rechercher.
     * @param caseSensitive Si true, la recherche est sensible à la casse.
     */
    public void rechercherSuivant(String text, boolean caseSensitive) {

        try {

            Document doc = getDocument();
            String content = doc.getText(0, doc.getLength());
            if (!caseSensitive) {

                content = content.toLowerCase();
                text = text.toLowerCase();
            }

            int start = (lastIndex >= 0) ? lastIndex + 1 : 0;
            int index = content.indexOf(text, start);
            if (index >= 0) {

                int end = index + text.length();
                highlighter.removeAllHighlights();
                highlighter.addHighlight(index, end, painter);
                lastIndex = index;
                setCaretPosition(end);
            }
        } catch (BadLocationException e) {

            e.printStackTrace();
        }
    }

    /**
     * Remplace le texte actuellement surligné par un autre texte.
     * @param searchText Le texte à rechercher.
     * @param replaceText Le texte de remplacement.
     * @param caseSensitive Si true, la recherche est sensible à la casse.
     */
    public void remplacer(String searchText, String replaceText, boolean caseSensitive) {

        try {

            Document doc = getDocument();
            String content = doc.getText(0, doc.getLength());
            if (!caseSensitive) {

                content = content.toLowerCase();
                searchText = searchText.toLowerCase();
            }

            int start = (lastIndex >= 0) ? lastIndex : 0;
            int index = content.indexOf(searchText, start);

            if (index >= 0) {

                doc.remove(index, searchText.length());
                doc.insertString(index, replaceText, null);
                lastIndex = index + replaceText.length();
                rechercher(searchText, caseSensitive); // Mettre à jour les surlignages
            }
        } catch (BadLocationException e) {
            
            e.printStackTrace();
        }
    }

    /**
     * Remplace toutes les occurrences d'un texte par un autre texte.
     * @param searchText Le texte à rechercher.
     * @param replaceText Le texte de remplacement.
     * @param caseSensitive Si true, la recherche est sensible à la casse.
     */
    public void remplacerTout(String searchText, String replaceText, boolean caseSensitive) {
        try {
            Document doc = getDocument();
            String content = doc.getText(0, doc.getLength());
            if (!caseSensitive) {
                content = content.toLowerCase();
                searchText = searchText.toLowerCase();
            }

            int index = 0;
            while ((index = content.indexOf(searchText, index)) >= 0) {
                doc.remove(index, searchText.length());
                doc.insertString(index, replaceText, null);
                index += replaceText.length();
                content = doc.getText(0, doc.getLength()); // Mise à jour du contenu
            }
            rechercher(searchText, caseSensitive); // Mettre à jour les surlignages
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
