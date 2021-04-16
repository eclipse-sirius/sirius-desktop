/******************************************************************************
 * Copyright (c) 2004, 2016 IBM Corporation and others.
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

import java.awt.Color;
import java.awt.Dimension;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.svggen.DOMTreeManager;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.util.SVGConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderInfo;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.svg.SVGColorConverter;
import org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.svg.SVGImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.DrawableRenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.RenderingListener;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Objects of this class can be used with draw2d to create an SVG DOM.<BR>
 * Class copied from
 * {@link org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.svg.export.GraphicsSVG}
 * to inherit of {@link SiriusGraphicsToGraphics2DAdaptor} instead of
 * {@link org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.GraphicsToGraphics2DAdaptor}
 * and so handles gradient with method
 * {@link #setBackgroundPattern(java.awt.GradientPaint)}.
 * 
 * @author jschofie / sshaw
 */
// CHECKSTYLE:OFF
public class SiriusGraphicsSVG extends SiriusGraphicsToGraphics2DAdaptor implements DrawableRenderedImage {

    private Document doc;

    /**
     * Static initializer that will return an instance of
     * <code>SiriusGraphicsSVG</code>
     *
     * @param viewPort
     *            the <code>Rectangle</code> area that is to be rendered.
     * @return a new <code>SiriusGraphicsSVG</code> object.
     */
    public static SiriusGraphicsSVG getInstance(Rectangle viewPort) {
        SVGGraphics2D svgGraphics;

        // Get the DOM implementation and create the document
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNameSpace = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document doc = impl.createDocument(svgNameSpace, "svg", null); //$NON-NLS-1$

        // Create the SVG Graphics Object
        svgGraphics = new SVGGraphics2D(doc);

        // Set the precision level to avoid NPEs (issue with Batik 1.5)
        svgGraphics.getGeneratorContext().setPrecision(3);

        // Set the Width and Height Attributes on the Root Element
        svgGraphics.setSVGCanvasSize(new Dimension(viewPort.width, viewPort.height));

        return new SiriusGraphicsSVG(svgGraphics, doc, svgNameSpace, viewPort);
    }

    /**
     * @return <code>SVGGraphics2D</code> object
     */
    public SVGGraphics2D getSVGGraphics2D() {
        return (SVGGraphics2D) getGraphics2D();
    }

    /**
     * @param graphics
     * @param doc
     * @param svgNameSpace
     * @param viewPort
     */
    private SiriusGraphicsSVG(SVGGraphics2D graphics, Document doc, String svgNameSpace, Rectangle viewPort) {

        this(graphics, doc, svgNameSpace, new org.eclipse.swt.graphics.Rectangle(viewPort.x, viewPort.y, viewPort.width, viewPort.height));
    }

    /**
     * @param graphics
     * @param doc
     * @param svgNameSpace
     * @param viewPort
     */
    private SiriusGraphicsSVG(SVGGraphics2D graphics, Document doc, String svgNameSpace, org.eclipse.swt.graphics.Rectangle viewPort) {

        super(graphics, viewPort);
        this.doc = doc;
        paintNotCompatibleStringsAsBitmaps = false;
    }

    /**
     * Method used to get the SVG DOM from the Graphics
     *
     * @return SVG document
     */
    public Document getDocument() {
        return doc;
    }

    /**
     * Method used to get the SVG Root element from the document
     *
     * @return DOM Root element
     */
    public Element getRoot() {
        return getSVGGraphics2D().getRoot();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.
     * GraphicsToGraphics2DAdaptor#drawRenderedImage(org.eclipse.gmf.runtime.
     * draw2d.ui.render.RenderedImage, org.eclipse.draw2d.geometry.Rectangle,
     * org.eclipse.gmf.runtime.draw2d.ui.render.RenderingListener)
     */
    @Override
    public RenderedImage drawRenderedImage(RenderedImage srcImage, Rectangle rect, RenderingListener listener) {

        // Check for a change in the state
        checkState();

        // Get the Tree Manager
        DOMTreeManager treeManager = getSVGGraphics2D().getDOMTreeManager();

        Point trans = getTranslationOffset();
        // Get the Root element of the SVG document to export
        if (srcImage instanceof SVGImage) {
            Document document = ((SVGImage) srcImage).getDocument();

            DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
            document = DOMUtilities.deepCloneDocument(document, impl);

            if (document instanceof SVGOMDocument) {
                RenderInfo info = srcImage.getRenderInfo();
                if (info != null && info.getBackgroundColor() != null && info.getForegroundColor() != null) {
                    SVGColorConverter.getInstance().replaceDocumentColors((SVGOMDocument) document,
                            new Color(info.getBackgroundColor().red, info.getBackgroundColor().green, info.getBackgroundColor().blue),
                            new Color(info.getForegroundColor().red, info.getForegroundColor().green, info.getForegroundColor().blue));

                }
            }
            Element root = document.getDocumentElement();

            // Create a "deep" copy of the document
            Element toAppend = (Element) doc.importNode(root, true);

            // Modify the X Attribute
            toAppend.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(rect.x + trans.x));

            // Modify the Y Attribute
            toAppend.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(rect.y + trans.y));

            // Modify the Width Attribute
            toAppend.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(rect.width));

            // Modify the Height Attribute
            toAppend.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(rect.height));

            treeManager.appendGroup(toAppend, null);
            return srcImage;
        } else {
            return super.drawRenderedImage(srcImage, rect, listener);
        }
    }

}
// CHECKSTYLE:ON
