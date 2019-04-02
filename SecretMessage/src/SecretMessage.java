import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class SecretMessage {
	BufferedImage img; 
	String message;
	String password =""; 
	public SecretMessage()
	{
		String encodeOrDecode = Utilities.getMessage(Strings.question_EncodeDecode); 
		try
		{
			if (encodeOrDecode.substring(0,1).equalsIgnoreCase("e"))
			{
				img = Utilities.getImage(Strings.question_ImgPath); 
				if (img == null){return;}
				String path = Utilities.getMessage(Strings.question_EncriptedImgPath); 
				password = Utilities.getMessage(Strings.question_Password); 
				message = Utilities.getMessage(Strings.question_Message);
				message = message.length() + "^" + message; 
				BufferedImage encodedImage = encodeMessage(img, message, password);
				Utilities.saveImageToPath(encodedImage, path);
				System.out.println(Strings.message_ImgEncoded);
			}
			else 
			{
				password = Utilities.getMessage(Strings.question_Password); 
				img = Utilities.getImage(Strings.question_DecodeImgPath);
				if (img == null){return;}
				message = decodeMessage(img, password); 
				System.out.println(Strings.label_Message + message);
			}
		}catch(Exception e){
			System.out.println(Strings.error_General);
		}
	}


	
	public static BufferedImage encodeMessage(BufferedImage image, String message, String password)
	{
		int i = 1;  
		int c; 
		int sindex = 0; 
		boolean finished = false; 
		message = password + "^" + message; 
		
		for (int row = 0; row < image.getHeight(); row++)
		{
			for (int col = 0; col < image.getWidth(); col++)
			{
				try{
					message.charAt(sindex);
				}
				catch(Exception e)
				{
					finished = true; 
					break; 
				}
				c= image.getRGB(col, row); 
				image.setRGB(col, row, setInt(c, message.charAt(sindex),  i) );
				if (i == 4)
				{
					i = 1; 
					sindex++; 
				}
				else 
				{
					i++; 
				}
			}
			if (finished)
			{
				break; 
			}
		}
		 
		return image; 
	}
	
	public static int setInt(int integer, char c, int numOrder)
	{   int finalInt = 0; 
		integer = integer | 3; 
		int emptyLastTwo = integer & 0xfffffffc; 
		
		if (numOrder == 1)
		{
			finalInt = c >> 6; 
		}
		else if (numOrder == 2)
		{
			finalInt = c >> 4; 
			finalInt = finalInt & 3;
		}
		else if (numOrder == 3)
		{
			finalInt = c >> 2; 
			finalInt = finalInt & 3; 
		}
		else if (numOrder == 4)
		{
			finalInt = c >> 0; 
			finalInt = finalInt & 3;
		}
		finalInt = emptyLastTwo | finalInt; 
		
		return finalInt; 
	}
	
	public static char getChar(int int1, int int2, int int3, int int4)
	{
		int last2 = int1 & 3; 
		int finalInt = last2;
		finalInt = finalInt << 2; 
		last2 = int2 & 3; 
		finalInt = finalInt | last2; 
		finalInt = finalInt << 2; 
		last2 = int3 & 3; 
		finalInt = finalInt | last2; 
		finalInt = finalInt << 2; 
		last2 = int4 & 3; 
		finalInt = finalInt | last2; 
		//System.out.println(Integer.toBinaryString(finalInt));
		
		return (char) finalInt;
	}
	
	public static String decodeMessage(BufferedImage image, String password)
	{
		int i = 0;  
		int[] intlist = new int[4]; 
		int c; 
		char foundChar = ' '; 
		int size = -1; 
		String message = "" ; 
		boolean finished = false; 
		boolean correctPassword = false; 
		
		for (int row = 0; row < image.getHeight(); row++)
		{
			for (int col = 0; col < image.getWidth(); col++)
			{
				if (size != -1 && message.length() >= size )
				{
					finished = true ; 
					break;
				}
				c = image.getRGB(col, row); 
				intlist[i] = c;				
				if (i == 3)
				{
					i = 0;  
					foundChar = getChar(intlist[0],intlist[1],intlist[2],intlist[3]);
					message = message + foundChar; 
					
					if (size == -1 && foundChar == '^' && correctPassword == false)
					{
						if (message.substring(0, message.length()-1).equals(password))
						{
							message = ""; 
							correctPassword = true;
						}
						else 
						{
							message = "The password was incorrect."; 
							finished = true; 
							break; 
						}
					}
					else if (size == -1 && foundChar == '^' && correctPassword == true)
					{
						size = Integer.parseInt(message.substring(0, message.length()-1)); 
						message = ""; 
					}
					
					intlist = new int[4]; 
				}
				else 
				{
					i++; 
				}
			}
			if (correctPassword == false )
			{
				break; 
			}
			if (finished)
			{
				break; 
			}
		}
		 
		return message; 
	}
	
}
