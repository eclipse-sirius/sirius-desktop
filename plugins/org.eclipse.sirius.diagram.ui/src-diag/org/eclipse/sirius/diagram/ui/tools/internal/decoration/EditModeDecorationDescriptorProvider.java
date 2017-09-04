/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.decoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.AbstractSiriusDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor.DisplayPriority;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.SiriusDecorationDescriptorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.description.DecorationDistributionDirection;
import org.eclipse.sirius.viewpoint.description.Position;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * This {@link SiriusDecorationDescriptorProvider} provides a decoration on the bottom left corner when the element is
 * in disableEditMode (and another where it is also invalid).
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class EditModeDecorationDescriptorProvider extends AbstractSiriusDecorationDescriptorProvider {

    private static final String NAME = "editModeDecorator"; //$NON-NLS-1$

    @Override
    public boolean provides(IDiagramElementEditPart editPart) {
        return true;
    }

    @Override
    public List<DecorationDescriptor> createDecorationDescriptors(IDiagramElementEditPart editPart, Session session) {
        Image decorationImage = getDecorationImage(editPart);
        if (decorationImage != null) {
            DecorationDescriptor decoDesc = new DecorationDescriptor();
            decoDesc.setName(NAME);
            decoDesc.setPosition(Position.SOUTH_WEST_LITERAL);
            decoDesc.setDistributionDirection(DecorationDistributionDirection.HORIZONTAL);
            decoDesc.setDisplayPriority(DisplayPriority.HIGH_PRIORITY.getValue());
            decoDesc.setDecorationAsImage(decorationImage);

            return Arrays.asList(decoDesc);
        }

        return new ArrayList<>();
    }

    /**
     * Check if an edit part is broken for decoration.
     * 
     * @param editPart
     *            the edit part to check
     * @return <code>true</code> if the editPart is not broken to be decorated, <code>false</code> otherwise
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
     * Check if the editPart respect conditions to be decorate.
     * 
     * @param editPart
     *            the editPart to check
     * @return true if the editPart respect conditions to be decorate, false otherwise
     */
    @Override
    protected boolean shouldBeDecorated(final EditPart editPart) {
        return editPart instanceof IDiagramElementEditPart && super.shouldBeDecorated(editPart);
    }

    /**
     * Get the decoration image.<br>
     * 
     * @param editPart
     *            the edit part to get the decoration image from
     * @return <code>null</code> if no image found.
     */
    protected Image getDecorationImage(EditPart editPart) {
        Image decorationImage = null;
        if (editPart instanceof IDiagramElementEditPart) {
            IDiagramElementEditPart part = (IDiagramElementEditPart) editPart;

            // Case 1 : permission authority forbids the edition of the semantic
            // element associated to this edit part
            if (isDecorableEditPart(part)) {
                IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(part.getEditingDomain().getResourceSet());
                if (auth != null) {
                    EObject representedObject = part.resolveTargetSemanticElement();
                    if (representedObject != null) {
                        decorationImage = getLockStatusDecorationImage(auth.getLockStatus(representedObject));
                    }
                }
            }

            // Case 2 : edit part is broken
            if (decorationImage == null && isBroken(editPart)) {
                // If the edit part is broken, we return a "deleted" image (red cross)
                decorationImage = DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETED_DIAG_ELEM_DECORATOR_ICON));
            }
        }
        return decorationImage;
    }

    /**
     * Return the image corresponding to the given {@link LockStatus}.
     * 
     * @param lockStatus
     *            the lock status of the element to decorate.
     * @return the image corresponding to the given {@link LockStatus}
     */
    protected Image getLockStatusDecorationImage(LockStatus lockStatus) {
        if (LockStatus.LOCKED_BY_OTHER.equals(lockStatus)) {
            // It means that the semantic element referenced by this edit part
            // is not editable, we return a "locked" image (red padlock)
            return DiagramUIPlugin.getPlugin().getImage(SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied.gif")); //$NON-NLS-1$
        }
        return null;
    }
}
