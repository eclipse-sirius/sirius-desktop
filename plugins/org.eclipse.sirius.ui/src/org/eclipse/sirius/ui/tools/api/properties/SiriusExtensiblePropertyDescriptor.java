/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ext.emf.ui.properties.CellEditorProviderCollector;
import org.eclipse.sirius.ext.emf.ui.properties.ExtensiblePropertyDescriptor;
import org.eclipse.swt.widgets.Composite;

/**
 * A specific {@link ExtensiblePropertyDescriptor} to test
 * {@link IPermissionAuthority#canEditInstance(org.eclipse.emf.ecore.EObject)}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusExtensiblePropertyDescriptor extends ExtensiblePropertyDescriptor {

    private IPermissionAuthority permissionAuthority;

    /**
     * Creates a new instance of this class.
     * 
     * @param object
     *            the semantic object.
     * @param itemPropertyDescriptor
     *            the emf itemPropertyDescriptor
     * @param collector
     *            the CellEditorProviderCollector used to retrieve the
     *            ICellEditorProviders
     */
    public SiriusExtensiblePropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor, CellEditorProviderCollector collector) {
        super(object, itemPropertyDescriptor, collector);
    }

    @Override
    public CellEditor createPropertyEditor(Composite parent) {
        CellEditor cellEditor = null;
        if (!(object instanceof EObject) || getPermissionAuthority() != null && getPermissionAuthority().canEditInstance((EObject) object)) {
            cellEditor = super.createPropertyEditor(parent);
        }
        return cellEditor;
    }

    private IPermissionAuthority getPermissionAuthority() {
        if (permissionAuthority == null && object instanceof EObject) {
            Session session = new EObjectQuery((EObject) object).getSession();
            if (session != null) {
                ModelAccessor modelAccessor = session.getModelAccessor();
                if (modelAccessor != null) {
                    permissionAuthority = modelAccessor.getPermissionAuthority();
                }
            }
        }
        return permissionAuthority;
    }

}
