/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Optional;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION;
import org.eclipse.sirius.properties.FillLayoutDescription;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class FillLayoutDescriptionItemProviderSpec extends FillLayoutDescriptionItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public FillLayoutDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public Object getImage(Object object) {
        FILL_LAYOUT_ORIENTATION labelValue = ((FillLayoutDescription) object).getOrientation();
        if (labelValue == FILL_LAYOUT_ORIENTATION.HORIZONTAL) {
            return overlayImage(object, getResourceLocator().getImage("full/obj16/FillLayoutDescriptionHorizontal")); //$NON-NLS-1$
        } else {
            return overlayImage(object, getResourceLocator().getImage("full/obj16/FillLayoutDescriptionVertical")); //$NON-NLS-1$
        }
    }

    @Override
    public String getText(Object object) {
        Object styledText = this.getStyledText(object);
        if (styledText instanceof StyledString) {
            return ((StyledString) styledText).getString();
        }
        return super.getText(object);
    }

    @Override
    public Object getStyledText(Object object) {
        FILL_LAYOUT_ORIENTATION labelValue = ((FillLayoutDescription) object).getOrientation();
        String label = Optional.ofNullable(labelValue).map(Object::toString).orElse(""); //$NON-NLS-1$
        if (!label.isEmpty()) {
            label = this.getString("_UI_FillLayoutDescription_type"); //$NON-NLS-1$
        }
        return new StyledString(label);
    }

}
