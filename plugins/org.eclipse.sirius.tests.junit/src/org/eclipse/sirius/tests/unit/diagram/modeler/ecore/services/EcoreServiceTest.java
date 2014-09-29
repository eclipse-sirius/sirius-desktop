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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.sirius.ecore.design.service.EcoreService;
import org.eclipse.sirius.ext.emf.AllContents;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EcoreServiceTest {
    private EcoreService service;

    private EPackage ecore;

    @BeforeClass
    public static void initializeEMF() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            @SuppressWarnings("unused")
            EPackage ecore = EcorePackage.eINSTANCE;
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        }
    }

    @Before
    public void createService() {
        service = new EcoreService();
        ecore = EcorePackage.eINSTANCE;
    }

    @Test
    public void no_roots_for_ecore_package() {
        Collection<EObject> roots = service.allRoots(ecore);
        assertNotNull(roots);
        assertEquals(0, roots.size());
    }

    @Test
    public void not_roots_for_loose_eobject() {
        EObject eobj = EcoreFactory.eINSTANCE.createEObject();
        assertNotNull(eobj);
        Collection<EObject> roots = service.allRoots(eobj);
        assertNotNull(roots);
        assertEquals(0, roots.size());
    }

    @Test
    public void roots() {
        ResourceSet rs = new ResourceSetImpl();
        Resource res1 = rs.createResource(URI.createFileURI("test1.ecore"));
        Resource res2 = rs.createResource(URI.createFileURI("test2.ecore"));
        EObject eobj1 = EcoreFactory.eINSTANCE.createEObject();
        EObject eobj2 = EcoreFactory.eINSTANCE.createEObject();

        res1.getContents().add(eobj1);
        res1.getContents().add(EcoreFactory.eINSTANCE.createEObject());
        Collection<EObject> roots = service.allRoots(eobj1);
        assertNotNull(roots);
        assertEquals(2, roots.size());
        assertTrue(roots.contains(eobj1));

        res2.getContents().add(eobj2);
        roots = service.allRoots(eobj1);
        assertNotNull(roots);
        assertEquals(3, roots.size());
        assertTrue(roots.contains(eobj1));
        assertTrue(roots.contains(eobj2));

        assertEquals(service.allRoots(eobj1), service.allRoots(eobj2));
    }

    @Test
    public void find_ecore_classifier_by_name() {
        EObject eobj = EcoreFactory.eINSTANCE.createEObject();
        String[] whitespace = { "", " ", "   ", "\t", " \t  " };

        for (EObject obj : AllContents.of(EcorePackage.eINSTANCE)) {
            if (obj instanceof EClassifier) {
                EClassifier type = (EClassifier) obj;
                String name = type.getName();
                for (String prefix : whitespace) {
                    for (String suffix : whitespace) {
                        assertSame(type, service.findTypeByName(eobj, prefix + name + suffix));
                        assertSame(type, service.findTypeByName(eobj, prefix + name.toLowerCase() + suffix));
                        assertSame(type, service.findTypeByName(eobj, prefix + name.toUpperCase() + suffix));
                    }
                }
            }
        }
    }
}
