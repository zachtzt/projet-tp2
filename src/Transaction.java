import java.time.YearMonth;

public class Transaction {
    private String placeStationnement;
    private int dureeMinutes;
    private double montantCredit;
    private double montantComptant;
    private double tauxHoraireCent;
    private CarteCredit carte = null;

    private static final int MAX_DUREE_PARKING = 120;
    private static final int MIN_DUREE_PARKING = 0;
    private static final int INTERVAL_MINUTES = 15;

    public Transaction(String placeStationnement, int tauxHoraireCent) {
        this.placeStationnement = placeStationnement;
        this.tauxHoraireCent = tauxHoraireCent;
        this.dureeMinutes = MIN_DUREE_PARKING;
        this.montantCredit = 0;
        this.montantComptant = 0;
    }

    public void setCarte(CarteCredit carte) {
        this.carte = carte;
    }

    public String getPlaceStationnement() {
        return placeStationnement;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    public void ajouterQuinzeMinutes(){
        if (validerCarte(this.carte)){
            if (this.dureeMinutes < MAX_DUREE_PARKING) {
                this.dureeMinutes += INTERVAL_MINUTES;
                this.montantCredit += (this.tauxHoraireCent / 4) / 100;
            } else
                System.out.println("Erreur ! Durée maximale de parking est de " + MAX_DUREE_PARKING + " minutes!");
        }
    }

    public void setDureeParkingToMax(){
        if (validerCarte(this.carte)){
            this.dureeMinutes = MAX_DUREE_PARKING;
            this.montantCredit = (this.tauxHoraireCent * 2 ) / 100;
        }
    }

    public void insererPiece(Piece p){
        this.montantComptant += (p.getCentValue() / 100);
        if ((this.dureeMinutes + (p.getCentValue() * 60) / tauxHoraireCent) < MAX_DUREE_PARKING) {
            this.dureeMinutes += (p.getCentValue() * 60) / tauxHoraireCent;
        } else {
            this.dureeMinutes = 120;
            System.out.println("Erreur ! Durée maximale de parking est de " + MAX_DUREE_PARKING + " minutes!");
        }
    }

    public void retirerQuinzeMinutes(){
        if (validerCarte(this.carte)){
            if (this.dureeMinutes >= MIN_DUREE_PARKING){
                this.dureeMinutes -= INTERVAL_MINUTES;
                this.montantCredit -= (this.tauxHoraireCent / 4) / 100;
            } else
                System.out.println("Erreur ! Durée minimale de parking est de " + MIN_DUREE_PARKING + " minute!");
        }
    }

    public boolean validerCarte(CarteCredit c) {
        if (c != null) {
            if (YearMonth.now().isBefore(c.getExpiration())) {
                if (c.getSolde() >= this.montantCredit) {
                    return true;
                } else
                    System.out.println("Erreur ! Vous n'avez pas assez d'argent dans votre compte de crédit!");
            } else
                System.out.println("Erreur ! Votre carte est expirée!");
        } else
            System.out.println("Erreur ! Vous n'avez pas donné vos infomations de carte de crédit!");
        return false;
    }

    public double getMontantCredit() {
        return montantCredit;
    }

    public double getMontantComptant() {
        return montantComptant;
    }
}
