package players;

import orchestration.*;

public abstract class GamePlayer{
	private int avance = 0;
	/**
	 * Méthode pour choisir son coup (défini dans la classe du joueur)
	 * @param partie la classe du plateau de jeu
	 * @return le coup à jouer
	 */
	public abstract Move move(State partie);
	
	/**
	 * Méthode pour obtenir le nombre de noeuds visité
	 * @return le nombre de noeuds visité
	 */
	public int parcoursNoeuds(){
		return 0;
	}
	
	/**
	 * Méthode pour obtenir la valeur de l'avance
	 * @return l'avance
	 */
	public int getAvance(){
		return this.avance;
	}
	
	/**
	 * Méthode pour modifier la valeur de l'avance
	 * @param plus la valeur à ajouter
	 */
	public void setAvance(int plus){
		this.avance+=plus;
	}
	
}
