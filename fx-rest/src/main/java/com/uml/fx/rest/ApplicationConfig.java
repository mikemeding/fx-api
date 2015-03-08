/**
 *
 * @author mike
 */
package com.uml.fx.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	/**
	 * Do not modify addRestResourceClasses() method. It is automatically
	 * populated with all resources defined in the project. If required, comment
	 * out calling this method in getClasses().
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.uml.fx.rest.Appraisal.class);
		resources.add(com.uml.fx.rest.CORSFilter.class);
        resources.add(com.uml.fx.rest.ContactRest.class);
        resources.add(com.uml.fx.rest.FundsExpert.class);
		resources.add(com.uml.fx.rest.NewsRest.class);
		resources.add(com.uml.fx.rest.TaxRates.class);
	}

}
