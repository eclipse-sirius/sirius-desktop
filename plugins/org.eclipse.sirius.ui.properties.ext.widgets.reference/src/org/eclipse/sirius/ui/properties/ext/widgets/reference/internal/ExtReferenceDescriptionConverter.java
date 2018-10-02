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
import org.eclipse.sirius.properties.core.api.DefaultDescriptionWithInitialOperationConverter;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;

/**
 * This class is used to convert the Sirius Extension reference description into the EEF one.
 * 
 * @author sbegaudeau
 */
public class ExtReferenceDescriptionConverter extends DefaultDescriptionWithInitialOperationConverter<ExtReferenceDescription> {

    /**
     * The constructor.
     */
    public ExtReferenceDescriptionConverter() {
        super(ExtReferenceDescription.class, EefExtWidgetsReferencePackage.Literals.EEF_EXT_REFERENCE_DESCRIPTION,
                EefExtWidgetsReferencePackage.Literals.EEF_EXT_REFERENCE_DESCRIPTION__ON_CLICK_EXPRESSION);
    }

    @Override
    protected EFactory getEFactory() {
        return EefExtWidgetsReferenceFactory.eINSTANCE;
    }
}
