/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.springframework.security.portlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.filter.PortletFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.springframework.security.portlet.util.RequestMatcher;

/**
 * Standard implementation of {@code PortletSecurityFilterChain}.
 * 
 * @author Eric Dalquist
 */
public class DefaultPortletSecurityFilterChain implements PortletSecurityFilterChain {
    private static final Log logger = LogFactory.getLog(DefaultPortletSecurityFilterChain.class);
    private final RequestMatcher requestMatcher;
    private final List<PortletFilter> filters;

    public DefaultPortletSecurityFilterChain(RequestMatcher requestMatcher, PortletFilter... filters) {
        this(requestMatcher, Arrays.asList(filters));
    }

    public DefaultPortletSecurityFilterChain(RequestMatcher requestMatcher, List<PortletFilter> filters) {
        logger.info("Creating filter chain: " + requestMatcher + ", " + filters);
        this.requestMatcher = requestMatcher;
        this.filters = new ArrayList<PortletFilter>(filters);
    }

    public RequestMatcher getRequestMatcher() {
        return requestMatcher;
    }

    public List<PortletFilter> getFilters() {
        return filters;
    }

    public boolean matches(PortletRequest request) {
        return requestMatcher.matches(request);
    }

    @Override
    public String toString() {
        return "[ " + requestMatcher + ", " + filters + "]";
    }
}