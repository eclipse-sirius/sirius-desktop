/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation;

import java.util.Collection;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshGraphicalOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.RefreshSemanticOrderingsOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SynchronizeISequenceEventsSemanticOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.SynchronizeInstanceRoleSemanticOrderingOperation;
import org.eclipse.sirius.diagram.sequence.business.internal.tool.ToolCommandBuilder;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceGraphicalHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewLocationHint;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Helper class to share code between different kinds of edit parts when inheritance is not possible or inconvenient.
 * 
 * @author pcdavid, smonnier, mporhel
 */
public final class SequenceEditPartsOperations {
    private SequenceEditPartsOperations() {
        // Prevent instantiation.
    }

    /**
     * Add graphical and semantic synchronize commands to do a full refresh of the semantic and graphical orderings.
     * 
     * @param self
     *            the edit part which created the base command.
     * @param cc
     *            the compound command to complete.
     */
    public static void appendFullRefresh(IGraphicalEditPart self, CompoundCommand cc) {
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(self);
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        TransactionalEditingDomain domain = self.getEditingDomain();
        cc.add(new ICommandProxy(CommandFactory.createICommand(domain, new RefreshGraphicalOrderingOperation(sequenceDiagram))));
        cc.add(new ICommandProxy(CommandFactory.createICommand(domain, new RefreshSemanticOrderingsOperation(sequenceDDiagram))));
    }

    /**
     * Add graphical and semantic synchronize commands to do a full refresh of the semantic and graphical orderings.
     * 
     * @param self
     *            the edit part which created the base command.
     * @param ctc
     *            the CompositeTransactionalCommand to complete.
     */
    public static void appendFullRefresh(IGraphicalEditPart self, CompositeTransactionalCommand ctc) {
        SequenceEditPartsOperations.addRefreshGraphicalOrderingCommand(ctc, self);
        SequenceEditPartsOperations.addRefreshSemanticOrderingCommand(ctc, self);
    }

    /**
     * Append a command to refresh the graphical ordering of the sequence diagram containing the specified edit part.
     * 
     * @param cc
     *            the target CompositeTransactionalCommand.
     * @param self
     *            an edit part of the sequence diagram to refresh.
     */
    public static void addRefreshGraphicalOrderingCommand(CompositeTransactionalCommand cc, IGraphicalEditPart self) {
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(self);
        TransactionalEditingDomain domain = cc.getEditingDomain();
        cc.compose(CommandFactory.createICommand(domain, new RefreshGraphicalOrderingOperation(sequenceDiagram)));
    }

    /**
     * Append a command to refresh the semantic ordering of a sequence diagram.
     * 
     * @param cc
     *            the target CompositeTransactionalCommand.
     * @param self
     *            an edit part of the sequence diagram to refresh.
     */
    public static void addRefreshSemanticOrderingCommand(CompositeTransactionalCommand cc, IGraphicalEditPart self) {
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(self);
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        TransactionalEditingDomain domain = cc.getEditingDomain();
        cc.compose(CommandFactory.createICommand(domain, new RefreshSemanticOrderingsOperation(sequenceDDiagram)));
    }

