/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.tools.api.refresh.BestMappingGetter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A Task to create {@link org.eclipse.sirius.diagram.DDiagramElementContainer}.
 * 
 * @author mchauvin
 */
public class CreateContainerTask extends AbstractCommandTask implements ICreationTask {

    private final DCommand cmd;

    private final ContainerCreationDescription tool;

    private final ModelAccessor modelAccessor;

    private DragAndDropTarget containerView;

    /** The created containers. */
    private Collection<AbstractDNode> createdAbstractDNodes = new ArrayList<AbstractDNode>();

    /**
     * Default constructor.
     * 
     * @param tool
     *            the tool
     * @param cmd
     *            the command
     * @param modelAccessor
     *            the {@link ModelAccessor}
     * @param containerView
     *            the container view
     */
    public CreateContainerTask(final ContainerCreationDescription tool, final DCommand cmd, final ModelAccessor modelAccessor, final DragAndDropTarget containerView) {
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
        EObjectQuery eObjectQuery = new EObjectQuery(containerView);
        Session session = eObjectQuery.getSession();
        Option<DDiagram> parentDiagramOption = eObjectQuery.getParentDiagram();
        if (parentDiagramOption.some() && parentDiagramOption.get() instanceof DSemanticDiagram) {
            DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) parentDiagramOption.get();
            createContainers(session, dSemanticDiagram);
        }
    }

    private void createContainers(Session session, DSemanticDiagram dSemanticDiagram) {
        IInterpreter interpreter = session.getInterpreter();
        DDiagramElementSynchronizer dDiagramElementSynchronizer = new DDiagramElementSynchronizer(dSemanticDiagram, interpreter, modelAccessor);
        Collection<EObject> createdObjects = cmd.getCreatedObjects();
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, dSemanticDiagram);
        for (EObject semanticElt : createdObjects) {
            BestMappingGetter bestMappingGetter = new BestMappingGetter((DSemanticDecorator) containerView, semanticElt);
            ContainerMapping bestMapping = bestMappingGetter.getBestContainerMapping(tool.getContainerMappings());
            if (bestMapping != null) {
                DNodeCandidate abstractDNodeCandidate = new DNodeCandidate(bestMapping, semanticElt, containerView, RefreshIdsHolder.getOrCreateHolder(dSemanticDiagram));
                AbstractDNode createdAbstractDNode = dDiagramElementSynchronizer.createNewNode(mappingManager, abstractDNodeCandidate, false);
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

    private boolean isAutoPinOnCreateEnabled() {
        return Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_AUTO_PIN_ON_CREATE.name(), false, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.CreateContainerTask_label;
    }

    /**
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
     * {@inheritDoc}
     */
    @Override
    public Collection<DRepresentationElement> getCreatedRepresentationElements() {
        return new ArrayList<DRepresentationElement>(createdAbstractDNodes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EObject> getAffectedElements() {
        // not applicable
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EObject> getCreatedReferences() {
        // not applicable
        return Collections.emptySet();
    }
}
