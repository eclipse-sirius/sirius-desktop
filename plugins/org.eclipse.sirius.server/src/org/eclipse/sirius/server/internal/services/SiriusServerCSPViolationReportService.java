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
package org.eclipse.sirius.server.internal.services;

import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_BAD_REQUEST;
import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_OK;

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
import org.eclipse.sirius.server.internal.SiriusServerPlugin;

/**
 * The service used to report CSP violations.
 *
 * @author sbegaudeau
 */
@SiriusServerPath("/csp-violation-reports")
public class SiriusServerCSPViolationReportService implements ISiriusServerService {
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
            StringBuilder builder = new StringBuilder();

            int bufferSize = 1024;
            final char[] buffer = new char[bufferSize];
            int index = reader.read(buffer, 0, buffer.length);
            while (index >= 0) {
                builder.append(buffer, 0, index);
                index = reader.read(buffer, 0, buffer.length);
            }
            String report = builder.toString();

            IStatus status = new Status(IStatus.ERROR, SiriusServerPlugin.PLUGIN_ID, report);
            SiriusServerPlugin.getPlugin().log(status);

            response = new SiriusServerResponse(STATUS_OK);
        } catch (IOException exception) {
            IStatus status = new Status(IStatus.ERROR, SiriusServerPlugin.PLUGIN_ID, exception.getMessage(), exception);
            SiriusServerPlugin.getPlugin().log(status);
        }
        return response;
    }
}
