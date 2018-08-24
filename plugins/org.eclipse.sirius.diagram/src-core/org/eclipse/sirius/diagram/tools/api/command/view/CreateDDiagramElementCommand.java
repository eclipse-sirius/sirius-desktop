/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.tools.api.command.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;

/**
 * A command to create a viewpoint diagram element.
 * 
 * @author mchauvin
 */
public class CreateDDiagramElementCommand extends RecordingCommand {

    private DragAndDropTarget container;

    private ModelAccessor accessor;

    private IInterpreter interpreter;

    private DNodeCandidate nodeCandidate;

    private DEdgeCandidate edgeCandidate;

    /**
     * Create new command able to create a new node diagram element.
     * 
     * @param domain
     *            the editing domain
     * @param semantic
     *            the semantic element represented
     * @param mapping
     *            the mapping to use
     * @param container
     *            the container in which to insert the created node
     */
    public CreateDDiagramElementCommand(final TransactionalEditingDomain domain, final EObject semantic, final AbstractNodeMapping mapping, final DragAndDropTarget container) {
        this(domain, semantic);
        this.container = container;
        nodeCandidate = new DNodeCandidate(mapping, semantic, container, RefreshIdsHolder.getOrCreateHolder(getParentDiagram()));
    }

    /**
     * Create a new command able a new edge diagram element.
     * 
     * @param domain
     *            the editing domain
     * @param semantic
     *            the semantic element represented
     * @param mapping
     *            the mapping to use
     * @param source
     *            the edge source
     * @param target
     *            the edge target
     */
    public CreateDDiagramElementCommand(final TransactionalEditingDomain domain, final EObject semantic, final EdgeMapping mapping, final EdgeTarget source, final EdgeTarget target) {
        this(domain, semantic);
        /* store source as container to retrieve later parent diagram */
        this.container = (DragAndDropTarget) source;
        edgeCandidate = new DEdgeCandidate(mapping, semantic, source, target, RefreshIdsHolder.getOrCreateHolder(getParentDiagram()));
    }

    private CreateDDiagramElementCommand(final TransactionalEditingDomain domain, final EObject semantic) {
        super(domain);
        accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semantic);
        interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {

        final DDiagram diagram = getParentDiagram();

        Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
        final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, diagram.getDescription(), accessor);
        diagramSync.setDiagram((DSemanticDiagram) diagram);
        final DDiagramElementSynchronizer elementSync = diagramSync.getElementSynchronizer();

        if (nodeCandidate != null) {
            elementSync.createNewNode(mappingManager, nodeCandidate, isBorder());
        } else if (edgeCandidate != null) {
            /* maps for decorations */
            final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
            final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

            /* create the mapping to edge targets map */
            final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = elementSync.computeMappingsToEdgeTargets(session.getSelectedViewpoints(false));
            new DecorationHelperInternal(getParentDiagram(), interpreter, accessor).computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);
            elementSync.createNewEdge(mappingManager, edgeCandidate, mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
        }

    }

    private boolean isBorder() {
        return false;
    }

    private DDiagram getParentDiagram() {
        DDiagram parentDiagram = null;
        if (container instanceof DDiagram) {
            parentDiagram = (DDiagram) container;
        } else if (container instanceof DDiagramElement) {
            parentDiagram = ((DDiagramElement) container).getParentDiagram();
        }
        return parentDiagram;
    }
}
