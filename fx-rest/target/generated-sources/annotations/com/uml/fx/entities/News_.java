package com.uml.fx.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(News.class)
public abstract class News_ {

	public static volatile SingularAttribute<News, Date> created;
	public static volatile SingularAttribute<News, Long> id;
	public static volatile SingularAttribute<News, String> text;
	public static volatile SingularAttribute<News, String> title;
	public static volatile SingularAttribute<News, String> user;

}

