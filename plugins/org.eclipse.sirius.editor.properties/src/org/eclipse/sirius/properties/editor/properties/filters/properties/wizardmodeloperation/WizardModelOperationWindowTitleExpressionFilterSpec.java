/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.editor.properties.filters.properties.wizardmodeloperation;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.editor.properties.filters.common.ViewpointPropertyFilter;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * A filter for the window title expression property section.
 * 
 * @author sbegaudeau
 */
public class WizardModelOperationWindowTitleExpressionFilterSpec extends ViewpointPropertyFilter {
    @Override
    protected EStructuralFeature getFeature() {
        return PropertiesPackage.eINSTANCE.getWizardModelOperation_WindowTitleExpression();
    }

    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.properties.WizardModelOperation;
    }
}
