/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgChaosGameRepresentation;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *Classe qui stocke les données nécessaires à la contruction et la représentation d'une matrice de type CGR.
 *
 * @author Davidson
 */
public class Data {
  /**
   * Séquence nucléotidique.
   */
    public static StringBuilder sequence = null;
   /**
    * ligne d'entête du fichier fasta contenant la description.
    */
    public static String entete = null;
    /**
     * Couleur de la représentation.
     */
    public static Color color = Color.RED;
    /**
     * Données du gradient.
     */
    public static BufferedImage gradient;
    /**
     * Rang de la matrice.
     */
    public static int rank=1;
    /**
     * Indice de Markov: 0 = pas de correction, 1 = rang 0, 2 = rang 1.
     */
    public static int markov = 0;
    /**
     * Fréquence d'une adénine dans la séquence.
     */
    public static double aFreq;
    /**
     * Fréquence d'une thymine dans la séquence.
     */
    public static double tFreq;
    /**
     * Fréquence d'une guanine dans la séquence.
     */
    public static double gFreq;
    /**
     * Fréquence d'une cytosine dans la séquence.
     */
    public static double cFreq;
    
}
