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
package org.eclipse.sirius.server.graphql.internal;

import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_BAD_REQUEST;
import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_OK;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.server.api.ISiriusServerService;
import org.eclipse.sirius.server.api.SiriusServerPath;
import org.eclipse.sirius.server.api.SiriusServerResponse;
import org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.api.SiriusGraphQLInterpreter;

/**
 * The GraphQL API.
 *
 * @author sbegaudeau
 */
@SiriusServerPath("/graphql")
public class SiriusServerGraphQLService implements ISiriusServerService {

    /**
     * The UTF-8 encoding.
     */
    private static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

    @Override
    public SiriusServerResponse doOptions(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        return new SiriusServerResponse(STATUS_OK);
    }

    @Override
    public SiriusServerResponse doPost(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        SiriusServerResponse response = new SiriusServerResponse(STATUS_BAD_REQUEST);
        try (Reader reader = new InputStreamReader(request.getInputStream(), UTF_8);) {
            SiriusServerGraphQLPayload payload = new Gson().fromJson(reader, SiriusServerGraphQLPayload.class);
            ISiriusGraphQLQueryResult result = new SiriusGraphQLInterpreter().execute(payload.getQuery(), payload.getVariables(), payload.getOperationName(), request);

            response = new SiriusServerResponse(STATUS_OK, result.getData());
        } catch (IOException exception) {
            IStatus status = new Status(IStatus.ERROR, SiriusServerGraphQLPlugin.PLUGIN_ID, exception.getMessage(), exception);
            SiriusServerGraphQLPlugin.getPlugin().log(status);
        }
        return response;
    }
}
