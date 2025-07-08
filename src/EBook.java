public class EBook extends Book {


    public EBook(String ISBN, String Title, int year, int price, String Authorname, int count) {
        super(ISBN, Title, year, price, Authorname,count);

    }
    public boolean Forsale() {
        return (count > 0);
    }
    public void buyEBook(int quantity, String Email) {
        if (quantity > count) {
            System.out.println("Quantum bookstore: Not enough copies in stock.");
            return;
        }
        reduceCount(quantity);
        System.out.println("Quantum bookstore: Shipping " + quantity + " copies to " + Email);
    }
}
