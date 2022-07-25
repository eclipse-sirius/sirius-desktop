/*******************************************************************************
 * Copyright (c) 2015, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.FilterListener;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.FilterListenerScope;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.GMFDiagramUpdater;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Multimap;

/**
 * Tests concerning filters of diagram.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DiagramListenersTests extends SiriusDiagramTestCase {

    /**
     * A specific {@link FilterListener} to count the notifications calls. It will be added instead of the original
     * FilterListener.
     * 
     * @author Laurent Redor
     */
    public class SpecificFilterListener extends FilterListener {
        private int nbCalls = 0;

        private int nbFilteredElementsUpdateCommand = 0;

        private int nbFiltersSortingCommand = 0;

        /**
         * Default constructor.
         * 
         * @param dDiagram
         *            the {@link DDiagram} to update
         * @param domain
         *            the contextual {@link TransactionalEditingDomain}
         */
        public SpecificFilterListener(DDiagram dDiagram, TransactionalEditingDomain domain) {
            super(dDiagram, domain);
        }

        @Override
        public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
            nbCalls++;
            Option<Command> result = super.localChangesAboutToCommit(notifications);
            if (result.some()) {
                Command command = result.get();
                if (command instanceof CompoundCommand) {
                    Iterator<Command> iter = ((CompoundCommand) command).getCommandList().iterator();
                    for (Iterator<Command> iterator = ((CompoundCommand) command).getCommandList().iterator(); iterator.hasNext();) {
                        countFilterCommands(iterator.next().getClass().getSimpleName());
                    }
                } else {
                    countFilterCommands(command.getClass().getSimpleName());
                }
            }
            return result;
        }

        private void countFilterCommands(String currentCommandClassName) {
            if ("FilteredElementsUpdateCommand".equals(currentCommandClassName)) { //$NON-NLS-1$
                nbFilteredElementsUpdateCommand++;
            } else if ("FiltersSortingCommand".equals(currentCommandClassName)) { //$NON-NLS-1$
                nbFiltersSortingCommand++;
            }
        }

        public int getNbCalls() {
            return nbCalls;
        }

        public int getNbFilteredElementsUpdateCommand() {
            return nbFilteredElementsUpdateCommand;
        }

        public int getNbFiltersSortingCommand() {
            return nbFiltersSortingCommand;
        }
    }

    /**
     * A {@link SessionManagerListener} to add a specific {@link FilterListener} to count notifications calls to
     * {@link FilterListener}.
     * 
     * @author Laurent Redor
     */
    public class AddSpecificFilterListenerSessionManagerListener implements SessionManagerListener {

        private Map<DDiagram, SpecificFilterListener> specificFilterListeners = new HashMap<>();

        @Override
        public void notifyAddSession(Session newSession) {
            // Add a SpecificFilterListener for each diagram.
            for (DRepresentation representation : new DViewQuery(newSession.getOwnedViews().iterator().next()).getLoadedRepresentations()) {
                if (representation instanceof DDiagram) {
                    specificFilterListeners.put((DDiagram) representation, new SpecificFilterListener((DDiagram) representation, newSession.getTransactionalEditingDomain()));
                    newSession.getEventBroker().addLocalTrigger(SessionEventBrokerImpl.asFilter(new FilterListenerScope()), specificFilterListeners.get(representation));
                }
            }
        }

        @Override
        public void notifyRemoveSession(Session removedSession) {
            removeSpecificFiltenerListeners(removedSession);
        }

        @Override
        public void viewpointSelected(Viewpoint selectedSirius) {
        }

        @Override
        public void viewpointDeselected(Viewpoint deselectedSirius) {
        }

        @Override
        public void notify(Session updated, int notification) {
        }

        public SpecificFilterListener getSpecificFilterListener(DDiagram dDiagram) {
            return specificFilterListeners.get(dDiagram);
        }

        public void removeSpecificFiltenerListeners(Session session) {
         // Remove the added SpecificFilterListener for each diagram.
            for (Iterator<Map.Entry<DDiagram, SpecificFilterListener>> it = specificFilterListeners.entrySet().iterator(); it.hasNext();) {
                Map.Entry<DDiagram, SpecificFilterListener> entry = it.next();
                session.getEventBroker().removeLocalTrigger(entry.getValue());
                it.remove();
            }
        }
    }

    String TEST_SEMANTIC_MODEL_FILENAME = "My.ecore"; //$NON-NLS-1$

    String TEST_MODELS_PROJECT_RELATIVE_PATH = "/data/unit/filter/mappingFilters/"; //$NON-NLS-1$

    String TEST_SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_MODELS_PROJECT_RELATIVE_PATH + TEST_SEMANTIC_MODEL_FILENAME; //$NON-NLS-1$

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_MODELS_PROJECT_RELATIVE_PATH + "My.odesign"; //$NON-NLS-1$ //$NON-NLS-2$

    String REPRESENTATIONS_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_MODELS_PROJECT_RELATIVE_PATH + "representations.aird"; //$NON-NLS-1$ //$NON-NLS-2$

    boolean errorCatchActiveOldState;

    AddSpecificFilterListenerSessionManagerListener specificSessionManagerListener;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        specificSessionManagerListener = new AddSpecificFilterListenerSessionManagerListener();
        SessionManager.INSTANCE.addSessionsListener(specificSessionManagerListener);
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_MODEL_PATH);
        TestsUtil.synchronizationWithUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    @Override
    protected void tearDown() throws Exception {
        // Store the specificSessionManagerListener, to allow a remove after cleanup, because it is cleaned by tearDown
        // (and it should be called after the tearDown that remove the session).
        AddSpecificFilterListenerSessionManagerListener localVariableForSessionManagerListener = specificSessionManagerListener;
        super.tearDown();
        SessionManager.INSTANCE.removeSessionsListener(localVariableForSessionManagerListener);
    }

    /**
     * Specific test to reveal a potential problem about the listeners on the diagram. This test is inspired from a
     * Capella use case and the command is similar to
     * {@link org.polarsys.capella.core.platform.sirius.ui.commands.DeleteRepresentationCommand} structure.
     * 
     * @throws Exception
     *             in case of problem
     */
    public void testDeleteElementsAndCheckErrorLog() throws Exception {
        // Remove specific FilterListeners (not needed for this test)
        specificSessionManagerListener.removeSpecificFiltenerListeners(session);
        final DDiagram diag1 = (DDiagram) getRepresentationsByName("newDiagram1").toArray()[0]; //$NON-NLS-1$
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diag1, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        errorCatchActiveOldState = isErrorCatchActive();
        setErrorCatchActive(true);
        try {
            final DRepresentationDescriptor diag1Descriptor = new DRepresentationQuery(diag1).getRepresentationDescriptor();
            final EObject semanticElement = diag1Descriptor.getTarget();

            // Execute a command that delete a semantic element after closing
            // corresponding editors and deleting corresponding representations.
            final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            domain.getCommandStack().execute(new AbstractCommand("Delete representation and close it") { //$NON-NLS-1$
                CompoundCommand realCommand;

                @Override
                protected boolean prepare() {
                    realCommand = new CompoundCommand();
                    realCommand.append(new RecordingCommand(domain, "Delete representation and close it", null) { //$NON-NLS-1$
                        @Override
                        protected void doExecute() {
                            closeActiveRepresentationEditor(diag1, session);
                            // Delete the current representation.
                            if (DialectManager.INSTANCE.deleteRepresentation(diag1Descriptor, session)) {
                                // Notify changes.
                                SessionManager.INSTANCE.notifyRepresentationDeleted(session);
                            }
                        }
                    });
                    Command removeEPackageCmd = RemoveCommand.create(domain, semanticElement.eContainer(), EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, semanticElement);
                    realCommand.append(removeEPackageCmd);
                    return true;
                }

                @Override
                public void redo() {
                }

                @Override
                public void execute() {
                    realCommand.execute();
                }
            });
            TestsUtil.synchronizationWithUIThread();

            // Check if there is problem about "not found interpreter" in error
            // log
            if (doesAnErrorOccurs()) {
                String errors = getErrorLoggersMessage();
                if (errors.contains("Impossible to find an interpreter")) { //$NON-NLS-1$
                    fail("This test logs error of kind \"Impossible to find an interpreter\": " + errors); //$NON-NLS-1$
                }
            }
        } finally {
            setErrorCatchActive(errorCatchActiveOldState);
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    /**
     * Check the number of notifications received by the {@link FilterListener} after moving an element that implies a
     * modification of the feature AbstractDNode_ArrangeConstraints (and indirectly of the feature
     * DRepresentationDescriptor_ChangeId). The number of notifications is checked for three opened diagrams.<BR/>
     * No diagram should be notified for this kind of change.
     */
    public void testNbNotificationsWhenMovingElement() {
        final DDiagram diag1 = (DDiagram) getRepresentationsByName("newDiagram1").toArray()[0]; //$NON-NLS-1$
        final DDiagram diag2 = (DDiagram) getRepresentationsByName("newDiagram2").toArray()[0]; //$NON-NLS-1$
        final DDiagram diag3 = (DDiagram) getRepresentationsByName("newDiagram3").toArray()[0]; //$NON-NLS-1$
        IEditorPart editor2 = DialectUIManager.INSTANCE.openEditor(session, diag2, new NullProgressMonitor());
        IEditorPart editor3 = DialectUIManager.INSTANCE.openEditor(session, diag3, new NullProgressMonitor());
        IEditorPart editor1 = DialectUIManager.INSTANCE.openEditor(session, diag1, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Remove original FilterListener added during the opening of the editor (through GMFDiagramUpdater), to avoid
        // to have side effect with original FilterListener.
        removeOriginalFilterListeners();

        try {
            // Move the node C1 in newDiagram1
            Optional<DDiagramElement> c1Dde = diag1.getDiagramElements().stream().filter(dde -> dde.getName().equals("C1")).findFirst(); //$NON-NLS-1$
            assertTrue("The diagram newDiagram1 should have an element named \"C1\".", c1Dde.isPresent()); //$NON-NLS-1$
            assertTrue("Element C1 should be unpinned.", !isPinned(c1Dde.get())); //$NON-NLS-1$
            moveEditPart(c1Dde.get(), new Point(180, 180));
            TestsUtil.synchronizationWithUIThread();

            assertTrue("Element C1 should be pinned (ie feature AbstractDNode_ArrangeConstraints changed).", isPinned(c1Dde.get())); //$NON-NLS-1$

            assertEquals("Wrong number of notifications of the FilterLister of newDiagram1 when moving a node in newDiagram1", 0, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag1).getNbCalls());
            assertEquals("Wrong number of notifications of the FilterLister of newDiagram2 when moving a node in newDiagram1", 0, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag2).getNbCalls());
            assertEquals("Wrong number of notifications of the FilterLister of newDiagram3 when moving a node in newDiagram1", 0, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag3).getNbCalls());
        } finally {
            DialectUIManager.INSTANCE.closeEditor(editor3, false);
            DialectUIManager.INSTANCE.closeEditor(editor2, false);
            DialectUIManager.INSTANCE.closeEditor(editor1, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    /**
     * Check the number of notifications received by the {@link FilterListener} when activating a filter that implies a
     * modification of the feature DDiagram_ActivatedFilters (and indirectly of the feature
     * DRepresentationDescriptor_ChangeId). The number of notifications is checked for three opened diagrams.<BR/>
     * Each diagram is notified twice: one for the change of the DDiagram_ActivatedFilters and one for the change of the
     * DDiagramElement_Visible of DNode C1.
     */
    public void testNbNotificationsWhenActivatingFilter() {
        final DDiagram diag1 = (DDiagram) getRepresentationsByName("newDiagram1").toArray()[0]; //$NON-NLS-1$
        final DDiagram diag2 = (DDiagram) getRepresentationsByName("newDiagram2").toArray()[0]; //$NON-NLS-1$
        final DDiagram diag3 = (DDiagram) getRepresentationsByName("newDiagram3").toArray()[0]; //$NON-NLS-1$
        IEditorPart editor2 = DialectUIManager.INSTANCE.openEditor(session, diag2, new NullProgressMonitor());
        IEditorPart editor3 = DialectUIManager.INSTANCE.openEditor(session, diag3, new NullProgressMonitor());
        IEditorPart editor1 = DialectUIManager.INSTANCE.openEditor(session, diag1, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        // Remove original FilterListener added during the opening of the editor (through GMFDiagramUpdater), to avoid
        // to have side effect with original FilterListener.
        removeOriginalFilterListeners();
        try {
            // Activate the filter "A" in newDiagram1
            activateFilter(diag1, "A"); //$NON-NLS-1$
            TestsUtil.synchronizationWithUIThread();

            assertEquals("Wrong number of notifications of the FilterLister of newDiagram1 when activating a filter in newDiagram1.", 2, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag1).getNbCalls());
            assertEquals("Wrong number of notifications of the FilterLister of newDiagram2 when activating a filter in newDiagram1.", 2, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag2).getNbCalls());
            assertEquals("Wrong number of notifications of the FilterLister of newDiagram3 when activating a filter in newDiagram1.", 2, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag3).getNbCalls());
            assertEquals("Wrong number of calls to FiltersSortingCommand for newDiagram1 when activating a filter in newDiagram1.", 1, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag1).getNbFiltersSortingCommand());
            assertEquals("Wrong number of calls to FiltersSortingCommand for newDiagram2 when activating a filter in newDiagram1.", 0, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag2).getNbFiltersSortingCommand());
            assertEquals("Wrong number of calls to FiltersSortingCommand for newDiagram3 when activating a filter in newDiagram1.", 0, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag3).getNbFiltersSortingCommand());
            assertEquals("Wrong number of calls to method CompositeFilterApplicationBuilder.computeCompositeFilterApplications() for newDiagram1 when activating a filter in newDiagram1.", 2, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag1).getNbFilteredElementsUpdateCommand());
            assertEquals("Wrong number of calls to method CompositeFilterApplicationBuilder.computeCompositeFilterApplications() for newDiagram2 when activating a filter in newDiagram1.", 2, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag2).getNbFilteredElementsUpdateCommand());
            assertEquals("Wrong number of calls to method CompositeFilterApplicationBuilder.computeCompositeFilterApplications() for newDiagram3 when activating a filter in newDiagram1.", 2, //$NON-NLS-1$
                    specificSessionManagerListener.getSpecificFilterListener(diag3).getNbFilteredElementsUpdateCommand());

        } finally {
            DialectUIManager.INSTANCE.closeEditor(editor3, false);
            DialectUIManager.INSTANCE.closeEditor(editor2, false);
            DialectUIManager.INSTANCE.closeEditor(editor1, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    /**
     * Remove {@link FilterListener}s added during the opening of the editors (through {@link GMFDiagramUpdater}).
     */
    private void removeOriginalFilterListeners() {
        List<ModelChangeTrigger> modelChangeTriggerToRemove = new ArrayList<>();
        Optional<Object> scopedTriggers = ReflectionHelper.getFieldValueWithoutException(session.getEventBroker(), "scopedTriggers"); //$NON-NLS-1$
        if (scopedTriggers.isPresent()) {
            @SuppressWarnings("unchecked")
            Multimap<NotificationFilter, ModelChangeTrigger> scopedTriggers2 = (Multimap<NotificationFilter, ModelChangeTrigger>) scopedTriggers.get();
            for (ModelChangeTrigger modelChangeTrigger : scopedTriggers2.values()) {
                if ("org.eclipse.sirius.diagram.ui.internal.refresh.listeners.FilterListener".equals(modelChangeTrigger.getClass().getName())) { //$NON-NLS-1$
                    modelChangeTriggerToRemove.add(modelChangeTrigger);
                }
            }
        }
        for (ModelChangeTrigger modelChangeTrigger : modelChangeTriggerToRemove) {
            session.getEventBroker().removeLocalTrigger(modelChangeTrigger);
        }
    }

    // Closes the opened editor about the current representation.
    private void closeActiveRepresentationEditor(DRepresentation representation_p, Session session_p) {
        IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session_p);
        if (null == editingSession) {
            return;
        }
        DialectEditor editor = editingSession.getEditor(representation_p);
        if (editor != null) {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            // We detach the editor here because sometimes cause of the
            // asyncExec of the closing, the detach could not be done during the
            // dispose of the editor
            editingSession.detachEditor(editor);
        }
    }

    /**
     * Move the edit part.
     * 
     * @param dde
     *            the DDiagramElement of the edit part to move.
     * @param point
     *            delta to move.
     * 
     */
    private void moveEditPart(DDiagramElement dde, Point point) {
        IGraphicalEditPart editPart = getEditPart(dde);
        ChangeBoundsRequest request = new ChangeBoundsRequest();
        request.setMoveDelta(point);
        request.setLocation(point);
        request.setType(RequestConstants.REQ_MOVE);
        // Instead of directly calling editPart.performRequest(request), like in other tests, we called getCommand and
        // then execute. Indeed, because of the hack
        // "org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation.isInteractiveMove()", the pin
        // status is not automatically changed when calling moveEditPart, so to simulate it in this test we change it
        // "manually" (simulate what is done in
        // org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation.handleAutoPinOnInteractiveMove(IDiagramElementEditPart,
        // Request, Command)).
        org.eclipse.gef.commands.Command result = editPart.getCommand(request);

        org.eclipse.gef.commands.CompoundCommand cc = new org.eclipse.gef.commands.CompoundCommand();
        cc.add(result);

        PinElementsCommand emfCommand = new PinElementsCommand(Collections.singleton(dde));
        org.eclipse.gef.commands.Command pinCmd = new ICommandProxy(new GMFCommandWrapper(editPart.getEditingDomain(), emfCommand));
        cc.add(pinCmd);

        result = cc.unwrap();
        editPart.getDiagramEditDomain().getDiagramCommandStack().execute(result);

        TestsUtil.synchronizationWithUIThread();
    }

    public static boolean isPinned(final DDiagramElement diagramElement) {
        final List<ArrangeConstraint> constraints = getArrangeConstraints(diagramElement);
        return constraints != null && hasAllPinnedConstraints(constraints);
    }

    private static List<ArrangeConstraint> getArrangeConstraints(final EObject diagramElement) {
        final List<ArrangeConstraint> constraints;
        if (diagramElement instanceof AbstractDNode) {
            final AbstractDNode node = (AbstractDNode) diagramElement;
            constraints = node.getArrangeConstraints();
        } else if (diagramElement instanceof DEdge) {
            final DEdge edge = (DEdge) diagramElement;
            constraints = edge.getArrangeConstraints();
        } else {
            constraints = Collections.emptyList();
        }
        return constraints;
    }

    private static boolean hasAllPinnedConstraints(final List<ArrangeConstraint> constraints) {
        return constraints.contains(ArrangeConstraint.KEEP_LOCATION) && constraints.contains(ArrangeConstraint.KEEP_SIZE) && constraints.contains(ArrangeConstraint.KEEP_RATIO);
    }
}
