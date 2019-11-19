package players;

import orchestration.*;
import java.util.List;
import java.util.ArrayList;

public class AlphaBetaPlayer extends GamePlayer{
	private String name;
	private int profondeur;  //profondeur de recherche
	private int parcoursNoeuds=0;

	/**
	 * Constructeur qui initialise nos deux attributs
	 * @param k profondeur de l'alphabeta
	 */
	public AlphaBetaPlayer(int k,int avance){
		setAvance(avance);
		this.name = "Joueur AlphaBeta #" + this.hashCode();
		this.profondeur = k;
	}
	
	/**
	 * Méthode pour choisir le coup
	 * @param partie la class du tableau de jeu
	 * @return meilleur coup choisi par l'algo de l'alphabeta
	 */
	@Override
	public Move move(State partie){
		return alphabeta(partie,partie.getEnjeu());
	}
	
	/**
	 * Méthode de l'alphabeta pour avoir le meilleur coup avec le moins de vérification
	 * @param partie la class du tableau de jeu
	 * @param joueur le joueur qui dois jouer
	 * @return le meilleur coup à jouer
	 */
	public Move alphabeta(State partie,GamePlayer joueur){
		Move meilleurCoup = null;
        int meilleureValeur = Integer.MIN_VALUE;
        // --- evaluation du coup avec le meilleur score selon une profondeur --- //
		for (Move c:partie.get_Moves(joueur)){
			this.parcoursNoeuds+=1;
			State copy = partie.getCopy();
			copy.play(c);
			copy.setenjeuplayer();
			// --- évalution du score max de ce coup selon une profondeur --- //
			int v = -evaluer(copy,copy.getEnjeu(),this.profondeur-1,Integer.MIN_VALUE,Integer.MAX_VALUE);
			if (v > meilleureValeur){
				meilleureValeur = v;
				meilleurCoup = c;
			}
		}
        return(meilleurCoup);
	}
	
	/**
	 * Méthode pour obtenir le score du meilleur coup selon une profondeur fixée
	 * @param partie la class du tableau de jeu
	 * @param joueur le joueur qui dois jouer
	 * @param profond la profondeur qui reste à faire
	 * @param alpha l'alpha de notre algo
	 * @param beta le beta de notre algo
	 * @return le meilleur coup à jouer pour notre alphabeta
	 */
	public int evaluer(State partie, GamePlayer joueur, int profond,int alpha, int beta){
		if(partie.isTerminal()==true){
			if(partie.victory() == joueur){
				return Integer.MAX_VALUE;
			}
			if(partie.victory() != null){
				return -Integer.MAX_VALUE;
			}
			return 0;
		}
		if(profond<=0){
            return partie.getScore(joueur);
        }
        else{
			for(Move c : partie.get_Moves(joueur)){
				this.parcoursNoeuds+=1;
				State copy = partie.getCopy();
				copy.play(c);
				copy.setenjeuplayer();
				alpha = Math.max(alpha,-evaluer(copy,copy.getEnjeu(),profond-1,-beta,-alpha));
				if (alpha>=beta){
					return alpha;
				}
			}
		}
		return alpha;
	}
	
	/**
	 * Méthode pour obtenir le nombre de noeuds visité
	 * @return le nombre de noeuds visité
	 */
	@Override
	public int parcoursNoeuds(){
		return this.parcoursNoeuds;
	}

	/**
	 * Méthode pour obtenir le nom du joueur alphabeta
	 * @return le nom du joueur alphabeta
	 */
	public String toString(){
		return this.name;
	}
	
}
