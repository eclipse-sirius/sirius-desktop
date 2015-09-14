/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.pinning;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.tools.internal.commands.UnpinElementsCommand;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * Eclipse action to unpin elements.
 * 
 * @author dlecan
 */
public class UnpinElementsEclipseAction extends AbstractPinUnpinElementsEclipseAction {

    /**
     * Constructor.
     */
    public UnpinElementsEclipseAction() {
        super(Messages.UnpinElementsEclipseAction_text, ActionIds.UNPIN_ELEMENTS, Messages.UnpinElementsEclipseAction_text, DiagramImagesPath.UNPIN_ELEMENTS_ICON);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command createCommand(final Collection<DDiagramElement> elements) {
        return new UnpinElementsCommand(elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doIsEnabled(final Collection<DDiagramElement> selectedDiagramElements) {
        boolean result = false;
        PinHelper pinHelper = new PinHelper();
        for (final DDiagramElement dDiagramElement : selectedDiagramElements) {
            result = result || pinHelper.isPinned(dDiagramElement);

            Resource dDiagramElementResource = dDiagramElement.eResource();
            if (dDiagramElementResource == null
                    || !PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramElementResource.getResourceSet()).canEditInstance(dDiagramElement.getParentDiagram())) {
                result = false;
                break;
            }
        }

        return result;
    }
}
