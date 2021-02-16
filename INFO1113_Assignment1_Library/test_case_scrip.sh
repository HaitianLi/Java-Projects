#!/bin/bash


##The scrip automatically check all the cases in "tests" folder

#################
#case for addBook()
#################

java Library < tests/addbook_fileNotexist.in |diff - tests/addbook_fileNotexist.out
if [ $? -eq "1" ]; then
	echo "addbook_fileNotexist failed"
else
	echo "addbook_fileNotexist passed"
fi

java Library < tests/addbook_bookAreadyExist.in |diff - tests/addbook_bookAreadyExist.out
if [ $? -eq "1" ]; then
	echo "addbook_bookAreadyExist failed"
else
	echo "addbook_bookAreadyExist passed"
fi

java Library < tests/addbook_bookNotexist.in |diff - tests/addbook_bookNotexist.out
if [ $? -eq "1" ]; then
	echo "addbook_bookNotexist failed"
else
	echo "addbook_bookNotexist passed"
fi

java Library < tests/addbook_Success.in |diff - tests/addbook_Success.out
if [ $? -eq "1" ]; then
	echo "addbook_Success failed"
else
	echo "addbook_Success passed"
fi


java Library < tests/addbook_UppercaseCommand.in |diff - tests/addbook_UppercaseCommand.out
if [ $? -eq "1" ]; then
	echo "addbook_UppercaseCommand failed"
else
	echo "addbook_UppercaseCommand passed"
fi

###########
#end of addBook() testscases
###########


#################
#case for addCollection()
#################

java Library < tests/addCollection_fileNotExist.in |diff - tests/addCollection_fileNotExist.out
if [ $? -eq "1" ]; then
	echo "addCollection_fileNotExist failed"
else
	echo "addCollection_fileNotExist passed"
fi


java Library < tests/addCollection_NoBookToAdd.in |diff - tests/addCollection_NoBookToAdd.out
if [ $? -eq "1" ]; then
	echo "addCollection_NoBookToAdd failed"
else
	echo "addCollection_NoBookToAdd passed"
fi


java Library < tests/addCollection_SuccessAddAll.in |diff - tests/addCollection_SuccessAddAll.out
if [ $? -eq "1" ]; then
	echo "addCollection_SuccessAddAll failed"
else
	echo "addCollection_SuccessAddAll passed"
fi


java Library < tests/addCollection_SuccessAddOne.in |diff - tests/addCollection_SuccessAddOne.out
if [ $? -eq "1" ]; then
	echo "addCollection_SuccessAddOne failed"
else
	echo "addCollection_SuccessAddOne passed"
fi



java Library < tests/addCollection_SuccessAddSome.in |diff - tests/addCollection_SuccessAddSome.out
if [ $? -eq "1" ]; then
	echo "addCollection_SuccessAddSome failed"
else
	echo "addCollection_SuccessAddSome passed"
fi


java Library < tests/addCollection_UpperCommand.in |diff - tests/addCollection_UpperCommand.out
if [ $? -eq "1" ]; then
	echo "addCollection_UpperCommand failed"
else
	echo "addCollection_UpperCommand passed"
fi

###########
#end of addCollection() testscases
###########


#################
#case for addMember()
#################


java Library < tests/addMember_FullName.in |diff - tests/addMember_FullName.out
if [ $? -eq "1" ]; then
	echo "addMember_FullName failed"
else
	echo "addMember_FullName passed"
fi

java Library < tests/addMember_SameName.in |diff - tests/addMember_SameName.out
if [ $? -eq "1" ]; then
	echo "addMember_SameName failed"
else
	echo "addMember_SameName passed"
fi

java Library < tests/addMember_Success.in |diff - tests/addMember_Success.out
if [ $? -eq "1" ]; then
	echo "addMember_Success failed"
else
	echo "addMember_Success passed"
fi

