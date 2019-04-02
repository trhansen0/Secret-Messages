
public interface Encryption {
	
	public String getMessage(); 
	public void setKey(String key); 
	public void encode(String message); 
	public void decode(String message); 
}
