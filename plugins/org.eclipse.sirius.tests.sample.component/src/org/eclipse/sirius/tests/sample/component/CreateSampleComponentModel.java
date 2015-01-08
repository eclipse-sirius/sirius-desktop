/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.component;

import java.io.IOException;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class CreateSampleComponentModel {

    public static void initializeEMF() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            @SuppressWarnings("unused")
            EPackage compoment = ComponentPackage.eINSTANCE;
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("component", new XMIResourceFactoryImpl());
        }
    }

    public static Component buildComponentTree(String name, int totalDepth, int payloadDepthStart, int fan) {
        Component root = createComponent(name, payloadDepthStart <= 0);
        if (totalDepth > 0) {
            for (int i = 0; i < fan; i++) {
                Component child = buildComponentTree(name + "." + i, totalDepth - 1, payloadDepthStart - 1, fan);
                root.getChildren().add(child);
            }
        }
        return root;
    }

    public static Component createComponent(String name, boolean isPayload) {
        Component c = ComponentFactory.eINSTANCE.createComponent();
        c.setName(name);
        c.setPayload(isPayload);
        return c;
    }

    public static void main(String[] args) throws IOException {
        initializeEMF();
        ResourceSetImpl rs = new ResourceSetImpl();
        Resource r = rs.createResource(URI.createFileURI(System.getProperty("user.home") + "/test.component"));
        r.getContents().add(buildComponentTree("c", 10, 3, 2));
        r.save(null);
    }
}
