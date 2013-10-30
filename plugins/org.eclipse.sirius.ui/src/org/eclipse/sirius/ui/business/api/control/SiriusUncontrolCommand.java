/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.control;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.internal.command.control.UncontrolCommand;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * An extension of the basic {@link UncontrolCommand} to handle both the
 * semantic model and the corresponding Sirius representations. Also handles
 * session state and modification-tracking management.
 * 
 * @since 2.1
 * 
 * @author pcdavid
 * @deprecated use
 *             {@link org.eclipse.sirius.business.api.control.SiriusUncontrolCommand}
 *             instead
 */
public class SiriusUncontrolCommand extends UncontrolCommand {

    /**
     * The session containing the models to modify.
     */
    private final Session session;

    /**
     * A flag to indicate if we should uncontrol the representations in addition
     * to the semantic elements.
     */
    private final boolean uncontrolRepresentations;

    /**
     * Create a new {@link SiriusUncontrolCommand}.
     * 
     * @param semanticRoot
     *            the semantic model element to uncontrol.
     * @param uncontrolRepresentations
     *            indicate if we should uncontrol the representations in
     *            addition to the semantic elements.
     */
    public SiriusUncontrolCommand(final EObject semanticRoot, final boolean uncontrolRepresentations) {
        super(semanticRoot);
        this.uncontrolRepresentations = uncontrolRepresentations;
        this.session = SessionManager.INSTANCE.getSession(semanticRoot);
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

    /**
     * Execute the uncontrol action.
     */
    @Override
    protected void doExecute() {
        Resource childSemanticResource = semanticElementToUncontrol.eResource();
        Resource childAirdResource = getChildAirdResource();

        // get the parent aird resource before uncontrol.
        Resource parentAirdResource = getParentAirdResource();

        // Uncontrol the semantic model
        super.doExecute();

        markContainerResourceAsModified(semanticElementToUncontrol.eContainer());
        if (uncontrolRepresentations && childAirdResource != null) {
            moveRepresentations(childAirdResource, parentAirdResource, semanticElementToUncontrol.eResource());
        }
        cleanupChildrenResources(childSemanticResource, childAirdResource);
        saveSession(); // Auto save the session
    }

    /**
     * Remove the children semantic and representation resources after
     * uncontrol, both from the session and from the disk, if they are empty.
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
     * Get the resource that contains the analysis which has the specified
     * object as main models references.
     * 
     * @param object
     *            The object to search. This object must be a root of a semantic
     *            model.
     * @return the corresponding resource if found, false otherwise.
     */
    protected Resource getAirdResourceWithAnalysisOn(final EObject object) {

        for (final Resource anResource : session.getAllSessionResources()) {
            for (final DAnalysis analysis : getAnalyses(anResource)) {
                Option<EObject> optionalMainModel = new DAnalysisQuery(analysis).getMainModel();
                if (optionalMainModel.some() && optionalMainModel.get().equals(object)) {
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
        final Collection<DAnalysis> analyses = Lists.newArrayList();
        for (EObject root : aird.getContents()) {
            if (root instanceof DAnalysis) {
                analyses.add((DAnalysis) root);
            }
        }
        return analyses;
    }

    /**
     * Moves all the representations stored in <code>childResource</code> into
     * <code>parentResource</code>.
     */
    private void moveRepresentations(final Resource childResource, final Resource parentResource, final Resource parentSemanticResource) {
        final Collection<DRepresentation> representations = collectExistingRepresentations(childResource);
        final DAnalysis parentAnalysis = getDAnalysis(parentResource);
        final DAnalysis childAnalysis = getDAnalysis(childResource);

        final DAnalysisSession aSession = (DAnalysisSession) session;
        final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        for (final DRepresentation representation : representations) {
            if (uiSession != null) {
                closeOpenedEditor(uiSession, representation);
            }
            aSession.moveRepresentation(parentAnalysis, representation);
        }

        if (childAnalysis != null) {
            parentAnalysis.getReferencedAnalysis().addAll(childAnalysis.getReferencedAnalysis());
        }
    }

    /**
     * Close any editor opened on a representation about to move.
     */
    private void closeOpenedEditor(final IEditingSession uiSession, final DRepresentation representation) {
        final IEditorPart editor = uiSession.getEditor(representation);
        if (editor != null) {
            editor.getEditorSite().getPage().closeEditor(editor, false);
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

    private Collection<DRepresentation> collectExistingRepresentations(final Resource resource) {
        return Sets.newHashSet(Iterators.filter(EcoreUtil.getAllProperContents(resource, true), DRepresentation.class));
    }

    private void markContainerResourceAsModified(final EObject obj) {
        EObject resourceContainer = getRootContainer(obj);
        if (obj != null && resourceContainer != null && resourceContainer.eResource() != null) {
            resourceContainer.eResource().setModified(true);
        }
    }

    private void saveSession() {
        final IEditingSession ui = SessionUIManager.INSTANCE.getUISession(session);
        if (ui != null) {
            ui.close();
        }
        session.save(new NullProgressMonitor());
        if (ui != null) {
            ui.open();
        }
    }

    /**
     * Delete the physical workspace file underlying a resource, if any and
     * unload and remove this resource from its resourceSet.
     * 
     * @param res
     *            the resource to delete
     */
    protected void deleteResource(final Resource res) {
        if (res != null) {
            final IFile file = WorkspaceSynchronizer.getFile(res);
            try {
                res.save(Collections.emptyMap());
                res.delete(Collections.emptyMap());

                // Delete physical file (if any)
                if (file != null) {
                    file.delete(true, true, new NullProgressMonitor());
                }
            } catch (IOException e) {
                SiriusEditPlugin.getPlugin().log(e);
            } catch (final CoreException e) {
                SiriusEditPlugin.getPlugin().log(e);
            }
        }
    }

    private void notifySessionAboutUncontrolledResource(final Resource controlledSemanticResource) {
        if (session instanceof DAnalysisSession) {
            ((DAnalysisSession) session).notifyUnControlledModel(semanticElementToUncontrol, controlledSemanticResource);
        }
    }
}
