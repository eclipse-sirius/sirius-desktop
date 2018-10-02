/**
 * Copyright (c) 2015 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.scxml.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.sirius.tests.sample.scxml.ScxmlPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ScxmlXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public ScxmlXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        ScxmlPackage.eINSTANCE.eClass();
    }

    /**
     * Register for "*" and "xml" file extensions the ScxmlResourceFactoryImpl
     * factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XMLProcessor.XML_EXTENSION, new ScxmlResourceFactoryImpl());
            registrations.put(XMLProcessor.STAR_EXTENSION, new ScxmlResourceFactoryImpl());
        }
        return registrations;
    }

} // ScxmlXMLProcessor
