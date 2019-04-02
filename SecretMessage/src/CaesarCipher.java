
public class CaesarCipher implements Encryption {

	private String message; 
	private int shift = 1; 
	private Utilities util; 
	
	public CaesarCipher()
	{
		getInput(); 
	}
	
	public void getInput()
	{
		int doEncript;
		String choice;  
		boolean again = true;
		int shift; 
		//encript/decript 
		do 
		{
			choice = Utilities.getMessage("1. Encript\n2. Decript\n3. Quit\nWhat would you like to do? ");
			 doEncript = Utilities.getChoice(choice); 	
		}while(doEncript > 3 || doEncript < 1);
		
		switch (doEncript)
		{
		case 1:
			shift = -1;
			while (shift < 0 || shift > 25)
			{
				choice = Utilities.getMessage("Choose number between 0 and 25 to encript by? ");
				shift = Utilities.getChoice(choice);
			}
			again = true; 
			while (again)
			{
				message = Utilities.getMessage("Please enter the message you want to encript.");
				if (message.equals(""))
				{
					again = Utilities.yesNoQuestion("You have not entered a message. Would you like to re-enter a message? ");
				}else
				{
					again = false;
				}
			}
			
			if (!message.equals(""))
			{
				this.shift = shift; 
				encode(message);
				System.out.println(this.message);
			}
			
			break; 
		case 2:
			again = true; 
			while (again)
			{
				message = Utilities.getMessage("Please enter the message you want to decript.");
				if (message.equals(""))
				{
					again = Utilities.yesNoQuestion("You have not entered a message. Would you like to re-enter a message? "); 
				} else 
				{
					again = false; 
				}
			}
			if (message.equals("")) 
			{
				break; 
			}
			choice = Utilities.getMessage("Please enter the number that the encoded message is shifted by. "); 
			shift = Utilities.getChoice(choice); 
			
			this.shift = shift; 
			decode(message); 
			
			System.out.println(this.message);
			break; 
		default:
			
		}
	}
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setKey(String key) {
		this.shift = Integer.parseInt(key); 
	}
	
	public void encode(String message)
	{
		String newMessage = "";
		int asciiNumber;
		for(int i = 0; i < message.length(); i++)
		{		
			asciiNumber = (int)message.charAt(i);
			asciiNumber = Utilities.getAscciShift(asciiNumber, this.shift);
			newMessage+=(char)asciiNumber;
		}
		this.message = newMessage; 
		
	}
	
	public void decode(String message)
	{
		String newMessage = ""; 
		int asciiNumber; 
		for(int i = 0; i < message.length(); i++)
		{
			asciiNumber = (int)message.charAt(i);
			asciiNumber = Utilities.getAscciShift(asciiNumber, -this.shift); 
			newMessage+=(char)(asciiNumber);
		}
		this.message = newMessage; 
	}
	

}