java Library < tests/addMember_Success_numberCheck.in |diff - tests/addMember_Success_numberCheck.out
if [ $? -eq "1" ]; then
	echo "addMember_Success_numberCheck failed"
else
	echo "addMember_Success_numberCheck passed"
fi

java Library < tests/addMember_Success_numberCheckMorethanOne.in |diff - tests/addMember_Success_numberCheckMorethanOne.out
if [ $? -eq "1" ]; then
	echo "addMember_Success_numberCheckMorethanOne failed"
else
	echo "addMember_Success_numberCheckMorethanOne passed"
fi


java Library < tests/addMember_UpperCaseCommand.in |diff - tests/addMember_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "addMember_UpperCaseCommand failed"
else
	echo "addMember_UpperCaseCommand passed"
fi



##########
#end of addMember() testscases
##########


################
#case for bookhistory()
################

java Library < tests/bookhistory_bookHasBeenRentedBy2member.in |diff - tests/bookhistory_bookHasBeenRentedBy2member.out
if [ $? -eq "1" ]; then
	echo "bookhistory_bookHasBeenRentedBy2member failed"
else
	echo "bookhistory_bookHasBeenRentedBy2member passed"
fi

java Library < tests/bookhistory_bookRentedNotReturn.in |diff - tests/bookhistory_bookRentedNotReturn.out
if [ $? -eq "1" ]; then
	echo "bookhistory_bookRentedNotReturn failed"
else
	echo "bookhistory_bookRentedNotReturn passed"
fi

java Library < tests/bookhistory_bookRentedReturned.in |diff - tests/bookhistory_bookRentedReturned.out
if [ $? -eq "1" ]; then
	echo "bookhistory_bookRentedReturned failed"
else
	echo "bookhistory_bookRentedReturned passed"
fi

java Library < tests/bookhistory_Nohistory.in |diff - tests/bookhistory_Nohistory.out
if [ $? -eq "1" ]; then
	echo "bookhistory_Nohistory failed"
else
	echo "bookhistory_Nohistory passed"
fi

java Library < tests/bookhistory_Notexist.in |diff - tests/bookhistory_Notexist.out
if [ $? -eq "1" ]; then
	echo "bookhistory_Notexist failed"
else
	echo "bookhistory_Notexist passed"
fi

java Library < tests/bookhistory_SecondRenterNotReturnYet.in |diff - tests/bookhistory_SecondRenterNotReturnYet.out
if [ $? -eq "1" ]; then
	echo "bookhistory_SecondRenterNotReturnYet failed"
else
	echo "bookhistory_SecondRenterNotReturnYet passed"
fi


##########
#end of bookHistory() testscases
##########


################
#case for common()
################


java Library < tests/common_DuplicateMember.in |diff - tests/common_DuplicateMember.out
if [ $? -eq "1" ]; then
	echo "common_DuplicateMember failed"
else
	echo "common_DuplicateMember passed"
fi

java Library < tests/common_ManyMemberManyCommonBooks.in |diff - tests/common_ManyMemberManyCommonBooks.out
if [ $? -eq "1" ]; then
	echo "common_ManyMemberManyCommonBooks failed"
else
	echo "common_ManyMemberManyCommonBooks passed"
fi

java Library < tests/common_ManyMemberNoCommon.in |diff - tests/common_ManyMemberNoCommon.out
if [ $? -eq "1" ]; then
	echo "common_ManyMemberNoCommon failed"
else
	echo "common_ManyMemberNoCommon passed"
fi

java Library < tests/common_ManyMembersHasCommon.in |diff - tests/common_ManyMembersHasCommon.out
if [ $? -eq "1" ]; then
	echo "common_ManyMembersHasCommon failed"
else
	echo "common_ManyMembersHasCommon passed"
fi

java Library < tests/common_NoBooks.in |diff - tests/common_NoBooks.out
if [ $? -eq "1" ]; then
	echo "common_NoBooks failed"
else
	echo "common_NoBooks passed"
fi

