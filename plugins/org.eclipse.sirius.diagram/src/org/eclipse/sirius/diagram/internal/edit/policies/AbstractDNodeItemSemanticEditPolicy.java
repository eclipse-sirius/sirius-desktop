/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.policies;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.diagram.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.internal.edit.commands.CreateSiriusElementCommand;
import org.eclipse.sirius.diagram.internal.edit.commands.DEdgeCreateCommand;
import org.eclipse.sirius.diagram.internal.edit.commands.DEdgeReorientCommand;
import org.eclipse.sirius.diagram.internal.edit.commands.WorkspaceImageCreateCommand;
import org.eclipse.sirius.diagram.internal.edit.parts.BundledImageEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.CustomStyleEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DotEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.EllipseEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.GaugeCompositeEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.LozengeEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.NoteEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.SquareEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.WorkspaceImageEditPart;
import org.eclipse.sirius.diagram.internal.providers.SiriusElementTypes;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Abstract policy to put common ex generated code for
 * DNodeXItemSemanticEditPolicy.
 * 
 * @was-generated
 */
public abstract class AbstractDNodeItemSemanticEditPolicy extends SiriusBaseItemSemanticEditPolicy {

    /**
     * @was-generated
     */
    protected Command getCreateCommand(CreateElementRequest req) {
        if (SiriusElementTypes.BundledImage_3004 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.CustomStyle_3014 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.Dot_3002 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.Ellipse_3016 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.GaugeCompositeStyle_3006 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.Lozenge_3017 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.Note_3013 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.Square_3003 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new CreateSiriusElementCommand(req));
        }
        if (SiriusElementTypes.WorkspaceImage_3005 == req.getElementType()) {
            if (req.getContainmentFeature() == null) {
                req.setContainmentFeature(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
            }
            return getGEFWrapper(new WorkspaceImageCreateCommand(req));
        }
        return super.getCreateCommand(req);
    }

    /**
     * @was-generated NOT
     */
    protected abstract Command getDestroyElementCommand(DestroyElementRequest req);

    /**
     * @was-generated
     */
    protected void addDestroyChildNodesCommand(CompoundCommand cmd) {
        View view = (View) getHost().getModel();
        EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
        if (annotation != null) {
            return;
        }
        for (Node node : Iterables.filter(view.getChildren(), Node.class)) {
            switch (SiriusVisualIDRegistry.getVisualID(node)) {
            case DNode2EditPart.VISUAL_ID:
            case DNode4EditPart.VISUAL_ID:
            case BundledImageEditPart.VISUAL_ID:
            case CustomStyleEditPart.VISUAL_ID:
            case DotEditPart.VISUAL_ID:
            case EllipseEditPart.VISUAL_ID:
            case GaugeCompositeEditPart.VISUAL_ID:
            case LozengeEditPart.VISUAL_ID:
            case NoteEditPart.VISUAL_ID:
            case SquareEditPart.VISUAL_ID:
            case WorkspaceImageEditPart.VISUAL_ID:
                cmd.add(getDestroyElementCommand(node));
                break;
            default:
                break;
            }
        }
    }

    /**
     * @was-generated
     */
    protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
        Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req) : getCompleteCreateRelationshipCommand(req);
        return command != null ? command : super.getCreateRelationshipCommand(req);
    }

    /**
     * @was-generated
     */
    protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (SiriusElementTypes.DEdge_4001 == req.getElementType()) {
            return getGEFWrapper(new DEdgeCreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * @was-generated
     */
    protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
        if (SiriusElementTypes.DEdge_4001 == req.getElementType()) {
            return getGEFWrapper(new DEdgeCreateCommand(req, req.getSource(), req.getTarget()));
        }
        return null;
    }

    /**
     * Returns command to reorient EClass based link. New link target or source
     * should be the domain model element associated with this node.
     * 
     * @was-generated
     */
    protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
        switch (getVisualID(req)) {
        case DEdgeEditPart.VISUAL_ID:
        case BracketEdgeEditPart.VISUAL_ID:
            return getGEFWrapper(new DEdgeReorientCommand(req));
        }
        return super.getReorientRelationshipCommand(req);
    }
}
