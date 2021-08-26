/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.tests.unit.api.mappings;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl;
import org.eclipse.sirius.diagram.description.impl.ContainerMappingImportImpl;
import org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl;
import org.eclipse.sirius.diagram.description.impl.DiagramImportDescriptionImpl;
import org.eclipse.sirius.diagram.description.impl.NodeMappingImpl;
import org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.ContainerMappingImportSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.ContainerMappingSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.DiagramImportDescriptionSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.NodeMappingImportSpec;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.NodeMappingSpec;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

import junit.framework.TestCase;

/**
 * Tests unsetting features on mapping/description import elements.
 * 
 * @author mporhel
 */
public class ImportSpecClassesUnsetTests extends TestCase {

    public void testContainerMappingImportSpecUnset() {
        ContainerMappingImport cmi = DescriptionFactory.eINSTANCE.createContainerMappingImport();

        String implemChanged = "The ContainerMappingImport implementation has changed, please update this test";
        assertTrue(implemChanged, cmi instanceof ContainerMappingImportSpec);
        assertFalse(implemChanged, cmi instanceof ContainerMappingImportImpl);

        assertTrue(implemChanged, cmi instanceof ContainerMappingSpec);
        assertTrue(implemChanged, cmi instanceof ContainerMappingImpl);
        assertTrue(implemChanged, cmi instanceof ContainerMapping);

        checkFeaturesUnset(cmi);
    }

    public void testNodeMappingImportSpecUnset() {
        NodeMappingImport nmi = DescriptionFactory.eINSTANCE.createNodeMappingImport();

        String implemChanged = "The NodeMappingImport implementation has changed, please update this test";
        assertTrue(implemChanged, nmi instanceof NodeMappingImportSpec);
        assertFalse(implemChanged, nmi instanceof NodeMappingImportImpl);

        assertTrue(implemChanged, nmi instanceof NodeMappingSpec);
        assertTrue(implemChanged, nmi instanceof NodeMappingImpl);
        assertTrue(implemChanged, nmi instanceof NodeMapping);

        checkFeaturesUnset(nmi);
    }

    public void testDiagramImportDescriptionSpecUnset() {
        DiagramImportDescription did = DescriptionFactory.eINSTANCE.createDiagramImportDescription();

        String implemChanged = "The DiagramImportDescription implementation has changed, please update this test";
        assertTrue(implemChanged, did instanceof DiagramImportDescriptionSpec);
        assertFalse(implemChanged, did instanceof DiagramImportDescriptionImpl);

        assertTrue(implemChanged, did instanceof DiagramDescriptionSpec);
        assertTrue(implemChanged, did instanceof DiagramDescriptionImpl);
        assertTrue(implemChanged, did instanceof DiagramDescription);

        checkFeaturesUnset(did);
    }

    private void checkFeaturesUnset(IdentifiedElement object) {
        for (EStructuralFeature f : object.eClass().getEAllStructuralFeatures()) {
            if (f.isChangeable()) {
                try {
                    object.eUnset(f);
                } catch (Exception e) {
                    fail("Error occurred while unsetting feature " + f.getName() + " on " + object + ": " + e.getMessage());
                } catch (StackOverflowError s) {
                    fail("StackOverflowError occurred while unsetting feature" + f.getName() + " on " + object);
                }
            }
        }
    }
}
