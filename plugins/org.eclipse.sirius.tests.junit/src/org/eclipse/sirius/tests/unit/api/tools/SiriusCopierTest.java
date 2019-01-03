/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.sirius.tests.sample.component.ComponentFactory;
import org.eclipse.sirius.tests.sample.component.ComponentPackage;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;

import junit.framework.TestCase;

/**
 * Test class to check the SiriusCopier behavior.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class SiriusCopierTest extends TestCase {

    public void testSiriusCopierOnSemanticElement() throws Exception {
        try {
            ComponentPackage.eINSTANCE.getComponent_Name().setID(true);
            Component component = ComponentFactory.eINSTANCE.createComponent();
            String originalName = "OriginalName";
            component.setName(originalName);

            Component copiedComponent = SiriusCopierHelper.copyWithNoUidDuplication(component);
            String message = "The COMPONENT__NAME attribute should have been copied with SiriusCopier";
            assertTrue(message, originalName.equals(copiedComponent.getName()));

            Collection<Component> copiedComponents = SiriusCopierHelper.copyAllWithNoUidDuplication(Arrays.asList(component));
            assertTrue(message, originalName.equals(copiedComponents.iterator().next().getName()));

        } finally {
            ComponentPackage.eINSTANCE.getComponent_Name().setID(false);
        }

    }

    public void testSiriusCopierOnIdentifiedElement() throws Exception {
        String originalUid = EcoreUtil.generateUUID();
        DRepresentationDescriptor repDesc = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
        repDesc.setUid(originalUid);

        DRepresentationDescriptor copiedRepDesc = SiriusCopierHelper.copyWithNoUidDuplication(repDesc);
        String message = "The IDENTIFIED_ELEMENT__UID attribute should not have been copied with SiriusCopier";
        assertTrue(message, !originalUid.equals(copiedRepDesc.getUid()));

        Collection<DRepresentationDescriptor> copiedRepDescs = SiriusCopierHelper.copyAllWithNoUidDuplication(Arrays.asList(repDesc));
        assertTrue(message, !originalUid.equals(copiedRepDescs.iterator().next().getUid()));
    }
}