java Library < tests/common_NoMembers.in |diff - tests/common_NoMembers.out
if [ $? -eq "1" ]; then
	echo "common_NoMembers failed"
else
	echo "common_NoMembers passed"
fi

java Library < tests/common_NoSuchMember.in |diff - tests/common_NoSuchMember.out
if [ $? -eq "1" ]; then
	echo "common_NoSuchMember failed"
else
	echo "common_NoSuchMember passed"
fi

java Library < tests/common_OneMember.in |diff - tests/common_OneMember.out
if [ $? -eq "1" ]; then
	echo "common_OneMember failed"
else
	echo "common_OneMember passed"
fi

java Library < tests/common_UppercaseCommand.in |diff - tests/common_UppercaseCommand.out
if [ $? -eq "1" ]; then
	echo "common_UppercaseCommand failed"
else
	echo "common_UppercaseCommand passed"
fi


##########
#end of common() testscases
##########


################
#case for getAllBooks()
################


java Library < tests/getAllBooks_HasLongInCommand.in |diff - tests/getAllBooks_HasLongInCommand.out
if [ $? -eq "1" ]; then
	echo "getAllBooks_HasLongInCommand failed"
else
	echo "getAllBooks_HasLongInCommand passed"
fi


java Library < tests/getAllBooks_NoBooks.in |diff - tests/getAllBooks_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getAllBooks_NoBooks failed"
else
	echo "getAllBooks_NoBooks passed"
fi


java Library < tests/getAllBooks_NoBooksNolong.in |diff - tests/getAllBooks_NoBooksNolong.out
if [ $? -eq "1" ]; then
	echo "getAllBooks_NoBooksNolong failed"
else
	echo "getAllBooks_NoBooksNolong passed"
fi


java Library < tests/getAllBooks_NoLongInCommand.in |diff - tests/getAllBooks_NoLongInCommand.in.out
if [ $? -eq "1" ]; then
	echo "getAllBooks_NoLongInCommand.in failed"
else
	echo "getAllBooks_NoLongInCommand.in passed"
fi


java Library < tests/getAllBooks_UppercaseCommand.in |diff - tests/getAllBooks_UppercaseCommand.out
if [ $? -eq "1" ]; then
	echo "getAllBooks_UppercaseCommand failed"
else
	echo "getAllBooks_UppercaseCommand passed"
fi

##########
#end of getAllBooks() testscases
##########


################
#case for getAuthors()
################


java Library < tests/getAuthors_AlphabeticalOrder.in |diff - tests/getAuthors_AlphabeticalOrder.out
if [ $? -eq "1" ]; then
	echo "getAuthors_AlphabeticalOrder  failed"
else
	echo "getAuthors_AlphabeticalOrder  passed"
fi


java Library < tests/getAuthors_NoBooks.in |diff - tests/getAuthors_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getAuthors_NoBooks  failed"
else
	echo "getAuthors_NoBooks  passed"
fi


java Library < tests/getAuthors_UpperCaseCommand.in |diff - tests/getAuthors_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getAuthors_UpperCaseCommand failed"
else
	echo "getAuthors_UpperCaseCommand passed"
fi

##########
#end of getAuthors() testscases
##########


################
##case for getAvailableBooks()
################


java Library < tests/getAvailableBooks_AllAvailable.in |diff - tests/getAvailableBooks_AllAvailable.out
if [ $? -eq "1" ]; then
	echo "getAvailableBooks_AllAvailable failed"
else
	echo "getAvailableBooks_AllAvailable passed"
fi


java Library < tests/getAvailableBooks_NoAvailableBooks.in |diff - tests/getAvailableBooks_NoAvailableBooks.out
if [ $? -eq "1" ]; then
	echo "getAvailableBooks_NoAvailableBooks failed"
else
	echo "getAvailableBooks_NoAvailableBooks passed"
fi


java Library < tests/getAvailableBooks_NoBooks.in |diff - tests/getAvailableBooks_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getAvailableBooks_NoBooks failed"
else
	echo "getAvailableBooks_NoBooks passed"
