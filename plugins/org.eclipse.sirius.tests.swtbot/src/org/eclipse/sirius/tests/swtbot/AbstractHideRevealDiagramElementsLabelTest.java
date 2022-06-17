/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

/**
 * Abstract class providing facilities for ensuring that hide/reveal Labels
 * located on the border of a Node/Bordered Node works correctly.
 * 
 * Used by {@link HideRevealDiagramElementsLabelsTestWithOldUI} and
 * {@link HideRevealDiagramElementsLabelsTest}.
 * 
 * @author alagarde
 */
public class AbstractHideRevealDiagramElementsLabelTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * The tooltip for hiding a label.
     */
    protected static final String HIDE_LABEL_TOOLTIP = "Hide label";

    /**
     * The tooltip for revealing a label.
     */
    protected static final String REVEAL_LABEL_TOOLTIP = "Show label";

    /**
     * The tooltip for hiding an element.
     */
    protected static final String HIDE_ELEMENT_TOOLTIP = "Hide element";

    /**
     * The tooltip for revealing an element.
     */
    protected static final String REVEAL_ELEMENT_TOOLTIP = "Show element";

    /**
     * Name of the node to test.
     */
    protected static final String NODE_WITH_LABEL_NAME = "myEnum";

    /**
     * Name of the edge to test.
     */
    protected static final String EDGE_WITH_LABEL_NAME = "toB";

    /**
     * The suffix attributed to all labels shown in outline.
     */
    protected static final String LABEL_SUFFIX_IN_OUTLINE = "label";

    /**
     * Name of the bordered node to test
     */
    protected static final String BORDERED_NODE_WITH_LABEL_NAME = "A";

    private static final String REPRESENTATION_INSTANCE_NAME = "new 2330 Diagram";

    private static final String REPRESENTATION_NAME = "2330 Diagram";

    private static final String MODEL = "tc2330.ecore";

    private static final String SESSION_FILE = "tc2330.aird";

    private static final String VSM_FILE = "tc2330.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/tools/hide-reveal/tc-2330/";

    protected String FILE_DIR = "/";

    protected UIResource sessionAirdResource;

    protected UILocalSession localSession;

    protected String previousPollDelay;

    /**
     * The icon in the outline displayed when labels are shown (not hidden).
     */
    protected Image shownImage;

    /**
     * Boolean indicating if items of the outline are correctly decorated.
     */
    protected boolean outlineIsCorrectlyDecorated;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // We change the timeOut of SWTBot, as we often search for widget that
        // doesn't exist
        previousPollDelay = System.getProperty(SWTBotPreferences.KEY_DEFAULT_POLL_DELAY);
        System.setProperty(SWTBotPreferences.KEY_DEFAULT_POLL_DELAY, "0");

        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (previousPollDelay != null) {
            System.setProperty(SWTBotPreferences.KEY_DEFAULT_POLL_DELAY, previousPollDelay);
        }
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Ensures that there is a Label in the Diagram with the given label.
     * 
     * @param label
     *            the label to search
     */
    protected void checkLabelIsVisible(String label) {
        // If the editPart can be retrieved from the given name, it means that
        // the element has an LabelEditPart.
        SWTBotGefEditPart editPart = editor.getEditPart(label);
        assertNotNull("This element's label should not be hidden : " + label, editPart);
        // We must also ensure that the editPart containing this label is
        // visible
        assertNotNull("This element should be visible :  " + label, editPart.part().getParent());
        assertTrue("This element should be visible : " + label, editPart.part().getParent().isSelectable());
    }

    /**
     * Ensures that there is a Label in the Diagram with the given label.
     * 
     * @param label
     *            the label to search
     */
    protected void checkEdgeLabelIsVisible(String label) {
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new CheckEdgeLabelIsVisible(editor, label));
    }

    /**
     * Ensures that no Label with the given name is visible.
     * 
     * @param label
     *            the label to search
     */
    protected void checkLabelIsHidden(String label) {
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new CheckLabelIsHidden(editor, label));
    }

    /**
     * Ensures that no Label with the given name is visible.
     * 
     * @param label
     *            the label to search
     */
    protected void checkEdgeLabelIsHidden(String label) {
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new CheckEdgeLabelIsHidden(editor, label));
    }

    /**
     * Ensures that the given node is hidden.
     * 
     * @param nodeEditPart
     *            the editPart of the node to test
     * 
     */
    protected void checkNodeIsHidden(SWTBotGefEditPart nodeEditPart) {
        Node node = (Node) ((ShapeNodeEditPart) nodeEditPart.part()).getModel();
        DDiagramElement elt = (DDiagramElement) node.getElement();
        boolean isVisible = DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(elt.getParentDiagram(), elt);
        Assert.assertFalse("The node " + nodeEditPart + " should be hidden ", isVisible);
    }

    /**
     * Ensures that the given node is visible.
     * 
     * @param nodeEditPart
     *            the editPart of the node to test
     * 
     */
    protected void checkNodeIsVisible(SWTBotGefEditPart nodeEditPart) {
        Node node = (Node) ((ShapeNodeEditPart) nodeEditPart.part()).getModel();
        DDiagramElement elt = (DDiagramElement) node.getElement();
        boolean isVisible = DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(elt.getParentDiagram(), elt);
        Assert.assertTrue("The node " + nodeEditPart + " should be visible ", isVisible);
    }

    /**
     * Ensures that the given node is hidden.
     * 
     * @param edgeEditPart
     *            the editPart of the node to test
     * 
     */
    protected void checkEdgeIsHidden(SWTBotGefEditPart edgeEditPart) {
        Edge edge = (Edge) ((GraphicalEditPart) edgeEditPart.part()).getModel();
        DDiagramElement elt = (DDiagramElement) edge.getElement();
        boolean isVisible = DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(elt.getParentDiagram(), elt);
        Assert.assertFalse("The edge " + edgeEditPart + " should be hidden ", isVisible);
    }

    /**
     * Ensures that the given item of the outline tree is correctly decorated,
     * according to the value of the given boolean.
     * 
     * @param item
     *            the TreeItem of the outline to test
     * @param mustHaveHiddenDecoration
     *            indicates whether the given item should be decorated or not
     */
    protected void checkOutlineIsCorrectlyDecorated(final SWTBotTreeItem item, final boolean mustHaveHiddenDecoration) {
        SWTBotUtils.waitAllUiEvents();
        item.display.asyncExec(new Runnable() {

            @Override
            public void run() {
                boolean a = mustHaveHiddenDecoration;
                boolean b = (shownImage != item.widget.getImage());
                outlineIsCorrectlyDecorated = (a == b);
            }
        });
        SWTBotUtils.waitAllUiEvents();
        Assert.assertTrue("Outline item is not decorated as expected", outlineIsCorrectlyDecorated);
    }

    /**
     * Sets the image when label is shown from the given item.
     * 
     * @param item
     *            the item of a label in the outline
     */
    protected void setShownImage(final SWTBotTreeItem item) {
        item.display.asyncExec(new Runnable() {
            @Override
            public void run() {
                shownImage = item.widget.getImage();
            }
        });

    }

    /**
     * Uses reflection to get the outlineView of the current editor, and expand
     * it.
     * 
     * @param expandRecursively
     *            true if outline must be recursively expanded
     * @return a {@link SWTBotView} allowing to manipulate the current editor's
     *         outline
     * @throws Exception
     *             if a reflective exception occurs when trying to get the
     *             outline
     */
    protected SWTBotView getAndExpandOutlineView(boolean expandRecursively) throws Exception {
        SWTBotView view = SWTBotCommonHelper.getOutlineView(designerViews);

        view.bot().tree().expandNode("p", expandRecursively);
        return view;
    }

    /**
     * Inner class to check if the label of this edit part is hidden.
     * 
     * @author lredor
     */
    private class CheckLabelIsHidden extends DefaultCondition {

        private final String label;

        private final SWTBotSiriusDiagramEditor editor;

        /**
         * Constructor.
         * 
         * @param editor
         *            the current editor
         * @param label
         *            name of the edit part to wait for its hiding.
         */
        public CheckLabelIsHidden(SWTBotSiriusDiagramEditor editor, String label) {
            this.label = label;
            this.editor = editor;
        }

        @Override
        public boolean test() throws Exception {
            // If no element can be found from the given name, it means that the
            // element doesn't exist or is hidden
            boolean elementCanBeFound = true;
            try {
                ((AbstractGraphicalEditPart) editor.getEditPart(label).part()).getFigure();
                elementCanBeFound = true;
            } catch (WidgetNotFoundException e) {
                elementCanBeFound = false;
            }
            return !elementCanBeFound;
        }

        @Override
        public String getFailureMessage() {
            return "The label \"" + this.label + "\" is still visible.";
        }
    }

    /**
     * Inner class to check if the label of this edge edit part is hidden.
     * 
     * @author nlepine
     */
    protected class CheckEdgeLabelIsHidden extends DefaultCondition {

        private final String label;

        private final SWTBotSiriusDiagramEditor editor;

        /**
         * Constructor.
         * 
         * @param editor
         *            the current editor
         * @param label
         *            name of the edit part to wait for its hiding.
         */
        public CheckEdgeLabelIsHidden(SWTBotSiriusDiagramEditor editor, String label) {
            this.label = label;
            this.editor = editor;
        }

        @Override
        public boolean test() throws Exception {
            return !((AbstractGraphicalEditPart) editor.getEditPart(label).part()).getFigure().isVisible();
        }

        @Override
        public String getFailureMessage() {
            return "The label \"" + this.label + "\" is still visible.";
        }
    }

    /**
     * Inner class to check if the label of this edge edit part is visible.
     * 
     * @author lredor
     */
    protected class CheckEdgeLabelIsVisible extends DefaultCondition {

        private final String label;

        private final SWTBotSiriusDiagramEditor editor;

        private String errorMessage;

        /**
         * Constructor.
         * 
         * @param editor
         *            the current editor
         * @param label
         *            name of the edit part to wait for its hiding.
         */
        public CheckEdgeLabelIsVisible(SWTBotSiriusDiagramEditor editor, String label) {
            this.label = label;
            this.editor = editor;
        }

        @Override
        public boolean test() throws Exception {
            errorMessage = null;
            // If the editPart can be retrieved from the given name, it means that
            // the element has an LabelEditPart.
            SWTBotGefEditPart editPart = editor.getEditPart(label);
            if (editPart == null) {
                errorMessage = "This element's label should not be hidden : " + label;
            } else if (!((AbstractGraphicalEditPart) editPart.part()).getFigure().isVisible()) {
                errorMessage = "This element should be visible : " + label;
            }
            return errorMessage == null;
        }

        @Override
        public String getFailureMessage() {
            return errorMessage;
        }
    }
}
