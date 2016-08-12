/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layout.data.manager.extension;

import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.ui.tools.api.layout.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.data.extension.LayoutDataManagerRegistry;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SiriusLayoutDataManagerForSemanticElements;

import junit.framework.TestCase;

/**
 * Tests manager selection.
 * 
 * @author mporhel
 * 
 */
public class LayoutDataManagerSelectionTest extends TestCase {

    /**
     * Test sample extension presence and deployment.
     */
    public void testSampleExtensionDeployment() {
        assertEquals("Sample layout data manager extension not found.", 1, LayoutDataManagerRegistry.getRegisteredExtensions().size());
        assertEquals("Sample layout data manager extension instance not found.", 2, LayoutDataManagerRegistry.getAllSiriusLayoutDataManagers().size());
    }

    /**
     * Tests that the extension do not react for non wanted models.
     */
    public void testDefaultManagerSelectionWhenNoFoundExtension() {
        EPackage p = EcoreFactory.eINSTANCE.createEPackage();

        DSemanticDiagram diagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        diagram.setTarget(p);

        List<SiriusLayoutDataManager> applicableManagers = LayoutDataManagerRegistry.getSiriusLayoutDataManagers(diagram);

        assertEquals("Extension should not accept the given diagram.", 1, applicableManagers.size());
    }

    /**
     * Test that the extension reacts to wanted models : a specific eannotation
     * should be present.
     */
    public void testAvailableManagersWhenExtensionProvides() {
        EPackage p = EcoreFactory.eINSTANCE.createEPackage();

        EAnnotation eannot = EcoreFactory.eINSTANCE.createEAnnotation();
        eannot.setSource(SampleManager.SAMPLE_SOURCE);
        p.getEAnnotations().add(eannot);

        DSemanticDiagram diagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        diagram.setTarget(p);

        List<SiriusLayoutDataManager> applicableManagers = LayoutDataManagerRegistry.getSiriusLayoutDataManagers(diagram);

        assertEquals("Extension should not accept the given diagram.", 2, applicableManagers.size());
        assertTrue("Extension should be the first manager.", applicableManagers.get(0) instanceof SampleManager);
        assertTrue("Default manager should be the last one.", applicableManagers.get(1) instanceof SiriusLayoutDataManagerForSemanticElements);
    }
}
