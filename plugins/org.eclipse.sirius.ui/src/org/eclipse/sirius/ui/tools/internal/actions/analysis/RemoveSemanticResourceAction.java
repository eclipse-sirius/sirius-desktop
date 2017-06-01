/*******************************************************************************
 * Copyright (c) 2009, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.analysis;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Action to remove semantic resources from a session.
 *
 * @author ymortier
 */
public class RemoveSemanticResourceAction extends Action {

    private static final String DELIMITER = ", "; //$NON-NLS-1$

    /**
     * Enum use to categorize errors that can happen when launching this action.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private enum ErrorKind {

        /**
         * A semantic resource loaded in session and controlled cannot be remove.
         */
        CONTROLLED_RESOURCE(Messages.RemoveSemanticResourceAction_Controlled_Resources_Message),
        /**
         * A semantic resource loaded in a session and belonging to the same project that has modeling nature cannot be
         * removed.
         */
        NOT_EXTERNAL_DEPENDENCY_FOR_MODELING_PROJECT(Messages.RemoveSemanticResourceAction_Not_External_Dependency_Message),
        /**
         * A semantic resource with one of its element containing a representation instance active cannot be remove.
         */
        ACTIVE_REPRESENTATION(Messages.RemoveSemanticResourceAction_Active_Representation_Message);

        private String message;

        ErrorKind(String theMessage) {
            this.message = theMessage;
        }

