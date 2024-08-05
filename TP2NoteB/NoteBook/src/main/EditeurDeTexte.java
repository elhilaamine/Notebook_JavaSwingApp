package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import FenetrePrincipale.JTextPaneCtrlF;

/**
 * Classe principale de l'éditeur de texte.
 * Cette classe crée la fenêtre principale, la zone de texte, et les menus.
 */
public class EditeurDeTexte extends JFrame implements Runnable {

    // Zone de texte avec fonctionnalités de recherche et remplacement
    private JTextPaneCtrlF zoneDeTexte;

    // Fenêtre pour la recherche et le remplacement
    private FenetreRechercheRemplacement fenetreRechercheRemplacement;

    // Gestionnaire pour les opérations d'annulation et de rétablissement
    private UndoManager refaireDefaire;

    // Barre d'état pour afficher des informations
    private JLabel barreDEtat;

    // Indicateur pour afficher ou masquer la barre d'état
    private boolean afficherBarreDEtat = true;

    /**
     * Constructeur de la classe EditeurDeTexte.
     * Initialise la fenêtre, la zone de texte, les menus et la barre d'état.
     */
    public EditeurDeTexte() {
        
        initialiserFenetre(); // Initialise les propriétés de la fenêtre
        ajouterZoneDeTexte(); // Ajoute la zone de texte à la fenêtre
        creerBarreDeMenu(); // Crée la barre de menu
        ajouterBarreDEtat(); // Ajoute la barre d'état

        // Initialise le gestionnaire d'annulation/rétablissement
        refaireDefaire = new UndoManager(); 

        // Ajoute un écouteur pour les modifications annulables
        zoneDeTexte.getDocument().addUndoableEditListener(refaireDefaire); 

        // Configure les écouteurs d'événements
        configurerEcouteurs(); 
    }

    /**
     * Méthode run pour afficher la fenêtre.
     */
    @Override
    public void run() {
        setVisible(true); // Rendre la fenêtre visible
    }

    /**
     * Initialise les propriétés de la fenêtre.
     */
    private void initialiserFenetre() {

         // Définir le titre de la fenêtre
        setTitle("Éditeur de Texte");

         // Définir la taille de la fenêtre
        setSize(800, 600);

        // Définir l'opération de fermeture par défaut
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    /**
     * Ajoute la zone de texte à la fenêtre.
     */
    private void ajouterZoneDeTexte() {

         // Initialise la zone de texte
        zoneDeTexte = new JTextPaneCtrlF();

        // Ajoute un panneau de défilement à la zone de texte
        JScrollPane panneauDeDefilement = new JScrollPane(zoneDeTexte); 

        // Ajoute le panneau de défilement au centre de la fenêtre
        getContentPane().add(panneauDeDefilement, BorderLayout.CENTER); 
    }

    /**
     * Crée la barre de menu et ajoute les éléments de menu.
     */
    private void creerBarreDeMenu() {

        // Crée une nouvelle barre de menu
        JMenuBar barreDeMenu = new JMenuBar(); 

        // Définit la barre de menu pour la fenêtre
        setJMenuBar(barreDeMenu);

        // Crée le menu "Fichier" et y ajoute des éléments de menu
        JMenu menuFichier = new JMenu("Fichier");
        barreDeMenu.add(menuFichier);
        ajouterElementDeMenu(menuFichier, "Nouveau", 
        e -> nouvelleAction());

        ajouterElementDeMenu(menuFichier, "Ouvrir", 
        e -> ouvrirAction());

        ajouterElementDeMenu(menuFichier, "Sauvegarder",
         e -> sauvegarderAction());

        ajouterElementDeMenu(menuFichier, "Quitter",
         e -> System.exit(0));

        // Crée le menu "Affichage" et y ajoute des éléments de menu
        JMenu menuAffichage = new JMenu("Affichage");
        barreDeMenu.add(menuAffichage);

        ajouterElementDeMenu(menuAffichage, "Zoom +",
         e -> zoomAvantAction());

        ajouterElementDeMenu(menuAffichage, "Zoom -", 
        e -> zoomArriereAction());

        ajouterElementDeMenu(menuAffichage, "Barre d’état", 
        e -> barreDEtatAction());
    }

    /**
     * Ajoute un élément de menu au menu spécifié.
     *
     * @param menu le menu auquel ajouter l'élément
     * @param nom le nom de l'élément de menu
     * @param actionListener l'action à effectuer lorsque l'élément est sélectionné
     */
    private void ajouterElementDeMenu(JMenu menu, String nom, ActionListener actionListener) {

        // Crée un nouvel élément de menu
        JMenuItem menuItem = new JMenuItem(nom); 

        // Ajoute l'écouteur d'action à l'élément de menu
        menuItem.addActionListener(actionListener); 

        // Ajoute l'élément de menu au menu
        menu.add(menuItem);
    }

    /**
     * Action pour créer un nouveau document.
     */
    private void nouvelleAction() {
        zoneDeTexte.setText(""); // Efface le contenu de la zone de texte
    }

    /**
     * Action pour ouvrir un fichier.
     */
    private void ouvrirAction() {

         // Crée un sélecteur de fichiers
        JFileChooser fileChooser = new JFileChooser();

        // Définit le filtre pour les fichiers texte
        fileChooser.setFileFilter(new FileNameExtensionFilter
        ("Fichiers texte", "txt")); 

         // Affiche le dialogue d'ouverture de fichier
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {

            // Récupère le fichier sélectionné
            File file = fileChooser.getSelectedFile(); 
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                // Lit le contenu du fichier dans la zone de texte
                zoneDeTexte.read(reader, null); 
            } catch (IOException e) {

                // Affiche un message d'erreur en cas d'exception
                JOptionPane.showMessageDialog(this,
                "Erreur lors de l'ouverture du fichier"
                , "Erreur", JOptionPane.ERROR_MESSAGE); 
            }
        }
    }

