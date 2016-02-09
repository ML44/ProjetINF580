README

But du programme :

Générer 2 vers de $n$ pieds qui riment.



Contraintes :

- Rimer : pour l'instant, on vérifie les 5 dernières lettres de 2 mots : si elle coincident, ils riment.

- Nombre de pieds
	

Pour chaque vers : 
1 tableau de taille 2*n (car possibles mots de 0 syllabes)

	
Mot vide : 
MotVide 
	indexé 0
 
	MotVide : taille 0

	Enchainement \forall Mot, MotVide - Mot = 0

.

dat :
	

param : index : mot := 
		
# mot numero_mot

	
param : rimes : mot rime :=

		# numero_mot rime


	param : syllabes : mot nb_syllabes :=
		
# numero_mot nb_syllabes

	
param : enchainement : mot1 mot2 valeur :=
		
# numero_mot1 numero_mot2 valeur
		
# valeur entière, mettre 0 pour les non existant !
		
# mettre tous les mot_vide mot_plein (poids nul)
		
# mettre tous les mot_plein mot_vide (poids très élevé dans le négatif)
		
# mot_vide mot_vide (poids nul)