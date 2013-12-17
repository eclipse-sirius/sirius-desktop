/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.query;

import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;

/**
 * A class aggregating all the graphical queries (read-only!) having a
 * {@link DiagramDescription} as a starting point.
 * 
 * @author lredor
 * 
 */
public class DiagramDescriptionQuery {

    /**
     * The element to query.
     */
    protected DiagramDescription diagramDescription;

    /**
     * Create a new query.
     * 
     * @param diagramDescription
     *            the element to query.
     */
    public DiagramDescriptionQuery(DiagramDescription diagramDescription) {
        this.diagramDescription = diagramDescription;
    }

    /**
     * Return true if the header must be enabled, false otherwise.
     * 
     * @return true if the header must be enabled, false otherwise.
     */
    public boolean isHeaderSectionEnabled() {
        boolean isHeaderSectionEnabled = false;

        // Look for global preference
        String headerPrefKey = SiriusDiagramPreferencesKeys.PREF_DISPLAY_HEADER_SECTION.name();
        boolean prefHeaderEnabled = Platform.getPreferencesService().getBoolean(SiriusDiagramEditorPlugin.ID, headerPrefKey, false, null);
        if (prefHeaderEnabled) {
            // Look for header contributions.
            for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
                if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(diagramDescription.eClass().getEPackage())) {
                    isHeaderSectionEnabled = diagramTypeDescriptor.getDiagramDescriptionProvider().supportHeader();
                    break;
                }
            }
        }
        return isHeaderSectionEnabled;
    }
}
