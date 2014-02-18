/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgUserInterface;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import pkgChaosGameRepresentation.Data;

/**
 *JPanel qui contient le gradient.
 * @author gosush
 */
public class GradientPanel extends JPanel {
    /**
     * Override de la méthode paintcomponent pour dessiner le gradient.
     * @param g objet fourni par le système
     */
        @Override
    public void paintComponent(Graphics g) {
         
                Graphics2D g2d = (Graphics2D)g;
                Color col = Data.color;
                GradientPaint gp = new GradientPaint(0, 0, Color.BLACK, 0, getHeight(), col);                
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
   
        }
        /**
         * Renvoi le dessin du gradient.
         * @return l'image du gradient.
         */
        public BufferedImage getImage(){
        
        int width = this.getWidth();
        int height = this.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        this.paintAll(g);
        g.dispose();        
        return image;
    }

}
