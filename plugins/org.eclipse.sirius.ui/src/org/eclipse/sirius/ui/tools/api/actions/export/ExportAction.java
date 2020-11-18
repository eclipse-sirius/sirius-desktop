/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.api.actions.export;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.FileUtil;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * Generic export action.
 *
 * @author mchauvin
 */
public class ExportAction extends WorkspaceModifyOperation {

    /**
     * Export action name.
     */
    public static final String EXPORT_DIAGRAMS_AS_IMAGES_ACTION_TITLE = Messages.ExportAction_exportDiagramsAsImagesTitle;

    private static final String CR = "\n"; //$NON-NLS-1$

    private static final char POINT = '.';

    private Session session;

    private Collection<DRepresentation> dRepresentationsToExportAsImage;

    private IPath outputPath;

    private ImageFileFormat imageFormat;

    private boolean exportToHtml;

    /**
     * Indicates if the diagram graphical element decorations will be in the exported image.
     */
    private boolean exportDecorations;

    /**
     * Indicates if diagrams should be scaled on export.
     */
    private boolean autoScaleDiagram;

    /**
     * Indicates scale level when exporting diagrams as image (value from 0 to 100 by step of 10).
     */
    private Integer diagramScaleLevel;

    /**
     * Default constructor.
     *
     * @param session
     *            The {@link Session} which owns the {@link DRepresentation}s to export as image
     * @param dRepresentationsToExportAsImage
     *            the {@link DRepresentation}s to export as image
     * @param outputPath
     *            the folder in which store the images, result of the export
     * @param imageFormat
     *            the format of the image, result of the export
     * @param exportToHtml
     *            true if we must export {@link DRepresentation} to html instead of image
     * @param exportDecorations
     *            true if we want the image to contain diagram element decorations
     */
    public ExportAction(Session session, Collection<DRepresentation> dRepresentationsToExportAsImage, IPath outputPath, ImageFileFormat imageFormat, boolean exportToHtml, boolean exportDecorations) {
        this.session = session;
        this.dRepresentationsToExportAsImage = dRepresentationsToExportAsImage;
        this.outputPath = outputPath;
        this.imageFormat = imageFormat;
        this.exportToHtml = exportToHtml;
        this.exportDecorations = exportDecorations;
    }

    /**
     * Sets the auto-scaling mode.
     * 
     * @param autoScaleDiagram
     *            <code>true</code> if the exported diagram should be automatically scaled.
     * 
     * @deprecated Use setDiagramScaleLevel instead.
     */
    @Deprecated
    public void setAutoScaleDiagram(boolean autoScaleDiagram) {
        this.autoScaleDiagram = autoScaleDiagram;
    }

    /**
     * Set the diagram scale level.
     * 
     * @param theDiagramScaleLevel
     *            diagram scale level in percent
     */
    public void setDiagramScaleLevel(Integer theDiagramScaleLevel) {
        if (theDiagramScaleLevel == null || theDiagramScaleLevel < 0 || theDiagramScaleLevel > 100) {
            throw new IllegalArgumentException(Messages.ExportAction_invalidLevel);
        } else {
            this.diagramScaleLevel = theDiagramScaleLevel;
            this.autoScaleDiagram = diagramScaleLevel != 0;
        }
    }

