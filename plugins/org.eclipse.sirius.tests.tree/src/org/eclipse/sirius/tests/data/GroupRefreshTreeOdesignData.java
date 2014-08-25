/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.data;

//Start of user code imports

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.viewpoint.description.Group;

//End of user code

public class GroupRefreshTreeOdesignData {
    private Group oDesign;

    public void loadFromPlugin() {
        ResourceSet set = new ResourceSetImpl();
        // Start of user code odesignURI
        oDesign = (Group) set.getResource(URI.createPlatformPluginURI("/org.eclipse.sirius.tests.tree/data/refresh/tree.odesign", true), true).getContents().get(0);
        // End of user code
    }

    public void createStandaloneInstance() {
        // org.eclipse.emf.ecore.impl.DynamicEObjectImpl@b57e9a (eClass:
        // org.eclipse.emf.ecore.impl.EClassImpl@18adae2 (name: Invalid_Class)
        // (instanceClassName: null) (abstract: false, interface: false))
        // oDesign = group0;
    }

    public GroupRefreshTreeOdesign group() {
        return new GroupRefreshTreeOdesign(oDesign);
    }

}
