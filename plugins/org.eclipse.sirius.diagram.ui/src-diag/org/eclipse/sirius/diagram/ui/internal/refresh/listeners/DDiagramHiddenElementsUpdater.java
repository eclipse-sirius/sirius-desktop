/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;

/**
 * A ResourceSet listener to handle hiddenElementLabels transient reference
 * update.
 * 
 * @author lredor
 */
public class DDiagramHiddenElementsUpdater extends ResourceSetListenerImpl {

    private DDiagram dDiagram;

    private Set<DDiagramElement> newElementsToHide;

    private Set<DDiagramElement> newElementsToReveal;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain}
     * @param dDiagram
     *            the {@link DDiagram} to update
     */
    public DDiagramHiddenElementsUpdater(TransactionalEditingDomain domain, DDiagram dDiagram) {
        super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters())));
        domain.addResourceSetListener(this);
        this.dDiagram = dDiagram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        Command cmd = null;
        newElementsToHide = new HashSet<DDiagramElement>();
        newElementsToReveal = new HashSet<DDiagramElement>();

        computeElementsToHideAndReveal(event.getNotifications());

        CompoundCommand compoundCommand = new CompoundCommand();
        if (!newElementsToHide.isEmpty()) {
            Command hideDDiagramEltCmd = AddCommand.create(getTarget(), dDiagram, DiagramPackage.Literals.DDIAGRAM__HIDDEN_ELEMENTS, newElementsToHide);
            compoundCommand.append(hideDDiagramEltCmd);
        }
        if (!newElementsToReveal.isEmpty()) {
            Command revealDDiagramEltCmd = RemoveCommand.create(getTarget(), dDiagram, DiagramPackage.Literals.DDIAGRAM__HIDDEN_ELEMENTS, newElementsToReveal);
            compoundCommand.append(revealDDiagramEltCmd);
        }
        if (!compoundCommand.isEmpty()) {
            cmd = compoundCommand;
        }
        newElementsToHide = null;
        newElementsToReveal = null;
        return cmd;
    }

    private void computeElementsToHideAndReveal(List<Notification> notifications) {
        // Finds elements to hide or reveal
        for (Notification notification : notifications) {
            if (notification.getNotifier() instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = (DDiagramElement) notification.getNotifier();
                DDiagram parentDDiagram = dDiagramElement.getParentDiagram();
                if (parentDDiagram != null && parentDDiagram.equals(dDiagram)) {
                    if (notification.getNewValue() instanceof HideLabelFilter && notification.getOldValue() == null) {
                        // check that this element is not already hidden
                        HideLabelFilter hideLabelFilter = (HideLabelFilter) notification.getNewValue();
                        if (!new DDiagramElementQuery(dDiagramElement).isHidden() && hideLabelFilter.getHiddenLabels().isEmpty()) {
                            newElementsToHide.add(dDiagramElement);
                        }
                    } else if (notification.getOldValue() instanceof HideLabelFilter && notification.getNewValue() == null) {
                        // check that this element is not hidden too
                        if (!new DDiagramElementQuery(dDiagramElement).isHidden()) {
                            newElementsToReveal.add(dDiagramElement);
                        }
                    } else if (notification.getNewValue() instanceof HideFilter && notification.getOldValue() == null) {
                        // check that the label of this element is not already
                        // hidden
                        if (!new DDiagramElementQuery(dDiagramElement).isLabelHidden()) {
                            newElementsToHide.add(dDiagramElement);
                        }
                    } else if (notification.getOldValue() instanceof HideFilter && notification.getNewValue() == null) {
                        // check that the label of this element is not hidden
                        // too
                        if (!new DDiagramElementQuery(dDiagramElement).isLabelHidden()) {
                            newElementsToReveal.add(dDiagramElement);
                        }
                    }
                }
            }
        }
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        dDiagram = null;
    }
}
