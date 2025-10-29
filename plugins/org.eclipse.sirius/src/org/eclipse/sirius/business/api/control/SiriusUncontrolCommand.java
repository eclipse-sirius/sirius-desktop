/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.internal.command.control.UncontrolCommand;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * An extension of the basic {@link UncontrolCommand} to handle both the semantic model and the corresponding Sirius
 * representations. Also handles session state and modification-tracking management.
 * 
 * @since 0.9.0
 * 
 * @author pcdavid
 */
public class SiriusUncontrolCommand extends UncontrolCommand {

    /** The session containing the models to modify. */
    private final Session session;

    /**
     * A flag to indicate if we should uncontrol the representations in addition to the semantic elements.
     */
    private final boolean uncontrolRepresentations;

    /**
     * A boolean to set if the session should be save at the end of this command.
     */
    private final boolean shouldEndBySaving;

    private IProgressMonitor monitor;

    /**
     * Create a new {@link SiriusUncontrolCommand}.
     * 
     * @param semanticRoot
     *            the semantic model element to uncontrol.
     * @param uncontrolRepresentations
     *            indicate if we should uncontrol the representations in addition to the semantic elements.
     * @param shouldEndBySaving
     *            A boolean to set if the session should be save at the end of
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of uncontrol operation
     */
    public SiriusUncontrolCommand(final EObject semanticRoot, final boolean uncontrolRepresentations, boolean shouldEndBySaving, IProgressMonitor monitor) {
        super(semanticRoot);
        this.uncontrolRepresentations = uncontrolRepresentations;
        this.session = SessionManager.INSTANCE.getSession(semanticRoot);
        this.shouldEndBySaving = shouldEndBySaving;
        this.monitor = monitor;
    }

    /**
     * Get root container of specified object.<br>
     * Default implementation consists in getting the resource container i.e the first parent container serialized in
     * its own resource.
     * 
     * @param eObject
     *            the current EObject.
     * @return should not be <code>null</code>
     */
    protected EObject getRootContainer(EObject eObject) {
        return new EObjectQuery(eObject).getResourceContainer();
    }

