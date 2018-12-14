/*******************************************************************************
 * Copyright (c) 2016-2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.edge;

import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabelWithAttachment;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.collect.Iterables;

/**
 * Tests to check the behavior of the preference "Show link between edge and its
 * labels on selection"
 * 
 * @author lredor
 */
public class AttachmentBetweenEdgeAndItsLabelsTest extends SiriusDiagramTestCase {

    protected static final String FOLDER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/computelabel/";

    private static final String DEFAULT_SEMANTIC_MODEL_PATH = FOLDER_PATH + "TestComputeLabel.ecore";

    private static final String DEFAULT_SESSION_FILE_PATH = FOLDER_PATH + "TestComputeLabel.aird";

    private static final String DEFAULT_MODELER_PATH = FOLDER_PATH + "testComputeLabelDiagram.odesign";

    protected static final String REPRESENTATION_DECRIPTION_NAME = "DiagramTestComputeLabel";

    private DDiagram diagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(DEFAULT_SEMANTIC_MODEL_PATH, DEFAULT_MODELER_PATH, DEFAULT_SESSION_FILE_PATH);
        assertEquals("Just one representation must be present in session", 1, getRepresentations(REPRESENTATION_DECRIPTION_NAME).size());
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DECRIPTION_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }

    /**
     * Check that there is only one attachment displayed when the preference is
     * enabled and a begin label is selected.
     */
    public void testAttachmentOnBeginLabelSelection() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION.name(), true);
        // Select the begin label
        IGraphicalEditPart edgeEditPart = getEditPart(getDiagramElementsFromLabel(diagram, "center_p1 package entitiesref").get(0), editor);
        DEdgeBeginNameEditPart labelEditPart = Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeBeginNameEditPart.class));
        editor.getDiagramGraphicalViewer().select(labelEditPart);
        TestsUtil.synchronizationWithUIThread();
        // Check that a specific edge is drawn between this label and the
        // corresponding edge
        assertTrue("An attachment between begin label and its edge should be visible.", isAttachmentLinkDisplayed(labelEditPart));
        assertFalse("An attachment between center label and its edge should not be visible.",
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeNameEditPart.class))));
        assertFalse("An attachment between end label and its edge should not be visible.",
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeEndNameEditPart.class))));
    }

    /**
     * Check that there is only one attachment displayed when the preference is
     * enabled and a end label is selected.
     */
    public void testAttachmentOnEndLabelSelection() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION.name(), true);
        // Select the end label
        IGraphicalEditPart edgeEditPart = getEditPart(getDiagramElementsFromLabel(diagram, "center_p1 package entitiesref").get(0), editor);
        DEdgeEndNameEditPart labelEditPart = Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeEndNameEditPart.class));
        editor.getDiagramGraphicalViewer().select(labelEditPart);
        TestsUtil.synchronizationWithUIThread();
        // Check that a specific edge is drawn between this label and the
        // corresponding edge
        assertTrue("An attachment between end label and its edge should be visible.", isAttachmentLinkDisplayed(labelEditPart));
        assertFalse("An attachment between begin label and its edge should not be visible.",
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeBeginNameEditPart.class))));
        assertFalse("An attachment between center label and its edge should not be visible.",
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeNameEditPart.class))));
    }

    /**
     * Check that there is only one attachment displayed when the preference is
     * enabled and a center label is selected (and also check that there is no
     * attachment displayed when the preference is disabled).
     */
    public void testAttachmentOnCenterLabelSelection() {
        testAttachmentOnCenterLabelSelection(false);
        editor.getDiagramGraphicalViewer().deselectAll();
        testAttachmentOnCenterLabelSelection(true);
    }

    /**
     * Check that there are 3 attachments displayed when the preference is
     * enabled and an edge with 3 labels is selected.
     */
    public void testAttachmentOnEdgeSelection() {
        testAttachmentOnEdgeSelection(true);
    }

    /**
     * Check that there is no attachment displayed when the preference is
     * disabled and edge is selected.
     */
    public void testNoAttachmentOnEdgeSelectionWithPreferenceDisabled() {
        testAttachmentOnEdgeSelection(false);
    }

    protected void testAttachmentOnCenterLabelSelection(boolean showLink) {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION.name(), showLink);
        // Select the center label
        IGraphicalEditPart edgeEditPart = getEditPart(getDiagramElementsFromLabel(diagram, "center_p1 package entitiesref").get(0), editor);
        DEdgeNameEditPart labelEditPart = Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeNameEditPart.class));
        editor.getDiagramGraphicalViewer().select(labelEditPart);
        TestsUtil.synchronizationWithUIThread();
        // Check that a specific edge is drawn between this label and the
        // corresponding edge
        assertEquals("An attachment between center label and its edge should be visible.", showLink, isAttachmentLinkDisplayed(labelEditPart));
        assertFalse("An attachment between begin label and its edge should not be visible.",
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeBeginNameEditPart.class))));
        assertFalse("An attachment between end label and its edge should not be visible.",
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeEndNameEditPart.class))));
    }

    protected void testAttachmentOnEdgeSelection(boolean showLink) {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_LINK_EDGE_LABEL_ON_SELECTION.name(), showLink);
        // Select the edge
        IGraphicalEditPart edgeEditPart = getEditPart(getDiagramElementsFromLabel(diagram, "center_p1 package entitiesref").get(0), editor);
        editor.getDiagramGraphicalViewer().select(edgeEditPart);
        TestsUtil.synchronizationWithUIThread();
        // Check that a specific edge is drawn between edge and its labels
        assertEquals("An attachment between begin label and its edge should be visible.", showLink,
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeBeginNameEditPart.class))));
        assertEquals("An attachment between center label and its edge should be visible.", showLink,
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeNameEditPart.class))));
        assertEquals("An attachment between end label and its edge should be visible.", showLink,
                isAttachmentLinkDisplayed(Iterables.getOnlyElement(Iterables.filter(edgeEditPart.getChildren(), DEdgeEndNameEditPart.class))));
    }

    private boolean isAttachmentLinkDisplayed(AbstractDEdgeNameEditPart labelEditPart) {
        String qualifiedName = "center";
        if (labelEditPart instanceof DEdgeBeginNameEditPart) {
            qualifiedName = "begin";
        } else if (labelEditPart instanceof DEdgeEndNameEditPart) {
            qualifiedName = "end";
        }
        IFigure figure = labelEditPart.getFigure();
        assertTrue("The " + qualifiedName + " label should be a SiriusWrapLabelWithAttachment.", figure instanceof SiriusWrapLabelWithAttachment);
        // Get the attachment polyline through reflection
        Optional<Object> attachmentObject = ReflectionHelper.getFieldValueWithoutException(figure, "attachment");
        assertTrue("Not possible to retreive attachment polyline of SiriusWrapLabelWithAttachment.", attachmentObject.isPresent());
        return ((Polyline) attachmentObject.get()).isVisible();
    }
}
