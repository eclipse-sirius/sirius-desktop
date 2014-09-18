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
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;

/**
 * Inherits {@link DTreeItemLabelProvider} to decorate DTreeItem.
 * 
 * @author edugueperoux
 */
public class DTreeDecoratingLabelProvider extends DTreeItemLabelProvider {

    private ILabelDecorator decorator;

    /**
     * Default constructor.
     * 
     * @param adapterFactoryLabelProvider
     *            Provider which to delegate label and image resolution
     * @param decorator
     *            {@link ILabelDecorator} to decorate the {@link Image} returned
     *            by {@link DTreeDecoratingLabelProvider#getImage(Object)}
     */
    public DTreeDecoratingLabelProvider(AdapterFactory adapterFactoryLabelProvider, ILabelDecorator decorator) {
        super(adapterFactoryLabelProvider);
        this.decorator = decorator;
    }

    /**
     * Overridden to decorate with a image to notify the user of lock status.
     * 
     * {@inheritDoc}
     */
    public Image getImage(Object element) {
        Image image = super.getImage(element);
        if (decorator != null) {
            Image decorated = decorator.decorateImage(image, element);
            if (decorated != null) {
                return decorated;
            }
        }
        return image;
    }

}
