package main;

import javax.swing.SwingUtilities;

/**
 * La classe principale pour démarrer l'éditeur de texte.
 * Elle utilise SwingUtilities pour garantir que l'interface utilisateur
 * est créée et mise à jour sur le thread de l'EDT (Event Dispatch Thread).
 */
public class DemmareNoteBook {
    
    /**
     * Méthode principale pour démarrer l'application.
     *
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        
        // Utilise SwingUtilities pour lancer l'éditeur de texte sur l'EDT
        SwingUtilities.invokeLater(new EditeurDeTexte());
    }
}
