/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.mappings;

import java.util.Collection;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ContainerMappingImportSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ContainerMappingSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.DiagramDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.DiagramImportDescriptionSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.NodeMappingImportSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.NodeMappingSpec;
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
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

import com.google.common.collect.Lists;

import junit.framework.TestCase;

/**
 * Tests for reuse mappings usage with filters
 * 
 * @author mporhel
 */
public class ImportSpecClassesUnsetTests extends TestCase {

    public void testContainerMappingImportSpecUnset() {

        ContainerMappingImport cmi = DescriptionFactory.eINSTANCE.createContainerMappingImport();

        assertTrue(cmi instanceof ContainerMappingImportSpec);
        assertFalse(cmi instanceof ContainerMappingImportImpl);

        assertTrue(cmi instanceof ContainerMappingSpec);
        assertTrue(cmi instanceof ContainerMappingImpl);
        assertTrue(cmi instanceof ContainerMapping);

        checkFeaturesUnset(cmi);
    }

    public void testNodeMappingImportSpecUnset() {
        NodeMappingImport nmi = DescriptionFactory.eINSTANCE.createNodeMappingImport();

        assertTrue(nmi instanceof NodeMappingImportSpec);
        assertFalse(nmi instanceof NodeMappingImportImpl);

        assertTrue(nmi instanceof NodeMappingSpec);
        assertTrue(nmi instanceof NodeMappingImpl);
        assertTrue(nmi instanceof NodeMapping);

        checkFeaturesUnset(nmi);
    }

    public void testDiagramImportDescriptionSpecUnset() {
        DiagramImportDescription did = DescriptionFactory.eINSTANCE.createDiagramImportDescription();

        assertTrue(did instanceof DiagramImportDescriptionSpec);
        assertFalse(did instanceof DiagramImportDescriptionImpl);

        assertTrue(did instanceof DiagramDescriptionSpec);
        assertTrue(did instanceof DiagramDescriptionImpl);
        assertTrue(did instanceof DiagramDescription);

        checkFeaturesUnset(did);
    }

    private void checkFeaturesUnset(IdentifiedElement object) {
        // TODO uncomment this and remove buildMessage() when corrected.
        Collection<EStructuralFeature> exceptions = Lists.newArrayList();
        Collection<EStructuralFeature> stackOverFlows = Lists.newArrayList();

        for (EStructuralFeature f : object.eClass().getEAllStructuralFeatures()) {
            if (f.isChangeable()) {
                try {
                    object.eUnset(f);
                } catch (Exception e) {
                    exceptions.add(f);
                    e.printStackTrace();

                    // TODO uncomment this and remove buildMessage() when
                    // corrected.
                    // fail("Error occurred while unsetting feature " +
                    // f.getName() + " on " + object + ": " + e.getMessage());
                } catch (StackOverflowError s) {
                    stackOverFlows.add(f);

                    // TODO uncomment this and remove buildMessage() when
                    // corrected.
                    // fail("StackOverflowError occurred while unsetting feature
                    // " + f.getName() + " on " + object);
                }
            }
        }

        // TODO Remove this and buildMessage() when corrected.
        StringBuilder sb = new StringBuilder();
        buildMessage(sb, "Error(s) occurred on ", object, exceptions);
        buildMessage(sb, "StackOverFlow(s) occurred on ", object, stackOverFlows);
        if (sb.length() != 0) {
            fail(sb.toString());
        }
    }

    private void buildMessage(StringBuilder sb, String message, IdentifiedElement object, Collection<EStructuralFeature> failures) {
        if (!failures.isEmpty()) {
            sb.append("\n").append(message).append(object.eClass().getName());
            if (StringUtil.isEmpty(object.getName())) {
                sb.append(" ").append(object.getName());
            }
            sb.append(" while unsetting ");
            if (failures.size() == 1) {
                sb.append(failures.iterator().next().getName());
            } else {
                sb.append("\n");
                for (EStructuralFeature f : failures) {
                    sb.append("  . ").append(f.getName()).append(", ").append("\n");
                }
            }
        }
    }
}
