package orchestration;

import players.*;
import java.util.List;
import java.util.ArrayList;

public class Demo{

	/**
	 * Classe Principale de notre application avec la boucle de notre jeu
	 */
	public static void main(String[] args){
		boolean verrou = false;
		int x=0;
		int y=0;
		int profondeur1 = 0;
		int profondeur2 = 0;
		int avance = 0;
		
		// --- vérification sur le nombre d'argument --- //
        if (args.length != 6){
			verrou = true;
        }
        if (!args[5].toLowerCase().equals("oui") && !args[5].toLowerCase().equals("non")){
			verrou = true;
		}
        
        // --- message d'erreur si il y a une faute d'arguments --- //
        if (verrou == true){
			throw new IllegalArgumentException("Arguments mal mis");
		}
		
		// --- vérification des types d'arguments (autre que joueur) --- //
        else{
			try{
				x = Integer.parseInt(args[0]);
				y = Integer.parseInt(args[1]);
				avance = Integer.parseInt(args[2]);
				profondeur1 = Integer.parseInt(args[3]);
				profondeur2 = Integer.parseInt(args[4]);
			}catch(NumberFormatException e){
				throw new IllegalArgumentException("Faute de format sur un argument");
			}
		}

		GamePlayer J1 = null;
		GamePlayer J2 = null;
		
		// --- mise en place du premier joueur --- //
		if(args[5].toLowerCase().equals("non")){
			J1 = new MinMaxPlayer(profondeur1,avance);
		}else{
			J1 = new AlphaBetaPlayer(profondeur1,avance);
		}
		
		// --- mise en place du deuxième joueur --- //
		if(args[5].toLowerCase().equals("non")){
			J2 = new MinMaxPlayer(profondeur2,0);
		}else{
			J2 = new AlphaBetaPlayer(profondeur2,0);
		}
		
		// --- définition du plateau de jeu --- //
		State partie = new State(x,y,J1,J2);
		
		// --- lancement de la partie --- //
		System.out.println("|| --- LANCEMENT DU JEU --- ||"+ System.lineSeparator());
		System.out.println("! Rappel ! le premier joueur porte le symbol X \n");
		
		while(partie.isTerminal()==false){
			// --- affichage de début de tour --- //
			System.out.println(partie);
			System.out.println("à toi de jouer "+partie.getEnjeu());
			
			// --- choix du coup à jouer --- //
			Move b = partie.getEnjeu().move(partie);
			
			// --- execution du coup --- //
			partie.play(b);
			partie.getEnjeu().setAvance(-1);
			if(partie.getEnjeu().getAvance()<=0){
				partie.setenjeuplayer();
			}
						
			// --- message si il n'y a pas de coup a jouer --- //
			if (b==null){
				System.out.println("tu ne peux plus jouer");
			}
		}
		
		// --- affichage de la grille final --- //
		System.out.println(partie);
		
		// --- message en cas d'égalité --- //
		if (partie.victory()==null){
			System.out.println("égalité");
		}
		
		// --- message en cas de gagnant --- //
		else{
			System.out.println("Le gagnant est "+partie.victory()+" avec un score de : "+partie.getScore(partie.victory()));
		}
		
		// --- résultat du nombre de noeud visité par les joueurs --- //
		System.out.println("J1 : Nombre de noeud(s) visité par :"+J1+" "+J1.parcoursNoeuds());
		System.out.println("J2 : Nombre de noeud(s) visité par :"+J2+" "+J2.parcoursNoeuds());
	}
}
