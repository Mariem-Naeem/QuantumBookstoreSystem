public abstract class Book {
    protected String ISBN;
  protected  String Title;
   protected int year;
   protected  int price;
   protected String AuthorName ;
   protected int count;
   public Book(String ISBN,String Title,int year,int price, String AuthorName,int count){
       this.ISBN=ISBN;
       this.Title=Title;
       this.year=year;
       this.price=price;
       this.AuthorName=AuthorName;
       this.count=count;
   }
    public abstract boolean Forsale();
    public int getCount() {
        return count;
    }

    public void reduceCount(int quantity) {
        this.count -= quantity;
    }
    public String Display() {
        return "Quantum bookstore: Book [ISBN=" + ISBN + ", Title=" + Title + ", Year=" + year + ", Price=" + price + ", Author=" + AuthorName + "]";
    }
}
