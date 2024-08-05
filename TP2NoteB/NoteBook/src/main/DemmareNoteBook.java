package main;

import javax.swing.SwingUtilities;

/**
 * Projet: Éditeur de Texte
 * 
 * Ce projet est un éditeur de texte simple. 
 * L'éditeur de texte inclut des fonctionnalités telles que 
 * l'annulation/rétablissement (Ctrl+Z/Ctrl+Y), la recherche 
 * et le remplacement (Ctrl+F) et les opérations de fichier 
 * de base. Le menu est limité à 'Fichier' et 'Affichage'.
 * 
 * Auteurs:
 * @author Jorge Andres Yepes
 * @author Sara Yousuf
 * @author Aya Rehimi
 * @author Mohammed Amine El Hila
 * 
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
