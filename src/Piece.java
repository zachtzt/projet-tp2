public class Piece {
    private int centValue;

    public Piece(int centValue) {
        setCentValue(centValue);
    }
    public int getCentValue() {
        return centValue;
    }

    private void setCentValue(int centValue) {
        if (centValue == 25 || centValue == 100 || centValue == 200)
            this.centValue = centValue;
    }
}
