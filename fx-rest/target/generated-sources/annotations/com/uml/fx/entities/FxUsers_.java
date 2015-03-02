package com.uml.fx.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FxUsers.class)
public abstract class FxUsers_ {

	public static volatile SingularAttribute<FxUsers, String> password;
	public static volatile SingularAttribute<FxUsers, Date> created;
	public static volatile SingularAttribute<FxUsers, String> name;
	public static volatile SingularAttribute<FxUsers, Integer> active;
	public static volatile SingularAttribute<FxUsers, Long> id;
	public static volatile SingularAttribute<FxUsers, Integer> can_edit_pages;
	public static volatile SingularAttribute<FxUsers, String> email;
	public static volatile SingularAttribute<FxUsers, String> username;

}

