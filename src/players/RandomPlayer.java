package players;

import orchestration.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends GamePlayer{
	private String name;
	private int parcoursNoeuds=0;

	/**
	 * Constructeur qui initialise le nom de ce joueur random
	 */
	public RandomPlayer(){
		this.name = "Joueur aléatoire #" + this.hashCode();
	}
	
	/**
	 * Méthode pour prendre un coup possible au hasard et le renvoyer
	 * @param partie la classe du tableau de jeu
	 * @return un mouvement possible
	 */
	@Override
	public Move move(State partie){
		Random gen = new Random();
		Move ok = null;
		int b = gen.nextInt(partie.get_Moves(partie.getEnjeu()).size());
		ok = partie.get_Moves(partie.getEnjeu()).get(b);
		if(ok == null){
			System.out.println("Tu ne peux pas jouer");
		}
		else{
			System.out.println("donne moi la valeur du pion que tu veux bouger :");
			System.out.println(ok.source);
			System.out.println("donne moi la valeur de la case ou tu aller :");
			System.out.println(ok.destination);
		}
		return(ok);
	}

	/**
	 * Méthode pour obtenir le  nom du joueur random
	 * @return nom u joueur random
	 */
	public String toString(){
		return this.name;
	}
}
