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
package org.eclipse.sirius.tests.unit.api.editors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.editor.tools.api.ecore.WorkspaceEPackageRegistry;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Test class for {@link WorkspaceEPackageRegistry}. Created for VP-2774.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WorkspaceEPackageRegistryTests extends SiriusDiagramTestCase {

    private WorkspaceEPackageRegistry workspaceEPackageRegistry;

    private ILogListener warningLogListener;

    private List<IStatus> warnings;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        workspaceEPackageRegistry = new WorkspaceEPackageRegistry(true);
        workspaceEPackageRegistry.init(ResourcesPlugin.getWorkspace());

        warnings = new ArrayList<IStatus>();
        warningLogListener = new WarningLogRegister();
        Platform.addLogListener(warningLogListener);
    }

    /**
     * Test the case of a EPackage with a EClass with null as name.
     * 
     * @throws Exception
     */
    public void testWorkspaceEPackageRegistryWithNullEClassName() throws Exception {
        URI ecoreResource1URI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/ecoreResource1.ecore", true);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource ecoreResource1 = resourceSet.createResource(ecoreResource1URI);
        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        ePackage.setName("exampleEPackage");
        ePackage.setNsURI("http://www.example.com/samples/exampleEPackage/0.1.0");
        EClass nullEClass = EcoreFactory.eINSTANCE.createEClass();
        ePackage.getEClassifiers().add(nullEClass);
        ecoreResource1.getContents().add(ePackage);

        int workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        ecoreResource1.save(Collections.emptyMap());

        int workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        String assertMessage = "A new correct EPackage (with a nsURI) must be added to the workspace";
        assertEquals(assertMessage, workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding + 1, workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding);
        assertTrue(assertMessage, workspaceEPackageRegistry.containsKey(ePackage.getNsURI()));
        assertEquals(0, warnings.size());

    }

    /**
     * Test the case of a EPackage with null as nsURI.
     * 
     * @throws Exception
     */
    public void testWorkspaceEPackageRegistryWithNullEPackageNSURI() throws Exception {
        URI ecoreResource1URI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/ecoreResource1.ecore", true);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource ecoreResource1 = resourceSet.createResource(ecoreResource1URI);
        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        ePackage.setName("exampleEPackage");
        ecoreResource1.getContents().add(ePackage);

        int workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        ecoreResource1.save(Collections.emptyMap());

        int workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        String assertMessage = "A EPackage with a nsURI to null must not be added to the workspace";
        assertEquals(assertMessage, workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding, workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding);
        assertFalse(assertMessage, workspaceEPackageRegistry.containsKey(ePackage.getNsURI()));
//        assertEquals("A warning message must be logged to say that a invalid ePackage (with a nsURI property to null) is in the workspace", 2, warnings.size());
    }

    /**
     * Test the case of two EPackage with the same nsURI in a same resource.
     * 
     * @throws Exception
     */
    public void testWorkspaceEPackageRegistryWithTwoSameEPackageNSURIInSameEcoreResource() throws Exception {
        URI ecoreResource1URI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/ecoreResource1.ecore", true);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource ecoreResource1 = resourceSet.createResource(ecoreResource1URI);

        EPackage ePackage1 = EcoreFactory.eINSTANCE.createEPackage();
        ePackage1.setName("exampleEPackage1");
        ePackage1.setNsURI("http://www.example.com/samples/exampleEPackage/0.1.0");
        ecoreResource1.getContents().add(ePackage1);

        EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
        ePackage2.setName("exampleEPackage2");
        ePackage2.setNsURI("http://www.example.com/samples/exampleEPackage/0.1.0");
        ecoreResource1.getContents().add(ePackage2);

        int workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        ecoreResource1.save(Collections.emptyMap());

        int workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        String assertMessage = "A new correct EPackage (with a nsURI) must be added to the workspace";
        assertEquals(assertMessage, workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding + 1, workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding);
        assertTrue(assertMessage, workspaceEPackageRegistry.containsKey(ePackage1.getNsURI()));
//        assertEquals("A warning message must be logged to say two EPackage with the same nsURI exists in the workspace", 3, warnings.size());
    }

    /**
     * Test the case of two EPackage with the same nsURI in a different
     * resource.
     * 
     * @throws Exception
     */
    public void testWorkspaceEPackageRegistryWithTwoSameEPackageNSURIInDifferentEcoreResource() throws Exception {
        URI ecoreResource1URI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/ecoreResource1.ecore", true);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource ecoreResource1 = resourceSet.createResource(ecoreResource1URI);

        EPackage ePackage1 = EcoreFactory.eINSTANCE.createEPackage();
        ePackage1.setName("exampleEPackage1");
        ePackage1.setNsURI("http://www.example.com/samples/exampleEPackage/0.1.0");
        ecoreResource1.getContents().add(ePackage1);

        URI ecoreResource2URI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/ecoreResource2.ecore", true);
        Resource ecoreResource2 = resourceSet.createResource(ecoreResource2URI);

        EPackage ePackage2 = EcoreFactory.eINSTANCE.createEPackage();
        ePackage2.setName("exampleEPackage2");
        ePackage2.setNsURI("http://www.example.com/samples/exampleEPackage/0.1.0");
        ecoreResource2.getContents().add(ePackage2);

        int workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        ecoreResource1.save(Collections.emptyMap());

        int workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding = workspaceEPackageRegistry.size();

        String assertMessage = "A new correct EPackage (with a nsURI) must be added to the workspace";
        assertEquals(assertMessage, workspaceEPackageRegistrySizeBeforeNewEcoreResourceAdding + 1, workspaceEPackageRegistrySizeAfterNewEcoreResourceAdding);
        assertTrue(assertMessage, workspaceEPackageRegistry.containsKey(ePackage1.getNsURI()));
//        assertEquals("A warning message must be logged to say two EPackage with the same nsURI exists in the workspace", 1, warnings.size());
    }

    @Override
    protected void tearDown() throws Exception {
        workspaceEPackageRegistry.dispose(ResourcesPlugin.getWorkspace());
        workspaceEPackageRegistry = null;
        Platform.removeLogListener(warningLogListener);
        warningLogListener = null;
        warnings = null;
        super.tearDown();
    }

    class WarningLogRegister implements ILogListener {

        public void logging(IStatus status, String plugin) {
            if (status.getSeverity() == IStatus.WARNING && DslCommonPlugin.PLUGIN_ID.equals(status.getPlugin())) {
                warnings.add(status);
            }
        }

    }
}
