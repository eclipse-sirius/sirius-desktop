/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.RefreshOrderingHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * An operation to re-compute the global semantic orderings of events and
 * instance roles in a sequence diagram according to the user-specified
 * criteria.
 * 
 * <pre>
 * Semantic Model + User-specified Ordering Expression ---> SemanticMessageOrdering
 * </pre>
 * 
 * @author pcdavid, smonnier
 */
public class RefreshSemanticOrderingsOperation extends AbstractModelChangeOperation<Boolean> {
    /**
     * The name of the variable used to pass event ends to sort to
     * user-specified expressions.
     */
    private static final String EVENT_ENDS_TO_SORT_VARIABLE = "eventEnds"; //$NON-NLS-1$

    private final SequenceDDiagram sequenceDDiagram;

    /**
     * Constructor.
     * 
     * @param diagram
     *            the diagram whose semantic ordering should be refreshed.
     */
    public RefreshSemanticOrderingsOperation(SequenceDDiagram diagram) {
        super("Refresh semantic ordering");
        this.sequenceDDiagram = Preconditions.checkNotNull(diagram);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean execute() {
        boolean changed = false;
        EventEndsOrdering semanticOrdering = sequenceDDiagram.getSemanticOrdering();
        if (semanticOrdering != null) {
            changed = refreshGlobalOrdering(semanticOrdering) || changed;
        }

        InstanceRolesOrdering irSemanticOrdering = sequenceDDiagram.getInstanceRoleSemanticOrdering();
        if (semanticOrdering != null) {
            changed = refreshGlobalOrdering(irSemanticOrdering) || changed;
        }
        return changed;
    }

    /**
     * Refreshes the semantic ordering of all the events in the diagram.
     * 
     * @return true if there is changes during this refresh, false otherwise.
     */
    private boolean refreshGlobalOrdering(EventEndsOrdering semanticOrdering) {
        Iterable<? extends EventEnd> allEnds = getAllEventEnds();
        return RefreshOrderingHelper.updateIfNeeded(semanticOrdering.getEventEnds(), computeEventEndsOrdering(semanticOrdering, allEnds));
    }

    /**
     * Returns all the event ends of the current Sequence diagram. 
     * 
     * The default implementation does the computation on each call, subclasses may override this method to change this behavior.
     * 
     * @return an Iterable with all event ends.
     */
    protected Iterable<? extends EventEnd> getAllEventEnds() {
        return RefreshOrderingHelper.getAllEventEnds(sequenceDDiagram);
    }
    
    private List<EventEnd> computeEventEndsOrdering(EventEndsOrdering semanticOrdering, Iterable<? extends EventEnd> allEnds) {
        Map<EObject, EventEnd> index = Maps.newHashMap();
        for (EventEnd eventEnd : allEnds) {
            index.put(eventEnd.getSemanticEnd(), eventEnd);
        }

        Iterable<EObject> semanticEnds = Iterables.transform(allEnds, new Function<EventEnd, EObject>() {
            public EObject apply(EventEnd from) {
                return from.getSemanticEnd();
            }
        });
        List<EObject> orderedSemanticEnds = computeOrdering(semanticEnds, DescriptionPackage.eINSTANCE.getSequenceDiagramDescription_EndsOrdering(), true);

        List<EventEnd> result = new ArrayList<EventEnd>();
        for (EObject semanticEnd : orderedSemanticEnds) {
            EventEnd ee = index.get(semanticEnd);
            if (ee != null) {
                result.add(ee);
            }
        }

        return result;
    }

    /**
     * Refreshes the semantic ordering of all the instance roles in the diagram.
     * 
     * @return true if there is changes during this refresh, false otherwise.
     */
    private boolean refreshGlobalOrdering(InstanceRolesOrdering semanticOrdering) {
        Iterable<? extends DNode> allInstanceRoles = Iterables.filter(sequenceDDiagram.getNodes(), InstanceRole.viewpointElementPredicate());
        return RefreshOrderingHelper.updateIfNeeded(semanticOrdering.getSemanticInstanceRoles(), computeInstanceRolesOrdering(semanticOrdering, allInstanceRoles));
    }

    private List<EObject> computeInstanceRolesOrdering(InstanceRolesOrdering semanticOrdering, Iterable<? extends DNode> allInstanceRoles) {
        List<EObject> semanticInstanceRoles = Lists.newArrayList();
        for (DNode node : allInstanceRoles) {
            semanticInstanceRoles.add(node.getTarget());
        }

        List<EObject> orderedSemanticEnds = computeOrdering(semanticInstanceRoles, DescriptionPackage.eINSTANCE.getSequenceDiagramDescription_InstanceRolesOrdering(), false);
        return orderedSemanticEnds;
    }

    private List<EObject> computeOrdering(Iterable<EObject> semanticEnds, EAttribute expressionAttribute, boolean declareEventEndsVariable) {
        if (StringUtil.isEmpty((String) getSequenceDescription().eGet(expressionAttribute))) {
            return Lists.newArrayList(semanticEnds);
        }

        final IInterpreter interpreter = InterpreterUtil.getInterpreter(sequenceDDiagram);
        if (declareEventEndsVariable) {
            interpreter.setVariable(EVENT_ENDS_TO_SORT_VARIABLE, Lists.newArrayList(semanticEnds));
        }

        try {
            RuntimeLoggerInterpreter loggerInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
            Collection<EObject> result = loggerInterpreter.evaluateCollection(sequenceDDiagram.getTarget(), getSequenceDescription(), expressionAttribute);
            return Lists.newArrayList(result);
        } finally {
            if (declareEventEndsVariable) {
                interpreter.unSetVariable(EVENT_ENDS_TO_SORT_VARIABLE);
            }
        }
    }

    private SequenceDiagramDescription getSequenceDescription() {
        if (sequenceDDiagram != null) {
            DiagramDescription desc = sequenceDDiagram.getDescription();
            return SequenceDiagramDescription.class.cast(desc);
        } else {
            return null;
        }
    }
}
