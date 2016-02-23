#nlp.mod
param n;#nombre de mots
set N = 0..n;
set presqueN = 1..n;
param index{N} symbolic;#ensemble des mots

param nbrePieds = 4;
param l integer = 2 * nbrePieds;#longueur de la phrase
set L = 1..l;
set presqueL = 1..(l-1);

#rime forcée
param lastWord1 integer;
param lastWord2 integer;

param syllabes{N} integer, default 0;#nombre de pieds de chaque mot
param rimes{N} integer;#classe d'équivalence pour la rime
param enchainement{N, N} integer, default 0;#fréquence à laquelle on a trouvé mot1, mot2

var m1{L, N} binary, default 0;#m1[b, i] = 1 ssi le b-ième mot du vers 1 est i
var m2{L, N} binary, default 0;#m2[b, i] = 1 ssi le b-ième mot du vers 2 est i

#trick pour éviter les produits dans la fonction objectif : M[b, b+1, i, j] = m[b, i] * m[b+1, j]
var M1{L, L, N, N} binary, default 0;
var M2{L, L, N, N} binary, default 0;

#on force le dernier mot
subject to LastWord1 : m1[l, lastWord1] = 1;
subject to LastWord2 : m2[l, lastWord2] = 1;

#bon nombre de mots dans la phrase
subject to NombreDeMots1{b in L} : sum{i in N} m1[b, i] = 1;  
subject to NombreDeMots2{b in L} : sum{i in N} m2[b, i] = 1;

#bon nombre de syllabes dans la phrase
subject to NombreDeSyllabes1 : sum{b in L} sum{i in N} m1[b, i] * syllabes[i] = nbrePieds;
subject to NombreDeSyllabes2 : sum{b in L} sum{i in N} m2[b, i] * syllabes[i] = nbrePieds;

#les 2 vers riment
subject to rimeOK : (sum{i in N} m1[l, i] * rimes[i]) = (sum{i in N} m1[l, i] * rimes[i]);

#empêcher 2 mots de zéro syllabes de se suivre
subject to AvoidZeroSyllabesWords1{b in presqueL} : m1[b, 0] + sum{i in presqueN} (m1[b, i] * syllabes[i] + m1[b + 1, i] * syllabes[i]) >= 1;
subject to AvoidZeroSyllabesWords2{b in presqueL} : m2[b, 0] + sum{i in presqueN} (m2[b, i] * syllabes[i] + m2[b + 1, i] * syllabes[i]) >= 1;

#empêcher le dernier mot d'être un mot de zéro syllabes
subject to AvoidZeroSyllabesLastWord1 : sum{i in N} m1[l, i]*syllabes[i] >= 1;
subject to AvoidZeroSyllabesLastWord2 : sum{i in N} m2[l, i]*syllabes[i] >= 1;

#gestion de M pour linéariser l'objectif
subject to linearise_1_1{b in presqueL, i in N, j in N} : M1[b, b + 1, i, j] <= m1[b, i];
subject to linearise_1_2{b in presqueL, i in N, j in N} : M1[b, b + 1, i, j] <= m1[b+1, j];
subject to linearise_1_3{b in presqueL, i in N, j in N} : M1[b, b + 1, i, j] >= m1[b+1, j] + m1[b, i] - 1;
subject to linearise_2_1{b in presqueL, i in N, j in N} : M2[b, b + 1, i, j] <= m2[b, i];
subject to linearise_2_2{b in presqueL, i in N, j in N} : M2[b, b + 1, i, j] <= m2[b+1, j];
subject to linearise_2_3{b in presqueL, i in N, j in N} : M2[b, b + 1, i, j] >= m2[b+1, j] + m2[b, i] - 1;


maximize objective : sum{b in presqueL, i in N, j in N} enchainement[i, j] * (M1[b, b + 1, i, j] + M2[b, b + 1, i, j]);
