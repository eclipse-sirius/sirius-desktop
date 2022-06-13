/******************************************************************************
 * Copyright (c) 2004, 2022 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.render;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.SharedImages;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.DiagramUIRenderDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.DiagramUIRenderPlugin;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.image.ImageConverter;
import org.eclipse.gmf.runtime.draw2d.ui.render.factory.RenderedImageFactory;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.RenderedImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.ImageData;
import org.w3c.dom.Element;

/**
 * Supports generation of an SVG DOM for a diagram or a subset of editparts on a diagram.<BR>
 * Class copied from {@link org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramSVGGenerator} to use a specific
 * GraphicsToGraphics2DAdaptor ({@link SiriusGraphicsSVG} instead of
 * {@link org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.svg.export.GraphicsSVG} ) that handles the gradient.
 *
 * @author jschofie / sshaw
 */
// CHECKSTYLE:OFF
@SuppressWarnings("restriction")
public class SiriusDiagramSVGGenerator extends DiagramGenerator {

    /**
     * Bugzilla 57850: the name of the system property to enable the embedded SVG in SVG export behavior (false by
     * default). If this property is set to true, embedded SVG in SVG export is launched.
     */
    public static final String ENABLE_EMBEDDED_SVG_IN_SVG_EXPORT = "org.eclipse.sirius.diagram.ui.enableEmbeddedSVGInSVGExport"; //$NON-NLS-1$

    private RenderedImage renderedImage = null;

    private Element svgRoot = null;

    private Rectangle viewBox = null;

    private boolean svgTraceability;

    private IFigure overlayFigure;

