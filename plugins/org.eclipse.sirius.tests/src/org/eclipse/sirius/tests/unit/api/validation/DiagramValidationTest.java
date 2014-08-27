/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.validation;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.ui.util.WorkbenchPartDescriptor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusMarkerNavigationProvider;
import org.eclipse.sirius.diagram.ui.part.ValidateAction;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IEditorPart;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Validation tests.
 * 
 * @author dlecan
 */
public class DiagramValidationTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/validation/My.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/validation/ticket1666.odesign";

    private static final String VIEWPOINT_NAME = "Ticket 1666";

    private static final String REPRESENTATION_DESC_NAME = "Validation";

    private static final String REPRESENTATION_DESC_NAME_BREAKDOWN = "Breakdown";

    private static final String ECLASS_NAME = "demo2";

    private DDiagram diagram;

    private IEditorPart editorPart;

    private ILogListener logListener;

    private EPackage subTicket1666_1EPackage;

    private EPackage rootEPackage;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();
        logListener = createMock(ILogListener.class);
        replay(logListener);
        DiagramPlugin.getDefault().getLog().addLogListener(logListener);
        DiagramUIPlugin.getPlugin().getLog().addLogListener(logListener);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

        initViewpoint(VIEWPOINT_NAME);
        subTicket1666_1EPackage = ((EPackage) semanticModel).getESubpackages().get(1);
        rootEPackage = ((EPackage) semanticModel).getESubpackages().get(2);
    }

    /**
     * Test case. It checks that source point location of a specific edge does
     * not change after zoom in operation.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testValidation() throws Exception {
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, subTicket1666_1EPackage);

        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        ConstraintStub.setEClassName(ECLASS_NAME);
        ConstraintStub.setCalled(false);

        // Save edit part to create session .aird file
        editorPart.doSave(new NullProgressMonitor());

        // Run validation
        WorkbenchPartDescriptor workbenchPartDescriptor = new WorkbenchPartDescriptor(editorPart.getSite().getId(), editorPart.getClass(), editorPart.getSite().getPage());
        ValidateAction va = new ValidateAction(workbenchPartDescriptor);
        va.run();

        assertTrue("Validation constraint has not been called", ConstraintStub.hasBeenCalled());

        // Get session .aird file to find validation warnings
        IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
        file.refreshLocal(1, new NullProgressMonitor());
        IMarker[] findUIMarkers = file.findMarkers(SiriusMarkerNavigationProvider.MARKER_TYPE, true, IResource.DEPTH_INFINITE);
        assertTrue("At least one marker must be found", findUIMarkers.length >= 1);

        boolean found = false;
        for (int i = 0; i < findUIMarkers.length && !found; i++) {
            IMarker iMarker = findUIMarkers[i];
            String message = (String) iMarker.getAttribute(IMarker.MESSAGE);
            found = message != null && message.indexOf(ECLASS_NAME) > -1;
        }
        assertTrue("Marker created by validation was not found", found);

        verify(logListener);
    }

    /**
     * Test there is only one error by elements not validate and not many errors
     * message duplicate for same element. Test VP-2940
     * 
     * @throws Exception
     *             Test errors.
     */
    public void testValidationOnlyOneErrorMessage() throws Exception {
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME_BREAKDOWN, rootEPackage);

        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        ConstraintStub.setCalled(false);

        // Save edit part to create session .aird file
        editorPart.doSave(new NullProgressMonitor());

        // Run validation
        WorkbenchPartDescriptor workbenchPartDescriptor = new WorkbenchPartDescriptor(editorPart.getSite().getId(), editorPart.getClass(), editorPart.getSite().getPage());
        ValidateAction va = new ValidateAction(workbenchPartDescriptor);
        va.run();

        // Get session .aird file to find validation warnings
        IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
        file.refreshLocal(1, new NullProgressMonitor());
        IMarker[] foundUIMarkers = file.findMarkers(SiriusMarkerNavigationProvider.MARKER_TYPE, true, IResource.DEPTH_INFINITE);
        assertEquals("Five markers must be found", 5, foundUIMarkers.length);

        // Check markers
        EPackage root = (EPackage) ((DSemanticDecorator) diagram).getTarget();
        checkMarkers(root, foundUIMarkers, 0, 1, "element_edge_");

        EPackage ap1 = root.getESubpackages().get(0);
        checkMarkers(ap1, foundUIMarkers, 1, 1, "container_");

        EPackage ap2 = ap1.getESubpackages().get(0);
        checkMarkers(ap2, foundUIMarkers, 1, 1, "container_");

        verify(logListener);
    }

    private void checkMarkers(EPackage tgt, IMarker[] foundMarkers, int nbViewMarker, int nbSemanticMarker, String locationPrefix) {
        int graphicMarkers = 0;
        int semanticMarkers = 0;
        for (IMarker marker : foundMarkers) {
            try {
                String location = (String) marker.getAttribute(IMarker.LOCATION);
                String message = (String) marker.getAttribute(IMarker.MESSAGE);

                if (location.endsWith(locationPrefix + tgt.getName())) {
                    if (message.startsWith("Graphic - ") && message.endsWith("-> View rule OK")) {
                        graphicMarkers++;
                    } else if (message.endsWith("-> Semantic rule OK")) {
                        semanticMarkers++;
                    }
                }
            } catch (CoreException e) {
                fail(e.getMessage());
            }
        }
        assertEquals("The element " + tgt.getName() + " does not have the expected ViewValidationRule markers", nbViewMarker, graphicMarkers);
        assertEquals("The element " + tgt.getName() + " does not have the expected SemanticValidationRule markers", nbSemanticMarker, semanticMarkers);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = null;
        editorPart = null;
        logListener = null;
        rootEPackage = null;
        subTicket1666_1EPackage = null;
        super.tearDown();
    }
}
