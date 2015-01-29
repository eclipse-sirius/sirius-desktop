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
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.PaneBasedSelectionWizardCommand;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;

/**
 * PaneBasedSelectionWizard tests.
 * 
 * @author mporhel
 */
public class PaneBasedSelectionWizardTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/paneBasedSelectionWizard/paneBasedSelectionTest.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/paneBasedSelectionWizard/paneBasedSelection.odesign";

    private static final String VIEWPOINT_NAME = "paneBasedSelectionTests";

    private static final String REPRESENTATION_DESC_NAME = "PaneBasedSelectionTestDiagram";

    private static final String EMPTY_SEMANTIC_MODEL_ERROR = "The semantic model is empty before the tool application";

    private static final String PANE_SELECTION_TOOL_1 = "paneSelectionTest1";

    private static final String PANE_SELECTION_TOOL_2 = "paneSelectionTest2";

    private static final String PANE_SELECTION_TOOL_3 = "paneSelectionTest3";

    private static final String PANE_SELECTION_TOOL_4 = "paneSelectionTest4";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
    }

    /**
     * Test a {@link PaneBasedSelectionWizardDescription} tool. Test the initial
     * operation execution.
     */
    public void testDefaultLayerPaneBasedSelection() {
        final EPackage ePackage = (EPackage) semanticModel;

        assertTrue(EMPTY_SEMANTIC_MODEL_ERROR, !ePackage.getEClassifiers().isEmpty());
        final EPackage testPackage = ePackage.getESubpackages().get(0);

        // check the precondition
        assertTrue(PaneBasedSelectionWizardCommand.canCreateCommand(getTool(diagram, PANE_SELECTION_TOOL_1), diagram, new TreeItemWrapper(null, null)));

        final String previousName = testPackage.getName();
        assertTrue(applySelectionTool(PANE_SELECTION_TOOL_1, diagram, diagram));
        final String currentName = testPackage.getName();

        assertNotSame("The name should change after tool execution", previousName, currentName);
        assertEquals("The name of the current package should be InitialOperationApplied1", "InitialOperationApplied1", currentName);
    }

    /**
     * Test a {@link PaneBasedSelectionWizardDescription} tool. Test the initial
     * operation execution on a edge.<BR>
     * Ticket #1617
     */
    public void testDefaultLayerPaneBasedSelectionOnEdge() {
        final EPackage ePackage = (EPackage) semanticModel;

        assertTrue(EMPTY_SEMANTIC_MODEL_ERROR, !ePackage.getEClassifiers().isEmpty());

        refresh(diagram);
        assertEquals("The diagram do not contain the right number of nodes", 7, diagram.getOwnedDiagramElements().size());
        // Get the last edge in this diagram (there is only one edge in this
        // diagram)
        DEdge edge = null;
        final EList<DDiagramElement> diagramElements = diagram.getOwnedDiagramElements();
        for (DDiagramElement diagramElement : diagramElements) {
            if (diagramElement instanceof DEdge) {
                edge = (DEdge) diagramElement;
            }
        }
        assertNotNull("This diagram should contain at least one edge.", edge);

        // check the precondition
        assertTrue(PaneBasedSelectionWizardCommand.canCreateCommand(getTool(diagram, PANE_SELECTION_TOOL_4), edge, new TreeItemWrapper(null, null)));

        final EPackage testPackage = (EPackage) edge.getTarget().eContainer();
        final String previousName = testPackage.getName();
        assertTrue(applySelectionTool(PANE_SELECTION_TOOL_4, diagram, edge));
        final String currentName = testPackage.getName();

        assertNotSame("The name should change after tool execution", previousName, currentName);
        assertEquals("The name of the current package should be InitialOperationApplied1", "InitialOperationApplied1", currentName);
    }

    /**
     * Test another {@link PaneBasedSelectionWizardDescription} tool. Test the
     * precondition evaluation.
     */
    public void testDefaultLayerUnapplicablePaneBasedSelection() {
        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue(EMPTY_SEMANTIC_MODEL_ERROR, !ePackage.getEClassifiers().isEmpty());

        // check the precondition
        assertFalse(PaneBasedSelectionWizardCommand.canCreateCommand((PaneBasedSelectionWizardDescription) getTool(diagram, PANE_SELECTION_TOOL_2), diagram, new TreeItemWrapper(null, null)));
    }

    /**
     * {@inheritDoc}
     */
    protected Command getCommand(final EObject container, final AbstractToolDescription tool, final Collection<EObject> selectedElements) {
        Command cmd = null;

        if (tool instanceof PaneBasedSelectionWizardDescription && container instanceof DSemanticDecorator) {
            cmd = getCommandFactory().buildPaneBasedSelectionWizardCommandFromTool((PaneBasedSelectionWizardDescription) tool, (DSemanticDecorator) container, selectedElements);
        } else {
            cmd = super.getCommand(container, tool, selectedElements);
        }
        return cmd;
    }

    /**
     * {@inheritDoc}
     */
    protected Collection<EObject> getSelectedEObject(final AbstractToolDescription tool, final DDiagram dDiagram, final EObject containerView) {
        if (tool instanceof PaneBasedSelectionWizardDescription && dDiagram instanceof DSemanticDecorator) {
            Collection<EObject> preSelection = Collections.<EObject> emptyList();
            try {
                final Method computePreSelection = PaneBasedSelectionWizardCommand.class.getDeclaredMethod("computePreSelection", PaneBasedSelectionWizardDescription.class, DSemanticDecorator.class);
                computePreSelection.setAccessible(true);
                if (containerView instanceof DSemanticDecorator)
                    preSelection = (Collection<EObject>) computePreSelection.invoke(null, tool, containerView);
            } catch (final SecurityException e) {
                fail("SecurityException in reflective call: " + e.getMessage());
            } catch (final NoSuchMethodException e) {
                fail("NoSuchMethodException in reflective call: " + e.getMessage());
            } catch (final IllegalArgumentException e) {
                fail("IllegalArgumentException in reflective call: " + e.getMessage());
            } catch (final IllegalAccessException e) {
                fail("IllegalAccessException in reflective call: " + e.getMessage());
            } catch (final InvocationTargetException e) {
                fail("InvocationTargetException in reflective call: " + e.getMessage());
            }
            return preSelection;
        }
        return super.getSelectedEObject(tool, diagram, containerView);
    }

    /**
     * Test left pane candidates (tree) of a
     * {@link PaneBasedSelectionWizardDescription} tool. Test the left pane tree
     * computation.
     */
    public void testDefaultLayerTreeSelectionPaneBasedSelection() {
        final EPackage ePackage = (EPackage) semanticModel;

        assertTrue(EMPTY_SEMANTIC_MODEL_ERROR, !ePackage.getEClassifiers().isEmpty());

        final PaneBasedSelectionWizardDescription tool = (PaneBasedSelectionWizardDescription) getTool(diagram, PANE_SELECTION_TOOL_3);
        assertNotNull("The tool should not be null", tool);

        assertTrue("The left pane candidates should be displayed as a tree", tool.isTree());

        final TreeItemWrapper candidates = getLeftPaneCandidates(tool, diagram);
        assertNotNull("Candidates cannot be null", candidates);

        final Collection<TreeItemWrapper> children = candidates.getChildren();
        assertEquals("The tree should have 2 roots", 2, children.size());

        for (TreeItemWrapper treeItemWrapper : children) {
            assertTrue("Candidates should be an EPackage", treeItemWrapper.getWrappedObject() instanceof EPackage);

            final EPackage child = (EPackage) treeItemWrapper.getWrappedObject();
            if (!"p0".equals(child.getName())) {
                assertEquals("It should not be a tree", 0, treeItemWrapper.getChildren().size());
            } else {
                assertEquals("p0 should have a child", 1, treeItemWrapper.getChildren().size());
                final TreeItemWrapper p1 = treeItemWrapper.getChildren().iterator().next();
                assertEquals("p1 should have a child", 2, p1.getChildren().size());
                for (TreeItemWrapper treeItemWrapper2 : p1.getChildren()) {
                    assertEquals("This should be a leaf", 0, treeItemWrapper2.getChildren().size());
                }
            }
        }
    }

    /**
     * Test left pane candidates (list) of a
     * {@link PaneBasedSelectionWizardDescription} tool. Test the left pane list
     * computation.
     */
    public void testDefaultLayerListSelectionPaneBasedSelection() {
        final EPackage ePackage = (EPackage) semanticModel;

        assertTrue(EMPTY_SEMANTIC_MODEL_ERROR, !ePackage.getEClassifiers().isEmpty());

        final PaneBasedSelectionWizardDescription tool = (PaneBasedSelectionWizardDescription) getTool(diagram, PANE_SELECTION_TOOL_1);
        assertNotNull("The tool should not be null", tool);

        assertFalse("The left pane candidates should be displayed as a list", tool.isTree());

        final TreeItemWrapper candidates = getLeftPaneCandidates(tool, diagram);
        assertNotNull("Candidates cannot be null", candidates);

        final Collection<TreeItemWrapper> children = candidates.getChildren();
        assertEquals("We should have 5 candidates", 5, children.size());

        for (TreeItemWrapper treeItemWrapper : children) {
            assertEquals("It should not be a tree", 0, treeItemWrapper.getChildren().size());
            assertTrue("Candidates should be an EPackage", treeItemWrapper.getWrappedObject() instanceof EPackage);
        }
    }

    /**
     * Test a {@link PaneBasedSelectionWizardDescription} tool. Test the right
     * pane pre-selected element evaluation.
     */
    public void testDefaultLayerPreSelectionPaneBasedSelection() {
        final EPackage ePackage = (EPackage) semanticModel;

        assertTrue(EMPTY_SEMANTIC_MODEL_ERROR, !ePackage.getEClassifiers().isEmpty());
        final EPackage testPackage = ePackage.getESubpackages().get(0);

        final PaneBasedSelectionWizardDescription tool = (PaneBasedSelectionWizardDescription) getTool(diagram, PANE_SELECTION_TOOL_1);
        // check the precondition
        assertTrue(PaneBasedSelectionWizardCommand.canCreateCommand(tool, diagram, new TreeItemWrapper(null, null)));

        final Collection<EObject> preSelectedElements = getSelectedEObject(tool, diagram);
        assertEquals("We should have 1 pre-selected element", 1, preSelectedElements.size());
        final EObject preSelectedPackage = preSelectedElements.iterator().next();
        assertEquals("The pre-selected element is not the wanted package", testPackage, preSelectedPackage);

    }

    private TreeItemWrapper getLeftPaneCandidates(final AbstractToolDescription tool, final DDiagram dDiagram) {
        final TreeItemWrapper candidates = new TreeItemWrapper(null, null);
        if (tool instanceof PaneBasedSelectionWizardDescription && dDiagram instanceof DSemanticDecorator) {
            try {
                final Method computeInput = PaneBasedSelectionWizardCommand.class.getDeclaredMethod("computeInput", PaneBasedSelectionWizardDescription.class, EObject.class, TreeItemWrapper.class);
                computeInput.setAccessible(true);

                computeInput.invoke(null, tool, dDiagram, candidates);
            } catch (final SecurityException e) {
                fail("SecurityException in reflective call: " + e.getMessage());
            } catch (final NoSuchMethodException e) {
                fail("NoSuchMethodException in reflective call: " + e.getMessage());
            } catch (final IllegalArgumentException e) {
                fail("IllegalArgumentException in reflective call: " + e.getMessage());
            } catch (final IllegalAccessException e) {
                fail("IllegalAccessException in reflective call: " + e.getMessage());
            } catch (final InvocationTargetException e) {
                fail("InvocationTargetException in reflective call: " + e.getMessage());
            }
        }
        return candidates;
    }

    /**
     * {@inheritDoc}
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
