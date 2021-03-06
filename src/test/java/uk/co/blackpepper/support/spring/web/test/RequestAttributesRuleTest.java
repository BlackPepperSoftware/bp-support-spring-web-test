/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
