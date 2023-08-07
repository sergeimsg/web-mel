package by.itacademy.htp.ex.util.validation;

public class NewsDataValidationImpl implements NewsDataValidation {

	@Override
	public boolean dataValidatioAddNews(String newsTitle, String newsDate, String newsBrief, String newsContent,
			String newsImgPath, String newsStatus, int userId) throws DataValidationException {

		boolean newsEditStatus = false;
		if (newsTitle.isEmpty() || newsDate.isEmpty() || newsBrief.isEmpty() || newsContent.isEmpty()
				|| newsContent.isEmpty() || newsImgPath.isEmpty() || newsStatus.isEmpty() || userId == 0) {
			throw new DataValidationException("There are empty fields in news");

		} else {
			newsEditStatus = true;
		}

		return newsEditStatus;
	}

	@Override
	public boolean dataValidatioEditdNews(String newsTitle, String newsDate, String newsBrief, String newsContent,
			String newsImgPath, String newsID) throws DataValidationException {
		
		boolean newsAddStatus = false;
		
		if (newsTitle.isEmpty() || newsDate.isEmpty() || newsBrief.isEmpty() || newsContent.isEmpty()
				|| newsImgPath.isEmpty() || newsID.isEmpty()) {
		
			throw new DataValidationException("There are empty fields in news. Add data.");

		} else {
			newsAddStatus = true;
		}

		return newsAddStatus;
	}

}
