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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
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
     */
    public VSMXMIHelper(XMLResource resource) {
        super(resource);

        VSMVersionSAXParser parser = new VSMVersionSAXParser(resource.getURI());
        this.version = parser.getVersion(new NullProgressMonitor());
        this.migrationNeeded = VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version));
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

    @Override
    public EObject createObject(EFactory eFactory, EClassifier type) {
        EFactory factory = eFactory;
        if (migrationNeeded) {
            // Handle EClass moved from one package to another,
            // prefix is not sufficient to retrieve the new package and factory
            // in
            // org.eclipse.emf.ecore.xmi.impl.XMLHandler.getFactoryForPrefix(String)
            // which does factory =
            // ePackage.getEFactoryInstance();
            // The migration participants return the new type with EClassifier
            // getType(EPackage, String, String).
            // Then we get the factory instance from the EClassifier's EPackage.
            if (type != null && type.getEPackage() != null && type.getEPackage().getEFactoryInstance() != null) {
                factory = type.getEPackage().getEFactoryInstance();
            }
        }
        EObject newObject = super.createObject(factory, type);
        if (migrationNeeded) {
            newObject = VSMMigrationService.getInstance().updateCreatedObject(newObject, version);
        }
        return newObject;
    }
}
