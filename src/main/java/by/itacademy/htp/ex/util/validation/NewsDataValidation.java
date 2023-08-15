package by.itacademy.htp.ex.util.validation;

public interface NewsDataValidation {
	
	boolean dataValidatioAddNews(String newsTitle,String newsDate, String newsBrief, String newsContent, String newsImgPath,
			String newsStatus, int userId) throws DataValidationException;


	boolean dataValidatioEditdNews(String newsTitle,String newsDate, String newsBrief, String newsContent, String newsImgPath,
			String newsID) throws DataValidationException;
	
	
	
	
}
