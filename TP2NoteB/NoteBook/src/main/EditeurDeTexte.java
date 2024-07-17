package main;


import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe principale de l'éditeur de texte.
 * Cette classe crée la fenêtre principale, la zone de texte, et les menus.
 */
public class EditeurDeTexte extends JFrame implements Runnable {

    private JTextPane zoneDeTexte;
    
    private FenetreRechercheRemplacement fenetreRechercheRemplacement;

    /**
     * Constructeur de la classe EditeurDeTexte.
     * Initialise la fenêtre, la zone de texte, et la barre de menu.
     */
    public EditeurDeTexte() {
    	
        initialiserFenetre();
        ajouterZoneDeTexte();
        creerBarreDeMenu();
 
        
        zoneDeTexte.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
           
               
                if ((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
                    fenetreRechercheRemplacement = new FenetreRechercheRemplacement(EditeurDeTexte.this);
                    fenetreRechercheRemplacement.setVisible(true);
                }
            }
        });
    }

    /**
     * Méthode principale pour exécuter l'application.
     * Rend la fenêtre visible.
     */
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
        getContentPane().add(panneauDeDefilement);
    }

    /**
     * Crée la barre de menu et y ajoute les menus et les éléments de menu.
     */
    private void creerBarreDeMenu() {
    	
        JMenuBar barreDeMenu = new JMenuBar();
        
        setJMenuBar(barreDeMenu);

        // Créer et ajouter le menu Fichier
        JMenu menuFichier = new JMenu("Fichier");
        barreDeMenu.add(menuFichier);

        ajouterElementDeMenu(menuFichier, "Nouveau", e -> nouvelleAction());
        ajouterElementDeMenu(menuFichier, "Ouvrir", e -> ouvrirAction());
        ajouterElementDeMenu(menuFichier, "Sauvegarder", 
        		e -> sauvegarderAction());
        ajouterElementDeMenu(menuFichier, "Quitter", e -> System.exit(0));

        // Créer et ajouter le menu Affichage
        JMenu menuAffichage = new JMenu("Affichage");
        barreDeMenu.add(menuAffichage);

        ajouterElementDeMenu(menuAffichage, "Zoom +", e -> zoomAvantAction());
        ajouterElementDeMenu(menuAffichage, "Zoom -", e -> zoomArriereAction());
        ajouterElementDeMenu(menuAffichage, "Barre d’état",
        		e -> barreDEtatAction());
    }

    /**
     * Ajoute un élément de menu avec un ActionListener au menu spécifié.
     *
     * @param menu         Le menu auquel ajouter l'élément de menu.
     * @param nom          Le nom de l'élément de menu.
     * @param actionListener L'action à effectuer lorsque l'élément de menu est sélectionné.
     */
    private void ajouterElementDeMenu(JMenu menu, String nom, 
    		ActionListener actionListener) {
    	
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
    	
        // Code pour ouvrir un fichier (lecture et affichage dans zoneDeTexte)
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
        }
    }

    /**
     * Action à effectuer lorsque l'utilisateur clique sur "Sauvegarder".
     * Affiche un sélecteur de fichier pour sauvegarder un fichier.
     */
    private void sauvegarderAction() {
    	
        // Code pour sauvegarder un fichier (écriture du contenu de zoneDeTexte)
        JFileChooser fileChooser = new JFileChooser();
        
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
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
        // Code pour afficher/masquer la barre d'état encr a faire
    }

}
