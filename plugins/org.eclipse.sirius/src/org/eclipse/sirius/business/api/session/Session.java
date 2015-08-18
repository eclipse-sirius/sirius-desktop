/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.session;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A session is initiated by a user, provides the model data and means to store
 * the representation data.
 * 
 * @author cbrun
 */
public interface Session {
    /**
     * ID for dangling, broken or invalid sessions.
     */
    String INVALID_SESSION = "INVALID SESSION"; //$NON-NLS-1$

    /**
     * Open the session and add it to the {@link SessionManager}. Initialize a
     * Session from a Session Resource URI with its own EditingDomain. This
     * operation must be called after get a Session from
     * SessionManager.getSession(URI) or SessionFactory.createSession(URI).
     * 
     * 
     * @param monitor
     *            {@link IProgressMonitor} used to indicate progress of the
     *            Session opening
     * @since 0.9.0
     */
    void open(IProgressMonitor monitor);

    /**
     * Returns <code>true</code> if the session is opened.
     * 
     * @return <code>true</code> if the session is opened.
     */
    boolean isOpen();

    /**
     * Get the {@link TransactionalEditingDomain} associated to this session.
     * 
     * @return the {@link TransactionalEditingDomain} associated to this session
     * 
     * @since 0.9.0
     */
    TransactionalEditingDomain getTransactionalEditingDomain();

    /**
     * Get the {@link ModelAccessor} associated to this session.
     * 
     * @return the {@link ModelAccessor} associated to this session
     */
    ModelAccessor getModelAccessor();

    /**
     * Should return an identifier for the session.
     * 
     * @return the session ID.
     */
    String getID();

    /**
     * Get the main {@link Resource} associated to this {@link Session}.
     * 
     * @return the main {@link Resource} associated to this {@link Session}
     * 
     * @since 0.9.0
     */
    Resource getSessionResource();

    /**
     * Get the referenced session {@link Resource} referenced directly or
     * indirectly by the main {@link Resource} session.
     * 
     * @return the referenced session {@link Resource} referenced directly or
     *         indirectly by the main {@link Resource} session
     * 
     * @since 0.9.0
     */
    Set<Resource> getReferencedSessionResources();

    /**
     * Return all the session resources in this session, including the main and
     * referenced (directly and indirectly) resources.
     * 
     * @return all the session resources in this session.
     * 
     * @since 0.9.0
     */
    Set<Resource> getAllSessionResources();

    /**
     * Add a new semantic resource in the session. Must be called in a
     * {@link org.eclipse.emf.transaction.Transaction}, use
     * {@link org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand}
     * to do it.
     * 
     * @param semanticResourceURI
     *            {@link URI} of a existing {@link Resource} representing a
     *            semantic model to attach to this {@link Session}
     * @param monitor
     *            the {@link IProgressMonitor} to associate to this operation
     */
    void addSemanticResource(final URI semanticResourceURI, IProgressMonitor monitor);

    /**
     * return the semantic resources associated with the session. NOTE : this
     * method doesn't return controlled resources.
     * 
     * @return the semantic resources associated with the session.
     */
    Collection<Resource> getSemanticResources();

    /**
     * Remove the specified semantic resource.
     * 
     * @param semanticResource
     *            the specified semantic resource to remove
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of semantic
     *            resource removal
     * @param removeReferencingResources
     *            indicates if the referencing resources are also to remove
     */
    void removeSemanticResource(final Resource semanticResource, IProgressMonitor monitor, boolean removeReferencingResources);

    /**
     * Save the session data.
     * 
     * @param monitor
     *            the Progress monitor to associate to this operation
     * @since 0.9.0
     */
    void save(IProgressMonitor monitor);

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
     * @param monitor
     *            the Progress monitor to associate to this operation
     * @since 0.9.0
     */
    void save(Map<?, ?> options, IProgressMonitor monitor);

    /**
     * Close the session, remove it from the {@link SessionManager}, dispose all
     * Session's resources and dispose the EditingDomain.
     * 
     * @param monitor
     *            {@link IProgressMonitor} to indicate the progress of the
     *            Session closing
     * 
     * @since 0.9.0
     */
    void close(IProgressMonitor monitor);

    /**
     * Get current viewpoint selection.
     * 
     * @param includeReferencedAnalysis
     *            if true, we walk through all DAnalysis to get selected
     *            {@link DView}, otherwise we consider only the main DAnalysis
     *            that of {@link Session#getSessionResource()}, specify false if
     *            we are not sure because now the Viewpoints selection is stored
     *            on the main DAnalysis
     * @return current viewpoint selection
     */
    Collection<Viewpoint> getSelectedViewpoints(boolean includeReferencedAnalysis);

