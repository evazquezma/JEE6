package es.pruebas.jee6.filter.wrapper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This filter wrapp a inner filter and decides when invoke it.
 * 
 * If you are using Spring, you can get Spring beans by using "WebApplicationContext springContext =   WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());". 
 * In this way you will not need instanciate class by hand.	
 * 
 * @author SISIFO
 *
 */
public class WrapperFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(WrapperFilter.class);

	private static final String FILTER_PARAM_INNER_FILTER_CLASS = "wrapper.param.filterClass";
	private static final String FILTER_PARAM_WRAPPER_CONDITION_CLASS = "wrapper.param.wrapperConditionClass";

	
	private Filter innerFilter;
	private FilterWrapperCondition filterWrapperCondition;

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initializing filter inhibitor");
		
		this.innerFilter = createAndInitInnerFilter(filterConfig);			
		this.filterWrapperCondition = createAndInitFilterWrapperCondition(filterConfig);
		
		LOGGER.info("Inhibitor engaged !");
	}
	

	private Filter createAndInitInnerFilter(final FilterConfig filterConfig) throws ServletException {
		Filter filter = null;
		try {
			LOGGER.info("Loading inner filter: "+ filterConfig.getInitParameter(FILTER_PARAM_INNER_FILTER_CLASS));
			filter = (Filter) Class.forName(filterConfig.getInitParameter(FILTER_PARAM_INNER_FILTER_CLASS)).newInstance();
		} catch (InstantiationException | RuntimeException | ClassNotFoundException | IllegalAccessException e) {
			LOGGER.error("exception while creating inner filter", e);
			throw new RuntimeException(e);
		} 
		
		filter.init(filterConfig);
		
		return filter;
	}
	

	private FilterWrapperCondition createAndInitFilterWrapperCondition(FilterConfig filterConfig) {
		FilterWrapperCondition wrapperCondition = null;
		try {
			LOGGER.info("Loading filter condition: "+ filterConfig.getInitParameter(FILTER_PARAM_WRAPPER_CONDITION_CLASS));
			wrapperCondition = (FilterWrapperCondition) Class.forName(filterConfig.getInitParameter(FILTER_PARAM_WRAPPER_CONDITION_CLASS)).newInstance();
		} catch (InstantiationException | RuntimeException | ClassNotFoundException | IllegalAccessException e) {
			LOGGER.error("exception while creating filter condition", e);
			throw new RuntimeException(e);
		} 
		
		wrapperCondition.init(filterConfig);
		
		return wrapperCondition;
	}
	
	
	@Override
	public void destroy() {
		LOGGER.info("Destroying wrapper filter");	
		LOGGER.info("Destroying inner filter");
		innerFilter.destroy();
		LOGGER.info("Destroyed !");
	}

	
	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
		if (filterWrapperCondition.allowInnerFilterCall(req, res)) {
			innerFilter.doFilter(req, res, chain);
		} else {
			chain.doFilter(req, res);
		}
	}
}
