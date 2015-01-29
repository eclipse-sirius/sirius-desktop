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
package org.eclipse.sirius.tests.unit.perf.scenario.modeler.ecore;

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design.EntitiesDiagramRefreshTests;

/**
 * Refresh tests for Entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EntitiesDiagramRefreshPerfTests extends EntitiesDiagramRefreshTests {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
    }

    @Override
    public void testDefaultLayerRefresh() {
        super.testDefaultLayerRefresh();
        TestsUtil.synchronizationWithUIThread();
    }

    public void testDefaultLayerRefreshX2() {
        defaultLayerRefreshWith(2 * NUMBER_OF_CLASS);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testDefaultLayerRefreshX4() {
        defaultLayerRefreshWith(4 * NUMBER_OF_CLASS);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    public void testPackageLayerRefresh() {
        super.testPackageLayerRefresh();
        TestsUtil.synchronizationWithUIThread();
    }

    public void testPackageLayerRefreshX2() {
        packageLayerRefresh(2 * NUMBER_OF_SUBPACKAGE);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testPackageLayerRefreshX4() {
        packageLayerRefresh(4 * NUMBER_OF_SUBPACKAGE);
        TestsUtil.synchronizationWithUIThread();
    }

}
