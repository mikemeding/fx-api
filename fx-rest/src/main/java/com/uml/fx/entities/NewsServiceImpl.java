package com.uml.fx.entities;

import com.uml.fx.json.JSONArray;
import com.uml.fx.json.JSONObject;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Created by mike on 3/4/15.
 */
@Stateless
@Local(NewsService.class)
public class NewsServiceImpl implements NewsService {
    @PersistenceContext(unitName = "fxPU")
    private EntityManager em;

    private static final Logger log = getLogger(NewsServiceImpl.class.getName());

    @Override
    public boolean addNews(News news) {
        em.persist(news);
        return false;
    }

    @Override
    public boolean deleteNews(long id) {
        TypedQuery<News> query = em.createNamedQuery(News.SELECT_BY_ID, News.class);
        query.setParameter("id",id);
        News item = query.getSingleResult();
        em.remove(item);
        return true;
    }

    @Override
    public boolean editNews(News news) {
        em.merge(news);
        return false;
    }

    @Override
    public JSONArray selectAll() {
        TypedQuery<News> query = em.createNamedQuery(News.SELECT_ALL, News.class);
        List<News> newsList = query.getResultList();
        JSONArray jsonArray = new JSONArray();
        for (News newsItem : newsList) {
            JSONObject jo = new JSONObject();
            jo.put("id", newsItem.getId()); //expose database generated id
            jo.put("title", newsItem.getTitle());
            jo.put("text", newsItem.getText());
            jo.put("created", newsItem.getCreated().toString());
            jo.put("user", newsItem.getUser());
            jsonArray.add(jo);
        }
        return jsonArray;
    }

    @Override
    public JSONObject selectById(long id) {
        TypedQuery<News> query = em.createNamedQuery(News.SELECT_BY_ID, News.class);
        query.setParameter("id", id);
        News news = query.getSingleResult();

        JSONObject jo = new JSONObject();
        jo.put("id", news.getId()); //expose database generated id
        jo.put("title", news.getTitle());
        jo.put("text", news.getText());
        jo.put("created", news.getCreated().toString());
        jo.put("user", news.getUser());

        return jo;
    }

    @Override
    public JSONObject selectByTitle(String title) {
        TypedQuery<News> query = em.createNamedQuery(News.SELECT_BY_TITLE, News.class);
        query.setParameter("title", title);
        News news = query.getSingleResult();

        JSONObject jo = new JSONObject();
        jo.put("id", news.getId()); //expose database generated id
        jo.put("title", news.getTitle());
        jo.put("text", news.getText());
        jo.put("created", news.getCreated().toString());
        jo.put("user", news.getUser());

        return jo;
    }
}
