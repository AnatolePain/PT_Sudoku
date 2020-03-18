/**
 * La classe <code>CaseNumber<code> sert a stocker les valeur d un element de la grille du sudoku,
 * elle est compose d un numero principal et de quatre numeros secondaire
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class CaseNumber {
    private byte firstNum;
    private byte[] subNum = new byte[4];
    private boolean locked;

    /**
     * initialise l instance de l objet en modifiable
     */
    public CaseNumber() {
        this.locked = false;
    }

    /**
     * 
     * @return retourne la valeur du numero principale
     */
    public byte getFirstNum() {
        return this.firstNum;
    }

    /**
     * 
     * @param indice represente l indice du numero secondaire (entre 0 et 3)
     * @return retourne la valeur du numero secondaire identifie par l indice
     */
    public byte getSubNum(int indice) {
        return this.subNum[indice];
    }

    /**
     * 
     * @param value valeur a attribuer au numero principale
     */
    public void setFirstNum(byte value) {
        this.firstNum = value;
    }

    /**
     * 
     * @param indice represente l indice du numero secondaire (entre 0 et 3)
     * @param value valeur a attribuer au numero secondaire identifie par l indice
     */
    public void setSubNum(int indice, byte value) {
        this.subNum[indice] = value;
    }

    /**
     * rend la case non modifiable
     */
    public void lockCase() {
        this.locked = true;
    }

    /**
     * rend la case modifiable
     */
    public void unlockCase() {
        this.locked = false;
    }
}