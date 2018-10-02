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
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.ui.tools.api.provider.DSemanticTargetBasedLabelProvider;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for lines.
 * 
 * @author dlecan
 */
public class DTableLineLabelProvider extends DSemanticTargetBasedLabelProvider implements ILabelProvider {

    private ILabelDecorator decorator;

    /**
     * Constructor.
     * 
     * @param decorator
     *            the decorator to use.
     */
    public DTableLineLabelProvider(ILabelDecorator decorator) {
        super(ViewHelper.INSTANCE.createAdapterFactory());
        this.decorator = decorator;
    }

    @Override
    public String getText(final Object element) {
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            return line.getLabel();
        }
        return ""; //$NON-NLS-1$
    }

    @Override
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
