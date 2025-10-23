import java.time.YearMonth;

public class CarteCredit {
    private String num;
    private YearMonth expiration;
    private double solde;

    public CarteCredit(String num, YearMonth expiration, double solde) {
        this.num = num;
        this.expiration = expiration;
        this.solde = solde > 0 ? solde : 0;
    }

    public double getSolde() {
        return solde;
    }
}
