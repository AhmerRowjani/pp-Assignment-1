package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl {  //changed 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'

	private ReturnBookUI uI;  //changed 'Ui' to 'uI'
	private enum ControlState { INITIALISED, READY, INSPECTING };  //changed 'cOnTrOl_sTaTe' to 'ControlState'
	private ControlState state;  //changed 'sTaTe' to 'state'
	
	private Library library;  //changed 'lIbRaRy' to 'library'
	private Loan currentLoan;  //changed 'CurrENT_loan' to 'currentLoan'
	

	public ReturnBookControl() {  //changed 'rETURN_bOOK_cONTROL' to 'ReturnBookControl'
		this.library = Library.getInstance();  //changed 'this.lIbRaRy' to 'this.library' and 'Library.GeTiNsTaNcE' to 'Library.getInstance'
		state = ControlState.INITIALISED;   //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState'
	}
	
	
	public void setUI(ReturnBookUI uI) {  //changed 'sEt_uI' to 'setUI' 
		if (!state.equals(ControlState.INITIALISED)){   //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState'
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}  //added curly brackets
		this.uI = uI;  //changed 'Ui' to 'uI'
		uI.setState(ReturnBookUI.uIState.READY);  //changed 'sEt_sTaTe' to 'setState' and uI_sTaTe to 'uIState'
		state = ControlState.READY;  //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState'  		
	}


	public void bookScanned(int bookId) {  //changed 'bOoK_sCaNnEd' to 'bookScanned' and 'bOoK_iD' to 'bookId'
		if (!state.equals(ControlState.READY)) { //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState' 
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}  //added curly brackets
		Book currentBook = Library.getBook(bookId);  //changed 'cUrReNt_bOoK' to 'currentBook' and 'lIbRaRy.gEt_BoOk' to Library.getBook and 'bOoK_iD' to 'bookId'
		
		if (currentBook == null) {  //changed 'cUrReNt_bOoK' to 'currentBook'
			uI.display("Invalid Book Id");  //changed 'Ui.DiSpLaY' to 'uI.display'
			return;
		}
		if (!currentBook.isOnLoan()) {  //changed 'cUrReNt_bOoK.iS_On_LoAn' to 'currentBook.isOnLoan'
			uI.display("Book has not been borrowed");  //changed 'Ui.DiSpLaY' to 'uI.display'
			return;
		}		
		currentLoan = Library.getLoanByBookId(bookId);  //changed 'CurrENT_loan' to 'currentLoan' and 'lIbRaRy.GeT_LoAn_By_BoOkId' to 'Library.getLoanByBookId' and 'bOoK_iD' to 'bookId' 	
		double overDueFine = 0.0;  //changed 'Over_Due_Fine' to overDueFine
		if (currentLoan.isOverDue()) {  //changed 'CurrENT_loan.Is_OvEr_DuE' to 'currentLoan.isOverDue'
			overDueFine = Library.calculateOverDueFine(currentLoan);   //changed 'Over_Due_Fine' to overDueFine and 'lIbRaRy.CaLcUlAtE_OvEr_DuE_FiNe(CurrENT_loan)' to 'Library.calculateOverDueFine(currentLoan)'
		}  //added curly brackets
		uI.display("Inspecting");  //changed 'Ui.DiSpLaY' to 'uI.display'
		uI.display(currentBook.toString());  //changed 'Ui.DiSpLaY(cUrReNt_bOoK.toString())' to 'uI.display(currentBook.toString())'
		uI.display(currentLoan.toString());  //changed 'Ui.DiSpLaY(CurrENT_loan.toString())' to 'uI.display(currentLoan.toString())'
		
		if (currentLoan.isOverDue()) {  //changed 'CurrENT_loan.Is_OvEr_DuE' to 'currentLoan.isOverDue'
			uI.display(String.format("\nOverdue fine : $%.2f", overDueFine));  //changed 'Over_Due_Fine' to overDueFine and 'Ui.DiSpLaY' to 'uI.display'
		}  //added curly brackets
		uI.setState(ReturnBookUI.uIState.INSPECTING);  //changed 'Ui.sEt_sTaTe' to 'uI.setState' and uI_sTaTe to 'uIState'
		state = ControlState.INSPECTING;  //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState' 		
	}


	public void scanningComplete() {  //changed 'sCaNnInG_cOmPlEtE' to scanningComplete'
	        if (!state.equals(ControlState.READY)) { //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState' 
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}  //added curly brackets
		uI.setState(ReturnBookUI.uIState.COMPLETED);  //changed 'Ui.sEt_sTaTe' to 'uI.setState' and uI_sTaTe to 'uIState'	
	}


	public void dischargeLoan(boolean isDamaged) {  //changed 'dIsChArGe_lOaN' to 'dischargeLoan' and 'iS_dAmAgEd' to 'isDamaged'
		if (!state.equals(ControlState.INSPECTING)) { //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState' 
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}  //added curly brackets
		Library.dischargeLoan(currentLoan, isDamaged);  //changed 'CurrENT_loan' to 'currentLoan' and 'lIbRaRy.dIsChArGe_lOaN' to 'Library.dischargeLoan' and 'iS_dAmAgEd' to 'isDamaged'
		currentLoan = null;  ////changed 'CurrENT_loan' to 'currentLoan'
		uI.setState(ReturnBookUI.uIState.READY);  //changed 'Ui.sEt_sTaTe' to 'uI.setState' and uI_sTaTe to 'uIState'
		state = ControlState.READY;  //changed 'sTaTe' to 'state' and changed 'cOnTrOl_sTaTe' to 'ControlState' 				
	}


}
