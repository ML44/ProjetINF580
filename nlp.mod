#nlp.mod
param n;#nombre de mots
set N = 0..n;
param index{N} symbolic;#ensemble des mots

param nbrePieds = 6;
param l integer = 2 * nbrePieds;#longueur de la phrase
set L = 1..l;
set presqueL = 1..(l-1);

param syllabes{N} integer, default 0;#nombre de pieds de chaque mot
param rimes{N} integer;#classe d'équivalence pour la rime
param enchainement{N, N} integer, default 0;#fréquence à laquelle on a trouvé mot1, mot2

var m1{L, N} integer, >=0, default 0;#m1[b, i] = 1 ssi le b-ième mot du vers 1 est i
var m2{L, N} integer, >=0, default 0;#m2[b, i] = 1 ssi le b-ième mot du vers 2 est i

subject to NombreDeMots1 : forall{b in L} sum{i in N} m1[b, i] = 1;  
subject to NombreDeMots2 : forall{b in L} sum{i in N} m2[b, i] = 1;
subject to NombreDeSyllabes1 : sum{b in L} sum{i in N} m1[b, i] * syllabes[i] = nbrePieds;
subject to NombreDeSyllabes2 : sum{b in L} sum{i in N} m2[b, i] * syllabes[i] = nbrePieds;
subject to rimeOK : (sum{i in N} m1[l, i] * rimes[i]) = (sum{i in N} m1[l, i] * rimes[i]);
subject to motFin1 : (sum{i in N} m1[l, i] * syllabes[i]) > 0;
subject to motFin2 : (sum{i in N} m2[l, i] * syllabes[i]) > 0;


maximize objective : sum{b in presqueL, i in N, j in N} enchainement[i, j] * (m1[b, i] * m1[b+1, j] + m2[b, i] * m2[b+1, j]);