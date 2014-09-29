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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.sirius.ecore.design.service.EOperationServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Automated tests for EOperationServices.
 * 
 * @author pcdavid
 */
public class EOperationServicesTest {
    private EOperationServices service;

    private EOperation op;

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
        service = new EOperationServices();
        op = EcoreFactory.eINSTANCE.createEOperation();
    }

    @Test
    public void render_empty_operation() {
        assertEquals("()", service.render(op));
    }

    @Test
    public void render_named_operation_untyped_no_parameters() {
        op.setName("foo");
        assertEquals("foo()", service.render(op));
    }

    @Test
    public void render_named_operation_typed_no_parameters() {
        op.setName("foo");
        op.setEType(EcorePackage.eINSTANCE.getEString());
        assertEquals("foo() : EString", service.render(op));
    }

    @Test
    public void render_operation_anonymous_untyped_parameter() {
        op.setName("foo");
        op.setEType(EcorePackage.eINSTANCE.getEString());
        EParameter p1 = EcoreFactory.eINSTANCE.createEParameter();
        op.getEParameters().add(p1);
        assertEquals("foo() : EString", service.render(op));
    }

    @Test
    public void render_full_operation_one_parameter() {
        op.setName("foo");
        op.setEType(EcorePackage.eINSTANCE.getEString());
        EParameter p1 = EcoreFactory.eINSTANCE.createEParameter();
        p1.setName("p1");
        p1.setEType(EcorePackage.eINSTANCE.getEInt());
        op.getEParameters().add(p1);
        assertEquals("foo(p1 EInt) : EString", service.render(op));
    }

    @Test
    public void render_full_operation_multiple_parameter() {
        op.setName("foo");
        op.setEType(EcorePackage.eINSTANCE.getEString());

        EParameter p1 = EcoreFactory.eINSTANCE.createEParameter();
        p1.setName("p1");
        p1.setEType(EcorePackage.eINSTANCE.getEInt());
        op.getEParameters().add(p1);

        EParameter p2 = EcoreFactory.eINSTANCE.createEParameter();
        p2.setName("p2");
        p2.setEType(EcorePackage.eINSTANCE.getEBoolean());
        op.getEParameters().add(p2);

        EParameter p3 = EcoreFactory.eINSTANCE.createEParameter();
        p3.setName("p3");
        p3.setEType(EcorePackage.eINSTANCE.getEObject());
        op.getEParameters().add(p3);

        assertEquals("foo(p1 EInt, p2 EBoolean, p3 EObject) : EString", service.render(op));
    }

    @Test
    public void edit_empty_operation_with_nothing() {
        assertIsBlank(op);

        service.performEdit(op, "");
        assertIsBlank(op);

        service.performEdit(op, "   ");
        assertIsBlank(op);

        service.performEdit(op, ":");
        assertIsBlank(op);

        service.performEdit(op, " :  ");
        assertIsBlank(op);

        service.performEdit(op, "():");
        assertIsBlank(op);

        service.performEdit(op, "  (   ) :  ");
        assertIsBlank(op);
    }

    @Test
    public void parse_single_parameter_blank_string() {
        String[] param = service.parseParameter("");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertNull(param[1]);

        param = service.parseParameter("   ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertNull(param[1]);

        param = service.parseParameter(" \t  ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertNull(param[1]);
    }

    @Test
    public void parse_single_parameter_name_only() {
        String[] param = service.parseParameter(" name  ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("name", param[0]);
        assertNull(param[1]);

        param = service.parseParameter(" name : ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("name", param[0]);
        assertNull(param[1]);

        param = service.parseParameter("name:");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("name", param[0]);
        assertNull(param[1]);
    }

    @Test
    public void parse_single_parameter_type_only() {
        String[] param = service.parseParameter(":T");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter(" :T  ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter(" : T");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter(": \t T\t");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertEquals("T", param[1]);
    }

    @Test
    public void parse_single_parameter_name_and_type_colon() {
        String[] param = service.parseParameter("n:T");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter(" n:T ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter("n : T");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter("  n: T ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter("  n :T ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);
    }

    @Test
    public void parse_single_parameter_name_and_type_space() {
        String[] param = service.parseParameter("n T");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter(" n T ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter("n T");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter("  n T ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);

        param = service.parseParameter("  n\tT ");
        assertNotNull(param);
        assertEquals(2, param.length);
        assertEquals("n", param[0]);
        assertEquals("T", param[1]);
    }

    @Test
    public void edit_blank_operation_set_name() {
        String[] inputs = { "name", " name ", " name", "name ", "  name \t\t  " };

        for (String editString : inputs) {
            op.setName(null);
            service.performEdit(op, editString);
            assertEquals("name", op.getName());
            assertNotNull(op.getEParameters());
            assertEquals(0, op.getEParameters().size());
            assertNull(op.getEType());
        }
    }

    @Test
    public void edit_blank_operation_set_type() {
        String[] inputs = { ":EBoolean", " : EBoolean ", " :EBoolean", ":EBoolean  ", "  :   EBoolean \t\t  " };

        for (String editString : inputs) {
            op.setEType(null);
            service.performEdit(op, editString);
            assertNull(op.getName());
            assertNotNull(op.getEParameters());
            assertEquals(0, op.getEParameters().size());
            assertEquals(EcorePackage.eINSTANCE.getEBoolean(), op.getEType());
        }
    }

    @Test
    public void edit_blank_operation_set_name_and_type() {
        String[] inputs = { "name:EBoolean", "name : EBoolean ", " name :EBoolean", "name : EBoolean  ", " name   :   EBoolean \t\t  ", "name():EBoolean", "name (): EBoolean", " name( ) :EBoolean" };

        for (String editString : inputs) {
            op.setName(null);
            op.setEType(null);
            service.performEdit(op, editString);
            assertEquals("name", op.getName());
            assertNotNull(op.getEParameters());
            assertEquals(0, op.getEParameters().size());
            assertEquals(EcorePackage.eINSTANCE.getEBoolean(), op.getEType());
        }
    }

    @Test
    public void edit_blank_operation_set_name_param_and_type() {
        String[] inputs = { "name(p:EInt):EBoolean", "name (p: EInt): EBoolean ", " name(p EINT) :EBoolean", "name (p: EInt): EBoolean  ", " name ( p eint)  :   EBoolean \t\t  ",
                "name(p:eint):EBoolean", "name (p  :EInT): EBoolean", " name(  p:  eint) :EBoolean" };

        for (String editString : inputs) {
            op.setName(null);
            op.getEParameters().clear();
            op.setEType(null);
            service.performEdit(op, editString);
            assertEquals("name", op.getName());
            assertNotNull(op.getEParameters());
            assertEquals(1, op.getEParameters().size());
            EParameter param = op.getEParameters().get(0);
            assertEquals("p", param.getName());
            assertEquals(EcorePackage.eINSTANCE.getEInt(), param.getEType());
            assertEquals(EcorePackage.eINSTANCE.getEBoolean(), op.getEType());
        }
    }

    @Test
    public void edit_blank_operation_set_name_params_and_type() {
        String[] inputs = { "name(p:EInt, p2:EOBJECT):EBoolean", "name (p: EInt  ,p2:eobject): EBoolean ", " name(p EINT,p2:EObject  ) :EBoolean", "name (p: EInt,p2 EObject\t): EBoolean  ",
                " name ( p eint, p2\teobjecT\t)  :   EBoolean \t\t  ", "name(p:eint,p2 :EOBJECT):EBoolean", "name (p  :EInT   ,p2    :EObject): EBoolean", " name(  p:  eint, p2 EObject) :EBoolean" };

        for (String editString : inputs) {
            op.setName(null);
            op.getEParameters().clear();
            op.setEType(null);
            service.performEdit(op, editString);
            assertEquals("name", op.getName());
            assertNotNull(op.getEParameters());
            assertEquals(2, op.getEParameters().size());
            EParameter p1 = op.getEParameters().get(0);
            assertEquals("p", p1.getName());
            assertEquals(EcorePackage.eINSTANCE.getEInt(), p1.getEType());

            EParameter p2 = op.getEParameters().get(1);
            assertEquals("p2", p2.getName());
            assertEquals(EcorePackage.eINSTANCE.getEObject(), p2.getEType());

            assertEquals(EcorePackage.eINSTANCE.getEBoolean(), op.getEType());
        }
    }

    @Test
    public void edit_operation_change_name() {
        String[] inputs = { "newName", "  newName", "newName  ", "  newName   ", "newName" };
        for (String editString : inputs) {
            fillOperation(op);
            service.performEdit(op, editString);
            assertEquals("newName", op.getName());
            assertEquals(2, op.getEParameters().size());
            assertParameter(op, 0, "p1", EcorePackage.eINSTANCE.getEBoolean());
            assertParameter(op, 1, "p2", EcorePackage.eINSTANCE.getEInt());
            assertEquals(EcorePackage.eINSTANCE.getEBoolean(), op.getEType());
        }
    }

    @Test
    public void edit_operation_change_parameter_no_type_change() {
        fillOperation(op);
        service.performEdit(op, "name(p1:EString)");
        assertEquals("name", op.getName());
        assertEquals(1, op.getEParameters().size());
        assertParameter(op, 0, "p1", EcorePackage.eINSTANCE.getEString());
        assertEquals(EcorePackage.eINSTANCE.getEBoolean(), op.getEType());
    }

    @Test
    public void edit_operation_change_type_no_name_change() {
        fillOperation(op);
        service.performEdit(op, ": EInt");
        assertEquals("name", op.getName());
    }

    private void fillOperation(EOperation op) {
        op.setName("name");
        op.getEParameters().clear();
        EParameter p = EcoreFactory.eINSTANCE.createEParameter();
        p.setName("p1");
        p.setEType(EcorePackage.eINSTANCE.getEBoolean());
        op.getEParameters().add(p);

        p = EcoreFactory.eINSTANCE.createEParameter();
        p.setName("p2");
        p.setEType(EcorePackage.eINSTANCE.getEInt());
        op.getEParameters().add(p);

        op.setEType(EcorePackage.eINSTANCE.getEBoolean());
    }

    private void assertParameter(EOperation op, int index, String name, EClassifier type) {
        assertTrue("Not parameter at " + index, index < op.getEParameters().size());
        EParameter param = op.getEParameters().get(index);
        assertEquals(name, param.getName());
        assertEquals(type, param.getEType());
    }

    private void assertIsBlank(EOperation op) {
        assertNull(op.getName());
        assertNull(op.getEType());
        assertNotNull(op.getEParameters());
        assertEquals(0, op.getEParameters().size());
    }
}
