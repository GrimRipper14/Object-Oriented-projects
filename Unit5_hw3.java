import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

abstract class Book {
    protected String Author;
    protected String Title;
    protected String Isbn;

    public Book(String Author, String Title, String Isbn) {
        this.Author = Author;
        this.Title = Title;
        this.Isbn = Isbn;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return Title;
    }

    public String getIsbn() {
        return Isbn;
    }

    @Override
    public abstract String toString();
}

class BookstoreBook extends Book {
    private double price;
    private boolean onSale;
    private double salePrice;

    public BookstoreBook(String Author, String Title, String Isbn, double price, boolean onSale, double deduction) {
        super(Author, Title, Isbn);
        this.price = price;
        this.onSale = onSale;
        if (onSale) {
            this.salePrice = price - (price * (deduction / 100));
        } else {
            this.salePrice = price;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s-%s by %s, $%.2f listed for $%.2f]", 
                             Isbn, Title.toUpperCase(), Author.toUpperCase(), price, salePrice);
    }
}

class LibraryBook extends Book {
    private String callNumber;

    public LibraryBook(String Author, String Title, String Isbn) {
        super(Author, Title, Isbn);
        this.callNumber = generateCallNumber(Isbn);
    }

    private String generateCallNumber(String Isbn) {
        Random rand = new Random();
        int floor = rand.nextInt(99) + 1;
        String AuthorInitials = Author.substring(0, 3).toUpperCase();
        char lastIsbnChar = Isbn.charAt(Isbn.length() - 1);
        return String.format("%02d.%s.%c", floor, AuthorInitials, lastIsbnChar);
    }

    @Override
    public String toString() {
        return String.format("[%s-%s by %s-%s]", 
            Isbn, Title.toUpperCase(), Author.toUpperCase(), callNumber);
    }
}

class BookList {
    private List<Book> list;

    public BookList() {
        list = new ArrayList<>();
    }

    public void addBook(Book book) {
        list.add(book);
    }

    public void displayBooks() {
        int libraryCount = 0;
        int bookstoreCount = 0;

        for (Book book : list) {
            if (book instanceof LibraryBook) {
                libraryCount++;
            } else if (book instanceof BookstoreBook) {
                bookstoreCount++;
            }
        }

        System.out.println("Library Books (" + libraryCount + ")");
        for (Book book : list) {
            if (book instanceof LibraryBook) {
                System.out.println("\t"+book);
            }
        }

        System.out.println("_ _ _ _");
        System.out.println("Bookstore Books (" + bookstoreCount + ")");
        for (Book book : list) {
            if (book instanceof BookstoreBook) {
                System.out.println("\t"+book);
            }
        }
        System.out.println("_ _ _ _");
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookList bookList = new BookList();

        System.out.println("Welcome to the book program!");
        String input;
        
        do {
            System.out.print("Would you like to create a book object? (yes/no): ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                System.out.print("Please enter the author, title and the isbn of the book separated by /: ");
                String[] bookInfo = scanner.nextLine().split("/");
                String Author = bookInfo[0].trim();
                String Title = bookInfo[1].trim();
                String Isbn = bookInfo[2].trim();

                String bookType;
                System.out.println("Got it!");
                System.out.print("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
                do {
                    bookType = scanner.nextLine().trim().toLowerCase();
                    if (bookType.equals("bb")) {
                        System.out.println("Got it!");
                        System.out.print("Please enter the list price of " + Title.toUpperCase() + " by " + Author.toUpperCase() + ": ");
                        double price = Double.parseDouble(scanner.nextLine().trim());
                        System.out.print("Is it on sale? (y/n): ");
                        boolean onSale = scanner.nextLine().trim().equalsIgnoreCase("y");
                        double deduction = 0;
                        if (onSale) {
                            System.out.print("Deduction percentage: ");
                            String deductionInput = scanner.nextLine().trim();
                            deductionInput = deductionInput.replace("%", ""); 
                            deduction = Double.parseDouble(deductionInput);   
                        }
                        BookstoreBook newBookstoreBook = new BookstoreBook(Author, Title, Isbn, price, onSale, deduction);
                        bookList.addBook(newBookstoreBook);
                        System.out.println("Got it!");
                        System.out.println("\n\nHere is your bookstore book information");
                        System.out.println(newBookstoreBook);
                        System.out.println("\n");
                        break;
                    } else if (bookType.equals("lb")) {
                        System.out.println("Got it!");
                        LibraryBook newLibraryBook = new LibraryBook(Author, Title, Isbn);
                        bookList.addBook(newLibraryBook);
                        System.out.println("\n\nHere is your library book information");
                        System.out.println(newLibraryBook);
                        break;
                    } else {
                        System.out.print("Oops! That’s not a valid entry. Please try again: ");
                    }
                } while (true);
            } else if (!input.equals("no")) {
                System.out.println("I’m sorry but " + input + " isn’t a valid answer. Please enter either yes or no.");
            }

        } while (!input.equals("no"));
        System.out.println("Sure!\n\n");
        System.out.println("Here are all your books...");

        bookList.displayBooks();
        scanner.close();
        System.out.println("Take care now!");
        
    }
}
