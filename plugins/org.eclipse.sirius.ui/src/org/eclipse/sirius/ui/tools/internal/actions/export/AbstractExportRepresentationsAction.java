/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.export;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.tools.api.actions.export.ExportAction;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.ui.tools.api.dialogs.AbstractExportRepresentationsAsImagesDialog;
import org.eclipse.sirius.ui.tools.api.dialogs.ExportOneRepresentationAsImageDialog;
import org.eclipse.sirius.ui.tools.api.dialogs.ExportSeveralRepresentationsAsImagesDialog;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Abstract action to export representations as images.
 * 
 * @author mporhel
 */
public abstract class AbstractExportRepresentationsAction extends Action {

    /**
     * Creates a new AbstractExportRepresentationsAction with the given text and image.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @see #setText
     * @see #setImageDescriptor
     */
    protected AbstractExportRepresentationsAction(String text, ImageDescriptor image) {
        super(text, image);
    }

    /**
     * Collect the diagrams to export, get the corresponding session, compute the export path and then show the path and
     * file format dialog to the user before exporting export the diagrams.
     */
    @Override
    public void run() {
        Collection<DRepresentationDescriptor> repDescriptorsToExport = getRepresentationToExport().stream().filter(Objects::nonNull).collect(Collectors.toList());

        // keep only the DRepresentationDescriptor which DRepresentation is not null
        repDescriptorsToExport = repDescriptorsToExport.stream().filter(repDesc -> repDesc.getRepresentation() != null).collect(Collectors.toList());

        if (!repDescriptorsToExport.isEmpty()) {
            DRepresentationDescriptor firstDRepDescriptorToExport = repDescriptorsToExport.iterator().next();
            // Make sure the representation is loaded
            firstDRepDescriptorToExport.getRepresentation();
            Session session = getSession(firstDRepDescriptorToExport);
            if (session != null) {
                IPath exportPath = getExportPath(firstDRepDescriptorToExport, session);
                if (exportPath != null) {
                    exportRepresentation(exportPath, repDescriptorsToExport, session);
                }
            }
        } else {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.ExportRepresentationsAction_noRepresentationsDialog_title,
                    Messages.ExportRepresentationsAction_noRepresentationsDialog_message);
        }
    }

    /**
     * Collect the representations to export.
     * 
     * @return the {@link DRepresentationDescriptor} corresponding to the representation to export.
     */
    protected abstract Collection<DRepresentationDescriptor> getRepresentationToExport();

    /**
     * Get the corresponding session.
     * 
     * @param repDescriptor
     *            a representation descriptor.
     * @return the session.
     */
    protected abstract Session getSession(DRepresentationDescriptor repDescriptor);

    /**
     * Display the export path and file format dialog and then export the representations.
     * 
     * @param exportPath
     *            the default export path.
     * @param representationsToExport
     *            represents the representation to export.
     * @param session
     *            the corresponding session.
     */
    protected void exportRepresentation(IPath exportPath, Iterable<DRepresentationDescriptor> representationsToExport, Session session) {
        final Shell shell = Display.getCurrent().getActiveShell();

        final AbstractExportRepresentationsAsImagesDialog dialog;
        if (Iterables.size(representationsToExport) > 1) {
            dialog = new ExportSeveralRepresentationsAsImagesDialog(shell, exportPath);
        } else {
            dialog = new ExportOneRepresentationAsImageDialog(shell, exportPath, representationsToExport.iterator().next().getName());
        }

        if (dialog.open() == Window.OK) {
            Collection<DRepresentation> dRepresentations = new ArrayList<>();
            for (DRepresentationDescriptor dRepresentationDescriptor : representationsToExport) {
                dRepresentations.add(dRepresentationDescriptor.getRepresentation());
            }
            List<DRepresentation> toExport = Lists.<DRepresentation> newArrayList(dRepresentations);
            final ExportAction exportAction = new ExportAction(session, toExport, dialog.getOutputPath(), dialog.getImageFormat(), dialog.isExportToHtml(), dialog.isExportDecorations());
            exportAction.setDiagramScaleLevel(dialog.getDiagramScaleLevelInPercent());
            final ProgressMonitorDialog pmd = new ProgressMonitorDialog(shell);
            try {
                pmd.run(false, false, exportAction);
            } catch (final InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof OutOfMemoryError) {
                    MessageDialog.openError(shell, Messages.ExportAction_memAllocError, cause.getMessage());
                } else if (cause instanceof SizeTooLargeException) {
                    MessageDialog.openError(shell, Messages.ExportAction_exportImpossibleTitle, cause.getMessage());

                    // Add in the 'error log' the representations export failed
                    SiriusPlugin.getDefault().error(Messages.ExportAction_exportError, cause);
                } else {
                    MessageDialog.openError(shell, Messages.AbstractExportRepresentationsAction_error, cause.getMessage());
                }
            } catch (final InterruptedException e) {
                MessageDialog.openInformation(shell, Messages.AbstractExportRepresentationsAction_error, e.getMessage());
            } finally {
                pmd.close();
            }
        } else {
            dialog.close();
        }
    }

    /**
     * Compute the default export path.
     * 
     * @param repDescriptor
     *            the first selected representation descriptor.
     * @param session
     *            the corresponding session.
     * @return the export path.
     */
    protected IPath getExportPath(DRepresentationDescriptor repDescriptor, Session session) {
        IPath exportPath = null;
        DRepresentation representation = repDescriptor.getRepresentation();
        if (representation != null) {
            URI representationResourceURI = repDescriptor.getRepresentation().eResource().getURI();
            URIQuery uriQuery = new URIQuery(representationResourceURI);
            Option<IResource> iResourceOption = uriQuery.getCorrespondingResource();
            if (iResourceOption.some()) {
                exportPath = iResourceOption.get().getParent().getLocation();
            } else {
                exportPath = getExportPath(session);
            }
        }
        return exportPath;
    }

    private IPath getExportPath(Session session) {
        IPath exportPath;
        URIQuery uriQuery;
        Option<IResource> iResourceOption;
        exportPath = Platform.getLocation();
        URI mainSessionResourceURI = session.getSessionResource().getURI();
        uriQuery = new URIQuery(mainSessionResourceURI);
        iResourceOption = uriQuery.getCorrespondingResource();
        if (iResourceOption.some()) {
            exportPath = iResourceOption.get().getParent().getLocation();
        } else if (uriQuery.isInMemoryURI()) {
            String opaquePart = mainSessionResourceURI.opaquePart();
            if (!StringUtil.isEmpty(opaquePart)) {
                String[] segments = opaquePart.split(Pattern.quote("/")); //$NON-NLS-1$
                if (segments != null && segments.length > 0) {
                    String projectName = segments[0];
                    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                    if (project.exists()) {
                        exportPath = project.getLocation();
                    }
                }
            }
        }
        return exportPath;
    }
}
