/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.session;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A post commit listener which select the newly created representation elements.
 * 
 * @author mporhel
 */
public final class SelectCreatedDRepresentationElementsListener extends ResourceSetListenerImpl {

    /**
     * Constructor.
     */
    public SelectCreatedDRepresentationElementsListener() {
        super(NotificationFilter.NOT_TOUCH.and(SessionEventBrokerImpl.asFilter(DanglingRefRemovalTrigger.IS_ATTACHMENT)).and(
                NotificationFilter.createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION).or(
                        NotificationFilter.createNotifierTypeFilter(ViewpointPackage.Literals.DREPRESENTATION_ELEMENT))));
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
        if (activeEditor instanceof DialectEditor) {
            final DialectEditor dialectEditor = (DialectEditor) activeEditor;
            DRepresentation currentRep = dialectEditor.getRepresentation();

            final Collection<DRepresentationElement> newElementsToSelect = Sets.newLinkedHashSet();
            for (Notification n : event.getNotifications()) {
                Set<DRepresentationElement> notificationValues = getNotificationValues(n);
                for (DRepresentationElement elt : notificationValues) {
                    if (currentRep == new DRepresentationElementQuery(elt).getParentRepresentation()) {
                        newElementsToSelect.add(elt);
                    }
                }
            }

            // Set the selection in async exec: for some dialect, ui could be
            // refresh by another post commit triggered after this one and doing
            // some UI refresh in sync exec.
            PlatformUI.getWorkbench().getDisplay().asyncExec(new SetSelectionRunnable(dialectEditor, newElementsToSelect, true));
        }
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
     * A runnable to set the selection of the given dialect editor. It is able
     * to retrigger itself once, if the first setSelection call did not succeed
     * in setting the selection. This could occurs if a DialectEditor launch an
     * aysnc UI refresh after the creation of this runnable.
     * 
     * @author mporhel
     * 
     */
    private static class SetSelectionRunnable implements Runnable {

        private final DialectEditor dialectEditor;

        private List<DRepresentationElement> newSelection;

        private boolean retrigger;

        /**
         * 
         * @param dialectEditor
         *            the current dialect editor
         * @param newElementsToSelect
         *            the new selection
         * @param retrigger
         *            true to check the selection and retrigger the runnable
         *            once.
         */
        public SetSelectionRunnable(DialectEditor dialectEditor, Collection<DRepresentationElement> newElementsToSelect, boolean retrigger) {
            super();
            this.dialectEditor = dialectEditor;
            this.newSelection = Lists.newArrayList(newElementsToSelect);
            this.retrigger = retrigger;
        }

        @Override
        public void run() {
            DialectUIManager.INSTANCE.setSelection(dialectEditor, newSelection);

            if (retrigger) {
                // Check the setSelection result
                Collection<DSemanticDecorator> selection = DialectUIManager.INSTANCE.getSelection(dialectEditor);
                if (selection.isEmpty() || Iterables.removeAll(selection, newSelection)) {
                    retrigger = false;
                    PlatformUI.getWorkbench().getDisplay().asyncExec(this);
                }
            }
        }
    }
}
