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

import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;

/**
 * {@link ILabelProvider} for the top level line representing column header.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTableColumnHeaderLabelProvider extends DSemanticTargetBasedLabelProvider implements ILabelProvider {

    private ILabelDecorator decorator;

    /**
     * Constructor.
     * 
     * @param decorator
     */
    public DTableColumnHeaderLabelProvider() {
        super(ViewHelper.INSTANCE.createAdapterFactory());
        this.decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        String text = ""; //$NON-NLS-1$
        if (element instanceof DColumn) {
            DColumn dColumn = (DColumn) element;
            text = dColumn.getLabel();
        }
        return text;
    }

    /**
     * Overridden to decorate the returned Image.
     * 
     * {@inheritDoc}
     */
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

}
