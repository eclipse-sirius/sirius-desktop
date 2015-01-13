/*******************************************************************************
 * Copyright (c) 2009-2012 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.PinElementsCommand;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * Eclipse action to pin elements.
 * 
 * @author dlecan
 */
public class PinElementsEclipseAction extends AbstractPinUnpinElementsEclipseAction {

    private static final String PIN_SELECTED_ELEMENTS = "Pin selected elements";

    /**
     * Constructor.
     */
    public PinElementsEclipseAction() {
        super(PIN_SELECTED_ELEMENTS, ActionIds.PIN_ELEMENTS, PIN_SELECTED_ELEMENTS, DiagramImagesPath.PIN_ELEMENTS_ICON);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command createCommand(final Collection<DDiagramElement> elements) {
        return new PinElementsCommand(elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doIsEnabled(final Collection<DDiagramElement> selectedDiagramElements) {
        boolean result = true;
        PinHelper pinHelper = new PinHelper();
        for (final DDiagramElement dDiagramElement : selectedDiagramElements) {
            result = result && pinHelper.isPinned(dDiagramElement);

            Resource dDiagramElementResource = dDiagramElement.eResource();
            if (dDiagramElementResource == null
                    || !PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramElementResource.getResourceSet()).canEditInstance(dDiagramElement.getParentDiagram())) {
                result = true;
                break;
            }
        }

        return !result;
    }
}
