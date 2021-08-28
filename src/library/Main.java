package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.BorrowBookControl;  //changed bORROW_bOOK_cONTROL to BorrowBookControl
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.FixBookControl;  //changed fIX_bOOK_cONTROL to FixBookControl
import library.payfine.PayFineUI;
import library.payfine.PayFineControl;  //changed pAY_fINE_cONTROL to PayFineControl
import library.returnBook.ReturnBookUI;
import library.returnBook.ReturnBookControl;  //changed rETURN_bOOK_cONTROL to ReturnBookControl


public class Main {
	
	private static Scanner IN;
	private static Library LIB;
	private static String MENU;
	private static Calendar CAL;
	private static SimpleDateFormat SDF;
	
	
	private static String getMenu() {  //changed Get_menu to getMenu
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			IN = new Scanner(System.in);
			LIB = Library.getInstance();  //changed GeTiNsTaNcE to getInstance
			CAL = Calendar.getInstance();  //changed gEtInStAnCe to getInstance
			SDF = new SimpleDateFormat("dd/MM/yyyy");
	
			for (Member m : LIB.listMembers()) {  //changed lIsT_MeMbErS to listMembers
				output(m);
			}
			output(" ");
			for (Book b : LIB.listBooks()) { //changed lIsT_BoOkS to listBooks
				output(b);
			}
						
			MENU = getMenu();  //changed Get_menu to getMenu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.getDate()));  //changed gEt_DaTe to getDate
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					ADD_MEMBER();
					break;
					
				case "LM": 
					LIST_MEMBERS();
					break;
					
				case "B": 
					ADD_BOOK();
					break;
					
				case "LB": 
					LIST_BOOKS();
					break;
					
				case "FB": 
					FIX_BOOKS();
					break;
					
				case "L": 
					BORROW_BOOK();
					break;
					
				case "R": 
					RETURN_BOOK();
					break;
					
				case "LL": 
					LIST_CURRENT_LOANS();
					break;
					
				case "P": 
					PAY_FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.SaVe();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void PAY_FINES() {
		new PayFineUI(new payFineControl()).run();  //changed pAY_fINE_cONTROL to payFineControl and RuN to run		
	}


	private static void LIST_CURRENT_LOANS() {
		output("");
		for (Loan loan : LIB.listCurrentLoans()) {  //changed lISt_CuRrEnT_LoAnS to listCurrentLoans
			output(loan + "\n");
		}		
	}



	private static void LIST_BOOKS() {
		output("");
		for (Book book : LIB.listBooks()) {  //changed lIsT_BoOkS to listBooks
			output(book + "\n");
		}		
	}



	private static void LIST_MEMBERS() {
		output("");
		for (Member member : LIB.listMembers()) {  //changed lIsT_MeMbErS to listMembers
			output(member + "\n");
		}		
	}



	private static void BORROW_BOOK() {
		new BorrowBookUI(new borrowBookControl()).run();  //changed bORROW_bOOK_cONTROL to borrowBookControl and RuN to run		
	}


	private static void RETURN_BOOK() {
		new ReturnBookUI(new returnBookControl()).run();  //changed rETURN_bOOK_cONTROL to returnBookControl and RuN to run 		
	}


	private static void FIX_BOOKS() {
		new FixBookUI(new fixBookControl()).run();	//changed fIX_bOOK_cONTROL to fixBookControl and RuN to run	
	}


	private static void INCREMENT_DATE() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();  //changed cHeCk_CuRrEnT_LoAnS to checkCurrentLoans
			output(SDF.format(CAL.getDate()));  //changed gEt_DaTe to getDate
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String author = input("Enter author: ");  //changed AuThOr to author
		String title  = input("Enter title: ");  //changed TiTlE to title
		String callNumber = input("Enter call number: ");  //changed CaLl_NuMbEr to callNumber
		Book book = LIB.addBook(author, title, callNumber);  //changed AuThOr to author and TiTlE to title and CaLl_NuMbEr to callNumber and aDd_BoOk to addBook and BoOk to book
		output("\n" + book + "\n"); //changed BoOk to book
		
	}

	
	private static void ADD_MEMBER() {
		try {
			String lastName = input("Enter last name: ");  //changed LaSt_NaMe to lastName
			String firstName  = input("Enter first name: ");  //changed FiRsT_NaMe to firstName
			String emailAddress = input("Enter email address: ");  //changed EmAiL_AdDrEsS to emailAddress
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue();  //changed PhOnE_NuMbEr to phoneNumber
			Member member = LIB.addMember(lastName, firstName, emailAddress, phoneNumber);  //changed LaSt_NaMe to lastName and FiRsT_NaMe to firstName and EmAiL_AdDrEsS to emailAddress and PhOnE_NuMbEr to phoneNumber and MeMbEr to member and aDd_MeMbEr to addMember
			output("\n" + member + "\n");  //changed MeMbEr to member
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
