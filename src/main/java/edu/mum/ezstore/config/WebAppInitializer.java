package edu.mum.ezstore.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		 rootContext.register(AppConfig.class,PersistenceConfig.class,SecurityConfig.class);
//		rootContext.register(AppConfig.class);

		container.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(MvcConfig.class);

		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
				new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/api/*");
		dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		// register spring chain security
		FilterRegistration.Dynamic securityChain = container.addFilter("springSecurityFilterChain",
				new DelegatingFilterProxy());
		securityChain.addMappingForUrlPatterns(null, false, "/*");
	}
}
