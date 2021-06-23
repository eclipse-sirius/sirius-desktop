/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.internal.tool.command.builders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.ui.PostRefreshCommandFactory;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Specific sequence viewpoint command to flag created elements from tool.
 * 
 * @author mporhel
 */
public class SequenceCreatedEventsFlaggingSiriusCommand extends SiriusCommand {

    private Predicate<DDiagramElement> shouldFlag;

    private DDiagram parentDiagram;

    private Point lostNodesLocation;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param name
     *            the command name.
     * @param diagram
     *            the current diagram
     * @param shouldFlag
     *            predicates to select created {@link DDiagramElement} eleemnts
     *            to flag.
     */
    public SequenceCreatedEventsFlaggingSiriusCommand(TransactionalEditingDomain domain, String name, DDiagram diagram, Predicate<DDiagramElement> shouldFlag) {
        super(domain, name);
        this.parentDiagram = diagram;
        this.shouldFlag = completeShouldFlagPredicate(shouldFlag);
    }

    private Predicate<DDiagramElement> completeShouldFlagPredicate(Predicate<DDiagramElement> pred) {
        Predicate<DDiagramElement> result = LostMessageEnd.viewpointElementPredicate();
        if (pred != null) {
            result = Predicates.or(pred, result);
        }
        return result;
    }

    @Override
    protected void doExecute() {
        super.doExecute();

        flagNewElements();
    }

    private void flagNewElements() {
        final Collection<EObject> createdSemantics = getCreatedObjects();

        final Collection<EObject> mainSemantics = flagAndRegisterMainCreatedElements();
        flagPostRefresh(mainSemantics, createdSemantics);
        flagPostPrecommitRefresh(mainSemantics, createdSemantics);
    }

    private void flagPostPrecommitRefresh(final Collection<EObject> mainSemantics, final Collection<EObject> createdObjects) {
        if (parentDiagram instanceof DSemanticDecorator && ((DSemanticDecorator) parentDiagram).getTarget() != null) {
            final EObject semanticElement = ((DSemanticDecorator) parentDiagram).getTarget();
            final Session session = SessionManager.INSTANCE.getSession(semanticElement);
            if (session != null) {
                final RefreshEditorsPrecommitListener listener = session.getRefreshEditorsListener();
                PostRefreshCommandFactory factory = new SequencePostRefreshFactory(parentDiagram, session, mainSemantics, createdObjects);
                listener.addPostRefreshCommandFactory(factory);
            }
        }
    }

    /**
     * Flag directly created by tool elements and register main semantics.
     */
    private Collection<EObject> flagAndRegisterMainCreatedElements() {
        final Collection<EObject> mainSemantics = new HashSet<>();
        final Collection<DDiagramElement> createdDDE = Lists.newArrayList(Iterables.filter(getCreatedRepresentationElements(), DDiagramElement.class));
        for (DDiagramElement dde : createdDDE) {
            if (shouldFlag != null && shouldFlag.apply(dde)) {
                safeAddCreationFlag(dde, LayoutConstants.TOOL_CREATION_FLAG);
                mainSemantics.add(dde.getTarget());
            } else if (dde.getTarget() != null) {
                mainSemantics.add(dde.getTarget());
            }
        }
        return mainSemantics;
    }

    /**
     * Flag indirectly created by tool elements, distinguish the main semantics
     * from other semantics elements.
     */
    private Collection<DDiagramElement> flagPostRefresh(final Collection<EObject> mainSemantics, final Collection<EObject> createdSemantics) {
        Collection<DDiagramElement> flags = new ArrayList<>();

        if (parentDiagram != null && shouldFlag != null) {
            for (DDiagramElement dde : Iterables.filter(parentDiagram.getDiagramElements(), shouldFlag)) {
                Option<DDiagramElement> flagged = Options.newNone();
                if (dde.getTarget() != null) {
                    if (mainSemantics.contains(dde.getTarget())) {
                        flagged = safeAddCreationFlag(dde, LayoutConstants.TOOL_CREATION_FLAG);
                    } else if (createdSemantics.contains(dde.getTarget())) {
                        flagged = safeAddCreationFlag(dde, LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC);
                    }
                }
                if (flagged.some()) {
                    flags.add(flagged.get());
                }
            }
        }

        return flags;
    }

