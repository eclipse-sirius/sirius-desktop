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
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

/**
 * {@link ILabelProvider} for the top level line representing column header.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTableColumnHeaderLabelProvider extends DSemanticTargetBasedLabelProvider implements ILabelProvider {

    private ILabelDecorator decorator;

    /**
     * Constructor.
     */
    public DTableColumnHeaderLabelProvider() {
        super(ViewHelper.INSTANCE.createAdapterFactory());
        this.decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
    }

    @Override
    public String getText(final Object element) {
        String text = ""; //$NON-NLS-1$
        if (element instanceof DColumn) {
            DColumn dColumn = (DColumn) element;
            text = dColumn.getLabel();
        }
        return text;
    }

    @Override
    public Image getImage(Object element) {
        Image image = super.getImage(element);
        if (decorator != null) {
            Image decorated = decorator.decorateImage(image, element);
            if (decorated != null) {
                image = decorated;
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
