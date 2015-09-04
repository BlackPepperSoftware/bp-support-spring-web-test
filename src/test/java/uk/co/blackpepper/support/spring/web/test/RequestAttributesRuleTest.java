package uk.co.blackpepper.support.spring.web.test;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class RequestAttributesRuleTest {

	private RequestAttributesRule rule;
	
	@Before
	public void setUp() {
		rule = new RequestAttributesRule();
	}
	
	@Test
	public void beforeSetsServletRequestAttributes() {
		RequestContextHolder.resetRequestAttributes();

		rule.before();
		
		assertThat(RequestContextHolder.getRequestAttributes(), is(instanceOf(ServletRequestAttributes.class)));
	}
	
	@Test
	public void beforeSetsHttpServletRequest() {
		RequestContextHolder.resetRequestAttributes();

		rule.before();
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assertThat(attributes.getRequest(), is(instanceOf(HttpServletRequest.class)));
	}
	
	@Test
	public void afterResetsRequestAttributes() {
		RequestContextHolder.setRequestAttributes(mock(RequestAttributes.class));
		
		rule.after();
		
		assertThat(RequestContextHolder.getRequestAttributes(), is(nullValue()));
	}
}
