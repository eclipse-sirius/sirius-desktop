/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
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
            DCell cell = getDCell(element).orElse(null);
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
        IToolTipProvider tooltipProvider = Platform.getAdapterManager().getAdapter(element, IToolTipProvider.class);
        if (tooltipProvider != null) {
            tooltip = tooltipProvider.getToolTipText(element);
        }
        return tooltip;
    }
}
