/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.modeler.uml;

import java.util.concurrent.Callable;
import java.util.function.Predicate;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.junit.Ignore;

/**
 * Tests that when "snapTogrid" is active, node corners are on the grid.
 * <p>
 * Top-left corner for location is OK (not tested) because EditPolicy will only provide position on the grid. Thus if
 * bottom-right corner is on the grid, all corners are on the grid. So we expect the element size to be a multiple of
 * the grid step.
 * </p>
 * 
 * @author nperansin
 */
public class GridOnLocationTest extends SiriusDiagramTestCase {

    private static final int SPACING = 50;

    private static final String RESOURCE_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/grid/";

    private static final String SEMANTIC_MODEL_PATH = RESOURCE_PATH + "noderefresh.uml";

    private static final String MODELER_PATH = RESOURCE_PATH + "noderefresh.odesign";

    private static final String VIEWPOINT_NAME = "UML2";

    private static final String DIAGRAM_DESC_NAME = "Node Class and Package Diagram with Ports";

    private static final Predicate<Size> WIDTH_ON_GRID = size -> onGrid(size.getWidth());

    private static final Predicate<Size> HEIGHT_ON_GRID = size -> onGrid(size.getHeight());

    private static final Predicate<Size> SIZE_ON_GRID = WIDTH_ON_GRID.and(HEIGHT_ON_GRID);

    private DDiagram diagram;

    private IEditorPart editorPart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        changeDiagramUIPreference(IPreferenceConstants.PREF_SNAP_TO_GRID, true);
        changeDiagramUIPreference(IPreferenceConstants.PREF_RULER_UNITS, RulerProvider.UNIT_PIXELS);
        changeDiagramUIPreference(IPreferenceConstants.PREF_GRID_SPACING, SPACING);
    }
    
    /**
     * Tests elements size matches grid spacing when creating a diagram, ie the bottom right corner is on a grid point.
     */
    @Ignore
    public void testNodeSizeOnGridAtCreation() throws Exception {

        // Get the semantic target of the diagram
        Package diagramTarget = (Package) ((Model) semanticModel).getPackagedElement("SubPackage");

        ensureDiagram(diagramTarget, DIAGRAM_DESC_NAME);

        // Check the size of resizable nodes
        Class subClass1 = (Class) diagramTarget.getPackagedElement("SubClass1");
        assertTrue(!subClass1.isAbstract()); // not abstract is resizable

        assertOnSize("GMF node Size should be on grid", subClass1, SIZE_ON_GRID);

        // Port size on horizontal side (Property)
        assertOnSize("GMF port has unexpected width", subClass1.getOwnedAttribute("a", null), WIDTH_ON_GRID);
        assertOnSize("GMF port has unexpected width", subClass1.getOwnedAttribute("b", null), WIDTH_ON_GRID);

        // Port size on vertical side (Association)
        assertOnSize("GMF port has unexpected height", subClass1.getNestedClassifier("c"), HEIGHT_ON_GRID);

        // Check the size of fixed-size nodes
        Class subClass2 = (Class) diagramTarget.getPackagedElement("SubClass2");
        assertTrue(subClass2.isAbstract()); // abstract is not resizable
        assertOnSize("GMF node size should be fix", subClass2, size -> size.getWidth() == 180 && size.getHeight() == 180);

        // Check size on container (always resizable)
        Package inPack = (Package) diagramTarget.getPackagedElement("Sub1");
        // FIXME
        // Container is not on grid as Diagram creation performs an "Arrange All".
        // "Arrange All" simply removes explicit dimension but do not arrange size on grid.
        assertOnSize("GMF node has unexpected size", inPack,
                // should be : SIZE_ON_GRID;
                size -> true);
    }

    /**
     * Tests elements size matches grid spacing when refreshing a diagram, ie the bottom right corner is on a grid point.
     */
    public void testNodeSizeOnGridOnRefresh() throws Exception {

        // Get the semantic target of the diagram
        Package diagramTarget = (Package) ((Model) semanticModel).getPackagedElement("EmptyPackage");

        // Check the size of a resizable node
        ensureDiagram(diagramTarget, DIAGRAM_DESC_NAME);
        Class subClass1 = executeInSession(() -> {
            // not abstract is resizable
            Class creation = diagramTarget.createOwnedClass("SubClass1", false);
            creation.createOwnedAttribute("a", null);

            return creation;
        });

        assertOnSize("GMF node Size should be on grid", subClass1, SIZE_ON_GRID);
        assertOnSize("GMF port has unexpected width", subClass1.getOwnedAttribute("a", null), WIDTH_ON_GRID);

        // Check size on container
        Package inPack = executeInSession(() -> diagramTarget.createNestedPackage("Sub1"));
        assertOnSize("GMF node has unexpected size", inPack, SIZE_ON_GRID);
    }

    private <V> V executeInSession(Callable<V> task) {
        UpdateCommand<V> command = new UpdateCommand<V>(task);
        executeCommand(command);
        TestsUtil.synchronizationWithUIThread();

        if (command.failure instanceof RuntimeException re) {
            throw re;
        } else if (command.failure instanceof Error re) {
            throw re;
        } else if (command.failure != null) {
            throw new RuntimeException(command.failure);
        } else if (command.transaction.getStatus().getSeverity() > IStatus.WARNING) {
            throw new RuntimeException(command.transaction.getStatus().getException());
        }
        return command.value;
    }

    private class UpdateCommand<V> extends RecordingCommand {

        final Callable<V> task;

        V value;

        Throwable failure;

        InternalTransaction transaction;

        protected UpdateCommand(Callable<V> task) {
            super(session.getTransactionalEditingDomain());
            this.task = task;
        }

        @Override
        protected void doExecute() {
            transaction = ((InternalTransactionalEditingDomain) session.getTransactionalEditingDomain()).getActiveTransaction();
            try {
                value = task.call();
                // Diagram refresh is not automatic
                DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
            } catch (Throwable fail) {
                failure = fail;
            }
        }
    }

    private void ensureDiagram(EObject diagramTarget, String diagramDescrName) throws Exception {
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());

        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(diagramDescrName, diagramTarget);

        // Open the editor (and refresh it)
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        if (editorPart != null) {
            DialectUIManager.INSTANCE.closeEditor(editorPart, false);
            TestsUtil.synchronizationWithUIThread();
        }
        super.tearDown();
    }

    private static boolean onGrid(int value) {
        return value % SPACING == 0;
    }

    private void assertOnSize(String message, Element target, Predicate<Size> expectation) {
        DDiagramElement siriusElement = getFirstDiagramElement(diagram, target);
        Node node = getGmfNode(siriusElement);
        assertTrue(message, expectation.test((Size) node.getLayoutConstraint()));
    }

}
