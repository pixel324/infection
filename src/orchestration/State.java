package orchestration;

import players.*;
import java.util.List;
import java.util.ArrayList;

public class State{
	private GamePlayer[][] board;
	private GamePlayer joueur1;
	private GamePlayer joueur2;
	private GamePlayer enjeu;
	public int tailleX, tailleY;

	/**
	 * Constructeur de la partie avec initialisation des attributs
	 */
	public State(int M, int N, GamePlayer joueur1, GamePlayer joueur2){
		// --- définition du tableau --- //
		this.board = new GamePlayer[N][M];
		// --- enregistrement des joueurs --- //
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.enjeu = joueur1;
		// --- placement des 2 pions --- //
		this.board[0][M-1] = joueur1;
		this.board[N-1][0] = joueur2;
		// --- enregistrement de la taille du tableau --- //
		this.tailleX = N;
		this.tailleY = M;
	}
	
	/**
	 * Méthode pour obtenir le score d'un joueur
	 * @param a joueur dont on veux avoir le score
	 * @return le nombre de pion du joueur
	 */
	public int getScore(GamePlayer a){
		int compt = 0;
		for(int i=0; i<this.tailleX;i++){
			for(int j=0; j<this.tailleY;j++){
				if(this.board[i][j]==a){
					compt = compt+1;
				}
			}
		}
		return compt;
	}
	
	/**
	 * Méthode pour savoir si le jeu est fini
	 * @return Si la parti est fini
	 */
	public boolean isTerminal(){
		boolean a = true;
		// --- vérifier si le tableau est rempli --- //
		for(int i=0; i<this.tailleX;i++){
			for(int j=0; j<this.tailleY;j++){
				if(this.board[i][j]==null){
					a = false;
				}
			}
		}
		// --- vérifier que aucun joueur n'est éliminé --- //
		if(getScore(this.joueur1)==0 || getScore(this.joueur2)==0){
			a = true;
		}
		return a;
	}
	
	/**
	 * Méthode de changement de joueur
	 */
	public void setenjeuplayer(){
		if(this.enjeu==this.joueur1){
			this.enjeu = this.joueur2;
		}
		else{
			this.enjeu = this.joueur1;
		}
	}
	
	/**
	 * Méthode pour obtenir le joueur gagnant ou null
	 * @return le joueur avec le plus grand score ou null
	 */
	public GamePlayer victory(){
		if(getScore(this.joueur1)>getScore(this.joueur2)){
			return this.joueur1;
		}
		if(getScore(this.joueur1)==getScore(this.joueur2)){
			return null;
		}
		return this.joueur2;
	}
	
	/**
	 * Méthode pour obtenir le joueur qui joue
	 * @return le joueur actuel
	 */
	public GamePlayer getEnjeu(){
		return this.enjeu;
	}
	
	/**
	 * Méthode pour obtenir le liste des mouvements qu'un joueur peut faire 
	 * @param joueur le joueur dont on veux avoir la liste de Moves possibles
	 * @return la liste des Moves possibles
	 */
	public List<Move> get_Moves(GamePlayer joueur){
		List<Move> a = new ArrayList<>();
		for(int i=0; i<this.tailleX;i++){
			for(int j=0; j<this.tailleY;j++){
				if(this.board[i][j]==joueur){
					int emplacement = i*this.tailleY+j;
					if(j-1>=0){
						if (this.board[i][j-1] == null){
							a.add(new Move(false,emplacement,emplacement-1));
						}
					}
					if(i-1>=0){
						if (this.board[i-1][j] == null){
							a.add(new Move(false,emplacement,emplacement-this.tailleY));
						}
					}
					if(j+1<this.tailleY){
						if (this.board[i][j+1] == null){
							a.add(new Move(false,emplacement, emplacement +1));
						}
					}
					if(i+1<this.tailleX){
						if (this.board[i+1][j] == null){
							a.add(new Move(false,emplacement,emplacement+this.tailleY));
						}
					}
					if(j-2>=0){
						if (this.board[i][j-2] == null){
							a.add(new Move(true,emplacement,emplacement-2));
						}
					}
					if(i-2>=0){
						if (this.board[i-2][j] == null){
							a.add(new Move(true,emplacement,emplacement-this.tailleY*2));
						}
					}
					if(j+2<this.tailleY){
						if (this.board[i][j+2] == null){
							a.add(new Move(true,emplacement, emplacement +2));
						}
					}
					if(i+2<this.tailleX){
						if (this.board[i+2][j] == null){
							a.add(new Move(true,emplacement,emplacement+this.tailleY*2));
						}
					}
				}
			}
		}
		// --- si il n'y a pas de coups --- //
		if (a.size() == 0){
			a.add(null);
		}
		return a;
	}

