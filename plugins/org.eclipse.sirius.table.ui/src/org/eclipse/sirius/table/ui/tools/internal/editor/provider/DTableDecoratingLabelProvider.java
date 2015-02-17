/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.swt.graphics.Image;

/**
 * Inherits {@link DTableColumnLabelProvider} to decorate DTableElement.
 * 
 * @author edugueperoux
 */
public class DTableDecoratingLabelProvider extends DTableColumnLabelProvider implements ILabelProvider {

    private ILabelDecorator decorator;

    /**
     * Create a new DTableDecoratingLabelProvider.
     * 
     * @param newColumn
     *            the column to decorate.
     * 
     * @param decorator
     *            the decorator to use.
     */
    public DTableDecoratingLabelProvider(DColumn newColumn, ILabelDecorator decorator) {
        super(newColumn);
        this.decorator = decorator;
    }

    @Override
    public Image getImage(Object element) {
        Image image = super.getImage(element);
        if (decorator != null) {
            DCell cell = null;
            if (element instanceof DLine) {
                final DLine line = (DLine) element;
                Option<DCell> optionalCell = TableHelper.getCell(line, column);
                if (optionalCell.some()) {
                    cell = optionalCell.get();
                }
            } else if (element instanceof DCell) {
                cell = (DCell) element;
            }
            if (cell != null && cell.getColumn().equals(column)) {
                Image decorated = decorator.decorateImage(image, cell);
                if (decorated != null) {
                    return decorated;
                }
            }
        }
        return image;
    }

    @Override
    public String getToolTipText(Object element) {
        String tooltip = null;
        IToolTipProvider tooltipProvider = (IToolTipProvider) Platform.getAdapterManager().getAdapter(element, IToolTipProvider.class);
        if (tooltipProvider != null) {
            tooltip = tooltipProvider.getToolTipText(element);
        }
        return tooltip;
    }
}
