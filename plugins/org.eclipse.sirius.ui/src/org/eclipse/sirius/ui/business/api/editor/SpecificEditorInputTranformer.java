/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.editor;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.SessionSpecificEditorInput;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import com.google.common.collect.Sets;

/**
 * Transform a standard editor input into a session, by creating silently a
 * session.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class SpecificEditorInputTranformer {

    private static final String SESSION_FILE_EXTENSION = "." + SiriusUtil.SESSION_RESOURCE_EXTENSION; //$NON-NLS-1$

    private static final String DEFAULT_FILE_NAME = "file"; //$NON-NLS-1$

    /**
     * The specific session manager.
     */
    protected SpecificSessionManager sessionManager;

    /**
     * Session of this SpecificEditorInputTranformer.
     */
    protected Session session;

    /** The temporary file created if file should not be saved in the workspace. */
    private File tempFile;

    /** The representation description used to create the diagram. */
    private RepresentationDescription description;

    private URI mySiriusURI;

    private String myRepresentationDescriptionName;

    /**
     * The viewpoint containing the diagram description used to create the
     * diagram.
     */
    private Viewpoint viewpoint;

    /**
     * Init environment.
     * 
     * @param viewpointURI
     *            the viewpoint URI
     * @param representationDescriptionName
     *            the representation description name
     */
    public void init(URI viewpointURI, String representationDescriptionName) {
        this.mySiriusURI = viewpointURI;
        this.myRepresentationDescriptionName = representationDescriptionName;
        viewpoint = getSirius(viewpointURI);
        description = getRepresentationDescription(representationDescriptionName);
    }

    private Viewpoint getSirius(URI viewpointURI) {
        return ViewpointRegistry.getInstance().getViewpoint(viewpointURI);
    }

    private RepresentationDescription getRepresentationDescription(final String wantedName) {
        for (final RepresentationDescription representationDescription : viewpoint.getOwnedRepresentations()) {
            if (wantedName.equals(representationDescription.getName())) {
                return representationDescription;
            }
        }
        throw new RuntimeException();
    }

    /**
     * Clean the environment. Should be called when you dispose your editor.
     */
    public void cleanEnvironment() {
        if (session != null) {
            session.close(new NullProgressMonitor());
        }
        if (tempFile != null) {
            tempFile.delete();
            tempFile = null;
        }
    }

    /**
     * Transform a standard input into a session one. Session will be
     * automatically created.
     * 
     * @param input
     *            the default input
     * @param selection
     *            the current selected element in the workbench
     * @param storeInWorkspace
     *            should the session file be stored in the workbench
     * @return the transformed editor input
     */
    public IEditorInput transformInput(IEditorInput input, ISelection selection, boolean storeInWorkspace) {
        if (input instanceof URIEditorInput) {
            // do nothing now => works only with workspace file
        } else if (input instanceof IFileEditorInput) {

            if (session != null) {
                cleanEnvironment();
            }

            final IPath semanticModelPath = ((IFileEditorInput) input).getFile().getFullPath();

            try {
                String analysisFilenameURI = getAnalysisURI(selection, storeInWorkspace).toString();
                final DRepresentation representation = createSessionAndRepresentation(semanticModelPath, analysisFilenameURI);
                return createNewEditorInput(representation, semanticModelPath);
            } catch (final IOException exception) {
                SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, "Failing of EditorInput transformation.", exception));
            } catch (CoreException exception) {
                SiriusEditPlugin.getPlugin().getLog().log(exception.getStatus());
            }
        }
        return input;
    }

    /**
     * Create a session and a representation (only if not already created by
     * initialization of Sirius) from a semantic model path and the analysis
     * URI.
     * 
     * @param semanticModelPath
     *            model path
     * @param analysisFilenameURI
     *            analysis filename URI
     * @return the created representation
     * @throws IOException
     *             if the semantic model path could not be loaded
     * @throws CoreException
     *             In case of session resource creation failed
     * @since 0.9.0
     */
    public DRepresentation createSessionAndRepresentation(final IPath semanticModelPath, final String analysisFilenameURI) throws IOException, CoreException {
        final URI sessionModelURI = URI.createURI(analysisFilenameURI);

        final IFile semanticModelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(semanticModelPath);
        final URI semanticModelURI = URI.createPlatformResourceURI(semanticModelFile.getFullPath().toOSString(), true);

        final SessionCreationOperation coreOp = new DefaultLocalSessionCreationOperation(sessionModelURI, new NullProgressMonitor());
        coreOp.execute();
        session = coreOp.getCreatedSession();
        AddSemanticResourceCommand addSemanticResourceCommand = new AddSemanticResourceCommand(session, semanticModelURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCommand);

        final EObject semanticModel = session.getSemanticResources().iterator().next().getContents().get(0);
        activateSirius();
        return createDRepresentation(semanticModel, "new representation");
    }

    private DRepresentation createDRepresentation(final EObject semanticModel, final String name) {
        final RepresentationDescription descriptionToUse = getRepresentationDescriptionInEditingDomain(description, semanticModel);
        Option<DRepresentation> optionalRepresentation = findRepresentation(descriptionToUse, semanticModel);
        if (optionalRepresentation.some()) {
            return optionalRepresentation.get();
        } else {
            CreateRepresentationCommand command = new CreateRepresentationCommand(session, descriptionToUse, semanticModel, name, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
            return command.getCreatedRepresentation();
        }
    }

    /**
     * Find the representation in the current session which has
     * <code>representationDescriptionUsed</code> as description and
     * <code>semanticElement</code> as root.
     * 
     * @param representationDescriptionUsed
     *            the search representation description
     * @param semanticElement
     *            the semantic root of the searched representation
     * @return an optional DRepresentation
     */
    private Option<DRepresentation> findRepresentation(RepresentationDescription representationDescriptionUsed, EObject semanticElement) {
        if (session != null && representationDescriptionUsed != null && semanticElement != null) {
            for (final DView view : session.getOwnedViews()) {
                for (final DRepresentation repr : view.getOwnedRepresentations()) {
                    RepresentationDescription reprDesc = DialectManager.INSTANCE.getDescription(repr);
                    if (repr instanceof DSemanticDecorator && representationDescriptionUsed.equals(reprDesc) && semanticElement.equals(((DSemanticDecorator) repr).getTarget())) {
                        return Options.newSome(repr);
                    }
                }
            }
        }
        return Options.newNone();
    }

    /**
     * Get the representation description instance in the session editing domain
     * from another one.
     * 
     * @param representationDescription
     *            the original representation description
     * @param semanticModel
     *            on which the representation will be created from the
     *            representation description
     * @return the representation description if found, <code>null</code>
     *         otherwise
     */
    private RepresentationDescription getRepresentationDescriptionInEditingDomain(final RepresentationDescription representationDescription, final EObject semanticModel) {
        Collection<RepresentationDescription> representationDescriptions = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), semanticModel);
        for (final RepresentationDescription representationDescriptionInEditingDomain : representationDescriptions) {
            if (representationDescriptionInEditingDomain.getName().equals(representationDescription.getName())) {
                return representationDescriptionInEditingDomain;
            }
        }
        return null;
    }

    private IEditorInput createNewEditorInput(final DRepresentation representation, final IPath semanticModelPath) {
        final String editorName = DialectUIManager.INSTANCE.getEditorName(representation);
        final URI uri = getNewEditorInputURI(representation);
        SessionSpecificEditorInput input = new SessionSpecificEditorInput(uri, editorName, session);
        input.init(semanticModelPath, mySiriusURI, myRepresentationDescriptionName);
        return input;
    }

    /**
     * Get the editor input URI from the created representation. additional
     * operation, such as model elements creation could be done in this method.
     * 
     * @param representation
     *            the created representation
     * @return the uri to use
     */
    protected URI getNewEditorInputURI(final DRepresentation representation) {
        return EcoreUtil.getURI(representation);
    }

    /**
     * Activate the viewpoint.
     * */
    private void activateSirius() {
        final ViewpointSelectionCallback selectionCallback = new ViewpointSelectionCallback();
        session.getTransactionalEditingDomain().getCommandStack()
                .execute(new ChangeViewpointSelectionCommand(session, selectionCallback, Sets.newHashSet(viewpoint), Sets.<Viewpoint> newHashSet(), new NullProgressMonitor()) {

                    @Override
                    public boolean canUndo() {
                        /* this activation is not undoable */
                        return false;
                    }
                });
    }

    /**
     * Get the analysis file URI.
     * 
     * @param storeInWorkspace
     *            should session file stored in workspace or outside
     * @param selection
     *            the current selected element in the workbench
     * @return the URI
     * @throws IOException
     *             if the file could not be created
     */
    protected URI getAnalysisURI(ISelection selection, boolean storeInWorkspace) throws IOException {
        if (storeInWorkspace) {
            return getWorkspaceAnalysisUri(selection);
        } else {
            return getInMemoryURI(selection);
        }
    }

    /**
     * Get an analysis file URI located in the workspace.
     * 
     * @param selection
     *            the current selection
     * @return Workspace analysis file URI
     */
    protected URI getWorkspaceAnalysisUri(ISelection selection) {
        final IResource selectedResource = getSelectedResource(selection);
        if (selectedResource instanceof IFile) {
            final IPath path = getWorkspacePath(selectedResource);
            if (path != null) {
                final String fileName = ((IFile) selectedResource).getName() + SESSION_FILE_EXTENSION;
                return URI.createPlatformResourceURI(path.append(fileName).toString(), true);
            }
        }
        return URI.createURI(DEFAULT_FILE_NAME + SESSION_FILE_EXTENSION);
    }

    /**
     * Get an analysis temporary file URI.
     * 
     * @return the URI
     * @throws IOException
     *             if a temporary file could not be created
     */
    protected URI getTempAnalysisURI() throws IOException {
        File temp = File.createTempFile(DEFAULT_FILE_NAME, SESSION_FILE_EXTENSION);
        temp.deleteOnExit();
        return URI.createFileURI(temp.getAbsolutePath());
    }

    /**
     * Get an analysis in memory URI.
     * 
     * @param selection
     *            the workbench current selection
     * @return the URI
     */
    protected URI getInMemoryURI(ISelection selection) {
        final IResource selectedResource = getSelectedResource(selection);
        if (selectedResource instanceof IFile) {
            final String sessionResourcePath = selectedResource.getFullPath().removeFileExtension().addFileExtension(SiriusUtil.SESSION_RESOURCE_EXTENSION).makeRelative().toString();
            return URI.createGenericURI(URIQuery.INMEMORY_URI_SCHEME, sessionResourcePath, null);
        }
        return URI.createGenericURI(URIQuery.INMEMORY_URI_SCHEME, DEFAULT_FILE_NAME + SESSION_FILE_EXTENSION, null);
    }

    /**
     * Get the path relative to workspace of a resource.
     * 
     * @param resource
     *            the resource
     * @return the relative workspace path of the given resource
     */
    private IPath getWorkspacePath(final IResource resource) {
        IPath path = null;
        if (resource instanceof IContainer) {
            path = ((IContainer) resource).getFullPath();
        } else if (resource instanceof IFile) {
            path = ((IFile) resource).getParent().getFullPath();
        }
        return path;
    }

    /**
     * Get the current selected resource from selection.
     * 
     * @param selection
     *            the current selection
     * @return the selected resource if there is one, or <code>null</code>
     *         otherwise
     */
    private IResource getSelectedResource(ISelection selection) {
        IResource resource = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection ss = (IStructuredSelection) selection;
            Object element = ss.getFirstElement();
            if (element instanceof IResource) {
                resource = (IResource) element;
            } else if (element instanceof IAdaptable) {
                IAdaptable adaptable = (IAdaptable) element;
                Object adapter = adaptable.getAdapter(IResource.class);
                resource = (IResource) adapter;
            }
        }
        return resource;
    }
}
