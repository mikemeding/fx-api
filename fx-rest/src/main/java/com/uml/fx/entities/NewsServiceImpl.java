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
    public boolean deleteNews(News news) {
        //TODO: Error checking???
        em.remove(news);
        return false;
    }

    @Override
    public boolean deleteNews(long id) {
        //TODO: add id lookup
        return false;
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
        for(News newsItem : newsList){
            JSONObject jo = new JSONObject();
            jo.append("title",newsItem.getTitle());
            jo.append("text",newsItem.getText());
            jo.append("created",newsItem.getCreated().toString());
            jo.append("user",newsItem.getUser());

            jsonArray.add(jo);
        }
        return jsonArray;
    }
}
