/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.EventFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.ui.tools.api.profiler.SiriusTasks;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Represents a frame container.
 * 
 * @author mporhel
 */
public abstract class AbstractFrame extends AbstractSequenceNode implements ISequenceEvent {

    private static final ProfilerTask COVERAGE = new ProfilerTask(Messages.AbstractFrame_coverageProfilerTaskCategory, Messages.AbstractFrame_coverageProfilerTaskName, SiriusTasks.IMAGES_VIEWPOINT);

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

    /**
     * Get proper logical bounds.
     * 
     * @return proper logical bounds
     */
    @Override
    public Rectangle getProperLogicalBounds() {
        /*
         * Combined Fragments are directly on the diagram itself, so we can use the raw GMF bounds as is.
         */
        return getRawNotationBounds();
    }

    /**
     * Get the covered lifelines.
     * 
     * @return the covered lifelines.
     */
    public Collection<Lifeline> computeCoveredLifelines() {
        if (CacheHelper.isStructuralCacheEnabled()) {
            Collection<Lifeline> coverage = CacheHelper.getCoverageCache().get(this);
            if (coverage != null) {
                return new ArrayList<Lifeline>(coverage);
            }
        }

        DslCommonPlugin.PROFILER.startWork(COVERAGE);

        Collection<EObject> semLifelines = new ArrayList<>();
        Collection<Lifeline> coveredLifelines = new ArrayList<>();

        EObject element = getNotationNode().getElement();
        if (element instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) element;
            DiagramElementMapping diagramElementMapping = dde.getDiagramElementMapping();
            if (diagramElementMapping instanceof FrameMapping) {
                FrameMapping mapping = (FrameMapping) diagramElementMapping;
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
        }

        Collection<Lifeline> allLifelines = new ArrayList<Lifeline>(getDiagram().getAllLifelines());
        for (Lifeline lifeline : allLifelines) {
            EObject sem = ISequenceElement.SEMANTIC_TARGET.apply(lifeline);
            if (semLifelines.contains(sem)) {
                coveredLifelines.add(lifeline);
            }
        }

        DslCommonPlugin.PROFILER.stopWork(COVERAGE);

        if (CacheHelper.isStructuralCacheEnabled()) {
            CacheHelper.getCoverageCache().put(this, coveredLifelines);
        }

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
     *            a collection of lifelines that should be a subset of computed lifelines (NO CHECK)
     * 
     * @return the covered lifelines.
     */
    public Collection<ISequenceEvent> computeParentEvents(Collection<Lifeline> coveredLifelines) {
        Collection<ISequenceEvent> parentEvents = new HashSet<>();
        Range verticalRange = this.getVerticalRange();
        for (Lifeline lifeline : coveredLifelines) {
            EventFinder finder = new EventFinder(lifeline);
            finder.setEventsToIgnore(Predicates.equalTo((ISequenceEvent) this));
            ISequenceEvent localParent = finder.findMostSpecificEvent(verticalRange);
            if (localParent != null && localParent.getVerticalRange().includes(verticalRange)) {
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
    @Override
    public Option<Lifeline> getLifeline() {
        return Options.newNone();
    }

    @Override
    public ISequenceEvent getParentEvent() {
        return null;
    }

    @Override
    public boolean isLogicallyInstantaneous() {
        return false;
    }

    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range) {
        return false;
    }

    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
        return false;
    }

    @Override
    public Range getOccupiedRange() {
        return Range.emptyRange();
    }

    @Override
    public Range getValidSubEventsRange() {
        return Range.emptyRange();
    }

    @Override
    public ISequenceEvent getHierarchicalParentEvent() {
        return null;
    }

    @Override
    public Option<Operand> getParentOperand() {
        return new ParentOperandFinder(this).getParentOperand();
    }

    @Override
    public Collection<ISequenceEvent> getEventsToMoveWith() {
        return getSubEvents();
    }
}
