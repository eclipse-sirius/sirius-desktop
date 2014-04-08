/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * Utility class for managing DDiagram models.
 * 
 * @author cbrun
 */
public final class SiriusHelper {

    /**
     * Avoid instantiation.
     */
    private SiriusHelper() {

    }

    /**
     * Get, or create if there is none, a migration annotation for a group.
     * 
     * @param source
     *            the source of the annotation
     * 
     * @param representation
     *            the DRepresentation
     * @param eObject
     *            the data of the annotation
     * @return the annotation entry
     */
    public static AnnotationEntry getOrCreateAnnotation(final String source, final DRepresentation representation, final EObject eObject) {
        AnnotationEntry annotation = new DRepresentationQuery(representation).getAnnotation(source, eObject).get();
        if (annotation == null && eObject != null) {
            annotation = DescriptionFactory.eINSTANCE.createAnnotationEntry();
            annotation.setSource(source);
            annotation.setData(eObject);
            representation.getOwnedAnnotationEntries().add(annotation);
        }
        return annotation;
    }
}
