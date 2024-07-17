package main;

import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

public class FenetreRechercheRemplacement extends JDialog{
	
	private JButton boutonSuivant;
	
	private JButton boutonPrecedent;
	
	private JButton boutonRemplacer;
	
	private JButton boutonRemplacerTout;
	
	private JTextField champRecherche;
	
	private JTextField champRemplacement;
	
	private JComboBox<String> recherchePrecedente;
	
	private JPanel panneauRechercheRemplacement;
	
	

	public FenetreRechercheRemplacement(JFrame fenetrePrincipale) {
		
		super(fenetrePrincipale,"Recherche et Remplacement",true);
		
		setSize(400,200);
		
		setLocationRelativeTo(fenetrePrincipale);
		
	
		creerBoutons();
		
		creerZoneTexte();
		
		creerListeDeroulante();
		
		creerPanneau();
		
		ajoutComposantsPanneau();
		
		add(panneauRechercheRemplacement);
		
	}

	private void ajoutComposantsPanneau() {
		
		
		panneauRechercheRemplacement.add(new JLabel("Recherche :"));  
        
		panneauRechercheRemplacement.add(champRecherche);  
        
        panneauRechercheRemplacement.add(new JLabel("Remplacement :"));  
        
        panneauRechercheRemplacement.add(champRemplacement);  
          
        
        panneauRechercheRemplacement.add(boutonSuivant);  
        
        panneauRechercheRemplacement.add(boutonPrecedent);  
        
        panneauRechercheRemplacement.add(boutonRemplacer);  
        
        panneauRechercheRemplacement.add(boutonRemplacerTout);  
        
        panneauRechercheRemplacement.add(new JLabel("Recherches précédentes :"));
        
        panneauRechercheRemplacement.add(recherchePrecedente);
		
	}

	private void creerPanneau() {
		
		panneauRechercheRemplacement = new JPanel(new GridLayout(5,2));
		
	}

	private void creerListeDeroulante() {
		
		recherchePrecedente = new JComboBox<>(); 
		
	}

	private void creerZoneTexte() {

		champRecherche = new JTextField(20);  
		
        champRemplacement = new JTextField(20);
		
	}

	private void creerBoutons() {
		
		boutonSuivant = new JButton("Suivant");
		
        boutonPrecedent = new JButton("Précédent");
        
        boutonRemplacer = new JButton("Remplacer"); 
        
        boutonRemplacerTout = new JButton("Remplacer tout");
		
		
		
		
	}
}
