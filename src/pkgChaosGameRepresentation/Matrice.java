/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgChaosGameRepresentation;

/**
 *Classe qui modélise une matrice.
 * @author Davidson
 */
public class Matrice {
/**
 * Constructeur à deux paramètres.
 * @param nbLignes le nombre de lignes
 * @param nbColonnes le nombre de colonnes
 */
	public Matrice(int nbLignes, int nbColonnes) {
		super();
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		this.values = new Object[nbLignes][nbColonnes];
	}

       /**
         * Nombre de lignes
         */
	public int nbLignes;
        /**
         * Nombre de colonnes
         */
	public int nbColonnes;
        /**
         * Contenu de la matrice
         */
	public Object values [][];
        
        /**
         * Methode qui permet d'afficher la matrice dans la console.
         */
	public void affiche( )
	{
		for(int i = 0; i<this.nbLignes;i++ )
		{
                    for (int j=0; j<this.nbColonnes;j++)
			System.out.printf(" "+this.values[i][j]);
			System.out.println("");
		}
		
	}
        
	/**
         * Effectue une transposée de la matrice.
         * @return la matrice transposée.
         */
	public Matrice transpose()
	{
		Matrice tA = new Matrice(this.nbColonnes,this.nbLignes);
		for(int i = 0; i<this.nbLignes;i++ )
			for (int j=0; j<this.nbColonnes;j++)
				tA.values[i][j] = this.values[j][i];
		return tA;
	}
	/**
         * Copie la matrice.
         * @return une copie de la matrice.
         */
	public Matrice copy()
	{
		Matrice B = new Matrice(this.nbLignes,this.nbColonnes);
		B.values = this.values;
		return B;
	}
	/**
         * Ajoute deux matrices ensembles.
         * @param B une matrice à ajouter
         * @return une matrice dont les cases ont été additionées.
         */
	public Matrice add(Matrice B)
	{
		assert this.nbColonnes == B.nbColonnes;
		assert this.nbLignes == B.nbLignes;
		Matrice C = new Matrice(this.nbLignes,this.nbColonnes);
		for(int i = 0; i<this.nbLignes;i++ )
			for (int j=0; j<this.nbColonnes;j++)
				C.values[i][j] =(double) this.values[i][j]+ (double)B.values[i][j];
		return C;
	}   
}
