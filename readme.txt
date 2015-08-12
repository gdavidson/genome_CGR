This software takes DNA sequence files(fasta format) as input and gives a visual representation of genomic signatures based on H.Joel Jeffrey's method (chaos game representation) .

Reference article:
- http://nar.oxfordjournals.org/content/18/8/2163.abstract

PDF: http://europepmc.org/backend/ptpmcrender.fcgi?accid=PMC330698&blobtype=pdf

Requires:
-JRE 1.7
-minimum screen resolution: 1280*1024

Known issues:
-Black picture if Markov correction is applied on a sequence containing undetermined bases (N).
-Coordinates (0,0) are top left, take it into consideration.


Installation:

Command line:
git clone https://github.com/gdavidson/genome_CGR.git
Or download and unzip:
https://github.com/gdavidson/genome_CGR/archive/master.zip


Run the program:

Go into the /dist folder and type:
java -jar runCGR.jar
or double-click runCGR.jar (windows).
