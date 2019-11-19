Auteurs du projet : 
		MAURAND Pierre 21702704, L2 Informatique, Groupe 4B

Pour lancer cette application :
	
	Étape 1 - Après décompression du fichier, lancer un terminal unix a l'enplacement de ce README.txt
	Étape 2 - Taper dans ce terminal la commande : mkdir build
	Étape 3 - Éxécuter la commande : javac -d build src/*/*.java
	Étape 4 - Éxécuter la commande : java -cp build/ orchestration.Demo <taille X> <taille Y> <coup d'avance J1> <profondeur J1> <profondeur J2> <élagage>

Détails sur les arguments :

	Les joueurs possibles : Minmax et AlphaBeta (avec Random et human qui existe mais pas ajouter au code principale)

	Un élagage : mettre "oui" ou "non" pour avoir un élagage (jouer avec un MinMax pour "non" et AlphaBeta pour "oui")

Commandes spécifiques :
 
	Création du .jar :
		A la suite de l'étape 3, il est possible de créer un .jar de cette application, pour cela :
			on se place dans "build" avec la commande "cd build",
			puis éxécuter la commande : jar cfe "nom_du_jar".jar orchestration.Demo . 
			(le point de cette dernière commande qui se trouve à la fin est très important)

	Lancement du .jar :
		Afin de lancer le .jar, il faut se placer dans le dossier ou est situé le .jar 
		(normalement toujours dans le dossier build) puis éxécuter la commande : java -jar "nom_du_jar".jar (+les arguments)
	
	Création d'une javadoc expliquant notre projet : 
		Toujours dans le même dossier que le README.txt, exécuter la commande "mkdir doc" dans 
		un terminal, puis exécutez la commande : javadoc -d doc src/*/*.java
	
	Accéder à la javadoc :
		Se placer dans le dossier doc grâce à la commande "cd doc", puis lancer le fichier nommé "index.html"

Information sur le jeu :

	Règle du jeu :
		Ce jeu est un duel entre deux joueurs (human,random,minmax,alphabeta). Chacun leur tour, ils joues un coup jusqu'à la fin
		de la partie. Celui qui à le plus gros score gagne.
		
	Mouvements possibles :
		Pour jouer à ce jeu, il est possible de déplacer un de ces pions sur une case vide à 2 de distance dans n'importe
		quelle direction. Il est aussi possible de dupliquer un pion dans une case vide à 1 de distance dans n'importe
		quelle direction. Ce second mouvement à pour conséquence de changer tout les pions qui le touche à 1 de distance en 
		sa couleur.

Enfin voici l'arborescence de notre application : 

	Infection (dossier)
		|
		|-->README.txt (fichier)
		|-->rapport (dossier)
		     |
		     |-->images (dossier)
				|-->illu1.png (fichier)
				|-->illu2.png (fichier)
		     |-->rapport.aux (fichier)
		     |-->rapport.log (fichier)
		     |-->rapport.pdf (fichier)
		     |-->rapport.synctex.gz (fichier)
		     |-->rapport.tex (fichier)
		     |-->rapport.toc (fichier)
		|-->src (dossier)
		     |
		     |-->orchestration (dossier)
		     |		|-->Demo.java (fichier)
		     |		|-->Move.java (fichier)
		     |		|-->State.java (fichier)
		     |-->players (dossier)
		     |	    |-->Humain.java (fichier)
		     |	    |-->MinMaxPlayer.java (fichier)
		     |      |-->RandomPlayer.java (fichier)
		     |      |-->AlphaBetaPlayer.java (fichier)
