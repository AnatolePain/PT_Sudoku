package source;

/**
 * La classe <code>CaseNumber<code> sert a stocker les valeurs d'un element de la grille du sudoku,
 * elle est composé d un numéro principal et de quatre numéros secondaire
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class CaseNumber {
    private byte firstNum;
    private byte[] subNum = new byte[4];
    private boolean locker;

    /**
     * initialise l'instance de l objet en modifiable
     */
    public CaseNumber() {
        this.locker = false;
    }

    /**
     * 
     * @return retourne la valeur du numéro principale
     */
    public byte getFirstNum() {
        return this.firstNum;
    }

    /**
     * 
     * @param indice represente l'indice du numéro secondaire (entre 0 et 3)
     * @return retourne la valeur du numéro secondaire identifié par l indice
     */
    public byte getSubNum(int indice) {
        return this.subNum[indice];
    }

    /**
     * 
     * @param value valeur à attribuer au numéro principale
     */
    public void setFirstNum(byte value) {
        this.firstNum = value;
    }

    /**
     * 
     * @param indice représente l'indice du numéro secondaire (entre 0 et 3)
     * @param value valeur à attribuer au numéro secondaire identifié par l'indice
     */
    public void setSubNum(int indice, byte value) {
        this.subNum[indice] = value;
    }

    /**
     * rend la case non modifiable
     */
    public void lockCase() {
        this.locker = true;
    }

    /**
     * rend la case modifiable
     */
    public void unlockCase() {
        this.locker = false;
    }

    /**
     * @return renvoie l'état de la case (true non modifiable, false modifiable)
     */
    public boolean getLocker() {
        return this.locker;
    }
}