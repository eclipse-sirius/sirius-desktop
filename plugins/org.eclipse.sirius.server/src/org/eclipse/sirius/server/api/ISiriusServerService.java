/*******************************************************************************
 * Copyright (c) 2018, 2020 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.api;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface used to contribute to the Sirius server API. Implementations of
 * this interface will have to be annotated with {@link SiriusServerPath} to
 * indicate the path of the URLs that they support.
 *
 * @author sbegaudeau
 */
public interface ISiriusServerService {
    /** The GET HTTP method. */
    String GET = "GET"; //$NON-NLS-1$

    /** The HEAD HTTP method. */
    String HEAD = "HEAD"; //$NON-NLS-1$

    /** The PUT HTTP method. */
    String PUT = "PUT"; //$NON-NLS-1$

    /** The POST HTTP method. */
    String POST = "POST"; //$NON-NLS-1$

    /** The DELETE HTTP method. */
    String DELETE = "DELETE"; //$NON-NLS-1$

    /** The OPTIONS HTTP method. */
    String OPTIONS = "OPTIONS"; //$NON-NLS-1$

    /**
     * Process the given request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse process(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        Optional<SiriusServerResponse> optionalResponse = Optional.empty();
        switch (request.getMethod()) {
        case GET:
            optionalResponse = Optional.ofNullable(this.doGet(request, variables, remainingPart));
            break;
        case HEAD:
            optionalResponse = Optional.ofNullable(this.doHead(request, variables, remainingPart));
            break;
        case PUT:
            optionalResponse = Optional.ofNullable(this.doPut(request, variables, remainingPart));
            break;
        case POST:
            optionalResponse = Optional.ofNullable(this.doPost(request, variables, remainingPart));
            break;
        case DELETE:
            optionalResponse = Optional.ofNullable(this.doDelete(request, variables, remainingPart));
            break;
        case OPTIONS:
            optionalResponse = Optional.ofNullable(this.doOptions(request, variables, remainingPart));
            break;
        default:
            optionalResponse = Optional.ofNullable(this.doError(request, variables, remainingPart));
            break;
        }
        return optionalResponse.orElseGet(() -> this.doError(request, variables, remainingPart));
    }

    /**
     * Process the given GET request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse doGet(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return null;
    }

    /**
     * Process the given HEAD request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse doHead(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return null;
    }

    /**
     * Process the given PUT request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse doPut(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return null;
    }

    /**
     * Process the given POST request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse doPost(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return null;
    }

    /**
     * Process the given DELETE request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse doDelete(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return null;
    }

    /**
     * Process the given OPTIONS request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse doOptions(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return null;
    }

    /**
     * Process the given invalid request.
     *
     * @param request
     *            The HTTP request to process
     * @param variables
     *            The variables extracted from the request
     * @param remainingPart
     *            The remaining part of the request
     * @return The result to return
     */
    default SiriusServerResponse doError(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return new SiriusServerResponse(SiriusServerResponse.STATUS_BAD_REQUEST);
    }
}