fi


java Library < tests/getAvailableBooks_NoLongInCommand.in |diff - tests/getAvailableBooks_NoLongInCommand.out
if [ $? -eq "1" ]; then
	echo "getAvailableBooks_NoLongInCommand failed"
else
	echo "getAvailableBooks_NoLongInCommand passed"
fi

java Library < tests/getAvailableBooks_SomeAvailable.in |diff - tests/getAvailableBooks_SomeAvailable.out
if [ $? -eq "1" ]; then
	echo "getAvailableBooks_SomeAvailable failed"
else
	echo "getAvailableBooks_SomeAvailable passed"
fi


java Library < tests/getAvailableBooks_WithLongInCommand.in |diff - tests/getAvailableBooks_WithLongInCommand.out
if [ $? -eq "1" ]; then
	echo "getAvailableBooks_WithLongInCommand failed"
else
	echo "getAvailableBooks_WithLongInCommand passed"
fi


java Library < tests/getAvailableBooks_SomeAvailableWithLongCommand.in |diff - tests/getAvailableBooks_SomeAvailableWithLongCommand.out
if [ $? -eq "1" ]; then
	echo "getAvailableBooks_SomeAvailableWithLongCommand failed"
else
	echo "getAvailableBooks_SomeAvailableWithLongCommand passed"
fi

##########
#end of getAvailableBooks() testscases
##########


################
#case for getBook()
################


java Library < tests/getBook_CommandWithLong.in |diff - tests/getBook_CommandWithLong.out
if [ $? -eq "1" ]; then
	echo "getBook_CommandWithLong failed"
else
	echo "getBook_CommandWithLong passed"
fi


java Library < tests/getBook_NoBooks.in |diff - tests/getBook_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getBook_NoBooks failed"
else
	echo "getBook_NoBooks passed"
fi


java Library < tests/getBook_NoLongInCommand.in |diff - tests/getBook_NoLongInCommand.out
if [ $? -eq "1" ]; then
	echo "getBook_NoLongInCommand failed"
else
	echo "getBook_NoLongInCommand passed"
fi


java Library < tests/getBook_NoSuchBook.in |diff - tests/getBook_NoSuchBook.out
if [ $? -eq "1" ]; then
	echo "getBook_NoSuchBook failed"
else
	echo "getBook_NoSuchBook passed"
fi

java Library < tests/getBook_UpperCaseCommand.in |diff - tests/getBook_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getBook_UpperCaseCommand failed"
else
	echo "getBook_UpperCaseCommand passed"
fi

# ##########
# #end of getBook() testscases
# ##########


# ################
# #case for getBooksByAuthor()
# ################


java Library < tests/getBooksByAuthor_NoBooks.in |diff - tests/getBooksByAuthor_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getBooksByAuthor_NoBooks failed"
else
	echo "getBooksByAuthor_NoBooks passed"
fi


java Library < tests/getBooksByAuthor_NoBooksByTarget.in |diff - tests/getBooksByAuthor_NoBooksByTarget.out
if [ $? -eq "1" ]; then
	echo "getBooksByAuthor_NoBooksByTarget failed"
else
	echo "getBooksByAuthor_NoBooksByTarget passed"
fi

java Library < tests/getBooksByAuthor_OrderBySerialNumber.in |diff - tests/getBooksByAuthor_OrderBySerialNumber.out
if [ $? -eq "1" ]; then
	echo "getBooksByAuthor_OrderBySerialNumber failed"
else
	echo "getBooksByAuthor_OrderBySerialNumber passed"
fi

java Library < tests/getBooksByAuthor_UpperCaseCommand.in |diff - tests/getBooksByAuthor_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getBooksByAuthor_UpperCaseCommand failed"
else
	echo "getBooksByAuthor_UpperCaseCommand passed"
fi

# ##########
# #end of getBooksByAuthor() testscases
# ##########


