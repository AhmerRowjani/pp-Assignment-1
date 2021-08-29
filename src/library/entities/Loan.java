package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };  //changed 'lOaN_sTaTe' to 'LoanState'
	
	private int loanId;  //changed 'LoAn_Id' to 'loanId'
	private Book book;  //changed 'BoOk' to 'book'
	private Member member;  //changed 'MeMbEr' to 'member'
	private Date date;  //changed 'DaTe' to 'date'
	private LoanState state;  //changed 'lOaN_sTaTe' to 'LoanState' and 'StAtE' to 'state'

	
	public Loan(int loanId, Book bOoK, Member mEmBeR, Date dueDate) {  ////changed 'BoOk' to 'book' and changed 'MeMbEr' to 'member' and DuE_dAtE to 'dueDate'
		this.LoAn_Id = loanId; //changed 'LoAn_Id' to 'loanId'
		this.BoOk = book;  //changed 'BoOk' to 'book'
		this.MeMbEr = member;  //changed 'MeMbEr' to 'member'
		this.DaTe = dueDate;  //changed DuE_dAtE to dueDate
		this.state = loanState.current;  //changed 'StAtE' to 'state' and lOaN_sTaTe.CURRENT to loanState.current

	}

	
	public void checkOverDue() {  //changed 'cHeCk_OvEr_DuE' to 'checkOverDue'
		if (state == loanState.current &&  //changed 'StAtE' to 'state' and ' lOaN_sTaTe.CURRENT to loanState.current
			Calendar.getInstance().getDate().after(date)) {  //changed Calendar.gEtInStAnCe to Calender.getInstance and gEt_DaTe to getDate and DaTe to date
			this.state = loanState.overDue;  //changed 'StAtE' to 'state' and lOaN_sTaTe.OVER_DUE to loanState.overDue			
		}  //added curly brackets
	}

	
	public boolean isOverDue() {  //changed Is_OvEr_DuE to isOverDue
		return state == loanState.overDue;  //changed 'StAtE' to 'state' and lOaN_sTaTe.OVER_DUE to loanState.overDue
	}

	
	public Integer getId() {  //Changed GeT_Id to getId
		return loanId;  //changed LoAn_Id to loanId
	}


	public Date getDueDate() {  //changed GeT_DuE_DaTe to getDueDate
		return date;  //changed DaTe to date
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")  //changed LoAn_Id to loanId
		  .append("  Borrower ").append(member.getId()).append(" : ")  //changed MeMbEr.GeT_ID to member.getId 
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n")  //changed MeMbEr.GeT_LaSt_NaMe to member.getLastName and MeMbEr.GeT_FiRsT_NaMe to member.getFirstName
		  .append("  Book ").append(book.getId()).append(" : " )  //changed BoOk.gEtId to book.getId
		  .append(book.getTitle()).append("\n")  //changed BoOk.gEtTiTlE to book.getTitle
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")  //changed DaTe to date
		  .append("  State: ").append(state);	//changed 'StAtE' to 'state'	
		return sb.toString();
	}


	public Member getMember() {  //changed 'GeT_MeMbEr' to 'getMember'
		return member;  ////changed 'MeMbEr' to 'member'
	}


	public Book getBook() {  //changed GeT_BoOk to getBook
		return book;  //changed BoOk to book
	}


	public void discharge() {  //changed DiScHaRgE to discharge
		state = loanState.DISCHARGED;  //changed 'StAtE' to 'state' and lOaN_sTaTe.DISCHARGED to loanState.DISCHARGED
	}

}
