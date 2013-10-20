/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions.pinning;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.sirius.diagram.ImagesPath;
import org.eclipse.sirius.diagram.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.tools.internal.commands.emf.UnpinElementsCommand;
import org.eclipse.sirius.diagram.ui.tools.api.layout.PinHelper;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.DDiagramElement;

/**
 * Eclipse action to unpin elements.
 * 
 * @author dlecan
 */
public class UnpinElementsEclipseAction extends AbstractPinUnpinElementsEclipseAction {

    private static final String UNPIN_SELECTED_ELEMENTS = "Unpin selected elements";

    /**
     * Constructor.
     */
    public UnpinElementsEclipseAction() {
        super(UNPIN_SELECTED_ELEMENTS, ActionIds.UNPIN_ELEMENTS, UNPIN_SELECTED_ELEMENTS, ImagesPath.UNPIN_ELEMENTS_ICON);
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

            if (dDiagramElement.eResource() == null
                    || !PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dDiagramElement.eResource().getResourceSet()).canEditInstance(dDiagramElement.getParentDiagram())) {
                result = false;
                break;
            }
        }

        return result;
    }
}
