.PHONY: all view

all : simple.pdf
	rm -f *.log *.aux *.out *.dvi *.bbl *.blg *.synctex.gz *.fdb_latexmk *.fls *.snm *.nav *.toc *.vrb

simple.pdf : clean
	pdflatex simple.tex
	pdflatex simple.tex
	open simple.pdf

clean :
	rm -f *.log *.aux *.out *.dvi *.bbl *.blg *.synctex.gz *.fdb_latexmk *.fls *.snm *.nav *.toc
