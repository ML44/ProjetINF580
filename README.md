#Générateur de poèmes

##But du programme :

Générer des couples de vers de $n$ pieds qui riment.

##Contraintes :

- Rimer : pour l'instant, on vérifie les 5 dernières lettres de 2 mots : si elle coincident, ils riment.

- Nombre de pieds

##Spécifications

###Représentation des mots

Chaque mot est représenté par un entier (stocké dans index.dat).

###Tableaux

Pour chaque vers on stocke un tableau d'entiers de taille 2*n (car possibles mots de 0 syllabes).

	
###Le mot vide
- indexé 0
- taille 0
- enchainements :
	- mot_vide mot = 0
	- mot mot_vide = 1
	- mot_vide mot_vide = 1

##Contraintes

###Contraintes du problème

- Somme des syllabes = n
- Maximiser les probas d'enchaînements

###Contraintes de l'implémentation

- TODO ...

##.dat files
	
### index.dat

param : index : mot := 	

\# mot numero_mot

### syllabes.dat

param : syllabes : mot nb_syllabes :=

\# numero\_mot nb_syllabes

### rimes.dat	

param : rimes : mot rime :=

\# numero_mot rime

### enchainement.dat
	
param : enchainement : mot1 mot2 valeur :=
		
\# numero\_mot1 numero_mot2 valeur
		
\# NB : valeur entière, par fréquences

\# mettre 0 pour les enchainements sans occurence !
