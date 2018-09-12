/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TopGraphicEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DiagramNameCompartmentEditPart;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.DelegatingDragEditPartsTracker;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.swt.graphics.Image;

/**
 * An edit part that shows representation name/icon as a header in diagram link notes.
 */
public class SiriusDiagramNameCompartmentEditPart extends DiagramNameCompartmentEditPart {

    public SiriusDiagramNameCompartmentEditPart(View view) {
        super(view);
    }

    @Override
    protected Image getLabelIcon(int i) {
        EditPart ep = getParent();
        if (ep instanceof SiriusNoteEditPart) {
            if (((SiriusNoteEditPart) ep).isLinkNote()) {

                if (((SiriusNoteEditPart) ep).isLinkNoteBroken()) {
                    return DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETED_DIAG_ELEM_DECORATOR_ICON));
                }

                EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(getElement());
                if (domain instanceof AdapterFactoryEditingDomain) {
                    IItemLabelProvider provider = (IItemLabelProvider) ((AdapterFactoryEditingDomain) domain).getAdapterFactory().adapt(getElement(), IItemLabelProvider.class);
                    if (provider != null) {
                        return ExtendedImageRegistry.getInstance().getImage(provider.getImage(getElement()));
                    }
                }
            }
        }
        return super.getLabelIcon(i);
    }

    @Override
    protected String getLabelText() {
        EditPart ep = getParent();
        if (ep instanceof SiriusNoteEditPart && ((SiriusNoteEditPart) ep).isLinkNote() && (((SiriusNoteEditPart) ep).isLinkNoteBroken())) {
            return DiagramUIPlugin.getPlugin().getString("palettetool.linkNote.deletedLabel"); //$NON-NLS-1$
        }
        return super.getLabelText();
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

    /**
     * Overridden to handle dangling link notes in sirius collab.
     */
    @Override
    protected void setVisibility(boolean vis) {
        EditPart parent = getParent();
        if (parent instanceof SiriusNoteEditPart && ((SiriusNoteEditPart) parent).isLinkNote()) {
            // This code is copied from GraphicalEditPart to avoid calling
            // the parent class method: DiagramNameCompartmentEditPart is invisible
            // if the view element is null, but this collides with the case
            // of a dangling link note in sirius collab.
            if (!vis && getSelected() != SELECTED_NONE)
                getViewer().deselect(this);

            IFigure _figure = getFigure();
            if (_figure.isVisible() == vis) {
                return;
            }

            // if we are going to hide the node then connections coming to the
            // node or outside it should be hidden as well
            if (getFlag(FLAG__AUTO_CONNECTIONS_VISIBILITY)) {
                setConnectionsVisibility(vis);
            }
            _figure.setVisible(vis);
            _figure.revalidate();
        } else {
            super.setVisibility(vis);
        }
    }

    private EObject getElement() {
        View primary = getPrimaryView();
        if (primary != null) {
            return primary.getElement();
        }
        return null;
    }

}