    /**
     * Creates a view with the given viewpoint.
     * 
     * @param viewpoint
     *            the viewpoint.
     * @param semantics
     *            collection of semantic model root element
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of view
     *            creation
     * @since 0.9.0
     */
    void createView(Viewpoint viewpoint, Collection<EObject> semantics, IProgressMonitor monitor);

    /**
     * Creates a view with the given viewpoint specifying if we want create new
     * DRepresentations.
     * 
     * @param viewpoint
     *            the viewpoint.
     * @param semantics
     *            collection of semantic model root element
     * @param createNewRepresentations
     *            true to create new DRepresentation for
     *            RepresentationDescription having their initialization
     *            attribute at true for selected {@link Viewpoint}s.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of view
     *            creation
     */
    void createView(Viewpoint viewpoint, Collection<EObject> semantics, boolean createNewRepresentations, IProgressMonitor monitor);

    /**
     * Adds a selected view to this session.
     * 
     * @param view
     *            the view to select.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of view
     *            selection
     * @throws IllegalArgumentException
     *             if the view cannot be added to the selected views.
     */
    void addSelectedView(final DView view, IProgressMonitor monitor) throws IllegalArgumentException;

    /**
     * Removes the given view from the selected views. if the given view is not
     * selected the invocation has no effect.
     * 
     * @param view
     *            the view to unselect.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of
     *            {@link DView} unselection
     */
    void removeSelectedView(final DView view, IProgressMonitor monitor);

    /**
     * Returns all selected views. The returned collection is unmodifiable.
     * 
     * @return all selected views.
     */
    Collection<DView> getSelectedViews();

    /**
     * Returns all owned views. The returned collection is unmodifiable.
     * 
     * @return all selected views.
     */
    Collection<DView> getOwnedViews();

    /**
     * Add a new listener for the session manager.
     * 
     * @param listener
     *            new listener to add.
     */
    void addListener(SessionListener listener);

    /**
     * Remove the given listener.
     * 
     * @param listener
     *            listener to remove.
     */
    void removeListener(SessionListener listener);

    /**
     * Returns the interpreter to use with this session.
     * 
     * @return the interpreter to use with this session.
     */
    IInterpreter getInterpreter();

    /**
     * This methods create the cross referencer on demand.
     * 
     * @return a cross referencer adapter for the session semantic models.
     */
    ECrossReferenceAdapter getSemanticCrossReferencer();

    /**
     * return the services associated with the session.
     * 
     * @return the services associated with the session.
     */
    SessionService getServices();

    /**
     * Return the current session status.
     * 
     * @return the value of the current status.
     * @since 0.9.0
     */
    SessionStatus getStatus();

    /**
     * Set the reloading policy to use for the session.
     * 
     * @param reloadingPolicy
     *            the custom reloading policy the session should use.
     * @since 0.9.0
     */
    void setReloadingPolicy(ReloadingPolicy reloadingPolicy);

    /**
     * Get the reloading policy used by the session.
     * 
     * @return the reloading policy used by the session.
     * @since 0.9.0
     */
    ReloadingPolicy getReloadingPolicy();

    /**
     * Set the saving policy to use for the session.
     * 
     * @param savingPolicy
     *            the custom saving policy the session should use.
     * @since 0.9.0
     */
    void setSavingPolicy(SavingPolicy savingPolicy);

    /**
     * Returns the custom saving policy the session should use; if no
     * SavingPolicy has been defined, creates a default one.<br/>
     * Subclasses can override this method to define a new default Saving
     * Policy.
     * 
     * @return the saving policy the session is using.
     * @since 1.0.0M7
     */
    SavingPolicy getSavingPolicy();

    /**
     * Return the session event broker suitable for identifying local or remote
     * atomic changes.
     * 
     * @return the session event broker suitable for identifying local or remote
     *         atomic changes.
     * @since 0.9.0
     */
    SessionEventBroker getEventBroker();

    /**
     * Return the PrecommitListener suitable for refresh all opened Sirius
     * editors.
     * 
     * @return the PrecommitListener suitable for refresh all opened Sirius
     *         editors.
     * @since 0.9.0
     */
    RefreshEditorsPrecommitListener getRefreshEditorsListener();
}
