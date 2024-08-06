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

    /** Painter pour définir la couleur de surbrillance. */
    private Highlighter.HighlightPainter painter;

    /** Index du dernier résultat trouvé. */
    private int lastIndex = -1;

    /**
     * Constructeur par défaut de la classe JTextPaneCtrlF
     * Initialise le highlighter pour marquer les résultats de recherche.
     */
    public JTextPaneCtrlF() {

        // Appelle le constructeur de la classe parent.
        super();

        // Initialise le Highlighter et le Painter.
        highlighter = new DefaultHighlighter();

        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        // Définit le Highlighter pour le JTextPane.
        setHighlighter(highlighter);
    }

    /**
     * Recherche un texte dans le document avec une option
     * de sensibilité à la casse. Cette méthode recherche
     * toutes les occurrences du texte donné dans le document
     * et les met en surbrillance.
     * 
     * @param text Le texte à rechercher.
     * @param caseSensitive Si true, la recherche est sensible à la casse.
     */
    public void rechercher(String text, boolean caseSensitive) {

        // Supprime toutes les surbrillances existantes.
        highlighter.removeAllHighlights();

        // Si le texte est null ou vide, retourne immédiatement.
        if (text == null || text.isEmpty()) {

            return;
        }

        try {

            // Récupère le document et son contenu.
            Document doc = getDocument();

            String content = doc.getText(0, doc.getLength());

            // Si la recherche n'est pas sensible à la casse,
            // transforme le contenu et le texte en minuscules.
            if (!caseSensitive) {

                content = content.toLowerCase();

                text = text.toLowerCase();
            }

            // Recherche les occurrences du texte et les surligne.
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

            // Récupère le document et son contenu.
            Document doc = getDocument();

            String content = doc.getText(0, doc.getLength());

            // Si la recherche n'est pas sensible à la casse,
            // transforme le contenu et le texte en minuscules.
            if (!caseSensitive) {

                content = content.toLowerCase();

                text = text.toLowerCase();
            }

            // Détermine l'index de départ pour la recherche.
            int start = (lastIndex >= 0) ? lastIndex + 1 : 0;

            int index = content.indexOf(text, start);

            // Si une occurrence est trouvée, la surligne
            // et met à jour la position du caret.
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

        String texteRecherche = searchText;

        String texteRemplacement = replaceText;

        // Si le texte à rechercher est vide, retourne immédiatement.
        if (texteRecherche.isEmpty()) {

            return;
        }

        Document doc = getDocument();

        String texteComplet;

        try {

            texteComplet = doc.getText(0, doc.getLength());

        } catch (BadLocationException e) {

            e.printStackTrace();

            return;
        }

        // Si la recherche n'est pas sensible à la casse,
        // transforme le texte à rechercher et le texte complet en minuscules.
        if (!caseSensitive) {

            texteRecherche = texteRecherche.toLowerCase();

            texteComplet = texteComplet.toLowerCase();
        }

        // Recherche la première occurrence du texte à rechercher.
        int index = texteComplet.indexOf(texteRecherche);

        if (index >= 0) {

            getCaret().setDot(index);

            getCaret().moveDot(index + texteRecherche.length());

            replaceSelection(texteRemplacement);
        }
    }

    /**
     * Remplace toutes les occurrences du texte de recherche
     * par le texte de remplacement dans le document.
     * @param searchText Le texte à rechercher.
     * @param replaceText Le texte de remplacement.
     * @param caseSensitive Si true, la recherche est sensible à la casse.
     */
    public void remplacerTout(String searchText, String replaceText, boolean caseSensitive) {

        String texteRecherche = searchText;

        String texteRemplacement = replaceText;

        // Si le texte à rechercher est vide, retourne immédiatement.
        if (texteRecherche.isEmpty()) {

            return;
        }

        Document doc = getDocument();

        String texteComplet;

        try {

            texteComplet = doc.getText(0, doc.getLength());

        } catch (BadLocationException e) {

            e.printStackTrace();

            return;
        }

        // Si la recherche n'est pas sensible à la casse,
        // transforme le texte à rechercher et le texte complet en minuscules.
        if (!caseSensitive) {

            texteRecherche = texteRecherche.toLowerCase();

            texteComplet = texteComplet.toLowerCase();
        }

        // Remplace toutes les occurrences du texte à 
        //rechercher par le texte de remplacement.
        int index = 0;

        while ((index = texteComplet.indexOf(texteRecherche, index)) >= 0) {

            setCaretPosition(index);

            replaceSelection(texteRemplacement);

            index += texteRemplacement.length();

            try {

                texteComplet = doc.getText(0, doc.getLength());

            } catch (BadLocationException e) {

                e.printStackTrace();

                return;
            }
        }
    }
}