    /**
     * Overridden to do the export work.
     *
     * {@inheritDoc}
     */
    @Override
    protected void execute(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            monitor.beginTask(ExportAction.EXPORT_DIAGRAMS_AS_IMAGES_ACTION_TITLE, dRepresentationsToExportAsImage.size());
            try {
                createImageFiles(monitor);
            } catch (final OutOfMemoryError | CoreException e) {
                throw new InvocationTargetException(e, e.getLocalizedMessage());
            }
        } finally {
            monitor.done();
            if (monitor.isCanceled()) {
                throw new InterruptedException(Messages.ExportAction_exportDiagramsAsImagesCancelled);
            }
        }
    }

    /**
     * Create the image files.
     *
     * @param monitor
     *            the progress monitor
     * @throws CoreException
     *             if one or several images creation failed. The Exception contains a Status with the causes of this
     *             Exception.
     */
    protected void createImageFiles(final IProgressMonitor monitor) throws CoreException {

        final List<IBeforeExport> beforeContributors = EclipseUtil.getExtensionPlugins(IBeforeExport.class, IExportRepresentationsAsImagesExtension.ID,
                IExportRepresentationsAsImagesExtension.CLASS_ATTRIBUTE);
        final List<IAroundExport> aroundContributors = EclipseUtil.getExtensionPlugins(IAroundExport.class, IExportRepresentationsAsImagesExtension.ID,
                IExportRepresentationsAsImagesExtension.CLASS_ATTRIBUTE);
        final List<IAfterExport> afterContributors = EclipseUtil.getExtensionPlugins(IAfterExport.class, IExportRepresentationsAsImagesExtension.ID,
                IExportRepresentationsAsImagesExtension.CLASS_ATTRIBUTE);

        ExportFormat.ScalingPolicy scalingPolicy = this.autoScaleDiagram ? ExportFormat.ScalingPolicy.AUTO_SCALING : ExportFormat.ScalingPolicy.NO_SCALING;
        ExportFormat exportFormat = new ExportFormat(exportToHtml ? ExportDocumentFormat.HTML : ExportDocumentFormat.NONE, imageFormat, scalingPolicy, this.diagramScaleLevel);
        final String imageFileExtension = exportFormat.getImageFormat().getName().toLowerCase();

        /*
         * Before action extensions
         */
        for (IBeforeExport iBeforeExport : beforeContributors) {
            iBeforeExport.beforeExportAction(dRepresentationsToExportAsImage, outputPath, imageFileExtension);
        }

        /*
         * Around extensions
         */
        if (!aroundContributors.isEmpty()) {
            for (IAroundExport iAroundExport : aroundContributors) {
                iAroundExport.aroundExportAction(dRepresentationsToExportAsImage, outputPath, imageFileExtension);
            }
        } else {
            // To know if error from image size to large
            List<DRepresentation> representationWithSizeTooLargeException = new ArrayList<DRepresentation>();
            List<DRepresentation> representationWithOtherException = new ArrayList<DRepresentation>();
            List<DRepresentation> representationWithImageFileCreationError = new ArrayList<DRepresentation>();
            try {
                for (final DRepresentation representation : dRepresentationsToExportAsImage) {
                    final IPath filePath;
                    // Check that the file name is informed
                    // Put extension to lowerCase.
                    String representationName = representation.getName();
                    if (outputPath.toFile().isDirectory()) {
                        filePath = getFilePath(outputPath, representationName, imageFileExtension);
                    } else {
                        if (outputPath.getFileExtension() != null) {
                            String imageFileExtensionLowerCase = outputPath.getFileExtension().toLowerCase();
                            filePath = outputPath.removeFileExtension().addFileExtension(imageFileExtensionLowerCase);
                        } else {
                            filePath = outputPath;
                        }

                    }

                    /*
                     * Before extensions
                     */
                    for (final IBeforeExport contributor : beforeContributors) {
                        contributor.beforeExportRepresentationAsImage(representation, filePath);
                    }
                    if (DialectUIManager.INSTANCE.canHandle(representation)) {
                        try {
                            monitor.subTask("Export as image: " + representationName); //$NON-NLS-1$
                            DialectUIManager.INSTANCE.export(representation, session, filePath, exportFormat, monitor, exportDecorations);
                            monitor.worked(1);
                        } catch (CoreException exception) {
                            if (exception instanceof SizeTooLargeException) {
                                representationWithSizeTooLargeException.add(representation);
                            }
                        } catch (WrappedException exception) {
                            if (exception.getCause() instanceof FileNotFoundException) {
                                representationWithImageFileCreationError.add(representation);
                            } else {
                                representationWithOtherException.add(representation);
                            }
                        }
                    }
                    /*
                     * After extensions
                     */
                    for (final IAfterExport contributor : afterContributors) {
                        contributor.afterExportRepresentationAsImage(representation, filePath);
                    }
                }

            } finally {
                if (!representationWithSizeTooLargeException.isEmpty() || !representationWithOtherException.isEmpty() || !representationWithImageFileCreationError.isEmpty()) {
                    handleErrors(representationWithSizeTooLargeException, representationWithOtherException, representationWithImageFileCreationError);
                }
            }
        }
        /*
         * After action extensions
         */
        for (IAfterExport iAfterExport : afterContributors) {
            iAfterExport.afterExportAction();
        }
    }

    private void handleErrors(List<DRepresentation> representationWithSizeTooLargeException, List<DRepresentation> representationWithOtherException,
            List<DRepresentation> representationWithImageFileCreationError) throws CoreException {

        // Construct message for dialog and error in error log.
        StringBuffer messageExceptionForDialog = new StringBuffer();
        messageExceptionForDialog.append(Messages.ExportAction_exportErrorReport);
        if (!representationWithSizeTooLargeException.isEmpty()) {
            messageExceptionForDialog.append(CR);
            messageExceptionForDialog.append(Messages.ExportAction_imagesTooLargeMessage);
        }
        for (DRepresentation rep : representationWithSizeTooLargeException) {
            addRepresentationInformation(messageExceptionForDialog, rep);
        }

        if (!representationWithOtherException.isEmpty()) {
            messageExceptionForDialog.append(CR);
            messageExceptionForDialog.append(Messages.ExportAction_exportOtherError);
        }
        for (DRepresentation rep : representationWithOtherException) {
            addRepresentationInformation(messageExceptionForDialog, rep);
        }

        if (!representationWithImageFileCreationError.isEmpty()) {
            messageExceptionForDialog.append(CR);
            messageExceptionForDialog.append(Messages.ExportAction_exportedDiagramImageCreationError);
        }
        for (DRepresentation rep : representationWithImageFileCreationError) {
            addRepresentationInformation(messageExceptionForDialog, rep);
        }
        Status status = new Status(IStatus.ERROR, SiriusPlugin.ID, messageExceptionForDialog.toString());
        SiriusEditPlugin.getPlugin().getLog().log(status);
        throw new CoreException(status);
    }

    private void addRepresentationInformation(StringBuffer messageExceptionForDialog, DRepresentation rep) {
        messageExceptionForDialog.append(CR);
        messageExceptionForDialog.append(" - "); //$NON-NLS-1$
        Object description = ReflectionHelper.invokeMethodWithoutExceptionWithReturn(rep, "getDescription", new Class[] {}, new Object[] {}); //$NON-NLS-1$
        String descriptionName = null;
        if (description instanceof RepresentationDescription) {
            descriptionName = ((RepresentationDescription) description).getName();
        }
        String semanticTargetName = null;
        if (rep instanceof DSemanticDecorator) {
            semanticTargetName = new EditingDomainServices().getLabelProviderText(((DSemanticDecorator) rep).getTarget());
        }
        messageExceptionForDialog.append(MessageFormat.format(Messages.ExportAction_representationInformation, rep.getName(), descriptionName, semanticTargetName));
    }

    /**
     * Get the {@link IPath} of the file to write. This method try first to use the filename given. If the file already
     * exists, it appends a number to its name.
     *
     * @param destinationFolder
     *            the folder where to write the file
     * @param providedFilename
     *            the name of the file
     * @param extension
     *            ) the extension of the file
     *
     */
    private IPath getFilePath(final IPath destinationFolder, final String providedFilename, final String extension) {

        String filename = null;

        if (providedFilename.length() == 0) {
            filename = Messages.ExportAction_defaultDiagramName;
        } else {
            filename = providedFilename;
        }

        IPath filePath;
        final StringBuffer file = new StringBuffer(filename).append(ExportAction.POINT).append(extension);

        final String filenameWithExtension = validFilename(file.toString());
        if (destinationFolder.append(filenameWithExtension).toFile().exists()) {
            int version = 1;

            do {
                final String newFileName = validFilename(new StringBuffer(filename).append('_').append(String.valueOf(version)).append(ExportAction.POINT).append(extension).toString());
                filePath = destinationFolder.append(newFileName);
                version++;
            } while (filePath.toFile().exists());
        } else {
            filePath = destinationFolder.append(filenameWithExtension);
        }
        return filePath;
    }

    private String validFilename(String filename) {
        final FileUtil util = new FileUtil(filename);
        if (util.isValid()) {
            return filename;
        } else {
            return util.getValidFilename();
        }
    }

}
