import java.util.List;
import java.util.ArrayList;

public class Member {
    private String name;
    private String memberNumber;
    public List<Book> BooksCurrentRenting = new ArrayList<>();
    public List<Book> RentHistory = new ArrayList<>();

    //constructor
    public Member(String name, String memberNumber) {
        this.name = name;
        this.memberNumber = memberNumber;
    }

    //common book will return the list of book which all targer members have in their rentHistory
    public static List<Book> commonBooks​(Member[] members) {
        //null check
        if (members == null) {
            return null;
        }

        List<Book> result = new ArrayList<>();

        //creat a list of list which is a list of member history
        ArrayList<List<Book>> temp_member_history_list = new ArrayList<>();
        for (Member i: members) {
            if (i == null) {
                return null;
            }
            temp_member_history_list.add(i.history());
        }

        //nested loop travers every element to find the shared history
        //if find one, add it to the result list
        //the count variable will count how many times the book shows in member history
        //if count is equals to the size of the big history list, means it shows in every members history, add it to the result list
        for (int i = 0; i < temp_member_history_list.size(); i++) {
            List<Book> history_list = temp_member_history_list.get(i);
            
            
            for (int j = 0; j < history_list.size(); j++) {
                int count = 0;
                Book temp_book = null;
                for (int k = 0; k < temp_member_history_list.size(); k++) {
                    if (temp_member_history_list.get(k).contains(history_list.get(j))) {
                        count++;
                        temp_book = history_list.get(j);
                    }
                }
                if (count == temp_member_history_list.size()) {
                    if (!result.contains(temp_book)) {
                        result.add(temp_book);
                    }
                }
            }
        }

        return result;
    }

    public String getMemberNumber() {
        return this.memberNumber;
    }

    public String getName() {
        return this.name;
    }

    public List<Book> history() {
        return RentHistory;
    }

    public boolean relinquish​(Book book) {
        boolean result = false;

        //null check, if the member not rent target book, means it could not be return, return false
        //if book is not null, and member is the current renter of the book
        //  change the member statements(remove the book object from the BooksCurrentRenting list, add it to RentHistory list) and 
        //  call book methods(to change the book statement(currentRenter->null, history.add(member), rented_>false)
        if (book == null || !BooksCurrentRenting.contains(book)) {
            result = false;
        } else if (book != null && BooksCurrentRenting.contains(book)) {
            book.relinquish​(this);
            book.setCurrentrenter(null);
            BooksCurrentRenting.remove(book);
            RentHistory.add(book);
            result = true;
        }

        return result;
    }

    public void relinquishAll() {
        //use the reliquish method to all element in the BooksCurrentRenting list
        //empty the BooksCurrentRenting list
        for (Book i:this.BooksCurrentRenting) {
            i.relinquish​(this);
            i.setCurrentrenter(null);
            this.RentHistory.add(i);
        }
        this.BooksCurrentRenting.clear();
        System.out.println("Success.");
    }

    public boolean rent​(Book book) {
        boolean result = false;
        //null check, and check if the book is rentbale, if not return false;
        //if target book is not null and the book is rentable
        //  use the book.rent method change the book's statements
        //  add target book to BooksCurrentRenting list
        //  return true
        //return false
        if (book == null || book.isRented() == true) {
            result = false;
        } else if (book != null && book.isRented() == false) {
            this.BooksCurrentRenting.add(book);
            book.rent​(this);
            result =  true;
        }

        return result;
    }

    //return the BooksCurrentRenting list
    public List<Book> renting() {
        return BooksCurrentRenting;
    }

}
