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

/**
 * Classe principale de l'éditeur de texte.
 * Cette classe crée la fenêtre principale, la zone de texte, et les menus.
 */
public class EditeurDeTexte extends JFrame implements Runnable {

    private JTextPane zoneDeTexte;
    private FenetreRechercheRemplacement fenetreRechercheRemplacement;
    private UndoManager refaireDefaire;
    private JLabel barreDEtat;
    private boolean afficherBarreDEtat = true;

    public EditeurDeTexte() {
        initialiserFenetre();
        ajouterZoneDeTexte();
        creerBarreDeMenu();
        ajouterBarreDEtat();

        refaireDefaire = new UndoManager();
        zoneDeTexte.getDocument().addUndoableEditListener(refaireDefaire);
        configurerEcouteurs();
    }

    @Override
    public void run() {
        setVisible(true);
    }

    /**
     * Initialise les paramètres de la fenêtre principale.
     */
    private void initialiserFenetre() {
        setTitle("Éditeur de Texte");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Ajoute une zone de texte éditable à la fenêtre.
     */
    private void ajouterZoneDeTexte() {
        zoneDeTexte = new JTextPane();
        JScrollPane panneauDeDefilement = new JScrollPane(zoneDeTexte);
        getContentPane().add(panneauDeDefilement, BorderLayout.CENTER);
    }

    /**
     * Crée la barre de menu et y ajoute les menus et les éléments de menu.
     */
    private void creerBarreDeMenu() {
        JMenuBar barreDeMenu = new JMenuBar();
        setJMenuBar(barreDeMenu);

        JMenu menuFichier = new JMenu("Fichier");
        barreDeMenu.add(menuFichier);
        ajouterElementDeMenu(menuFichier, "Nouveau", e -> nouvelleAction());
        ajouterElementDeMenu(menuFichier, "Ouvrir", e -> ouvrirAction());
        ajouterElementDeMenu(menuFichier, "Sauvegarder", e -> sauvegarderAction());
        ajouterElementDeMenu(menuFichier, "Quitter", e -> System.exit(0));

        JMenu menuAffichage = new JMenu("Affichage");
        barreDeMenu.add(menuAffichage);
        ajouterElementDeMenu(menuAffichage, "Zoom +", e -> zoomAvantAction());
        ajouterElementDeMenu(menuAffichage, "Zoom -", e -> zoomArriereAction());
        ajouterElementDeMenu(menuAffichage, "Barre d’état", e -> barreDEtatAction());
    }

    /**
     * Ajoute un élément de menu avec un ActionListener au menu spécifié.
     *
     * @param menu           Le menu auquel ajouter l'élément de menu.
     * @param nom            Le nom de l'élément de menu.
     * @param actionListener L'action à effectuer lorsque l'élément de menu est sélectionné.
     */
    private void ajouterElementDeMenu(JMenu menu, String nom, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(nom);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }

    /**
     * Action à effectuer lorsque l'utilisateur clique sur "Nouveau".
     * Efface le contenu de la zone de texte.
     */
    private void nouvelleAction() {
        zoneDeTexte.setText("");
    }

    /**
     * Action à effectuer lorsque l'utilisateur clique sur "Ouvrir".
     * Affiche un sélecteur de fichier pour ouvrir un fichier.
     */
    private void ouvrirAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers texte", "txt"));
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                zoneDeTexte.read(reader, null);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ouverture du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Action à effectuer lorsque l'utilisateur clique sur "Sauvegarder".
     * Affiche un sélecteur de fichier pour sauvegarder un fichier.
     */
    private void sauvegarderAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers texte", "txt"));
        int option = fileChooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            try (FileWriter writer = new FileWriter(file)) {
                zoneDeTexte.write(writer);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la sauvegarde du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Action à effectuer lorsque l'utilisateur clique sur "Zoom +".
     * Augmente la taille de la police dans la zone de texte.
     */
    private void zoomAvantAction() {
        zoneDeTexte.setFont(zoneDeTexte.getFont()
                .deriveFont(zoneDeTexte.getFont().getSize() + 2f));
    }

    /**
     * Action à effectuer lorsque l'utilisateur clique sur "Zoom -".
     * Réduit la taille de la police dans la zone de texte.
     */
    private void zoomArriereAction() {
        zoneDeTexte.setFont(zoneDeTexte.getFont()
                .deriveFont(zoneDeTexte.getFont().getSize() - 2f));
    }

    /**
     * Action à effectuer lorsque l'utilisateur clique sur "Barre d’état".
     * Affiche ou masque la barre d'état.
     */
    private void barreDEtatAction() {
        afficherBarreDEtat = !afficherBarreDEtat;
        barreDEtat.setVisible(afficherBarreDEtat);
    }

    /**
     * Ajoute une barre d'état à la fenêtre.
     */
    private void ajouterBarreDEtat() {
        barreDEtat = new JLabel("Ligne: 1, Colonne: 1");
        getContentPane().add(barreDEtat, BorderLayout.SOUTH);
        zoneDeTexte.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    int caretPos = zoneDeTexte.getCaretPosition();
                    int ligne = zoneDeTexte.getDocument().getDefaultRootElement().getElementIndex(caretPos) + 1;
                    int col = caretPos - zoneDeTexte.getDocument().getDefaultRootElement().getElement(ligne - 1).getStartOffset() + 1;
                    barreDEtat.setText("Ligne: " + ligne + ", Colonne: " + col);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Configure les écouteurs de touches pour la zone de texte.
     */
    private void configurerEcouteurs() {
        zoneDeTexte.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_Z) && e.isControlDown()) {
                    if (refaireDefaire.canUndo()) {
                        refaireDefaire.undo();
                    }
                }
                if ((e.getKeyCode() == KeyEvent.VK_Y) && e.isControlDown()) {
                    if (refaireDefaire.canRedo()) {
                        refaireDefaire.redo();
                    }
                }
                if ((e.getKeyCode() == KeyEvent.VK_F) && e.isControlDown()) {
                    fenetreRechercheRemplacement = new FenetreRechercheRemplacement(zoneDeTexte);
                    fenetreRechercheRemplacement.setVisible(true);
                }
            }
        });
    }
}