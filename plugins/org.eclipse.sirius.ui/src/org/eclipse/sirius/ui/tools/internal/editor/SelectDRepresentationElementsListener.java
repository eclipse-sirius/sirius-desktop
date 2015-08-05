/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A post commit listener which select the representation elements specified
 * through the "Elements To Select" expression and "Inverse Selection Order" tag
 * of the tool. </br> Elements will be selected only on the active editor.
 * 
 * Each dialect is responsible to add this post commit listener or one
 * specializing this one.
 * 
 * @author mporhel
 */
public class SelectDRepresentationElementsListener extends ResourceSetListenerImpl {

    /**
     * This {@link NotificationFilter} corresponds to
     * {@link DRepresentationElement} creation or specified "Elements To Select"
     * list.
     */
    private static final NotificationFilter DEFAULT_NOTIFICATION_FILTER = NotificationFilter.createFeatureFilter(ViewpointPackage.Literals.UI_STATE__ELEMENTS_TO_SELECT).or(
            NotificationFilter.NOT_TOUCH.and(SessionEventBrokerImpl.asFilter(DanglingRefRemovalTrigger.IS_ATTACHMENT)).and(
                    NotificationFilter.createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION).or(
                            NotificationFilter.createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION_ELEMENT))));

    /**
     * The dialect editor.
     */
    protected DialectEditor dialectEditor;

    private boolean activateDefaultSelection;

    /**
     * Constructor.
     * 
     * @param editor
     *            the editor on which the representation elements should be
     *            selected, if the editor is active.
     * @param filter
     *            the notification filter used to take on more specific notified
     *            elements
     * @param activateDefaultSelection
     *            If UIState.elementsToSelect has not be notified, the notified
     *            elements are selected only if defaultSelection is true.
     */
    public SelectDRepresentationElementsListener(DialectEditor editor, NotificationFilter filter, boolean activateDefaultSelection) {
        super(DEFAULT_NOTIFICATION_FILTER.or(Preconditions.checkNotNull(filter)));
        init(editor, activateDefaultSelection);
    }

    /**
     * Constructor.
     *
     * @param editor
     *            the editor on which the representation elements should be
     *            selected, if the editor is active.
     * @param activateDefaultSelection
     *            If UIState.elementsToSelect has not be notified, the notified
     *            elements are selected only if defaultSelection is true.
     */
    public SelectDRepresentationElementsListener(DialectEditor editor, boolean activateDefaultSelection) {
        super(DEFAULT_NOTIFICATION_FILTER);
        init(editor, activateDefaultSelection);
    }

    private void init(DialectEditor editor, boolean defaultSelection) {
        dialectEditor = Preconditions.checkNotNull(editor);
        this.activateDefaultSelection = defaultSelection;
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
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
        if (dialectEditor.equals(activeEditor)) {
            DRepresentation currentRep = dialectEditor.getRepresentation();

            List<DRepresentationElement> elementsToSelect = extractElementsToSelect(event, currentRep);
            if (elementsToSelect != null) {
                // Set the selection in async exec: for some dialect, ui could
                // be refresh by another post commit triggered after this one
                // and doing some UI refresh in sync exec.
                PlatformUI.getWorkbench().getDisplay().asyncExec(new SetSelectionRunnable(dialectEditor, elementsToSelect));
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
    private List<DRepresentationElement> extractElementsToSelect(ResourceSetChangeEvent event, DRepresentation currentRep) {
        List<DRepresentationElement> elementsToSelect = null;
        final List<DRepresentationElement> notifiedElements = Lists.newArrayList();
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
            if (notifiedElements.isEmpty() || !activateDefaultSelection) {
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
        List<DRepresentationElement> dRepElements = Lists.newArrayList();
        List<EObject> semanticElements = Lists.newArrayList();

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
                selectedElements = Lists.newArrayList();
                caseHasSemanticElement(selectedElements, notifiedElements, semanticElements, currentRep);
            } else if (hasRepElement && !hasRepresentation && !hasSemanticElement) {
                // Selected elements are filtered with the notified only
                // if there is at least a notified element
                if (!notifiedElements.isEmpty()) {
                    dRepElements.retainAll(notifiedElements);
                }
                selectedElements = Lists.newArrayList(dRepElements);
            } else {
                selectedElements = Lists.newArrayList();
            }
        }
        return selectedElements;
    }

    private void caseHasSemanticElement(List<DRepresentationElement> selectedElements, List<DRepresentationElement> notifiedElements, List<EObject> semanticElements, DRepresentation currentRep) {
        // use crossReferencer to get DRepresentationElement from
        // semantic elements.
        for (EObject semanticElement : semanticElements) {
            List<DRepresentationElement> repElementsFromSemantic = Lists.newArrayList();
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

    private boolean analyseNotifications(ResourceSetChangeEvent event, DRepresentation currentRep, List<DRepresentationElement> keptNotifiedElements) {
        final Collection<DRepresentationElement> notifiedElements = Sets.newLinkedHashSet();
        boolean elementsToSelectUpdated = false;
        for (Notification n : event.getNotifications()) {
            if (!n.getFeature().equals(ViewpointPackage.Literals.UI_STATE__ELEMENTS_TO_SELECT) && !n.getFeature().equals(ViewpointPackage.Literals.DREPRESENTATION__UI_STATE)) {
                Set<DRepresentationElement> notificationValues = getNotificationValues(n);
                for (DRepresentationElement elt : notificationValues) {
                    if (currentRep == new DRepresentationElementQuery(elt).getParentRepresentation()) {
                        notifiedElements.add(elt);
                    }
                }
            } else {
                elementsToSelectUpdated = true;
            }
        }

        // Minimize the elements to select: do not select the elements whose
        // parent is itself newly created and will be selected.
        for (DRepresentationElement elt : notifiedElements) {
            if (!notifiedElements.contains(elt.eContainer())) {
                keptNotifiedElements.add(elt);
            }
        }
        return elementsToSelectUpdated;
    }

    private Set<DRepresentationElement> getNotificationValues(Notification notification) {
        final Set<DRepresentationElement> values = Sets.newLinkedHashSet();
        Object value = notification.getNewValue();
        if (value instanceof Collection) {
            Iterables.addAll(values, Iterables.filter((Collection<?>) value, DRepresentationElement.class));
        } else if (value instanceof DRepresentationElement) {
            values.add((DRepresentationElement) value);
        }
        return values;
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
     * A runnable to set the selection of the given dialect editor. It is able
     * to retrigger itself once, if the first setSelection call did not succeed
     * in setting the selection. This could occurs if a DialectEditor launch an
     * async UI refresh after the creation of this runnable.
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
        public SetSelectionRunnable(DialectEditor dialectEditor, Collection<DRepresentationElement> elementsToSelect) {
            super();
            this.dialectEditor = dialectEditor;
            this.newSelection = Lists.newArrayList(elementsToSelect);
        }

        @Override
        public void run() {
            DialectUIManager.INSTANCE.setSelection(dialectEditor, newSelection);
        }
    }
}
