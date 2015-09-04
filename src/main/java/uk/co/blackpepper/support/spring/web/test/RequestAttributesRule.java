package uk.co.blackpepper.support.spring.web.test;

import org.junit.rules.ExternalResource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * JUnit rule to set a mock {@code RequestAttributes} during test execution.
 * 
 * @see org.springframework.web.context.request.RequestAttributes
 */
public class RequestAttributesRule extends ExternalResource {
	
	@Override
	protected void before() {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
	}
	
	@Override
	protected void after() {
		RequestContextHolder.resetRequestAttributes();
	}
}
