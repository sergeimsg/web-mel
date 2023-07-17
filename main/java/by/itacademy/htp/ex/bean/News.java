package by.itacademy.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idNews = 0;
	private String title = "";
	private String briefNews = "";
	private String content = "";
	private String newsDate = "";
	private String imagePath = "";
	private int status = 1;
	private int userID = 0;

	public News() {
	}

	public News(int idNews, String title, String briefNews, String content, String newsDate, String imagePath,
			int status, int userID) {
		super();
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
		this.imagePath = imagePath;
		this.status = status;
		this.userID = userID;

	}

	public News(int idNews, String title, String briefNews, String content, String newsDate, String imagePath,
			int status) {
		super();
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
		this.imagePath = imagePath;
		this.status = status;
		

	}
	
	
	public News(String title, String briefNews, String content, String newsDate, String imagePath, int status, int userID) {
		super();

		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
		this.imagePath = imagePath;
		this.status = status;
		this.userID = userID;
		
	}

	public News(int idNews, String title, String briefNews, String content, String newsDate, String imagePath) {
		super();
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
		this.imagePath = imagePath;
	}

	public News(int idNews, String title, String briefNews, String content, String newsDate) {
		super();
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(briefNews, content, idNews, imagePath, newsDate, status, title, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(briefNews, other.briefNews) && Objects.equals(content, other.content)
				&& idNews == other.idNews && Objects.equals(imagePath, other.imagePath)
				&& Objects.equals(newsDate, other.newsDate) && status == other.status
				&& Objects.equals(title, other.title) && userID == other.userID;
	}

	@Override
	public String toString() {
		return "News [idNews=" + idNews + ", title=" + title + ", briefNews=" + briefNews + ", content=" + content
				+ ", newsDate=" + newsDate + ", imagePath=" + imagePath + ", status=" + status + ", userID=" + userID
				+ "]";
	}

	
	

}
