public class Borne {
    private Transaction transactionCourante;
    private double banqueDeLaBorne;

    public Borne() {
        this.transactionCourante = null;
        this.banqueDeLaBorne = 0;
    }

    public void commencerTransaction(String placeStationnement){
        if (placeStationnement.matches("G[0-9]{3}") || placeStationnement.matches("SQ[0-9]{3}")){
            this.transactionCourante = new Transaction(placeStationnement);
        } else {
            System.out.println("Erreur ! Veuillez réessayer avec une place de stationnement valide.");
        }
    }
    public void terminerTransactionCourante(){
        this.banqueDeLaBorne += this.transactionCourante.getMontant();
        this.transactionCourante = null;
    }
    public void genererRapport(){
        System.out.println("-----------------------------------------------");
        System.out.println("Argent dans la Borne : " + this.banqueDeLaBorne);
        System.out.println("-----------------------------------------------");
        System.out.println("Le solde de cette banque sera remis à 0,00$");
        this.banqueDeLaBorne = 0;
    }
}
