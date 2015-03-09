/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.properties;

import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * Specialization for the manage of DTable elements.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public abstract class AbstractEObjectPropertySource extends AbstractCompositeEObjectPropertySource {

    /**
     * Creates a new <code>CompositeEObjectPropertySource</code>.
     */
    public AbstractEObjectPropertySource() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void setPropertyValue(final Object id, final Object value) {
        final Identifier identifier = (Identifier) id;

        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(identifier.getEObject());
        if (permissionAuthority == null || permissionAuthority.canEditInstance(identifier.getEObject())) {
            // Test if the value is different
            boolean isDifferent = true;
            final Object propertyValue = getPropertySource(identifier).getPropertyValue(identifier.getId());
            if (propertyValue instanceof PropertyValueWrapper && value != null) {
                isDifferent = !value.equals(((PropertyValueWrapper) propertyValue).getEditableValue(propertyValue));
            }
            if (isDifferent) {
                getPropertySource(identifier).setPropertyValue(identifier.getId(), value);
            }
        }

    }
}
