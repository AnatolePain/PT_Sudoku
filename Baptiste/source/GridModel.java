/**
 * La classe <code>GridModel<code> représente la grille du sudoku avec un tableau de 9*9 objets de type CaseNumber
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class GridModel {
    private CaseNumber[][] array = new CaseNumber[9][9];
    private CaseNumber selectedCase;

    /**
     * initialise toutes les cases de l'objet GridModel avec des objets de type CaseNumber
     */
    public GridModel() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.array[i][j] = new CaseNumber();
            }
        }
    }

    /**
     * 
     * @param x coordonnée en abscisse de la case
     * @param y coordonnée en ordonnée de la case
     * @return retourne la valeur du numéro principal de la case ciblé par les coordonnées
     */
    public byte getCaseFirstNum(int x, int y) {
        return array[x][y].getFirstNum();
    }

    /**
     * 
     * @param x coordonnées en abscisse de la case
     * @param y coordonnées en ordonnee de la case
     * @param indice represente l indice du numéro secondaire (entre 0 et 3)
     * @return retourne la valeur du numéro secondaire de la case identifié par l indice et les coordonnées
     */
    public byte getCaseSubNum(int x, int y, int indice) {
        return array[x][y].getSubNum(indice);
    }

    /**
     * 
     * @param x coordonnées en abscisse de la case
     * @param y coordonnées en ordonnée de la case
     * @param value valeur a attribuer au numéro principale de la case identifié par les coordonnées
     */
    public void setCaseFirstNum(int x, int y, byte value) {
        if (isPossible(value, x, y)) {
            System.out.println("vrai!");
        }
        this.array[x][y].setFirstNum(value);
    }

    /**
     * 
     * @param x coordonnées en abscisse de la case
     * @param y coordonnées en ordonnée de la case
     * @param indice represente l'indice du numéro secondaire (entre 0 et 3)
     * @param value valeur à attribuer au numéro principale de la case identifié par l'indice et les coordonnées
     */
    public void setCaseSubNum(int x, int y, int indice, byte value) {
        this.array[x][y].setSubNum(indice, value);
    }

    /**
     * 
     * @param v la valeur à attribuer dans la case
     * @param x la position x (en cases) de la case
     * @param y la position y (en cases) de la case
     * @return true si le placement est possible, false sinon
     */
    private boolean isPossible(byte v, int x, int y) {
        int squareZonePositionX = (x/3)*3;
        int squareZonePositionY = (y/3)*3;
        int iteration = 0;
        for (int i = squareZonePositionX; i < squareZonePositionX+3; i++) {
            for (int j = squareZonePositionY; j < squareZonePositionY+3; j++) {
                System.out.print(this.getCaseFirstNum(i, j) + ",");
                if (this.getCaseFirstNum(i, j) == v) {
                    iteration++;
                }
            }
            System.out.println();
        }
        
        for (int i = 0; i < 9; i++) {
            if (this.getCaseFirstNum(x, i) == v) {
                iteration++;
            }
            System.out.println();
            System.out.print(this.getCaseFirstNum(x, i) + ",");
        }

        for (int j = 0; j < 9; j++) {
            if (this.getCaseFirstNum(j, y) == v) {
                iteration++;
            }
            System.out.println();
            System.out.print(this.getCaseFirstNum(j, y) + ",");
        }

        if (iteration != 0) {
            return false;
        }

        return true;
    }
}