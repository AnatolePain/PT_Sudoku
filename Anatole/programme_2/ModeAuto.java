public class ModeAuto {

	private GridModel gridModel;
	private PanelSudoku panSudo;
	private TimerVue temps;

	public ModeAuto (GridModel g, PanelSudoku s, TimerVue t) {
		gridModel = g;
		panSudo = s;
		temps = t;
	}


	public boolean resolution (int x, int y){

		temps.updateTimer();

		//si on arrive a la position 81 on arrête le programme
	    if (x == 9  || y == 9 ) {
	        return true;
		}

		//verifie si la case est egale a zero
	    if (gridModel.getCaseFirstNum(x, y) != 0){
			return resolution(avancementX(x),avancementY(x,y));
		}
		
	    //test si sur toutes les cases les 9 numéro (de 1 à 9) sur chaque case
	    for (byte k = 1; k <= 9; k++)
	    {
	    	//verifie si k n'est pas a la fois sur sa ligne, sa collonne , on son bloc 
	        if (gridModel.isPossible(k, x, y) == true)
	        {

				gridModel.setCaseFirstNum(x, y, k);
				panSudo.setScreenCase(x, y);

	            if ( resolution(avancementX(x),avancementY(x,y))){
					return true;
				}

	        }
		}

		gridModel.setCaseFirstNum(x, y, (byte)0);
		panSudo.setScreenCase(x , y);

	    return false;
	}

	private int avancementX(int x){

		if(x == 8){
			return 0;
		}else{
			x++;
			return x;
		}
			
	}

	private int avancementY(int x, int y){

		if( x == 8 ){
			y++;
			return y;
		}else{
			return y;
		}

	}

}