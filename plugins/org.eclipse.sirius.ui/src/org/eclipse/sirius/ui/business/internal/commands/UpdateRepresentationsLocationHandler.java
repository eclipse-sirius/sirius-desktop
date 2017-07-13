/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.ui.business.internal.commands;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl.FactoryImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.internal.representation.DRepresentationLocationManager;
import org.eclipse.sirius.common.tools.api.util.WorkspaceUtil;
import org.eclipse.sirius.ui.tools.internal.actions.repair.RepresentationFilesNeedCloseSessionValidator;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Lists;

/**
 * The class will handle the Update representation location command from a project, a folder or an aird context menu.
 * 
 * @author fbarbin
 *
 */
public class UpdateRepresentationsLocationHandler extends AbstractHandler {

    private static final String ERROR_MSG = MessageFormat.format(Messages.UpdateRepresentationsLocationHandler_errorMsg, Messages.UpdateRepresentationsLocationHandler_label);

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            Collection<IFile> files = getAirdFiles(((IStructuredSelection) selection).toList());
            files.stream().forEach(file -> handleIFile(file));
        }
        return null;

    }

    private void handleIFile(IFile iFile) {
        RepresentationFilesNeedCloseSessionValidator validator = new RepresentationFilesNeedCloseSessionValidator(Messages.UpdateRepresentationsLocationHandler_label);
        if (validator.validate(iFile).isOK()) {
            UpdateRepresentationLocationOperation operation = new UpdateRepresentationLocationOperation(iFile);
            try {
                PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(ERROR_MSG, e);
            }
        }
    }

    /**
     * Gets all aird files found in the given resource list (Folder, Project, File etc).
     * 
     * @param selection
     *            the selected element.
     * @return the collection of IFile with aird extension found
     */
    public static Collection<IFile> getAirdFiles(List<?> selection) {
        Collection<IFile> files = Lists.newArrayList();
        selection.iterator().forEachRemaining(el -> {
            if (el instanceof IContainer) {
                files.addAll(WorkspaceUtil.getFilesFromWorkspace(Collections.singleton((IContainer) el), SiriusUtil.SESSION_RESOURCE_EXTENSION));
            } else if (el instanceof IFile) {
                files.add((IFile) el);
            }
        });
        return files;
    }

    /**
     * A WorkspaceModifyOperation to execute the update.
     * 
     * @author fbarbin
     *
     */
    private class UpdateRepresentationLocationOperation extends WorkspaceModifyOperation {
        IFile file;

        UpdateRepresentationLocationOperation(IFile file) {
            this.file = file;
        }

        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
            monitor.beginTask(Messages.UpdateRepresentationsLocationHandler_label, IProgressMonitor.UNKNOWN);

            URI uri = URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
            ResourceSet resourceSet = new ResourceSetImpl();
            TransactionalEditingDomain domain = FactoryImpl.INSTANCE.createEditingDomain(resourceSet);
            Resource airdResource = resourceSet.getResource(uri, true);

            // We execute changes in a EMF Transaction to keep the consistency of the aird in case of error.
            domain.getCommandStack().execute(new RecordingCommand(domain) {

                @Override
                protected void doExecute() {
                    Stream<DRepresentationDescriptor> descriptors = airdResource.getContents().stream().filter(DAnalysis.class::isInstance).map(DAnalysis.class::cast)
                            .flatMap(d -> d.getOwnedViews().stream()).flatMap(v -> v.getOwnedRepresentationDescriptors().stream());
                    descriptors.forEach(repDesc -> {
                        DRepresentation representation = repDesc.getRepresentation();
                        DRepresentationLocationManager representationLocationManager;
                        representationLocationManager = new DRepresentationLocationManager();
                        Resource resourceforRepresentation = representationLocationManager.getOrCreateRepresentationResource(representation, airdResource);
                        resourceSet.getResources().add(resourceforRepresentation);
                        resourceforRepresentation.getContents().add(representation);
                        repDesc.updateRepresentation(representation);
                    });
                    try {
                        for (Resource resource : resourceSet.getResources()) {
                            ResourceQuery query = new ResourceQuery(resource);
                            if (query.isAirdOrSrmResource()) {
                                if (resource.getContents().isEmpty()) {
                                    resource.delete(Collections.emptyMap());
                                } else {
                                    resource.save(Collections.emptyMap());
                                }
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(ERROR_MSG, e);
                    }

                }
            });
        }
    }
}
