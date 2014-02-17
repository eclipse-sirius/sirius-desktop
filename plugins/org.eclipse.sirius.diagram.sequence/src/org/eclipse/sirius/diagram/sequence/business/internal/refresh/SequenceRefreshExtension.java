/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.refresh.IRefreshExtension;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Specific refresh extension to handle structural changes in sequence diagrams.
 * It allows to keep the last known position when the parent of an abstract node
 * event changes.
 * 
 * @author mporhel
 * 
 */
public class SequenceRefreshExtension implements IRefreshExtension {

    private SequenceDDiagram currentDiagram;

    private Map<EObject, AbsoluteBoundsFilter> flags;

    /**
     * {@inheritDoc}
     */
    public void beforeRefresh(DDiagram dDiagram) {
        if (dDiagram instanceof SequenceDDiagram) {
            currentDiagram = (SequenceDDiagram) dDiagram;

            Collection<DDiagramElement> nodeEvents = getEventsToSync(currentDiagram);

            if (nodeEvents.size() != 0) {
                flags = Maps.newHashMapWithExpectedSize(nodeEvents.size());
                for (DDiagramElement elt : nodeEvents) {
                    Iterable<AbsoluteBoundsFilter> flag = Iterables.filter(elt.getGraphicalFilters(), AbsoluteBoundsFilter.class);
                    EObject semanticTarget = elt.getTarget();
                    if (semanticTarget != null && Iterables.size(flag) == 1) {
                        flags.put(semanticTarget, Iterables.getOnlyElement(flag));
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void postRefresh(DDiagram dDiagram) {
        if (currentDiagram != null && currentDiagram.equals(dDiagram) && flags != null) {
            Collection<DDiagramElement> nodeEvents = getEventsToSync((SequenceDDiagram) dDiagram);

            if (nodeEvents.size() != 0) {
                for (DDiagramElement elt : nodeEvents) {
                    Iterable<AbsoluteBoundsFilter> flag = Iterables.filter(elt.getGraphicalFilters(), AbsoluteBoundsFilter.class);
                    EObject semanticTarget = elt.getTarget();
                    if (semanticTarget != null && Iterables.isEmpty(flag) && flags.containsKey(semanticTarget)) {
                        AbsoluteBoundsFilter prevFlag = flags.get(semanticTarget);

                        AbsoluteBoundsFilter newFlag = DiagramFactory.eINSTANCE.createAbsoluteBoundsFilter();
                        newFlag.setX(LayoutConstants.EXTERNAL_CHANGE_FLAG.x);
                        newFlag.setY(prevFlag.getY());
                        newFlag.setHeight(prevFlag.getHeight());
                        newFlag.setWidth(prevFlag.getWidth());

                        elt.getGraphicalFilters().add(newFlag);
                    }
                }
            }
        }
        if (flags != null) {
            flags.clear();
            flags = null;
        }
        currentDiagram = null;
    }

    private Collection<DDiagramElement> getEventsToSync(SequenceDDiagram sdd) {
        Collection<DDiagramElement> diagramElements = Lists.newArrayList(sdd.getDiagramElements());
        Predicate<DDiagramElement> eventsToSync = Predicates.or(AbstractNodeEvent.viewpointElementPredicate(), Message.viewpointElementPredicate());
        return Lists.newArrayList(Iterables.filter(diagramElements, eventsToSync));
    }
}
