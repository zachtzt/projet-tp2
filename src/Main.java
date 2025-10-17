import java.time.YearMonth;

public class Main {
    public static void main(String[] args) {
        Borne b = new Borne();
        b.commencerTransaction("G123");

        b.getTransactionCourante().insererPiece(new Piece(200));
        b.getTransactionCourante().insererPiece(new Piece(200));
        b.getTransactionCourante().insererPiece(new Piece(200));
        b.getTransactionCourante().insererPiece(new Piece(200));
        b.getTransactionCourante().insererPiece(new Piece(200));
        b.terminerTransactionCourante();

        System.out.println("\n\n");

        b.commencerTransaction("SQ224");
        CarteCredit c = new CarteCredit("1234567891011123", YearMonth.of(2026, 11), 300);
        b.getTransactionCourante().setCarte(c);
        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.getTransactionCourante().insererPiece(new Piece(200));
        b.terminerTransactionCourante();

        b.genererRapport();
    }
}