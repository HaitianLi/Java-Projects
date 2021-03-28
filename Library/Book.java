import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Book{
    private String serialNumber;
    private String title;
    private String author;
    private String genre;
    private boolean rented = false;
    private Member currentRenter;
    public List<Member> rentHistory = new ArrayList<>();

    //constructor
    public Book(String title, String author, String genre, String serialNumber) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.serialNumber = serialNumber;
    }

    public static List<Book> filterAuthor​(List<Book> books, String author) {
        //null check
        if (books == null || author == null) {
            return null;
        }
        List<Book> result = new ArrayList<>();

        //traverse the book list, add the book which have the targer author to the result list
        for (Book i: books) {
            if (i.getAuthor().equals(author)) {
                result.add(i);
            }
        }

        return result;
    }  

    public static List<Book> filterGenre​(List<Book> books, String genre) {
        //null check
        if (books == null || genre == null) {
            return null;
        }

        List<Book> result = new ArrayList<>();

        //traverse the book list, add the book which have the targer genre to the result list
        for (Book i: books) {
            if (i.getGenre().equals(genre)) {
                result.add(i);
            }
        }
        

        return result;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public String getTitle() {
        return this.title;
    }

    //will return the current renter of the book, if there is no current renter will return null
    public Member getCurrentrenter() {
        return this.currentRenter;
    }

    //get & set isrented value
    //get the rented value
    public boolean isRented() {
        return rented;
    }

    //set the rented value to true
    public void Rented() {
        this.rented = true;
    }

    //set the rented value to original which is false
    public void resetRented() {
        this.rented = false;
    }


    public String longString() {
        //format
        //If the book is rented: [serialNumber]: [title] ([author], [genre])\nRented by: [renter number].
        //If the book is available: [serialNumber]: [title] ([author], [genre])\nCurrently available.
        String result = "";
        if (!this.rented) {
            result += this.serialNumber +": "+this.title+" ("+this.author+", "+this.genre+")"+"\n"+"Currently available.";
        } else {
            result += this.serialNumber +": "+this.title+" ("+this.author+", "+this.genre+")"+"\n"+"Rented by: "+this.currentRenter.getMemberNumber()+".";
        }

        return result;
    }

    public static Book readBook​(String filename, String serialNumber) {
    
        //null check
        if (filename == null || serialNumber == null) {
            return null;
        }

        //use static method Library.readFile to process the csv file
        //since the csv file separate the data with ","
        //so it is a String list which have "Setialnumber, title, author, genre" in it
        ArrayList<String> temp_books = Library.readFile(filename);

        //traverse the temp_books list(from csv)
        //if there is target book in, creat a new book object, return it
        //if no target book return null
        Book result = null;
        for (int i = 0; i < temp_books.size(); i++) {
            String [] book_temp = temp_books.get(i).split(",");
            if (book_temp[0].equals(serialNumber)) {
                Book book_add = new Book(book_temp[1], book_temp[2], book_temp[3], book_temp[0]);
                result = book_add;

            }
        }
        return result;

    }

    public static List<Book> readBookCollection​(String filename) {
        //null check
        if (filename == null) {
            return null;
        }
        
        List<Book> result = new ArrayList<>();

        //process the csv file, if filenot exist return null
        File fname = new File(filename);
        List<String> bookcollection = new ArrayList<>();
        try {
            Scanner target = new Scanner(fname);
            while (target.hasNextLine()) {
                bookcollection.add(target.nextLine());
            }
            target.close();
        } catch (FileNotFoundException e) {
            return null;
        }
        
        //travers the String("Setialnumber, title, author, genre") in the bookcollection list
        //creat new book object add to the result list, return result list, the library will check the same serial number
        for (int i = 1; i< bookcollection.size(); i++) {
            String [] temp_book = bookcollection.get(i).split(",");
            result.add(new Book(temp_book[1], temp_book[2], temp_book[3], temp_book[0]));
        }

        return result;        
    }

    public boolean relinquish​(Member member) {
        boolean result = false;
        //if there is a current renter and the rented value is true. means the book could be relinquish
            //if the book's current renter is not target member, return false
            //else change the book statements(rented->false, currentRenter->null, rentHistory.add(targetMember)), return true
        //return false
        if (currentRenter != null && this.rented == true) {
            if (member == null || !this.currentRenter.getMemberNumber().equals(member.getMemberNumber())) {
                result = false;
            } else if (member != null && this.currentRenter.getMemberNumber().equals(member.getMemberNumber())) {
                this.rentHistory.add(member);
                this.resetRented();
                result = true;
            }
        }

        return result;
    }
    
    public boolean rent​(Member member) {
        boolean result = false;
        //null check, and check if the book could be rent
        //if it could be rent and the member is not null
            //change book statements   currentRenter->target member, rented->true, return true
        //return false
        if (member == null || this.rented == true) {
            result = false;
        } else if (member != null && !this.rented) {
            this.setCurrentrenter(member);
            this.Rented();
            result = true;
        }
        return result;
    }

    //return book history
    public List<Member> renterHistory() {
        return rentHistory;
    }


    public static void saveBookCollection​(String filename, Collection<Book> books) {
        //null check
        if (filename == null || books == null) {
            return;
        }
        //write data to the csv file
        File output = new File(filename);
        try {
            PrintWriter writer = new PrintWriter(output);
            writer.printf("%s,%s,%s,%s\n","serialNumber","title","author","genre");
            for (Book i:books) {
                writer.printf("%s,%s,%s,%s\n",i.getSerialNumber(),i.getTitle(),i.getAuthor(),i.getGenre());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            //will auto creat a new file if the file not exits, so this part may not invoke
        }

    }

    //set the current renter to target member
    public void setCurrentrenter(Member member) {
        this.currentRenter = member;
    }

    //return the shoutString format of the book object
    public String shortString() {
        String result = "";
        result += this.title+" (" +this.author +")";
        return result;
    }

    //override the equals method
    //will return true if two book objects have same author and title
    public boolean equals(Object obj) {
        Book n = (Book)obj;
        return (this.author.equals(n.getAuthor()) && this.title.equals(n.getTitle()));
    }

}