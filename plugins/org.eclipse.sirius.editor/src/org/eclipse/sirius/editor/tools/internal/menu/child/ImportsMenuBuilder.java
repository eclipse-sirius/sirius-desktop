/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.menu.child;

import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;

/**
 * The imports menu.
 * 
 * @author cbrun
 * 
 */
public class ImportsMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * build the menu.
     */
    public ImportsMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getContainerMappingImport());
        addValidType(DescriptionPackage.eINSTANCE.getNodeMappingImport());
        addValidType(DescriptionPackage.eINSTANCE.getDiagramImportDescription());
        addValidType(DescriptionPackage.eINSTANCE.getEdgeMappingImport());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getLabel() {
        return "New Import...";
    }

}
