package org.zkoss.simple;


import java.util.logging.Logger;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@SuppressWarnings("serial")
public class RegistrationIntegrateComposer extends SelectorComposer<Window> {

	@Wire("#submitButton")
	private Button submitButton;
	
	@Wire("#nameBox")
	private Textbox nameBox;
	@Wire("#maleRadio")
	private Radio maleRadio;
	@Wire("#birthdayBox")
	private Datebox birthdayBox;
	
	
	@Wire("#acceptTermBox")
	private Checkbox acceptTermCheckbox;

	private static Logger logger = Logger.getLogger(RegistrationIntegrateComposer.class.getName());
	
	@Listen("onCheck = #acceptTermBox")
	public void changeSubmitStatus(){
		if (acceptTermCheckbox.isChecked()){
			submitButton.setDisabled(false);
			submitButton.setImage("/images/submit.png");
		}else{
			submitButton.setDisabled(true);
			submitButton.setImage("");
		}
	}
	
	@Listen("onClick = #resetButton")
	public void reset(){
		//set raw value to avoid triggering constraint error message
		nameBox.setRawValue("");
		maleRadio.setChecked(true);
		birthdayBox.setRawValue(null);
		acceptTermCheckbox.setChecked(false);
		submitButton.setDisabled(true);
	}
	
	@Listen("onClick = #submitButton")
	public void submit(){
		if (!validateInput()){
			logger.fine("input validation failed");
			return;
		}
		
		Messagebox.show("Congratulation! "+nameBox.getValue()+". Your registration is success.");
		reset();
	}
	
	private boolean validateInput(){
		if (nameBox.getValue().length()==0){
			return false;
		}
		
		if (birthdayBox.getValue()==null){
			return false;
		}
	
		return true;
	}
}
