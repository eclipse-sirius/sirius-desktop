/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.ext.widgets.reference.internal;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;

public class AbstractExtReferenceDescriptionExtendsFilter extends ViewpointPropertyFilter {

    @Override
    protected EStructuralFeature getFeature() {
        return PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription_Extends();
    }

    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof AbstractExtReferenceDescription;
    }

}
