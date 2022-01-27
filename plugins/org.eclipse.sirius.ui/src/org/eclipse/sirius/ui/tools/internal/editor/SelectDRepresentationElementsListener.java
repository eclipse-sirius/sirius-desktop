/*******************************************************************************
 * Copyright (c) 2014, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;

/**
 * A post commit listener which selects the representation elements specified through the "Elements To Select"
 * expression and "Inverse Selection Order" tag of the tool. </br>
 * Elements will be selected only on the active editor.
 * 
 * Each dialect is responsible to add this post commit listener or one specializing this one.
 * 
 * @author mporhel
 */
public class SelectDRepresentationElementsListener extends ResourceSetListenerImpl {

    /**
     * This {@link NotificationFilter} corresponds to {@link DRepresentationElement} creation or specified "Elements To
     * Select" list.
     */
    private static final NotificationFilter DEFAULT_NOTIFICATION_FILTER = NotificationFilter.createFeatureFilter(ViewpointPackage.Literals.UI_STATE__ELEMENTS_TO_SELECT)
            .or(NotificationFilter.NOT_TOUCH.and(SessionEventBrokerImpl.asFilter(DanglingRefRemovalTrigger.IS_ATTACHMENT)).and(NotificationFilter
                    .createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION).or(NotificationFilter.createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION_ELEMENT))));

    /**
     * The dialect editor.
     */
    protected DialectEditor dialectEditor;

    private boolean selectOnlyViewWithNewSemanticTarget;

    /**
     * Default constructor.
     *
     * @param editor
     *            the editor on which the representation elements should be selected, if the editor is active.
     * @param selectOnlyViewWithNewSemanticTarget
     *            true to select only created view whose semantic target has also been created otherwise select also
     *            created view whose semantic target was already existing
     */
    public SelectDRepresentationElementsListener(DialectEditor editor, boolean selectOnlyViewWithNewSemanticTarget) {
        this(editor, selectOnlyViewWithNewSemanticTarget, DEFAULT_NOTIFICATION_FILTER);
    }

    /**
     * Constructor with a specific filter.
     *
     * @param editor
     *            the editor on which the representation elements should be selected, if the editor is active.
     * @param selectOnlyViewWithNewSemanticTarget
     *            true to select only created view whose semantic target has also been created otherwise select also
     *            created view whose semantic target was already existing
     * @param filter
     *            a filter to override default filter ({@link #DEFAULT_NOTIFICATION_FILTER}, or <code>null</code> to
     *            request the default
     */
    public SelectDRepresentationElementsListener(DialectEditor editor, boolean selectOnlyViewWithNewSemanticTarget, NotificationFilter filter) {
        super(filter != null ? filter : DEFAULT_NOTIFICATION_FILTER);
        dialectEditor = Objects.requireNonNull(editor);
        this.selectOnlyViewWithNewSemanticTarget = selectOnlyViewWithNewSemanticTarget;
        DRepresentation representation = editor.getRepresentation();
        if (representation != null) {
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(representation);
            if (domain != null) {
                domain.addResourceSetListener(this);
            }
        }
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    @Override
    public void resourceSetChanged(final ResourceSetChangeEvent event) {
        // Do not react to undo/redo. The option might not be present: react if
        // value is Boolean.FALSE or null ie is not Boolean.TRUE
        if (!Boolean.TRUE.equals(event.getTransaction().getOptions().get(Transaction.OPTION_IS_UNDO_REDO_TRANSACTION))) {
            DRepresentation currentRep = dialectEditor.getRepresentation();

            if (currentRep == null) {
                // There is no more representation managed by this editor.
                // If the representation is null it means that its has been deleted from the Resource
                // In such case this method can't compute the list of element to select
                return;
            }

            Display display = Display.getCurrent();

            // We are the UI Thread, so we perform the selection.
            if (display != null && Thread.currentThread() == display.getThread()) {
                IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
                if (dialectEditor.equals(activeEditor)) {
                    List<DRepresentationElement> elementsToSelect = extractElementsToSelect(event, currentRep);
                    if (elementsToSelect != null) {
                        // Set the selection in async exec: for some dialect, ui
                        // could be refresh by another post commit triggered after
                        // this one and doing some UI refresh in sync exec.
                        EclipseUIUtil.displayAsyncExec(new SetSelectionRunnable(dialectEditor, elementsToSelect));
                    }
                }
            }
            // We are out of the UI Thread.
            else {
                // We retrieve the elementToSelect before since we cannot differ this call in a separate thread.
                List<DRepresentationElement> elementsToSelect = extractElementsToSelect(event, currentRep);
                if (elementsToSelect != null) {
                    // The current EMF transaction (we are in a post commit listener here) is not released until all
                    // post commits are executed. If we are out of the UI Thread, retrieving the current active editor
                    // implies to wait for the UI Thread. To avoid a dead lock by blocking the current transaction while
                    // waiting for the UI Thread (this one could also be waiting for the transaction to be released) we
                    // execute this code in a separate thread.
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.submit(() -> {
                        // getActiveEditor will perform a synExec.
                        IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
                        if (dialectEditor.equals(activeEditor)) {
                            // Set the selection in async exec: for some dialect, ui
                            // could be refresh by another post commit triggered after
                            // this one and doing some UI refresh in sync exec.
                            EclipseUIUtil.displayAsyncExec(new SetSelectionRunnable(dialectEditor, elementsToSelect));
                        }
                    });
                }
            }
        }
    }

    /**
     * Extracts elements to select from notifications.
     * 
     * @param event
     *            the event
     * @param currentRep
     *            the representation
     * @return the list to select. If null selection must not be changed
     */
    protected List<DRepresentationElement> extractElementsToSelect(ResourceSetChangeEvent event, DRepresentation currentRep) {
        List<DRepresentationElement> elementsToSelect = null;
        final List<DRepresentationElement> notifiedElements = new ArrayList<>();
        boolean elementsToSelectUpdated = analyseNotifications(event, currentRep, notifiedElements);
        if (elementsToSelectUpdated) {
            List<DRepresentationElement> elementsToSelectFromUiState = extractSelectionFromUIState(currentRep, notifiedElements);
            if (elementsToSelectFromUiState != null) {
                elementsToSelect = elementsToSelectFromUiState;
            } else { // keep default selection and reverse it if necessary
                if (notifiedElements.size() > 0) {
                    // Select created elements
                    elementsToSelect = notifiedElements;
                } else {
                    // do not change the selection if there is no created
                    // elements
                    elementsToSelect = null;
                }
            }
        } else {
            // Keep default behavior if change has been done out of a tool
            if (notifiedElements.isEmpty()) {
                elementsToSelect = null;
            } else {
                elementsToSelect = notifiedElements;
            }
        }

        // reverse selection if required
        if (elementsToSelect != null && !elementsToSelect.isEmpty()) {
            UIState uiState = currentRep.getUiState();
            if (uiState != null) {
                if (uiState.isInverseSelectionOrder()) {
                    Collections.reverse(elementsToSelect);
                }
            }
        }
        return elementsToSelect;
    }

    /**
     * Extracts elements to select from UIState.
     * 
     * @param currentRep
     *            the representation
     * @param notifiedElements
     *            notified elements
     * @return null if this method can't give a pertinent result
     */
    private List<DRepresentationElement> extractSelectionFromUIState(DRepresentation currentRep, List<DRepresentationElement> notifiedElements) {
        List<DRepresentationElement> selectedElements = null;
        List<DRepresentationElement> dRepElements = new ArrayList<>();
        List<EObject> semanticElements = new ArrayList<>();

        UIState uiState = currentRep.getUiState();
        if (uiState != null && uiState.isSetElementsToSelect()) {
            Collection<EObject> elementsToSelectFromUIState = uiState.getElementsToSelect();
            boolean hasRepElement = false;
            boolean hasSemanticElement = false;
            boolean hasRepresentation = false;
            // elementsToSelect must contains either only DRepresentationElement
            // either only semantic elements
            for (EObject currentElement : elementsToSelectFromUIState) {
                if (currentElement instanceof DRepresentationElement) {
                    hasRepElement = true;
                    dRepElements.add((DRepresentationElement) currentElement);
                } else if (currentElement instanceof DRepresentation) {
                    hasRepresentation = true;
                    break;
                } else {
                    hasSemanticElement = true;
                    semanticElements.add(currentElement);
                }
            }

            if (hasSemanticElement && !hasRepresentation && !hasRepElement) {
                selectedElements = new ArrayList<>();
                caseHasSemanticElement(selectedElements, notifiedElements, semanticElements, currentRep);
            } else if (hasRepElement && !hasRepresentation && !hasSemanticElement) {
                // Selected elements are filtered with the notified only
                // if there is at least a notified element
                if (!notifiedElements.isEmpty()) {
                    dRepElements.retainAll(notifiedElements);
                }
                selectedElements = new ArrayList<DRepresentationElement>(dRepElements);
            } else {
                selectedElements = new ArrayList<>();
            }
        }
        return selectedElements;
    }

    private void caseHasSemanticElement(List<DRepresentationElement> selectedElements, List<DRepresentationElement> notifiedElements, List<EObject> semanticElements, DRepresentation currentRep) {
        // use crossReferencer to get DRepresentationElement from
        // semantic elements.
        for (EObject semanticElement : semanticElements) {
            List<DRepresentationElement> repElementsFromSemantic = new ArrayList<>();
            EObjectQuery eObjectQuery = new EObjectQuery(semanticElement);
            Collection<EObject> referencers = eObjectQuery.getInverseReferences(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET);
            for (EObject referencer : referencers) {
                if (referencer instanceof DRepresentationElement && currentRep.equals(new EObjectQuery(referencer).getRepresentation().get())) {
                    repElementsFromSemantic.add((DRepresentationElement) referencer);
                }
            }

            // Selected elements are filtered with the notified only
            // if there is at least a notified element
            boolean notifiedElementsEmpty = notifiedElements.isEmpty();
            if (!notifiedElementsEmpty) {
                // The representation elements corresponding to a semantic
                // element must follow the notifiedElements order
                for (DRepresentationElement elem : notifiedElements) {
                    if (notifiedElementsEmpty || repElementsFromSemantic.contains(elem)) {
                        selectedElements.add(elem);
                    }
                }
            } else {
                selectedElements.addAll(repElementsFromSemantic);
            }
        }
    }

    /**
     * Tells if the specified <code>view</code> has its target created in the same transaction as itself.
     * 
     * Note: this method is useful only for tree and table dialect, as we want to select only
     * {@link DRepresentationElement} created by semantic change and not on treeItem expansion. And as being based on
     * {@link org.eclipse.emf.ecore.change.ChangeDescription#getObjectsToDetach()} work only from Eclipse Mars. See Bug
     * 460206.
     */
    protected boolean isViewWithNewSemanticTarget(Collection<EObject> attachedEObjects, DRepresentationElement view) {
        boolean isViewWithNewSemanticTarget = false;
        if (attachedEObjects != null && !attachedEObjects.isEmpty()) {
            isViewWithNewSemanticTarget = EcoreUtil.isAncestor(attachedEObjects, view.getTarget());
        }
        return isViewWithNewSemanticTarget;
    }

    /**
     * Analyze the notifications to retrieve the elements to select by default.
     * 
     * @param event
     *            current event to retrieve notifications
     * @param currentRep
     *            The current representation
     * @param keptNotifiedElements
     *            List of elements to complete
     * @return true if the element must be updated according to UI State, false otherwise.
     */
    protected boolean analyseNotifications(ResourceSetChangeEvent event, DRepresentation currentRep, List<DRepresentationElement> keptNotifiedElements) {
        boolean elementsToSelectUpdated = false;
        Collection<EObject> attachedEObjects = null;
        for (Notification n : event.getNotifications()) {
            Object feature = n.getFeature();
            if (!ViewpointPackage.Literals.UI_STATE__ELEMENTS_TO_SELECT.equals(feature) && !ViewpointPackage.Literals.DREPRESENTATION__UI_STATE.equals(feature)) {
                Set<DRepresentationElement> notificationValues = getAttachmentNotificationValues(n);
                for (DRepresentationElement elt : notificationValues) {
                    if (currentRep == new DRepresentationElementQuery(elt).getParentRepresentation()) {
                        if (attachedEObjects == null && isSelectOnlyViewWithNewSemanticTarget()) {
                            // Compute the change description effect only once.
                            TransactionChangeDescription changeDescription = event.getTransaction().getChangeDescription();
                            // Get the objects attached during the current
                            // transaction from the change description. The
                            // getObjectsToDetach() compute those elements which
                            // will be detached in case of rollback or undo.
                            attachedEObjects = changeDescription.getObjectsToDetach();
                        }
                        // EcoreUtil.isAncestor() used to only select top level
                        // created views.
                        if ((!isSelectOnlyViewWithNewSemanticTarget() || isViewWithNewSemanticTarget(attachedEObjects, elt)) && !EcoreUtil.isAncestor(keptNotifiedElements, elt)) {
                            keptNotifiedElements.add(elt);
                        }
                    }
                }
            } else {
                elementsToSelectUpdated = true;
            }
        }
        return elementsToSelectUpdated;
    }

    /**
     * Get the attached elements corresponding to the <code>notification</code>. The notification is supposed to be an
     * "attached" notification.
     * 
     * @param notification
     *            The notification to analyze
     * @return list of attached elements.
     */
    protected Set<DRepresentationElement> getAttachmentNotificationValues(Notification notification) {
        final Set<DRepresentationElement> values = new LinkedHashSet<>();
        Object value = notification.getNewValue();
        if (value instanceof Collection) {
            Iterables.addAll(values, Iterables.filter((Collection<?>) value, DRepresentationElement.class));
        } else if (value instanceof DRepresentationElement) {
            values.add((DRepresentationElement) value);
        }
        return values;
    }

    /**
     * Return the selectOnlyViewWithNewSemanticTarget status.
     * 
     * @return the selectOnlyViewWithNewSemanticTarget status.
     */
    protected boolean isSelectOnlyViewWithNewSemanticTarget() {
        return selectOnlyViewWithNewSemanticTarget;
    }

    /**
     * Dispose this {@link SelectDRepresentationElementsListener}.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
    }

    /**
     * A runnable to set the selection of the given dialect editor. It is able to retrigger itself once, if the first
     * setSelection call did not succeed in setting the selection. This could occurs if a DialectEditor launch an async
     * UI refresh after the creation of this runnable.
     * 
     * @author mporhel
     * 
     */
    private static class SetSelectionRunnable implements Runnable {

        private final DialectEditor dialectEditor;

        private List<DRepresentationElement> newSelection;

        /**
         * 
         * @param dialectEditor
         *            the current dialect editor
         * @param elementsToSelect
         *            the new selection
         */
        SetSelectionRunnable(DialectEditor dialectEditor, Collection<DRepresentationElement> elementsToSelect) {
            super();
            this.dialectEditor = dialectEditor;
            this.newSelection = new ArrayList<DRepresentationElement>(elementsToSelect);
        }

        @Override
        public void run() {
            DialectUIManager.INSTANCE.setSelection(dialectEditor, newSelection);
        }
    }
}
