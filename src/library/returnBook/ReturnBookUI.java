package library.returnBook;
import java.util.Scanner;


public class ReturnBookUI {

	public static enum UIState { INITIALISED, READY, INSPECTING, COMPLETED };  //changed 'uI_sTaTe' to 'UIState'

	private ReturnBookControl control;  //changed 'rETURN_bOOK_cONTROL' to ReturnBookControl' and 'CoNtRoL' to 'control'
	private Scanner input;  //changed iNpUt to input
	private UIState state; //changed 'uI_sTaTe' to 'UIState' and 'StATe' to state


	
	public ReturnBookUI(ReturnBookControl control) {   //changed 'rETURN_bOOK_cONTROL' to ReturnBookControl' and 'CoNtRoL' to 'control'
		this.control = Control;  //changed 'CoNtRoL' to 'control' and cOnTrOL' to Control
		input = new Scanner(System.in);  //changed iNpUt to input
		state = UIState.INITIALISED;  //changed 'uI_sTaTe' to 'UIState' and 'StATe' to state
		Control.setUI(this);  //changed 'cOnTrOL.sEt_uI'to 'Control.setUI'
	}


	public void run() {  //changed RuN to run		
		output("Return Book Use Case UI\n");  //changed oUtPuT to output
		
		while (true) {
			
			switch (state) {  //changed 'StATe' to state

			
			case INITIALISED:
				break;
				
			case READY:
				String bookInputString = input("Scan Book (<enter> completes): "); //changed BoOk_InPuT_StRiNg to bookInputString and iNpUt to input
				if (bookInputString.length() == 0) { //changed BoOk_InPuT_StRiNg.length to bookInputString.length
					control.scanningComplete();  //changed CoNtRoL.sCaNnInG_cOmPlEtE to control.scanningComplete
				}  //added curly bracket
				else {
					try {
						int Book_Id = Integer.valueOf(bookInputString).intValue();  //changed Book_Id to bookId and BoOk_InPuT_StRiNg to bookInputString
						 control.bookScanned(bookId);  //changed CoNtRoL.bOoK_sCaNnEd to control.bookScanned and Book_Id to bookId 
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");  //changed oUtPuT to output
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");  //changed AnS to ans and iNpUt to input
				boolean isDamaged = false;  //changed Is_DAmAgEd to isDamaged
				if (ans.toUpperCase().equals("Y"))  //changed AnS to ans					
					isDamaged = true;  //changed Is_DAmAgEd to isDamaged
				
				control.dischargeLoan(isDamaged);  //changed Is_DAmAgEd to isDamaged and CoNtRoL.dIsChArGe_lOaN to control.dischargeLoan 
			
			case COMPLETED:
				output("Return processing complete");  //changed oUtPuT to output
				return;
			
			default:
				output("Unhandled state");  //changed oUtPuT to output
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);	 //changed 'StATe' to state		
			}
		}
	}

	
	private String input(String prompt) { //changed PrOmPt to prompt and iNpUt to input
		System.out.print(prompt);  //changed PrOmPt to prompt
		return input.nextLine();  //changed iNpUt to input
	}	
		
		
	private void output(Object object) {  //changed oUtPuT to output and ObJeCt to object
		System.out.println(object);  //changed ObJeCt to object
	}
	
			
	public void display(Object object) {  //changed DiSpLaY to display
		output(object);  //changed oUtPuT to output
	}
	
	public void setState(UIState state) {  //changed 'uI_sTaTe' to 'UIState' and sEt_sTaTe to setState
		this.state = state;  //changed 'StATe' to state
	}

	
}
