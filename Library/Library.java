import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Library {
    public static String HELP_STRING = Help_builder();
    private List<Book> Books = new ArrayList<>();
    private List<Member> Members = new ArrayList<>();
    private int member_number = 100000;

    public static void main(String[] args) {
        //make a new library object, and call the run() method to start the loop;
        Library library_obj = new Library();
        library_obj.run();
    }

    public static String  Help_builder() {
        String result = "";
        result += "EXIT ends the library process\nCOMMANDS outputs this help string\n\nLIST ALL [LONG] ";
        result += "outputs either the short or long string for all books\nLIST AVAILABLE [LONG] outputs ";
        result += "either the short of long string for all available books\nNUMBER COPIES outputs the ";
        result += "number of copies of each book\nLIST GENRES outputs the name of every genre in the ";
        result += "system\nLIST AUTHORS outputs the name of every author in the system\n\nGENRE <genre> ";
        result += "outputs the short string of every book with the specified genre\nAUTHOR <author> ";
        result += "outputs the short string of every book by the specified author\n\nBOOK <serialNum";
        result += "ber> [LONG] outputs either the short or long string for the specified book\nBOOK HIS";
        result += "TORY <serialNumber> outputs the rental history of the specified book\n\nMEMBER <me";
        result += "mberNumber> outputs the information of the specified member\nMEMBER";
        result += " BOOKS <memberNumber> outputs the books currently rented by the";
        result += " specified member\nMEMBER HISTORY <memberNumber> outputs the rental";
        result += " history of the specified member\n\nRENT <memberNumber>";
        result += " <serialNumber> loans out the specified book to the given";
        result += " member\nRELINQUISH <memberNumber> <serialNumber> returns the";
        result += " specified book from the member\nRELINQUISH ALL <memberNumber>";
        result += " returns all books rented by the specified member\n\nADD MEMBER";
        result += " <name> adds a member to the system\nADD BOOK <filename> ";
        result += "<serialNumber> adds a book to the system\n\nADD COLLECTION ";
        result += "<filename> adds a collection of books to the system\nSAVE ";
        result += "COLLECTION <filename> saves the system to a csv file\n\nCOMMON ";
        result += "<memberNumber1> <memberNumber2> ... outputs the common books in members\' history";
        return result;
    }

    public void addBook​(String bookFile, String serialNumber) {
        //travers the csv file for each line, add the book which have the same serialnumber as user input
        //check if the book which wanna add already in the system first, if yes, it will not add to the system;
        for (Book i: Books) {
            if (i.getSerialNumber().equals(serialNumber)) {
                System.out.println("Book already exists in system.");
                return;
            }
        }

        //read the file by Scanner, check if the file exist only, so the scanner could be close immiditaly
        try {
            Scanner scan = new Scanner(new File(bookFile));
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file.");
            return;
        }

        //call the readbook (static) method in Book class to add book to the main system.
        Book book_add = Book.readBook​(bookFile, serialNumber);

        if (book_add == null) {
            System.out.println("No such book in file.");
        } else {
            Books.add(book_add);
            System.out.printf("Successfully added: %s.\n", book_add.shortString());
        }

   
    }

    public void addCollection​(String filename) {
        
        //call the readBookCollection (static method) in Book class to add collection
        List<Book> readfileresult_list = Book.readBookCollection​(filename);

        //if the method return null, the file not exist.
        if (readfileresult_list == null) {
            System.out.println("No such collection.");
            return;
        }

        //check if the book in the collection already in the library system.
        //use the boolean value, if found the same serial number, will not add to the main system
        //if successfuly added, count it.
        int count = 0;
        for (Book i: readfileresult_list) {
            boolean found_same = false;
            for (Book j : Books) {
                if (i.getSerialNumber().equals(j.getSerialNumber())) {
                    found_same = true;
                }
            }
            if (!found_same) {
                Books.add(i);
                count++;
            }
        }

        //check the count number from the last part
        if (count == 0) {
            System.out.println("No books have been added to the system.");
        } else {
            System.out.printf("%d books successfully added.\n" , count);
        }

    }

    public void addMember​(String name) {
        //since we assumen the input is always valid, dont need to check anything.
        //member ID original be set as type int, since we need to add up one when new member added in system(start from 100000)
        //make int to string, call member contructor to make the member object
        //increment the member number
        //so the variable "private int member_number" (line 16)always be the member number for the new member;
        String int_to_string_membernumber = member_number +"";
        Member member_add = new Member(name, int_to_string_membernumber);
        Members.add(member_add);
        member_number++;
        System.out.println("Success.");

    }

    public void bookHistory​(String serialNumber) {

        //check if the system is empty(no books)
        if (Books.size() == 0) {
            System.out.println("No such book in system.");
            return;
        }

        /*find the target book(serialNUmber(user input))
            if find
                call the book method renterhistory which will return the list of member who had rent the book
                if size of list is zero
                    output, return;
                else
                    tarverse the list, out put with member class getMemberNumber method;
                change the boolean value of found or not
        if not found
            output*/
        boolean found_target_book = false;
        for (Book i: Books) {
            
            if (i.getSerialNumber().equals(serialNumber)) {
                List <Member> temp_member = i.renterHistory();
                if (temp_member.size() == 0) {
                    System.out.println("No rental history.");
                    return;
                } else {
                    for (Member j: temp_member) {
                        System.out.println(j.getMemberNumber());
                    }
                }
                found_target_book = true;
            }
        }

        if (!found_target_book) {
            System.out.println("No such book in system.");
        }

    }

    public void common​(String[] memberNumbers) {
        //check if system contain any member and book
        if (MemberSystemCheckEmpty()) {
            return;
        }
        if (BookSystemCheckEmpty()) {
            return;
        }
        
        //check duplicate member number input from user, by nested for loop, if count > 0 means there are duplicate
        for (int i = 0; i < memberNumbers.length; i++) {
            int count = 0;
            for (int j = i + 1; j < memberNumbers.length; j++) {
                if (memberNumbers[i].equals(memberNumbers[j])) {
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("Duplicate members provided.");
                return;
            }
        }

        
        List<Member> temp_member_list = new ArrayList<>();
        
        //traverse the system member list, make sure the target member number is valid
        //if valid, add the member object to the new member array temp_member_list since the input from user is a string array
        for (String j:memberNumbers) {
            boolean found_one = false;
            for (Member i: Members) {
                if (i.getMemberNumber().equals(j)) {
                    found_one=true;
                    temp_member_list.add(i);
                }
            }
            if (!found_one) {
                System.out.println("No such member in system.");
                return;
            }
        }

        //make the temp_member_list to a member list, since the Member.commonBooks only take member array as parameter
        // member.commanBooks() will return a list of books that all members in the input list had rented.
        Member[] target_member_array = temp_member_list.toArray(new Member[temp_member_list.size()]);
        List<Book> share_list = Member.commonBooks​(target_member_array);

        //traverse the share_lsit, output to the wanted data to console;
        if (share_list.size() == 0) {
            System.out.println("No common books.");
        } else {
            for (Book i:share_list) {
                System.out.println(i.shortString());
            }
        }

    }

    public void getAllBooks​(boolean fullString) {
        //check system book list
        if (BookSystemCheckEmpty()) {
            return;
        }

        // if fullstring is true, call the book.longString(), to out put the wanted data in specific format;
        // if is false, call book.sortSting()        
        if (fullString) {
            for (int i = 0; i < Books.size(); i++) {
                if (i == 0) {
                    System.out.println(Books.get(i).longString());
                } else {
                    System.out.println("\n"+Books.get(i).longString());
                }
            }
        } else {
            for (Book i : Books) {
                System.out.println(i.shortString());
            }
        }

    }

    public void getAuthors() {
        // check whether there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        }

        //use hashset so there will no duplicate elements
        HashSet<String> temp = new HashSet<>();
        for (int i = 0; i < Books.size(); i++) {
            temp.add(Books.get(i).getAuthor());
        }

        //convert hashset to list to sort the order
        List<String> tempList = new ArrayList<String>(temp);
        Collections.sort(tempList);

        //output
        for (String i: tempList) {
            System.out.println(i);
        }
        
    }

    public void getAvailableBooks​(boolean fullString) {
        // check whether there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        }

        //traverse the book list, if the book object have no current renter means its available
        //statment in the for loop to check the boolean value fullString, if its true, output longform, otherwise shortform.
        //if found a book is available, change the found_available to true;
        boolean found_avaiable = false;
        boolean first = true;
        for (int i = 0; i< Books.size(); i++) {
            if (Books.get(i).getCurrentrenter()==null) {
                if (fullString) {
                    if (first) {
                        System.out.println(Books.get(i).longString());
                        first = false;
                    } else {
                        System.out.println("\n"+Books.get(i).longString());
                    }
                } else {
                    System.out.println(Books.get(i).shortString());
                }
                found_avaiable = true;
            }
        }
        
        // output if no available
        if (!found_avaiable) {
            System.out.println("No books available.");
        }

    }

    public void getBook​(String serialNumber, boolean fullString) {
        // check whether there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        }

        //travers book list, find the target serialnumber, and also check the fullstring to decide output longString or shortString
        //if found a book, count + 1.
        int count = 0;
        for (Book i: Books) {
            if (i.getSerialNumber().equals(serialNumber)) {
                count++;
                if (fullString) {
                    System.out.println(i.longString());
                    
                } else {
                    System.out.println(i.shortString());
                }
            }
        }

        if (count == 0) {
            System.out.print("No such book in system.\n");
        }

    }

    public void getBooksByAuthor​(String author) {
        // check whether there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        }

        //call the static method book.filterauthor, return a list of book object
        List<Book> list_author_book = Book.filterAuthor​(Books, author);
        
        //buuble sort by the serial number
        bubbleSort(list_author_book);

        //output
        if (list_author_book.size() == 0) {
            System.out.printf("No books by %s.\n", author);
        } else {
            for (Book i:list_author_book) {
                System.out.println(i.shortString());
            }
        }

    }

    public void getBooksByGenre​(String genre) {
        // check whether there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        }

        //call the static method book.filterGenre​, return a list of book object
        List<Book> list_genre_book = Book.filterGenre​(Books, genre);

        //sort the list by Serial number
        bubbleSort(list_genre_book);

        //output
        if (list_genre_book.size() == 0) {
            System.out.printf("No books with genre %s.\n", genre);
        } else {
            for (Book i:list_genre_book) {
                System.out.println(i.shortString());
            }
        }
    }

    public void getCopies() {
        // check whether there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        }
        
        // find out the copies, if alreay find one that have copies
        // put it in existalreay list, so it will not recount.
        //the .equals is override in book class, will return true if 2 books have same title and author
        //so when first time find equals books, put its title string in existalreay array
        //every time find another equals book, will check the existalready list 
        //to make sure iteration of the copie rather than  make a same object in the Map
        //since the object Book always have the different serial number even they have same title and author
        Map<Book, Integer> map = new HashMap<Book, Integer>();
        List<String> existalready = new ArrayList<>();

        for (Book i: Books) {
            boolean find_same = false;
            for (Book j: Books) {
                if (i.equals(j) && !existalready.contains(i.getTitle())) {
                    if (!map.containsKey(i)) {
                        map.put(i, 1);
                    } else {
                        map.replace(i, map.get(i)+1);
                    }
                    find_same = true;
                }
            }
            if (find_same) {
                existalready.add(i.getTitle());
            }
        }

        //make a title list which could be sorted
        Set<Book> book_set = map.keySet();
        List<String> set_to_list = new ArrayList<String>();

        for (Book i:book_set) {
            set_to_list.add(i.getTitle());
        }
        Collections.sort(set_to_list);

        //output the shortString by the sorted title order
        for (String i : set_to_list) {
            for (Book j:book_set) {
                if (i.equals(j.getTitle())) {
                    System.out.printf("%s: %d\n",j.shortString(),map.get(j));
                }
            }
        }



    }

    public void getGenres() {
        // check whether there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        }

        //use hashset, so no duplicate element
        HashSet<String> temp = new HashSet<String>();
        for (int i = 0; i < Books.size(); i++) {
            temp.add(Books.get(i).getGenre());
        }

        //sort the output data
        List<String> tempList = new ArrayList<String>(temp);
        Collections.sort(tempList);

        //output
        for (String i: tempList) {
            System.out.println(i);
        }

    }

    public void getMember​(String memberNumber) {
        // check whether there are members in library
        if (MemberSystemCheckEmpty()) {
            return;
        }
        
        //traverse the member list to make sure the input number is exist
        Member temp_member = MemberExistCheck(memberNumber);
        if (temp_member == null) {
            return;
        }

        //output
        System.out.printf("%s: %s\n", temp_member.getMemberNumber(), temp_member.getName());


    }

    public void getMemberBooks​(String memberNumber) {
        // check whether there are members in library
        if (MemberSystemCheckEmpty()) {
            return;
        }
        
        //make sure the input member is exist
        Member temp_member = MemberExistCheck(memberNumber);
        if (temp_member == null) {
            return;
        }

        //check the list variable of member BooksCurrentRenting
        //if nothing in it output
        //else output book shortString
        if (temp_member.BooksCurrentRenting.size() == 0) {
            System.out.println("Member not currently renting.");
        } else {
            for (Book i:temp_member.BooksCurrentRenting) {
                System.out.println(i.shortString());
            }
        }

    }

    public void memberRentalHistory​(String memberNumber) {
        // check whether there are members in library
        if (MemberSystemCheckEmpty()) {
            return;
        }
        
        //traverse the member list to make sure the input number is exist
        Member temp_member = MemberExistCheck(memberNumber);
        if (temp_member == null) {
            return;
        }

        //check the RentHistory variable from member object
        if (temp_member.RentHistory.size() == 0) {
            System.out.println("No rental history for member.");
        } else {
            for (Book i:temp_member.RentHistory) {
                System.out.println(i.shortString());
            }
        }
        

    }

    public void relinquishAll​(String memberNumber) {
        // check whether there are books in library
        if (MemberSystemCheckEmpty()) {
            return;
        }

        //traverse the member list to make sure the input number is exist
        Member temp_member = MemberExistCheck(memberNumber);
        if (temp_member == null) {
            return;
        } else {
            temp_member.relinquishAll();
        }

        //call the memthod in member class to relinquishall
        
    }

    public void relinquishBook​(String memberNumber, String serialNumber) {
        //check system member and book lists
        if (MemberSystemCheckEmpty()) {
            return;
        }
        if (BookSystemCheckEmpty()) {
            return;
        }
        
        //check if target member and target book exist
        Member temp_member = MemberExistCheck(memberNumber);
        if (temp_member == null) {
            return;
        }
        
        Book temp_book = BookExistCheck(serialNumber);
        if (temp_book == null) {
            return;
        }


        //call member method to return book(will change member.BooksCurrentRenting,member.RentHistory, and call book method to change book statements)
        if (temp_member.relinquish​(temp_book)) {
            System.out.println("Success.");
        } else {
            System.out.println("Unable to return book.");
        }
    }

    public void rentBook​(String memberNumber, String serialNumber) {
        //check system member and book lists
        if (MemberSystemCheckEmpty()) {
            return;
        }
        if (BookSystemCheckEmpty()) {
            return;
        }
        
        //check if target member and target book exist
        Member temp_member = MemberExistCheck(memberNumber);
        if (temp_member == null) {
            return;
        }
        
        Book temp_book = BookExistCheck(serialNumber);
        if (temp_book == null) {
            return;
        }


        // rent method in member call rent method in book
        // the method will set up the member renting book list(BooksCurrentRenting)
        // and book current renter, book statement;
        if (temp_member.rent​(temp_book)) {
            System.out.println("Success.");
        } else {
            System.out.println("Book is currently unavailable.");
        }

    }


    //the run method use while loop, keep scan the user input, assume there is only valid input;
    //while loop use if else statement to process inputs, command case insensitive
    //
    public void run() {

        String [] command;
        Scanner scan = new Scanner(System.in);
        System.out.print("user: ");
        while (scan.hasNextLine()) {
            
            //read the whole line and split the command
            //read the first word as the main command
            command = scan.nextLine().split(" ");
            String main_command = command[0].toUpperCase();
            
            //the if else statment use the main command to separatly process different command
            //since there is no invalid input

            //The logic of the if else statement is in the Readme.txt file
            if (main_command.equals("EXIT")) {
                System.out.println("Ending Library process.");
                scan.close();
                return;

            } else if (main_command.equals("COMMANDS")) {
                System.out.println(HELP_STRING);

            } else if (main_command.equals("LIST")) {
                String second_command = command[1].toUpperCase();

                if (second_command.equals("ALL")) {
                    boolean check_long = false;
                    if (command.length == 3 && command[2].toUpperCase().equals("LONG")) {
                        check_long = true;
                        getAllBooks​(check_long);
                        
                    } else {
                        getAllBooks​(check_long);
                    }
                    
                    
                } else if (second_command.equals("AVAILABLE")) {
                    boolean check_long = false;
                    if (command.length == 3 && command[2].toUpperCase().equals("LONG")) {
                        check_long = true;
                        getAvailableBooks​(check_long);
                        
                    } else {
                        getAvailableBooks​(check_long);
                    }

                } else if (second_command.equals("GENRES")) {
                    getGenres();
                    
                } else if (second_command.equals("AUTHORS")) {
                    getAuthors();
                }

            } else if (main_command.equals("NUMBER")) {
                getCopies();

            } else if (main_command.equals("GENRE")) {
                String target_genre = "";
                for (int i = 1; i < command.length;i++) {
                    if (i == 1) {
                        target_genre += command[i];
                    } else {
                        target_genre += " " + command[i];
                    }
                }
                getBooksByGenre​(target_genre);
            } else if (main_command.equals("AUTHOR")) {
                String target_author = "";
                for (int i = 1; i < command.length;i++) {
                    if (i == 1) {
                        target_author += command[i];
                    } else {
                        target_author += " " + command[i];
                    }
                }
                getBooksByAuthor​(target_author);
                
            } else if (main_command.equals("BOOK")) {


                String second_command = command[1].toUpperCase();
                if (second_command.equals("HISTORY")) {
                    bookHistory​(command[2]);
                } else {
                    if (command.length > 2 && command[2].toUpperCase().equals("LONG")) {
                        getBook​(command[1], true);
                        
                    } else {
                        getBook​(command[1], false);
                    }
                }

                
            } else if (main_command.equals("MEMBER")) {
                String second_command = command[1].toUpperCase();
                if (second_command.equals("BOOKS")) {
                    getMemberBooks​(command[2]);

                } else if (second_command.equals("HISTORY")) {
                    memberRentalHistory​(command[2]);
                } else {
                    getMember​(second_command);
                }
                
            } else if (main_command.equals("RENT")) {
                rentBook​(command[1], command[2]);
                
            } else if (main_command.equals("RELINQUISH")) {
                String second_command = command[1].toUpperCase();
                if (second_command.equals("ALL")) {
                    relinquishAll​(command[2]);
                } else {
                    relinquishBook​(second_command, command[2]);
                }
                
            } else if (main_command.equals("ADD")) {
                String second_command = command[1].toUpperCase();

                if (second_command.equals("MEMBER")) {
                    String name = "";
                    for (int i = 2; i < command.length;i++) {
                        if (i == 2) {
                            name += command[i];
                        } else {
                            name += " " + command[i];
                        }
                    }
                    addMember​(name);
                    
                } else if (second_command.equals("BOOK")) {
                    //assume no invalid input;
                    String filename = command[2];
                    String snumber = command[3];
                    addBook​(filename, snumber);
                    
                } else if (second_command.equals("COLLECTION")) {
                    String fname = command[2];
                    addCollection​(fname);
                    
                }
                
            } else if (main_command.equals("SAVE")) {
                saveCollection​(command[2]);
            } else if (main_command.equals("COMMON")) {
                String [] member_snumber_list = new String[command.length - 1];
                //start with 1, skip the main command;
                for (int i = 1; i < command.length; i++) {
                    member_snumber_list[i-1] = command[i];
                } 
                common​(member_snumber_list);
                
            }
            System.out.println();
            System.out.print("user: ");
        }
        scan.close();

    }
    public void saveCollection​(String filename) {
        // check wheather there are books in library
        if (BookSystemCheckEmpty()) {
            return;
        } else {
            //call book method to save collection
            Book.saveBookCollection​(filename, Books);
            System.out.println("Success.");
        }

        
    }

    //process the csv file, return the arraylist which contains all books details from the csv file.
    public static ArrayList<String> readFile(String filename) {
        File fname = new File(filename);
        ArrayList<String> bookcollection = new ArrayList<>();
        
        try {
            Scanner scan = new Scanner(fname);
            while (scan.hasNextLine()) {
                bookcollection.add(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("No such file.");
        }
        //delete later
        // for (String i:bookcollection) {
        //     System.out.println(i);
        // }
        return bookcollection;
    }

    
    //4 check method
    // check if the system book list is empty if so return true;
    // check if the system Member list is empty if so return true;
    // check if the input serialnumber is exist in the book list if yes return the book object, no->return null;
    // check if the input membernumber is exist in the member list if yes return the member object, no->return null;

    public boolean BookSystemCheckEmpty() {
        boolean result = false;
        if (Books.size() == 0) {
            System.out.println("No books in system.");
            result = true;
        }
        return result;
    }

    public Book BookExistCheck(String serialNumber) {
        Book temp_book = null;
        for (Book i: Books) {
            if (i.getSerialNumber().equals(serialNumber)) {
                temp_book = i;
            }
        }

        if (temp_book == null) {
            System.out.println("No such book in system.");
            return temp_book;
        } else {
            return temp_book;
        }

    }

    public boolean MemberSystemCheckEmpty() {
        boolean result = false;
        if (Members.size() == 0) {
            System.out.println("No members in system.");
            result = true;
        }
        return result;
    }

    public Member MemberExistCheck(String memberNumber) {

        Member temp_member = null;
        for (Member i: Members) {
            if (i.getMemberNumber().equals(memberNumber)) {
                temp_member = i;
            }
        }

        if (temp_member == null) {
            System.out.println("No such member in system.");
            return temp_member;
        } else {
            return temp_member;
        }
    }

    public void bubbleSort(List<Book> list_book) {
        
        int size_number = list_book.size();
        Book temp = null;
        for (int i = 0; i < size_number-1;i++) {
            for (int j = 0; j< size_number-i-1;j++) {
                int a_Serial_number =Integer.parseInt(list_book.get(j+1).getSerialNumber());
                int b_Serial_number = Integer.parseInt(list_book.get(j).getSerialNumber());
                if (a_Serial_number < b_Serial_number) {
                    temp = list_book.get(j);
                    list_book.set(j, list_book.get(j+1));
                    list_book.set(j + 1, temp);
                }
            }
        }
    }
}
