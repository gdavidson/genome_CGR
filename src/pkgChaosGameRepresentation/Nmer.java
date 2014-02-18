
package pkgChaosGameRepresentation;

/**
 * Classe qui permet de modéliser un oligonucléotide de taille n ou "n-mère".
 * @author Davidson
 */
public class Nmer {
   /**
    *Sequence de l'oligomère
    */
    private String oligo;
   /**
    * Vecteur de coordonée de l'oligomère, coord[0]=x coord[1]=y
    */
    private int[] coord = new int[2];

    /**
     * Vecteur de coordonée d'une adénine.
     */
    private static final int[] vA = {0,0};
    /**
     * Vecteur de coordonée d'une cytosine.
     */
    private static final int[] vC = {0,1};
    /**
     * Vecteur de coordonée d'une thymine.
     */
    private static final int[] vT = {1,0};
    /**
     * Vecteur de coordonée d'une guanine.
     */
    private static final int[] vG = {1,1};
   /**
    * Constructeur avec 1 paramètre
    * @param oligo la séquence de l'oligomère.
    */
    public Nmer(String oligo) {
        this.oligo = oligo;
          }
    /**
     * Calcule et initialise le vecteur de coordonée du premier oligomère.
     * 
     * Le calcul se fait selon la formule 1.1 et doit uniquement être utilisé
     * pour le premier nucléotide si on travaille sur une séquence génomique.
     */
    public void setFirstNucCoord(){
        int m = oligo.length();
        int x = 0;
        int y = 0;
        for (int i=0;i<m;i++){
            String nucleotide = oligo.substring(i,i+1);
            switch(nucleotide){
                case "A" :
                    x+= Math.pow(2, i)*vA[0];
                    y+= Math.pow(2, i)*vA[1];
                    break;
                case "C" :
                    x+= Math.pow(2, i)*vC[0];
                    y+= Math.pow(2, i)*vC[1];
                    break;
                case "G" :
                    x+= Math.pow(2, i)*vG[0];
                    y+= Math.pow(2, i)*vG[1];
                    break;
                case "T" :
                    x+= Math.pow(2, i)*vT[0];
                    y+= Math.pow(2, i)*vT[1];
                    break;
            }
        }
      
        this.coord[0]=x; 
        this.coord[1]=y;
}
    /**
     * Calcule et initialise le vecteur de coordonée d'un oligo en fonction du
     * précédent.
     * 
     * Le calcul se fait avec la formule 1.3 et peut être utilisée uniquement
     * en fournissant l'oligomère précédent, cette méthode est à utiliser pour
     * gagner du temps en cas de travail sur de longues séquences.
     * 
     * @param lastNuc l'oligomère précédent
     */
 public void setNextNucCoord(Nmer lastNuc){
   int x = 0; int y = 0;
     int m = this.oligo.length();
     int[] firstCoord = lastNuc.getCoord();
     String nucleotide = this.oligo.substring(m-1);
     String nucleotideprec = lastNuc.getOligo().substring(0,1);
     int [] nucprec = getVector(nucleotideprec);
      
     
                switch(nucleotide){
                case "A" :
                    x=  (int) (firstCoord[0]+Math.pow(2, m)*vA[0]-nucprec[0]) >> 1;
                    y= (int) (firstCoord[1]+Math.pow(2, m)*vA[1]-nucprec[1]) >> 1;
                    break;
                case "C" :
                    x= (int) (firstCoord[0]+Math.pow(2, m)*vC[0]-nucprec[0]) >> 1;
                    y= (int) (firstCoord[1]+Math.pow(2, m)*vC[1]-nucprec[1]) >> 1;
                    break;
                case "G" :
                    x= (int) (firstCoord[0]+Math.pow(2, m)*vG[0]-nucprec[0]) >> 1;
                    y= (int) (firstCoord[1]+Math.pow(2, m)*vG[1]-nucprec[1]) >> 1;
                    break;
                case "T" :
                    x= (int) (firstCoord[0]+Math.pow(2, m)*vT[0]-nucprec[0]) >> 1;
                    y= (int) (firstCoord[1]+Math.pow(2, m)*vT[1]-nucprec[1]) >> 1;
                    break;
            }
            
            this.coord[0]=(int) x;
            this.coord[1]=(int) y;  
 }
 /**
  * Retourne le vecteur de coordonée d'une base.
  * @param nucleotide une base: A,T,G ou C sous forme de String
  * @return le vecteur de coordonées correspondant à un des vecteurs "static".
  */
  private static int[] getVector(String nucleotide){
        int[] returnvec = new int[2];         
     switch(nucleotide){
                case "A" :
                  returnvec = vA;
                    break;
                case "C" :
                  returnvec = vC;
                    break;
                case "G" :
                   returnvec = vG;
                    break;
                case "T" :
                  returnvec = vT;
                    break;
            }
     return returnvec;
 }
   /**
    * Retourne le vecteur de coordonée.
    * 
    * @return le vecteur de coordonée sous forme dun tableau à 2 entrées.
    */
    public int[] getCoord() {
        return coord;
    }
/**
 * Retourne la séquence.
 * 
 * @return la séquence de l'oligomère sous forme d'un String.
 */
    public String getOligo() {
        return oligo;
    }
   /**
    * calcule la fréquence d'un oligomère.
    * @param snuc un String qui représente l'oligomère
    * @param count le nombre d'occurences de l'oligomère
    * @param length la taille de la séquence
    * @param m la taille de l'oligomère
    * @param mar indice de markov, 0: pas de correction, 1: rang 0, 2 : rang 1 
    * @return la fréquence de l'oligomère sous forme d'un double.
    */
    public static double getFrequency(String snuc, int count, int length, int m,int mar){
       double freq = 0;
       //Calcul sans correction
       if (mar==0){
        freq = (double) count/(length-m+1);
       }
       //Calcul avec correction de rang 0
       if (mar==1){
           double fw= Nmer.getFrequency(snuc, count, length, m, 0);
           double gw = 1;
                   for (int i=0;i<m;i++){
            String nucleotide = snuc.substring(i,i+1);
            double freqbase=1;
            switch(nucleotide){
                case "A" :
                freqbase=Data.aFreq;
                    break;
                case "C" :
                freqbase=Data.cFreq;
                    break;
                case "G" :
                freqbase=Data.gFreq;
                    break;
                case "T" :
                freqbase=Data.tFreq;
                    break;
            }
            gw*=freqbase;
        }
        freq=fw/gw;
       }
       //Calcul avec correction de rang 1
       if (mar==2){
           double fw= Nmer.getFrequency(snuc, count, length, m, 0);
           double gw = 1;
           double vwt=1;
                   for (int i=0;i<m;i++){
            String nucleotide = snuc.substring(i,i+1);
            double freqbase=1;
            switch(nucleotide){
                case "A" :
                freqbase=Data.aFreq;
                    break;
                case "C" :
                freqbase=Data.cFreq;
                    break;
                case "G" :
                freqbase=Data.gFreq;
                    break;
                case "T" :
                freqbase=Data.tFreq;
                    break;
            }
            gw*=freqbase;
            vwt*=freqbase*(1-freqbase);
        }
         double vw =vwt/(length-m+1);
         freq=(fw-gw)*Math.pow(vw, -(1/2));
                                        
       }
        return freq;
        
    }
    
}
