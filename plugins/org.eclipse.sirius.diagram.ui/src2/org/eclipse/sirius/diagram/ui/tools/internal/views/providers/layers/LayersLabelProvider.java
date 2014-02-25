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
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.ImageProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * The label provider.
 * 
 * @author mchauvin
 */
public class LayersLabelProvider extends ColumnLabelProvider {

    private IDiagramWorkbenchPart diagramPart;

    private int columnIndex;

    /**
     * Construct a new Layer label provider.
     * 
     * @param part
     *            the part responsible of the diagram access.
     */
    public LayersLabelProvider(final IDiagramWorkbenchPart part) {
        diagramPart = part;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(Object element) {
        Image result = null;
        if (columnIndex == 0 && diagramPart != null) {
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                final EObject designerElement = ((View) obj).getElement();
                if (designerElement instanceof DDiagram) {
                    final List<Layer> activatedLayers = ((DDiagram) designerElement).getActivatedLayers();
                    Image img = null;
                    if (EqualityHelper.contains(activatedLayers, (EObject) element)) {
                        img = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.ACTIVE_LAYER_ICON);
                    } else {
                        img = DiagramUIPlugin.getPlugin().getBundledImage(DiagramImagesPath.INACTIVE_LAYER_ICON);
                    }
                    result = img;
                }
            }
        } else if (columnIndex == 1) {
            if (element instanceof Layer) {
                Layer layer = (Layer) element;
                if (!StringUtil.isEmpty(layer.getIcon())) {
                    result = ImageProvider.getImageFromPath(layer.getIcon());
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        switch (columnIndex) {
        case 2:
            if (element instanceof Layer) {
                return new IdentifiedElementQuery((Layer) element).getLabel();
            }
            break;
        default:
            break;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element) {
        if (element instanceof Layer) {
            String endUserDoc = ((Layer) element).getEndUserDocumentation();
            if (endUserDoc != null && endUserDoc.trim().length() > 0) {
                return endUserDoc;
            }
        }
        return super.getToolTipText(element);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipShift(java.lang.Object)
     */
    @Override
    public Point getToolTipShift(final Object object) {
        return new Point(5, 5);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipDisplayDelayTime(java.lang.Object)
     */
    @Override
    public int getToolTipDisplayDelayTime(final Object object) {
        return 200;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipStyle(java.lang.Object)
     */
    @Override
    public int getToolTipStyle(final Object object) {
        return SWT.SHADOW_OUT;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
     */
    @Override
    public void update(final ViewerCell cell) {
        columnIndex = cell.getColumnIndex();
        super.update(cell);
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
