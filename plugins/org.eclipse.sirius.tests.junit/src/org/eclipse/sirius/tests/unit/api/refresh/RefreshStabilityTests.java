/*******************************************************************************
 * Copyright (c) 2019, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.Collections;

import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtensionProvider;
import org.eclipse.sirius.diagram.business.api.refresh.RefreshExtensionService;
import org.eclipse.sirius.diagram.business.internal.helper.refresh.RefreshExtensionProviderDescriptor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;

/**
 * Check that the diagram refresh algorithm produces a deterministic result.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class RefreshStabilityTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/stability/";

    private static final String PATH2 = "/data/unit/refresh/stability2/";

    private static final String SEMANTIC_MODEL_FILENAME = "My.ecore";

    private static final String MODELER_FILENAME = "My.odesign";

    private static final String AIRD_MODEL_FILENAME = "representations.aird";

    private RefreshExtensionProviderDescriptor refreshExtensionProviderDescriptor;

    @SuppressWarnings("restriction")
    @Override
    public void setUp() throws Exception {
        super.setUp();

        platformProblemsListener.clearErrors();
        platformProblemsListener.clearWarnings();

        // register a custom RefreshExtensionProviderDescriptor so that the beforeRefresh method throw a NPE or CCE
        refreshExtensionProviderDescriptor = new RefreshExtensionProviderDescriptor(new ConfigurationElementHandle(null, 0) {
            @Override
            public String getAttribute(String propertyName) {
                return "pipo";
            }
        }) {
            @Override
            public IRefreshExtensionProvider getProviderInstance() {
                return new IRefreshExtensionProvider() {

                    @Override
                    public boolean provides(DDiagram diagram) {
                        return diagram.getName().startsWith("RefreshTest");
                    }

                    @Override
                    public IRefreshExtension getRefreshExtension(DDiagram diagram) {
                        return new IRefreshExtension() {

                            @Override
                            public void postRefresh(DDiagram dDiagram) {
                            }

                            @Override
                            public void beforeRefresh(DDiagram dDiagram) {
                                String name = diagram.getName();
                                if (name.contains("NPE")) {
                                    throw new NullPointerException();
                                } else if (name.contains("CCE")) {
                                    throw new ClassCastException();
                                }
                            }
                        };
                    }
                };
            }
        };
        RefreshExtensionService.getInstance().getProviders().add(refreshExtensionProviderDescriptor);
    }

    /**
     * Test that the refresh algorithm will process the semantic elements in a deterministic order when the semantic
     * candidate expression is empty.
     * 
     * @throws Exception
     */
    public void testEmptySemanticCandidatesExpression() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, AIRD_MODEL_FILENAME, SEMANTIC_MODEL_FILENAME, MODELER_FILENAME);

        // If there was a bug it could be random because of the usage of Set instead of List. So we launch it 10 times.
        for (int i = 0; i < 10; i++) {
            doTestEmptySemanticCandidatesExpression();
        }
    }

    private void doTestEmptySemanticCandidatesExpression() throws CoreException {
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + AIRD_MODEL_FILENAME, true);
        session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation dRepresentation = getRepresentationsByName("new MyDiagram").get(0);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation, new NullProgressMonitor());

        assertEquals("Bad session status", SessionStatus.SYNC, session.getStatus());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        closeSession(session);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test that the NPE and CCE raised during refresh at opening are properly catched and logged.
     * 
     * @throws Exception
     */
    public void testExceptionCatchDuringRefreshAtOpening() throws Exception {
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH2, AIRD_MODEL_FILENAME, SEMANTIC_MODEL_FILENAME);

        platformProblemsListener.setWarningCatchActive(true);
        platformProblemsListener.setErrorCatchActive(true);

        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME), Collections.emptyList(), TEMPORARY_PROJECT_NAME + "/" + AIRD_MODEL_FILENAME);

        // Open a representation that refreshes without error
        IEditorPart editor = openRepresentation("RefreshTest package entities");
        assertFalse("A warning occur", platformProblemsListener.doesAWarningOccurs());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // Open a representation that raises a NPE in the refresh part
        editor = openRepresentation("RefreshTest package entitiesNPE");
        assertTrue("A warning should have occurred. The NPE in the refresh should have been catched and logged", platformProblemsListener.doesAWarningOccurs());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // Open a representation that raises a CCE in the refresh part
        editor = openRepresentation("RefreshTest package entitiesCCE");
        assertTrue("A warning should have occurred. The CCE in the refresh should have been catched and logged", platformProblemsListener.doesAWarningOccurs());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        closeSession(session);
        TestsUtil.synchronizationWithUIThread();

        platformProblemsListener.clearWarnings();
    }

    private IEditorPart openRepresentation(String name) {
        DRepresentation dRepresentation = getRepresentationsByName(name).get(0);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation, new NullProgressMonitor());
        assertEquals("Bad session status", SessionStatus.SYNC, session.getStatus());
        assertFalse("An error occurred", platformProblemsListener.doesAnErrorOccurs());
        TestsUtil.synchronizationWithUIThread();
        return editor;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        RefreshExtensionService.getInstance().getProviders().remove(refreshExtensionProviderDescriptor);
    }
}
