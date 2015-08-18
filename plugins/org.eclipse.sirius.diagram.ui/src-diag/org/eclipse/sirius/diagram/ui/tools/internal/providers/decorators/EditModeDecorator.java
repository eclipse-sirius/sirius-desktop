/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.providers.decorators;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.decorators.AbstractSiriusDecorator;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * Decorator.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EditModeDecorator extends AbstractSiriusDecorator {

    /**
     * Create a decorator.
     * 
     * @param decoratorTarget
     *            target to decorate.
     */
    public EditModeDecorator(final IDecoratorTarget decoratorTarget) {
        super(decoratorTarget);
    }

    /**
     * Check if an edit part is broken for decoration.
     * 
     * @param editPart
     *            the edit part to check
     * @return <code>true</code> if the editPart is not broken to be decorated,
     *         <code>false</code> otherwise
     */
    protected boolean isBroken(EditPart editPart) {
        if (editPart instanceof IDiagramElementEditPart) {
            final EObject target = ((IDiagramElementEditPart) editPart).resolveTargetSemanticElement();
            if (target == null || target.eResource() == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the position of the decorator according to editPart.
     * 
     * @param editPart
     *            the editpart
     * @return a Direction
     */
    @Override
    protected Direction getDirection(final EditPart editPart) {
        if (editPart instanceof AbstractConnectionEditPart) {
            return Direction.SOUTH;

        }
        return Direction.SOUTH_WEST;
    }

    /**
     * Check if the editPart respect conditions to be decorate.
     * 
     * @param editPart
     *            the editPart to check
     * @return true if the editPart respect conditions to be decorate, false
     *         otherwise
     */
    @Override
    protected boolean shouldBeDecorated(final EditPart editPart) {
        boolean shouldBeDecorated = super.shouldBeDecorated(editPart);

        if (shouldBeDecorated && editPart instanceof IDiagramElementEditPart) {
            IDiagramElementEditPart part = (IDiagramElementEditPart) editPart;

            // Case 1 : permission authority forbids the edition of the semantic
            // element associated to this edit part
            if (isDecorableEditPart(part)) {
                IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(part.getEditingDomain().getResourceSet());
                if (auth != null) {
                    EObject representedObject = part.resolveTargetSemanticElement();
                    if (representedObject != null) {
                        shouldBeDecorated = LockStatus.LOCKED_BY_OTHER.equals(auth.getLockStatus(representedObject));
                    }
                }
            }

            // Case 2 : edit part is broken
            if (!shouldBeDecorated) {
                shouldBeDecorated = isBroken(editPart);
            }
        }
        return shouldBeDecorated;
    }

    /**
     * Get the decoration image.<br>
     * 
     * @param editPart
     *            the edit part to get the decoration image from
     * @return <code>null</code> if no image found.
     */
    @Override
    protected Image getDecorationImage(EditPart editPart) {
        if (isBroken(editPart)) {
            // If the edit part is borken, we return a "delete" image (red
            // cross)
            return DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_DIAGRAM_ICON));
        } else {
            // It means that the semantic element referenced by this edit part
            // is not editable, we return a "locked" image (red padlock)
            return DiagramUIPlugin.getPlugin().getImage(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied.gif")); //$NON-NLS-1$
        }
    }

}
