/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.actions.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.FileUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
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
    public static final String EXPORT_DIAGRAMS_AS_IMAGES_ACTION_TITLE = "Export Diagrams as images";

    private static final String EXPORT_DIAGRAMS_AS_IMAGES_ACTION_CANCELLED = "The operation was cancelled";

    private static final String EXPORT_DIAGRAMS_AS_IMAGES_ACTION_DEFAULT_DIAGRAM_NAME = "DiagramWithoutName";

    private static final String EXPORT_DIAGRAMS_AS_IMAGES_ACTION_ERROR_ON_MEMORY_ALLOCATION = "Not enough memory available to create image files";

    private static final char POINT = '.';

    private Session session;

    private Collection<DRepresentation> dRepresentationsToExportAsImage;

    private IPath outputPath;

    private ImageFileFormat imageFormat;

    private boolean exportToHtml;

    /**
     * Default constructor.
     * 
     * @param session
     *            The {@link Session} which owns the {@link DRepresentation}s to
     *            export as image
     * @param dRepresentationsToExportAsImage
     *            the {@link DRepresentation}s to export as image
     * @param outputPath
     *            the folder in which store the images, result of the export
     * @param imageFormat
     *            the format of the image, result of the export
     * @param exportToHtml
     *            true if we must export {@link DRepresentation} to html instead
     *            of image
     */
    public ExportAction(Session session, Collection<DRepresentation> dRepresentationsToExportAsImage, IPath outputPath, ImageFileFormat imageFormat, boolean exportToHtml) {
        this.session = session;
        this.dRepresentationsToExportAsImage = dRepresentationsToExportAsImage;
        this.outputPath = outputPath;
        this.imageFormat = imageFormat;
        this.exportToHtml = exportToHtml;
    }

    /**
     * Overridden to do the export work.
     * 
     * {@inheritDoc}
     */
    protected void execute(IProgressMonitor monitor) throws CoreException, java.lang.reflect.InvocationTargetException, InterruptedException {
        try {
            monitor.beginTask(EXPORT_DIAGRAMS_AS_IMAGES_ACTION_TITLE, 7);
            try {
                createImageFiles(monitor);
            } catch (final OutOfMemoryError e) {
                handleException(e, EXPORT_DIAGRAMS_AS_IMAGES_ACTION_ERROR_ON_MEMORY_ALLOCATION);
            }
        } finally {
            monitor.done();
            if (monitor.isCanceled()) {
                throw new InterruptedException(EXPORT_DIAGRAMS_AS_IMAGES_ACTION_CANCELLED);
            }
        }
    }

    private void handleException(final Throwable t, final String title) {
        final Shell shell = new Shell();
        MessageDialog.openError(shell, title, t.getMessage());
        shell.dispose();
    }

    /**
     * Create the image files.
     * 
     * @param monitor
     *            the progress monitor
     */
    protected void createImageFiles(final IProgressMonitor monitor) {

        final List<IBeforeExport> beforeContributors = EclipseUtil.getExtensionPlugins(IBeforeExport.class, IExportRepresentationsAsImagesExtension.ID,
                IExportRepresentationsAsImagesExtension.CLASS_ATTRIBUTE);
        final List<IAroundExport> aroundContributors = EclipseUtil.getExtensionPlugins(IAroundExport.class, IExportRepresentationsAsImagesExtension.ID,
                IExportRepresentationsAsImagesExtension.CLASS_ATTRIBUTE);
        final List<IAfterExport> afterContributors = EclipseUtil.getExtensionPlugins(IAfterExport.class, IExportRepresentationsAsImagesExtension.ID,
                IExportRepresentationsAsImagesExtension.CLASS_ATTRIBUTE);

        ExportFormat exportFormat = new ExportFormat(exportToHtml ? ExportDocumentFormat.HTML : ExportDocumentFormat.NONE, imageFormat);
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
            boolean errorDuringExport = false;
            List<Throwable> messageException = new ArrayList<Throwable>();
            final Shell shell = Display.getCurrent().getActiveShell();
            try {
                for (final DRepresentation representation : dRepresentationsToExportAsImage) {
                    final IPath filePath;
                    // Check that the file name is informed
                    // Put extension to lowerCase.
                    if (outputPath.toFile().isDirectory()) {
                        filePath = getFilePath(outputPath, representation.getName(), imageFileExtension);
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
                            DialectUIManager.INSTANCE.export(representation, session, filePath, exportFormat, new SubProgressMonitor(monitor, 7));
                        } catch (CoreException exception) {
                            if (exception instanceof SizeTooLargeException) {
                                errorDuringExport = true;
                                messageException.add(exception);
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
                if (errorDuringExport) {
                    // Construct message for dialog and error in error log.
                    StringBuffer messageExceptionForDialog = new StringBuffer();
                    messageExceptionForDialog.append("One or more representations could not be exported because they are too large: ");
                    for (Throwable thr : messageException) {
                        messageExceptionForDialog.append("\n"); //$NON-NLS-1$
                        messageExceptionForDialog.append(" - "); //$NON-NLS-1$
                        messageExceptionForDialog.append(thr.getMessage());
                    }
                    // Create a popup menu to inform user that representations
                    // export failed
                    MessageDialog.openError(shell, "Image export impossible", messageExceptionForDialog.toString());

                    // Add in the 'error log' the representations export
                    // failed
                    SiriusPlugin.getDefault().error("Error while export representation to image",
                            new SizeTooLargeException(new Status(IStatus.ERROR, SiriusPlugin.ID, messageExceptionForDialog.toString())));
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

    /**
     * Get the {@link IPath} of the file to write. This method try first to use
     * the filename given. If the file already exists, it appends a number to
     * its name.
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
            filename = new String(EXPORT_DIAGRAMS_AS_IMAGES_ACTION_DEFAULT_DIAGRAM_NAME);
        } else {
            filename = providedFilename;
        }

        IPath filePath;
        final StringBuffer file = new StringBuffer(filename).append(POINT).append(extension);

        final String filenameWithExtension = validFilename(file.toString());
        if (destinationFolder.append(filenameWithExtension).toFile().exists()) {
            int version = 1;

            do {
                final String newFileName = validFilename(new StringBuffer(filename).append('_').append(String.valueOf(version)).append(POINT).append(extension).toString());
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
