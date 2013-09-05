/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
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
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.sirius.DAnalysis;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.business.api.session.Session;

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
     * Adds the given representation to the given analysis.
     * <p>
     * If the given representation is already in the given analysis then the
     * operation has no effect.
     * </p>
     * The models references of the newContainer are updated according to the
     * new representation.
     * 
     * @param newContainer
     *            the new container of the representation (must not be
     *            <code>null</code>).
     * @param representation
     *            the representation to add (must not be <code>null</code>).
     */
    void moveRepresentation(final DAnalysis newContainer, final DRepresentation representation);

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
     * @since 2.1
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
     * Saves the session data using the specified options.
     * 
     * <p>
     * Options are handled generically as feature-to-setting entries; the
     * resource will ignore options it doesn't recognize. The options could even
     * include things like an Eclipse progress monitor...
     * </p>
     * <p>
     * An implementation typically uses the
     * {@link org.eclipse.emf.ecore.resource.ResourceSet#getURIConverter URI
     * converter} of the {@link #getResourceSet containing} resource set to
     * {@link org.eclipse.emf.ecore.resource.URIConverter#createOutputStream
     * create} an output stream, and then delegates to
     * {@link #save(java.io.OutputStream, Map) save(OutputStream, Map)}.
     * </p>
     * 
     * @param options
     *            the save options.
     * @since 2.6
     * @deprecated use
     *             {@link Session#save(Map, org.eclipse.core.runtime.IProgressMonitor)}
     *             instead
     */
    void save(Map<?, ?> options);

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
