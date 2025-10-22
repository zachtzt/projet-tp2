import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Borne {
    private Transaction transactionCourante;
    private double banqueDeLaBorne;

    public Borne() {
        this.transactionCourante = null;
        this.banqueDeLaBorne = 0;
    }

    public void commencerTransaction(String placeStationnement){
        if (placeStationnement.matches("G[0-9]{3}")){
            if ((LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY && (LocalDateTime.now().getHour() > 9 && LocalDateTime.now().getHour() < 23)) ||
                    (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY && (LocalDateTime.now().getHour() > 13 && LocalDateTime.now().getHour() < 18)) ||
                    (LocalDateTime.now().getHour() > 8 && LocalDateTime.now().getHour() < 23))
                    this.transactionCourante = new Transaction(placeStationnement, 425);
            else
                System.out.println("Cette place de stationnement n'est pas payante en ce moment!");
        } else if (placeStationnement.matches("SQ[0-9]{3}")) {
            if ((LocalDate.now().getDayOfWeek().getValue() <= 5 && (LocalDateTime.now().getHour() > 9 && LocalDateTime.now().getHour() < 21)) ||
                    (LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY && (LocalDateTime.now().getHour() > 9 && LocalDateTime.now().getHour() < 18)))
                    this.transactionCourante = new Transaction(placeStationnement, 225);
            else
                System.out.println("Cette place de stationnement n'est pas payante en ce moment!");
        } else {
            System.out.println("Erreur ! Veuillez réessayer avec une place de stationnement valide.");
        }
    }

    public Transaction getTransactionCourante(){
        return transactionCourante;
    }

    public void annulerTransactionCourante(){
        this.transactionCourante = null;
        System.out.println("TRANSACTION ANNULÉE");
    }

    public void terminerTransactionCourante(){
        this.banqueDeLaBorne += this.transactionCourante.getMontantCredit() + this.transactionCourante.getMontantComptant();
        System.out.println("-----------------------------------------------");
        if (this.transactionCourante.getMontantCredit() > 0) {
            System.out.println("Type : Crédit");
            System.out.println("Total : " + this.transactionCourante.getMontantCredit());
            System.out.println("-----------------------------------------------");
        }
        if (this.transactionCourante.getMontantComptant() > 0) {
            System.out.println("Type : Comptant");
            System.out.println("Total : " + this.transactionCourante.getMontantComptant());
            System.out.println("-----------------------------------------------");
        }
        System.out.println("Place du stationnement : " + this.transactionCourante.getPlaceStationnement());
        System.out.println("Total de la transaction : " + (this.transactionCourante.getMontantCredit() + this.transactionCourante.getMontantComptant()) + " $");
        System.out.println("Durée du parking : " + this.transactionCourante.getDureeMinutes() + " minutes");
        System.out.println("-----------------------------------------------");
        this.transactionCourante = null;
    }
    public String genererRapport(){
        String rapport =
                        "-----------------------------------------------\n" +
                        "Argent dans la Borne : " + this.banqueDeLaBorne + " $\n" +
                        "Le solde de cette banque sera remis à 0,00$\n" +
                        "-----------------------------------------------\n";
        this.banqueDeLaBorne = 0;

        return rapport;
    }
}
