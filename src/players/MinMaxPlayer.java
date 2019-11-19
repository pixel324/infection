package players;

import orchestration.*;
import java.util.List;
import java.util.ArrayList;

public class MinMaxPlayer extends GamePlayer{
	private String name;
	private int profondeur;  //profondeur de recherche
	private int parcoursNoeuds=0;

	/**
	 * Constructeuur qui initialise nos deux attributs
	 * @param k la profondeur de recherche (coup d'avance)
	 */
	public MinMaxPlayer(int k,int avance){
		setAvance(avance);
		this.name = "Joueur MinMax #" + this.hashCode();
		this.profondeur = k;
	}
	
	/**
	 * Méthode pour choisir le coup
	 * @param partie la classe du tableau de jeu
	 * @return meilleur coup choisi par negamax
	 */
	@Override
	public Move move(State partie){
		return negamax(partie,partie.getEnjeu());
	}
	
	/**
	 * Méthode du négamax pour avoir le meilleur coup
	 * @param partie la classe du plateau de jeu
	 * @param joueur qui est le joueur qui dois jouer
	 */
	public Move negamax(State partie, GamePlayer joueur){
        Move meilleurCoup = null;
        int meilleureValeur = Integer.MIN_VALUE;
        // --- evaluation du coup avec le meilleur score selon une profondeur --- //
		for (Move c:partie.get_Moves(joueur)){
			this.parcoursNoeuds+=1;
			State copy = partie.getCopy();
			copy.play(c);
			copy.setenjeuplayer();
			// --- évalution du score max de ce coup selon une profondeur --- //
			int v = -evaluer(copy,copy.getEnjeu(),this.profondeur-1);
			if (v > meilleureValeur){
				meilleureValeur = v;
				meilleurCoup = c;
			}
		}
        return(meilleurCoup);
    }
    
    /**
     * Méthode d'évaluation du meilleur score à la profondeur k
     * @param a la classe du tableau de jeu
     * @param b le joueur qui dois jouer
     * @param profond la profondeur actuel de recherche
     */
    public int evaluer(State a,GamePlayer b, int profond){
		// --- en cas de fin de jeu --- //
		if(a.isTerminal()==true){
			if(a.victory() == b){
				return Integer.MAX_VALUE;
			}
			if(a.victory() != null){
				return -Integer.MAX_VALUE;
			}
			return 0;
		}
		// --- si nous somme à la dernière profondeur --- //
		if(profond<=0){
			return a.getScore(b);
		}
		// --- sinon on fait l'évaluation du meilleur score à la profondeur en dessous --- //
		else{
            int meilleureValeur = Integer.MIN_VALUE;
            for(Move coup: a.get_Moves(b)){
				this.parcoursNoeuds+=1;
                State copy = a.getCopy();
                copy.play(coup);
                copy.setenjeuplayer();
                int v = -evaluer(copy,copy.getEnjeu(),profond-1);
                meilleureValeur = Math.max(v,meilleureValeur);
            }
            return meilleureValeur;
        }
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
	 * Méthode pour obtenir le nom du joueur minmax
	 * @return le nom du joueur minmax
	 */
	public String toString(){
        return this.name;
    }
}
