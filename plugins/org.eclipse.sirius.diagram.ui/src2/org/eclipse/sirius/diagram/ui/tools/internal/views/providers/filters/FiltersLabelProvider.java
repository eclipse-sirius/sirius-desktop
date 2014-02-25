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
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.filters;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.swt.graphics.Image;

/**
 * The label provider.
 * 
 * @author mchauvin
 */
public class FiltersLabelProvider extends LabelProvider implements ITableLabelProvider {

    private IDiagramWorkbenchPart diagramPart;

    /**
     * Construct a new Layer label provider.
     * 
     * @param part
     *            the part responsible of the diagram access.
     */
    public FiltersLabelProvider(final IDiagramWorkbenchPart part) {
        diagramPart = part;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(final Object element, final int columnIndex) {
        if (columnIndex == 0 && diagramPart != null) {

            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                final EObject designerElement = ((View) obj).getElement();
                if (designerElement instanceof DDiagram) {
                    final List<FilterDescription> activatedFilters = ((DDiagram) designerElement).getActivatedFilters();
                    Image img = null;
                    if (activatedFilters.contains(element)) {
                        img = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.ACTIVE_FILTER_ICON);
                    } else {
                        img = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.INACTIVE_FILTER_ICON);
                    }
                    return img;
                }
            }

        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(final Object element, final int columnIndex) {
        switch (columnIndex) {
        case 1:
            if (element instanceof FilterDescription) {
                return ((FilterDescription) element).getName();
            }
            break;
        default:
            break;
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        diagramPart = null;
    }

}
