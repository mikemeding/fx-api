package com.uml.fx.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FxUser.class)
public abstract class FxUser_ {

	public static volatile SingularAttribute<FxUser, String> password;
	public static volatile SingularAttribute<FxUser, Date> created;
	public static volatile SingularAttribute<FxUser, String> name;
	public static volatile SingularAttribute<FxUser, Integer> active;
	public static volatile SingularAttribute<FxUser, Long> id;
	public static volatile SingularAttribute<FxUser, Integer> can_edit_pages;
	public static volatile SingularAttribute<FxUser, String> email;
	public static volatile SingularAttribute<FxUser, String> username;

}