    /**
     * Execute the uncontrol action.
     */
    @Override
    protected void doExecute() {
        try {
            monitor.beginTask(Messages.SiriusUncontrolCommand_label, 4);

            Resource childSemanticResource = semanticElementToUncontrol.eResource();
            Resource childAirdResource = getChildAirdResource();

            // get the parent aird resource before uncontrol.
            Resource parentAirdResource = getParentAirdResource();

            // Uncontrol the semantic model
            super.doExecute();
            monitor.worked(1);

            markContainerResourceAsModified(semanticElementToUncontrol.eContainer());
            if (uncontrolRepresentations && childAirdResource != null) {
                moveRepresentations(childAirdResource, parentAirdResource, semanticElementToUncontrol.eResource());
            }
            monitor.worked(1);
            cleanupChildrenResources(childSemanticResource, childAirdResource);
            monitor.worked(1);
            if (shouldEndBySaving) {
                session.save(new SubProgressMonitor(monitor, 1));
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Remove the children semantic and representation resources after uncontrol, both from the session and from the
     * disk, if they are empty.
     */
    private void cleanupChildrenResources(final Resource childSemanticResource, final Resource childAirdResource) {
        notifySessionAboutUncontrolledResource(childSemanticResource);
        deleteResource(childSemanticResource);
        if (childAirdResource != null && uncontrolRepresentations && airdResourceHasNoRepresentations(childAirdResource)) {
            removeObsoleteAnalyis(childAirdResource);
            notifySessionAboutUncontrolledResource(childAirdResource);
            deleteResource(childAirdResource);
        }
    }

    private void removeObsoleteAnalyis(final Resource childAirdResource) {
        if (session instanceof DAnalysisSession) {
            ((DAnalysisSession) session).removeReferencedAnalysis(getDAnalysis(childAirdResource));
        }
    }

    private boolean airdResourceHasNoRepresentations(final Resource childAirdResource) {
        return Iterators.size(Iterators.filter(EcoreUtil.getAllProperContents(childAirdResource, true), DRepresentation.class)) == 0;
    }

    private Resource getChildAirdResource() {
        return getAirdResourceWithAnalysisOn(semanticElementToUncontrol);
    }

    private Resource getParentAirdResource() {
        return getAirdResourceWithAnalysisOn(getRootContainer(semanticElementToUncontrol.eContainer()));
    }

    /**
     * Get the resource that contains the analysis which has the specified object as main models references.
     * 
     * @param object
     *            The object to search. This object must be a root of a semantic model.
     * @return the corresponding resource if found, false otherwise.
     */
    protected Resource getAirdResourceWithAnalysisOn(final EObject object) {

        for (final Resource anResource : session.getAllSessionResources()) {
            for (final DAnalysis analysis : getAnalyses(anResource)) {
                Set<EObject> releventModels = new DAnalysisQuery(analysis).getMainModels();
                if (Iterables.contains(releventModels, object)) {
                    return anResource;
                }
            }
        }
        return null;
    }

    /**
     * Returns all DAnalysis among the roots of the specified resource.
     * 
     * @param aird
     *            the resource in which to search
     * @return list of DAnalysis of this resource
     */
    protected Collection<DAnalysis> getAnalyses(final Resource aird) {
        final Collection<DAnalysis> analyses = new ArrayList<>();
        for (EObject root : aird.getContents()) {
            if (root instanceof DAnalysis) {
                analyses.add((DAnalysis) root);
            }
        }
        return analyses;
    }

    /**
     * Moves all the representations stored in <code>childResource</code> into <code>parentResource</code>.
     */
    private void moveRepresentations(final Resource childResource, final Resource parentResource, final Resource parentSemanticResource) {
        final Collection<DRepresentationDescriptor> repDescriptors = collectExistingRepresentations(childResource);
        final DAnalysis parentAnalysis = getDAnalysis(parentResource);
        final DAnalysis childAnalysis = getDAnalysis(childResource);

        final DAnalysisSession aSession = (DAnalysisSession) session;
        for (final DRepresentationDescriptor repDescriptor : repDescriptors) {
            aSession.moveRepresentation(parentAnalysis, repDescriptor);
        }

        if (childAnalysis != null) {
            parentAnalysis.getReferencedAnalysis().addAll(childAnalysis.getReferencedAnalysis());
        }
    }

    private DAnalysis getDAnalysis(final Resource parentResource) {
        for (final EObject root : parentResource.getContents()) {
            if (root instanceof DAnalysis) {
                return (DAnalysis) root;
            }
        }
        return null;
    }

    private Collection<DRepresentationDescriptor> collectExistingRepresentations(final Resource resource) {
        return Sets.newHashSet(Iterators.filter(EcoreUtil.getAllProperContents(resource, true), DRepresentationDescriptor.class));
    }

    private void markContainerResourceAsModified(final EObject obj) {
        EObject resourceContainer = getRootContainer(obj);
        if (obj != null && resourceContainer != null) {
            Resource containerResource = resourceContainer.eResource();
            if (containerResource != null) {
                containerResource.setModified(true);
            }
        }
    }

    /**
     * Delete the physical workspace file underlying a resource, if any and unload and remove this resource from its
     * resourceSet.
     * 
     * @param res
     *            the resource to delete
     */
    protected void deleteResource(final Resource res) {
        if (res != null) {
            try {
                res.save(Collections.emptyMap());
                res.delete(Collections.emptyMap());
            } catch (IOException e) {
                SiriusPlugin.getDefault().error(Messages.SiriusUncontrolCommand_resourceDeletionFailedMsg, e);
            }
        }
    }

    private void notifySessionAboutUncontrolledResource(final Resource controlledSemanticResource) {
        if (session instanceof DAnalysisSession) {
            ((DAnalysisSession) session).notifyUnControlledModel(semanticElementToUncontrol, controlledSemanticResource);
        }
    }
}
