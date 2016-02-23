#nlp.mod
param n;#nombre de mots
set N = 0..n;
set presqueN = 1..n;
param index{N} symbolic;#ensemble des mots

param nbrePieds = 8;
param l integer = 2 * nbrePieds;#longueur de la phrase
set L = 1..l;
set presqueL = 1..(l-1);
set demiL = 1..(l/2);

param syllabes{N} integer, default 0;#nombre de pieds de chaque mot
param rimes{N} integer;#classe d'équivalence pour la rime
param enchainement{N, N} integer, default 0;#fréquence à laquelle on a trouvé mot1, mot2

var m1_1{L, N} binary, default 0;#m1[b, i] = 1 ssi le b-ième mot de la première moitié du vers 1 est i
var m1_2{L, N} binary, default 0;#m1[b, i] = 1 ssi le b-ième mot de la deuxième moitié du vers 1 est i
var m2_1{L, N} binary, default 0;#m2[b, i] = 1 ssi le b-ième mot de la première moitié du vers 2 est i
var m2_2{L, N} binary, default 0;#m2[b, i] = 1 ssi le b-ième mot de la deuxième moitié du vers 2 est i

#trick pour éviter les produits dans la fonction objectif : M[b, b+1, i, j] = m[b, i] * m[b+1, j]
var M1_1{L, L, N, N} binary, default 0;
var M1_2{L, L, N, N} binary, default 0;
var M2_1{L, L, N, N} binary, default 0;
var M2_2{L, L, N, N} binary, default 0;

#bon nombre de mots dans la phrase
subject to NombreDeMots1_1{b in L} : sum{i in N} m1_1[b, i] = 1;  
subject to NombreDeMots1_2{b in L} : sum{i in N} m1_2[b, i] = 1; 
subject to NombreDeMots2_1{b in L} : sum{i in N} m2_1[b, i] = 1;
subject to NombreDeMots2_2{b in L} : sum{i in N} m2_2[b, i] = 1;

#bon nombre de syllabes dans la phrase
subject to NombreDeSyllabes1_1 : sum{b in L} sum{i in N} m1_1[b, i] * syllabes[i] = nbrePieds;
subject to NombreDeSyllabes2_1 : sum{b in L} sum{i in N} m2_1[b, i] * syllabes[i] = nbrePieds;
subject to NombreDeSyllabes1_2 : sum{b in L} sum{i in N} m1_2[b, i] * syllabes[i] = nbrePieds;
subject to NombreDeSyllabes2_2 : sum{b in L} sum{i in N} m2_2[b, i] * syllabes[i] = nbrePieds;

#les 2 vers riment
subject to rimeOK : (sum{i in N} m1_2[l, i] * rimes[i]) = (sum{i in N} m2_2[l, i] * rimes[i]);

#empêcher 2 mots de zéro syllabes de se suivre
subject to AvoidZeroSyllabesWords1_1{b in presqueL} : m1_1[b, 0] + sum{i in presqueN} (m1_1[b, i] * syllabes[i] + m1_1[b + 1, i] * syllabes[i]) >= 1;
subject to AvoidZeroSyllabesWords2_1{b in presqueL} : m2_1[b, 0] + sum{i in presqueN} (m2_1[b, i] * syllabes[i] + m2_1[b + 1, i] * syllabes[i]) >= 1;
subject to AvoidZeroSyllabesWords1_2{b in presqueL} : m1_2[b, 0] + sum{i in presqueN} (m1_2[b, i] * syllabes[i] + m1_2[b + 1, i] * syllabes[i]) >= 1;
subject to AvoidZeroSyllabesWords2_2{b in presqueL} : m2_2[b, 0] + sum{i in presqueN} (m2_2[b, i] * syllabes[i] + m2_2[b + 1, i] * syllabes[i]) >= 1;

#empêcher le dernier mot d'être un mot de zéro syllabes
subject to AvoidZeroSyllabesLastWord1 : sum{i in N} m1_2[l, i]*syllabes[i] >= 1;
subject to AvoidZeroSyllabesLastWord2 : sum{i in N} m2_2[l, i]*syllabes[i] >= 1;

#contraintes d'overlap
subject to overlap1{b in demiL, i in N} : m1_1[(nbrePieds/2) + b, i] = m1_2[b, i];
subject to overlap2{b in demiL, i in N} : m2_1[(nbrePieds/2) + b, i] = m2_2[b, i];

#gestion de M pour linéariser l'objectif
subject to linearise_1_1_1{b in presqueL, i in N, j in N} : M1_1[b, b + 1, i, j] <= m1_1[b, i];
subject to linearise_1_2_1{b in presqueL, i in N, j in N} : M1_1[b, b + 1, i, j] <= m1_1[b+1, j];
subject to linearise_1_3_1{b in presqueL, i in N, j in N} : M1_1[b, b + 1, i, j] >= m1_1[b+1, j] + m1_1[b, i] - 1;
subject to linearise_2_1_1{b in presqueL, i in N, j in N} : M2_1[b, b + 1, i, j] <= m2_1[b, i];
subject to linearise_2_2_1{b in presqueL, i in N, j in N} : M2_1[b, b + 1, i, j] <= m2_1[b+1, j];
subject to linearise_2_3_1{b in presqueL, i in N, j in N} : M2_1[b, b + 1, i, j] >= m2_1[b+1, j] + m2_1[b, i] - 1;
subject to linearise_1_1_2{b in presqueL, i in N, j in N} : M1_2[b, b + 1, i, j] <= m1_2[b, i];
subject to linearise_1_2_2{b in presqueL, i in N, j in N} : M1_2[b, b + 1, i, j] <= m1_2[b+1, j];
subject to linearise_1_3_2{b in presqueL, i in N, j in N} : M1_2[b, b + 1, i, j] >= m1_2[b+1, j] + m1_2[b, i] - 1;
subject to linearise_2_1_2{b in presqueL, i in N, j in N} : M2_2[b, b + 1, i, j] <= m2_2[b, i];
subject to linearise_2_2_2{b in presqueL, i in N, j in N} : M2_2[b, b + 1, i, j] <= m2_2[b+1, j];
subject to linearise_2_3_2{b in presqueL, i in N, j in N} : M2_2[b, b + 1, i, j] >= m2_2[b+1, j] + m2_2[b, i] - 1;


maximize objective : sum{b in presqueL, i in N, j in N} enchainement[i, j] * (M1_1[b, b + 1, i, j] + M2_1[b, b + 1, i, j] + M1_2[b, b + 1, i, j] + M2_2[b, b + 1, i, j]);
