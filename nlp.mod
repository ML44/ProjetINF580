#nlp.mod
param n;#nombre de mots
Set N = 0..n;
param word{N} symbolic;#ensemble des mots

param l integer, >0;#longueur de la phrase
Set L = 1..l;
var x1{L};#premier vers
var x2[L];#2e vers

param pieds{N} integer;#nombre de pieds de chaque mot
param rime{N} integer;#classe d'équivalence pour la rime
param valeur{N, N} integer;#fréquence à laquelle on a trouvé mot1, mot2

subject to nbrePieds1 : sum{i in L} pieds[x1[i]] = 12;
subject to nbrePieds2 : sum{i in L} pieds[x2[i]] = 12;
subject to rimeOK: rime[x1[l]] = rime[x2[l]];

maximize objective : sum{i in L} valeur[x1[i], x1[i+1]] + valeur[x2[i], x2[i+1]];