#nlp.mod
param n;#nombre de mots
set N = 0..n;
param index{N} symbolic;#ensemble des mots

param l integer = 24;#longueur de la phrase
set L = 1..l;
#var x1{L};#premier vers
#var x2{L};#2e vers

param syllabes{N} integer, default 0;#nombre de pieds de chaque mot
param rimes{N} integer;#classe d'équivalence pour la rime
param enchainement{N, N} integer, default 0;#fréquence à laquelle on a trouvé mot1, mot2

var U1{N} integer, default 0;#nombre d'utilisation de chaque mot dans le vers 1
var U2{N} integer, default 0;#nombre d'utilisation de chaque mot dans le vers 2

var E1{N, N} integer, default 0;#E[i, j] = nombre d'enchainements mot i -> mot j
var E2{N, N} integer, default 0;

subject to nbrePieds1 : sum{i in N} syllabes[i] * U1[i] = 12;
subject to nbrePieds2 : sum{i in L} syllabes[i] * U2[i] = 12;
#subject to rimeOK : rimes[x1[l]] = rimes[x2[l]];
subject to nombreDeMots1 : sum{i in N, j in N} E1[i, j] = 11;
subject to nombreDeMots2 : sum{i in N, j in N} E2[i, j] = 11;

maximize objective : sum{i in N, j in N} enchainement[i, j] *(E1[i, j] + E2[i, j]);