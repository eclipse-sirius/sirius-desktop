/**
 * Copyright (c) 2016, 2017 Obeo.
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

import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EefExtWidgetsReferenceFactory;
import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EefExtWidgetsReferencePackage;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionConverter;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle;

/**
 * The converter of the conditional style.
 * 
 * @author sbegaudeau
 */
public class ExtReferenceDescriptionConditionalStyleConverter extends DefaultDescriptionConverter<ExtReferenceWidgetConditionalStyle> {

    /**
     * The constructor.
     */
    public ExtReferenceDescriptionConditionalStyleConverter() {
        super(ExtReferenceWidgetConditionalStyle.class, EefExtWidgetsReferencePackage.Literals.EEF_EXT_REFERENCE_CONDITIONAL_STYLE);
    }

    @Override
    protected EFactory getEFactory() {
        return EefExtWidgetsReferenceFactory.eINSTANCE;
    }
}
