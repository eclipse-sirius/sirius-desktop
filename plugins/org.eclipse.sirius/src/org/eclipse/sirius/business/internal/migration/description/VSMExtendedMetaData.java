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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;

/**
 * VSM extended meta data implementation. Delegates to
 * {@link VSMMigrationService}.
 * 
 * @author fbarbin
 * 
 */
public class VSMExtendedMetaData extends BasicExtendedMetaData {

    private String version;

    /**
     * Constructor.
     * 
     * @param version
     *            the current version of the model to migrate.
     */
    public VSMExtendedMetaData(String version) {
        this.version = version;
    }

    @Override
    public EStructuralFeature getAttribute(EClass eClass, String namespace, String name) {
        EStructuralFeature attribute = VSMMigrationService.getInstance().getAttribute(eClass, name, version);
        if (attribute != null) {
            return attribute;
        }
        return super.getAttribute(eClass, namespace, name);

    }

    @Override
    protected EStructuralFeature getLocalElement(EClass eClass, String namespace, String name) {
        EStructuralFeature feature = VSMMigrationService.getInstance().getLocalElement(eClass, name, version);
        if (feature != null) {
            return feature;
        }
        return super.getLocalElement(eClass, namespace, name);
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name) {
        EClassifier type = VSMMigrationService.getInstance().getType(ePackage, name, version);
        if (type != null) {
            return type;
        }
        return super.getType(ePackage, name);
    }

    @Override
    public EPackage getPackage(String namespace) {
        EPackage ePackage = VSMMigrationService.getInstance().getPackage(namespace, version);
        if (ePackage != null) {
            return ePackage;
        }
        return super.getPackage(namespace);
    }
}
