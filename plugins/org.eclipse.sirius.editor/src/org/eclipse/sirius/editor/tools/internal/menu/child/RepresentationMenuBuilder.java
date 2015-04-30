/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.menu.child;

import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * The representations menu.
 * 
 * @author cbrun
 * 
 */
public class RepresentationMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public RepresentationMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getRepresentationDescription());
        addRestrictedType(DescriptionPackage.eINSTANCE.getRepresentationImportDescription());
    }

    @Override
    public String getLabel() {
        return "New Representation";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.REPRESENTATION;
    }
}
