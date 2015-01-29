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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.common.AbstractEcoreSynchronizerTest;

/**
 * 
 * @author cbrun
 */
public class EntitiesDiagramTooltipsTests extends AbstractEcoreSynchronizerTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activateViewpoint(DESIGN_VIEWPOINT_NAME);
    }

    @Override
    protected String getSemanticResourcePath() {
        return "/org.eclipse.emf.ecore/model/Ecore.ecore";
    }

    public void testTooltipInitializedOnDiagramCreation() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Entities");
        assertNotNull("The unit test data seems incorrect", classDiag);
        prepareSynchronizer(classDiag, "Test class diagram");

        // The tooltip on the EClasses computes a simple "qualified name"
        final DNodeList nodeList = (DNodeList) findElementNamed(getRefreshedDiagram(), "EClass");
        assertEquals("ecore.EClass", nodeList.getTooltipText());
        TestsUtil.synchronizationWithUIThread();

        // The tooltip on EOperation computes signatures (without parameter
        // type, only their names)
        final DDiagramElement featureCountOperation = findElementNamed(getRefreshedDiagram(), "getFeatureCount() : EInt");
        assertEquals("getFeatureCount() : EInt", featureCountOperation.getTooltipText());

        final DDiagramElement isSuperTypeOfOperation = findElementNamed(getRefreshedDiagram(), "isSuperTypeOf(someClass EClass) : EBoolean");
        assertEquals("isSuperTypeOf(someClass) : EBoolean", isSuperTypeOfOperation.getTooltipText());
    }
}
