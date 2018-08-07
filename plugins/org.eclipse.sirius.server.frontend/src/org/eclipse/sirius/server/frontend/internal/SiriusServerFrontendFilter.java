/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.frontend.internal;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet filter used to redirect the request to provide a single page
 * application.
 *
 * @author sbegaudeau
 */
public class SiriusServerFrontendFilter implements Filter {
    /**
     * The path of the HTTP API.
     */
    private static final String HTTP_API_PATH = "/api"; //$NON-NLS-1$

    /**
     * The path of the WebSocket API.
     */
    private static final String WS_API_PATH = "/ws"; //$NON-NLS-1$

    /**
     * The path of the images API.
     */
    private static final String IMAGES_PATH = "/images"; //$NON-NLS-1$

    /**
     * The path of the static resources.
     */
    private static final String STATIC_PATH = "/static"; //$NON-NLS-1$

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String requestURI = httpServletRequest.getRequestURI();
            if (this.isStaticResource(requestURI)) {
                this.handleStaticResource(httpServletRequest, httpServletResponse, chain);
            } else if (this.isFrontEnd(requestURI)) {
                this.redirectToFrontEnd(httpServletRequest, httpServletResponse);
            }
        }
    }

    /**
     * Indicates if the given request URI matches a static resource.
     *
     * @param requestURI
     *            The URI of the request
     * @return <code>true</code> if the given request matches a static
     *         resources, <code>false</code> otherwise
     */
    private boolean isStaticResource(String requestURI) {
        return requestURI.startsWith(STATIC_PATH);
    }

    /**
     * Indicates if the given request URI should be redirected to the front end
     * for a proper single page application.
     *
     * @param requestURI
     *            The URI of the request
     * @return <code>true</code> if the given request should be redirected to
     *         the front end
     */
    private boolean isFrontEnd(String requestURI) {
        boolean isFrontEnd = true;
        isFrontEnd = isFrontEnd && !requestURI.startsWith(HTTP_API_PATH);
        isFrontEnd = isFrontEnd && !requestURI.startsWith(WS_API_PATH);
        isFrontEnd = isFrontEnd && !requestURI.startsWith(IMAGES_PATH);
        isFrontEnd = isFrontEnd && !requestURI.startsWith(STATIC_PATH);
        return isFrontEnd;
    }

    /**
     * Delegates the request to the default servlet to handle static resources.
     *
     * @param httpServletRequest
     *            The request
     * @param httpServletResponse
     *            The response
     * @throws ServletException
     *             In case of error
     * @throws IOException
     *             In case of error
     */
    private void handleStaticResource(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * Redirects the request to the front end in order to have a proper single
     * page application.
     *
     * @param httpServletRequest
     *            The request
     * @param httpServletResponse
     *            The response
     * @throws ServletException
     *             In case of error
     * @throws IOException
     *             In case of error
     */
    private void redirectToFrontEnd(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, httpServletResponse); //$NON-NLS-1$
    }

    @Override
    public void destroy() {
        // Do nothing
    }

}