    private Option<DDiagramElement> safeAddCreationFlag(DDiagramElement dde, Rectangle toolCreationFlag) {
        if (Iterables.isEmpty(Iterables.filter(dde.getGraphicalFilters(), AbsoluteBoundsFilter.class))) {
            AbsoluteBoundsFilter flag = getFlag(toolCreationFlag);

            if (LostMessageEnd.viewpointElementPredicate().apply(dde) && lostNodesLocation != null) {
                flag.setY(lostNodesLocation.y);
            }

            dde.getGraphicalFilters().add(flag);
            return Options.newSome(dde);
        }
        return Options.newNone();
    }

    private AbsoluteBoundsFilter getFlag(Rectangle toolCreationFlag) {
        AbsoluteBoundsFilter flag = DiagramFactory.eINSTANCE.createAbsoluteBoundsFilter();
        flag.setX(toolCreationFlag.x);
        flag.setY(toolCreationFlag.y);
        flag.setHeight(toolCreationFlag.height);
        flag.setWidth(toolCreationFlag.width);

        return flag;
    }

    /**
     * Set lost nodes location, for first layout.
     * 
     * @param location
     *            location of lost nodes.
     */
    public void setLostNodesLocation(Point location) {
        this.lostNodesLocation = location;
    }

    /**
     * Post refresh factory to flag elements created during precommit
     * representation refresh.
     */
    private final class SequencePostRefreshFactory implements PostRefreshCommandFactory {

        private final Collection<EObject> mainSemantics = new ArrayList<>();

        private final Collection<EObject> createdObjects = new ArrayList<>();

        private final Session session;

        private final DDiagram diagram;

        SequencePostRefreshFactory(DDiagram parentDiagram, Session session, Collection<EObject> mainSemantics, Collection<EObject> createdObjects) {
            this.diagram = parentDiagram;
            this.session = session;
            this.mainSemantics.addAll(mainSemantics);
            this.createdObjects.addAll(createdObjects);
        }

        @Override
        public Command getPostCommandToExecute(TransactionalEditingDomain domain, Collection<DRepresentation> refreshedRepresentations) {
            Command result = null;
            // Flag new elements of the clicked with tool diagram -> diagram
            if (session != null && diagram != null && refreshedRepresentations.contains(diagram)) {
                final Collection<EObject> gmfDiags = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diagram);
                if (!gmfDiags.isEmpty()) {
                    CompoundCommand cc = new CompoundCommand();
                    for (final Diagram gmfDiag : Iterables.filter(gmfDiags, Diagram.class)) {
                        cc.append(new SequenceFlagAndSyncCommand(domain, gmfDiag, mainSemantics, createdObjects));
                    }
                    result = cc;
                }
            }
            return result;

        }
    }

    /**
     * Command to flag elements created during precommit representation refresh.
     */
    private final class SequenceFlagAndSyncCommand extends RecordingCommand {

        private final Collection<EObject> mainSemantics = new ArrayList<>();

        private final Collection<EObject> createdObjects = new ArrayList<>();

        private final Diagram gmfDiag;

        SequenceFlagAndSyncCommand(TransactionalEditingDomain domain, Diagram gmfDiag, Collection<EObject> mainSemantics, Collection<EObject> createdObjects) {
            super(domain, Messages.SequenceFlagAndSyncCommand_commandName);
            this.gmfDiag = gmfDiag;
            this.mainSemantics.addAll(mainSemantics);
            this.createdObjects.addAll(createdObjects);
        }

        @Override
        protected void doExecute() {
            Collection<DDiagramElement> flagPostRefresh = flagPostRefresh(mainSemantics, createdObjects);

            if (flagPostRefresh != null && Iterables.any(flagPostRefresh, LostMessageEnd.viewpointElementPredicate())) {
                CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(gmfDiag);
                canonicalSynchronizer.storeViewsToArrange(false);
                canonicalSynchronizer.synchronize();
            }

            if (mainSemantics != null) {
                mainSemantics.clear();
            }

            if (createdObjects != null) {
                createdObjects.clear();
            }
        }
    }
}
