/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.util.Iterator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.IPopupMenuContributionPolicy;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction;

/**
 * This policy allows to use straighten to actions ({@link StraightenToAction}) when at least one edge is selected in
 * case of a multi selection.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class MultiSelectionEdgePolicy implements IPopupMenuContributionPolicy {
    @Override
    public boolean appliesTo(ISelection selection, IConfigurationElement configuration) {
        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            Iterator<Object> selectionIte = structuredSelection.iterator();
            while (selectionIte.hasNext()) {
                Object object = selectionIte.next();
                if (object instanceof AbstractDiagramEdgeEditPart) {
                    return true;
                }
            }
        }
        return false;
    }
}
