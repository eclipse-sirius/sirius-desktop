/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceMessageEditPolicy;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Queries on GEF and GMF requests.
 * 
 * @author pcdavid
 */
public class RequestQuery extends org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery {

    /**
     * Constaint to check if request if from another indirect request.
     */
    public static final String IS_MOVED_BY_PARENT_EXECUTION = "isMovedByParentExecution"; //$NON-NLS-1$

    /**
     * Constructor.
     * 
     * @param request
     *            the request to query.
     */
    public RequestQuery(Request request) {
        super(request);
    }

    /**
     * Determines the final bounds for this execution (in logical coordinates)
     * if we accept the specified request. Uses Draw2D information to get the
     * current bounds.
     * 
     * @param self
     *            {@link AbstractNodeEvent} in move/resize
     * 
     * @return final bounds of self
     */
    public Rectangle getFinalBounds(ExecutionEditPart self) {
        Rectangle bounds = self.getFigure().getBounds().getCopy();
        return getLogicalTransformedRectangle(bounds);
    }

    /**
     * Get the list of AbstractNodeEvents from the current GroupRequest.
     * 
     * @return the list of AbstractNodeEvents
     */
    public Set<AbstractNodeEvent> getAbstractNodeEvent() {
        List<IGraphicalEditPart> editParts = getEditParts();

        if (editParts.isEmpty()) {
            return Collections.emptySet();
        }

        Set<AbstractNodeEvent> result = Sets.newHashSet();
        for (IGraphicalEditPart part : editParts) {
            Option<AbstractNodeEvent> execution = ISequenceElementAccessor.getAbstractNodeEvent(part.getNotationView());
            if (execution.some()) {
                result.add(execution.get());
            }
        }
        return result;
    }

    /**
     * Get the list of Executions from the current GroupRequest.
     * 
     * @return the list of Executions
     */
    public Set<Execution> getExecutions() {
        List<IGraphicalEditPart> editParts = getEditParts();

        if (editParts.isEmpty()) {
            return Collections.emptySet();
        }

        Set<Execution> result = Sets.newHashSet();
        for (IGraphicalEditPart part : editParts) {
            Option<Execution> execution = ISequenceElementAccessor.getExecution(part.getNotationView());
            if (execution.some()) {
                result.add(execution.get());
            }
        }
        return result;
    }

    /**
     * Get the list of {@link ISequenceEvent} from the current GroupRequest.
     * 
     * @return the list of Executions
     */
    public Set<ISequenceEvent> getISequenceEvents() {
        List<IGraphicalEditPart> editParts = getEditParts();

        if (editParts.isEmpty()) {
            return Collections.emptySet();
        }

        Set<ISequenceEvent> result = Sets.newHashSet();
        for (IGraphicalEditPart part : editParts) {
            Option<ISequenceEvent> ise = ISequenceElementAccessor.getISequenceEvent(part.getNotationView());
            if (ise.some()) {
                result.add(ise.get());
            }
        }
        return result;
    }

    /**
     * Get the list of InstanceRoles from the current GroupRequest.
     * 
     * @return the list of InstanceRoles
     */
    public List<InstanceRole> getInstanceRoles() {
        List<IGraphicalEditPart> editParts = getEditParts();

        if (editParts.isEmpty()) {
            return Collections.emptyList();
        }

        List<InstanceRole> instanceRoles = new ArrayList<InstanceRole>();
        for (IGraphicalEditPart part : editParts) {
            Option<InstanceRole> instanceRole = ISequenceElementAccessor.getInstanceRole(part.getNotationView());
            if (instanceRole.some()) {
                instanceRoles.add(instanceRole.get());
            }
        }
        return instanceRoles;
    }

    /**
     * Tests whether this execution is being moved indirectly by ancestor, or
     * because is linked to a moving execution, as part of the request, in which
     * case the parent/main execution is responsible for most of the work.
     * 
     * @return true if this request if from another indirect request
     */
    public boolean isExecutionMovedIndirectly() {
        Map<?, ?> extData = request.getExtendedData();
        if (request instanceof ChangeBoundsRequest && extData != null && extData.get(IS_MOVED_BY_PARENT_EXECUTION) instanceof Boolean) {
            Boolean value = (Boolean) extData.get(IS_MOVED_BY_PARENT_EXECUTION);
            return value.booleanValue();
        } else {
            return false;
        }
    }

    /**
     * Checks if the current request is a creation request for a new create
     * message.
     * 
     * @return true if the current request is a creation request for a new
     *         create message
     */
    public boolean isCreateMessageCreation() {
        return isSequenceMessageCreation(Predicates.instanceOf(CreationMessageMapping.class));
    }

    /**
     * Checks if the current request is a creation request for a new destroy
     * message.
     * 
     * @return true if the current request is a creation request for a new
     *         destroy message
     */
    public boolean isDestroyMessageCreation() {
        return isSequenceMessageCreation(Predicates.instanceOf(DestructionMessageMapping.class));
    }

    /**
     * Checks if the current request is a creation request for a new standard
     * message.
     * 
     * @return true if the current request is a creation request for a new
     *         standard message
     */
    public boolean isStandardMessageCreation() {
        return isSequenceMessageCreation(Predicates.instanceOf(BasicMessageMapping.class));
    }

    /**
     * Checks if the current request is a creation request for a message.
     * 
     * @return true if the current request is a creation request for message
     */
    public boolean isSequenceMessageCreation() {
        return isSequenceMessageCreation(null);
    }

    private boolean isSequenceMessageCreation(Predicate<Object> expectedMessageCreationToolMappingTypes) {
        boolean result = false;
        if (!isNoteAttachmentCreationRequest() && request instanceof CreateConnectionRequest) {
            CreateConnectionRequest createRequest = (CreateConnectionRequest) request;
            if (!(request instanceof CreateUnspecifiedTypeConnectionRequest) && createRequest.getNewObject() instanceof MessageCreationTool) {
                if (expectedMessageCreationToolMappingTypes != null) {
                    MessageCreationTool messageCreationTool = (MessageCreationTool) createRequest.getNewObject();
                    result = Iterables.any(messageCreationTool.getEdgeMappings(), expectedMessageCreationToolMappingTypes);
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Request created from SequenceMessageEditPolicy.
     * 
     * @return true if created from message;
     */
    public boolean isDirectedByMessage() {
        Object object = request.getExtendedData().get(SequenceMessageEditPolicy.REQUEST_FROM_SEQUENCE_MESSAGE_EDIT_POLICY);
        return object instanceof Boolean && Boolean.TRUE.equals(object);
    }

    public Map getExtendedData() {
        return request.getExtendedData();
    }

}
