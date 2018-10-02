/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.junit.Test;

/**
 * Unit tests for {@link EcoreIntrinsicExtender}.
 * 
 * @author laurent.redor@obeo.fr
 */
public class EcoreIntrinsicExtenderTest {

    /**
     * This test has been added to detect problem during the first update of the
     * metamodels.
     */
    @Test
    public void udpateMetamodels() {
        EcoreIntrinsicExtender extender = new EcoreIntrinsicExtender();
        extender.updateMetamodels(Collections.EMPTY_LIST);
    }

    /**
     * Test the method
     * {@link EcoreIntrinsicExtender#eInstanceOf(org.eclipse.emf.ecore.EObject, String)}
     * . Check that the extender can match an EClass with the corresponding
     * viewpoint declaration using AQL syntax (::) when using the method.
     */
    @Test
    public void testEInstanceOf() {
        EcoreIntrinsicExtender extender = new EcoreIntrinsicExtender();
        final Collection<EcoreMetamodelDescriptor> descriptors = new ArrayList<EcoreMetamodelDescriptor>();
        descriptors.add(new EcoreMetamodelDescriptor(EcorePackage.eINSTANCE));
        extender.updateMetamodels(descriptors);
        assertFalse(extender.eInstanceOf(EcoreFactory.eINSTANCE.createEAttribute(), "ecore-EAttribute"));
        assertTrue(extender.eInstanceOf(EcoreFactory.eINSTANCE.createEAttribute(), "ecore::EAttribute"));
    }

    /**
     * Test the method {@link EcoreIntrinsicExtender#createInstance(String)} .
     * Check that the extender can create an EClass with the corresponding
     * viewpoint declaration using AQL syntax (::).
     */
    @Test
    public void testCreateInstance() {
        EcoreIntrinsicExtender extender = new EcoreIntrinsicExtender();
        final Collection<EcoreMetamodelDescriptor> descriptors = new ArrayList<EcoreMetamodelDescriptor>();
        descriptors.add(new EcoreMetamodelDescriptor(EcorePackage.eINSTANCE));
        extender.updateMetamodels(descriptors);
        assertNull(extender.createInstance("ecore-EAttribute"));
        assertNotNull(extender.createInstance("ecore::EAttribute"));
    }

    /**
     * Test the method {@link EcoreIntrinsicExtender#eIsKnownType(String)}.
     * Check that the extender can match an EClass with the corresponding
     * viewpoint declaration using AQL syntax (::) when using the method.
     */
    @Test
    public void testFindFirstEClassFromName() {
        EcoreIntrinsicExtender extender = new EcoreIntrinsicExtender();
        final Collection<EcoreMetamodelDescriptor> descriptors = new ArrayList<EcoreMetamodelDescriptor>();
        descriptors.add(new EcoreMetamodelDescriptor(EcorePackage.eINSTANCE));
        extender.updateMetamodels(descriptors);
        assertFalse(extender.eIsKnownType("ecore-EAttribute"));
        assertTrue(extender.eIsKnownType("ecore::EAttribute"));
    }
}
