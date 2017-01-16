/**
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.properties.ext.widgets.reference.internal;

import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EefExtWidgetsReferenceFactory;
import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EefExtWidgetsReferencePackage;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.sirius.properties.core.api.DefaultStyleDescriptionConverter;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle;

/**
 * Converter for the style of the reference widget.
 * 
 * @author sbegaudeau
 */
public class ExtReferenceDescriptionStyleConverter extends DefaultStyleDescriptionConverter<ExtReferenceWidgetStyle> {

    /**
     * The constructor.
     */
    public ExtReferenceDescriptionStyleConverter() {
        super(ExtReferenceWidgetStyle.class, EefExtWidgetsReferencePackage.Literals.EEF_EXT_REFERENCE_WIDGET_STYLE);
    }

    @Override
    protected EFactory getEFactory() {
        return EefExtWidgetsReferenceFactory.eINSTANCE;
    }

}
