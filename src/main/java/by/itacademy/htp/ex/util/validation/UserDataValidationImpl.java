package by.itacademy.htp.ex.util.validation;

public class UserDataValidationImpl implements UserDataValidation {

	@Override
	public boolean checkAuthData(String login, String password) throws DataValidationException {

		boolean statusCheckLogin = false;
		try {

			if (login.isEmpty() || password.isEmpty()) {

				throw new DataValidationException("Field login or password is empty");
			}

			else {
				statusCheckLogin = true;
			}

		} catch (Exception e) {

			throw new DataValidationException("Field login or password is empty");
		}

		return statusCheckLogin;
	}

	@Override
	public boolean checkRegistrationForm(String login, String password, String name, String surname, String email)
			throws DataValidationException {

		boolean checkStatus = false;

		try {

			if (login.isEmpty() || password.isBlank() || name.isEmpty() || surname.isEmpty() || email.isEmpty()) {

				throw new DataValidationException("Field is empty");	
				
				
			} else {
				checkStatus = true;

			}

		} catch (Exception e) {

			throw new DataValidationException("Field is empty");

		}

		return checkStatus;
	}
}