    /**
     * Creates a new instance.
     *
     * @param diagramEditPart
     *            the diagram editpart
     * @param svgTraceability
     *            whether we should add an attribute in SVG elements to keep the ID of the target semantic element.
     */
    public SiriusDiagramSVGGenerator(DiagramEditPart diagramEditPart, boolean svgTraceability) {
        super(diagramEditPart);
        if (diagramEditPart instanceof DDiagramEditPart) {
            this.overlayFigure = ((DDiagramEditPart) diagramEditPart).getOverlayFigure();
        }
        this.svgTraceability = svgTraceability;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator# setUpGraphics(int, int)
     */
    @Override
    protected Graphics setUpGraphics(int width, int height) {
        viewBox = new Rectangle(0, 0, width, height);
        // GraphicsSVG replaced by SiriusGraphicsSVG
        return SiriusGraphicsSVG.getInstance(viewBox, svgTraceability);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator#
     * getImageDescriptor(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected ImageDescriptor getImageDescriptor(Graphics g) {
        try {
            // GraphicsSVG replaced by SiriusGraphicsSVG
            SiriusGraphicsSVG svgG = (SiriusGraphicsSVG) g;
            // Get the root element (the svg element)
            svgRoot = svgG.getRoot();

            ByteArrayOutputStream os = new ByteArrayOutputStream(5000); // 5K
                                                                        // buffer
            stream(os);
            os.close();

            setRenderedImage(RenderedImageFactory.getInstance(os.toByteArray()));

            return RenderedImageDescriptor.createFromRenderedImage(getRenderedImage());
        } catch (IOException ex) {
            Log.error(DiagramUIRenderPlugin.getInstance(), IStatus.ERROR, ex.getMessage(), ex);
        }

        return null;
    }

    /**
     * Writes the SVG Model out to a file.
     *
     * @param outputStream
     *            output stream to store the SVG Model
     */
    public void stream(OutputStream outputStream) {
        try {

            // Define the view box
            svgRoot.setAttributeNS(null, "viewBox", //$NON-NLS-1$
                    String.valueOf(viewBox.x) + " " + //$NON-NLS-1$
                            String.valueOf(viewBox.y) + " " + //$NON-NLS-1$
                            String.valueOf(viewBox.width) + " " + //$NON-NLS-1$
                            String.valueOf(viewBox.height));
            EObject semanticRoot = ((DSemanticDiagram) getDiagramEditPart().resolveSemanticElement()).getTarget();
            svgRoot.setAttributeNS(DiagramPackage.eNS_URI, "diagram:semanticRoot", EcoreUtil.getURI(semanticRoot).toString()); //$NON-NLS-1$

            // Write the document to the stream
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$

            DOMSource source = new DOMSource(svgRoot);
            StreamResult result = new StreamResult(outputStream);
            transformer.transform(source, result);
        } catch (Exception ex) {
            Log.error(DiagramUIRenderPlugin.getInstance(), IStatus.ERROR, ex.getMessage(), ex);
        }
    }

    /**
     * Copied from
     * org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator.createSWTImageDescriptorForParts(List,
     * Rectangle) to override the RenderedMapModeGraphics by the Sirius one.
     */
    @Override
    public ImageDescriptor createSWTImageDescriptorForParts(List editparts, org.eclipse.swt.graphics.Rectangle sourceRect) {

        // initialize imageDesc to the error icon
        ImageDescriptor imageDesc = new ImageDescriptor() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.resource.ImageDescriptor#getImageData()
             */
            @Override
            public ImageData getImageData() {
                return SharedImages.get(SharedImages.IMG_ERROR).getImageData();
            }
        };

        Graphics graphics = null;
        try {
            IMapMode mm = getMapMode();

            PrecisionRectangle rect = new PrecisionRectangle();
            rect.setX(sourceRect.x);
            rect.setY(sourceRect.y);
            rect.setWidth(sourceRect.width);
            rect.setHeight(sourceRect.height);

            mm.LPtoDP(rect);

            // Create the graphics and wrap it with the HiMetric graphics object
            graphics = setUpGraphics((int) Math.round(rect.preciseWidth()), (int) Math.round(rect.preciseHeight()));

            SiriusRenderedMapModeGraphics mapModeGraphics = new SiriusRenderedMapModeGraphics(graphics, getMapMode());

            renderToGraphics(mapModeGraphics, new Point(sourceRect.x, sourceRect.y), editparts);
            imageDesc = getImageDescriptor(graphics);
        } finally {
            if (graphics != null)
                disposeGraphics(graphics);
        }

        return imageDesc;
    }

    @Override
    protected void renderToGraphics(Graphics graphics, Point translateOffset, List editparts) {
        // List sortedEditparts = sortSelection(editparts);

        graphics.translate((-translateOffset.x), (-translateOffset.y));
        graphics.pushState();

        List<GraphicalEditPart> connectionsToPaint = new LinkedList<GraphicalEditPart>();

        Map decorations = findDecorations(editparts);

        for (Iterator editPartsItr = editparts.listIterator(); editPartsItr.hasNext();) {
            IGraphicalEditPart editPart = (IGraphicalEditPart) editPartsItr.next();

            // do not paint selected connection part
            if (editPart instanceof ConnectionEditPart) {
                connectionsToPaint.add(editPart);
            } else {
                connectionsToPaint.addAll(findConnectionsToPaint(editPart));
                // paint shape figure
                IFigure figure = editPart.getFigure();
                setCurrentId(graphics, editPart);
                paintFigure(graphics, figure);
                paintDecorations(graphics, figure, decorations);
                resetCurrentId(graphics);
            }
        }

        // paint the connection parts after shape parts paint
        decorations = findDecorations(connectionsToPaint);

        for (Iterator<GraphicalEditPart> connItr = connectionsToPaint.iterator(); connItr.hasNext();) {
            GraphicalEditPart conn = connItr.next();
            setCurrentId(graphics, conn);
            IFigure figure = conn.getFigure();
            paintFigure(graphics, figure);
            paintDecorations(graphics, figure, decorations);
            resetCurrentId(graphics);
        }
        // for each editpart (including children and connections), register the corresponding figure's absolute bounds
        // with the annotation to add
        // then in stream (or is it getImageDescritor?) iterate on the final SVG document, and for each
        // SVGOMRectElement, find the best match in the previous map and add the corresponding id.

        if (this.overlayFigure != null) {
            paintFigure(graphics, overlayFigure);
        }

        // Add symbol tags at the end of the document if there is any referenced SVG images.
        if (isEmbeddedSVGinSVGExportEnabled() && SVGImageRegistry.hasReferencedImages()) {
            if (graphics instanceof SiriusRenderedMapModeGraphics && ((SiriusRenderedMapModeGraphics) graphics).getGraphics() instanceof SiriusGraphicsSVG) {
                SVGImageRegistry.getURLs().forEach(url -> {
                    Optional<String> uuid = SVGImageRegistry.getUUID(url);
                    if (uuid.isPresent()) {
                        ((SiriusRenderedMapModeGraphics) graphics).drawSymbolSVGImage(url, uuid.get());
                    }
                });
            }
            SVGImageRegistry.reset();
        }
    }

    private void resetCurrentId(Graphics gfx) {
        CommonEditPartOperation.setGraphicsTraceabilityId(gfx, null);
    }

    private void setCurrentId(Graphics gfx, GraphicalEditPart part) {
        if (part instanceof IGraphicalEditPart) {
            CommonEditPartOperation.setGraphicsTraceabilityId(gfx, () -> {
                EObject o = ((IGraphicalEditPart) part).resolveSemanticElement();
                if (o instanceof DSemanticDecorator) {
                    return ((DSemanticDecorator) o).getTarget();
                }
                return o;
            });
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.internal.clipboard.DiagramGenerator#
     * createAWTImageForParts(java.util.List)
     */
    @Override
    public Image createAWTImageForParts(List editparts, org.eclipse.swt.graphics.Rectangle sourceRect) {
        createSWTImageDescriptorForParts(editparts, sourceRect);
        if (getRenderedImage() != null) {
            try {
                BufferedImage bufImg = getRenderedImage().getAdapter(BufferedImage.class);
                if (bufImg == null) {
                    bufImg = ImageConverter.convert(getRenderedImage().getSWTImage());
                }
                return bufImg;
            } catch (Error e) {
                // log the Error but allow execution to continue
                Trace.catching(DiagramUIRenderPlugin.getInstance(), DiagramUIRenderDebugOptions.EXCEPTIONS_THROWING, getClass(), "createAWTImageForParts() failed to generate image", //$NON-NLS-1$
                        e);
                return ImageConverter.convert(SharedImages.get(SharedImages.IMG_ERROR));

            } catch (Exception ex) {
                // log the Exception but allow execution to continue
                Trace.catching(DiagramUIRenderPlugin.getInstance(), DiagramUIRenderDebugOptions.EXCEPTIONS_THROWING, getClass(), "createAWTImageForParts() failed to generate image", //$NON-NLS-1$
                        ex);
                return ImageConverter.convert(SharedImages.get(SharedImages.IMG_ERROR));
            }
        }

        return ImageConverter.convert(SharedImages.get(SharedImages.IMG_ERROR));
    }

    /**
     * @return Returns the rendered image created by previous call to createSWTImageDescriptorForParts
     */
    public RenderedImage getRenderedImage() {
        return renderedImage;
    }

    /**
     * @param svgImage
     *            The svgImage to set.
     */
    private void setRenderedImage(RenderedImage renderedImage) {
        this.renderedImage = renderedImage;
    }

    /**
     * Bugzilla 57850: the embedded SVG in SVG export behavior. This system property has been added to allow to use the
     * embedded SVG in SVG export behavior. It is a temporary method, waiting a potential feedback.
     * 
     * @return true if embedded SVG must be enabled or false to have the current behavior.
     */
    public static boolean isEmbeddedSVGinSVGExportEnabled() {
        return Boolean.valueOf(System.getProperty(ENABLE_EMBEDDED_SVG_IN_SVG_EXPORT, "true")); //$NON-NLS-1$
    }

}
// CHECKSTYLE:ON
