package com.uml.fx.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contact.class)
public abstract class Contact_ {

	public static volatile SingularAttribute<Contact, Date> date;
	public static volatile SingularAttribute<Contact, String> name;
	public static volatile SingularAttribute<Contact, Long> id;
	public static volatile SingularAttribute<Contact, String> message;
	public static volatile SingularAttribute<Contact, String> email;
	public static volatile SingularAttribute<Contact, Integer> refundAmount;

}

