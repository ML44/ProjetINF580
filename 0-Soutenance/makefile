.PHONY: all view

all : rapport.pdf
	rm -f *.log *.aux *.out *.dvi *.bbl *.blg *.synctex.gz *.fdb_latexmk *.fls *.snm *.nav *.toc
	
rapport.pdf : clean
	pdflatex rapport.tex
	pdflatex rapport.tex
	open rapport.pdf

slides.pdf : clean
	pdflatex slides.tex
	pdflatex slides.tex
	open slides.pdf

clean :
	rm -f *.log *.aux *.out *.dvi *.bbl *.blg *.synctex.gz *.fdb_latexmk *.fls *.snm *.nav *.toc
