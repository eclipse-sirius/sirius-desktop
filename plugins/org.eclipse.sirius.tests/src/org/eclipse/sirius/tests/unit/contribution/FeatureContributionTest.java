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
package org.eclipse.sirius.tests.unit.contribution;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.sirius.business.internal.contribution.FeatureContributor;
import org.eclipse.sirius.description.contribution.ContributionFactory;
import org.eclipse.sirius.description.contribution.FeatureContribution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Objects;

/**
 * Unit tests for the application of feature contributions. We use Ecore models
 * built in memory for convenience. This test can run in standalone (outside
 * Eclipse).
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class FeatureContributionTest {
    private EClass targetClass;

    private EClass sourceClass;

    private EAttribute nameFeature;

    private EReference supertypesFeature;

    private ContributionFactory factory = ContributionFactory.eINSTANCE;

    /**
     * Make sure EMF is properly initialized when we are run outside of Eclipse.
     */
    @BeforeClass
    public static void initializeEMF() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            @SuppressWarnings("unused")
            EPackage ecore = EcorePackage.eINSTANCE;
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        }
    }

    @Before
    public void setup() {
        targetClass = EcoreFactory.eINSTANCE.createEClass();
        targetClass.setName("base");
        sourceClass = EcoreFactory.eINSTANCE.createEClass();
        sourceClass.setName("contrib");
        Freezer.freeze(sourceClass); // Forbid change by default.
        nameFeature = EcorePackage.eINSTANCE.getENamedElement_Name();
        supertypesFeature = EcorePackage.eINSTANCE.getEClass_ESuperTypes();
    }

    @Test
    public void ignore_does_not_change_target() {
        String nameBefore = targetClass.getName();
        FeatureContribution fc = factory.createIgnoreFeatureContribution();
        fc.setSourceFeature(nameFeature);
        fc.setTargetFeature(nameFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
        
        Assert.assertEquals(nameBefore, targetClass.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ignore_fails_if_source_feature_missing() {
        FeatureContribution fc = factory.createIgnoreFeatureContribution();
        fc.setTargetFeature(nameFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ignore_fails_if_target_feature_missing() {
        FeatureContribution fc = factory.createIgnoreFeatureContribution();
        fc.setTargetFeature(nameFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
    }

    @Test(expected = NullPointerException.class)
    public void ignore_fails_if_source_missing() {
        FeatureContribution fc = factory.createIgnoreFeatureContribution();
        fc.setSourceFeature(nameFeature);
        fc.setTargetFeature(nameFeature);
        new FeatureContributor(fc).apply(targetClass, null);
    }

    @Test(expected = NullPointerException.class)
    public void ignore_fails_if_target_missing() {
        FeatureContribution fc = factory.createIgnoreFeatureContribution();
        fc.setSourceFeature(nameFeature);
        fc.setTargetFeature(nameFeature);
        new FeatureContributor(fc).apply(null, sourceClass);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ignore_fails_if_features_not_compatible() {
        FeatureContribution fc = factory.createIgnoreFeatureContribution();
        fc.setSourceFeature(nameFeature);
        fc.setTargetFeature(EcorePackage.eINSTANCE.getEClass_Abstract());
        new FeatureContributor(fc).apply(targetClass, sourceClass);
    }

    @Test
    public void set_string_attribute() {
        Assert.assertFalse(Objects.equal(targetClass.getName(), sourceClass.getName()));
        
        FeatureContribution fc = factory.createSetFeatureContribution();
        fc.setSourceFeature(nameFeature);
        fc.setTargetFeature(nameFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
        
        Assert.assertEquals(sourceClass.getName(), targetClass.getName());
    }

    @Test
    public void set_boolean_attribute() {
        Freezer.thaw(sourceClass);
        sourceClass.setAbstract(true);
        Freezer.freeze(sourceClass);
        
        FeatureContribution fc = factory.createSetFeatureContribution();
        EStructuralFeature abstractFeature = EcorePackage.eINSTANCE.getEClass_Abstract();
        fc.setSourceFeature(abstractFeature);
        fc.setTargetFeature(abstractFeature);
        
        Assert.assertFalse(sourceClass.isAbstract() == targetClass.isAbstract());
        new FeatureContributor(fc).apply(targetClass, sourceClass);
        Assert.assertEquals(sourceClass.isAbstract(), targetClass.isAbstract());
    }

    @Test
    public void add_to_many_valued_reference() {
        Freezer.thaw(sourceClass);
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("Super");
        sourceClass.getESuperTypes().add(c);
        Freezer.freeze(sourceClass);
        Assert.assertTrue(targetClass.getESuperTypes().isEmpty());
        
        FeatureContribution fc = factory.createAddFeatureContribution();
        fc.setSourceFeature(supertypesFeature);
        fc.setTargetFeature(supertypesFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
        
        Assert.assertEquals(1, targetClass.getESuperTypes().size());
        Assert.assertSame(c, targetClass.getESuperTypes().get(0));
    }

    @Test
    public void remove_from_many_valued_references() {
        Freezer.thaw(sourceClass);
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("Super");
        targetClass.getESuperTypes().add(c);
        sourceClass.getESuperTypes().add(c);
        Freezer.freeze(sourceClass);
        Assert.assertEquals(1, targetClass.getESuperTypes().size());
        
        FeatureContribution fc = factory.createRemoveFeatureContribution();
        fc.setSourceFeature(supertypesFeature);
        fc.setTargetFeature(supertypesFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
        
        Assert.assertTrue(targetClass.getESuperTypes().isEmpty());
    }
    
    @Test
    public void clear_many_valued_reference() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("Super1");
        targetClass.getESuperTypes().add(c);
        c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("Super2");
        targetClass.getESuperTypes().add(c);
        Assert.assertEquals(2, targetClass.getESuperTypes().size());
        
        FeatureContribution fc = factory.createClearFeatureContribution();
        fc.setSourceFeature(supertypesFeature);
        fc.setTargetFeature(supertypesFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
        
        Assert.assertTrue(targetClass.getESuperTypes().isEmpty());
    }
    
    @Test
    public void reset_attribute_to_default() {
        EAttribute abstractFeature = EcorePackage.eINSTANCE.getEClass_Abstract();
        boolean defaultValue = ((Boolean) abstractFeature.getDefaultValue()).booleanValue();
        targetClass.setAbstract(!defaultValue);
        
        FeatureContribution fc = factory.createResetFeatureContribution();
        fc.setSourceFeature(abstractFeature);
        fc.setTargetFeature(abstractFeature);
        new FeatureContributor(fc).apply(targetClass, sourceClass);
        
        Assert.assertEquals(defaultValue, targetClass.isAbstract());
    }

}
