/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES.
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICreationTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.diagram.tools.api.refresh.BestMappingGetter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;

/**
 * Create a {@link DEdge}.
 * 
 * @author cbrun
 */
public class CreateDEdgeTask extends AbstractCommandTask implements ICreationTask {

    /** The tool. */
    private EdgeCreationDescription tool;

    /** The command. */
    private DCommand cmd;

    /** The extended package. */
    private ModelAccessor modelAccessor;

    /** the source node. */
    private EdgeTarget sourceView;

    /** the target node. */
    private EdgeTarget targetView;

    /** the created edges. */
    private Collection<DEdge> createdDEdges = new ArrayList<DEdge>();

    /**
     * Default constructor.
     * 
     * @param tool
     *            the tool
     * @param cmd
     *            the command
     * @param modelAccessor
     *            the extended package
     * @param sourceView
     *            the sourceView
     * @param targetView
     *            the targetView
     */
    public CreateDEdgeTask(final EdgeCreationDescription tool, final DCommand cmd, final ModelAccessor modelAccessor, final EdgeTarget sourceView, final EdgeTarget targetView) {
        this.tool = tool;
        this.cmd = cmd;
        this.modelAccessor = modelAccessor;
        this.sourceView = sourceView;
        this.targetView = targetView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {

        EObjectQuery eObjectQuery = new EObjectQuery(sourceView);
        DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) eObjectQuery.getParentDiagram().get();
        Session session = eObjectQuery.getSession();
        IInterpreter interpreter = session.getInterpreter();

        DDiagramSynchronizer dDiagramSynchronizer = new DDiagramSynchronizer(interpreter, dSemanticDiagram.getDescription(), modelAccessor);
        dDiagramSynchronizer.setDiagram(dSemanticDiagram);
        dDiagramSynchronizer.setTool(true);
        DDiagramElementSynchronizer dDiagramElementSynchronizer = dDiagramSynchronizer.getElementSynchronizer();
        /* maps for decorations */
        Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
        Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, dSemanticDiagram);
        /* create the mapping to edge targets map */
        Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = dDiagramElementSynchronizer.computeMappingsToEdgeTargets(session.getSelectedViewpoints(false));
        new DecorationHelperInternal(dSemanticDiagram, interpreter, modelAccessor).computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);

        // Difference with CreateDNodeTask/CreateContainerTask: candidate creation is not explicitly done by the
        // CreateDEdgeTask but delegated to DDiagramSynchronizer created for that.
        Map<EdgeMapping, Collection<EObject>> map = new LinkedHashMap<>();
        for (EObject semanticElt : getAllSemantics()) {
            BestMappingGetter bestMappingGetter = new BestMappingGetter(sourceView, targetView, semanticElt);
            EdgeMapping bestEdgeMapping = bestMappingGetter.getBestEdgeMapping(tool.getEdgeMappings());

            if (bestEdgeMapping != null) {
                Collection<EObject> collection = map.get(bestEdgeMapping);
                if (collection == null) {
                    collection = new LinkedHashSet<>();
                    map.put(bestEdgeMapping, collection);
                }
                collection.add(semanticElt);
            }
        }

        for (Entry<EdgeMapping, Collection<EObject>> entry : map.entrySet()) {
            EdgeMapping mapping = entry.getKey();
            Collection<EObject> expectedSemanticElements = entry.getValue();
            Collection<DEdge> createdDEdgeOption = dDiagramSynchronizer.createEdgeMapping(mappingManager, mappingsToEdgeTargets, mapping, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration,
                    expectedSemanticElements);
            if (!createdDEdgeOption.isEmpty()) {
                createdDEdges.addAll(createdDEdgeOption);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.CreateDEdgeTask_label;
    }

    private Collection<EObject> getAllSemantics() {
        final Collection<EObject> semantics = new LinkedHashSet<EObject>(cmd.getCreatedObjects());
        semantics.addAll(cmd.getAffectedElements());
        return semantics;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICreationTask#getCreatedRepresentationElements()
     */
    @Override
    public Collection<DRepresentationElement> getCreatedRepresentationElements() {
        return new ArrayList<DRepresentationElement>(createdDEdges);
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
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getAffectedElements()
     */
    @Override
    public Collection<EObject> getAffectedElements() {
        // not applicable
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.helper.task.IModificationTask#getCreatedReferences()
     */
    @Override
    public Collection<EObject> getCreatedReferences() {
        // not applicable
        return Collections.emptySet();
    }

}
