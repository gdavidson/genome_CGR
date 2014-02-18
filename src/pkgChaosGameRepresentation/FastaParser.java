
package pkgChaosGameRepresentation;

import java.io.*;

/**
 *Classe qui permet de parcourir un fichier fasta et d'en extraire des données.
 * On peut principalement extraire la séquence et la ligne d'introduction, mais
 * également calculer la fréquence des différentes bases.
 * @author Davidson
 */
public class FastaParser {
  /**
   * Nom ou/et chemin d'accès du fichier fasta.
   */
   private String filename;
   /**
    *Constructeur avec un paramètre.
    * 
    * @param filename le nom ou/et chemin d'accès du fichier avec l'extension.
    */
    public FastaParser(String filename){
        this.filename = filename;
   
        }
    /**
     * Lit le fichier, extrait la séquence et l'entête qui sont stockées dans la classe "Data".
     * @throws FileNotFoundException
     * @throws IOException 
     */
 public void readData() throws FileNotFoundException, IOException{
             //lecture du fichier et stockage des lignes dans une ArrayList
        InputStream ips = new FileInputStream(filename);
	InputStreamReader ipsr = new InputStreamReader(ips);
	BufferedReader br = new BufferedReader(ipsr);
	
	String ligne;
	int numligne=0;
	String entetet=null;
        StringBuilder sequenceSB = new StringBuilder(10000);
	while ((ligne = br.readLine()) != null) {
	if (numligne==0) {
            entetet=ligne;
        }
        else {
            sequenceSB.append(ligne.trim().toUpperCase());
        }
	numligne++;
    }
    //récupération et initalisation de la séquence et de l'entête.   
  


    Data.sequence=sequenceSB;
    Data.entete=entetet;
 }
/**
 *Calcule la fréquence des quatres bases A,T,G et C.
 */    
public static void calculateBaseFrequencies(){
 //initialisation des quatre compteurs.
int adeninecountt=0;
int guaninecountt=0;
int cytosinecountt=0;
int thyminecountt=0;
StringBuilder sequence = Data.sequence;
//parcours de la séquence et incrémentation des différents compteurs.
for (int j=0;j<sequence.length();j++){
  
    String nucleotide = sequence.substring(j,j+1);
    if (nucleotide.equals("A")){
        adeninecountt++;
    }
    if (nucleotide.equals("C")){
        cytosinecountt++;
    }
    if (nucleotide.equals("T")){
        thyminecountt++;
    }
    if (nucleotide.equals("G")){
        guaninecountt++;
    }
}
//calcul de la fréquence.
Data.aFreq= (double) adeninecountt/sequence.length();
Data.cFreq= (double) cytosinecountt/sequence.length();
Data.gFreq= (double) guaninecountt/sequence.length();
Data.tFreq= (double) thyminecountt/sequence.length();
    
}
   /**

   /**
    *Retourne le nom du fichier fasta
    * 
    * @return le nom du fichier
    */
    public String getFilename() {
        return filename;
    }

}
