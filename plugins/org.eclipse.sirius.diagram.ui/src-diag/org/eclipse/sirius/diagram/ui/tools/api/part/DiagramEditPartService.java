/******************************************************************************
 * Copyright (c) 2005, 2019, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - Renamed, completed and adapted for Sirius.
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.part;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmf.runtime.common.core.command.FileModificationValidator;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.DiagramUIRenderPlugin;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.image.ImageExporter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.internal.refresh.layout.SiriusCanonicalLayoutHandler;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.part.OffscreenEditPartFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusDiagramImageGenerator;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusDiagramSVGGenerator;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;

/**
 * Utility class to render a diagram to an image file (with the use of specific OffscreenEditPartFactory). This class is
 * overridden for a problem in the default {@link org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer} used
 * by OffscreenEditPartFactory so we therefore use our {@link OffscreenEditPartFactory} in place.
 *
 * Many methods are duplicated from {@link CopyToImageUtil} (version
 * org.eclipse.gmf.runtime.diagram.ui.render_1.4.1.v20100909-1300).
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class DiagramEditPartService extends org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil {

    private static Class<?> exportToHtmlClass;

    private static Boolean canExportToHtml;

    private boolean exportToHtml;

    /**
     * Determines if we should scale the diagram when exporting.
     */
    private boolean autoScalingEnabled = true;

    /**
     * Scale level when exporting diagrams as image.
     */
    private Integer diagramScaleLevel = 100;

    /**
     * Determines if we allow downscaling (i.e. scaling to less than 100% of the original size). Only used if
     * autoScalingEnabled is true.
     */
    private boolean allowDownScaling = true;

    /**
     * The actual scaling factor used when exporting. 1.0 means 100%; 2.0 means 200% etc.
     */
    private double scalingFactor;

    /**
     * Whether we should insert a new attribute in SVG export to keep the traceability with the semantic element.
     */
    private boolean enableSemanticTraceability;

    /**
     * Constructor.
     */
    public DiagramEditPartService() {
        this(false);
    }

    /**
     * Constructor.
     * 
     * @param semanticTraceability
     *            whether to include semantic traceability (if the image format supports it).
     */
    public DiagramEditPartService(boolean semanticTraceability) {
        this.enableSemanticTraceability = semanticTraceability;
    }

    /**
     * Check if GMF is able to export in HTML.
     *
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    public static boolean canExportToHtml() {
        if (DiagramEditPartService.canExportToHtml == null) {
            try {
                DiagramEditPartService.exportToHtmlClass = Class.forName("org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToHTMLImageUtil"); //$NON-NLS-1$
                DiagramEditPartService.canExportToHtml = true;
            } catch (ClassNotFoundException e) {
                DiagramEditPartService.canExportToHtml = false;
            }
        }
        return DiagramEditPartService.canExportToHtml;
    }

    /**
     * Layout GMF views created by a
     * {@link org.eclipse.sirius.diagram.business.api.refresh.view.refresh.CanonicalSynchronizer#synchronize()} .
     *
     * NOTE : a set of {@link org.eclipse.emf.common.command.AbstractCommand.NonDirtying} commands will be executed.
     *
     * @param diagramEditPart
     *            the <code>DiagramEditPart</code> to layout.
     */
    public void arrangeCreatedViews(DiagramEditPart diagramEditPart) {
        SiriusCanonicalLayoutHandler.launchArrangeCommand(diagramEditPart);
    }

    /**
     * Set export to html to true.
     */
    public void exportToHtml() {
        this.exportToHtml = true;
    }

    /**
     * Creates a <code>DiagramEditPart</code> given the <code>Diagram</code> without opening an editor.
     *
     * NOTE : to avoid post-commit canonical refresh, execute DDiagramCanonicalSynchronizer#synchronize() on the GMF
     * model before.
     *
     * @param diagram
     *            the <code>Diagram</code>
     * @param shell
     *            An out parameter for the shell that must be disposed after the copy to image operation has completed.
     * @param preferencesHint
     *            The preference hint that is to be used to find the appropriate preference store from which to retrieve
     *            diagram preference values. The preference hint is mapped to a preference store in the preference
     *            registry <@link DiagramPreferencesRegistry>.
     * @return the new populated <code>DiagramEditPart</code>
     */
    @Override
    public DiagramEditPart createDiagramEditPart(Diagram diagram, Shell shell, PreferencesHint preferencesHint) {
        return OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell, preferencesHint);
    }

    /**
     * Enable or disable the automatic detection of the best (highest) export resolution possible.
     * 
     * @param autoScalingEnabled
     *            whether or not the enable auto-scaling.
     */
    public void setAutoScalingEnabled(boolean autoScalingEnabled) {
        this.autoScalingEnabled = autoScalingEnabled;
    }

    /**
     * Set the diagram scale level..
     * 
     * @param theDiagramScaleLevel
     *            diagram scale level in percent
     */
    public void setDiagramScaleLevel(Integer theDiagramScaleLevel) {
        this.diagramScaleLevel = theDiagramScaleLevel;
    }

    /**
     * Determines if we allow downscaling (i.e. scaling to less than 100% of the original size). Only used if
     * Auto-Scaling is enabled. The default is true.
     * 
     * @param allowDownScaling
     *            if we should allow downscaling on export.
     */
    public void setAllowDownScaling(boolean allowDownScaling) {
        this.allowDownScaling = allowDownScaling;
    }

    /**
     * Returns the scaling factor used to export the diagram.
     * 
     * @return the scaling factor used to export the diagram.
     */
    public double getScalingFactor() {
        return scalingFactor;
    }

    /**
     * Only overridden to use {@link SiriusDiagramSVGGenerator} instead of
     * {@link org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramSVGGenerator} .
     */
    @Override
    public byte[] copyToImageByteArray(DiagramEditPart diagramEP, List editParts, int maxWidth, int maxHeight, ImageFileFormat format, IProgressMonitor monitor, boolean useMargins)
            throws CoreException {
        Assert.isNotNull(diagramEP);
        byte[] result = null;
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

            DiagramGenerator gen = getDiagramGenerator(diagramEP, format);
            if (editParts == null || editParts.isEmpty()) {
                // CHECKSTYLE:OFF
                editParts = diagramEP.getPrimaryEditParts();
                // CHECKSTYLE:ON
            }
            if (format.equals(ImageFileFormat.SVG)) {
                gen.createConstrainedSWTImageDecriptorForParts(editParts, maxWidth, maxHeight, useMargins);
                monitor.worked(1);
                saveToOutputStream(stream, (SiriusDiagramSVGGenerator) gen, format, monitor);
            } else {
                Image image = gen.createConstrainedSWTImageDecriptorForParts(editParts, maxWidth, maxHeight, useMargins).createImage();
                monitor.worked(1);
                saveToOutputStream(stream, image, format, monitor);
                image.dispose();
            }
            result = stream.toByteArray();
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, -1, MessageFormat.format(Messages.DiagramEditPartService_imageExportException, "i/o exception"), e)); //$NON-NLS-1$
        }
        monitor.worked(1);
        return result;
    }

    @Override
    public DiagramGenerator copyToImage(DiagramEditPart diagramEP, IPath destination, org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat format, IProgressMonitor monitor) throws CoreException {
        if (exportToHtml && DiagramEditPartService.canExportToHtml()) {
            try {
                CopyToImageUtil copyToHmlUtil = (CopyToImageUtil) DiagramEditPartService.exportToHtmlClass.newInstance();
                return copyToHmlUtil.copyToImage(diagramEP, destination, format, monitor);
            } catch (InstantiationException e) {
                throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, -1, MessageFormat.format(Messages.DiagramEditPartService_imageExportException, "InstanciationException"), e)); //$NON-NLS-1$
            } catch (IllegalAccessException e) {
                throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, -1, MessageFormat.format(Messages.DiagramEditPartService_imageExportException, "IllegalAccessException"), e)); //$NON-NLS-1$
            } catch (OutOfMemoryError e) {
                throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, -1, MessageFormat.format(Messages.DiagramEditPartService_imageExportException, "OutofMemoryError"), e)); //$NON-NLS-1$
            }
        }
        // we check whether the target resolution is in reasonable limits.
        if (isTooBig(getDiagramGenerator(diagramEP, format), diagramEP, format, 1.0)) {
            String representationName = ((DSemanticDiagram) ((Diagram) diagramEP.getModel()).getElement()).getName();
            throw new SizeTooLargeException(new Status(IStatus.ERROR, SiriusPlugin.ID, representationName));
        }
        return super.copyToImage(diagramEP, destination, format, monitor);
    }

    private boolean isTooBig(DiagramGenerator gen, DiagramEditPart diagramEP, ImageFileFormat format, double factor) {
        if (Double.isNaN(factor)) {
            return true;
        }
        List<?> editParts = diagramEP.getPrimaryEditParts();
        org.eclipse.swt.graphics.Rectangle imageRect = gen.calculateImageRectangle(editParts);
        int maxSize = getMaximumTotalSize();
        return (imageRect.height * factor) * (imageRect.width * factor) > maxSize && format != ImageFileFormat.SVG;
    }

    /**
     * Return the maximum total size the image can have, total size being defined by width*height. This is used as a
     * safeguard to prevent out of memory errors.
     * 
     * This method is protected so that extenders can override the way this value is retrieved.
     * 
     * @return an integer representing the maximum total size (width*size) up to which the runtime will not even try to
     *         export the image.
     */
    protected int getMaximumTotalSize() {
        SiriusDiagramUiPreferencesKeys pref = SiriusDiagramUiPreferencesKeys.PREF_MAXIMUM_EXPORT_BUFFER_SIZE;
        if ("win32".equals(SWT.getPlatform())) { //$NON-NLS-1$
            /*
             * Windows has a separate preference as is can safely support larger image buffers.
             */
            pref = SiriusDiagramUiPreferencesKeys.PREF_MAXIMUM_EXPORT_BUFFER_SIZE_WINDOWS;
        }
        return DiagramUIPlugin.getPlugin().getPreferenceStore().getInt(pref.name());
    }

    /**
     * Return a factor to apply on the width and height of the image when exporting. This is useful to adapt the image
     * resolution based on the current context.
     * 
     * @param diagramEP
     *            the diagram edit part.
     * @param gen
     *            the image generator.
     * @return a factor to apply on the width and height of the image when exporting. If the size of the diagram is too
     *         big (width * height = a number bigger than int), NaN will be returned and caller must handle this case
     */
    protected double getExportResolutionFactor(DiagramEditPart diagramEP, SiriusDiagramImageGenerator gen) {
        double factor = 1.0;
        if (this.autoScalingEnabled) {
            List<?> editParts = diagramEP.getPrimaryEditParts();
            org.eclipse.swt.graphics.Rectangle imageRect = gen.calculateImageRectangle(editParts);
            // w = the image width / h = the image height
            // c= zoom factor we are looking for
            // max=the target size
            //
            // (w*c) * (h*c) == max
            // (c*c) == max/(w*h)
            // c == sqrt(max/(w*h))
            factor = Math.floor(Math.sqrt(Double.valueOf(getMaximumTotalSize()) / ((imageRect.width) * (imageRect.height))) * 100) / 100;
            if (factor < 1.0 && !allowDownScaling) {
                factor = 1.0;
            } else {
                // Apply Scale level : 100% means full autoScale whereas 0% means no autoScale
                factor = 1.0 + (factor - 1.0) * this.diagramScaleLevel / 100.;
            }

        }
        return factor;
    }

    /**
     * Only overridden to use {@link SiriusDiagramSVGGenerator} instead of
     * {@link org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramSVGGenerator} and
     * {@link SiriusDiagramImageGenerator} instead of
     * {@link org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramImageGenerator} .
     */
    @Override
    protected DiagramGenerator getDiagramGenerator(DiagramEditPart diagramEP, ImageFileFormat format) {
        if (format.equals(ImageFileFormat.SVG)) {
            return new SiriusDiagramSVGGenerator(diagramEP, enableSemanticTraceability);
        } else {
            SiriusDiagramImageGenerator generator = new SiriusDiagramImageGenerator(diagramEP);
            double factor = getExportResolutionFactor(diagramEP, generator);
            if (!isTooBig(generator, diagramEP, format, factor)) {
                generator.setResolutionScale(factor);
                this.scalingFactor = factor;
            }
            return generator;
        }
    }

    /**
     * Only overridden to use {@link SiriusDiagramSVGGenerator} instead of
     * {@link org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramSVGGenerator} .
     */
    @Override
    protected void copyToImage(DiagramGenerator gen, List editParts, org.eclipse.swt.graphics.Rectangle imageRect, IPath destination, ImageFileFormat format, IProgressMonitor monitor)
            throws CoreException {
        boolean found = false;
        if (format.equals(ImageFileFormat.SVG)) {
            gen.createSWTImageDescriptorForParts(editParts, imageRect);
            monitor.worked(1);
            saveToFile(destination, (SiriusDiagramSVGGenerator) gen, format, monitor);
            found = true;
        } else if (format.equals(ImageFileFormat.PNG)) {
            String exportFormat = ImageExporter.PNG_FILE;
            java.awt.Image image = gen.createAWTImageForParts(editParts, imageRect);
            monitor.worked(1);
            if (image instanceof BufferedImage) {
                ImageExporter.exportToFile(destination, (BufferedImage) image, exportFormat, monitor, format.getQuality());
                found = true;
            }
        }

        if (!found) {
            Image image = null;
            try {
                image = gen.createSWTImageDescriptorForParts(editParts, imageRect).createImage();
                monitor.worked(1);
                saveToFile(destination, image, format, monitor);
            } finally {
                if (image != null) {
                    image.dispose();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil#saveToFile(org.eclipse.core.runtime.IPath,
     *      org.eclipse.swt.graphics.Image, org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat,
     *      org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected void saveToFile(IPath destination, Image image, ImageFileFormat imageFormat, IProgressMonitor monitor) throws CoreException {
        // super.saveToFile(destination, image, imageFormat, monitor);
        IStatus fileModificationStatus = createFile(destination);
        if (!fileModificationStatus.isOK()) {
            // can't write to the file
            return;
        }

        // CHECKSTYLE:OFF (duplicate code from GMF)
        try (FileOutputStream stream = new FileOutputStream(destination.toOSString())) {
            saveToOutputStream(stream, image, imageFormat, monitor);
        } catch (Exception e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
            IStatus status = new Status(IStatus.ERROR, "exportToFile", IStatus.OK, //$NON-NLS-1$
                    e.getMessage(), e);
            throw new CoreException(status);
        }
        // CHECKSTYLE:ON

        refreshLocal(destination);
    }

    private void saveToOutputStream(OutputStream stream, Image image, ImageFileFormat imageFormat, IProgressMonitor monitor) {
        monitor.worked(1);

        ImageData imageData = image.getImageData();
        // In original CopyToImageUtil class, the reduction to a "8BitPalette"
        // or a "WebSafePallette" is for GIF and BMP. For GIF file, it is
        // explicitly constraint by the format characteristic and by an
        // SWT.ERROR_UNSUPPORTED_DEPTH in
        // org.eclipse.swt.internal.image.GIFFileFormat.unloadIntoByteStream(ImageLoader).
        // But for BMP, there is no apparent reason so it is remove for this
        // format.
        if (imageFormat.equals(ImageFileFormat.GIF)) {
            imageData = createImageData(image);
        }

        monitor.worked(1);

        ImageLoader imageLoader = new ImageLoader();
        imageLoader.data = new ImageData[] { imageData };
        imageLoader.logicalScreenHeight = image.getBounds().width;
        imageLoader.logicalScreenHeight = image.getBounds().height;
        if (imageFormat.equals(ImageFileFormat.JPG)) {
            imageLoader.compression = 100;
        }
        imageLoader.save(stream, imageFormat.getOrdinal());

        monitor.worked(1);
    }

    /**
     * Saves an SVG DOM to a file.<BR>
     * Method duplicated from
     * {@link #saveSVGToFile(IPath, org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramSVGGenerator, IProgressMonitor)}
     * to use a {@link SiriusDiagramSVGGenerator} instead of a DiagramSVGGenerator.
     * 
     * @param destination
     *            the destination file, including path and file name
     * @param generator
     *            the svg generator for a diagram, used to write
     * @param monitor
     *            the progress monitor
     * @exception CoreException
     *                if this method fails
     */
    protected void saveSVGToFile(IPath destination, SiriusDiagramSVGGenerator generator, IProgressMonitor monitor) throws CoreException {
        saveToFile(destination, generator, ImageFileFormat.SVG, monitor);
    }

    /**
     * Saves an SVG files.<BR>
     * Method duplicated from
     * {@link #saveToFile(IPath, org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramSVGGenerator, ImageFileFormat, IProgressMonitor)}
     * to use a {@link SiriusDiagramSVGGenerator} instead of a DiagramSVGGenerator.
     * 
     * @param destination
     *            the destination file, including path and file name
     * @param generator
     *            the svg generator for a diagram, used to write
     * @param format
     *            currently supports SVG
     * @param monitor
     *            the progress monitor
     * @exception CoreException
     *                if this method fails
     */
    protected void saveToFile(IPath destination, SiriusDiagramSVGGenerator generator, ImageFileFormat format, IProgressMonitor monitor) throws CoreException {

        IStatus fileModificationStatus = createFile(destination);
        if (!fileModificationStatus.isOK()) {
            // can't write to the file
            return;
        }
        monitor.worked(1);

        try {
            FileOutputStream os = new FileOutputStream(destination.toOSString());
            monitor.worked(1);
            saveToOutputStream(os, generator, format == ImageFileFormat.JPEG ? ImageFileFormat.JPG : format, monitor);
            os.close();
            monitor.worked(1);
            refreshLocal(destination);
        } catch (IOException ex) {
            Log.error(DiagramUIRenderPlugin.getInstance(), IStatus.ERROR, ex.getMessage(), ex);
            IStatus status = new Status(IStatus.ERROR, "exportToFile", IStatus.OK, //$NON-NLS-1$
                    ex.getMessage(), null);
            throw new CoreException(status);
        }
    }

    /**
     * Method duplicated from
     * {@link #saveToOutputStream(OutputStream, org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramSVGGenerator, ImageFileFormat, IProgressMonitor)}
     * to use a {@link SiriusDiagramSVGGenerator} instead of a DiagramSVGGenerator.
     */
    private void saveToOutputStream(OutputStream stream, SiriusDiagramSVGGenerator generator, ImageFileFormat format, IProgressMonitor monitor) throws CoreException {
        if (format == ImageFileFormat.SVG) {
            generator.stream(stream);
        } else {
            throw new IllegalArgumentException("Unexpected format: " + format.getName()); //$NON-NLS-1$
        }
        monitor.worked(1);
    }

    /**
     * create a file in the workspace if the destination is in a project in the workspace.
     *
     * @param destination
     *            the destination file.
     * @return the status from validating the file for editing
     * @exception CoreException
     *                if this method fails
     */
    private IStatus createFile(IPath destination) throws CoreException {
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(destination);
        if (file != null && !file.exists()) {
            File osFile = new File(destination.toOSString());
            if (osFile.exists()) {
                file.refreshLocal(IResource.DEPTH_ZERO, null);
            } else {
                ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
                InputStream input = new ByteArrayInputStream(new byte[0]);
                file.create(input, false, null);
            }
        }

        if (file != null) {
            return FileModificationValidator.approveFileModification(new IFile[] { file });
        }
        return Status.OK_STATUS;
    }

    /**
     * refresh the file in the workspace if the destination is in a project in the workspace.
     *
     * @param destination
     *            the destination file.
     * @exception CoreException
     *                if this method fails
     */
    private void refreshLocal(IPath destination) throws CoreException {
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(destination);
        if (file != null) {
            file.refreshLocal(IResource.DEPTH_ZERO, null);
        }
    }

    /**
     * Retrieve the image data for the image, using a palette of at most 256 colours.
     *
     * @param image
     *            the SWT image.
     * @return new image data.
     */
    private ImageData createImageData(Image image) {

        ImageData imageData = image.getImageData();

        /**
         * If the image depth is 8 bits or less, then we can use the existing image data.
         */
        if (imageData.depth <= 8) {
            return imageData;
        }

        /**
         * get an 8 bit imageData for the image
         */
        ImageData newImageData = get8BitPaletteImageData(imageData);

        /**
         * if newImageData is null, it has more than 256 colours. Use the web safe pallette to get an 8 bit image data
         * for the image.
         */
        if (newImageData == null) {
            newImageData = getWebSafePalletteImageData(imageData);
        }

        return newImageData;
    }

    /**
     * Retrieve an image data with an 8 bit palette for an image. We assume that the image has less than 256 colours.
     *
     * @param imageData
     *            the imageData for the image.
     * @return the new 8 bit imageData or null if the image has more than 256 colours.
     */
    private ImageData get8BitPaletteImageData(ImageData imageData) {
        PaletteData palette = imageData.palette;
        RGB[] colours = new RGB[256];
        PaletteData newPaletteData = new PaletteData(colours);
        ImageData newImageData = new ImageData(imageData.width, imageData.height, 8, newPaletteData);

        int lastPixel = -1;
        int newPixel = -1;
        for (int i = 0; i < imageData.width; ++i) {
            for (int j = 0; j < imageData.height; ++j) {
                int pixel = imageData.getPixel(i, j);

                if (pixel != lastPixel) {
                    lastPixel = pixel;

                    RGB colour = palette.getRGB(pixel);
                    for (newPixel = 0; newPixel < 256; ++newPixel) {
                        if (colours[newPixel] == null) {
                            colours[newPixel] = colour;
                            break;
                        }
                        if (colours[newPixel].equals(colour)) {
                            break;
                        }
                    }

                    if (newPixel >= 256) {
                        /**
                         * Diagram has more than 256 colors, return null
                         */
                        return null;
                    }
                }

                newImageData.setPixel(i, j, newPixel);
            }
        }

        RGB colour = new RGB(0, 0, 0);
        for (int k = 0; k < 256; ++k) {
            if (colours[k] == null) {
                colours[k] = colour;
            }
        }

        return newImageData;
    }

    /**
     * If the image has less than 256 colours, simply create a new 8 bit palette and map the colours to the new palatte.
     */
    private ImageData getWebSafePalletteImageData(ImageData imageData) {
        PaletteData palette = imageData.palette;
        RGB[] webSafePallette = getWebSafePallette();
        PaletteData newPaletteData = new PaletteData(webSafePallette);
        ImageData newImageData = new ImageData(imageData.width, imageData.height, 8, newPaletteData);

        int lastPixel = -1;
        int newPixel = -1;
        for (int i = 0; i < imageData.width; ++i) {
            for (int j = 0; j < imageData.height; ++j) {
                int pixel = imageData.getPixel(i, j);

                if (pixel != lastPixel) {
                    lastPixel = pixel;

                    RGB colour = palette.getRGB(pixel);
                    RGB webSafeColour = getWebSafeColour(colour);
                    for (newPixel = 0; newPixel < 256; ++newPixel) {
                        if (webSafePallette[newPixel].equals(webSafeColour)) {
                            break;
                        }
                    }

                    Assert.isTrue(newPixel < 216);
                }
                newImageData.setPixel(i, j, newPixel);
            }
        }

        return newImageData;
    }

    /**
     * Retrieves a web safe colour that closely matches the provided colour.
     *
     * @param colour
     *            a colour.
     * @return the web safe colour.
     */
    private RGB getWebSafeColour(RGB colour) {
        int red = Math.round((colour.red + 25) / 51) * 51;
        int green = Math.round((colour.green + 25) / 51) * 51;
        int blue = Math.round((colour.blue + 25) / 51) * 51;
        return new RGB(red, green, blue);
    }

    /**
     * Retrieves a web safe pallette. Our palette will be 216 web safe colours and the remaining filled with white.
     *
     * @return array of 256 colours.
     */
    private RGB[] getWebSafePallette() {
        RGB[] colours = new RGB[256];
        int i = 0;
        for (int red = 0; red <= 255; red = red + 51) {
            for (int green = 0; green <= 255; green = green + 51) {
                for (int blue = 0; blue <= 255; blue = blue + 51) {
                    RGB colour = new RGB(red, green, blue);
                    colours[i++] = colour;
                }
            }
        }

        RGB colour = new RGB(0, 0, 0);
        for (int k = 0; k < 256; ++k) {
            if (colours[k] == null) {
                colours[k] = colour;
            }
        }

        return colours;
    }

}
