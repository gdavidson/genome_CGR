/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgUserInterface;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pkgChaosGameRepresentation.CGR;
import pkgChaosGameRepresentation.Data;
import pkgChaosGameRepresentation.FastaParser;
import pkgChaosGameRepresentation.Matrice;

/**
 *JPanel qui dessine la CGR.
 * @author Davidson
 */
public class PicturePanel extends JPanel {
    /**
     * Label qui contient la fréquence maximum
     */
    public JLabel maxLabel;
    /**
     * Label qui contient la fréquence minimum
     */
    public JLabel minLabel;
    /**
     * Methode qui scale une valeur entre 0 et 1.
     * @param num la valeur à échelonner
     * @param max la valeur maximale de l'échantillon
     * @param min la valeur minimale de l'échantillon
     * @return une valeur normalisée 
     */
    private double scaleBetween0And1(double num, double max, double min){
        double scalednum = (num-min)/(max-min);
        return scalednum;
    }
    

    /**
     * Override de la méthode paintcomponent pour dessiner la CGR.
     * @param g objet fourni par le système
     */
    @Override
    public void paintComponent(Graphics g) { 

        if (Data.sequence==null){;}
        else{
  //initialise la cgr
  CGR cgr =new CGR(Data.rank);
  //calcul les fréquences des bases si la correction de markov l'exige.
  if (Data.markov==1 || Data.markov==2){FastaParser.calculateBaseFrequencies();}
  //calcul les coordonées avec l'algorithme optimisé si la séquence l'exige.
  if (Data.rank>=3 && Data.sequence.length()>500000){cgr.calculateGenomicCGR();}
  //calcul les coordonées avec la méthode normale.
  else{cgr.calculateStandardCGR();}
  
  Matrice matrix = cgr.oligoMatrix;
  //taille latérale de la matrice necessaire pour calculer les dimensions des cases.
  int sidesize = (int) Math.pow(2, Data.rank);
  HashMap freqMap = cgr.frequencyMap; BufferedImage gradient = Data.gradient;int gradientHeight=gradient.getHeight()-1;
  String snuc=null; double freq=0; double maxfreq= cgr.getMaxFrequency(); double minfreq = cgr.getMinFrequency();
  NumberFormat df = new DecimalFormat("#0.0000");
  maxLabel.setText(df.format(maxfreq));minLabel.setText(df.format(minfreq));
  //parcours de la matrice pour le dessin
  for(int i = 0; i<matrix.nbLignes;i++ )
		{
                    for (int j=0; j<matrix.nbColonnes;j++){
                //récupération du motif.
		snuc = (String) matrix.values[i][j];
                //récupération de la fréquence.
                if (snuc==null && Data.markov!=2){ freq=0;minfreq=0;}
                if (snuc==null && Data.markov==2){freq=0; if(minfreq>0){minfreq=0;}}
                if(snuc!=null){freq = (double) freqMap.get(snuc);}
               //récupération de la couleur sur l'image du gradient selon la fréquence.
                double ratio = 1-scaleBetween0And1(freq,maxfreq,minfreq);
                double xt = gradientHeight*ratio; int x = (int) xt;
                g.setColor(new Color(gradient.getRGB(10, x)));
                //calcul des dimensions et coordonées de la case et remplissage avec la bonne couleur.
                Dimension d = getSize();
                int squarewidth = d.width/sidesize;
                int squareheight= d.height/sidesize;
                g.fillRect(j*squarewidth, i*squareheight, squarewidth, squareheight);
                    }	
                    
		}
        }
  }  
}
