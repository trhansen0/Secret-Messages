import java.awt.Color;
import java.awt.image.BufferedImage;

public class ChangeColor {
	
	public ChangeColor()
	{
		int fromChoice;
		int toChoice; 
		String toImgPath; 
		BufferedImage img = Utilities.getImage(Strings.question_ImgPath);
		//get new imagelocation
		do 
		{
			fromChoice = Utilities.getChoice(Utilities.getMessage(Strings.question_ColorChangeFrom)); 
		}while(validChoice(fromChoice) == false); 
		do
		{
			toChoice = Utilities.getChoice(Utilities.getMessage(Strings.question_ColorChangeTo)); 		
		}while(validChoice(toChoice) == false); 
		
		if (fromChoice != toChoice)
		{
			changeImage(img, fromChoice, toChoice);
			Utilities.saveImageToPath(img, "z.png");
		}
	}
	
	private boolean validChoice(int choice)
	{
		if (choice == 1 || choice == 2 || choice == 3)
		{
			return true; 
		}
		return false; 
	}
	
	private void changeImage(BufferedImage img, int from, int to)
	{
		Color c; 
 
		for (int x = 0; x < img.getWidth(); x++)
		{
			for (int y = 0; y < img.getHeight(); y++)
			{
				c = new Color(img.getRGB(x, y)); 
				img.setRGB(x, y, swapColors(c,from,to).getRGB());
				
			}
		}
	}
	//1-green  2-red  3-blue
	private Color swapColors(Color color, int from, int to)
	{
		Color c; 
		int pxlColors[] = {color.getGreen(),color.getRed(),color.getBlue()};
		int temp = pxlColors[from-1]; 
		pxlColors[from-1] = pxlColors[to-1];
		pxlColors[to-1] = temp; 
		
		c = new Color(pxlColors[1], pxlColors[0], pxlColors[2]) ; 
		
		return c; 
	}
	
	
}
