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

import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;

/**
 * Label provider for lines.
 * 
 * 
 * @author dlecan
 */
public class DTableLineLabelProvider extends DSemanticTargetBasedLabelProvider implements ILabelProvider {

    private ILabelDecorator decorator;

    /**
     * Constructor.
     * 
     * @param decorator the decorator to use.
     */
    public DTableLineLabelProvider(ILabelDecorator decorator) {
        super(ViewHelper.INSTANCE.createAdapterFactory());
        this.decorator = decorator;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            return line.getLabel();
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Overridden to decorate the returned Image.
     * 
     * {@inheritDoc}
     */
    public Image getImage(Object element) {
        Image image = super.getImage(element);
        if (decorator != null && element instanceof DLine) {
            Image decorated = decorator.decorateImage(image, element);
            if (decorated != null) {
                return decorated;
            }
        }
        return image;
    }

}
