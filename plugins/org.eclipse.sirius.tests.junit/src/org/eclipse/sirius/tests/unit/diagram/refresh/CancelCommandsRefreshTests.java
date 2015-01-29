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

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.tests.unit.diagram.modelers.uml.UML2ModelerConstants;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Tests to assure correct diagrams refresh when commands are cancelled.
 * 
 * @author mchauvin
 */
public class CancelCommandsRefreshTests extends GenericTestCase implements UML2ModelerConstants {

    private IEditorPart part;

    private DDiagram originalDiagram;

    private Diagram originalGMFDiagram;

    private ResourceSetListener listener;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp("/" + SiriusTestsPlugin.PLUGIN_ID + SEMANTIC_MODEL_PATH, "/" + SiriusTestsPlugin.PLUGIN_ID + MODELER_PATH);
        initViewpoint("UML Analysis workspace");

        launchRefreshCommand();

        DiagramDescription desc = findDiagramDescription("Class Diagram");
        originalDiagram = getFirstDiagram(desc, session);
        assertEquals(INITIAL_DIAGRAM_ELEMENT_NUMBER, getNumberOfDiagramElements(originalDiagram));
        assertEquals(INITIAL_DNODE_NUMBER, getNumberOfNodes(originalDiagram));

        part = DialectUIManager.INSTANCE.openEditor(session, originalDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        originalGMFDiagram = getGMFDiagram(originalDiagram, session);
        assertEquals(INITIAL_NODE_NUMBER, numberOfNodes(originalGMFDiagram));
        createAndAddListenerToRollback();
    }

    private void createAndAddListenerToRollback() {
        listener = new ResourceSetListenerImpl() {

            @Override
            public boolean isAggregatePrecommitListener() {
                return true;
            }

            @Override
            public boolean isPrecommitOnly() {
                return true;
            }

            @Override
            public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
                throw new RollbackException(Status.CANCEL_STATUS);
            }
        };
        session.getTransactionalEditingDomain().addResourceSetListener(listener);
    }

    public void testSameNumberOfDiagramElementsAfterRollBackOnDeletion() throws Exception {
        launchDeletionOnFirstDiagramElement();
        assertEquals(INITIAL_DIAGRAM_ELEMENT_NUMBER, getNumberOfDiagramElements(originalDiagram));
    }

    public void testSameNumberOfGMFViewsAfterRollBackOnDeletion() throws Exception {
        launchDeletionOnFirstDiagramElement();
        assertEquals(INITIAL_NODE_NUMBER, numberOfNodes(originalGMFDiagram));
    }

    public void testNoDeletionOfSemanticElements() throws Exception {
        final EObject semanticRoot = EcoreUtil.getRootContainer(((DSemanticDiagram) originalDiagram).getTarget());
        final int numberOfSemanticElementsBeforeRollback = 1 + numberOfChildren(semanticRoot);
        launchDeletionOnFirstDiagramElement();
        final int numberOfSemanticElementsAfterRollback = 1 + numberOfChildren(semanticRoot);
        assertEquals(numberOfSemanticElementsBeforeRollback, numberOfSemanticElementsAfterRollback);
    }

    public void testDeletionWithoutRollBack() throws Exception {
        session.getTransactionalEditingDomain().removeResourceSetListener(listener);
        final EObject semanticRoot = EcoreUtil.getRootContainer(((DSemanticDiagram) originalDiagram).getTarget());
        final int numberOfSemanticElementsBeforeDeletion = 1 + numberOfChildren(semanticRoot);
        launchDeletionOnFirstDiagramElement();
        final int numberOfSemanticElementsAfterDeletion = 1 + numberOfChildren(semanticRoot);
        /* -1 for the deletion */
        assertEquals(INITIAL_DIAGRAM_ELEMENT_NUMBER - 1, getNumberOfDiagramElements(originalDiagram));
        /* -3 for the semantic deletion */
        assertEquals(numberOfSemanticElementsBeforeDeletion - 3, numberOfSemanticElementsAfterDeletion);

    }

    public void testDeletionAfterRollbackOnFirstDeletion() throws Exception {
        final EObject semanticRoot = EcoreUtil.getRootContainer(((DSemanticDiagram) originalDiagram).getTarget());
        launchDeletionOnFirstDiagramElement();
        assertEquals(INITIAL_DIAGRAM_ELEMENT_NUMBER, getNumberOfDiagramElements(originalDiagram));
        session.getTransactionalEditingDomain().removeResourceSetListener(listener);
        final int numberOfSemanticElementsBeforeDeletion = 1 + numberOfChildren(semanticRoot);
        launchDeletionOnFirstDiagramElement();
        /* -1 for the deletion */
        assertEquals(INITIAL_DIAGRAM_ELEMENT_NUMBER - 1, getNumberOfDiagramElements(originalDiagram));
        /* -3 for the semantic deletion */
        final int numberOfSemanticElementsAfterDeletion = 1 + numberOfChildren(semanticRoot);
        assertEquals(numberOfSemanticElementsBeforeDeletion - 3, numberOfSemanticElementsAfterDeletion);
    }

    private void launchDeletionOnFirstDiagramElement() throws Exception {
        DDiagramElement element = originalDiagram.getOwnedDiagramElements().get(0);
        final Request deleteRequest = new GroupRequest(org.eclipse.gef.RequestConstants.REQ_DELETE);
        myPerformRequest(getEditPart(element), deleteRequest);
    }

    private void myPerformRequest(final IGraphicalEditPart targetEditPart, final Request request) {
        org.eclipse.gef.commands.Command command = targetEditPart.getCommand(request);
        if (command != null) {
            getDiagramEditDomain().getDiagramCommandStack().execute(command);
            return;
        }
    }

    private IDiagramEditDomain getDiagramEditDomain() {
        return ((DiagramEditor) part).getDiagramEditDomain();
    }

    private int numberOfNodes(final View view) {
        return numberOfNodesWithPredicateTrue(view, null);
    }

    private int numberOfNodesWithPredicateTrue(final View view, final Predicate<View> predicate) {
        int sum = 0;
        for (final View childView : (List<View>) view.getChildren()) {
            if (predicate == null || predicate.apply(childView))
                sum += 1;
            sum += numberOfNodesWithPredicateTrue(childView, predicate);
        }
        return sum;
    }

    private int numberOfChildren(final EObject root) {
        int sum = 0;
        for (Iterator<EObject> i = root.eAllContents(); i.hasNext(); i.next()) {
            sum++;
        }
        return sum;
    }

    private static interface Predicate<T> {
        boolean apply(T input);
    }

    @Override
    protected void tearDown() throws Exception {
        session.getTransactionalEditingDomain().removeResourceSetListener(listener);
        DialectUIManager.INSTANCE.closeEditor(part, false);
        TestsUtil.synchronizationWithUIThread();

        super.tearDown();
    }

}
