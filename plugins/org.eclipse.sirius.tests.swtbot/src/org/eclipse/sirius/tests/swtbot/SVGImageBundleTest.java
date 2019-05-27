/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.lang.reflect.Field;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.BundledImageEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.EllipseEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.BundledImageFigure;
import org.eclipse.sirius.ext.draw2d.figure.ODesignEllipseFigure;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Test Bundle image modification and refresh, from VSM and from semantic model.
 * 
 * 
 * @author jdupont
 */
public class SVGImageBundleTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "vp-1580";

    private static final String VSM_FILE = "1580.odesign";

    private static final String SESSION_FILE = "1580.aird";

    private static final String ECORE_FILE = "1580.ecore";

    private static final String FILE_DIR = "/";

    private static final String GROUP = "VP-1580 Group";

    private static final String DATA_UNIT_DIR = "data/unit/swtImageBundle/vp-1580/";

    private static final String REPRESENTATION_NAME = "Diagram";

    private static final String REPRESENTATION_INSTANCE_NAME = "Diagram";

    private static final String DEFAULT = "Default";

    private static final String NODE_CLASS = "Class";

    private static final String BUNDLED_IMAGE_DESCRIPTION = "Basic Shape blue square";

    private static final String GENERAL = "General";

    private static final String GRAY = "gray";

    private static final String CLASS1 = "Class1";

    private static final String CLASS2 = "Class2";

    private static final String PROPERTIES = "Properties";

    private static final String SEMANTIC = "Semantic";

    private static final String SQUARE = "square";

    private static final String TRIANGLE = "triangle";

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * Test that the semantic node modification change graphical the style.
     */
    public void testModifySemanticResources() {
        // Open representation
        openDiagramEditor();

        // Check diagram opening and verify that node are bundleImage type and
        // shape is square
        checkNodeIsCorrectTypeAndShape(CLASS1, SQUARE);
        checkNodeIsCorrectTypeAndShape(CLASS2, SQUARE);

        // Change property of node naming Class1 (Abstract true) to change the
        // type (change to
        // Ellipse description)
        changeNodeClassToAbstract(CLASS1);

        // The refresh is automatically do in runtime but with SWTBot it's
        // necessary to do a manual refresh
        editor.click(0, 0);
        editor.save();
        manualRefresh();

        // Check that the node Class1 is gray and corresponding to ellipse
        // description
        checkNodeIsCorrectTypeAndColor(CLASS1, GRAY);

    }

    /**
     * Test that the VSM BundleImage shape modification change graphical style.
     */
    public void testChangeVSM() {
        // Open diagram editor
        openDiagramEditor();

        checkNodeIsCorrectTypeAndShape(CLASS1, SQUARE);
        checkNodeIsCorrectTypeAndShape(CLASS2, SQUARE);

        // Open odesign file
        openVSM();

        // Select a node mapping
        SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();
        activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(REPRESENTATION_NAME).expandNode(DEFAULT)
                .expandNode(NODE_CLASS).select(BUNDLED_IMAGE_DESCRIPTION);

        // Change the shape square (from bundledImage) to triangle
        modifyVSM(SQUARE, TRIANGLE);

        // save the VSM
        activeEditor.setFocus();
        activeEditor.saveAndClose();

        // The refresh is automatically do in runtime but with SWTBot it's
        // necessary to do a manual refresh
        editor.click(0, 0);
        editor.save();
        manualRefresh();

        // Check that the node are a bundledImage type triangle
        checkNodeIsCorrectTypeAndShape(CLASS1, TRIANGLE);
        checkNodeIsCorrectTypeAndShape(CLASS2, TRIANGLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, getFilesUsedForTest());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Check that the node is a Bundled image description and the shape corresponding to shape pass in parameters.
     * 
     * @param name
     *            the name of edit part
     * @param the
     *            shape representing node
     */
    private void checkNodeIsCorrectTypeAndShape(String name, String shape) {
        SWTBotGefEditPart editPart = checkEditPart(name, BundledImageEditPart.class);
        assertTrue(editPart.part() instanceof BundledImageEditPart);
        IFigure figure = ((BundledImageEditPart) editPart.part()).getFigure();
        assertTrue(figure.getChildren().get(0) instanceof BundledImageFigure);
        BundledImageFigure bundleImage = (BundledImageFigure) figure.getChildren().get(0);
        try {
            // Reflexion to access shapeName field
            @SuppressWarnings("rawtypes")
            Class classBundleImage = bundleImage.getClass();
            Field shapeName = classBundleImage.getDeclaredField("shapeName");
            shapeName.setAccessible(true);
            String shapeNameValue = (String) shapeName.get(bundleImage);
            assertThat(shapeNameValue, equalTo(shape));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Check that the Node is an ellipse and color correspond to parameter.
     * 
     * @param name
     *            the edit part naming
     * @param color
     *            the color of edit part
     */
    private void checkNodeIsCorrectTypeAndColor(String name, String color) {
        SWTBotGefEditPart editPart = checkEditPart(name, EllipseEditPart.class);
        IFigure figure = ((EllipseEditPart) editPart.part()).getFigure();
        assertTrue(figure.getChildren().get(0) instanceof ODesignEllipseFigure);
        ODesignEllipseFigure ellipse = (ODesignEllipseFigure) figure.getChildren().get(0);
        assertThat(ellipse.getBackgroundColor(), equalTo(VisualBindingManager.getDefault().getColorFromName(color)));
    }

    /**
     * Change property node class corresponding to name parameters, to abstract. This change causes the modification
     * shape to ellipse
     * 
     * @param name
     *            the name of edit part
     */
    private void changeNodeClassToAbstract(String name) {
        selectAndcheckEditPart(name, AbstractDiagramNodeEditPart.class);
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(SEMANTIC, propertiesBot.bot());
        SWTBotTree tree = propertiesBot.bot().tree();
        tree.expandNode("Class1 -> Class2").select().getNode("Abstract").doubleClick();
        if (TestsUtil.isPhotonPlatformOrLater()) {
            SWTBotCheckBox checkBox = propertiesBot.bot().checkBox(0);
            checkBox.select();
        } else {
            SWTBotCCombo comboBox = propertiesBot.bot().ccomboBox(0);
            comboBox.setSelection(1);
        }
    }

    /**
     * Open the VSM
     */
    private void openVSM() {
        SWTBotView projectExplorer = bot.viewByTitle("Model Explorer");
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();
    }

    /**
     * Modify the VSM
     */
    private void modifyVSM(String oldShape, String shape) {
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL, propertiesBot.bot());
        try {
            SWTBotCombo comboBox = propertiesBot.bot().comboBox(oldShape);
            comboBox.setFocus();
            comboBox.setSelection(shape);
        } catch (WidgetNotFoundException wnfe) {
            SWTBotCCombo comboBox = propertiesBot.bot().ccomboBox(oldShape);
            comboBox.setFocus();
            comboBox.setSelection(shape);
        }
    }

    /**
     * Open the diagram editor
     */
    private void openDiagramEditor() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Return the selected edit part.
     * 
     * @param name
     * @param type
     * @return the selected edit part
     */
    private SWTBotGefEditPart checkEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);

        assertNotNull("The requested edit part should not be null", botPart);

        return botPart;
    }

    /**
     * Return the selected edit part.
     * 
     * @param name
     * @param type
     * @return the selected edit part
     */
    private SWTBotGefEditPart selectAndcheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = checkEditPart(name, type);
        botPart.select();

        return botPart;
    }

    /**
     * Return files used in the current test.
     */
    String[] getFilesUsedForTest() {
        return new String[] { VSM_FILE, ECORE_FILE, SESSION_FILE };
    }

}
