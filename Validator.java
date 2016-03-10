/********************************************************
*    		SWT-Validator
*
*Author:Abhishek Koserwal
*Licensed under MIT (https://github.com/Abhikos/SWT-Validator/blob/master/LICENSE)
*
*
********************************************************/
package com.teamcenter.hendrickson.schmgr.operations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;


public class Validator {
	
	private static boolean noErrorMessage=false;
	private String warningMessage;
    private String patternValue;
    private Text text;
    private Combo combo;
    private ControlDecoration controlObj;
    private int position;
    private String description;
    private Button button;

    
   
    
    public Validator(){
    	
    	
    	
	}
    
	public Validator(Text text,String patternValue,int position,String desc){
	
		this.patternValue=patternValue;
		this.text=text;
		this.controlObj = new ControlDecoration(text, position);
		this.position=position;
		this.setDescription(desc);
		this.setControlDecorationForTextBox(controlObj, text, "required", desc, position);
		
		
	}
	
	public Validator(Combo combo,String patternValue,int position,String desc){
		
		this.patternValue=patternValue;
		this.combo = combo;
		this.controlObj = new ControlDecoration(combo, position);
		this.position=position;
		this.setDescription(desc);
		this.setControlDecorationForCombo(controlObj, combo, "required", desc, position);
		if(this.combo.getText() != null && !this.combo.getText().isEmpty())
			this.setControlDecorationForCombo(controlObj, combo, "required", desc, position);
			else
			this.setControlDecorationForCombo(controlObj, combo, "required", desc, position);
		
	}
	
	
	
	public boolean isValidPattern(String success_des, String error_desc){
		
		if(this.text.getText() != null && !this.text.getText().isEmpty()){
		Pattern pattern = Pattern.compile(this.patternValue);
		Matcher matcher = pattern.matcher(this.text.getText());
		if(matcher.matches()){
			
			this.setControlDecorationForTextBox(controlObj, text, "correct", success_des, position);
		
		
		
		}else{
			this.setControlDecorationForTextBox(controlObj, text, "error", error_desc, position);
			
		
	
		}
		
		
		return matcher.matches();
		
		}else{
			this.setControlDecorationForTextBox(controlObj, text, "error", error_desc, position);
			
			return false;
		}
	}

	
	
	public boolean isValidPatternCombo(String success_des, String error_desc){
		if(this.combo.getText() != null && !this.combo.getText().isEmpty()){
		Pattern pattern = Pattern.compile(this.patternValue);
		Matcher matcher = pattern.matcher(this.combo.getText());
		if(matcher.matches()){
			
		    this.setControlDecorationForCombo(controlObj, combo, "correct", success_des, position);
		
		   
		
		}else{
			
			this.setControlDecorationForCombo(controlObj, combo, "error", error_desc, position);
			
			
		
	
		}
		
		return matcher.matches();
		}else{
			
			this.setControlDecorationForCombo(controlObj, combo, "required", getDescription(), position);
		
			return false;
			
		}
		
	}
	
	
	void setControlDecorationForTextBox(ControlDecoration controlObj,Text text,String msg,String desc,int position){
		
        controlObj.setImage(getFieldDecorationImage(msg));
    	controlObj.setDescriptionText(desc);
    	controlObj.show();
    	
	}
	
	void setControlDecorationForCombo(ControlDecoration controlObj,Combo combo,String msg,String desc,int position){
		
	    
    	controlObj.setImage(getFieldDecorationImage(msg));
		controlObj.setDescriptionText(desc);
		controlObj.show();
    }
	
	
	
	
	Image getFieldDecorationImage(String type){
		
		String Icontype=FieldDecorationRegistry.DEC_INFORMATION;
		
	
		if(type.equals("error")){
		
			Icontype=FieldDecorationRegistry.DEC_ERROR;
			 
	    }
		else if(type.equals("warn")){
	    	
	    	Icontype=FieldDecorationRegistry.DEC_WARNING;
	    
	    }else if(type.equals("required")){
	    	
	    	Icontype=FieldDecorationRegistry.DEC_REQUIRED;
	    	
	    }
	    else if(type.equals("content")){
	    	
	    	Icontype = FieldDecorationRegistry.DEC_CONTENT_PROPOSAL;

	    }
	    else if(type.equals("quickfix")){
	    	
	    	Icontype = FieldDecorationRegistry.DEC_ERROR_QUICKFIX;
	    	
	    }else{
	    	
	        Icontype=FieldDecorationRegistry.DEC_INFORMATION;
			
	    }

		
		Image image = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(Icontype)
				.getImage();
		
		if(type.equals("correct"))
			return image = ResourceManager.getPluginImage("example", "icons/tick.png");
		else
		return image;
	}

	public void getErrorMessageBox(String errormsg){
		MessageBox m = new MessageBox(Display.getDefault().getActiveShell(),SWT.ICON_WARNING);
		m.setMessage(errormsg);
		m.open();
	}
	
	public boolean isValidMap(HashMap<Object,Boolean> validCheck){
		
		if(validCheck.containsValue(false))
			return false;
		else
			return true;
	}
	
	
	public String getWarningMessage() {
		return warningMessage;
	}
	
	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
	

	public String getPatternValue() {
		return patternValue;
	}


	public void setPatternValue(String patternValue) {
		this.patternValue = patternValue;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public ControlDecoration getControlObj() {
		return controlObj;
	}

	public void setControlObj(ControlDecoration controlObj) {
		this.controlObj = controlObj;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public static boolean isNoErrorMessage() {
		return noErrorMessage;
	}

	public static void setNoErrorMessage(boolean noErrorMessage) {
		
		Validator.noErrorMessage = noErrorMessage;
		
	}






}
