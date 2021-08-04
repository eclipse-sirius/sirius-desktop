/*******************************************************************************
 * Copyright (c) 201, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.query.model;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DModelElement;

/**
 * Query defining all queries for DModelElement.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class DModelElementInternalQuery {

    /**
     * The {@link DModelElement} which are the target of the Query.
     */
    protected DModelElement dModelElement;

    /**
     * Create a new query.
     * 
     * @param dModelElement
     *            the model element to query
     */
    public DModelElementInternalQuery(DModelElement dModelElement) {
        this.dModelElement = dModelElement;
    }

    /**
     * Return the annotation corresponding to source.
     * 
     * @param source
     *            The source of annotation
     * @return an annotation {@link DAnnotation}
     */
    // CHECKSTYLE:OFF
    public DAnnotation getDAnnotation(String source) {
        if (dModelElement.getEAnnotations() != null) {
            if (dModelElement.getEAnnotations() instanceof BasicEList) {
                int size = dModelElement.getEAnnotations().size();
                if (size > 0) {
                    DAnnotation[] eAnnotationArray = (DAnnotation[]) ((BasicEList<?>) dModelElement.getEAnnotations()).data();
                    if (source == null) {
                        for (int i = 0; i < size; ++i) {
                            DAnnotation eAnnotation = eAnnotationArray[i];
                            if (eAnnotation.getSource() == null) {
                                return eAnnotation;
                            }
                        }
                    } else {
                        for (int i = 0; i < size; ++i) {
                            DAnnotation eAnnotation = eAnnotationArray[i];
                            if (source.equals(eAnnotation.getSource())) {
                                return eAnnotation;
                            }
                        }
                    }
                }
            } else {
                if (source == null) {
                    for (DAnnotation eAnnotation : dModelElement.getEAnnotations()) {
                        if (eAnnotation.getSource() == null) {
                            return eAnnotation;
                        }
                    }
                } else {
                    for (DAnnotation eAnnotation : dModelElement.getEAnnotations()) {
                        if (source.equals(eAnnotation.getSource())) {
                            return eAnnotation;
                        }
                    }
                }
            }
        }

        return null;
    }
    // CHECKSYLE:ON

}
