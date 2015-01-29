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
package org.eclipse.sirius.tests.unit.diagram.dragndrop;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.internal.resource.DraggedObjectTester;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.junit.Assert;

/**
 * Test the {@link DraggedObjectTester}. Especially according to the VP-2210.
 * 
 * @author edugueperoux
 */
public class DraggedObjectTesterTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/dragndrop/VP-2210/";

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-2210.ecore";

    private static final String CONTROLLED_RESOURCE_FILENAME = "VP-2210_new Package 1.ecore";

    private static final String SESSION_RESOURCE_FILENAME = "VP-2210.aird";

    private EPackage ePackageOfControlledResourceToDrag;

    private EPackage ePackageOfNonControlledResourceToDrag;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_RESOURCE_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + CONTROLLED_RESOURCE_FILENAME, TEMPORARY_PROJECT_NAME + "/" + CONTROLLED_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SESSION_RESOURCE_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME, EcoreModeler.MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);

        EPackage rootEPackage = (EPackage) session.getSemanticResources().iterator().next().getContents().get(0);

        EPackage rootEPackageOfControlledResource = rootEPackage.getESubpackages().get(0);
        ePackageOfControlledResourceToDrag = rootEPackageOfControlledResource.getESubpackages().get(0);

        EPackage rootEPackageOfNonControlledResource = rootEPackage.getESubpackages().get(1);
        ePackageOfNonControlledResourceToDrag = rootEPackageOfNonControlledResource.getESubpackages().get(0);
    }

    /**
     * Test that DraggedObjectTester tell me if the object (which is in a
     * controlled resource) in drag is in the current session.
     */
    public void testDraggedObjectTester_isInSession_WithDraggedObject_FromControlledResource() {
        DraggedObjectTester draggedObjectTester = new DraggedObjectTester(ePackageOfControlledResourceToDrag);
        Assert.assertTrue("a object of a controlled resource should be considered as owned by a session by the DraggedObjectTester", draggedObjectTester.isInSession(session));
    }
    
    /**
     * Test that DraggedObjectTester tell me if the object (which is in a non
     * controlled resource) in drag is in the current session.
     */
    public void testDraggedObjectTester_isInSession_WithDraggedObject_FromNonControlledResource() {
        DraggedObjectTester draggedObjectTester = new DraggedObjectTester(ePackageOfNonControlledResourceToDrag);
        Assert.assertTrue("a object of a non controlled resource should be considered as owned by a session by the DraggedObjectTester", draggedObjectTester.isInSession(session));
    }
    
    /**
     * Test that DraggedObjectTester tell me if the object (which is in a non
     * controlled resource) in drag is in the current session.
     */
    public void testDraggedObjectTesterisInSessionWithDraggedObjectInWrapper() {
        DraggedObjectTester draggedObjectTester = new DraggedObjectTester(new IAdaptable() {
            public Object getAdapter(Class adapter) {
                if (adapter == EObject.class)
                    return ePackageOfNonControlledResourceToDrag;
                return null;
            }
        });
        Assert.assertTrue("a object of a non controlled resource should be considered as owned by a session by the DraggedObjectTester", draggedObjectTester.isInSession(session));
    }

    @Override
    protected void tearDown() throws Exception {

        ePackageOfControlledResourceToDrag = null;
        ePackageOfNonControlledResourceToDrag = null;

        super.tearDown();
    }

}
