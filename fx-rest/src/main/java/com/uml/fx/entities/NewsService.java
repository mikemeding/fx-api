package com.uml.fx.entities;

import com.uml.fx.json.JSONArray;
import com.uml.fx.json.JSONObject;

import javax.ejb.Local;

/**
 * Created by mike on 3/4/15.
 */
@Local
public interface NewsService {
    boolean addNews(News news);

    boolean deleteNews(long id);

    boolean editNews(News news);

    JSONArray selectAll();

    JSONObject selectById(long id);

    JSONObject selectByTitle(String title);
}
