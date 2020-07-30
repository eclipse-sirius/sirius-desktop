/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
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

    private DRepresentation representation;

    private Session session;

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
     * Create a new query.
     * 
     * @param representation
     *            the element to query.
     * @param session
     *            the session containing the representation.
     */
    public DRepresentationQuery(DRepresentation representation, Session session) {
        this.representation = representation;
        this.session = session;
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
     * Check if the current representation is a dangling representation, ie if its target element is null or if it does
     * not belong to any session.
     * 
     * @return true if the current representation is orphan.
     */
    public boolean isDanglingRepresentation() {
        if (representation instanceof DSemanticDecorator) {
            DSemanticDecorator semDecRep = (DSemanticDecorator) representation;
            if (session == null) {
                session = SessionManager.INSTANCE.getSession(semDecRep.getTarget());
            }
            return semDecRep.getTarget() == null || session == null;
        }
        return false;
    }

    /**
     * Get the {@link DRepresentationDescriptor} that references the {@link DRepresentation}.
     * 
     * @return the {@link DRepresentationDescriptor}
     */
    public DRepresentationDescriptor getRepresentationDescriptor() {
        DRepresentationDescriptor result = null;
        if (representation instanceof DSemanticDecorator) {
            if (session == null) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
            }
            if (session != null) {
                result = findDescriptorFromCrossReferencer();
                if (result == null) {
                    result = findDescriptorFromAnalysis();
                }
            } else {
                // There is no session (during a migration participant for example) so we search the analysis of the
                // eResource
                result = findDescriptorFromEResource();
            }
        }
        return result;
    }

    private DRepresentationDescriptor findDescriptorFromAnalysis() {
        DRepresentationDescriptor result = null;
        Collection<DAnalysis> allAnalyses = ((DAnalysisSessionImpl) session).allAnalyses();
        for (DAnalysis dAnalysis : allAnalyses) {
            result = findDescriptorFromAnalysis(dAnalysis);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    private DRepresentationDescriptor findDescriptorFromEResource() {
        DRepresentationDescriptor result = null;
        Resource eResource = representation.eResource();
        if (eResource != null && eResource.getContents() != null) {
            Iterator<EObject> contentsIterator = eResource.getContents().iterator();
            while (contentsIterator.hasNext()) {
                EObject content = contentsIterator.next();
                if (content instanceof DAnalysis) {
                    result = findDescriptorFromAnalysis((DAnalysis) content);
                }
                if (result != null) {
                    break;
                }
            }
        }
        return result;
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

        if (representation.eResource() != null) {
            EList<DView> ownedViews = dAnalysis.getOwnedViews();
            for (DView view : ownedViews) {
                EList<DRepresentationDescriptor> ownedRepresentationDescriptors = view.getOwnedRepresentationDescriptors();
                for (DRepresentationDescriptor descriptor : ownedRepresentationDescriptors) {
                    // the representation has to be seen as loaded from the descriptor otherwise it would mean that the
                    // DRepresentationDescriptor does not exists
                    if (descriptor.isLoadedRepresentation()) {
                        DRepresentation representationTemp = descriptor.getRepresentation();
                        if (representation.equals(representationTemp)) {
                            return descriptor;
                        }
                    }
                }
            }
        }
        return null;
    }

    private DRepresentationDescriptor findDescriptorFromCrossReferencer() {
        DRepresentationDescriptor result = null;
        Collection<EStructuralFeature.Setting> usages = session.getSemanticCrossReferencer().getInverseReferences(representation);
        for (EStructuralFeature.Setting setting : usages) {
            if (ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR.isInstance(setting.getEObject())
                    && setting.getEStructuralFeature() == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__REPRESENTATION) {
                result = (DRepresentationDescriptor) setting.getEObject();
                break;
            }
        }
        return result;
    }

}
