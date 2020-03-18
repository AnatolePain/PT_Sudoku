public class GridModel {
    private CaseNumber[][] array = new CaseNumber[9][9];
    private CaseNumber selectedCase;

    public GridModel() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                this.array[i][j] = new CaseNumber();
            }
        }
    }

    public byte getCaseFirstNum(int x, int y) {
        return array[x][y].getFirstNum();
    }

    public byte getCaseSubNum(int x, int y, int indice) {
        return array[x][y].getSubNum(indice);
    }

    public void setCaseFirstNum(int x, int y, byte value) {
        this.array[x][y].setFirstNum(value);
    }

    public void setCaseSubNum(int x, int y, int indice, byte value) {
        this.array[x][y].setSubNum(indice, value);
    }

}