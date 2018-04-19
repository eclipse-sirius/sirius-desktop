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
package org.eclipse.sirius.server.backend.internal;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.server.backend.internal.services.dashboard.SiriusServerDashboardService;
import org.eclipse.sirius.server.backend.internal.services.project.SiriusServerProjectService;
import org.eclipse.sirius.server.backend.internal.services.projects.SiriusServerProjectsService;

/**
 * Filter used to dynamically dispatch request to the appropriate service.
 *
 * @author sbegaudeau
 */
public class SiriusServerBackendFilter implements Filter {

    /**
     * The default character encoding.
     */
    private static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;

            Optional<SiriusServerResponse> optionalResponse = this.processRequest(request);
            if (optionalResponse.isPresent()) {
                SiriusServerResponse httpServiceResponse = optionalResponse.get();
                Object payload = httpServiceResponse.getPayload();
                String jsonPayload = new Gson().toJson(payload);

                response.setStatus(httpServiceResponse.getStatus());
                response.setContentType(httpServiceResponse.getContentType());
                response.setCharacterEncoding(UTF_8);
                response.getWriter().write(jsonPayload);
            } else {
                response.setStatus(500);
                response.getWriter().write("Something went wrong..."); //$NON-NLS-1$
            }
        }
    }

    /**
     * Process the given request by invoking the proper Sirius server service.
     *
     * @param request
     *            The request
     * @return A proper response if a service supporting the given request has
     *         been found, an error response otherwise
     */
    private Optional<SiriusServerResponse> processRequest(HttpServletRequest request) {
        List<SiriusServerServiceDescriptor> descriptors = this.getDescriptors(request);
        descriptors.sort((descriptor1, descriptor2) -> descriptor2.getResult().getVariables().size() - descriptor1.getResult().getVariables().size());

        Optional<SiriusServerServiceDescriptor> optionalDescriptor = descriptors.stream().findFirst();
        return optionalDescriptor.flatMap(descriptor -> {
            Optional<ISiriusServerService> optionalServiceInstance = this.createServiceInstance(descriptor.getServiceClass());

            Map<String, String> variables = descriptor.getResult().getVariables();
            String remainingPart = descriptor.getResult().getRemainingPart();
            return optionalServiceInstance.map(service -> service.process(request, variables, remainingPart));
        });
    }

    /**
     * Computes the best {@link SiriusServerServiceDescriptor} for the given
     * request.
     *
     * @param request
     *            The request
     * @return The list of {@link SiriusServerServiceDescriptor} which can
     *         handle the given request
     */
    private List<SiriusServerServiceDescriptor> getDescriptors(HttpServletRequest request) {
        List<Class<? extends ISiriusServerService>> serviceClasses = new ArrayList<>();
        serviceClasses.add(SiriusServerDashboardService.class);
        serviceClasses.add(SiriusServerProjectsService.class);
        serviceClasses.add(SiriusServerProjectService.class);

        List<SiriusServerServiceDescriptor> descriptors = new ArrayList<>();
        for (Class<? extends ISiriusServerService> serviceClass : serviceClasses) {
            SiriusServerPath siriusServerPath = serviceClass.getAnnotation(SiriusServerPath.class);
            String path = siriusServerPath.value();

            SiriusServerPathMatcher matcher = new SiriusServerPathMatcher(path);
            SiriusServerMatchResult result = matcher.match(request.getServletPath());
            if (result.hasMatched()) {
                descriptors.add(new SiriusServerServiceDescriptor(serviceClass, result));
            }
        }
        return descriptors;
    }

    /**
     * Creates the service instance from the given service class.
     *
     * @param serviceClass
     *            The service class
     * @return The service instance created
     */
    private Optional<ISiriusServerService> createServiceInstance(Class<? extends ISiriusServerService> serviceClass) {
        try {
            ISiriusServerService httpService = serviceClass.newInstance();
            return Optional.of(httpService);
        } catch (InstantiationException | IllegalAccessException exception) {
            IStatus status = new Status(IStatus.ERROR, SiriusServerBackendPlugin.PLUGIN_ID, exception.getMessage(), exception);
            SiriusServerBackendPlugin.getPlugin().log(status);
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        // Do nothing
    }

    /**
     * Descriptor containing both a service class and a match result.
     *
     * @author sbegaudeau
     */
    private static class SiriusServerServiceDescriptor {
        /**
         * The service class.
         */
        private Class<? extends ISiriusServerService> serviceClass;

        /**
         * The match result.
         */
        private SiriusServerMatchResult result;

        /**
         * The constructor.
         *
         * @param serviceClass
         *            The service class
         * @param matcher
         *            The match result
         */
        SiriusServerServiceDescriptor(Class<? extends ISiriusServerService> serviceClass, SiriusServerMatchResult result) {
            this.serviceClass = serviceClass;
            this.result = result;
        }

        /**
         * Return the serviceClass.
         *
         * @return the serviceClass
         */
        public Class<? extends ISiriusServerService> getServiceClass() {
            return this.serviceClass;
        }

        /**
         * Return the match result.
         *
         * @return the match result
         */
        public SiriusServerMatchResult getResult() {
            return this.result;
        }
    }
}
