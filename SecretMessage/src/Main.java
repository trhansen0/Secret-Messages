import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecretMessage secretMessage; 
		ChangeColor colorChange; 
		Encryption cipher; 
		boolean cont = true; //when false quit program 
		String choice = ""; 
		while (cont)
		{
			System.out.println("1. " + Strings.message_SecretMessage);
			System.out.println("2. " + Strings.message_ColorChange);
			System.out.println("3. " + Strings.message_CaesarCipher);
			System.out.println("4. " + Strings.message_VigenereCipher); 
			System.out.println("5. " + Strings.message_Quit);
			choice = Utilities.getMessage(Strings.question_GeneralQuestion);
			switch (Utilities.getChoice(choice))
			{
			case 1:
				secretMessage = new SecretMessage(); 
				break; 
			case 2:
				colorChange = new ChangeColor();
				break;
			case 3:
				cipher = new CaesarCipher();
				break;
			case 4: 
				cipher = new VigenereCipher(); 
				break;
			case 5: 
				cont = false; 
				break;
			default:
				System.out.println(Strings.error_TryAgain);
			}
		}
	
	}
	
	
	

}
