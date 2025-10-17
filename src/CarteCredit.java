import java.time.YearMonth;

public class CarteCredit {
    private String num;
    private YearMonth expiration;
    private double solde;

    public CarteCredit(String num, YearMonth expiration, double solde) {
        setNum(num);
        this.expiration = expiration;
        this.solde = solde > 0 ? solde : 0;
    }

    public String getNum() {
        return num;
    }

    private void setNum(String num) {
        if (num.matches("[0-9]{16}"))
            this.num = num;
    }

    public YearMonth getExpiration() {
        return expiration;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
