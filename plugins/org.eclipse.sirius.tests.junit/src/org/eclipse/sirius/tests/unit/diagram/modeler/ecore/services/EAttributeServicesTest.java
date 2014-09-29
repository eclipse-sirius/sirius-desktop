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
import static org.junit.Assert.assertNull;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.sirius.ecore.design.service.EAttributeServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Automated tests for EAttributeServices.
 * 
 * @author pcdavid
 */
public class EAttributeServicesTest {
    private EAttributeServices service;

    private EAttribute attr;

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
        service = new EAttributeServices();
        attr = EcoreFactory.eINSTANCE.createEAttribute();
    }

    @Test
    public void render_basic_eattribute() {
        attr.setName("attr");
        attr.setEType(EcorePackage.eINSTANCE.getEString());

        assertEquals("attr : EString", service.render(attr));
    }

    @Test
    public void render_derived_eattribute() {
        attr.setName("attr");
        attr.setEType(EcorePackage.eINSTANCE.getEString());
        attr.setDerived(true);

        assertEquals("/attr : EString", service.render(attr));
    }

    @Test
    public void render_anonymous_attribute() {
        attr.setEType(EcorePackage.eINSTANCE.getEString());

        assertEquals(": EString", service.render(attr));
        attr.setDerived(true);
        assertEquals(": EString", service.render(attr));
    }

    @Test
    public void render_untyped_attribute() {
        attr.setName("attr");
        assertEquals("attr", service.render(attr));
    }

    @Test
    public void render_attribute_with_default_value() {
        attr.setName("attr");

        attr.setEType(EcorePackage.eINSTANCE.getEString());
        attr.setDefaultValue("default");
        assertEquals("attr : EString = default", service.render(attr));

        attr.setEType(EcorePackage.eINSTANCE.getEInt());
        attr.setDefaultValue(0);
        assertEquals("attr : EInt = 0", service.render(attr));

        attr.setEType(EcorePackage.eINSTANCE.getEBoolean());
        attr.setDefaultValue(Boolean.TRUE);
        assertEquals("attr : EBoolean = true", service.render(attr));
    }

    @Test
    public void render_attribute_with_default_value_literal() {
        attr.setName("attr");
        attr.setEType(EcorePackage.eINSTANCE.getEString());
        attr.setDefaultValueLiteral("\"default\"");
        assertEquals("attr : EString = \"default\"", service.render(attr));

        attr.setDefaultValue("default"); // resets defaultValueLiteral
        assertEquals("attr : EString = default", service.render(attr));

        attr.setDefaultValueLiteral("\"default\""); // explicit override
        assertEquals("attr : EString = \"default\"", service.render(attr));
    }

    @Test
    public void render_untyped_anonymous_attribute() {
        assertEquals("", service.render(attr));
    }

    @Test
    public void edit_empty_attribute_with_name() {
        service.performEdit(attr, "attr");
        assertEquals("attr", attr.getName());
    }

    @Test
    public void edit_empty_attribute_with_name_and_type() {
        service.performEdit(attr, "attr : EString");
        assertEquals("attr", attr.getName());
        assertEquals(EcorePackage.eINSTANCE.getEString(), attr.getEType());
    }

    @Test
    public void edit_empty_attribute_with_name_and_type_and_default() {
        service.performEdit(attr, "attr : EInt = 0");
        assertEquals("attr", attr.getName());
        assertEquals(EcorePackage.eINSTANCE.getEInt(), attr.getEType());
        assertEquals("0", attr.getDefaultValueLiteral());
    }

    @Test
    public void edit_empty_attribute_with_type() {
        service.performEdit(attr, ":EBoolean");
        assertEquals(null, attr.getName());
        assertEquals(EcorePackage.eINSTANCE.getEBoolean(), attr.getEType());
        assertEquals(EcorePackage.eINSTANCE.getEBoolean().getDefaultValue(), attr.getDefaultValue());
        assertEquals(null, attr.getDefaultValueLiteral());
    }

    @Test
    public void edit_empty_attribute_with_type_and_default() {
        service.performEdit(attr, ":EBoolean = true");
        assertEquals(null, attr.getName());
        assertEquals(EcorePackage.eINSTANCE.getEBoolean(), attr.getEType());
        assertEquals(Boolean.TRUE, attr.getDefaultValue());
        assertEquals("true", attr.getDefaultValueLiteral());
    }

    @Test
    public void edit_empty_attribute_with_name_and_default() {
        service.performEdit(attr, "attr=true");
        assertEquals("attr", attr.getName());
        assertEquals(null, attr.getEType());
        assertEquals(null, attr.getDefaultValue());
        assertEquals("true", attr.getDefaultValueLiteral());
    }

    @Test
    public void edit_empty_attribute_with_default() {
        service.performEdit(attr, "=true");
        assertEquals(null, attr.getName());
        assertEquals(null, attr.getEType());
        assertEquals(null, attr.getDefaultValue());
        assertEquals("true", attr.getDefaultValueLiteral());
    }

    @Test
    public void edit_empty_attribute_with_nothing() {
        service.performEdit(attr, "");
        assertIsBlank(attr);
        service.performEdit(attr, ":");
        assertIsBlank(attr);
        service.performEdit(attr, "=");
        assertIsBlank(attr);
        service.performEdit(attr, ":=");
        assertIsBlank(attr);
    }

    @Test
    public void edit_named_attribute_with_name() {
        attr.setName("before");
        service.performEdit(attr, "after");
        assertEquals("after", attr.getName());
    }

    @Test
    public void edit_attribute_set_derived() {
        attr.setName("name");
        attr.setDerived(false);
        service.performEdit(attr, "/newName");
        assertEquals("newName", attr.getName());
        assertEquals(true, attr.isDerived());
    }

    @Test
    public void edit_attribute_set_not_derived() {
        attr.setName("name");
        attr.setDerived(true);
        service.performEdit(attr, "newName");
        assertEquals("newName", attr.getName());
        assertEquals(false, attr.isDerived());
    }

    @Test
    public void edit_named_attribute_with_name_and_whitespace() {
        attr.setName("before");
        service.performEdit(attr, "   after \t  ");
        assertEquals("after", attr.getName());
    }

    private void assertIsBlank(EAttribute attr) {
        assertNull(attr.getName());
        assertNull(attr.getEType());
        assertNull(attr.getDefaultValue());
        assertNull(attr.getDefaultValueLiteral());
    }
}
