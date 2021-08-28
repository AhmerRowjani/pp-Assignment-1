package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title; //changed 'tItLe' to 'title'
	private String author;  //changed 'AuThOr' to 'author'
	private String callNo;  //changed 'CALLNO' to 'callNo'
	private int id;  //changed 'iD' to 'id'
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; //changed 'sTaTe' to 'State'
	private State state;  //changed 'StAtE' to 'state'
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;  //changed 'AuThOr' to 'author'
		this.title = title;  //changed 'tItLe' to 'title'
		this.callNo = callNo;  //changed 'CALLNO' to 'callNo'
		this.id = id;  //changed 'iD' to 'id'
		this.state = State.AVAILABLE;  //changed 'sTaTe.AVAILABLE' to 'State.AVAILABLE' and 'this.StAtE' to 'this.state'
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(iD).append("\n")
		  .append("  Title:  ").append(tItLe).append("\n")
		  .append("  Author: ").append(AuThOr).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(StAtE);
		
		return sb.toString();
	}

	public Integer getId() {  //changed 'gEtId' to 'getId'
		return id;  //changed 'iD' to 'id'
	}

	public String getTitle() {  //changed 'gEtTiTlE' to 'getTitle'
		return title;  //changed 'tItLe' to 'title'
	}


	
	public boolean isAvailable() {  //changed 'iS_AvAiLaBlE' to 'isAvailable'
		return state == State.AVAILABLE;   //changed 'sTaTe.AVAILABLE' to 'State.AVAILABLE' and 'StAtE' to 'state'
	}

	
	public boolean isOnLoan() {  //changed 'iS_On_LoAn' to 'isOnLoan'
		return state == State.ON_LOAN;  //changed 'sTaTe.ON_LOAN' to 'State.ON_LOAN' and 'StAtE' to 'state'
	}

	
	public boolean isDamaged() {  //changed 'iS_DaMaGeD' to 'isDamaged'
		return state == State.DAMAGED;  //changed 'sTaTe.DAMAGED' to 'State.DAMAGED' and 'StAtE' to 'state'
	}

	
	public void borrow() {  //changed 'BoRrOw' to 'borrow'
		if (state.equals(State.AVAILABLE)) { //changed 'StAtE.equals' to 'state.equals' and changed 'sTaTe.AVAILABLE' to 'State.AVAILABLE'
			state = State.ON_LOAN;  //changed 'sTaTe.ON_LOAN' to 'State.ON_LOAN' and 'StAtE' to 'state'
		}  //added curly brackets 
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));  //changed 'StAtE' to 'state'
		
		
	}


	public void return(boolean damaged) {  //changed 'ReTuRn' to 'return' and 'DaMaGeD to 'damaged'
		if (state.equals(State.ON_LOAN)) { //changed 'StAtE.equals' to 'state.equals' and 'sTaTe.ON_LOAN' to 'State.ON_LOAN'
			if (damaged) { //changed 'DaMaGeD to 'damaged'
				state = State.DAMAGED;  //changed 'sTaTe.DAMAGED' to 'State.DAMAGED' and 'StAtE' to 'state'
			}  //added curly brackets
			else 
				state = State.AVAILABLE;   //changed 'sTaTe.AVAILABLE' to 'State.AVAILABLE' and 'StAtE' to 'state'
			
		}  //added curly brackets
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));  //changed 'StAtE' to 'state'
				
	}

	
	public void repair() {  //cahnged 'RePaIr' to 'repair'
		if (state.equals(State.DAMAGED)) {  //changed 'StAtE.equals' to 'state.equals' and changed 'sTaTe.DAMAGED' to 'State.DAMAGED'
			state = State.AVAILABLE;  //changed 'sTaTe.AVAILABLE' to 'State.AVAILABLE' and 'StAtE' to 'state'
		}  //added curly brackets
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));   //changed 'StAtE' to 'state'
		
	}


}
