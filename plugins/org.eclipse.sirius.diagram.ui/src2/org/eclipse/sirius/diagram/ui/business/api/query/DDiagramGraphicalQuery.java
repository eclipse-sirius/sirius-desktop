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
package org.eclipse.sirius.diagram.ui.business.api.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;

/**
 * A class aggregating all the graphical queries (read-only!) having a
 * {@link DDiagram} as a starting point.
 * 
 * @author lredor
 * 
 */
public class DDiagramGraphicalQuery extends DDiagramQuery {

    /**
     * Create a new query.
     * 
     * @param dDiagram
     *            the element to query.
     */
    public DDiagramGraphicalQuery(DDiagram dDiagram) {
        super(dDiagram);
    }

    /**
     * Return the GMF diagram associated with this DDiagram.
     * 
     * @return the GMF diagram associated with this DDiagram.
     */
    public Option<Diagram> getAssociatedGMFDiagram() {
        for (final AnnotationEntry annotation : new DDiagramQuery(dDiagram).getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
            EObject eObject = annotation.getData();
            if (eObject instanceof Diagram) {
                final Diagram diagramInResource = (Diagram) eObject;
                final EObject semanticElement = ViewUtil.resolveSemanticElement(diagramInResource);
                if (semanticElement == dDiagram) {
                    return Options.newSome(diagramInResource);
                }
            }
        }
        return Options.newNone();
    }

    /**
     * Return true if the header must be enabled, false otherwise.
     * 
     * @return true if the header must be enabled, false otherwise.
     */
    public boolean isHeaderSectionEnabled() {
        boolean isHeaderSectionEnabled = false;

        if (dDiagram.getDescription() != null) {
            isHeaderSectionEnabled = new DiagramDescriptionQuery(dDiagram.getDescription()).isHeaderSectionEnabled();
        }
        return isHeaderSectionEnabled;
    }
}
