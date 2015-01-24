package es.pruebas.jee6.filter.wrapper;

import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface FilterWrapperCondition {
	/**
	 * int the class
	 * @param filterConfig
	 */
	void init(FilterConfig filterConfig);
	
	/**
	 * Decides if the filter is suitable for this request and response.
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	boolean allowInnerFilterCall(final ServletRequest req, final ServletResponse res);
}
