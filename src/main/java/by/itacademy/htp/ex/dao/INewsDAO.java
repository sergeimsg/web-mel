package by.itacademy.htp.ex.dao;

import java.util.List;

import by.itacademy.htp.ex.bean.News;



public interface INewsDAO {
	List<News> getList() throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException;
	boolean addNews(News news) throws NewsDAOException;
	boolean updateNews(News news) throws NewsDAOException;
	boolean deleteNewses(String[] idNewses)throws NewsDAOException;
}
