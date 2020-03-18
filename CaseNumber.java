public class CaseNumber {
    private byte firstNum;
    private byte[] subNum = new byte[4];
    private boolean locked;

    public CaseNumber() {
        this.locked = false;
    }

    public byte getFirstNum() {
        return this.firstNum;
    }

    public byte getSubNum(int indice) {
        return this.subNum[indice];
    }

    public void setFirstNum(byte value) {
        this.firstNum = value;
    }

    public void setSubNum(int indice, byte value) {
        this.subNum[indice] = value;
    }

    public void lockCase() {
        this.locked = true;
    }

    public void unlockCase() {
        this.locked = false;
    }
}