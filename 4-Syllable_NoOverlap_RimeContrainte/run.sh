cd dataGeneration
make
java normalisation $1
java index $1
java enchainement $1
java rimes $1
cd ..
ampl nlp.run