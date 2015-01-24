package es.pruebas.jee6.filter.wrapper;

import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Example basic implementation.
 * 
 * @author SISIFO
 *
 */
public class ExclusionFilterWrapperCondition implements FilterWrapperCondition {
	private static final String PARAM_EXCLUSION_PREFIX = "wrapper.exclusionFilterWrapperCondition.param";
	private String[] exclusionsRules;
	
	
	@Override
	public void init(FilterConfig filterConfig) {		
		exclusionsRules = filterConfig.getInitParameter(PARAM_EXCLUSION_PREFIX).split(",");
		for (int i=0; i<exclusionsRules.length; i++) {
			exclusionsRules[i] = filterConfig.getServletContext().getContextPath() + exclusionsRules[i].trim();						
		}
		
	}
	

	@Override
	public boolean allowInnerFilterCall(ServletRequest req, ServletResponse res) {
		HttpServletRequest httpReq = (HttpServletRequest) req;
		for (String pattern : exclusionsRules) {
			if (httpReq.getRequestURI().startsWith(pattern)) {
				return false;
			}
		}
		return true;
	}
}
