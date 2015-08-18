/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.refresh;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;

/**
 * An utility to create GMF diagram if necessary. (We may need to ask session
 * more than walking in the resource).
 * 
 * @author mchauvin
 */
public final class DiagramCreationUtil {

    private DDiagram diagram;

    private Diagram gmfDiagram;

    /**
     * Construct a new instance.
     * 
     * @param diagram
     *            the Sirius diagram
     */
    public DiagramCreationUtil(final DDiagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Find the GMF diagram associated to the Sirius one in its resource.
     * 
     * @return the associated GMF diagram if there is one, <code>null</code> if
     *         there is none
     */
    public boolean findAssociatedGMFDiagram() {
        for (final EObject annotation : new DRepresentationQuery(diagram).getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
            if (annotation instanceof AnnotationEntry) {
                EObject eObject = ((AnnotationEntry) annotation).getData();
                if (eObject instanceof Diagram) {
                    final Diagram diagramInResource = (Diagram) eObject;
                    final EObject semanticElement = ViewUtil.resolveSemanticElement(diagramInResource);
                    if (semanticElement == diagram) {
                        gmfDiagram = diagramInResource;
                    }
                }
            }
        }
        return gmfDiagram != null;
    }

    /**
     * Get the GMF Diagram associated to the Sirius diagram.
     * 
     * @return the associated GMF diagram to the Sirius one.
     */
    public Diagram getAssociatedGMFDiagram() {
        return this.gmfDiagram;
    }

    /**
     * Create a new GMF diagram from the Sirius one.
     * 
     */
    public void createNewGMFDiagram() {
        gmfDiagram = ViewService.createDiagram(diagram, getDiagramKind(), getPreferencesHint());
        if (gmfDiagram == null) {
            // do nothing -> we may log something in trace mode
        }
    }

    private PreferencesHint getPreferencesHint() {
        return new PreferencesHint("org.eclipse.sirius.diagram"); //$NON-NLS-1$
    }

    private String getDiagramKind() {
        return "Sirius"; //$NON-NLS-1$
    }

}
