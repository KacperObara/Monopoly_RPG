package not.used;

import com.badlogic.gdx.Input.TextInputListener;

// not used now
public class CustomTextInputListener implements TextInputListener{

	private String newText = null;
	
	@Override
	public void input(String text)
	{
		System.out.println(text);
		newText = text;
	}

	@Override
	public void canceled()
	{
		newText = null;
	}
	
	public String getText(){
	      return newText;
	   }
	
	public String returnText(){
	      return newText;
	   }
	
}

