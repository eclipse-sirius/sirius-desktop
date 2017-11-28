/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;

import junit.framework.TestCase;

public class EqualityHelperTestCase extends TestCase {

    private static final String MODEL_FILE = "/org.eclipse.sirius.tests.junit/data/unit/dialect/dialect.odesign";

    public void testSameObjectReferences() throws IOException {

        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        assertTrue(EqualityHelper.areEquals(ePackage, ePackage));
        final EPackage eDifferentPackage = EcoreFactory.eINSTANCE.createEPackage();
        assertFalse(EqualityHelper.areEquals(ePackage, eDifferentPackage));

        final ResourceSet set = new ResourceSetImpl();
        final EObject odesign = ModelUtils.load(URI.createPlatformPluginURI(MODEL_FILE, true), set);
        assertTrue(EqualityHelper.areEquals(odesign, odesign));
    }

    public void testObjectsInDifferentResources() throws IOException {
        final ResourceSet set = new ResourceSetImpl();
        final EObject odesign = ModelUtils.load(URI.createPlatformPluginURI(MODEL_FILE, true), set);

        final ResourceSet set2 = new ResourceSetImpl();
        final EObject odesignSecondVersion = ModelUtils.load(URI.createPlatformPluginURI(MODEL_FILE, true), set2);

        assertTrue(EqualityHelper.areEquals(odesign, odesignSecondVersion));

    }

    public void testRemoveWithObjectsInDifferentResources() throws IOException {
        final ResourceSet set = new ResourceSetImpl();
        final EObject odesign = ModelUtils.load(URI.createPlatformPluginURI(MODEL_FILE, true), set);

        final ResourceSet set2 = new ResourceSetImpl();
        final EObject odesignSecondVersion = ModelUtils.load(URI.createPlatformPluginURI(MODEL_FILE, true), set2);

        final Collection<EObject> odesigns = new HashSet<EObject>();
        odesigns.add(odesign);

        assertTrue(odesigns.size() == 1);
        EqualityHelper.remove(odesigns, odesignSecondVersion);
        assertTrue(odesigns.isEmpty());
    }

    public void test1NullObjects() throws IOException {
        final ResourceSet set = new ResourceSetImpl();
        final EObject odesign = ModelUtils.load(URI.createPlatformPluginURI(MODEL_FILE, true), set);
        assertFalse(EqualityHelper.areEquals(odesign, null));
        assertFalse(EqualityHelper.areEquals(null, odesign));
    }

    public void test2NullObjects() {
        assertTrue(EqualityHelper.areEquals(null, null));
    }

}
