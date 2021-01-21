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
package org.eclipse.sirius.ui.business.internal.preference;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.internal.preferences.PreferenceHelper;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PropertyPage;
import org.osgi.service.prefs.BackingStoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * {@link PropertyPage} presenting some preferences that are associated to the aird file.
 * 
 * @author lfasani
 */
public class SiriusPreferencesPropertyPage extends PropertyPage {

    private ProjectScope projectScope;

    private String siriusUiQualifier;

    private String siriusQualifier;

    private SessionPreferenceComponent component;

    @Override
    protected Control createContents(Composite parent) {
        noDefaultAndApplyButton();

        IResource resourceAird = Adapters.adapt(getElement(), IResource.class);
        IProject project = resourceAird.getProject();
        projectScope = new ProjectScope(project);

        String sessionId = getSessionId(resourceAird);
        siriusQualifier = SiriusPlugin.ID + sessionId;
        siriusUiQualifier = SiriusEditPlugin.ID + sessionId;

        component = new SessionPreferenceComponent();
        Composite composite = component.createComposite(parent);

        initialize(sessionId);

        return composite;
    }

    private String getSessionId(IResource resourceAird) {
        String dAnalysisUid = ""; //$NON-NLS-1$
        InputStream inputStream = null;
        SearchDAnalysisUidHandler testHandler = new SearchDAnalysisUidHandler();
        try {
            inputStream = new FileInputStream(new File(resourceAird.getRawLocation().toOSString()));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, testHandler);
        } catch (SAXException e) {
            // Expectedly thrown by the Handler
        } catch (IOException | ParserConfigurationException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } finally {
            try {
                inputStream.close();
            } catch (final IOException e) {
                SiriusPlugin.getDefault().error(e.getMessage(), e);
            }
        }
        dAnalysisUid = testHandler.getSessionId();
        return dAnalysisUid;
    }

    /**
     * This handler is used to get the DAnalysis.uid value.<br/>
     * It throws an SAXException as soon as the value is found to stop the parsing.
     *
     */
    private class SearchDAnalysisUidHandler extends DefaultHandler {
        private final String dAnalysisTag = ViewpointPackage.eNAME + ":" + ViewpointPackage.eINSTANCE.getDAnalysis().getName(); //$NON-NLS-1$

        private String dAnalysisUid;

        public String getSessionId() {
            return dAnalysisUid;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals(dAnalysisTag)) {
                dAnalysisUid = attributes.getValue(ViewpointPackage.eINSTANCE.getIdentifiedElement_Uid().getName());
                throw new SAXException();
            }
        }
    };

    @SuppressWarnings("deprecation")
    private void initialize(String sessionId) {
        boolean isProjectSpecificSettings = isProjectSpecificSettings();
        component.setProjectSpecificSettings(isProjectSpecificSettings);

        component.setRefreshAtOpening(
                PreferenceHelper.getPreference(projectScope, SiriusEditPlugin.ID, sessionId, SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), Boolean.class));
        component.setAutoRefresh(PreferenceHelper.getPreference(projectScope, SiriusPlugin.ID, sessionId, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), Boolean.class));

    }

    private boolean isProjectSpecificSettings() {
        try {
            return projectScope.getNode(siriusQualifier).keys().length > 0;
        } catch (BackingStoreException e) {
        }
        return false;
    }

    @Override
    public boolean performOk() {
        try {
            if (component.isProjectSpecificSettings()) {
                // Do not use FieldEditor.store() because it removes the node if the value is the default one.
                projectScope.getNode(siriusUiQualifier).putBoolean(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), component.isRefreshAtOpening());
                projectScope.getNode(siriusQualifier).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), component.isAutoRefresh());
            } else {
                projectScope.getNode(siriusUiQualifier).clear();
                projectScope.getNode(siriusQualifier).clear();
            }
            projectScope.getNode(siriusQualifier).flush();
            projectScope.getNode(siriusUiQualifier).flush();
        } catch (BackingStoreException e) {
            SiriusPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusPlugin.ID, e.getMessage(), e));
        }

        return true;
    }
}
