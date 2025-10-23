import java.time.YearMonth;

public class Transaction {
    private String placeStationnement;
    private int dureeMinutes;
    private double montantCredit;
    private double montantComptant;
    private double tauxHoraireCent;
    private CarteCredit carte = null;

    public Transaction(String placeStationnement, int tauxHoraireCent) {
        this.placeStationnement = placeStationnement;
        this.tauxHoraireCent = tauxHoraireCent;
        this.dureeMinutes = Borne.MIN_DUREE_PARKING;
        this.montantCredit = 0;
        this.montantComptant = 0;
    }

    public CarteCredit getCarte() {
        return carte;
    }
    public void setCarte(String num, YearMonth expiration) {
        carte = new CarteCredit(num, expiration, 1000);
    }

    public String getPlaceStationnement() {
        return placeStationnement;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }
    public void setDureeMinutes(int dureeMinutes) {
        if (dureeMinutes >= 0)
            this.dureeMinutes = dureeMinutes;
    }

    public double getMontantCredit() {
        return montantCredit;
    }
    public void setMontantCredit(double montantCredit) {
        this.montantCredit = montantCredit;
    }

    public double getMontantComptant() {
        return montantComptant;
    }
    public void setMontantComptant(double montantComptant) {
        this.montantComptant = montantComptant;
    }

    public double getMontant(){
        return montantComptant + montantCredit / 100;
    }

    public double getTauxHoraireCent() {
        return tauxHoraireCent;
    }
}