	/**
	 * Méthode qui joue le coup choisi
	 * @param a le Move à jouer
	 */
	public void play(Move a){
		if (a!=null){
			// --- emplacement du pion à poser --- //
			int x = a.destination/this.tailleY;
			int y = a.destination%this.tailleY;
			// --- poser un  pion --- //
			this.board[x][y] = this.enjeu;
			
			// --- si c'est une duplication, on va contaminer les pions en contacte --- //
			if (a.type==false){
				for(int mouve = -1; mouve<2;mouve=mouve+2){
					if(x-mouve>-1 && x-mouve<tailleX){
						if (this.board[x-mouve][y]!=this.enjeu && board[x-mouve][y]!=null){
							this.board[x-mouve][y]=this.enjeu;
						}
					}
					if(y-mouve>-1 && y-mouve<tailleY){
						if (this.board[x][y-mouve]!=this.enjeu && board[x][y-mouve]!=null){
							this.board[x][y-mouve]=this.enjeu;
						}
					}
				}
			}
			// --- si c'est un saut, on supprime le pion d'origine --- //
			if (a.type==true){
				this.board[a.source/this.tailleY][a.source%this.tailleY]=null;
			}
		}
	}

	/**
	 * Méthode pour faire une copie du jeu, pour les IA
	 * @return une copie parfaite du tableau de jeu avec les mêmes attributs
	 */
	// --- méthode pour faire une copie du jeu, pour les ia --- //
	public State getCopy(){
		State news = new State(this.tailleY,this.tailleX,this.joueur1,this.joueur2);
		news.enjeu = this.enjeu;
		for(int i=0;i<this.tailleX;i++){
			for(int j=0;j<this.tailleY;j++){
				news.board[i][j]=this.board[i][j];
			}
		}
		return news;
	}
	
	/**
	 * Méthode pour le graphisme en terminal
	 * @return une ligne de séparation pour le graphisme
	 */
	public String formebase(){
		String forme = " |";
		for (int ill = 0; ill<this.tailleY; ill++){
			forme = forme + "---|";
		}
		return forme;
	}
	
	/**
	 * Méthode d'affichage de la partie
	 * @return la représentation graphique du tableau de jeu
	 */
	public String toString(){
		String situation = "   ";
		String forme;
		for (int i=0;i<this.tailleY;i++){
			situation = situation + i+"   ";
		}
		situation = situation + System.lineSeparator();
		for (int i = 0; i<this.tailleX ; i++){
			situation = situation + formebase() + System.lineSeparator() + i+ "|";
			for (int j = 0; j<this.tailleY ; j++){
				if (this.board[i][j] == this.joueur1){
					situation = situation + " X |";
				}
				if (this.board[i][j] == this.joueur2){
					situation = situation + " 0 |";
				}
				if (this.board[i][j] == null){
					situation = situation + "   |";
				}
			}
			situation = situation + System.lineSeparator();
			if(i==this.tailleX-1){
				situation = situation + formebase() + System.lineSeparator();
			}
		}
	return situation;
	}
}
