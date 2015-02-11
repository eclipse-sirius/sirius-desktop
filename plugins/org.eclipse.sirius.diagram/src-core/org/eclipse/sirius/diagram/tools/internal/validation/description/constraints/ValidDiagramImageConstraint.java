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
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.tools.internal.validation.description.constraints.AbstractValidImageConstraint;

/**
 * Validate the images referenced in diagrams.
 * 
 * @author bgrouhan
 * 
 */
public class ValidDiagramImageConstraint extends AbstractValidImageConstraint {
    private static final EAttribute[] attrs = {DescriptionPackage.eINSTANCE.getLayer_Icon(), 
        StylePackage.eINSTANCE.getWorkspaceImageDescription_WorkspacePath(),
        ToolPackage.eINSTANCE.getContainerCreationDescription_IconPath(),
        ToolPackage.eINSTANCE.getEdgeCreationDescription_IconPath(),
        ToolPackage.eINSTANCE.getNodeCreationDescription_IconPath(),
        ToolPackage.eINSTANCE.getToolSection_Icon()};
    
    @Override
    public EAttribute[] getImagePathAttributes() {
        return attrs;
    }
    
}
