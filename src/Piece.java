public class Piece {
    private double centValue;

    public Piece(int centValue) {
        setCentValue(centValue);
    }
    public double getCentValue() {
        return centValue;
    }

    private void setCentValue(double centValue) {
        if (centValue == 25 || centValue == 100 || centValue == 200)
            this.centValue = centValue;
    }
}
