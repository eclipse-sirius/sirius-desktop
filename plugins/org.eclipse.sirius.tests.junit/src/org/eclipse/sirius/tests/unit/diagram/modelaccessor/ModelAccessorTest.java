/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.modelaccessor;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderService;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Test case for {@link ModelAccessor}.
 * 
 * @author mporhel
 */
public class ModelAccessorTest extends TestCase {

    /** The model. */
    private ModelAccessor accessor;

    /** The metamodel descriptors */
    private List<MetamodelDescriptor> descriptors;

    private final String instanciableEClass = "DAnalysis";

    private final String abstractEClass = "DDiagramElement";

    private final String interfaceEClass = "DDiagramElement";

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ResourceSet resourceSet = new ResourceSetImpl();
        accessor = ExtenderService.createModelAccessor(resourceSet);
        accessor.init(resourceSet);

        descriptors = new ArrayList<MetamodelDescriptor>();
        EcoreMetamodelDescriptor descriptor = new EcoreMetamodelDescriptor(ViewpointPackage.eINSTANCE);
        descriptors.add(descriptor);

        accessor.activateMetamodels(descriptors);
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        accessor.dispose();
        accessor = null;

        descriptors.clear();
        descriptors = null;
    }

    /**
     * Tests the eIsKnown() method of ModelAccessor.
     * 
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testModelAccessorEIsKnown() throws Exception {
        assertTrue("Model Accessor should know this EClass ", accessor.eIsKnownType(this.instanciableEClass));
        assertTrue("Model Accessor should know this EClass ", accessor.eIsKnownType(this.abstractEClass));
        assertTrue("Model Accessor should know this EClass ", accessor.eIsKnownType(this.interfaceEClass));
    }

    /**
     * Tests the createInstance() method of ModelAccessor.
     * 
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testModelAccessorCreateInstance() throws Exception {
        assertNotNull("Model Accessor should instantiate this EClass ", accessor.createInstance(this.instanciableEClass));
        assertEquals("Model Accessor should instantiate this EClass ", this.instanciableEClass, accessor.createInstance(this.instanciableEClass).eClass().getName());

        try {
            accessor.createInstance(this.abstractEClass);
            fail("Model Accessor should not instantiate this EClass ");
        } catch (MetaClassNotFoundException e) {
            // wanted behavior : abtractEClass cannot be instantiated.
        }

        try {
            accessor.createInstance(this.interfaceEClass);
            fail("Model Accessor should not instantiate this EClass ");
        } catch (MetaClassNotFoundException e) {
            // wanted behavior : interfaceEClass cannot be instantiated.
        }

    }

    public void testModelAccesorConvertDataTypes() throws Exception {
        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("ConvertTest");
        ePackage.getEClassifiers().add(eClass);

        EAttribute integerAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        integerAttribute.setName("testInteger");
        integerAttribute.setEType(EcorePackage.eINSTANCE.getEInt());
        eClass.getEStructuralFeatures().add(integerAttribute);

        EAttribute booleanAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        booleanAttribute.setName("testBoolean");
        booleanAttribute.setEType(EcorePackage.eINSTANCE.getEBoolean());
        eClass.getEStructuralFeatures().add(booleanAttribute);

        EFactory eFactory = ePackage.getEFactoryInstance();
        EObject convertTest = eFactory.create(eClass);
        accessor.eSet(convertTest, "testInteger", "123");
        assertEquals(123, accessor.eGet(convertTest, "testInteger"));

        accessor.eSet(convertTest, "testBoolean", "true");
        assertEquals(true, accessor.eGet(convertTest, "testBoolean"));

    }

    /**
     * Ensures that model {@link ModelAccessor} works as expected when setting
     * values to {@link EEnum} features.
     * 
     * @throws Exception
     *             if unexpected issues are thrown while manipulating the
     *             {@link ModelAccessor}
     */
    public void testModelAccesorWithEEnum() throws Exception {
        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("EEnumTest");
        ePackage.getEClassifiers().add(eClass);

        EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
        EEnumLiteral literal1 = EcoreFactory.eINSTANCE.createEEnumLiteral();
        literal1.setName("literal1 Name");
        literal1.setLiteral("literal1 Literal");
        literal1.setValue(0);
        eEnum.getELiterals().add(literal1);
        EEnumLiteral literal2 = EcoreFactory.eINSTANCE.createEEnumLiteral();
        literal2.setName("literal2 Name");
        literal2.setLiteral("literal2 Literal");
        literal2.setValue(1);
        eEnum.getELiterals().add(literal2);

        EAttribute eenumAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eenumAttribute.setName("eenumAttribute");
        eenumAttribute.setEType(eEnum);
        eClass.getEStructuralFeatures().add(eenumAttribute);

        EFactory eFactory = ePackage.getEFactoryInstance();
        EObject instance = eFactory.create(eClass);

        // Check with value
        assertFalse("eenumAttribute should not have value yet", instance.eIsSet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal1.getValue());
        assertEquals("eenumAttribute has not the expected value", literal1, instance.eGet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal2.getValue());
        assertEquals("eenumAttribute has not the expected value", literal2, instance.eGet(eenumAttribute));

        accessor.eSet(instance, eenumAttribute.getName(), -1);
        assertEquals("eenumAttribute should not have changed as value is invalid", literal2, instance.eGet(eenumAttribute));

        // Check with name
        instance.eUnset(eenumAttribute);
        assertFalse("eenumAttribute should not have value yet", instance.eIsSet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal1.getName());
        assertEquals("eenumAttribute has not the expected value", literal1, instance.eGet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal2.getName());
        assertEquals("eenumAttribute has not the expected value", literal2, instance.eGet(eenumAttribute));

        // Check with literal
        instance.eUnset(eenumAttribute);
        assertFalse("eenumAttribute should not have value yet", instance.eIsSet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal1.getLiteral());
        assertEquals("eenumAttribute has not the expected value", literal1, instance.eGet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal2.getLiteral());
        assertEquals("eenumAttribute has not the expected value", literal2, instance.eGet(eenumAttribute));

        // Check with Enumerator
        instance.eUnset(eenumAttribute);
        assertFalse("eenumAttribute should not have value yet", instance.eIsSet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal1);
        assertEquals("eenumAttribute has not the expected value", literal1, instance.eGet(eenumAttribute));
        accessor.eSet(instance, eenumAttribute.getName(), literal2);
        assertEquals("eenumAttribute has not the expected value", literal2, instance.eGet(eenumAttribute));

        // Check invalid values and name/literal/enumerator
        accessor.eSet(instance, eenumAttribute.getName(), "invalidValue");
        assertEquals("eenumAttribute should not have changed as value is invalid", literal2, instance.eGet(eenumAttribute));

        try {
            accessor.eSet(instance, eenumAttribute.getName(), FilterKind.COLLAPSE_LITERAL);
            fail("A ClassCastException was expected on eSet with an enumerator from a wrong EEnum.");
        } catch (ClassCastException e) {
            // ok
        }
        assertEquals("eenumAttribute should not have changed as value is invalid", literal2, instance.eGet(eenumAttribute));

    }

    public void testModelAccesorInstanceOf() throws Exception {
        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        /* should return false, and not failed */
        boolean isIntance = accessor.eInstanceOf(ePackage, null);
        assertFalse(isIntance);
    }

    public void testModelAccesorInstanceOf2() throws Exception {
        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        /* should return true (#480) */
        boolean isIntance = accessor.eInstanceOf(ePackage, ePackage.eClass().getName().concat(" "));
        assertTrue(isIntance);
    }
}