# ################
# #case for getBooksByGenre()
# ################


java Library < tests/getBooksByGenre_NoBooks.in |diff - tests/getBooksByGenre_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getBooksByGenre_NoBooks failed"
else
	echo "getBooksByGenre_NoBooks passed"
fi


java Library < tests/getBooksByGenre_NoBooksByTarget.in |diff - tests/getBooksByGenre_NoBooksByTarget.out
if [ $? -eq "1" ]; then
	echo "getBooksByGenre_NoBooksByTarget failed"
else
	echo "getBooksByGenre_NoBooksByTarget passed"
fi


java Library < tests/getBooksByGenre_OrderBySerialNumber.in |diff - tests/getBooksByGenre_OrderBySerialNumber.out
if [ $? -eq "1" ]; then
	echo "getBooksByGenre_OrderBySerialNumber failed"
else
	echo "getBooksByGenre_OrderBySerialNumber passed"
fi


java Library < tests/getBooksByGenre_UpperCaseCommand.in |diff - tests/getBooksByGenre_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getBooksByGenre_UpperCaseCommand failed"
else
	echo "getBooksByGenre_UpperCaseCommand passed"
fi

# ##########
# #end of getBooksByGenre() testscases
# ##########


# ################
# #case for getCopies()
# ################


java Library < tests/getCopies_NoBooks.in |diff - tests/getCopies_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getCopies_NoBooks failed"
else
	echo "getCopies_NoBooks passed"
fi


java Library < tests/getCopies_OutputInLexicograpthicallyOrder.in |diff - tests/getCopies_OutputInLexicograpthicallyOrder.out
if [ $? -eq "1" ]; then
	echo "getCopies_OutputInLexicograpthicallyOrder failed"
else
	echo "getCopies_OutputInLexicograpthicallyOrder passed"
fi


java Library < tests/getCopies_UpperCaseCommand.in |diff - tests/getCopies_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getCopies_UpperCaseCommand failed"
else
	echo "getCopies_UpperCaseCommand passed"
fi

# ##########
# #end of getCopies() testscases
# ##########


# ################
# #case for getGenres()
# ################


java Library < tests/getGenres_AlphabeticalOrder.in |diff - tests/getGenres_AlphabeticalOrder.out
if [ $? -eq "1" ]; then
	echo "getGenres_AlphabeticalOrder failed"
else
	echo "getGenres_AlphabeticalOrder passed"
fi

java Library < tests/getGenres_NoBooks.in |diff - tests/getGenres_NoBooks.out
if [ $? -eq "1" ]; then
	echo "getGenres_NoBooks failed"
else
	echo "getGenres_NoBooks passed"
fi

java Library < tests/getGenres_UpperCaseCommand.in |diff - tests/getGenres_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getGenres_UpperCaseCommand failed"
else
	echo "getGenres_UpperCaseCommand passed"
fi

# ##########
# #end of getGenres() testscases
# ##########


# ################
# #case for getMember()
# ################


java Library < tests/getMember_MemberExist.in |diff - tests/getMember_MemberExist.out
if [ $? -eq "1" ]; then
	echo "getMember_MemberExist failed"
else
	echo "getMember_MemberExist passed"
fi


java Library < tests/getMember_NoMembers.in |diff - tests/getMember_NoMembers.out
if [ $? -eq "1" ]; then
	echo "getMember_NoMembers failed"
else
	echo "getMember_NoMembers passed"
fi


java Library < tests/getMember_NoSuchMember.in |diff - tests/getMember_NoSuchMember.out
if [ $? -eq "1" ]; then
	echo "getMember_NoSuchMember failed"
else
	echo "getMember_NoSuchMember passed"
fi


java Library < tests/getMember_UpperCaseCommand.in |diff - tests/getMember_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getMember_UpperCaseCommand failed"
else
	echo "getMember_UpperCaseCommand passed"
fi


# ##########
# #end of getMember() testscases
# ##########


# ################
# #case for getMemberBooks()
# ################


