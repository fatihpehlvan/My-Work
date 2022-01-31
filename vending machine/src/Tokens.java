public class Tokens {
    private final String id;
    private final String product;
    private int piece;

    public Tokens(String item) {
        String[] itemArray = item.split(" ");
        this.id = itemArray[0];
        this.product = itemArray[1];
        this.piece = Integer.parseInt(itemArray[2]);
    }

    public String getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int newPiece){
        piece = newPiece;
    }
}
