/********************************************************
*    		SWT-Validator
*
*Author:Abhishek Koserwal
*Licensed under MIT (https://github.com/Abhikos/SWT-Validator/blob/master/LICENSE)
*
*
********************************************************/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;


public class Validator {
	
	private String errorMessage;
	private String warningMessage;
	private int DECORATOR_POSITION;
	private int DECORATOR_MARGIN_WIDTH;
	private int DEFAULT_WIDTH_HINT;
    private String patternValue;
    private Text text;
    private Combo combo;
    private ControlDecoration controlObj;
    private int position;
    private String description;
    private Button button;
    private static int count=1;
	
    
    Validator(){
		
		this.errorMessage="error";
	}
    
	Validator(Text text,String patternValue,int position,String desc){
		this.patternValue=patternValue;
		this.text=text;
		this.controlObj = new ControlDecoration(text, position);
		this.position=position;
		this.setDescription(desc);
		this.setControlDecorationForTextBox(controlObj, text, "required", desc, position);
		
		
	}
	
	Validator(Combo combo,String patternValue,int position,String desc){
		this.patternValue=patternValue;
		this.combo = combo;
		this.controlObj = new ControlDecoration(combo, position);
		this.position=position;
		this.setDescription(desc);
		this.setControlDecorationForCombo(controlObj, combo, "required", desc, position);
	}
	
	
	
	boolean isValidPattern(){
		
		Pattern pattern = Pattern.compile(this.patternValue);
		Matcher matcher = pattern.matcher(this.text.getText());
		if(matcher.matches()){
			
			this.setControlDecorationForTextBox(controlObj, text, "correct", "Correct", position);
			setErrorMessage("no");
			this.count+=1;
		}else{
			this.setControlDecorationForTextBox(controlObj, text, "error", "Invalid", position);
			setErrorMessage("error");
			
		}
		
		
		return matcher.matches();
		
	
	}
	
	void isbuttonEnable(Button btn,int elementCount){
		System.out.println("count"+count);
		if(count>=elementCount){
			btn.setEnabled(true);
		}else{
			btn.setEnabled(false);
		}
		
	}
	
	
	boolean isValidPatternCombo(){
		
		Pattern pattern = Pattern.compile(this.patternValue);
		Matcher matcher = pattern.matcher(this.combo.getText());
		if(matcher.matches()){
			
		    this.setControlDecorationForCombo(controlObj, combo, "correct", "correct", DECORATOR_POSITION);
		    setErrorMessage("no");
		    this.count+=1;
		}else{
			this.setControlDecorationForCombo(controlObj, combo, "error", "invalid", DECORATOR_POSITION);
			setErrorMessage("error");
	
		}
		
		return false;
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
		
		return image;
	}

	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getWarningMessage() {
		return warningMessage;
	}
	
	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
	
	public int getDECORATOR_POSITION() {
		return DECORATOR_POSITION;
	}

	public void setDECORATOR_POSITION(int dECORATOR_POSITION) {
		DECORATOR_POSITION = dECORATOR_POSITION;
	}

	public int getDECORATOR_MARGIN_WIDTH() {
		return DECORATOR_MARGIN_WIDTH;
	}

	public void setDECORATOR_MARGIN_WIDTH(int dECORATOR_MARGIN_WIDTH) {
		DECORATOR_MARGIN_WIDTH = dECORATOR_MARGIN_WIDTH;
	}

	public int getDEFAULT_WIDTH_HINT() {
		return DEFAULT_WIDTH_HINT;
	}

	public void setDEFAULT_WIDTH_HINT(int dEFAULT_WIDTH_HINT) {
		DEFAULT_WIDTH_HINT = dEFAULT_WIDTH_HINT;
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

	public int getCount() {
		return count;
	}






}
