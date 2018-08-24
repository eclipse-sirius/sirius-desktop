/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.helper.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICreationTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.api.refresh.BestMappingGetter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * This task create {@link org.eclipse.sirius.viewpoint.DNode} and/or
 * {@link org.eclipse.sirius.viewpoint.DNodeListElement}.
 * 
 * @author mchauvin
 * @author cbrun
 */
public class CreateDNodeTask extends AbstractCommandTask implements ICreationTask {

    /** The tool that describes how to create the node. */
    private final NodeCreationDescription tool;

    /** The extended package. */
    private final ModelAccessor modelAccessor;

    /** A candidate container. */
    private DragAndDropTarget containerView;

    /** The created nodes or node list elements. */
    private Collection<AbstractDNode> createdAbstractDNodes = new ArrayList<AbstractDNode>();

    /** The command */
    private final DCommand cmd;

    /**
     * Create a new {@link CreateDNodeTask}.
     * 
     * @param tool
     *            the tool that describes how to create the node.
     * @param cmd
     *            the command that creates and initializes semantic elements.
     * @param modelAccessor
     *            the extended package.
     * @param containerView
     *            the containerView.
     */
    public CreateDNodeTask(final NodeCreationDescription tool, final DCommand cmd, final ModelAccessor modelAccessor, final DragAndDropTarget containerView) {
        this.tool = tool;
        this.cmd = cmd;
        this.modelAccessor = modelAccessor;
        this.containerView = containerView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        if (containerView instanceof DSemanticDecorator) {
            DSemanticDecorator containerViewDSemanticDecorator = (DSemanticDecorator) containerView;
            EObjectQuery eObjectQuery = new EObjectQuery(containerViewDSemanticDecorator);
            Session session = eObjectQuery.getSession();
            DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) eObjectQuery.getParentDiagram().get();
            createNodes(session, dSemanticDiagram, containerViewDSemanticDecorator);
        }
    }

    private void createNodes(Session session, DSemanticDiagram dSemanticDiagram, DSemanticDecorator containerViewDSemanticDecorator) {
        IInterpreter interpreter = session.getInterpreter();
        DDiagramElementSynchronizer dDiagramElementSynchronizer = new DDiagramElementSynchronizer(dSemanticDiagram, interpreter, modelAccessor);
        Collection<EObject> createdObjects = cmd.getCreatedObjects();
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, dSemanticDiagram);
        for (EObject semanticElt : createdObjects) {
            BestMappingGetter bestMappingGetter = new BestMappingGetter(containerViewDSemanticDecorator, semanticElt);
            NodeMapping bestMapping = bestMappingGetter.getBestNodeMapping(tool.getNodeMappings());
            if (bestMapping != null) {
                DNodeCandidate abstractDNodeCandidate = new DNodeCandidate(bestMapping, semanticElt, containerView, RefreshIdsHolder.getOrCreateHolder(dSemanticDiagram));
                AbstractDNode createdAbstractDNode = dDiagramElementSynchronizer.createNewNode(mappingManager, abstractDNodeCandidate,
                        bestMapping.eContainingFeature() == DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS);
                if (createdAbstractDNode != null) {
                    AbstractNodeMappingSpecOperations.createBorderingNodes(bestMapping, semanticElt, createdAbstractDNode, Collections.emptyList(), dSemanticDiagram);
                    if (isAutoPinOnCreateEnabled()) {
                        createdAbstractDNode.getArrangeConstraints().add(ArrangeConstraint.KEEP_LOCATION);
                        createdAbstractDNode.getArrangeConstraints().add(ArrangeConstraint.KEEP_RATIO);
                        createdAbstractDNode.getArrangeConstraints().add(ArrangeConstraint.KEEP_SIZE);
                    }
                    AbstractNodeMappingSpecOperations.setInitialVisibility(createdAbstractDNode, dSemanticDiagram, session);
                    createdAbstractDNodes.add(createdAbstractDNode);
                }
            }
        }
    }

    @Override
    public String getLabel() {
        return Messages.CreateDNodeTask_label;
    }

    private boolean isAutoPinOnCreateEnabled() {
        return Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_AUTO_PIN_ON_CREATE.name(), false, null);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICreationTask#getCreatedElements()
     */
    @Override
    public Collection<EObject> getCreatedElements() {
        // not applicable
        return Collections.emptySet();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getAffectedElements()
     */
    @Override
    public Collection<EObject> getAffectedElements() {
        // not applicable
        return Collections.emptySet();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getCreatedReferences()
     */
    @Override
    public Collection<EObject> getCreatedReferences() {
        // not applicable
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICreationTask#getCreatedRepresentationElements()
     */
    @Override
    public Collection<DRepresentationElement> getCreatedRepresentationElements() {
        return new ArrayList<DRepresentationElement>(createdAbstractDNodes);
    }
}
