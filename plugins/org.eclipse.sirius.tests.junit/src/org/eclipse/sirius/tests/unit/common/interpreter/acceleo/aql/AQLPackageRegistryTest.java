/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.aql;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.acceleo.aql.business.internal.AQLSiriusInterpreter;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.AcceleoMTLInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;

import junit.framework.TestCase;

/**
 * Test that {@link AcceleoMTLInterpreter} dispose does not impact
 * {@link Registry#INSTANCE}. See VP-4430.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AQLPackageRegistryTest extends TestCase {

    private static final String TEMPORARY_PROJECT_NAME = "DesignerTestProject";

    private AQLSiriusInterpreter aqlSiriusInterpreter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);
        aqlSiriusInterpreter = new AQLSiriusInterpreter();
    }

    /**
     * Test that {@link AcceleoMTLInterpreter} dispose does not impact
     * {@link Registry#INSTANCE}. See VP-4430.
     * 
     * @throws Exception
     */
    public void testEcoreRemoval() throws Exception {
        EPackage ecorePackage = EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        assertNotNull("The Ecore package must be registred by extension in the platform", ecorePackage);
        Freezer.freeze(AllContents.of(ecorePackage, true));

        URI semanticResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/ecore.ecore", true);
        Resource ecoreRes = new ResourceSetImpl().createResource(semanticResourceURI);
        EPackage content = EcoreUtil.copy(EcorePackage.eINSTANCE);
        ecoreRes.getContents().add(content);
        ecoreRes.save(Collections.emptyMap());
        aqlSiriusInterpreter.activateMetamodels(Collections.<MetamodelDescriptor> singletonList(new EcoreMetamodelDescriptor(content)));
        aqlSiriusInterpreter.dispose();
        EclipseTestsSupportHelper.INSTANCE.deleteProject(TEMPORARY_PROJECT_NAME);

        EPackage ecorePackageAfter = EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        assertSame("The registered version of Ecore should not have changed", ecorePackage, ecorePackageAfter);
    }

    @Override
    protected void tearDown() throws Exception {
        Freezer.thaw(AllContents.of(EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI), true));
        aqlSiriusInterpreter = null;
        super.tearDown();
    }
}