java Library < tests/getMemberBooks​_MemberNormalRenting.in |diff - tests/getMemberBooks​_MemberNormalRenting.out
if [ $? -eq "1" ]; then
	echo "getMemberBooks​_MemberNormalRenting failed"
else
	echo "getMemberBooks​_MemberNormalRenting passed"
fi


java Library < tests/getMemberBooks​_MemberNotRenting.in |diff - tests/getMemberBooks​_MemberNotRenting.out
if [ $? -eq "1" ]; then
	echo "getMemberBooks​_MemberNotRenting failed"
else
	echo "getMemberBooks​_MemberNotRenting passed"
fi


java Library < tests/getMemberBooks​_NoMember.in |diff - tests/getMemberBooks​_NoMember.out
if [ $? -eq "1" ]; then
	echo "getMemberBooks​_NoMember failed"
else
	echo "getMemberBooks​_NoMember passed"
fi


java Library < tests/getMemberBooks​_NoSuchMember.in |diff - tests/getMemberBooks​_NoSuchMember.out
if [ $? -eq "1" ]; then
	echo "getMemberBooks​_NoSuchMember failed"
else
	echo "getMemberBooks​_NoSuchMember passed"
fi


java Library < tests/getMemberBooks​_TwoMemberRenting.in |diff - tests/getMemberBooks​_TwoMemberRenting.out
if [ $? -eq "1" ]; then
	echo "getMemberBooks​_TwoMemberRenting failed"
else
	echo "getMemberBooks​_TwoMemberRenting passed"
fi


java Library < tests/getMemberBooks_UpperCaseCommand.in |diff - tests/getMemberBooks_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "getMemberBooks_UpperCaseCommand failed"
else
	echo "getMemberBooks_UpperCaseCommand passed"
fi




# ##########
# #end of getMemberBooks() testscases
# ##########


# ################
# #case for memberRentalHistory()
# ################


java Library < tests/memberRentalHistory_InRentingOrder.in |diff - tests/memberRentalHistory_InRentingOrder.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_InRentingOrder failed"
else
	echo "memberRentalHistory_InRentingOrder passed"
fi


java Library < tests/memberRentalHistory_MemberRentAndReturned.in |diff - tests/memberRentalHistory_MemberRentAndReturned.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_MemberRentAndReturned failed"
else
	echo "memberRentalHistory_MemberRentAndReturned passed"
fi


java Library < tests/memberRentalHistory_MemberRentButNotReturn.in |diff - tests/memberRentalHistory_MemberRentButNotReturn.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_MemberRentButNotReturn failed"
else
	echo "memberRentalHistory_MemberRentButNotReturn passed"
fi


java Library < tests/memberRentalHistory_NoHistory.in |diff - tests/memberRentalHistory_NoHistory.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_NoHistory failed"
else
	echo "memberRentalHistory_NoHistory passed"
fi

java Library < tests/memberRentalHistory_NoMembers.in |diff - tests/memberRentalHistory_NoMembers.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_NoMembers failed"
else
	echo "memberRentalHistory_NoMembers passed"
fi

java Library < tests/memberRentalHistory_NormalHistory.in |diff - tests/memberRentalHistory_NormalHistory.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_NormalHistory failed"
else
	echo "memberRentalHistory_NormalHistory passed"
fi

java Library < tests/memberRentalHistory_NoSuchMember.in |diff - tests/memberRentalHistory_NoSuchMember.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_NoSuchMember failed"
else
	echo "memberRentalHistory_NoSuchMember passed"
fi

java Library < tests/memberRentalHistory_UpperCaseCommand.in |diff - tests/memberRentalHistory_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "memberRentalHistory_UpperCaseCommand failed"
else
	echo "memberRentalHistory_UpperCaseCommand passed"
fi



# ##########
# #end of memberRentalHistory() testscases
# ##########


# ################
# #case for relinquishAll()
# ################

