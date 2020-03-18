/**
 * La classe <code>GridModel<code> represente la grille du sudoku avec un tableau de 9*9 objets de type <code>CaseNumber<code>
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class GridModel {
    private CaseNumber[][] array = new CaseNumber[9][9];
    private CaseNumber selectedCase;

    /**
     * initialise toute les cases de l objet <code>GridModel<code> avec des objets de type <code>CaseNumber<code>
     */
    public GridModel() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                this.array[i][j] = new CaseNumber();
            }
        }
    }

    /**
     * 
     * @param x coordonnee en abscisse de la case
     * @param y coordonnee en ordonnee de la case
     * @return retourne la valeur du numero principel de la case cibler par les coordonnees
     */
    public byte getCaseFirstNum(int x, int y) {
        return array[x][y].getFirstNum();
    }

    /**
     * 
     * @param x coordonnee en abscisse de la case
     * @param y coordonnee en ordonnee de la case
     * @param indice represente l indice du numero secondaire (entre 0 et 3)
     * @return retourne la valeur du numero secondaire de la case identifie par l indice et les coordonnees
     */
    public byte getCaseSubNum(int x, int y, int indice) {
        return array[x][y].getSubNum(indice);
    }

    /**
     * 
     * @param x coordonnee en abscisse de la case
     * @param y coordonnee en ordonnee de la case
     * @param value valeur a attribuer au numero principale de la case identifie par les coordonnees
     */
    public void setCaseFirstNum(int x, int y, byte value) {
        this.array[x][y].setFirstNum(value);
    }

    /**
     * 
     * @param x coordonnee en abscisse de la case
     * @param y coordonnee en ordonnee de la case
     * @param indice represente l indice du numero secondaire (entre 0 et 3)
     * @param value valeur a attribuer au numero principale de la case identifie par l indice et les coordonnees
     */
    public void setCaseSubNum(int x, int y, int indice, byte value) {
        this.array[x][y].setSubNum(indice, value);
    }

}