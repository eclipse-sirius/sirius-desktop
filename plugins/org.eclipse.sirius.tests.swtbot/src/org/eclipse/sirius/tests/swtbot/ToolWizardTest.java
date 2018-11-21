/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
import static org.hamcrest.Matchers.containsString;

import java.util.Collection;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Test class for all tool wizards : selection wizard, pane based wizard, ...
 * 
 * @author dlecan
 */
public class ToolWizardTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Test predicate.
     * 
     * @author dlecan
     */
    private final class AttributeNamePredicate implements Predicate<EAttribute> {
        private final String name;

        /**
         * @param name
         *            Name.
         */
        public AttributeNamePredicate(final String name) {
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean apply(final EAttribute arg0) {
            return arg0.getName().equals(name);
        }
    }

    private static final String WIZARD_TITLE = "Name selection";

    private static final String REPRESENTATION_INSTANCE_NAME = "p package entities";

    private static final String REPRESENTATION_NAME = "EntitiesTest";

    private static final String MODEL = "toolWizard.ecore";

    private static final String SESSION_FILE = "toolWizard.aird";

    private static final String VSM_FILE = "toolWizard.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/toolWizard/";

    private static final String FILE_DIR = "/";

    // Point somewhere in diagram
    private static final Point SOMEWHERE_IN_DIAGRAM = new Point(322, 223);

    private static final Point SOMEWHERE_IN_PACKAGE_P1 = new Point(240, 117);

    private static final Point SOMEWHERE_IN_BODY_CLASS_EC1 = new Point(44, 52);

    private static final Point SOMEWHERE_IN_HEADER_CLASS_EC1 = new Point(44, 30);

    private static final Point SOMEWHERE_IN_BODY_CLASS_EC2 = new Point(387, 82);

    private static final Point ON_EDGE_LABEL = new Point(89, 103);

    private static final Point ON_EDGE = new Point(94, 132);

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddClassOnDiagram() throws Exception {
        editor.activateTool("Class");
        editor.click(SOMEWHERE_IN_DIAGRAM.x, SOMEWHERE_IN_DIAGRAM.y);

        // Throw an exception if edit part is not found
        editor.getEditPart("new EClass 3");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddClassInPackage() throws Exception {
        editor.activateTool("Class");
        editor.click(SOMEWHERE_IN_PACKAGE_P1.x, SOMEWHERE_IN_PACKAGE_P1.y);

        // Throw an exception if edit part is not found
        editor.getEditPart("new EClass 3");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddOperationInClassHeader() throws Exception {
        addOperationIn(SOMEWHERE_IN_HEADER_CLASS_EC1);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddOperationInClassBody() throws Exception {
        addOperationIn(SOMEWHERE_IN_BODY_CLASS_EC1);
    }

    private void addOperationIn(final Point pointWhereToClick) {
        final EClass eClass = getEClass("EC1");

        final int nbOfOperations = eClass.getEOperations().size();

        editor.activateTool("Operation");
        editor.click(pointWhereToClick.x, pointWhereToClick.y);

        final int newNbOfOperations = eClass.getEOperations().size();

        assertEquals("Wrong number of operations", nbOfOperations + 1, newNbOfOperations);
    }

    private EClass getEClass(final String name) {
        final IGraphicalEditPart editPart = (IGraphicalEditPart) editor.getEditPart(name).part();
        final DSemanticDecorator eObject = (DSemanticDecorator) editPart.resolveSemanticElement();
        final EClass eClass = (EClass) eObject.getTarget();
        return eClass;
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testImpossibleToAddOperationInClassBody() throws Exception {
        final EClass eClass = getEClass("EC2");

        final int nbOfOperations = eClass.getEOperations().size();

        editor.activateTool("Operation");
        editor.click(SOMEWHERE_IN_BODY_CLASS_EC2.x, SOMEWHERE_IN_BODY_CLASS_EC2.y);

        final int newNbOfOperations = eClass.getEOperations().size();

        assertEquals("Wrong number of operations", nbOfOperations, newNbOfOperations);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeAttributeNameThroughWizard() throws Exception {
        final EClass eClass = getEClass("new EClass 2");
        final EList<EAttribute> eAttributes = eClass.getEAttributes();

        final Collection<EAttribute> filtered = Collections2.filter(eAttributes, new AttributeNamePredicate("new Attribute"));
        assertEquals("One attribute should be found", 1, filtered.size());

        editor.activateTool("Change name");

        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                // Click on attribute named "new Attribute"
                editor.click(SOMEWHERE_IN_BODY_CLASS_EC2.x, SOMEWHERE_IN_BODY_CLASS_EC2.y);
            }
        });

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        final SWTBotShell shell = bot.shell(WIZARD_TITLE);
        shell.setFocus();

        final SWTBot shellBot = new SWTBot(shell.widget);

        // No need to click in displayed list, default selection is ok for this
        // test
        final SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();

        bot.waitUntilWidgetAppears(Conditions.shellCloses(shell));

        // Name of the attribute should have been changed by the wizard
        final Collection<EAttribute> filtered2 = Collections2.filter(eAttributes, new AttributeNamePredicate("EC1"));
        assertEquals("One attribute should be found", 1, filtered2.size());
    }

    /**
     * Tests that a double click on an element of a selection wizard triggered from the SelectModelElementVariable of a
     * tool acts as the finish button is clicked.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSelectModelElementVariableWizardWithDoubleClick() throws Exception {
        final EClass eClass = getEClass("new EClass 2");
        final EList<EAttribute> eAttributes = eClass.getEAttributes();

        final Collection<EAttribute> filtered = Collections2.filter(eAttributes, new AttributeNamePredicate("new Attribute"));
        assertEquals("One attribute should be found", 1, filtered.size());

        editor.activateTool("CreateEClassWizard");

        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                editor.click(0, 0);
            }
        });

        bot.waitUntilWidgetAppears(Conditions.shellIsActive("Selection Wizard"));

        final SWTBotShell shell = bot.shell("Selection Wizard");
        shell.setFocus();

        final SWTBot shellBot = new SWTBot(shell.widget);

        // No need to click in displayed list, default selection is ok for this
        // test
        shellBot.tree().getTreeItem("EC1").doubleClick();

        bot.waitUntilWidgetAppears(Conditions.shellCloses(shell));

        try {
            // Name of the attribute should have been changed by the wizard
            editor.getEditPart("EC1suffix");
        } catch (WidgetNotFoundException e) {
            fail("No new element with the name of the selected one from the wizard has been created.");
        }
    }

    /**
     * Tests that a double click on an element of a selection wizard opened from corresponding tool acts as the finish
     * button is clicked.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeAttributeNameThroughWizardWithDoubleClick() throws Exception {
        final EClass eClass = getEClass("new EClass 2");
        final EList<EAttribute> eAttributes = eClass.getEAttributes();

        final Collection<EAttribute> filtered = Collections2.filter(eAttributes, new AttributeNamePredicate("new Attribute"));
        assertEquals("One attribute should be found", 1, filtered.size());

        editor.activateTool("Change name");

        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                // Click on attribute named "new Attribute"
                editor.doubleClick(SOMEWHERE_IN_BODY_CLASS_EC2.x, SOMEWHERE_IN_BODY_CLASS_EC2.y);
            }
        });

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        final SWTBotShell shell = bot.shell(WIZARD_TITLE);
        shell.setFocus();

        final SWTBot shellBot = new SWTBot(shell.widget);

        // No need to click in displayed list, default selection is ok for this
        // test
        shellBot.tree().getTreeItem("EC1").doubleClick();

        bot.waitUntilWidgetAppears(Conditions.shellCloses(shell));

        // Name of the attribute should have been changed by the wizard
        final Collection<EAttribute> filtered2 = Collections2.filter(eAttributes, new AttributeNamePredicate("EC1"));
        assertEquals("One attribute should be found", 1, filtered2.size());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testChangeEdgeLabelThroughWizard() throws Exception {
        SWTBotGefEditPart edgeLabel = editor.getEditPart("newEReference1");
        IGraphicalEditPart edgeEditPart = (IGraphicalEditPart) edgeLabel.part();
        EObject target = edgeEditPart.resolveSemanticElement();
        assertThat(target.toString(), containsString("newEReference1"));

        editor.activateTool("Change name");

        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                // Click on edge label
                editor.click(ON_EDGE_LABEL.x, ON_EDGE_LABEL.y);
            }
        });

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        final SWTBotShell shell = bot.shell(WIZARD_TITLE);
        shell.setFocus();

        final SWTBot shellBot = new SWTBot(shell.widget);

        // No need to click in displayed list, default selection is ok for this
        // test
        final SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();

        bot.waitUntilWidgetAppears(Conditions.shellCloses(shell));

        // Edge label should have changed
        assertThat(target.toString(), containsString("EC1"));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRemoveEdgeViewThroughToolDescription() throws Exception {
        SWTBotGefEditPart edge = editor.getEditPart("newEReference1");
        IGraphicalEditPart edgeEditPart = (IGraphicalEditPart) edge.part();
        EObject target = edgeEditPart.resolveSemanticElement();
        assertThat(target.toString(), containsString("newEReference1"));

        editor.activateTool("Action on edges");

        // Click on edge label
        editor.click(ON_EDGE.x, ON_EDGE.y);

        // Edge label should disappear
        assertFalse(isVisible("newEReference1"));

    }

    private boolean isVisible(String gefEditPartName) throws Exception {
        try {
            editor.getEditPart(gefEditPartName);
        } catch (WidgetNotFoundException e) {
            return false;
        } catch (Exception e) {
            throw e;
        }
        return true;
    }

}
