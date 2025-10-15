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

        b.commencerTransaction("SQ224");

        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.getTransactionCourante().ajouterQuinzeMinutes();
        b.terminerTransactionCourante();

        b.genererRapport();
    }
}