JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
CLASSES = \
	normalisation.java \
	index.java \
	enchainement.java \
	rimes.java \
	syllabes.java \
	
default: classes

.java.class:
	$(JC) $(JFLAGS) $*.java

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

new: clean classes