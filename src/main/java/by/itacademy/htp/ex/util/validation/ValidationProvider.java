package by.itacademy.htp.ex.util.validation;

public class ValidationProvider {
	
	
	private static final ValidationProvider instance = new ValidationProvider();
	
	private static final NewsDataValidationImpl newsValidationInstance = new NewsDataValidationImpl();
	
	private static final UserDataValidationImpl userDataValidationInstance = new UserDataValidationImpl();
	
	private ValidationProvider() {
		
		}
	
	public UserDataValidationImpl getUserDataValidationInstance() {
		
		return userDataValidationInstance;
	}
	
	public NewsDataValidationImpl getNewsValidationInstance() {
		
		return newsValidationInstance;
		
	} 
	
	
	
	public static ValidationProvider getInstance() {
		
		return instance;
	}

}
