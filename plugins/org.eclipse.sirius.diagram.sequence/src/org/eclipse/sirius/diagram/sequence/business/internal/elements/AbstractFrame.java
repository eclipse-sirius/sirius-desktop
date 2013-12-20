/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.tools.api.profiler.SiriusTasks;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Represents a frame container.
 * 
 * @author mporhel
 */
public abstract class AbstractFrame extends AbstractSequenceNode implements ISequenceEvent {

    private static final ProfilerTask COVERAGE = new ProfilerTask("Sequence", "Compute interaction use coverage", SiriusTasks.IMAGES_VIEWPOINT);

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing the combined fragment.
     */
    AbstractFrame(Node node) {
        super(node);
    }

    /**
     * Returns a predicate to check whether a GMF View represents a frame.
     * 
     * @return a predicate to check whether a GMF View represents a frame.
     */
    public static Predicate<View> notationPredicate() {
        return Predicates.or(InteractionUse.notationPredicate(), CombinedFragment.notationPredicate());
    }

    public Rectangle getProperLogicalBounds() {
        /*
         * Combined Fragments are directly on the diagram itself, so we can use
         * the raw GMF bounds as is.
         */
        return getRawNotationBounds();
    }

    /**
     * Get the covered lifelines.
     * 
     * @return the covered lifelines.
     */
    public Collection<Lifeline> computeCoveredLifelines() {
        DslCommonPlugin.PROFILER.startWork(COVERAGE);
        Collection<EObject> semLifelines = Lists.newArrayList();
        Collection<Lifeline> coveredLifelines = Lists.newArrayList();

        EObject element = getNotationNode().getElement();
        if (element instanceof DDiagramElement && ((DDiagramElement) element).getDiagramElementMapping() instanceof FrameMapping) {
            DDiagramElement dde = (DDiagramElement) element;
            FrameMapping mapping = (FrameMapping) dde.getDiagramElementMapping();
            EObject semanticInteractionUse = dde.getTarget();
            IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticInteractionUse);

            if (interpreter != null && !StringUtil.isEmpty(mapping.getCoveredLifelinesExpression())) {
                try {
                    semLifelines = interpreter.evaluateCollection(semanticInteractionUse, mapping.getCoveredLifelinesExpression());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(mapping, DescriptionPackage.eINSTANCE.getFrameMapping_CoveredLifelinesExpression(), e);
                }
            }
        }

        Collection<Lifeline> allLifelines = Lists.newArrayList(getDiagram().getAllLifelines());
        for (Lifeline lifeline : allLifelines) {
            EObject sem = ISequenceElement.SEMANTIC_TARGET.apply(lifeline);
            if (semLifelines.contains(sem)) {
                coveredLifelines.add(lifeline);
            }
        }

        DslCommonPlugin.PROFILER.stopWork(COVERAGE);
        return coveredLifelines;
    }

    /**
     * Get the covered lifelines.
     * 
     * @return the covered lifelines.
     */
    public Collection<ISequenceEvent> computeParentEvents() {
        Collection<Lifeline> coveredLifelines = computeCoveredLifelines();
        return computeParentEvents(coveredLifelines);
    }

    /**
     * Get the covered lifelines.
     * 
     * @param coveredLifelines
     *            a collection of lifelines that should be a subset of computed
     *            lifelines (NO CHECK)
     * 
     * @return the covered lifelines.
     */
    public Collection<ISequenceEvent> computeParentEvents(Collection<Lifeline> coveredLifelines) {
        Collection<ISequenceEvent> parentEvents = Sets.newHashSet();
        for (Lifeline lifeline : coveredLifelines) {
            EventFinder finder = new EventFinder(lifeline);
            finder.setEventsToIgnore(Predicates.equalTo((ISequenceEvent) this));
            ISequenceEvent localParent = finder.findMostSpecificEvent(this.getVerticalRange());
            if (localParent != null && localParent.getVerticalRange().includes(this.getVerticalRange())) {
                parentEvents.add(localParent);
            }
        }
        return parentEvents;
    }

    /**
     * Combined fragments are not associated to a particular lifeline.
     * <p>
     * {@inheritDoc}
     */
    public Option<Lifeline> getLifeline() {
        return Options.newNone();
    }

    /**
     * {@inheritDoc}
     */
    public ISequenceEvent getParentEvent() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLogicallyInstantaneous() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canChildOccupy(ISequenceEvent child, Range range) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Range getOccupiedRange() {
        return Range.emptyRange();
    }

    /**
     * {@inheritDoc}
     */
    public Range getValidSubEventsRange() {
        return Range.emptyRange();
    }

    /**
     * {@inheritDoc}
     */
    public ISequenceEvent getHierarchicalParentEvent() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Option<Operand> getParentOperand() {
        return new ParentOperandFinder(this).getParentOperand();
    }

    /**
     * {@inheritDoc}
     */
    public Collection<ISequenceEvent> getEventsToMoveWith() {
        return getSubEvents();
    }
}
