/*******************************************************************************
 * Copyright (c) 2008, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.session.danalysis;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * The contract for DAnalysis session.
 * 
 * @author mchauvin
 */
public interface DAnalysisSession extends Session {

    /**
     * Add an analysis to this session.
     * 
     * @param analysisResource
     *            the resource which contains the analysis to add
     */
    void addAnalysis(Resource analysisResource);

    /**
     * Remove an analysis to this session.
     * 
     * @param analysisResource
     *            the resource which contains the analysis to remove
     */
    void removeAnalysis(Resource analysisResource);

    /**
     * Set the analysis selector.
     * 
     * @param selector
     *            the selector
     */
    void setAnalysisSelector(DAnalysisSelector selector);

    /**
     * Adds the given representation descriptor to the given analysis. The
     * corresponding representation is moved as root object into the resource of
     * the target DAnalysis.
     * <p>
     * If the given representation descriptor is already in the given analysis
     * then the operation has no effect.
     * </p>
     * The models references of the newContainer are updated according to the
     * new representation descriptor.
     * 
     * @param newDAnalysisContainer
     *            the new container of the representation descriptor(must not be
     *            <code>null</code>).
     * @param repDescriptor
     *            corresponds to the representation to move (must not be
     *            <code>null</code>).
     */
    void moveRepresentation(final DAnalysis newDAnalysisContainer, final DRepresentationDescriptor repDescriptor);

    /**
     * Add a referenced analysis.
     * 
     * <p>
     * If the given analysis is already in the referenced analysis then the
     * operation has no effect.
     * </p>
     * <p>
     * The main analysis will reference the given one.
     * </p>
     * 
     * @param analysis
     *            the analysis to reference (must not be <code>null</code> ).
     */
    void addReferencedAnalysis(final DAnalysis analysis);

    /**
     * Add a referenced analysis.
     * 
     * <p>
     * If the given analysis is already in the referenced analysis then the
     * operation has no effect.
     * </p>
     * 
     * 
     * @param analysis
     *            the analysis to reference (must not be <code>null</code> ).
     * 
     * @param referencers
     *            the analysis on which the reference is added (must not be
     *            <code>null</code>).
     */
    void addReferencedAnalysis(final DAnalysis analysis, final Collection<DAnalysis> referencers);

    /**
     * Remove a referenced analysis.
     * 
     * <p>
     * If the given analysis is not in the referenced analysis then the
     * operation has no effect.
     * </p>
     * 
     * 
     * @param analysis
     *            the referenced analysis to remove (must not be
     *            <code>null</code> ).
     * 
     * @since 0.9.0
     */
    void removeReferencedAnalysis(final DAnalysis analysis);

    /**
     * Clients should call that method when a control action has been applied on
     * a semantic model.
     * 
     * @param newControlled
     *            the newly controlled resource.
     */
    void notifyControlledModel(Resource newControlled);

    /**
     * Clients should call that method when an uncontrol action has been applied
     * on an EObject.
     * 
     * @param uncontrolled
     *            the uncontrolled element.
     * @param resource
     *            the uncontrolled resource
     */
    void notifyUnControlledModel(EObject uncontrolled, Resource resource);

    /**
     * Add adapters on the current analysis.
     * 
     * @param analysis
     *            the current analysis.
     */
    void addAdaptersOnAnalysis(DAnalysis analysis);

    /**
     * Remove adapters from the current analysis.
     * 
     * @param analysis
     *            the current analysis.
     */
    void removeAdaptersOnAnalysis(DAnalysis analysis);
}
