import java.time.YearMonth;

public class CarteCredit {
    private String num;
    private YearMonth expiration;
    private double solde;

    public CarteCredit(String num, YearMonth expiration, double solde) {
        this.num = num;
        this.expiration = expiration;
        this.solde = solde;
    }

    public String getNum() {
        return num;
    }

    private void setNum(String num) {
        this.num = num;
    }

    public YearMonth getExpiration() {
        return expiration;
    }

    public void setExpiration(YearMonth expiration) {
        this.expiration = expiration;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
