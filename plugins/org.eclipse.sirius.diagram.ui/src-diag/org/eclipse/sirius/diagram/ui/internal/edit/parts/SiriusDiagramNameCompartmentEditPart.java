/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TopGraphicEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DiagramNameCompartmentEditPart;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.DelegatingDragEditPartsTracker;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;

/**
 * An edit part that shows representation name/icon as a header in representation link notes.
 */
public class SiriusDiagramNameCompartmentEditPart extends DiagramNameCompartmentEditPart {

    public SiriusDiagramNameCompartmentEditPart(View view) {
        super(view);
    }

    @Override
    protected Image getLabelIcon(int i) {
        if (getElement() != null) {
            EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getElement());
            if (domain instanceof AdapterFactoryEditingDomain) {
                IItemLabelProvider provider = (IItemLabelProvider) ((AdapterFactoryEditingDomain) domain).getAdapterFactory().adapt(getElement(), IItemLabelProvider.class);
                if (provider != null) {
                    return ExtendedImageRegistry.getInstance().getImage(provider.getImage(getElement()));
                }
            }
        }
        return super.getLabelIcon(i);
    }

    /**
     * @see org.eclipse.gef.EditPart#getDragTracker(org.eclipse.gef.Request)
     */
    // overridden to avoid exceptions when clicking diagram name label to select note
    @Override
    public DragTracker getDragTracker(Request request) {
        TopGraphicEditPart tgep = getTopGraphicEditPart();
        return new DelegatingDragEditPartsTracker(tgep, tgep);
    }

    private EObject getElement() {
        View primary = getPrimaryView();
        if (primary != null) {
            return primary.getElement();
        }
        return null;
    }

}
