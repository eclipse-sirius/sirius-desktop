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
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.tools.internal.handler.ChangeFilterActivation;
import org.eclipse.swt.widgets.Item;

/**
 * The cell modifier.
 * 
 * @author mchauvin
 */
public class FiltersCellModifier implements ICellModifier {
    private final IDiagramWorkbenchPart diagramPart;

    private final String[] filtersColumns;

    /**
     * Construct a new cell modifier.
     * 
     * @param adapter
     *            the layer activation adapter
     * @param part
     *            the workbench diagram part
     * @param columns
     *            the layer table columns
     */
    public FiltersCellModifier(final FiltersActivationAdapter adapter, final IDiagramWorkbenchPart part, final String[] columns) {
        diagramPart = part;
        filtersColumns = columns;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
     *      java.lang.String)
     */
    public boolean canModify(final Object element, final String property) {

        if (property.equals(filtersColumns[0])) {
            /* first column */
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
     *      java.lang.String)
     */
    public Object getValue(final Object element, final String property) {

        final FilterDescription filter = (FilterDescription) element;
        Object result = null;

        if (property.equals(filtersColumns[0])) {
            /* first column */
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                final EObject designerElement = ((View) obj).getElement();
                if (designerElement instanceof DDiagram) {
                    final List<FilterDescription> activatedFilters = ((DDiagram) designerElement).getActivatedFilters();
                    if (activatedFilters.contains(element)) {
                        result = Boolean.TRUE;
                    } else {
                        result = Boolean.FALSE;
                    }
                }
            }
        } else {
            /* second column */
            result = filter.getName();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
     *      java.lang.String, java.lang.Object)
     */
    public void modify(final Object element, final String property, final Object value) {
        Object objElement;

        if (element instanceof Item) {
            objElement = ((Item) element).getData();
        } else {
            objElement = element;
        }

        final FilterDescription filterDescription = (FilterDescription) objElement;

        if (property.equals(filtersColumns[0])) {
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                final EObject designerElement = ((View) obj).getElement();
                final PaletteViewer paletteViewer = diagramPart.getDiagramGraphicalViewer().getEditDomain().getPaletteViewer();
                if (designerElement instanceof DDiagram && paletteViewer != null) {
                    final Runnable change = new ChangeFilterActivation(diagramPart, (DDiagram) designerElement, filterDescription, value.equals(Boolean.TRUE));
                    change.run();
                }
            }
        }
    }
}
