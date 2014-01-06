/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.tool.internal.action.repair;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.repair.IRepairParticipant;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.migration.resource.session.commands.MigrationCommandExecutor;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.flag.SequenceDiagramAbsoluteBoundsFlagger;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.collect.Iterables;

/**
 * Sequence diagram repair participant.
 * 
 * @author fbarbin
 * 
 */
public class SequenceDiagramRepairParticipant implements IRepairParticipant {

    /**
     * {@inheritDoc}
     */
    public void repairStarted() {
        // nothing

    }

    /**
     * {@inheritDoc}
     */
    public void repairCompeleted() {
        // nothing

    }

    /**
     * {@inheritDoc}
     */
    public void restoreModelElementState(DView view, IProgressMonitor monitor) {
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(view.eResource());

        new MigrationCommandExecutor().execute(editingDomain, migrateModel(view.eResource(), editingDomain));

    }

    /**
     * {@inheritDoc}
     */
    public void postRefreshOperations(TransactionalEditingDomain domain, Resource resource) {
        // nothing

    }

    /**
     * {@inheritDoc}
     */
    public void saveModelElementState(DView view, IProgressMonitor monitor) {
        // nothing

    }

    /**
     * Scan {@link DRepresentationContainer}s for sequence diagrams to repair.
     * 
     * @param model
     *            the current session model
     * @param domain
     *            the current {@link TransactionalEditingDomain}
     * @return a {@link CompoundCommand} of sequence diagrams reparing commands
     *         in each {@link DRepresentationContainer}
     */
    private Command migrateModel(Resource model, TransactionalEditingDomain domain) {
        CompoundCommand cc = new CompoundCommand("Repair Sequence Diagram");
        EObject eObject = model.getContents().get(0);
        if (eObject instanceof DAnalysis) {
            DAnalysis dAnalysis = (DAnalysis) eObject;
            for (DRepresentationContainer container : Iterables.filter(dAnalysis.getOwnedViews(), DRepresentationContainer.class)) {
                CompoundCommand migrateRepresentationContainer = migrateRepresentationContainer(container, domain);
                if (!migrateRepresentationContainer.getCommandList().isEmpty()) {
                    cc.append(migrateRepresentationContainer);
                }
            }
        }
        return cc;
    }

    /**
     * Add a {@link SequenceInstanceRoleRepairCommand} and a
     * {@link FlagSequenceEventsCommand} for each {@link SequenceDDiagram}
     * founds in the {@link DRepresentationContainer}.
     * 
     * @param container
     *            a {@link DRepresentationContainer} to scan for
     *            {@link SequenceDDiagram}
     * @param domain
     *            the current {@link TransactionalEditingDomain}
     * @return a {@link CompoundCommand} of sequence diagrams reparing commands
     */
    private CompoundCommand migrateRepresentationContainer(DRepresentationContainer container, TransactionalEditingDomain domain) {
        CompoundCommand cc = new CompoundCommand("Repair Sequence Diagram");
        for (SequenceDDiagram seqDDiag : Iterables.filter(container.getAllRepresentations(), SequenceDDiagram.class)) {
            final Diagram gmfDiagram = SiriusGMFHelper.getGmfDiagram(seqDDiag);
            Option<SequenceDiagram> iSequenceDiagram = ISequenceElementAccessor.getSequenceDiagram(gmfDiagram);
            if (iSequenceDiagram.some()) {
                cc.append(new SequenceInstanceRoleRepairCommand(iSequenceDiagram.get(), domain));
                cc.append(new FlagSequenceEventsCommand(domain, iSequenceDiagram.get()));
            }
        }
        return cc;
    }

    /**
     * {@inheritDoc}
     */
    public void endRepairOnView() {
    }

    /**
     * {@inheritDoc}
     */
    public void startRepairOnView(Session session, DView view) {
    }

    /**
     * Command to fix sequence diagram instance role with gmf size at -1 for
     * height and/or width.
     * 
     * @author mporhel
     * 
     */
    private class SequenceInstanceRoleRepairCommand extends RecordingCommand {

        private SequenceDiagram sequenceDiagram;

        /**
         * Constructor.
         * 
         * @param sequenceDiagram
         *            the sequence diagram to fix.
         * @param editingDomain
         *            the editing domain.
         */
        public SequenceInstanceRoleRepairCommand(SequenceDiagram sequenceDiagram, TransactionalEditingDomain editingDomain) {
            super(editingDomain);
            this.sequenceDiagram = sequenceDiagram;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void doExecute() {
            fixInstanceRoleBounds();
        }

        private void fixInstanceRoleBounds() {
            for (InstanceRole ir : sequenceDiagram.getAllInstanceRoles()) {
                Node notationNode = ir.getNotationNode();

                if (notationNode != null && notationNode.getLayoutConstraint() instanceof Size && notationNode.getElement() instanceof DNode) {
                    Size size = (Size) notationNode.getLayoutConstraint();
                    DNode dNode = (DNode) notationNode.getElement();
                    Dimension dim = new DNodeQuery(dNode).getDefaultDimension();

                    // Try to apply default dimension if -1
                    if (size.getWidth() == -1 && dim != null) {
                        size.setWidth(dim.width);
                    }

                    // Try to apply default dimension if -1
                    if (size.getHeight() == -1 && dim != null) {
                        size.setHeight(dim.height);
                    }
                }
            }
        }
    }

    /**
     * A command to flag sequence events with their absolute bounds
     * 
     * @author mporhel
     * 
     */
    private class FlagSequenceEventsCommand extends RecordingCommand {
        /**
         * The diagram to deal with.
         */
        SequenceDiagram diagram;

        public FlagSequenceEventsCommand(TransactionalEditingDomain domain, SequenceDiagram diagram) {
            super(domain, "Remove hideNotification node");
            this.diagram = diagram;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
         */
        @Override
        protected void doExecute() {
            if (diagram != null) {
                new SequenceDiagramAbsoluteBoundsFlagger(diagram).flag();
            }
        }
    }

}
