/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools.tasks;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.semantic.RemoveDanglingReferences;

import junit.framework.TestCase;

/**
 * Check the behavior and scope of the static methods of {@link RemoveDanglingReferences}.
 * 
 * @author mporhel
 */
public class RemoveDanglingReferencesTestCase extends TestCase {

    public void testDanglingReferenceRemovalFromElement() throws Exception {
        TestData testData = initResourceSet();

        // Detach the common super type EClass and check it create several
        // dangling references.
        EcoreUtil.remove(testData.commonSuperType);
        checkDanglingReferences(testData, true, true, true);

        // Remove dangling references on one EObject, the current behavior is to
        // delete the dangling references in its containing resource.
        RemoveDanglingReferences.removeDanglingReferences(testData.subClass1);

        checkDanglingReferences(testData, false, false, true);
    }

    public void testDanglingReferenceRemovalFromResourceSet() throws Exception {
        TestData testData = initResourceSet();

        // Detach the common super type EClass and check it create several
        // dangling references.
        EcoreUtil.remove(testData.commonSuperType);
        checkDanglingReferences(testData, true, true, true);

        // Remove dangling references in all the resource set.
        RemoveDanglingReferences.removeDanglingReferences(testData.subClass1.eResource().getResourceSet());

        checkDanglingReferences(testData, false, false, false);
    }

    public void testDanglingReferenceRemovalFromElementInOtherResource() throws Exception {
        TestData testData = initResourceSet();

        // Detach the common super type EClass and check it create several
        // dangling references.
        EcoreUtil.remove(testData.commonSuperType);
        checkDanglingReferences(testData, true, true, true);

        // Remove dangling references in the other resource, it was not impacted
        // by the previous step EClass removal.
        // done.
        RemoveDanglingReferences.removeDanglingReferences(testData.subClassInOtherResource);

        checkDanglingReferences(testData, true, true, false);
    }

    public void testDanglingReferenceRemovalFromARemovedElement() throws Exception {
        TestData testData = initResourceSet();

        // Detach the common super type EClass and check it create several
        // dangling references.
        EcoreUtil.remove(testData.commonSuperType);
        checkDanglingReferences(testData, true, true, true);

        // Call RemoveDanglingReferences.removeDanglingReferences on the removed
        // element: it will have no effect as the element has already been
        // removed from its ressource.
        RemoveDanglingReferences.removeDanglingReferences(testData.commonSuperType);

        checkDanglingReferences(testData, true, true, true);
    }
    // @formatter:off
/* 
    public void testDanglingReferenceRemovalFromResource() throws Exception {
        TestData testData = initResourceSet();
        Resource eResource = testData.commonSuperType.eResource();

        // Detach the common super type EClass and check it create several
        // dangling references.
        EcoreUtil.remove(testData.commonSuperType);
        checkDanglingReferences(testData, true, true, true);

        // Remove dangling references in the resource where the deletion was
        // done.
        RemoveDanglingReferences.removeDanglingReferences(eResource);

        // Check the dangling reference have been removed from the resource on
        // which the removal was asked.
        checkDanglingReferences(testData, false, false, true);
    }

    public void testDanglingReferenceRemovalFromOtherResource() throws Exception {
        TestData testData = initResourceSet();

        // Detach the common super type EClass and check it create several
        // dangling references.
        EcoreUtil.remove(testData.commonSuperType);
        checkDanglingReferences(testData, true, true, true);

        // Remove dangling references in the other resource, it was not impacted
        // by the previous step EClass removal.
        // done.
        RemoveDanglingReferences.removeDanglingReferences(testData.subClassInOtherResource.eResource());

        checkDanglingReferences(testData, true, true, false);
    }
*/
    // @formatter:on

    private void checkDanglingReferences(TestData testData, boolean expectedDanglingSubClass1, boolean expectedDanglingSubClass2, boolean expectedDanglingSubClassInOtherResource) {
        checkDanglingReferences(testData.subClass1, expectedDanglingSubClass1);
        checkDanglingReferences(testData.subClass2, expectedDanglingSubClass2);
        checkDanglingReferences(testData.subClassInOtherResource, expectedDanglingSubClassInOtherResource);
    }

    private void checkDanglingReferences(EClass eClass, boolean expectedDangling) {
        if (expectedDangling) {
            assertFalse("Some dangling reference was expected.", eClass.getESuperTypes().isEmpty());
            assertNull("Some dangling reference was expected.", eClass.getESuperTypes().get(0).eResource());
        } else {
            assertTrue("The dangling reference has not been removed.", eClass.getESuperTypes().isEmpty());
        }
    }

    /*
     * Init the test data: 1 resource set, 2 EPackage in 2 resources. 4 eClasses: 3 in the first package, 1 in the
     * other. 1 classes from the first package is added as supertype of the 3 other classes.
     */
    private TestData initResourceSet() {
        ResourceSet rset = new ResourceSetImpl();
        Resource res1 = rset.createResource(URI.createFileURI(new File("test.ecore").getAbsolutePath()));
        rset.getResources().add(res1);
        Resource res2 = rset.createResource(URI.createFileURI(new File("test2.ecore").getAbsolutePath()));
        rset.getResources().add(res2);

        // We don't use the result, but the following line is needed to make
        // sure everything is properly initialized.
        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(rset);

        EPackage p1 = EcoreFactory.eINSTANCE.createEPackage();
        EClass commonSuperType = EcoreFactory.eINSTANCE.createEClass();
        EClass subClass1 = EcoreFactory.eINSTANCE.createEClass();
        EClass subClass2 = EcoreFactory.eINSTANCE.createEClass();
        subClass1.getESuperTypes().add(commonSuperType);
        subClass2.getESuperTypes().add(commonSuperType);
        p1.getEClassifiers().add(commonSuperType);
        p1.getEClassifiers().add(subClass1);
        p1.getEClassifiers().add(subClass2);
        res1.getContents().add(p1);

        EPackage p2 = EcoreFactory.eINSTANCE.createEPackage();
        EClass subClass3 = EcoreFactory.eINSTANCE.createEClass();
        subClass3.getESuperTypes().add(commonSuperType);
        p2.getEClassifiers().add(subClass3);
        res2.getContents().add(p2);

        return new TestData(commonSuperType, subClass1, subClass2, subClass3);
    }

    private class TestData {
        final EClass commonSuperType;

        final EClass subClass1;

        final EClass subClass2;

        final EClass subClassInOtherResource;

        public TestData(EClass commonSuperType, EClass subClass1, EClass subClass2, EClass subClassInOtherResource) {
            this.commonSuperType = commonSuperType;
            this.subClass1 = subClass1;
            this.subClass2 = subClass2;
            this.subClassInOtherResource = subClassInOtherResource;
        }
    }
}
