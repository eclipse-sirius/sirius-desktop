/**
 * Copyright (c) 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.properties.ext.widgets.reference.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.core.api.PreconfiguredPreprocessor;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;

/**
 * The preprocessor of the style.
 * 
 * @author mbats
 */
public class ExtReferenceDescriptionStylePreprocessor extends PreconfiguredPreprocessor<ExtReferenceWidgetStyle> {
    /**
     * The constructor.
     */
    public ExtReferenceDescriptionStylePreprocessor() {
        super(ExtReferenceWidgetStyle.class, PropertiesExtWidgetsReferencePackage.Literals.EXT_REFERENCE_WIDGET_STYLE);
    }

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof ExtReferenceWidgetStyle;
    }

}
