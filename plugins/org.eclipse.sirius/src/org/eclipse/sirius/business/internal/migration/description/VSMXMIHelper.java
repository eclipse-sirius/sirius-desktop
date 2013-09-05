/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.description;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.osgi.framework.Version;

/**
 * VSM XMI Helper implementation. Delegate the setValue to VSMMigrationService.
 * 
 * @author fbarbin
 * 
 */
public class VSMXMIHelper extends XMIHelperImpl {
    private String version;

    private boolean migrationNeeded;

    /**
     * Constructor.
     * 
     * @param resource
     *            the resource creating that helper.
     * @param version
     *            the mm version
     */
    public VSMXMIHelper(String version, XMLResource resource) {
        super(resource);
        this.version = version;
        this.migrationNeeded = version == null || VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version));
    }

    @Override
    public void setValue(EObject object, EStructuralFeature feature, Object value, int position) {
        Object newValue = null;
        if (migrationNeeded) {
            newValue = VSMMigrationService.getInstance().getValue(object, feature, value, version);
        }

        if (newValue != null) {
            super.setValue(object, feature, newValue, position);
        } else {
            super.setValue(object, feature, value, position);
        }

    }
}
