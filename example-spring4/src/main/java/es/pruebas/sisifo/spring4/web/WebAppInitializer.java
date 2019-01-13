//package es.pruebas.sisifo.spring4.web;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//public class WebAppInitializer implements WebApplicationInitializer {
//
//	@Override
//	public void onStartup(final ServletContext servletContext) throws ServletException {
////		// Crear root context de Spring
////		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
////		rootContext.register(ApplicationConfig.class);
////		servletContext.addListener(new ContextLoaderListener(rootContext));
//
//		// Crear web context de Spring
//		final AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
//		webContext.register(WebApplicationConfig.class);
//		webContext.setServletContext(servletContext);
//		servletContext.addListener(new ContextLoaderListener(webContext));
//
//		// Servlets
//		final ServletRegistration.Dynamic springServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
//		springServlet.setLoadOnStartup(1);
//		springServlet.addMapping("/");
//
//
//
//	}
//
//}
