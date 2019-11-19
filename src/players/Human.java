package players;

import orchestration.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Human extends GamePlayer{
	private String name;
	private int parcoursNoeuds=0;

	/**
	 * Constructeur pour initialiser l'attribut
	 * @param name le nom choisi pour le joueur
	 */
	public Human(String name){
		this.name = name;
	}

	/**
	 * Méthode pour choisir le coup à jouer
	 * @param partie la classe du plateau de jeu
	 * @return le mouvement choisi à jouer
	 */
	@Override
	public Move move(State partie){
		Scanner scan = new Scanner(System.in);
		List<Move> coupPossible = partie.get_Moves(partie.getEnjeu());
		Move bon = null;
		if(coupPossible.get(0)!=null){
			while(bon==null){
				try{
					System.out.println("donne moi la valeur du pion que tu veux bouger avec X puis Y :");
					String a1 = scan.nextLine();
					String a2 = scan.nextLine();
					System.out.println("donne moi la valeur de la case ou tu aller avec X puis Y :");
					String b1 = scan.nextLine();
					String b2 = scan.nextLine();
					int a = Integer.parseInt(a1);
					int b = Integer.parseInt(b1);
					int a3 = Integer.parseInt(a2);
					int b3 = Integer.parseInt(b2);
					for(Move test : coupPossible){
						if(test.source == (a+a3*partie.tailleY) && test.destination == (b+b3*partie.tailleY)){
							bon = test;
						}
					}
					if(bon==null){
						System.out.println("Ton coups n'est pas possible, recommence ...");
					}
				}catch(NumberFormatException error){
					System.out.println("des chiffres please !!");
				}
			}
		}
		return(bon);
	}
	
	/**
	 * Méthode pour obtenir le nom du joueur
	 * @return le nom du joueur
	 */
	public String toString(){
		return this.name;
	}
}