        public String getMessage() {
            return message;
        }

    }

    /** The session to change. */
    private Session session;

    /** The resources to remove. */
    private Collection<Resource> toRemove;

    /**
     * Creates a new {@link RemoveSemanticResourceAction}.
     *
     * @param selection
     *            the resources to remove.
     * @param session
     *            the session to change.
     */
    public RemoveSemanticResourceAction(final Collection<Resource> selection, final Session session) {
        super(Messages.RemoveSemanticResourceAction_name);
        this.session = session;
        this.toRemove = new ArrayList<Resource>(selection);
    }

    @Override
    public boolean isEnabled() {
        boolean res = super.isEnabled();
        res = res && this.session != null;
        res = res && !this.toRemove.isEmpty();
        return res;
    }

    /**
     * Returns true if the given element is an external dependency of the session.
     * 
     * @param resource
     *            the element from which we want to know if it is an external dependency.
     * @param sourceProject
     * @return true if the given element is an external dependency of the session. False otherwise.
     */
    private boolean isExternalDependency(Resource resource, IProject sourceProject) {
        String platformString = resource.getURI().toPlatformString(true);
        if (platformString != null) {
            IProject resourceProject = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString)).getProject();
            return !sourceProject.equals(resourceProject);
        }
        return true;
    }

    @Override
    public void run() {
        Map<ErrorKind, Set<Resource>> errorKindToResource = checkAllResourcesAreRemovable();

        if (errorKindToResource.isEmpty()) {
            CompoundCommand compoundCommand = new CompoundCommand();
            for (Resource semanticResourceToRemove : toRemove) {
                compoundCommand.append(new RemoveSemanticResourceCommand(session, semanticResourceToRemove, new NullProgressMonitor(), true));
            }
            final TransactionalEditingDomain transDomain = session.getTransactionalEditingDomain();
            transDomain.getCommandStack().execute(compoundCommand);
        } else {
            Set<Entry<ErrorKind, Set<Resource>>> errorKindToResourceEntrySet = errorKindToResource.entrySet();
            String errorMessage = ""; //$NON-NLS-1$
            for (Entry<ErrorKind, Set<Resource>> errorKindToResourceEntry : errorKindToResourceEntrySet) {
                switch (errorKindToResourceEntry.getKey()) {
                case NOT_EXTERNAL_DEPENDENCY_FOR_MODELING_PROJECT:
                    errorMessage = getErrorString(errorMessage, ErrorKind.NOT_EXTERNAL_DEPENDENCY_FOR_MODELING_PROJECT.getMessage(), errorKindToResourceEntry);
                    break;
                case ACTIVE_REPRESENTATION:
                    errorMessage = getErrorString(errorMessage, ErrorKind.ACTIVE_REPRESENTATION.getMessage(), errorKindToResourceEntry);
                    break;
                case CONTROLLED_RESOURCE:
                    errorMessage = getErrorString(errorMessage, ErrorKind.CONTROLLED_RESOURCE.getMessage(), errorKindToResourceEntry);
                    break;
                default:
                    break;
                }
            }
            final String finalErrorMessage = errorMessage;
            Display.getDefault().asyncExec(() -> {
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.RemoveSemanticResourceAction_Error_Removal_Title, finalErrorMessage);
            });
        }
    }

    /**
     * Returns the error message corresponding to the given elements concatenated to the current one.
     * 
     * @param errorMessage
     *            the current error message to be concatenated with new messages.
     * @param sourceMessage
     *            the source error message not formated with {@link MessageFormat}.
     * @param errorKindToResourceEntry
     *            the values to use when formating the source message.
     * @return the error message corresponding to the given elements concatenated to the current one.
     */
    private String getErrorString(String errorMessage, String sourceMessage, Entry<ErrorKind, Set<Resource>> errorKindToResourceEntry) {
        String resultMessage = errorMessage;
        if (!resultMessage.isEmpty()) {
            resultMessage += "\n\n"; //$NON-NLS-1$
        }
        resultMessage += MessageFormat.format(sourceMessage, errorKindToResourceEntry.getValue().stream().map(r -> r.getURI().lastSegment()).collect(Collectors.joining(DELIMITER)));
        return resultMessage;
    }

    /**
     * Checks that all selection resources are removable. If not, all resources not removable are put in a map that is
     * returned.
     * 
     * @return a map with all resources not removable.
     */
    private Map<ErrorKind, Set<Resource>> checkAllResourcesAreRemovable() {
        Map<ErrorKind, Set<Resource>> errorKindToResource = new HashMap<>();
        Set<Resource> repDescriptorEResources = DialectManager.INSTANCE.getAllRepresentationDescriptors(session).stream().map(DRepresentationDescriptor::getTarget).map(EObject::eResource)
                .collect(Collectors.toSet());

        for (Resource semanticResourceToRemove : toRemove) {
            IFile airdFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(session.getSessionResource().getURI().toPlatformString(true)));
            if (airdFile != null) {
                IProject project = airdFile.getProject();
                boolean executeRemoval = !ModelingProject.hasModelingProjectNature(project) || (semanticResourceToRemove instanceof AirdResource);
                executeRemoval = executeRemoval || isExternalDependency(semanticResourceToRemove, airdFile.getProject());
                if (!executeRemoval) {
                    Set<Resource> notExternalResource = errorKindToResource.get(ErrorKind.NOT_EXTERNAL_DEPENDENCY_FOR_MODELING_PROJECT);
                    if (notExternalResource == null) {
                        notExternalResource = new HashSet<>();
                        errorKindToResource.put(ErrorKind.NOT_EXTERNAL_DEPENDENCY_FOR_MODELING_PROJECT, notExternalResource);
                    }
                    notExternalResource.add(semanticResourceToRemove);
                }
            }
            if (!(semanticResourceToRemove instanceof AirdResource) && ((DAnalysisSessionEObject) session).getControlledResources().contains(semanticResourceToRemove)) {
                Set<Resource> controlledResources = errorKindToResource.get(ErrorKind.CONTROLLED_RESOURCE);
                if (controlledResources == null) {
                    controlledResources = new HashSet<>();
                    errorKindToResource.put(ErrorKind.CONTROLLED_RESOURCE, controlledResources);
                }
                controlledResources.add(semanticResourceToRemove);
            }
            if (!(semanticResourceToRemove instanceof AirdResource) && repDescriptorEResources.contains(semanticResourceToRemove)) {
                Set<Resource> resourcesWithActiveRepresentations = errorKindToResource.get(ErrorKind.ACTIVE_REPRESENTATION);
                if (resourcesWithActiveRepresentations == null) {
                    resourcesWithActiveRepresentations = new HashSet<>();
                    errorKindToResource.put(ErrorKind.ACTIVE_REPRESENTATION, resourcesWithActiveRepresentations);
                }
                resourcesWithActiveRepresentations.add(semanticResourceToRemove);
            }
        }
        return errorKindToResource;
    }

}
