/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and Obeo.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryListener2;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Preconditions;

/**
 * Reload the VSMs used inside a session when the global registry detects their
 * content has changed.
 * 
 * @author pcdavid
 */
public class SessionVSMUpdater implements ViewpointRegistryListener2 {
    private final DAnalysisSessionImpl session;

    /**
     * Create a new update for the specified session.
     * 
     * @param session
     *            the session to update.
     */
    public SessionVSMUpdater(DAnalysisSessionImpl session) {
        this.session = Preconditions.checkNotNull(session);
    }

    @Override
    public void modelerDesciptionFilesLoaded() {
        for (Resource res : findAllVSMResources(session)) {
            // Unload emtpy odesign.
            if (!res.isModified() && res.isLoaded() && res.getContents().isEmpty()) {
                session.unregisterResourceInCrossReferencer(res);
                res.unload();
            }
            // Reload unloaded odesign (ViewpointRegistry can unload them).
            IFile correspondingFile = WorkspaceSynchronizer.getFile(res);
            if (!res.isLoaded() && correspondingFile != null && correspondingFile.exists()) {
                loadVSMInSession(res);
            } else if (res.getURI().isPlatformPlugin()) {
                if (res.isLoaded()) {
                    res.unload();
                }
                loadVSMInSession(res);
            }
        }
        session.notifyListeners(SessionListener.VSM_UPDATED);
    }

    private void loadVSMInSession(Resource vsmResource) {
        try {
            vsmResource.load(Collections.emptyMap());
            if (vsmResource.isLoaded() && !vsmResource.getContents().isEmpty()) {
                session.registerResourceInCrossReferencer(vsmResource);
                // Refresh the imports of interpreter in case of new Java Extension
                InterpreterRegistry.prepareImportsFromSession(session.getInterpreter(), session);
            }
        } catch (IOException e) {
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.SessionVSMUpdater_VSMLoadErrorMsg, vsmResource.getURI()), e);
        }
    }

    private static List<Resource> findAllVSMResources(DAnalysisSessionImpl session) {
        // @formatter:off
        return session.allAnalyses().stream()
                      .flatMap(analysis -> analysis.getOwnedViews().stream())
                      .map(view -> view.getViewpoint().eResource())
                      .distinct()
                      .collect(Collectors.toList());
        // @formatter:on
    }
}
