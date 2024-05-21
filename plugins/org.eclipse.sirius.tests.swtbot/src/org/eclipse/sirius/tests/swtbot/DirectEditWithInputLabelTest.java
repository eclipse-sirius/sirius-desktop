/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

/**
 * Test class for inputlabelExpression of directEditTool (see VP-3497).
 * 
 * @author lredor
 */
public class DirectEditWithInputLabelTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_NAME = "new ClassDiag";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "ClassDiag";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/directEdit/inputLabel/";

    private static final String FILE_DIR = "/";

    /**
     * The name of the class to edit.
     */
    private static final String CLASS_NAME = "MyClass";

    /**
     * The name of the class to edit with a specific behavior using "new" variables.
     */
    private static final String SPECIFIC_CLASS_NAME = "ClassA";

    /**
     * The expected name for the class to edit with a specific behavior using "new" variables.
     */
    private static final String SPECIFIC_CLASS_EXPECTED_NEW_NAME = "new ClassDiag ClassA";

    /**
     * The suffix to add at the end of the name.
     */
    private static final String SUFFIX = "Suffix";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a Node corresponds to
     * the expected one (specified in inputLabelExpression of directEditTool)
     * </LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDirectEditWithInputLabelExpressionOnNode() throws Exception {
        checkDirectEditWithInputLabelExpression("<<node>> ", AbstractDiagramNodeEditPart.class);
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a Node corresponds to the expected one (specified in
     * inputLabelExpression of directEditTool using diagram and view variables)</LI>
     * <LI>and that the application of the tool is OK (using diagram and view variables).</LI>
     * </UL>
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDirectEditWithInputLabelExpressionOnNodeUsingVariables() throws Exception {
        checkDirectEditWithInputLabelExpression("<<node>> ", SPECIFIC_CLASS_NAME, AbstractDiagramNodeEditPart.class);
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a Container corresponds to the expected one (specified in
     * inputLabelExpression of directEditTool)</LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDirectEditWithInputLabelExpressionOnContainer() throws Exception {
        checkDirectEditWithInputLabelExpression("<<container>> ");
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a List corresponds to
     * the expected one (specified in inputLabelExpression of directEditTool)
     * </LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDirectEditWithInputLabelExpressionOnList() throws Exception {
        checkDirectEditWithInputLabelExpression("<<list>> ");
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a bordered node of a
     * node corresponds to the expected one (specified in inputLabelExpression
     * of directEditTool)</LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDirectEditWithInputLabelExpressionOnBorderedNode() throws Exception {
        checkDirectEditWithInputLabelExpression("<<borderedNode>> ");
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a node in a container
     * corresponds to the expected one (specified in inputLabelExpression of
     * directEditTool)</LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDirectEditWithInputLabelExpressionOnNodeInContainer() throws Exception {
        checkDirectEditWithInputLabelExpression("<<nodeInContainer>> ", AbstractDiagramNodeEditPart.class);
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a diagram element
     * corresponds to the expected one (specified in inputLabelExpression of
     * directEditTool)</LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @param prefix
     *            The prefix used to search the editPart to rename (by direct
     *            edit tool).
     * 
     * @throws Exception
     *             Test error.
     */
    public void checkDirectEditWithInputLabelExpression(final String prefix) throws Exception {
        checkDirectEditWithInputLabelExpression(prefix, AbstractDiagramNameEditPart.class);
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a diagram element
     * corresponds to the expected one (specified in inputLabelExpression of
     * directEditTool)</LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @param prefix
     *            The prefix used to search the editPart to rename (by direct
     *            edit tool).
     * @param expectedEditPartType
     *            the type of the editPart to rename
     * @throws Exception
     *             Test error.
     */
    public void checkDirectEditWithInputLabelExpression(final String prefix, final Class<? extends EditPart> expectedEditPartType) throws Exception {
        checkDirectEditWithInputLabelExpression(prefix, CLASS_NAME, expectedEditPartType);
    }

    /**
     * This method checks
     * <UL>
     * <LI>that the display label during a direct edit of a diagram element corresponds to the expected one (specified
     * in inputLabelExpression of directEditTool)</LI>
     * <LI>and that the application of the tool is OK.</LI>
     * </UL>
     * 
     * @param prefix
     *            The prefix used to search the editPart to rename (by direct edit tool).
     * @param className
     *            The name of the class to rename
     * @param expectedEditPartType
     *            the type of the editPart to rename
     * @throws Exception
     *             Test error.
     */
    public void checkDirectEditWithInputLabelExpression(final String prefix, final String className, final Class<? extends EditPart> expectedEditPartType) throws Exception {
        // Click a first time on the edit part to select it.
        editor.click(prefix + className, expectedEditPartType);
        // Click a second time on the edit part to enable the direct edit mode.
        editor.clickCentered(prefix + className, expectedEditPartType);

        // editor.getEditPart(prefix + CLASS_NAME,
        // expectedEditPartType).activateDirectEdit();

        /* wait until text widget appears */
        editor.bot().text();
        /* Find the text widget and check its label now. */
        List<Text> controls = editor.bot().getFinder().findControls(editor.getWidget(), new IsInstanceOf<Text>(Text.class), true);
        if (controls.size() == 1) {
            final Text textControl = controls.get(0);
            UIThreadRunnable.syncExec(new VoidResult() {
                @Override
                public void run() {
                    if (CLASS_NAME.equals(className)) {
                        assertEquals(CLASS_NAME, textControl.getText());
                    } else {
                        assertEquals(SPECIFIC_CLASS_EXPECTED_NEW_NAME, textControl.getText());
                    }
                }
            });
        } else {
            throw new WidgetNotFoundException(String.format("Expected to find one text control, but found %s.  Is the editor in direct-edit mode?", controls.size()));
        }
        // Edit the current edit part by adding a suffix to its current name.
        editor.directEditTypeSuffix(SUFFIX);
        // Check that an editPart exists with the expected new name.
        if (CLASS_NAME.equals(className)) {
            editor.getEditPart(prefix + CLASS_NAME + SUFFIX, expectedEditPartType);
        } else {
            editor.getEditPart(prefix + SPECIFIC_CLASS_EXPECTED_NEW_NAME, expectedEditPartType);
        }
    }

}
