package orchestration;

import players.*;

public class Move{
	public final boolean type;
	public final int source;
	public final int destination;

	/**
	 * Constructeur d'un mouvement avec son point de départ, son point d'arrivé, et son type de saut
	 * @param type qui est le booléen qui permet de savoir si c'est un saut ou non
	 * @param source qui est le point de départ du point
	 * @param destination qui est son point d'arrivé
	 */
	public Move(boolean type, int source, int destination){
		this.type = type;
		this.source = source;
		this.destination = destination;
	}
	
	/**
	 * @return Le String qui présente notre situation
	 */
	public String toString(){
		return "Mouvement de la case "+this.source+" à la case "+this.destination;
	}
}
