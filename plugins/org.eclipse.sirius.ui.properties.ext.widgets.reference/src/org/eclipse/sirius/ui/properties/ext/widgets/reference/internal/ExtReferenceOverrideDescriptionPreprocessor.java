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
import org.eclipse.sirius.properties.core.api.OverrideDescriptionPreprocessor;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;

/**
 * The preprocessor of the reference.
 * 
 * @author mbats
 */
public class ExtReferenceOverrideDescriptionPreprocessor extends OverrideDescriptionPreprocessor<ExtReferenceOverrideDescription> {
    /**
     * The constructor.
     */
    public ExtReferenceOverrideDescriptionPreprocessor() {
        super(ExtReferenceOverrideDescription.class, PropertiesExtWidgetsReferencePackage.Literals.EXT_REFERENCE_OVERRIDE_DESCRIPTION);
    }

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof ExtReferenceOverrideDescription;
    }

}
