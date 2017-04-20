/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.editor.properties.filters.properties.toolbaraction;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ToolbarAction;

/**
 * The filter for the property section of the image expression.
 * 
 * @author sbegaudeau
 */
public class ToolbarActionImageExpressionFilterSpec extends ViewpointPropertyFilter {

    @Override
    protected EStructuralFeature getFeature() {
        return PropertiesPackage.eINSTANCE.getToolbarAction_ImageExpression();
    }

    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof ToolbarAction;
    }

}
