public class Transaction {
    private String placeStationnement;
    private int dureeMinutes;
    private double montant;
    private double tauxHoraireCent;

    private static final int MAX_DUREE_PARKING = 120;
    private static final int MIN_DUREE_PARKING = 0;
    private static final int INTERVAL_MINUTES = 15;

    public Transaction(String placeStationnement, int tauxHoraireCent) {
        this.placeStationnement = placeStationnement;
        this.tauxHoraireCent = tauxHoraireCent;
        this.dureeMinutes = MIN_DUREE_PARKING;
        this.montant = 0;
    }

    public String getPlaceStationnement() {
        return placeStationnement;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    public void ajouterQuinzeMinutes(){
        if (this.dureeMinutes < MAX_DUREE_PARKING){
            this.dureeMinutes += INTERVAL_MINUTES;
            this.montant += (this.tauxHoraireCent / 4) / 100;
        } else
            System.out.println("Erreur ! Durée maximale de parking est de " + MAX_DUREE_PARKING + " minutes!");
    }

    public void setDureeParkingToMax(){
        this.dureeMinutes = MAX_DUREE_PARKING;
        this.montant = (this.tauxHoraireCent * 2 ) / 100;
    }

    public void insererPiece(Piece p){
        this.montant += (p.getCentValue() / 100);
        if ((this.dureeMinutes + (p.getCentValue() * 60) / tauxHoraireCent) < MAX_DUREE_PARKING) {
            this.dureeMinutes += (p.getCentValue() * 60) / tauxHoraireCent;
        } else {
            this.dureeMinutes = 120;
            System.out.println("Erreur ! Durée maximale de parking est de " + MAX_DUREE_PARKING + " minutes!");
        }
    }

    public void retirerQuinzeMinutes(){
        if (this.dureeMinutes >= MIN_DUREE_PARKING){
            this.dureeMinutes -= INTERVAL_MINUTES;
            this.montant -= (this.tauxHoraireCent / 4) / 100;
        } else
            System.out.println("Erreur ! Durée minimale de parking est de " + MIN_DUREE_PARKING + " minute!");
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
