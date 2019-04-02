
public class VigenereCipher implements Encryption {
	private String message; 
	private String key = "HELLOWORLD"; 
	
	public VigenereCipher()
	{
		getInput(); 
	}
	
	public void getInput()
	{
		String choice; 
		String messageToEncode = "";
		String messageToDecode = ""; 
		String key = "";
		int doEncript; 
		do 
		{
			choice = Utilities.getMessage("1. Encript\n2. Decript\n3. Quit\nWhat would you like to do? ");
			doEncript = Utilities.getChoice(choice); 	
		}while(doEncript > 3 || doEncript < 1);
		
		switch(doEncript)
		{
		case 1:
			while (messageToEncode.trim().equals(""))
			{
				messageToEncode = Utilities.getMessage("What is the message to encode?");
			}
			while (key.trim().equals(""))
			{
				key = Utilities.getMessage("What is the key?"); 			
			}
			
			this.key = key; 
			encode(messageToEncode); 
	
			System.out.println(this.message);
			break; 
		case 2:
			while (messageToDecode.trim().equals(""))
			{
				messageToDecode = Utilities.getMessage("What is the key?");
			}
			while (key.trim().equals(""))
			{
				key = Utilities.getMessage("What is the encoded message?"); 			
			}
			
			this.key = key; 
			decode(messageToDecode); 
			
			System.out.println(this.message);
			break; 
		case 3:
			break;
		}
	}
	
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setKey(String key) {
		this.key = key; 
	}
	
	@Override
	public void encode(String message)
	{
		String encodedMessage = ""; //message that needs to be encoded 
		int msgChar,keyChar;  
		for(int msgIndex = 0, keyIndex = 0; msgIndex < message.length(); msgIndex++, keyIndex++)
		{
			if(keyIndex >= this.key.length())
			{
				keyIndex = 0; 
			}
			
			msgChar = message.charAt(msgIndex);
			keyChar = this.key.charAt(keyIndex);
			encodedMessage += (char)Utilities.getAscciShift(msgChar,keyChar);
		}
		this.message = encodedMessage; 
	}
	
	@Override
	public void decode(String message)
	{
		int msgChar,keyChar;
		String encodedMessage = ""; 
		for(int msgIndex = 0,keyIndex = 0; msgIndex < message.length(); keyIndex++,msgIndex++)
		{
			if(keyIndex >= this.key.length())
			{
				keyIndex = 0; 
			}
			msgChar = message.charAt(msgIndex);
			keyChar = this.key.charAt(keyIndex);
			encodedMessage += (char)Utilities.getAscciShift(-msgChar,keyChar);
		}
		this.message = encodedMessage; 
	}

}
