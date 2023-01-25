/*******************************************************************************
 * Copyright (c) 2016, 2023 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.tools.api.properties.ISiriusPropertySheetPageProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * Provides an EEFTabbedPropertySheetPage for a given source part.
 * 
 * @author pcdavid
 */
public class EEFPropertySheetPageProvider implements ISiriusPropertySheetPageProvider {
    private static final String DESCRIPTION_REFERENCE_NAME = "description"; //$NON-NLS-1$

    @Override
    public IPropertySheetPage getPropertySheetPage(Object source, String contributorId) {
        if (source instanceof DialectEditor) {
            DialectEditor editor = (DialectEditor) source;
            DRepresentation representation = editor.getRepresentation();
            if (representation != null && representation.eClass().getEStructuralFeature(EEFPropertySheetPageProvider.DESCRIPTION_REFERENCE_NAME) != null) {
                EStructuralFeature ref = representation.eClass().getEStructuralFeature(EEFPropertySheetPageProvider.DESCRIPTION_REFERENCE_NAME);
                Object value = representation.eGet(ref);
                if (value instanceof RepresentationDescription) {
                    RepresentationDescription description = (RepresentationDescription) value;
                    if (forcesLegacyPropertySheet(description)) {
                        return null;
                    }
                }
            }
        }

        return new SiriusEEFTabbedPropertySheetPage(new ContributorWrapper(source, contributorId));
    }

    private boolean forcesLegacyPropertySheet(RepresentationDescription description) {
        EObject current = description;
        while (current != null) {
            if (current instanceof DocumentedElement) {
                String doc = ((DocumentedElement) current).getDocumentation();
                if (doc != null && doc.contains("FORCE_LEGACY_PROPERTIES")) { //$NON-NLS-1$
                    return true;
                }
            }
            current = current.eContainer();
        }
        return false;
    }
}
