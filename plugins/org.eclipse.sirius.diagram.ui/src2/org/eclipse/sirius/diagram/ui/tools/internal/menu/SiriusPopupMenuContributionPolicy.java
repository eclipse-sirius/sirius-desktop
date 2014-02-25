/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.IPopupMenuContributionPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramRootEditPart;

/**
 * A PopupMenuContributionPolicy to filter on which edit part the Sirius
 * popupContribution should be applied.
 * 
 * @author Florian Barbin
 * 
 */
public class SiriusPopupMenuContributionPolicy implements IPopupMenuContributionPolicy {

    @Override
    public boolean appliesTo(ISelection selection, IConfigurationElement configuration) {
        if (selection instanceof StructuredSelection) {
            Object firstSelectedElement = ((StructuredSelection) selection).getFirstElement();
            if (firstSelectedElement instanceof IGraphicalEditPart && ((IGraphicalEditPart) firstSelectedElement).getRoot() instanceof DDiagramRootEditPart) {
                return true;
            }
        }
        return false;
    }

}
