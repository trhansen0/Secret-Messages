import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Utilities 
{
	public static String getMessage(String question)
	{
		String message = ""; 
		try
		{
			System.out.println(question);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
			message = reader.readLine(); 
		}
		catch (Exception e)
		{
			message = ""; 
		}
		
		return message; 
	}
	
	public static boolean yesNoQuestion(String question)
	{
		boolean tryAgain = true;
		boolean isYes = false; 
		String answer; 
		
		while(tryAgain)
		{
			answer = getMessage(question).toUpperCase(); 
			
			if (answer.charAt(0) == 'N')
			{
				tryAgain = false;
				isYes = false; 
			} else
			{
				tryAgain = false;
				isYes = true;
			} 
		}
		return isYes;
	}
	
	public static BufferedImage getImageFromPath(String path)
	{
		BufferedImage img; 
		try
		{
			img = ImageIO.read(new File(path));
		}catch(Exception e)
		{
			img = null; 
			System.out.println(Strings.error_ReadImage);
		}
		return img; 
	}

	public static BufferedImage getImage(String question)
	{
		String imgPath; 
		BufferedImage img= null; 
		boolean quit = false; 
		while (img == null && quit == false)
		{
			try
			{
				System.out.println(question);
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
				imgPath = reader.readLine(); 
				if (imgPath.equalsIgnoreCase("quit"))
				{
					quit = true;
				}
				else
				{
					img = Utilities.getImageFromPath(imgPath);
				}
				 
			}
			catch (Exception e)
			{
				imgPath = ""; 
				img = null; 
			} 
		}
		return img; 
	}

	public static boolean saveImageToPath(BufferedImage img, String path)
	{
		try {
		    File outputfile = new File(path);
		    ImageIO.write(img, "png", outputfile);
		    return true; 
		} catch (IOException e) {
			System.out.println(Strings.error_FileNotMade);
			return false; 
		}
	}
	
	public static int getChoice(String userChoice)
	{
		int choice = 0; 
		try
		{
			choice = Integer.parseInt(userChoice); 
		}
		catch(Exception e)
		{
			choice = 0; 
		}
		System.out.println(choice);
		return choice; 
		
	}
	
	public static int getAscciShift(int charToShift, int amtToShift)
	{
		if (charToShift + amtToShift > 126 )
		{
			charToShift = (charToShift + amtToShift)-126+32;
		} else if (charToShift + amtToShift < 32)
		{
			charToShift = (charToShift + amtToShift)+126-32;
		}else 
		{
			charToShift += amtToShift; 
		}
		return charToShift; 
	}
}
