import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;

public class Borne {
    private Transaction transactionCourante;
    private double banqueDeLaBorne;

    public static final int MAX_DUREE_PARKING = 120;
    public static final int MIN_DUREE_PARKING = 0;
    public static final int INTERVAL_MINUTES = 15;

    public Borne() {
        transactionCourante = null;
        banqueDeLaBorne = 0;
    }

    public void commencerTransaction(String placeStationnement){
        if (placeStationnement.matches("G[0-9]{3}")){
            transactionCourante = new Transaction(placeStationnement, 425);
        } else if (placeStationnement.matches("SQ[0-9]{3}")) {
            transactionCourante = new Transaction(placeStationnement, 225);
        }
    }

    public boolean verifHoraire(String placeStationnement){
        if (placeStationnement.matches("G[0-9]{3}") && (
                (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY && (LocalDateTime.now().getHour() > 9 && LocalDateTime.now().getHour() < 23)) ||
                (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY && (LocalDateTime.now().getHour() > 13 && LocalDateTime.now().getHour() < 18)) ||
                (LocalDateTime.now().getHour() > 8 && LocalDateTime.now().getHour() < 23))){
            return true;
        } else if ((placeStationnement.matches("SQ[0-9]{3}")) && (
                (LocalDate.now().getDayOfWeek().getValue() <= 5 && (LocalDateTime.now().getHour() > 9 && LocalDateTime.now().getHour() < 21)) ||
                (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY && (LocalDateTime.now().getHour() > 9 && LocalDateTime.now().getHour() < 18)))) {
            return true;
        } else {
            return false;
        }

    }

    public boolean verifPlace(String placeStationnement){
        return (placeStationnement.matches("G[0-9]{3}") || placeStationnement.matches("SQ[0-9]{3}"));
    }

    public boolean ajouterQuinzeMinutes(){
        if (getTransactionCourante().getDureeMinutes() < MAX_DUREE_PARKING) {
            getTransactionCourante().setDureeMinutes(getTransactionCourante().getDureeMinutes() + INTERVAL_MINUTES);
            getTransactionCourante().setMontantCredit(getTransactionCourante().getMontantCredit() + (getTransactionCourante().getTauxHoraireCent() / 4));
            return true;
        }
        return false;
    }

    public void setDureeParkingToMax(){
        getTransactionCourante().setDureeMinutes(MAX_DUREE_PARKING);
        getTransactionCourante().setMontantComptant((getTransactionCourante().getTauxHoraireCent() * 2) / 100);
    }

    public boolean retirerQuinzeMinutes(){
        if (getTransactionCourante().getDureeMinutes() >= MIN_DUREE_PARKING){
            getTransactionCourante().setDureeMinutes(getTransactionCourante().getDureeMinutes() - INTERVAL_MINUTES);
            getTransactionCourante().setMontantCredit(getTransactionCourante().getMontantCredit() - (getTransactionCourante().getTauxHoraireCent() / 4));
            return true;
        }
        return false;
    }

    public boolean validerCarte(YearMonth expiration) {
        return (YearMonth.now().isBefore(expiration));
    }

    public boolean insererPiece(Piece p){
        transactionCourante.setMontantComptant(transactionCourante.getMontantComptant() + (p.getCentValue() / 100));
        if ((transactionCourante.getDureeMinutes() + (p.getCentValue() * 60) / transactionCourante.getTauxHoraireCent()) < MAX_DUREE_PARKING) {
            transactionCourante.setDureeMinutes((int)Math.round(transactionCourante.getDureeMinutes() + (p.getCentValue() * 60) / transactionCourante.getTauxHoraireCent()));
            return true;
        } else {
            transactionCourante.setDureeMinutes(120);
            return false;
        }
    }

    public void annulerTransactionCourante(){
        transactionCourante = null;
    }

    public String terminerTransactionCourante(){
        banqueDeLaBorne += transactionCourante.getMontant();
        String facture = "";

        if (transactionCourante.getMontantCredit() > 0) {
            facture += "Type : Crédit\n" +
                        "Total : " + transactionCourante.getMontantCredit() / 100 + "\n";
        }
        if (transactionCourante.getMontantComptant() > 0) {
            facture += "Type : Comptant\n" +
                        "Total : " + transactionCourante.getMontantComptant() + "\n";
        }
        facture += "Place du stationnement : " + transactionCourante.getPlaceStationnement() + "\n" +
                    "Total de la transaction : " + (transactionCourante.getMontant()) + " $\n" +
                    "Durée du parking : " + transactionCourante.getDureeMinutes() + " minutes";
        transactionCourante = null;
        return facture;
    }
    public String genererRapport(){
        String rapport =
                        "-----------------------------------------------\n" +
                        "Solde de la borne : " + banqueDeLaBorne + " $\n" +
                        "La banque de cette borne est remise à 0.00$\n" +
                        "-----------------------------------------------\n";
        banqueDeLaBorne = 0;

        return rapport;
    }

    public Transaction getTransactionCourante(){
        return transactionCourante;
    }
}
