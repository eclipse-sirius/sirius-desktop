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
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionQuery;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Refreshes the semantic ordering of a an element of a sequence diagram to reflect the current graphical ordering. This
 * command assumes that the <code>GraphicalMessageOrdering</code> and the <code>SemanticMessageOrdering</code> are up to
 * date according to the current visual (resp. semantic) order but that when they do not match, the graphical ordering
 * is the authoritative one and the semantic ordering should be changed to match it, through the appropriate use of the
 * user-specified <code>ReorderTool</code>.
 * 
 * @author pcdavid, smonnier
 */
public class SynchronizeInstanceRoleSemanticOrderingOperation extends AbstractModelChangeOperation<Void> {
    private final SequenceDDiagram sequenceDiagram;

    private final SequenceDiagram diagram;

    private final Set<InstanceRole> reordered = new LinkedHashSet<>();

    private InstanceRole instanceRole;

    private Set<InstanceRole> selection = new LinkedHashSet<>();

    /**
     * Constructor.
     * 
     * @param instanceRole
     *            the instance role to move to its new location in the semantic order.
     */
    public SynchronizeInstanceRoleSemanticOrderingOperation(InstanceRole instanceRole) {
        super(Messages.SynchronizeInstanceRoleSemanticOrderingOperation_operationName);
        this.instanceRole = Objects.requireNonNull(instanceRole);
        this.diagram = instanceRole.getDiagram();
        this.sequenceDiagram = this.diagram.getSequenceDDiagram();
    }

    /**
     * Constructor.
     * 
     * @param instanceRole
     *            the instance role to move to its new location in the semantic order.
     * @param selection
     *            additional events to reorder
     */
    public SynchronizeInstanceRoleSemanticOrderingOperation(InstanceRole instanceRole, Collection<InstanceRole> selection) {
        this(instanceRole);
        this.selection.addAll(Objects.requireNonNull(selection));
    }

    @Override
    public Void execute() {
        updateSemanticPositions();
        return null;
    }

    private void updateSemanticPositions() {
        updateSemanticPosition(instanceRole);
        for (InstanceRole selected : selection) {
            updateSemanticPosition(selected);
        }
    }

    private void updateSemanticPosition(InstanceRole instanceRoleToUpdate) {
        DDiagramElement dde = resolveDiagramElement(instanceRoleToUpdate);
        if (dde == null || reordered.contains(instanceRoleToUpdate)) {
            return;
        }

        InstanceRoleReorderTool reorderTool = findReorderTool(dde);
        if (reorderTool == null) {
            return;
        }

        EObject semanticElement = dde.getTarget();
        List<EObject> rolesBySemanticOrder = sequenceDiagram.getInstanceRoleSemanticOrdering().getSemanticInstanceRoles();
        List<EObject> rolesByGraphicalOrder = getSemanticInstanceRolesByGraphicalOrder();

        // The semantic ordering contains the state before the move
        EObject predecessorBefore = findEndPredecessor(semanticElement, rolesBySemanticOrder);

        // The graphical ordering contains the state after
        EObject predecessorAfter = findEndPredecessor(semanticElement, rolesByGraphicalOrder);

        if (!rolesBySemanticOrder.isEmpty() && !Objects.equals(predecessorBefore, predecessorAfter) || !Iterables.elementsEqual(rolesBySemanticOrder, rolesByGraphicalOrder)) {
            applySemanticReordering(semanticElement, predecessorBefore, predecessorAfter, reorderTool);
            reordered.add(instanceRoleToUpdate);

            new RefreshSemanticOrderingsOperation(sequenceDiagram).execute();
        }
    }

    private List<EObject> getSemanticInstanceRolesByGraphicalOrder() {
        Iterable<Diagram> diagramViews = Iterables.filter(ISequenceElementAccessor.getViewsForSemanticElement(sequenceDiagram, sequenceDiagram.getTarget()), Diagram.class);
        if (!Iterables.isEmpty(diagramViews)) {
            Option<SequenceDiagram> seqDiag = ISequenceElementAccessor.getSequenceDiagram(diagramViews.iterator().next());
            if (seqDiag.some()) {
                return Lists.newArrayList(Iterables.transform(seqDiag.get().getSortedInstanceRole(), ISequenceElement.SEMANTIC_TARGET));
            }
        }
        return Collections.emptyList();
    }

    private DDiagramElement resolveDiagramElement(InstanceRole instanceRoleToUpdate) {
        EObject element = instanceRoleToUpdate.getNotationView().getElement();
        if (element instanceof DDiagramElement) {
            return (DDiagramElement) element;
        }
        throw new RuntimeException(MessageFormat.format(Messages.SynchronizeInstanceRoleSemanticOrderingOperation_invalidInstanceRoleContext, instanceRoleToUpdate));
    }

    private EObject findEndPredecessor(EObject semanticElement, List<EObject> semanticInstanceRoles) {
        EObject result = null;
        for (EObject current : semanticInstanceRoles) {
            if (semanticElement == current) {
                break;
            } else {
                result = current;
            }
        }
        return result;
    }

    private void applySemanticReordering(EObject semanticElement, EObject predecessorBefore, EObject predecessorAfter, InstanceRoleReorderTool reorderTool) {
        SiriusCommand cmd = ToolCommandBuilder.buildInstanceRoleReorderCommand(sequenceDiagram, reorderTool, semanticElement, predecessorBefore, predecessorAfter);
        cmd.execute();
    }

    private InstanceRoleReorderTool findReorderTool(DDiagramElement diagramElement) {
        if (diagramElement != null) {
            Collection<AbstractToolDescription> allTools;
            Session session = SessionManager.INSTANCE.getSession(diagramElement);
            if (session != null) {
                allTools = new DiagramComponentizationManager().getAllTools(session.getSelectedViewpoints(false), sequenceDiagram.getDescription());
            } else {
                allTools = new DiagramDescriptionQuery(sequenceDiagram.getDescription()).getAllTools();
            }
            // TODO Consider layers activation
            for (InstanceRoleReorderTool toolDesc : Iterables.filter(allTools, InstanceRoleReorderTool.class)) {
                if (toolDesc.getMappings().contains(diagramElement.getMapping())) {
                    return toolDesc;
                }
            }
        }
        return null;
    }

}
