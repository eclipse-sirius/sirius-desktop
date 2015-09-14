/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DEdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.EdgeTargetQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.EdgeTargetQuery.FoldingState;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A command to fold all the edges (which support it) which have a given element
 * as folding point.
 *
 * @author pcdavid
 */
public class ToggleFoldingStateCommand extends RecordingCommand {
    private final IDiagramElementEditPart part;

    /**
     * <code>true</code> if we are folding, <code>false</code> if we are
     * unfolding.
     */
    private boolean folding;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain.
     * @param part
     *            the folding point from which to start the folding.
     */
    public ToggleFoldingStateCommand(TransactionalEditingDomain domain, IDiagramElementEditPart part) {
        super(domain, Messages.ToggleFoldingStateCommand_label);
        this.part = part;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        EdgeTarget startingPoint = getStartingPoint();
        if (startingPoint != null) {
            toggleFoldingState(startingPoint);
        }
    }

    private void toggleFoldingState(EdgeTarget startingPoint) {
        FoldingState currentState = new EdgeTargetQuery(startingPoint).getFoldingState();
        folding = currentState == FoldingState.UNFOLDED;
        Set<DDiagramElement> handledElements = Sets.newHashSet((DDiagramElement) startingPoint);
        Iterable<DEdge> allFoldables = new EdgeTargetQuery(startingPoint).getAllFoldableEdges();
        for (DEdge edge : allFoldables) {
            if (new DDiagramElementQuery(edge).isExplicitlyFolded() != folding) {
                setEdgeFoldingPointState(edge);
                handledElements.add(edge);
                setAccessibleElementsState(edge, handledElements);
            }
        }
    }

    private void setAccessibleElementsState(DEdge edge, Set<DDiagramElement> handledElements) {
        setEdgeFoldedState(edge);
        handledElements.add(edge);
        EdgeTarget elementToHandle = new DEdgeQuery(edge).getFoldingTarget();
        if (!handledElements.contains(elementToHandle)) {
            setElementFoldedState((DDiagramElement) elementToHandle);
            handledElements.add((DDiagramElement) elementToHandle);
            for (DEdge nextEdge : new EdgeTargetQuery(elementToHandle).getFoldableEdgesToFollow()) {
                setAccessibleElementsState(nextEdge, handledElements);
            }
        }
    }

    private void setEdgeFoldingPointState(DEdge edge) {
        if (folding) {
            addFilterType(edge, DiagramFactory.eINSTANCE.createFoldingPointFilter());
        } else {
            removeFilterType(edge, DiagramFactory.eINSTANCE.createFoldingPointFilter());
        }
    }

    private void setEdgeFoldedState(DEdge edge) {
        if (folding) {
            if (!new DDiagramElementQuery(edge).isExplicitlyFolded()) {
                addFilterType(edge, DiagramFactory.eINSTANCE.createFoldingFilter());
            }
        } else {
            removeFilterType(edge, DiagramFactory.eINSTANCE.createFoldingFilter());
        }
    }

    private void setElementFoldedState(DDiagramElement element) {
        if (folding) {
            addFilterType(element, DiagramFactory.eINSTANCE.createFoldingFilter());
        } else {
            removeFilterType(element, DiagramFactory.eINSTANCE.createFoldingFilter());
        }
    }

    private void addFilterType(DDiagramElement element, GraphicalFilter filter) {
        if (!Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(filter.getClass()))) {
            element.getGraphicalFilters().add(filter);
        }
    }

    private void removeFilterType(DDiagramElement element, GraphicalFilter filter) {
        for (Iterator<GraphicalFilter> iter = element.getGraphicalFilters().iterator(); iter.hasNext(); /* */) {
            GraphicalFilter gf = iter.next();
            if (filter.eClass().isInstance(gf)) {
                iter.remove();
            }
        }
    }

    private EdgeTarget getStartingPoint() {
        DDiagramElement element = part.resolveDiagramElement();
        if (element instanceof EdgeTarget) {
            return (EdgeTarget) element;
        } else {
            return null;
        }
    }
}
