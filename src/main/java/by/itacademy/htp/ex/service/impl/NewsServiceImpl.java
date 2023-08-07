package by.itacademy.htp.ex.service.impl;

import java.util.List;

import by.itacademy.htp.ex.bean.News;
import by.itacademy.htp.ex.dao.DaoProvider;
import by.itacademy.htp.ex.dao.INewsDAO;
import by.itacademy.htp.ex.dao.NewsDAOException;
import by.itacademy.htp.ex.service.INewsService;
import by.itacademy.htp.ex.service.ServiceException;

public class NewsServiceImpl implements INewsService{

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	
	@Override
	public boolean save(News news) throws ServiceException {
		
		try {
			
			return newsDAO.addNews(news);
			
		} catch ( NewsDAOException e) {
			
						
			throw new ServiceException(e);
		}
	
	}

	@Override
	public void find() {
				
	}

	@Override
	public boolean update(News news) throws ServiceException {
		
		
		try {
			
			return newsDAO.updateNews(news);
			
		} catch (NewsDAOException e) {
			
			throw new ServiceException("Error in DB when update news",e);
		}
		
		
		
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
			
		try {
			return newsDAO.getLatestsList(5);
		} catch (NewsDAOException e) {
			
			throw new ServiceException("Error in DB when prepare list of news",e);
		}
		
		
	}

	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException("Error in DB when prepare list", e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException("Error in DB when choose news",e);
		}
	}

	@Override
	public boolean delete(String[] newsIdCheckBox) throws ServiceException {
		
		
		try {
			
			return newsDAO.deleteNewses(newsIdCheckBox);
			
		} catch (NewsDAOException e) {
			throw new ServiceException("Error in DB when delete news",e);
		}
		
	}

}