java Library < tests/relinquishAll_NoMembers.in |diff - tests/relinquishAll_NoMembers.out
if [ $? -eq "1" ]; then
	echo "relinquishAll_NoMembers failed"
else
	echo "relinquishAll_NoMembers passed"
fi

java Library < tests/relinquishAll_NoSuchMember.in |diff - tests/relinquishAll_NoSuchMember.out
if [ $? -eq "1" ]; then
	echo "relinquishAll_NoSuchMember failed"
else
	echo "relinquishAll_NoSuchMember passed"
fi

java Library < tests/relinquishAll_RentBeforeRElinquish.in |diff - tests/relinquishAll_RentBeforeRElinquish.out
if [ $? -eq "1" ]; then
	echo "relinquishAll_RentBeforeRElinquish failed"
else
	echo "relinquishAll_RentBeforeRElinquish passed"
fi

java Library < tests/relinquishAll_Success.in |diff - tests/relinquishAll_Success.out
if [ $? -eq "1" ]; then
	echo "relinquishAll_Success failed"
else
	echo "relinquishAll_Success passed"
fi

java Library < tests/relinquishAll_UpperCaseCommand.in |diff - tests/relinquishAll_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "relinquishAll_UpperCaseCommand failed"
else
	echo "relinquishAll_UpperCaseCommand passed"
fi


# ##########
# #end of relinquishAll() testscases
# ##########


# ################
# #case for relinquishBook()
# ################


java Library < tests/relinquishBook_NoBooks.in |diff - tests/relinquishBook_NoBooks.out
if [ $? -eq "1" ]; then
	echo "relinquishBook_NoBooks failed"
else
	echo "relinquishBook_NoBooks passed"
fi

java Library < tests/relinquishBook_NoMember.in |diff - tests/relinquishBook_NoMember.out
if [ $? -eq "1" ]; then
	echo "relinquishBook_NoMember failed"
else
	echo "relinquishBook_NoMember passed"
fi

java Library < tests/relinquishBook_NoSuchBook.in |diff - tests/relinquishBook_NoSuchBook.out
if [ $? -eq "1" ]; then
	echo "relinquishBook_NoSuchBook failed"
else
	echo "relinquishBook_NoSuchBook passed"
fi

java Library < tests/relinquishBook_NoSuchMember.in |diff - tests/relinquishBook_NoSuchMember.out
if [ $? -eq "1" ]; then
	echo "relinquishBook_NoSuchMember failed"
else
	echo "relinquishBook_NoSuchMember passed"
fi

java Library < tests/relinquishBook_Success.in |diff - tests/relinquishBook_Success.out
if [ $? -eq "1" ]; then
	echo "relinquishBook_Success failed"
else
	echo "relinquishBook_Success passed"
fi

java Library < tests/relinquishBook_UnableReturn.in |diff - tests/relinquishBook_UnableReturn.out
if [ $? -eq "1" ]; then
	echo "relinquishBook_UnableReturn failed"
else
	echo "relinquishBook_UnableReturn passed"
fi

java Library < tests/relinquishBook_UpperCaseCommand.in |diff - tests/relinquishBook_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "relinquishBook_UpperCaseCommand failed"
else
	echo "relinquishBook_UpperCaseCommand passed"
fi


# ##########
# #end of relinquishBook() testscases
# ##########


# ################
# #case for rentBook()
# ################

java Library < tests/rentBook_BookUnabailable.in |diff - tests/rentBook_BookUnabailable.out
if [ $? -eq "1" ]; then
	echo "rentBook_BookUnabailable failed"
else
	echo "rentBook_BookUnabailable passed"
fi

java Library < tests/rentBook_NoBooks.in |diff - tests/rentBook_NoBooks.out
if [ $? -eq "1" ]; then
	echo "rentBook_NoBooks failed"
else
	echo "rentBook_NoBooks passed"
fi

java Library < tests/rentBook_NoMembers.in |diff - tests/rentBook_NoMembers.out
if [ $? -eq "1" ]; then
	echo "rentBook_NoMembers failed"
