/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.decoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
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
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
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

    /**
     * DISABLE_PRINT_FOR_PERMISSION_AUTHORITY_DECORATION.
     */
    protected static final String DISABLE_PRINT_FOR_PERMISSION_AUTHORITY_DECORATION = "org.eclipse.sirius.diagam.ui.hidePrintingOfPermissionAuthorityDecoration"; //$NON-NLS-1$

    private static final ImageDescriptor LOCK_BY_OTHER_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied.gif"); //$NON-NLS-1$

    private static final ImageDescriptor DELETED_DIAG_ELEM_DECORATOR_IMAGE_DESCRIPTOR = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETED_DIAG_ELEM_DECORATOR_ICON);

    private static final String NAME = "editModeDecorator"; //$NON-NLS-1$

    @Override
    public boolean provides(IDiagramElementEditPart editPart) {
        return true;
    }

    @Override
    public List<DecorationDescriptor> createDecorationDescriptors(IDiagramElementEditPart editPart, Session session) {
        DecorationDescriptor decoDesc = new DecorationDescriptor();
        addDecorationImage(editPart, decoDesc);
        if (decoDesc.getDecorationAsImage() != null) {
            decoDesc.setName(NAME);
            decoDesc.setPosition(Position.SOUTH_WEST_LITERAL);
            decoDesc.setDistributionDirection(DecorationDistributionDirection.HORIZONTAL);
            decoDesc.setDisplayPriority(DisplayPriority.HIGH_PRIORITY.getValue());
            // add tooltip
            decoDesc.setTooltipAsString(getToolTip(editPart));

            return Arrays.asList(decoDesc);
        }

        return new ArrayList<>();
    }

    private String getToolTip(IDiagramElementEditPart editPart) {
        try {
            EObject representedObject = editPart.resolveTargetSemanticElement();
    
            if (representedObject != null) {
                IToolTipProvider tooltipProvider = Platform.getAdapterManager().getAdapter(representedObject, IToolTipProvider.class);
                if (tooltipProvider != null) {
                    return tooltipProvider.getToolTipText(representedObject);
                }
            }
        } catch (IllegalStateException e) {
            // Nothing to log here, this can happen if the resource is not accessible anymore (distant resource).
        }
        return null;
    }

    /**
     * Check if an edit part is broken for decoration.
     * 
     * @param editPart
     *            the edit part to check
     * @return <code>true</code> if the editPart is not broken to be decorated, <code>false</code> otherwise
     */
    protected boolean isBroken(IDiagramElementEditPart editPart) {
        final EObject target = editPart.resolveTargetSemanticElement();
        if (isBroken(target)) {
            return true;
        }
        return false;
    }

    /**
     * Check if an EObject is broken (ie null or without eResource).
     * 
     * @param eObject
     *            the eObject to check
     * @return <code>true</code> if the eObject is broken, <code>false</code> otherwise
     */
    protected boolean isBroken(EObject eObject) {
        return eObject == null || eObject.eResource() == null;
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
     * @param decoDesc
     *            the DecorationDescriptor on which to set the image
     */
    protected void addDecorationImage(IDiagramElementEditPart editPart, DecorationDescriptor decoDesc) {
        Image decorationImage = null;
        IDiagramElementEditPart part = editPart;

        Boolean isBroken = null;
        // Case 1 : permission authority forbids the edition of the semantic
        // element associated to this edit part
        if (isDecorableEditPart(part)) {
            IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(part.getEditingDomain().getResourceSet());
            if (auth != null) {
                EObject representedObject = part.resolveTargetSemanticElement();
                isBroken = isBroken(representedObject);
                if (!isBroken) {
                    if (Boolean.getBoolean(DISABLE_PRINT_FOR_PERMISSION_AUTHORITY_DECORATION)) {
                        decoDesc.setPrintable(false);
                    }
                    decorationImage = getLockStatusDecorationImage(auth.getLockStatus(representedObject));
                }
            }
        }

        // Case 2 : edit part is broken
        if (decorationImage == null) {
            if ((isBroken != null && isBroken.booleanValue()) || (isBroken == null && isBroken(part))) {
                // If the edit part is broken, we return a "deleted" image (red cross)
                decorationImage = DiagramUIPlugin.getPlugin().getImage(DELETED_DIAG_ELEM_DECORATOR_IMAGE_DESCRIPTOR);
            }
        }

        if (decorationImage != null) {
            decoDesc.setDecorationAsImage(decorationImage);
        }
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
            return DiagramUIPlugin.getPlugin().getImage(LOCK_BY_OTHER_IMAGE_DESCRIPTOR);
        }
        return null;
    }
}
