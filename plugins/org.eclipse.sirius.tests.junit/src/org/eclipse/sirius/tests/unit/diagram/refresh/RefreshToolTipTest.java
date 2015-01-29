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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Label;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.junit.Assert;

import com.google.common.collect.Iterables;

/**
 * A test ensuring that the DDiagramElement synchronizer correctly refreshes
 * tooltip. In particular, :
 * <ul>
 * <li>ensures that refresh works with a null ToolTip value.</li>
 * <li>ensures that tooltips are properly update when the label position is on
 * the border</li>
 * </ul>
 * <p>
 * Relevant tickets :
 * <ul>
 * <li>VP-2023 : NPE on tooltip refresh when creating a new element</li>
 * <li>VP-2028 : NPE on tooltip refresh when creating a new element</li>
 * <li>VP-2025 : Tooltips not properly updated when the label position is on the
 * border</li>
 * <li>VP-2030 : Tooltips not properly updated when the label position is on the
 * border</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class RefreshToolTipTest extends SiriusDiagramTestCase {

    private static final String TEST_DATA_FOLDER_FOR_NULL_VALUE = SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/tooltip/";

    private static final String SEMANTIC_MODEL_PATH_FOR_NULL_VALUE = TEST_DATA_FOLDER_FOR_NULL_VALUE + "vp-2023.uml";

    private static final String MODELER_DESCRIPTION_PATH_FOR_NULL_VALUE = TEST_DATA_FOLDER_FOR_NULL_VALUE + "vp-2023.odesign";

    private static final String SESSION_PATH_FOR_NULL_VALUE = TEST_DATA_FOLDER_FOR_NULL_VALUE + "vp-2023.aird";

    private static final String TEST_DATA_FOLDER_FOR_UPDATE = SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/tooltip/vp-2025/";

    private static final String SEMANTIC_MODEL_PATH_FOR_UPDATE = TEST_DATA_FOLDER_FOR_UPDATE + "vp-2025.ecore";

    private static final String SESSION_PATH_FOR_UPDATE = TEST_DATA_FOLDER_FOR_UPDATE + "vp-2025.aird";

    /**
     * Ensures that refresh works with a null ToolTip value (see VP-2023).
     * 
     * @throws Exception
     *             any exception
     */
    public void testRefreshToolTipWithNullValue() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH_FOR_NULL_VALUE, MODELER_DESCRIPTION_PATH_FOR_NULL_VALUE, SESSION_PATH_FOR_NULL_VALUE);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        DDiagram diagram = (DDiagram) getRepresentations("VP-2023").iterator().next();

        // Step 1 : checking that the diagram is empty
        assertTrue("Diagram should be empty", diagram.getOwnedDiagramElements().isEmpty());

        try {
            // Step 2 : applying a Creation tool that creates a Style with a
            // tooltip
            // expression that will return null.
            applyNodeCreationTool("Comment", diagram, diagram);

            // Step 3 : ensuring that the Creation works and raised no NPE
            // see VP-2023
            assertEquals("Diagram should not be empty", 1, diagram.getOwnedDiagramElements().size());
        } catch (NullPointerException exception) {
            // We create an explicit error message if a NPE is raised
            Assert.fail("No expcetion should have been raised during Node creation. This is a regression.");
        }
    }

    /**
     * Ensures that tooltips are properly update when the label position is on
     * the border (see VP-2025).
     * 
     * @throws Exception
     *             any exception
     */
    public void testUpdateToolTipeWhenLabelPositionIsBorder() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/refresh/tooltip/vp-2025/vp-2025.odesign", "/" + TEMPORARY_PROJECT_NAME + "/" + "vp-2025.odesign");
        genericSetUp(SEMANTIC_MODEL_PATH_FOR_UPDATE, "/" + TEMPORARY_PROJECT_NAME + "/" + "vp-2025.odesign", SESSION_PATH_FOR_UPDATE);

        // Step 1 : opening the VSM editor and a diagram

        DDiagram diagram = (DDiagram) getRepresentations("VP-2025").iterator().next();
        DDiagramEditor editor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        try {
            // Step 2 : getting :
            // - a Node
            DNode node = (DNode) diagram.getOwnedDiagramElements().iterator().next();
            // - its Edit Part and its label's editPart
            IDiagramNodeEditPart nodeEditPart = (IDiagramNodeEditPart) getEditPart(node);
            Label nodeTooltip = (Label) nodeEditPart.getFigure().getToolTip();
            // - the labels of these 2 editParts
            DNodeNameEditPart nodeLabelEditPart = Iterables.filter(nodeEditPart.getChildren(), DNodeNameEditPart.class).iterator().next();
            Label nodeLabelToolTip = (Label) nodeLabelEditPart.getFigure().getToolTip();

            // Step 3 : ensuring that both label and node edit parts have the
            // expected tooltip value
            assertEquals("foo", node.getTooltipText());
            assertNotNull(nodeLabelToolTip);
            assertEquals("foo", nodeLabelToolTip.getText());
            assertNotNull(nodeTooltip);
            assertEquals("foo", nodeTooltip.getText());

            // Step 4 : modifying the VSM to change the tooltip expression
            final NodeMapping nodeMapping = getNodeMapping(getLayer(diagram, "Default"), "Employee");
            session.getTransactionalEditingDomain().getCommandStack().execute(new SiriusCommand(session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                    nodeMapping.getStyle().setTooltipExpression("bar");
                }
            });
            refresh(diagram);

            // Step 5 : ensuring tooltip has been updated
            nodeEditPart = (IDiagramNodeEditPart) getEditPart(node);
            nodeTooltip = (Label) nodeEditPart.getFigure().getToolTip();
            nodeLabelEditPart = Iterables.filter(nodeEditPart.getChildren(), DNodeNameEditPart.class).iterator().next();
            nodeLabelToolTip = (Label) nodeLabelEditPart.getFigure().getToolTip();

            assertEquals("bar", nodeTooltip.getText());
            assertNotNull(nodeLabelToolTip);
            assertNotNull(nodeTooltip);
        } finally {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
        }
    }
}
