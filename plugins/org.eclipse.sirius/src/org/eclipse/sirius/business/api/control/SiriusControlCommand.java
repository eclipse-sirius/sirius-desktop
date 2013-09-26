/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.business.internal.command.control.ControlCommand;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A command to control semantic element in its own semantic resource and the
 * some associated representations in its own representations resource.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusControlCommand extends ControlCommand {

    /** The session containing the models to modify. */
    private final Session session;

    /**
     * The representations which must be controlled in addition to the semantic
     * element.
     */
    private final Set<DRepresentation> representations;

    /**
     * The URI of the <code>.aird</code> resource in which to move the
     * representations.
     */
    private final URI representationsDestination;

    /**
     * A boolean to set if the session should be save at the end of this
     * command.
     */
    private final boolean shouldEndBySaving;

    private IProgressMonitor monitor;

    /**
     * Create a new {@link SiriusControlCommand}.
     * 
     * @param semanticRoot
     *            the semantic element to control.
     * @param semanticDest
     *            the URI of the resource in which to control the semantic
     *            element.
     * @param representations
     *            the set of representations to control in addition to the
     *            semantic element.
     * @param representationsDest
     *            the URI of the <code>.aird</code> resource in which to move
     *            the representations.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of control
     *            operation
     */
    public SiriusControlCommand(final EObject semanticRoot, final URI semanticDest, final Set<DRepresentation> representations, final URI representationsDest, IProgressMonitor monitor) {
        this(semanticRoot, semanticDest, representations, representationsDest, true, monitor);
    }

    /**
     * Create a new {@link SiriusControlCommand}.
     * 
     * @param semanticRoot
     *            the semantic element to control.
     * @param semanticDest
     *            the URI of the resource in which to control the semantic
     *            element.
     * @param representations
     *            the set of representations to control in addition to the
     *            semantic element.
     * @param representationsDest
     *            the URI of the <code>.aird</code> resource in which to move
     *            the representations.
     * @param shouldEndBySaving
     *            A boolean to set if the session should be save at the end of
     *            this command.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of control
     *            operation
     */
    public SiriusControlCommand(final EObject semanticRoot, final URI semanticDest, final Set<DRepresentation> representations, final URI representationsDest, final boolean shouldEndBySaving,
            IProgressMonitor monitor) {
        super(semanticRoot, semanticDest);
        this.session = SessionManager.INSTANCE.getSession(semanticRoot);
        this.representations = Sets.newHashSet(representations);
        this.representationsDestination = representationsDest;
        this.shouldEndBySaving = shouldEndBySaving;
        this.monitor = monitor;
    }

    /**
     * Executes the control command.
     */
    @Override
    protected void doExecute() {
        try {
            monitor.beginTask("Control resource", 3);
            super.doExecute(); // Control the semantic model
            monitor.worked(1);
            markContainerResourceAsModified(semanticObjectToControl.eContainer());
            enableTrackingModification(controlledResource);
            createNewRepresentationsFileAndMoveRepresentations();
            monitor.worked(1);
            notifySessionAboutControlledModel();
            if (shouldEndBySaving) {
                session.save(new SubProgressMonitor(monitor, 1));
            }
        } finally {
            monitor.done();
        }
    }

    private void markContainerResourceAsModified(final EObject obj) {
        EObject rootContainer = getRootContainer(obj);
        if (obj != null && rootContainer != null && rootContainer.eResource() != null) {
            rootContainer.eResource().setModified(true);
        }
    }

    /**
     * Get root container of specified object.<br>
     * Default implementation consists in getting the resource container i.e the
     * first parent container serialized in its own resource.
     * 
     * @param eObject
     *            the current EObject.
     * @return should not be <code>null</code>
     */
    protected EObject getRootContainer(EObject eObject) {
        return new EObjectQuery(eObject).getResourceContainer();
    }

    private void enableTrackingModification(final Resource resource) {
        if (!resource.isTrackingModification()) {
            resource.setTrackingModification(true);
        }
    }

    /**
     * Create a new representations resource if needed :
     * <UL>
     * <LI>some representations must be moved</LI>
     * <LI>or the preference to create empty representations file is set to true
     * </LI>
     * </UL>
     * then move the representations to this new resource (if there are some
     * representations to move).
     */
    private void createNewRepresentationsFileAndMoveRepresentations() {
        boolean emptyAirdFragmentOnControl = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, DesignerPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), false, null);
        if (representations.isEmpty() && !emptyAirdFragmentOnControl) {
            return;
        }
        final Resource newRepresentationsFile;
        if (representations.isEmpty() && emptyAirdFragmentOnControl) {
            // It is allowed to create an aird fragment with no representation
            Resource firstAird = session.getSessionResource();
            newRepresentationsFile = firstAird.getResourceSet().createResource(representationsDestination);
            // Creation of a DRepresentationContainer for each session
            // viewpoint. This way, we will be able to open the empty aird
            // fragment with the viewpoints properly set
            DAnalysis newDAnalysis = getDAnalysis(newRepresentationsFile);
            for (Viewpoint viewpoint : session.getSelectedSiriuss(false)) {
                DRepresentationContainer createDRepresentationContainer = ViewpointFactory.eINSTANCE.createDRepresentationContainer();
                createDRepresentationContainer.setViewpoint(viewpoint);
                newDAnalysis.getOwnedViews().add(createDRepresentationContainer);
                newDAnalysis.getSelectedViews().add(createDRepresentationContainer);
            }

        } else {
            newRepresentationsFile = getOrCreateChildResource(getParentAird(), representationsDestination);
        }
        final DAnalysis newDAnalysis = getDAnalysis(newRepresentationsFile);
        // Add the new semantic root to the models reference of the new analysis
        newDAnalysis.getModels().add(this.controlledResource.getContents().get(0));
        // Update the referencedAnalysis according to the new analysis
        updateReferencedAnalysisReferences(newDAnalysis);

        // Move the selected representations to the new analysis
        moveRepresentations(newDAnalysis);
        // Update the models references of all representations files (except the
        // representations file of the new analysis that will be updated during
        // the moveRepresentations) of this session according to the
        // representations
        updateModelsReferences(newDAnalysis);
    }

    /**
     * The new analysis is added to the referencedAnalysis of the first parent
     * representations file and the children analysis of this first parent
     * representations file are analyzed to be eventually moved.<BR>
     * <UL>
     * <LI>Take the root of the resource container the parent of the controlled
     * semantic element</LI>
     * <LI>For each analysis that have this root as first models:</LI>
     * <UL>
     * <LI>If the controlled semantic element contains the first models of a
     * referencedAnalysis of the current analysis, then move this one in the new
     * analysis (this corresponds to a fragmentation of intermediate level).</LI>
     * <LI>Add the new analysis to the referencedAnalysis references</LI>
     * <UL>
     * </UL>
     * 
     * @param newDAnalysis
     *            The new analysis
     */
    private void updateReferencedAnalysisReferences(final DAnalysis newDAnalysis) {
        EObject semanticParentRoot = getRootContainer(semanticObjectToControl.eContainer());
        Set<DAnalysis> referencers = Sets.newLinkedHashSet();

        for (Resource aird : session.getAllSessionResources()) {
            DAnalysis currentAnalysis = getDAnalysis(aird);
            Option<EObject> optionalMainModel = new DAnalysisQuery(currentAnalysis).getMainModel();
            if (optionalMainModel.some() && optionalMainModel.get().equals(semanticParentRoot)) {
                List<DAnalysis> referencedAnalysis = new ArrayList<DAnalysis>(currentAnalysis.getReferencedAnalysis());
                for (DAnalysis childrenAnalysis : referencedAnalysis) {
                    Option<EObject> optionalChildrenMainModel = new DAnalysisQuery(childrenAnalysis).getMainModel();
                    if (optionalChildrenMainModel.some() && new EObjectQuery(optionalChildrenMainModel.get()).isContainedIn(semanticObjectToControl)) {
                        currentAnalysis.getReferencedAnalysis().remove(childrenAnalysis);
                        newDAnalysis.getReferencedAnalysis().add(childrenAnalysis);
                    }
                }
                referencers.add(currentAnalysis);
            }
        }

        if (!referencers.isEmpty() && session instanceof DAnalysisSession) {
            // Let the session set the reference and add adapters (visibility
            // propagator, semantic cross referencer, representation change
            // adapter, ...)
            ((DAnalysisSession) session).addReferencedAnalysis(newDAnalysis, referencers);
        }
    }

    /**
     * Move the selected representations from this session to another analysis.
     * The models references of the target analysis are updated according to the
     * moved representations.
     * 
     * @param targetAnalysis
     *            The analysis in which the representations must be moved.
     */
    private void moveRepresentations(final DAnalysis targetAnalysis) {
        for (final DRepresentation representation : representations) {
            ((DAnalysisSession) session).moveRepresentation(targetAnalysis, representation);
        }
    }

    /**
     * Update the models references of all representations files of this
     * session.
     * 
     * @param analysisToIgnore
     *            The models references of this DAnalysis will not be updated.
     */
    private void updateModelsReferences(DAnalysis analysisToIgnore) {
        for (Resource resource : ((DAnalysisSession) session).getAllSessionResources()) {
            for (EObject content : resource.getContents()) {
                if (content instanceof DAnalysis && !content.equals(analysisToIgnore)) {
                    for (final DView view : ((DAnalysis) content).getOwnedViews()) {
                        if (view instanceof DRepresentationContainer) {
                            DAnalysisSessionHelper.updateModelsReferences((DAnalysis) content, Iterators.filter(((DRepresentationContainer) view).eAllContents(), DSemanticDecorator.class));
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the current resource containing the representations to move.
     */
    private Resource getParentAird() {
        return representations.iterator().next().eResource();
    }

    /**
     * Returns the first DAnalysis among the roots of the specified resource.
     * Creates and adds a new one if none is found.
     */
    private DAnalysis getDAnalysis(final Resource aird) {
        for (EObject root : aird.getContents()) {
            if (root instanceof DAnalysis) {
                return (DAnalysis) root;
            }
        }

        final DAnalysis newAnalysis = ViewpointFactory.eINSTANCE.createDAnalysis();
        aird.getContents().add(newAnalysis);
        return newAnalysis;
    }

    private void notifySessionAboutControlledModel() {
        if (session instanceof DAnalysisSession) {
            ((DAnalysisSession) session).notifyControlledModel(controlledResource);
        }
    }

}
