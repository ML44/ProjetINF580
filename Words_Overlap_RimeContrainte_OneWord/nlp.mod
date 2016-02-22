#nlp.mod
param n;#nombre de mots
set N = 0..n;
param index{N} symbolic;#ensemble des mots
param lastWord integer;#dernier mot décidé

param LongueurMax integer, >= 0;#nombre de syllabes autorisé

param syllabes{N} integer, default 0;#nombre de pieds de chaque mot
param rimes{N} integer;#classe d'équivalence pour la rime
param enchainement{N, N} integer, default 0;#fréquence à laquelle on a trouvé mot1, mot2

var mot{N} binary, default 0;#mot[i] = 1 ssi le mot choisi est le mot i

#ne pas dépasser le nombre de syllabes permis
subject to NotTooMuchSyllables : sum{i in N} syllabes[i]*mot[i] <= LongueurMax;

#empêcher l'usage du mot vide si on doit encore rajouter des syllabes, et forcer son usage si on n'a plus de syllabes
subject to EmptyWordUse1 : mot[0]*LongueurMax = 0;
subject to EmptyWordUse2 : mot[0] + LongueurMax >= 1;

#empêcher 2 mots de zéro syllabes de se suivre s'il reste plus des syllabes
subject to No2ZeroSyllablesWords : sum{i in N} mot[i]*syllabes[i] + syllabes[lastWord] >= 1 - mot[0];

#ne sélectionner qu'un seul mot
subject to OnlyOneWord : sum{i in N} mot[i] = 1;

maximize objective : sum{i in N} enchainement[i, lastWord]*mot[i];