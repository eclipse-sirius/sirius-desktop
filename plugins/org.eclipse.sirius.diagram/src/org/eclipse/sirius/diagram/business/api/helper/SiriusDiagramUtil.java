/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and other.
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
package org.eclipse.sirius.diagram.business.api.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramComponentizationHelper;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.model.business.api.helper.ViewpointUtil;
import org.eclipse.sirius.viewpoint.DView;

/**
 * Useful operations to manipulate a DDiagram model.
 * 
 * @author ymortier
 */
public final class SiriusDiagramUtil {

    /** The "environment:/diagram" resource uri. */
    public static final String DIAGRAM_ENVIRONMENT_RESOURCE_URI = ViewpointUtil.ENVIRONMENT_URI_SCHEME + ":/diagram"; //$NON-NLS-1$

    /**
     * Avoid instantiation.
     */
    private SiriusDiagramUtil() {
        // empty.
    }

    /**
     * Find the functional analysis that owns the specified viewpoint.
     * 
     * @param diagram
     *            the viewpoint.
     * @return the found analysis or <code>null</code> if no analysis is found.
     */
    public static DView findRepresentationContainer(final DDiagram diagram) {
        EObject current = diagram;
        while (current != null) {
            if (current instanceof DView) {
                return (DView) current;
            }
            current = current.eContainer();
        }
        return null;
    }

    /**
     * Find parent {@link DiagramDescription} from a {@link DiagramElementMapping}.
     * 
     * @param mapping
     *            the {@link DiagramElementMapping} instance the current session
     * @return the first parent of mapping which is an instance of {@link DiagramDescription}
     */
    public static DiagramDescription findDiagramDescription(final DiagramElementMapping mapping) {
        EObject parent = mapping.eContainer();
        DiagramDescription diagramDescription = null;
        while (parent != null) {
            if (parent instanceof DiagramDescription) {
                diagramDescription = (DiagramDescription) parent;
                break;
            } else if (parent instanceof DiagramExtensionDescription) {
                diagramDescription = DiagramComponentizationHelper.getDiagramDescription((DiagramExtensionDescription) parent, ViewpointRegistry.getInstance().getViewpoints());
                break;
            }
            parent = parent.eContainer();
        }
        return diagramDescription;
    }
}
