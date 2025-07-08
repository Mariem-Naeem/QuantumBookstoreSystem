public class ShowCaseBook extends Book{
    String FileType;
    ShowCaseBook(String ISBN, String Title, int year, int price, String Authorname,String FileType){
        super(ISBN,Title,year,price,Authorname,0);
        this.FileType=FileType;

    }
    public boolean Forsale() {
        return false;
    }

    public void buyShowCaseBook() {
        System.out.println("Quantum bookstore: This book is a showcase copy and not for sale.");
    }
    public String Display() {
        return super.Display() + ", FileType=" + FileType;
    }
}
