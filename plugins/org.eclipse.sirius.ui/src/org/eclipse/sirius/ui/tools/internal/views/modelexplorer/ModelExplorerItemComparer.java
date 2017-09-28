/*******************************************************************************
 * Copyright (c) 2013, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer;

import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationItemImpl;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * A {@link IElementComparer} to have {@link ItemWrapper} considered equals to {@link ItemWrapper#getWrappedObject()}.
 * This comparer is created to allow link to editor functionality to select {@link RepresentationItemImpl} corresponding
 * to opened {@link DRepresentationDescriptor} of an opened diagram.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ModelExplorerItemComparer implements IElementComparer {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object a, Object b) {
        boolean equals = false;
        // We compare wrapped object of RepresentationItemImpl only when other object is a DRepresentationDescriptor. In
        // every other cases we use standard equality. It allows to link with editor RepresentationItemImpl in
        // ModelExplorerView with DRepresentationDescriptor of the opened diagram.
        if ((a instanceof DRepresentationDescriptor && b instanceof RepresentationItemImpl) || (b instanceof DRepresentationDescriptor && a instanceof RepresentationItemImpl)) {
            Object realAObject = a;
            if (realAObject instanceof ItemWrapper) {
                ItemWrapper itemWrapper = (ItemWrapper) realAObject;
                realAObject = itemWrapper.getWrappedObject();
            }
            Object realBObject = b;
            if (realBObject instanceof ItemWrapper) {
                ItemWrapper itemWrapper = (ItemWrapper) realBObject;
                realBObject = itemWrapper.getWrappedObject();
            }
            equals = realAObject != null && realAObject.equals(realBObject);
        } else {
            equals = a.equals(b);
        }
        return equals;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode(Object element) {
        int hashCode = 0;
        Object realElementObject = element;
        if (realElementObject instanceof ItemWrapper) {
            ItemWrapper itemWrapper = (ItemWrapper) realElementObject;
            realElementObject = itemWrapper.getWrappedObject();
        }
        if (realElementObject != null) {
            hashCode = realElementObject.hashCode();
        }
        return hashCode;
    }

}