else
	echo "rentBook_NoMembers passed"
fi

java Library < tests/rentBook_NoSuchBook.in |diff - tests/rentBook_NoSuchBook.out
if [ $? -eq "1" ]; then
	echo "rentBook_NoSuchBook failed"
else
	echo "rentBook_NoSuchBook passed"
fi

java Library < tests/rentBook_NoSuchMember.in |diff - tests/rentBook_NoSuchMember.out
if [ $? -eq "1" ]; then
	echo "rentBook_NoSuchMember failed"
else
	echo "rentBook_NoSuchMember passed"
fi

java Library < tests/rentBook_Success.in |diff - tests/rentBook_Success.out
if [ $? -eq "1" ]; then
	echo "rentBook_Success failed"
else
	echo "rentBook_Success passed"
fi

java Library < tests/rentBook_UpperCaseCommand.in |diff - tests/rentBook_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "rentBook_UpperCaseCommand failed"
else
	echo "rentBook_UpperCaseCommand passed"
fi


# ##########
# #end of rentBook() testscases
# ##########


# ################
# #case for saveCollection()
# ################

java Library < tests/saveCollection_CheckSuccessByAddCollection.in |diff - tests/saveCollection_CheckSuccessByAddCollection.out
if [ $? -eq "1" ]; then
	echo "saveCollection_CheckSuccessByAddCollection failed"
else
	echo "saveCollection_CheckSuccessByAddCollection passed"
fi


java Library < tests/saveCollection_NoBooks.in |diff - tests/saveCollection_NoBooks.out
if [ $? -eq "1" ]; then
	echo "saveCollection_NoBooks failed"
else
	echo "saveCollection_NoBooks passed"
fi


java Library < tests/saveCollection_Success.in |diff - tests/saveCollection_Success.out
if [ $? -eq "1" ]; then
	echo "saveCollection_Success failed"
else
	echo "saveCollection_Success passed"
fi


java Library < tests/saveCollection_UpperCaseCommand.in |diff - tests/saveCollection_UpperCaseCommand.out
if [ $? -eq "1" ]; then
	echo "saveCollection_UpperCaseCommand failed"
else
	echo "saveCollection_UpperCaseCommand passed"
fi



# ##########
# #end of saveCollection() testscases
# ##########


# ################
# #case for The Integration
# ################

java Library < tests/TheIntegration_1.in |diff - tests/TheIntegration_1.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_1 failed"
else
	echo "TheIntegration_1 passed"
fi

java Library < tests/TheIntegration_2.in |diff - tests/TheIntegration_2.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_2 failed"
else
	echo "TheIntegration_2 passed"
fi

java Library < tests/TheIntegration_3.in |diff - tests/TheIntegration_3.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_3 failed"
else
	echo "TheIntegration_3 passed"
fi

java Library < tests/TheIntegration_4.in |diff - tests/TheIntegration_4.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_4 failed"
else
	echo "TheIntegration_4 passed"
fi

java Library < tests/TheIntegration_5.in |diff - tests/TheIntegration_5.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_5 failed"
else
	echo "TheIntegration_5 passed"
fi

java Library < tests/TheIntegration_6.in |diff - tests/TheIntegration_6.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_6 failed"
else
	echo "TheIntegration_6 passed"
fi

java Library < tests/TheIntegration_7.in |diff - tests/TheIntegration_7.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_7 failed"
else
	echo "TheIntegration_7 passed"
fi


java Library < tests/TheIntegration_8.in |diff - tests/TheIntegration_8.out
if [ $? -eq "1" ]; then
	echo "TheIntegration_8 failed"
else
	echo "TheIntegration_8 passed"
fi


java Library < tests/HelpCommand.in |diff - tests/HelpCommand.out
if [ $? -eq "1" ]; then
	echo "HelpCommand failed"
else
	echo "HelpCommand passed"
fi




############################End##########################