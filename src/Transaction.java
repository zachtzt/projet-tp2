public class Transaction {
    private String placeStationnement;
    private int dureeMinutes;
    private double montant;

    private static final int MAX_DUREE_PARKING = 120;
    private static final int MIN_DUREE_PARKING = 0;
    private static final int INTERVAL_MINUTES = 15;

    public Transaction(String placeStationnement) {
        this.placeStationnement = placeStationnement;
        this.dureeMinutes = MIN_DUREE_PARKING;
        this.montant = 0;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    public void ajouterQuinzeMinutes(){
        if (this.dureeMinutes < MAX_DUREE_PARKING){
            this.dureeMinutes += INTERVAL_MINUTES;

        }
        else
            System.out.println("Erreur ! Durée maximale de parking est de ");
    }
    public void retirerQuinzeMinutes(){
        if (this.dureeMinutes >= MIN_DUREE_PARKING)
            this.dureeMinutes -= INTERVAL_MINUTES;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
