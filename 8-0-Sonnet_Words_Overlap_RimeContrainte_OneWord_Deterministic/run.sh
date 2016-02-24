cd dataGeneration
make
java normalisation $1
java index
java enchainement
java rimes
java syllabes
cd ..
ampl nlp.run