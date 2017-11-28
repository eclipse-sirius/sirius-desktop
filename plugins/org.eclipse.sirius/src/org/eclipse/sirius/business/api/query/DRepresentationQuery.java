/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DAnnotation;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DRepresentation} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class DRepresentationQuery {

    private DRepresentation representation;

    /**
     * Create a new query.
     * 
     * @param representation
     *            the element to query.
     */
    public DRepresentationQuery(DRepresentation representation) {
        this.representation = representation;
    }

    /**
     * Get the annotation of a representation with id source and data eObject.
     * 
     * @param source
     *            the source of the annotation
     * @param eObject
     *            the data of the annotation
     * @return the annotation entry
     */
    public Option<AnnotationEntry> getAnnotation(final String source, final EObject eObject) {
        for (AnnotationEntry annotation : representation.getOwnedAnnotationEntries()) {
            if (source.equals(annotation.getSource()) && eObject.equals(annotation.getData())) {
                return Options.newSome(annotation);
            }
        }
        return Options.newNone();
    }

    /**
     * Get all the annotations of a representation with id source.
     * 
     * @param source
     *            the source of the annotation
     * @return the annotation entries
     */
    public Collection<AnnotationEntry> getAnnotation(final String source) {
        final Collection<AnnotationEntry> annotationEntries = new ArrayList<>();
        for (AnnotationEntry annotation : representation.getOwnedAnnotationEntries()) {
            if (source.equals(annotation.getSource())) {
                annotationEntries.add(annotation);
            }
        }
        return annotationEntries;
    }

    /**
     * Get the annotation of a representation with id source and map details.
     * 
     * @param source
     *            the source of the annotation
     * @param detail
     *            the detail of the annotations
     * @return the annotation
     */
    public Option<DAnnotation> getDAnnotation(final String source, String detail) {
        DAnnotation annotation = representation.getDAnnotation(source);
        if (annotation != null && annotation.getDetails().get(detail) != null) {
            return Options.newSome(annotation);
        }
        return Options.newNone();
    }

    /**
     * Check if the current representation is a dangling representation, ie if
     * its target element is null or if it does not belong to any session.
     * 
     * @return true if the current representation is orphan.
     */
    public boolean isDanglingRepresentation() {
        if (representation instanceof DSemanticDecorator) {
            DSemanticDecorator semDecRep = (DSemanticDecorator) representation;
            return semDecRep.getTarget() == null || SessionManager.INSTANCE.getSession(semDecRep.getTarget()) == null;
        }
        return false;
    }

    /**
     * Get the {@link DRepresentationDescriptor} that references the
     * {@link DRepresentation}.
     * 
     * @return the {@link DRepresentationDescriptor}
     */
    public DRepresentationDescriptor getRepresentationDescriptor() {
        if (representation instanceof DSemanticDecorator) {
            Session session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
            if (session != null) {
                Collection<EStructuralFeature.Setting> usages = session.getSemanticCrossReferencer().getInverseReferences(representation);
                for (EStructuralFeature.Setting setting : usages) {
                    if (ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR.isInstance(setting.getEObject())
                            && setting.getEStructuralFeature() == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__REPRESENTATION) {
                        return (DRepresentationDescriptor) setting.getEObject();
                    }
                }
            }
        }
        return null;
    }
}
