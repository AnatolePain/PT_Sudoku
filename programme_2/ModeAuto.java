/**
 * La classe <code>ModeAuto</Code> permet la résolution algorithmique de la grille sur un 
 * modele de backtracking
 * 
 * source: http://igm.univ-mlv.fr/~dr/XPOSE2013/sudoku/backtracking.html#principe
 * @version 0.1
 * @author Anatole Pain
 */
public class ModeAuto {

	private GridModel gridModel;
	private PanelSudoku panSudo;

	/**
	 * Constructeur faisant fait le liens entre lesGridModel et le PanelSudoku
	 * @param g est le GridModele
	 * @param s est le PanelSudoku
	 */
	public ModeAuto (GridModel g, PanelSudoku s) {
		this.gridModel = g;
		this.panSudo = s;
	}

	/**
	 * Résolution du sodoku en utilisant la récursivité
	 * 
	 * @param x cordonné x de la grille
	 * @param y cordonné y de la grille
	 * @return
	 */
	public boolean resolution (int x, int y){

		//si on arrive a la position 81 on arrête le programme
	    if (x == 9  || y == 9 ) {
	        return true;
		}

		//verifie si la case est egale a zero si oui on la passe 
	    if (this.gridModel.getCaseFirstNum(x, y) != 0){
			return resolution(avancementX(x),avancementY(x,y));
		}
		
	    //test si sur toutes les cases les 9 numéro (de 1 à 9) sur chaque case
	    for (byte k = 1; k <= 9; k++){

	    	//verifie si k n'est pas a la fois sur sa ligne, sa collonne , ou son bloc 
	        if (this.gridModel.isPossible(k, x, y) == true){

				//on affiche la case à l'écran 
				this.panSudo.setScreenCase(x , y);

				this.gridModel.setCaseFirstNum(x, y, k);
				this.panSudo.setScreenCase(x, y);

				/*on va de plus en plus profond dans la récursivité en réapelant resolution(),
				jusqu'à ce que ça soit false. On recule alors et celle d'avant return true */
	            if ( resolution(avancementX(x),avancementY(x,y))){
					return true;
				}

	        }
		}

		//si les numéro de 1 à 9 sont impossible sur une case :
		
		//on remet la case à 0
		this.gridModel.setCaseFirstNum(x, y, (byte)0);

		//on return false 
	    return false;
	}

	/**
	 * Gère l'augmentation du x pour avancer sur la grille ligne par ligne
	 * 
	 * @param x cordonné x de la grille 
	 * @return
	 */
	private int avancementX(int x){

		if(x == 8){
			return 0;
		}else{
			x++;
			return x;
		}
			
	}

	/**
	 * Gère l'augmentation du y pour avancer sur la grille ligne par ligne
	 * 
	 * @param x cordonné x de la grille 
	 * @param y cordonné y de la grille 
	 * @return
	 */
	private int avancementY(int x, int y){

		if( x == 8 ){
			y++;
			return y;
		}else{
			return y;
		}

	}

}