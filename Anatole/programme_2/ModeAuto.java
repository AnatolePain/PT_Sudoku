public class ModeAuto {


    //gère l'affichage
	public static void affichage (int grille[][])
	{
	    for (int i = 0; i < 9; i++)
	    {
	        for (int j = 0; j < 9; j++)
	        {
	        	System.out.print(grille[i][j]+", ");
	            
	        }
	        System.out.println();
	    }
	   System.out.println();
	   System.out.println();
	}

	//renvoi true si l'entier k n'est pas sur la ligne
	public static boolean absentSurLigne (int k, int grille[][], int i)
	{
	    for (int j=0; j < 9; j++)
	        if (grille[i][j] == k)
	            return false;
	    return true;
	}

	//renvoi true si l'entier k n'est pas sur la colonne 
	public static boolean absentSurColonne (int k, int grille[][], int j)
	{
	    for (int i=0; i < 9; i++){
	        if (grille[i][j] == k){
	            return false;
	        }
	    }
	    return true;
	}

	//revoie true si l'entier k n'est pas dans le bloc de 9 case 
	//prend en paramètre les coordonnées du bloc 
	public static boolean absentSurBloc (int k, int grille[][], int i, int j)
	{
	    int _i = i-(i%3), _j = j-(j%3);  // ou encore : _i = 3*(i/3), _j = 3*(j/3);
	    for (i=_i; i < _i+3; i++)
	        for (j=_j; j < _j+3; j++)
	            if (grille[i][j] == k)
	                return false;
	    return true;
	}


	public static boolean automatique (int grille[][], int position){

		//si on arrive a la position 81 on arrête le programme
	    if (position == 81){
	        return true;
	    }


	    //exemple position 56 = coordonné (2;6)
	    //i = 56/9 = 6  ;  j = 56%9 = 2
	    int i = position/9, j = position%9;

	    //verifie si la case est egale a zero
	    if (grille[i][j] != 0){
	        return estValide(grille, position+1);
	    }

	    //test si sur toute cases les 9 numéro (de 1 à 9) sur chaque case
	    for (int k = 1; k <= 9; k++)
	    {
	    	//verifie si k n'est pas a la fois sur sa ligne, sa collonne , on son bloc 
	        if (absentSurLigne(k,grille,i) && absentSurColonne(k,grille,j) && absentSurBloc(k,grille,i,j))
	        {
	            grille[i][j] = k;

	            //p=81 (i,j)=k ;

	            System.out.println(" p="+position+" ("+i+","+j+")="+k);

	            if ( estValide (grille, position+1) ){
	                return true;
	            }
	        }
	    }
	    grille[i][j] = 0;

	    System.out.println("CHECK: " + position);

	    return false;
	}

	public static void main(String[] args)
	{
	    /*int grille[][] =
	    {
	        {9,0,0,1,0,0,0,0,5},
	        {0,0,5,0,9,0,2,0,1},
	        {8,0,0,0,4,0,0,0,0},
	        {0,0,0,0,8,0,0,0,0},
	        {0,0,0,7,0,0,0,0,0},
	        {0,0,0,0,2,6,0,0,9},
	        {2,0,0,3,0,0,0,0,6},
	        {0,0,0,2,0,0,9,0,0},
	        {0,0,1,9,0,4,5,7,0}
	    };*/

	    int grille[][] = {  {0 ,0 ,0 ,0 ,9 ,5 ,0 ,0 ,4 },
							{5 ,3 ,0 ,4 ,0 ,8 ,7 ,0 ,2 },
							{0 ,0 ,0 ,7 ,0 ,0 ,6 ,0 ,3 },
							{9 ,0 ,0 ,0 ,3 ,4 ,0 ,8 ,0 },
							{0 ,4 ,0 ,0 ,1 ,0 ,0 ,7 ,0 },
							{0 ,2 ,0 ,5 ,7 ,0 ,0 ,0 ,6 },
							{4 ,0 ,9 ,0 ,0 ,2 ,0 ,0 ,0 },
							{6 ,0 ,7 ,9 ,0 ,3 ,0 ,2 ,1 },
							{2 ,0 ,0 ,6 ,5 ,0 ,0 ,0 ,0 } };


	    System.out.println("Grille avant");
	    affichage(grille);

	    /*for(int i = 0 ; i < 9 ; i++ ){

	    	for(int j = 0 ; j < 9 ; j++ ){
	    		System.out.print(((i*9)+j)+", ");
	    	}
	    	System.out.println();
	    }
	    System.out.println();
	    System.out.println();*/

	    if(estValide(grille,0) == true){
	    	System.out.println("TRUE");
	    }else{
	    	System.out.println("FALSE");
	    }

	    System.out.println();
	    System.out.println();

	    System.out.println("Grille apres");
	    affichage(grille);
	}

}