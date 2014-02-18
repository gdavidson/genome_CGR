/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgChaosGameRepresentation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *Classe qui permet la représentation de type CGR, elle permet de modéliser les
 *données contenues dans une matrice.
 * @author Davidson
 */
public class CGR {

    /**
     * rang de la CGR
     */
    public int m;
    /**
     * HashMap qui permet de retrouver la fréquence d'un oligomère en fonction de sa séquence.
     */
    public HashMap frequencyMap = new HashMap();
    /**
     * Matrice qui contient les oligomères sous forme de String, elle peut se visualiser avec la méthode
     * affiche de la classe Matrice.
     */
    public Matrice oligoMatrix;
   /**
    *Constructeur avec un paramètre.
    * 
    * @param m le rang de la CGR (de 1 à 10)
    */
    public CGR(int m) {
        assert (m>0 && m<=10);
        
        this.m = m;
    }
    /**
     * Methode qui permet d'initialiser les HashMaps et la matrice pour les petites
     * séquences.
     */
    public void calculateStandardCGR(){
        StringBuilder sequence = Data.sequence;
        //initialise une hashmap de compteurs du nombre d'occurence.
        HashMap oligoCount = new HashMap();
        //initialise la matrice
        Matrice matrix = new Matrice((int) Math.pow(2, m),(int)Math.pow(2, m));
        //parcours de la séquence
        for(int i=0;i<sequence.length();i++){
           
           if (i+m>sequence.length()){break;}
           else{
            String snuc = sequence.substring(i,i+m);
            //création d'un objet de type Nmer
            Nmer nuc = new Nmer(snuc);
            //calcul des coordonées
            nuc.setFirstNucCoord();
            //incrémentation du compteur d'occurences.
         if (!oligoCount.containsKey(snuc)){
              oligoCount.put(snuc, 1);
          }
          else{
              int count= (int) oligoCount.get(snuc);
              count++;
              oligoCount.put(snuc, count);
                        }
          //stockage du string de l'oligo grâce aux coordonées calculées.
           int[] coord = nuc.getCoord();int x = coord[0];int y = coord[1];
           matrix.values[x][y]= snuc;
           
           }              
        }
        
        this.oligoMatrix=matrix;
        //parcours des compteurs et calcul de la fréquence et remplissage de la map de fréquence.
        Set keys = oligoCount.keySet();
        for (Iterator it = keys.iterator();it.hasNext();){
            String key = (String) it.next();
            int count = (int) oligoCount.get(key);
            double freq = Nmer.getFrequency(key, count, Data.sequence.length(), this.m, Data.markov);
            frequencyMap.put(key, freq);
            
        }
    }
    /**
     * Methode qui permet d'initialiser les HashMaps et la matrice pour les séquences de génome.
     * 
     */
    public void calculateGenomicCGR(){
        StringBuilder sequence = Data.sequence;
        //initialise une hashmap de compteurs du nombre d'occurence.
        HashMap oligoCount = new HashMap();
        //initialise un nucléotide à garder en mémoire pour calculer les coordonées du suivant.
        Nmer lastNuc=null;
        //initialise la matrice
        Matrice matrix = new Matrice((int) Math.pow(2, m),(int)Math.pow(2, m));
        //parcours de la séquence
        for(int i=0;i<sequence.length();i++){
           
           if (i+m>sequence.length()){break;}
           else{
            String snuc = sequence.substring(i,i+m);
            //création d'un objet de type Nmer
            Nmer nuc = new Nmer(snuc);
            //calcul des coordonées pour le premier nucléotide.
            if (lastNuc==null){
            nuc.setFirstNucCoord();
            lastNuc=nuc;
            }
            //calcul des coordonées pour les nucléotides suivants.
            else{
                nuc.setNextNucCoord(lastNuc);
                lastNuc=nuc;
            }
            //incrémentation du compteur d'occurences.
            if (!oligoCount.containsKey(snuc)){
              oligoCount.put(snuc, 1);
            }
            else{
              int count= (int) oligoCount.get(snuc);
              count++;
              oligoCount.put(snuc, count);
                        }
          //stockage du string de l'oligo grâce aux coordonées calculées.
           int[] coord = nuc.getCoord();int x = coord[0];int y = coord[1];
           if (x<matrix.nbLignes && y < matrix.nbColonnes){matrix.values[x][y]= snuc;}
           
           }              
        }
        this.oligoMatrix=matrix;
        //parcours des compteurs et calcul de la fréquence et remplissage de la map de fréquence.
      Set keys = oligoCount.keySet();
        for (Iterator it = keys.iterator();it.hasNext();){
       String key = (String) it.next();
       int count = (int) oligoCount.get(key);
       double freq = Nmer.getFrequency(key, count, Data.sequence.length(), this.m, Data.markov);
       frequencyMap.put(key, freq);
            
        }
    }
    /**
     * Retourne la fréquence maximum de la Map de fréquence.
     * @return la fréquence maximum
     */
    public double getMaxFrequency(){
        double maxfreq=0;
        Set keys = frequencyMap.keySet();
        for (Iterator it = keys.iterator();it.hasNext();){
            String key = (String) it.next();
            double freq = (double) frequencyMap.get(key);
            
            if (freq>maxfreq){maxfreq=freq;}
            }
        return maxfreq;
    }
    /**
     * Retourne la fréquence minimum de la Map de fréquence.
     * @return la fréquence minimum
     */
    public double getMinFrequency(){
        double minfreq=0;
        Set keys = frequencyMap.keySet();
        for (Iterator it = keys.iterator();it.hasNext();){
            String key = (String) it.next();
            double freq = (double) frequencyMap.get(key);
            if (minfreq==0){minfreq=freq;}
            if (freq<minfreq){minfreq=freq;}
            }
        return minfreq;
        
    }
    
}
