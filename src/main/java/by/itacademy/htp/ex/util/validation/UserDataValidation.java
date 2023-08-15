package by.itacademy.htp.ex.util.validation;

public interface UserDataValidation {
	
       boolean checkAuthData(String login, String password) throws DataValidationException;
       
       boolean checkRegistrationForm(String login, String password, String name, String surname, String email) throws DataValidationException;
       
       
       
}
