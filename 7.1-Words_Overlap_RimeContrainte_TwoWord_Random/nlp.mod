#nlp.mod
param n;#nombre de mots
set N = 0..n;
param index{N} symbolic;#ensemble des mots
param lastWord integer;#dernier mot décidé

param alea1{N} binary;#used to forbid the use of random words as mot1
param alea2{N} binary;#used to forbid the use of random words as mot2

param LongueurMax integer, >= 0;#nombre de syllabes autorisé

param syllabes{N} integer, default 0;#nombre de pieds de chaque mot
param rimes{N} integer;#classe d'équivalence pour la rime
param enchainement{N, N} integer, default 0;#fréquence à laquelle on a trouvé mot1, mot2

#variables pour les 3 mots à ajouter
var mot1{N} binary, default 0;#mot[i] = 1 ssi le mot choisi est le mot i
var mot2{N} binary, default 0;

#variables pour linéariser la fonction objectif
var M12{N, N} binary, default 0;#mot1[i]*mot2[j] = M12[i, j]

#ne pas dépasser le nombre de syllabes permis
subject to NotTooMuchSyllables : sum{i in N} syllabes[i] * (mot1[i] + mot2[i]) <= LongueurMax;

#empêcher l'usage du mot vide si on doit encore rajouter des syllabes, et forcer son usage si on n'a plus de syllabes
subject to EmptyWordUse1_1 : mot1[0] * LongueurMax = 0;
subject to EmptyWordUse2_1 : mot1[0] + LongueurMax >= 1;
subject to EmptyWordUse1_2 : (mot2[0] * LongueurMax) - (sum{i in N} syllabes[i] * M12[i, 0]) = 0;
subject to EmptyWordUse2_2 : mot2[0] + (LongueurMax - sum{i in N} syllabes[i] * mot1[i]) >= 1;

#empêcher 2 mots de zéro syllabes de se suivre s'il reste des syllabes
subject to No2ZeroSyllablesWords1 : sum{i in N} mot1[i]*syllabes[i] + syllabes[lastWord] >= 1 - mot1[0];
subject to No2ZeroSyllablesWords2 : sum{i in N} mot2[i]*syllabes[i] + sum{i in N} mot1[i]*syllabes[i] >= 1 - mot2[0];

#ne sélectionner qu'un seul mot
subject to OnlyOneWord1 : sum{i in N} mot1[i] = 1;
subject to OnlyOneWord2 : sum{i in N} mot2[i] = 1;

#contraintes pour linéariser
subject to linearization1_12{i in N, j in N} : M12[i, j] <= mot1[i];
subject to linearization2_12{i in N, j in N} : M12[i, j] <= mot2[j];
subject to linearization3_12{i in N, j in N} : M12[i, j] >= mot1[i] + mot2[j] - 1;

maximize objective : (sum{i in N} enchainement[i, lastWord] * mot1[i] * alea1[i]) + sum{i in N, j in N} enchainement[i, j] * M12[i, j]  * alea2[j];