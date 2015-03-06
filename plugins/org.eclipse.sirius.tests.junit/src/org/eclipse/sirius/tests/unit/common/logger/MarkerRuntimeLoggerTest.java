/*******************************************************************************
 * Copyright (c) 2015, Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.logger;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.business.api.logger.MarkerRuntimeLogger;
import org.eclipse.sirius.business.api.logger.RuntimeLogger;
import org.eclipse.sirius.business.internal.logger.MarkerRuntimeLoggerImpl;
import org.junit.Test;

/**
 * Test the {@link MarkerRuntimeLogger} used for reporting evaluation errors
 * during refresh.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 *
 */
public class MarkerRuntimeLoggerTest {
    /**
     * Resources which might be decorated by the marker runtime logger are not
     * necessarily living in the workspace. This test makes sure in that case
     * the RuntimeLogger is failing and throwing an Exception.
     * 
     * @throws Exception
     */
    @Test
    public void addingMarkerDoesNotFailWithNonWorkspaceURIs() throws Exception {
        RuntimeLogger logger = new MarkerRuntimeLoggerImpl();
        logger.error(EcorePackage.eINSTANCE.getEClass(), EcorePackage.eINSTANCE.getEClass_Abstract(), "some message");
        logger.info(EcorePackage.eINSTANCE.getEClass(), EcorePackage.eINSTANCE.getEClass_Abstract(), "some message");
        logger.warning(EcorePackage.eINSTANCE.getEClass(), EcorePackage.eINSTANCE.getEClass_Abstract(), "some message");

    }

    /**
     * Makes sure clearing the markers will not fail with non workspace URIs.
     * 
     * @throws Exception
     */
    @Test
    public void clearingMarkerDoesNotFailWithNonWorkspaceURIs() throws Exception {
        RuntimeLogger logger = new MarkerRuntimeLoggerImpl();
        logger.clear(EcorePackage.eINSTANCE.getEClass());
        logger.clearAll();

    }
}
