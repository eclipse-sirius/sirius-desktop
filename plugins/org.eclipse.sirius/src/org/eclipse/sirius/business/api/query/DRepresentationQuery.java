/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.query;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.query.DRepresentationWithSessionInternalQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DAnnotation;

/**
 * A class aggregating all the queries (read-only!) having a {@link DRepresentation} as a starting point and a potential
 * session.
 * 
 * @author mporhel
 * 
 */
public class DRepresentationQuery {

    private DRepresentationWithSessionInternalQuery internalQuery;

    /**
     * Create a new query.
     * 
     * @param representation
     *            the element to query.
     */
    public DRepresentationQuery(DRepresentation representation) {
        this.internalQuery = new DRepresentationWithSessionInternalQuery(representation);
    }

    /**
     * Create a new query.
     * 
     * @param representation
     *            the element to query.
     * @param session
     *            the session containing the representation.
     */
    public DRepresentationQuery(DRepresentation representation, Session session) {
        this.internalQuery = new DRepresentationWithSessionInternalQuery(representation, session);
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
        return internalQuery.getAnnotation(source, eObject);
    }

    /**
     * Get all the annotations of a representation with id source.
     * 
     * @param source
     *            the source of the annotation
     * @return the annotation entries
     */
    public Collection<AnnotationEntry> getAnnotation(final String source) {
        return internalQuery.getAnnotation(source);
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
        return internalQuery.getDAnnotation(source, detail);
    }

    /**
     * Check if the current representation is a dangling representation, ie if its target element is null or if it does
     * not belong to any session.
     * 
     * @return true if the current representation is orphan.
     */
    public boolean isDanglingRepresentation() {
        return internalQuery.isDanglingRepresentation();
    }

    /**
     * Get the {@link DRepresentationDescriptor} that references the {@link DRepresentation}.
     * 
     * @return the {@link DRepresentationDescriptor}
     */
    public DRepresentationDescriptor getRepresentationDescriptor() {
        return internalQuery.getRepresentationDescriptor();
    }

    /**
     * Find a {@link DRepresentationDescriptor} that references the {@link DRepresentation} in the given
     * {@link DAnalysis}.
     * 
     * @param dAnalysis
     *            a {@link DAnalysis}
     * @return the {@link DRepresentationDescriptor} if a descriptor has been found and null otherwise.
     */
    public DRepresentationDescriptor findDescriptorFromAnalysis(DAnalysis dAnalysis) {
        return internalQuery.findDescriptorFromAnalysis(dAnalysis);
    }

    /**
     * Gives the value of the preference {@link SiriusPreferencesKeys}.PREF_AUTO_REFRESH for this representation.
     * 
     * @return the value
     */
    public boolean isAutoRefresh() {
        return internalQuery.isAutoRefresh();
    }

}
