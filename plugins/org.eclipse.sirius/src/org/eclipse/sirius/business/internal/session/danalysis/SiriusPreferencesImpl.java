/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.SiriusPreferences;
import org.eclipse.sirius.business.internal.preferences.PreferenceHelper;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Sirius preferences used at session level.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class SiriusPreferencesImpl implements SiriusPreferences {
    private static final String PREF_REFRESH_ON_REPRESENTATION_OPENING = "PREF_REFRESH_ON_REPRESENTATION_OPENING"; //$NON-NLS-1$

    private static final String ORG_ECLIPSE_SIRIUS_UI_PLUGIN_ID = "org.eclipse.sirius.ui"; //$NON-NLS-1$

    ProjectScope projectScope;

    private String sessionId;

    /**
     * Default constructor.
     * 
     * @param dAnalysisSessionImpl
     *            the session associated to the SessionScope
     */
    public SiriusPreferencesImpl(DAnalysisSessionImpl dAnalysisSessionImpl) {
        sessionId = dAnalysisSessionImpl.getMainAnalysis().getUid();
        IFile airdFile = WorkspaceSynchronizer.getFile(dAnalysisSessionImpl.getMainAnalysis().eResource());
        if (airdFile != null) {
            IProject project = airdFile.getProject();
            projectScope = new ProjectScope(project);
        }
    }

    @Override
    public boolean isAutoRefresh() {
        Boolean preference = PreferenceHelper.getPreference(projectScope, SiriusPlugin.ID, sessionId, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), Boolean.class);

        return preference.booleanValue();
    }

    @Override
    public void setAutoRefresh(boolean value) {
        try {
            if (projectScope != null) {
                IEclipsePreferences sessionScopeNode = projectScope.getNode(SiriusPlugin.ID + sessionId);
                sessionScopeNode.putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), value);
                sessionScopeNode.flush();
            } else {
                SiriusPlugin.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, SiriusPlugin.ID, MessageFormat.format(Messages.SiriusPreferencesImpl_noProjectScope, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name())));
            }
        } catch (IllegalStateException | BackingStoreException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, e.getMessage(), e));
        }
    }

    @Override
    public void unsetAutoRefresh() {
        try {
            if (projectScope != null) {
                IEclipsePreferences sessionScopeNode = projectScope.getNode(SiriusPlugin.ID + sessionId);
                sessionScopeNode.remove(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name());
                sessionScopeNode.flush();
            } else {
                SiriusPlugin.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, SiriusPlugin.ID, MessageFormat.format(Messages.SiriusPreferencesImpl_noProjectScope, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name())));
            }
        } catch (IllegalStateException | BackingStoreException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, e.getMessage(), e));
        }
    }

    @Override
    public boolean isRefreshOnRepresentationOpening() {
        Boolean preference = PreferenceHelper.getPreference(projectScope, ORG_ECLIPSE_SIRIUS_UI_PLUGIN_ID, sessionId, PREF_REFRESH_ON_REPRESENTATION_OPENING, Boolean.class);

        return preference.booleanValue();
    }

    @Override
    public void setRefreshOnRepresentationOpening(boolean value) {
        try {
            if (projectScope != null) {
                IEclipsePreferences sessionScopeNode = projectScope.getNode(ORG_ECLIPSE_SIRIUS_UI_PLUGIN_ID + sessionId);
                sessionScopeNode.putBoolean(PREF_REFRESH_ON_REPRESENTATION_OPENING, value);
                sessionScopeNode.flush();
            } else {
                SiriusPlugin.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, SiriusPlugin.ID, MessageFormat.format(Messages.SiriusPreferencesImpl_noProjectScope, PREF_REFRESH_ON_REPRESENTATION_OPENING)));
            }
        } catch (IllegalStateException | BackingStoreException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, e.getMessage(), e));
        }
    }

    @Override
    public void unsetRefreshOnRepresentationOpening() {
        try {
            if (projectScope != null) {
                IEclipsePreferences sessionScopeNode = projectScope.getNode(ORG_ECLIPSE_SIRIUS_UI_PLUGIN_ID + sessionId);
                sessionScopeNode.remove(PREF_REFRESH_ON_REPRESENTATION_OPENING);
                sessionScopeNode.flush();
            } else {
                SiriusPlugin.getDefault().getLog()
                        .log(new Status(IStatus.ERROR, SiriusPlugin.ID, MessageFormat.format(Messages.SiriusPreferencesImpl_noProjectScope, PREF_REFRESH_ON_REPRESENTATION_OPENING)));
            }
        } catch (IllegalStateException | BackingStoreException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, e.getMessage(), e));
        }
    }
}
