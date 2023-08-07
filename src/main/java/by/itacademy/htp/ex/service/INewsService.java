package by.itacademy.htp.ex.service;

import java.util.List;

import by.itacademy.htp.ex.bean.News;

public interface INewsService {
  boolean save(News news) throws ServiceException;
  void find();
  boolean update(News news) throws ServiceException;
  boolean delete(String[] newsIdCheckbox) throws ServiceException;
  List<News> latestList(int count)  throws ServiceException;
  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException;
}