    /**
     * Action pour sauvegarder un fichier.
     */
    private void sauvegarderAction() {

        // Crée un sélecteur de fichiers
        JFileChooser fileChooser = new JFileChooser(); 

        // Définit le filtre pour les fichiers texte
        fileChooser.setFileFilter(new FileNameExtensionFilter
        ("Fichiers texte", "txt")); 

        // Affiche le dialogue de sauvegarde de fichier
        int option = fileChooser.showSaveDialog(this); 

        if (option == JFileChooser.APPROVE_OPTION) {

            // Récupère le fichier sélectionné
            File file = fileChooser.getSelectedFile(); 
            if (!file.getName().endsWith(".txt")) {

                // Ajoute l'extension .txt si nécessaire
                file = new File(file.getAbsolutePath() + ".txt"); 
            }
            try (FileWriter writer = new FileWriter(file)) {

                // Écrit le contenu de la zone de texte dans le fichier
                zoneDeTexte.write(writer); 
            } catch (IOException e) {

                // Affiche un message d'erreur en cas d'exception
                JOptionPane.showMessageDialog(this, 
                "Erreur lors de la sauvegarde du fichier"
                , "Erreur", JOptionPane.ERROR_MESSAGE); 
            }
        }
    }

    /**
     * Action pour augmenter la taille de la police (zoom avant).
     */
    private void zoomAvantAction() {

        // Augmente la taille de la police
        zoneDeTexte.setFont(zoneDeTexte.getFont().deriveFont(zoneDeTexte
        .getFont().getSize() + 2f)); 
    }

    /**
     * Action pour diminuer la taille de la police (zoom arrière).
     */
    private void zoomArriereAction() {

        // Diminue la taille de la police
        zoneDeTexte.setFont(zoneDeTexte.getFont()
        .deriveFont(zoneDeTexte.getFont().getSize() - 2f)); 
    }

    /**
     * Action pour afficher ou masquer la barre d'état.
     */
    private void barreDEtatAction() {

        // Inverse l'état d'affichage de la barre d'état
        afficherBarreDEtat = !afficherBarreDEtat; 

        // Affiche ou masque la barre d'état
        barreDEtat.setVisible(afficherBarreDEtat); 
    }

    /**
     * Ajoute la barre d'état à la fenêtre et configure son écouteur de caret.
     */
    private void ajouterBarreDEtat() {

        // Initialise la barre d'état avec le texte par défaut
        barreDEtat = new JLabel("Ligne: 1, Colonne: 1"); 

        // Ajoute la barre d'état au bas de la fenêtre
        getContentPane().add(barreDEtat, BorderLayout.SOUTH); 

        zoneDeTexte.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {

                try {

                    // Récupère la position du caret
                    int caretPos = zoneDeTexte.getCaretPosition(); 

                    // Calcule la ligne actuelle
                    int ligne = zoneDeTexte.getDocument().getDefaultRootElement()
                    .getElementIndex(caretPos) + 1; 

                    // Calcule la colonne actuelle
                    int col = caretPos - zoneDeTexte.getDocument()
                    .getDefaultRootElement().getElement(ligne - 1).getStartOffset() + 1; 

                    // Met à jour le texte de la barre d'état
                    barreDEtat.setText("Ligne: " + ligne + ", Colonne: " + col); 
                } catch (Exception ex) {

                    // Affiche la pile d'exécution en cas d'erreur
                    ex.printStackTrace(); 
                }
            }
        });
    }

    /**
     * Configure les écouteurs pour les raccourcis clavier.
     */
    private void configurerEcouteurs() {
        zoneDeTexte.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if ((e.getKeyCode() == KeyEvent.VK_Z) && e.isControlDown()) {

                    if (refaireDefaire.canUndo()) {

                        // Annule la dernière action si possible
                        refaireDefaire.undo(); 
                    }
                }
                if ((e.getKeyCode() == KeyEvent.VK_Y) && e.isControlDown()) {

                    if (refaireDefaire.canRedo()) {

                        // Rétablit la dernière action annulée si possible
                        refaireDefaire.redo();
                    }
                }
                if ((e.getKeyCode() == KeyEvent.VK_F) && e.isControlDown()) {

                    // Crée et affiche la fenêtre de recherche/remplacement
                    fenetreRechercheRemplacement = 
                    new FenetreRechercheRemplacement(zoneDeTexte); 
                    
                    fenetreRechercheRemplacement.setVisible(true);
                }
            }
        });
    }
}