    /**
     * Append a command to synchronize the semantic ordering of the specified event.
     * 
     * @param cc
     *            the target CompositeTransactionalCommand.
     * @param event
     *            the event which has moved graphically and whose semantic ordering should be updated to match the new
     *            position.
     * @param set
     */
    public static void addSynchronizeSemanticOrderingCommand(CompositeTransactionalCommand cc, ISequenceEvent event) {
        cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new SynchronizeISequenceEventsSemanticOrderingOperation(event)));
    }

    /**
     * Append a command to synchronize the semantic ordering of the specified event.
     * 
     * @param cc
     *            the target CompositeTransactionalCommand.
     * @param event
     *            the event which has moved graphically and whose semantic ordering should be updated to match the new
     *            position.
     * @param selection
     *            additionnal events to reorder.
     */
    public static void addSynchronizeSemanticOrderingCommand(CompositeTransactionalCommand cc, ISequenceEvent event, Collection<ISequenceEvent> selection) {
        cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new SynchronizeISequenceEventsSemanticOrderingOperation(event, selection)));
    }

    /**
     * Append a command to synchronize the semantic ordering of the specified event.
     * 
     * @param cc
     *            the target CompositeTransactionalCommand.
     * @param instanceRole
     *            the instance role which has moved graphically and whose semantic ordering should be updated to match
     *            the new position.
     * @param set
     */
    public static void addSynchronizeSemanticOrderingCommand(CompositeTransactionalCommand cc, InstanceRole instanceRole) {
        cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new SynchronizeInstanceRoleSemanticOrderingOperation(instanceRole)));
    }

    /**
     * Append a command to synchronize the semantic ordering of the specified event.
     * 
     * @param cc
     *            the target CompositeTransactionalCommand.
     * @param instanceRole
     *            the instance role which has moved graphically and whose semantic ordering should be updated to match
     *            the new position.
     * @param selection
     *            additionnal instance roles to reorder.
     */
    public static void addSynchronizeSemanticOrderingCommand(CompositeTransactionalCommand cc, InstanceRole instanceRole, Collection<InstanceRole> selection) {
        cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new SynchronizeInstanceRoleSemanticOrderingOperation(instanceRole, selection)));
    }

    /**
     * Registers an edit part in the viewer's registry using the specified <code>DDiagramElement</code> of
     * <code>DDiagram</code> as key.
     * 
     * @param self
     *            the edit part to register.
     * @param element
     *            the key to use.
     */
    public static void registerDiagramElement(IGraphicalEditPart self, EObject element) {
        self.getViewer().getEditPartRegistry().put(element, self);
    }

    /**
     * Unregisters an edit part in the viewer's registry using the specified <code>DDiagramElement</code> of
     * <code>DDiagram</code> as key.
     * 
     * @param self
     *            the edit part to unregister.
     * @param element
     *            the key of the registry entry to remove.
     */
    public static void unregisterDiagramElement(IGraphicalEditPart self, EObject element) {
        Map<Object, Object> registry = self.getViewer().getEditPartRegistry();
        if (registry.get(element) == self) {
            registry.remove(element);
        }
    }

    /**
     * Configures a bordered node to appear on the given side and at the given offset of its parent part.
     * 
     * @param self
     *            the bordered node.
     * @param side
     *            the side of the parent on which the node should be.
     * @param offset
     *            the offset (relative to the parent) at which the node should be placed.
     */
    public static void setBorderItemLocation(AbstractBorderItemEditPart self, int side, Dimension offset) {
        IBorderItemLocator borderItemLocator = self.getBorderItemLocator();
        if (borderItemLocator instanceof DBorderItemLocator) {
            DBorderItemLocator dbil = (DBorderItemLocator) borderItemLocator;
            dbil.setCurrentSideOfParent(side);
            dbil.setBorderItemOffset(offset);
        }
    }

    /**
     * Shared implementation of most of SiriusBaseItemSemanticEditPolicy.buildCreateEdgeCommand() for use in
     * ExecutionSemanticEditPolicy and InstanceRoleSemanticEditPolicy.
     * 
     * @param self
     *            the edit part.
     * @param result
     *            the command to modify.
     * @param request
     *            the original request.
     * @param source
     *            the source of the edge to create.
     * @param target
     *            the target of the edge to create.
     * @param edgeCreationDescription
     *            the tool to use to create the edge.
     * @param cmdFactoryProvider
     *            the command factory provider to use to build a command from the tool.
     */
    public static void buildCreateEdgeCommand(IGraphicalEditPart self, CompoundCommand result, CreateConnectionRequest request, EdgeTarget source, EdgeTarget target,
            EdgeCreationDescription edgeCreationDescription, IDiagramCommandFactoryProvider cmdFactoryProvider) {
        org.eclipse.emf.common.command.Command emfCommand;
        TransactionalEditingDomain domain = self.getEditingDomain();
        if (edgeCreationDescription instanceof MessageCreationTool && ((DDiagramElement) source).getParentDiagram() instanceof SequenceDDiagram) {
            Point sourceLocation = request.getLocation().getCopy();
            Point targetLocation = request.getLocation().getCopy();
            GraphicalHelper.screen2logical(sourceLocation, self);
            GraphicalHelper.screen2logical(targetLocation, self);

            Point srcLocationHint = SequenceEditPartsOperations.getConnectionSourceLocation(request, self);
            Point tgtLocationHint = SequenceEditPartsOperations.getConnectionTargetLocation(request, self);

            if (srcLocationHint != null) {
                sourceLocation = srcLocationHint;
            }

            if (tgtLocationHint != null) {
                targetLocation = tgtLocationHint;
            }

            // Avoid deferred message, accept only messageToSelf
            if (source != target) {
                targetLocation = sourceLocation;
            }

            SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(self);
            SequenceDDiagram diagram = sequenceDiagram.getSequenceDDiagram();
            EventEnd startingEndBefore = SequenceGraphicalHelper.getEndBefore(diagram, sourceLocation.y);
            EventEnd finishingEndBefore = SequenceGraphicalHelper.getEndBefore(diagram, targetLocation.y);
            emfCommand = ToolCommandBuilder.buildCreateMessageCommand(source, target, (MessageCreationTool) edgeCreationDescription, startingEndBefore, finishingEndBefore);

            org.eclipse.emf.common.command.CompoundCommand cc = new org.eclipse.emf.common.command.CompoundCommand();
            cc.append(emfCommand);
            cc.append(CommandFactory.createRecordingCommand(domain, new RefreshSemanticOrderingsOperation(diagram)));
            emfCommand = cc;
        } else {
            emfCommand = cmdFactoryProvider.getCommandFactory(domain).buildCreateEdgeCommandFromTool(source, target, edgeCreationDescription);
        }
        result.add(new ICommandProxy(new GMFCommandWrapper(domain, emfCommand)));
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#getVerticalRange()}.
     * 
     * @param self
     *            the event.
     * @return the maximal range occupied by children of the event.
     */
    public static Range getVerticalRange(ISequenceEventEditPart self) {
        ISequenceEvent iSequenceEvent = self != null ? self.getISequenceEvent() : null;
        return iSequenceEvent != null ? iSequenceEvent.getVerticalRange() : Range.emptyRange();
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange()}.
     * 
     * @param self
     *            the event.
     * @param newRange
     *            the new range
     */
    public static void setVerticalRange(ISequenceEventEditPart self, Range newRange) {
        ISequenceEvent iSequenceEvent = self != null ? self.getISequenceEvent() : null;
        if (iSequenceEvent != null) {
            iSequenceEvent.setVerticalRange(newRange);
        }
    }

    /**
     * Return the source connection anchor location from view location hint.
     * 
     * @param request
     *            the current request.
     * @param self
     *            an edit part to compute logical location.
     * @return the source location.
     */
    public static Point getConnectionSourceLocation(CreateConnectionRequest request, IGraphicalEditPart self) {
        return SequenceEditPartsOperations.getConnectionLocation(self, ViewLocationHint.SOURCE_ANCHOR_LOCATION);
    }

    /**
     * Return the target connection anchor location from view location hint.
     * 
     * @param request
     *            the current request.
     * @param self
     *            an edit part to compute logical location.
     * @return the target location.
     */
    public static Point getConnectionTargetLocation(CreateConnectionRequest request, IGraphicalEditPart self) {
        return SequenceEditPartsOperations.getConnectionLocation(self, ViewLocationHint.TARGET_ANCHOR_LOCATION);
    }

    private static Point getConnectionLocation(IGraphicalEditPart self, String key) {
        Object hint = ViewLocationHint.getInstance().getData(key);
        if (hint instanceof Point) {
            Point location = ((Point) hint).getCopy();
            GraphicalHelper.screen2logical(location, self);
            return location;
        } else {
            return null;
        }
    }
}
