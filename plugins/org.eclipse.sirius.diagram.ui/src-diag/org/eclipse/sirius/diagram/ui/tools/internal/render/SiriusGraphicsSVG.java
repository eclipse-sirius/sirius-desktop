/******************************************************************************
 * Copyright (c) 2004, 2024 IBM Corporation and others.
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
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.awt.image.renderable.RenderableImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGOMDefsElement;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.anim.dom.SVGOMGElement;
import org.apache.batik.anim.dom.SVGOMImageElement;
import org.apache.batik.constants.XMLConstants;
import org.apache.batik.dom.AbstractDocument;
import org.apache.batik.dom.util.DOMUtilities;
import org.apache.batik.svggen.DOMTreeManager;
import org.apache.batik.svggen.GenericImageHandler;
import org.apache.batik.svggen.SVGGeneratorContext;
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
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Objects of this class can be used with draw2d to create an SVG DOM.<BR>
 * Class copied from {@link org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.svg.export.GraphicsSVG} to inherit of
 * {@link SiriusGraphicsToGraphics2DAdaptor} instead of
 * {@link org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.GraphicsToGraphics2DAdaptor} and so handles
 * gradient with method {@link #setBackgroundPattern(java.awt.GradientPaint)}.
 * 
 * @author jschofie / sshaw
 */
// CHECKSTYLE:OFF
@SuppressWarnings("restriction")
public class SiriusGraphicsSVG extends SiriusGraphicsToGraphics2DAdaptor implements DrawableRenderedImage {

    private static class CustomSVGGraphics2D extends SVGGraphics2D {
        private boolean svgTraceability;

        private String currentId;

        public CustomSVGGraphics2D(Document doc, boolean svgTraceability) {
            super(doc);
            this.svgTraceability = svgTraceability;
        }

        public void setupCustomShapeConverter() {
            this.shapeConverter = new AnnotatedSVGShape(this.generatorCtx, DiagramPackage.eNS_URI, svgTraceability);
            final GenericImageHandler handler = getGenericImageHandler();
            this.getGeneratorContext().setGenericImageHandler(new GenericImageHandler() {

                @Override
                public void setDOMTreeManager(DOMTreeManager domTreeManager) {
                    handler.setDOMTreeManager(domTreeManager);
                }

                @Override
                public AffineTransform handleImage(RenderableImage image, Element imageElement, double x, double y, double width, double height, SVGGeneratorContext generatorContext) {
                    return handler.handleImage(image, imageElement, x, y, width, height, generatorContext);
                }

                @Override
                public AffineTransform handleImage(java.awt.image.RenderedImage image, Element imageElement, int x, int y, int width, int height, SVGGeneratorContext generatorContext) {
                    return handler.handleImage(image, imageElement, x, y, width, height, generatorContext);
                }

                @Override
                public AffineTransform handleImage(Image image, Element imageElement, int x, int y, int width, int height, SVGGeneratorContext generatorContext) {
                    return handler.handleImage(image, imageElement, x, y, width, height, generatorContext);
                }

                @Override
                public Element createElement(SVGGeneratorContext generatorContext) {
                    Element result = handler.createElement(generatorContext);
                    if (svgTraceability) {
                        result.setAttributeNS(DiagramPackage.eNS_URI, AnnotatedSVGShape.ATTR_NAME, currentId);
                    }
                    return result;
                }
            });
        }

        public void setCurrentId(String id) {
            ((AnnotatedSVGShape) this.shapeConverter).setCurrentId(id);
            this.currentId = id;
        }

        @Override
        public void drawString(String s, float x, float y) {
            super.drawString(s, x, y);
            if (svgTraceability) {
                Optional<Object> currentGroup = ReflectionHelper.getFieldValueWithoutException(domGroupManager, "currentGroup"); //$NON-NLS-1$
                if (currentGroup.isPresent() && currentGroup.get() instanceof Element) {
                    Element group = (Element) currentGroup.get();
                    if (group.getLastChild() instanceof Element) {
                        ((Element) group.getLastChild()).setAttributeNS(DiagramPackage.eNS_URI, AnnotatedSVGShape.ATTR_NAME, currentId);
                    }
                }
            }
        }

        /**
         * Add use tag to reference SVG image.
         * 
         * @param uri
         *            Image URI
         ** @param x
         *            the <i>x</i> coordinate.
         * @param y
         *            the <i>y</i> coordinate.
         * @param width
         *            the width of the rectangle.
         * @param height
         *            the height of the rectangle.
         */
        public void drawSVGReference(String uri, int x, int y, int width, int height) {
            Element useElement = getDOMFactory().createElementNS(SVG_NAMESPACE_URI, SVGConstants.SVG_USE_TAG);
            String uuid = SVGImageRegistry.registerUUID(uri);

            // Ignore the currentState of the Graphics by setting default attributes
            // These attributes can be overloaded if they are defined in the embedded svg
            useElement.setAttributeNS(null, SVG_FILL_ATTRIBUTE, SVGConstants.CSS_BLACK_VALUE);
            useElement.setAttributeNS(null, SVG_STROKE_ATTRIBUTE, SVGConstants.CSS_BLACK_VALUE);
            useElement.setAttributeNS(null, SVG_STROKE_WIDTH_ATTRIBUTE, "0"); //$NON-NLS-1$

            useElement.setAttributeNS(null, SVG_X_ATTRIBUTE, getGeneratorContext().doubleString(x));
            useElement.setAttributeNS(null, SVG_Y_ATTRIBUTE, getGeneratorContext().doubleString(y));
            useElement.setAttributeNS(null, SVG_WIDTH_ATTRIBUTE, getGeneratorContext().doubleString(width));
            useElement.setAttributeNS(null, SVG_HEIGHT_ATTRIBUTE, getGeneratorContext().doubleString(height));
            useElement.setAttributeNS(XMLConstants.XLINK_NAMESPACE_URI, XMLConstants.XLINK_HREF_QNAME, '#' + uuid);
            if (svgTraceability) {
                useElement.setAttributeNS(DiagramPackage.eNS_URI, AnnotatedSVGShape.ATTR_NAME, currentId);
            }
            domGroupManager.addElement(useElement);

            // remove clip-path attribute
            String attributeNS = useElement.getAttributeNS(null, SVG_CLIP_PATH_ATTRIBUTE);
            if (!attributeNS.isEmpty()) {
                useElement.removeAttributeNS(null, SVG_CLIP_PATH_ATTRIBUTE);
            }

        }

        /**
         * (non-Javadoc)
         * 
         * @see org.apache.batik.svggen.SVGGraphics2D#drawImage(java.awt.Image, int, int, int, int,
         *      java.awt.image.ImageObserver)
         */
        @Override
        public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
            boolean drawImage = super.drawImage(img, x, y, width, height, observer);

            // [578509] Improve the quality of SVG exports: remove clip-path attribute for Word to display base64 images
            // correctly
            if (drawImage && SiriusDiagramSVGGenerator.isEmbeddedSVGinSVGExportEnabled()) {
                Optional<SVGOMImageElement> lastImageElement = getLastSVGOMGElement();
                if (lastImageElement.isPresent()) {
                    String attributeNS = lastImageElement.get().getAttributeNS(null, SVG_CLIP_PATH_ATTRIBUTE);
                    if (!attributeNS.isEmpty()) {
                        lastImageElement.get().removeAttributeNS(null, SVG_CLIP_PATH_ATTRIBUTE);
                    }
                }

            }
            return drawImage;
        }

        private Optional<SVGOMImageElement> getLastSVGOMGElement() {
            Optional<Object> toplevelGroup = ReflectionHelper.getFieldValueWithoutException(domTreeManager, "topLevelGroup"); //$NON-NLS-1$
            if (toplevelGroup.isPresent() && toplevelGroup.get() instanceof Element) {
                return getLastSVGOMGElement((Element) toplevelGroup.get());
            }
            return Optional.empty();
        }

        private Optional<SVGOMImageElement> getLastSVGOMGElement(Element element) {
            for (Node n = element.getLastChild(); n != null; n = n.getPreviousSibling()) {
                if (n instanceof SVGOMGElement) {
                    for (Node child = n.getLastChild(); child != null; child = child.getPreviousSibling()) {
                        if (child instanceof SVGOMImageElement) {
                            return Optional.of((SVGOMImageElement) child);
                        }
                    }
                }
            }
            return Optional.empty();
        }

    }

    private Document doc;

    /**
     * Static initializer that will return an instance of <code>SiriusGraphicsSVG</code>
     *
     * @param viewPort
     *            the <code>Rectangle</code> area that is to be rendered.
     * @param svgTraceability
     *            whether we should add an attribute on SVG elements to keep the semantic target ID.
     * @return a new <code>SiriusGraphicsSVG</code> object.
     */
    public static SiriusGraphicsSVG getInstance(Rectangle viewPort, boolean svgTraceability) {
        CustomSVGGraphics2D svgGraphics;

        // Get the DOM implementation and create the document
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNameSpace = SVGDOMImplementation.SVG_NAMESPACE_URI;
        Document doc = impl.createDocument(svgNameSpace, "svg", null); //$NON-NLS-1$

        // Create the SVG Graphics Object
        svgGraphics = new CustomSVGGraphics2D(doc, svgTraceability);

        // Set the precision level to avoid NPEs (issue with Batik 1.5)
        svgGraphics.getGeneratorContext().setPrecision(3);

        // Set the Width and Height Attributes on the Root Element
        svgGraphics.setSVGCanvasSize(new Dimension(viewPort.width, viewPort.height));

        svgGraphics.setupCustomShapeConverter();

        return new SiriusGraphicsSVG(svgGraphics, doc, svgNameSpace, viewPort);
    }

    /**
     * @return <code>SVGGraphics2D</code> object
     */
    public SVGGraphics2D getSVGGraphics2D() {
        return (SVGGraphics2D) getGraphics2D();
    }

    public void setCurrentId(String id) {
        ((CustomSVGGraphics2D) getSVGGraphics2D()).setCurrentId(id);
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
     * GraphicsToGraphics2DAdaptor#drawRenderedImage(org.eclipse.gmf.runtime. draw2d.ui.render.RenderedImage,
     * org.eclipse.draw2d.geometry.Rectangle, org.eclipse.gmf.runtime.draw2d.ui.render.RenderingListener)
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

    /**
     * Draw SVG image reference with use tag.
     * 
     * @param uri
     *            Image URI
     * @param x1
     *            the x coordinate of the source
     * @param y1
     *            the y coordinate of the source
     * @param w1
     *            the width of the source
     * @param h1
     *            the height of the source
     * @param x2
     *            the x coordinate of the destination
     * @param y2
     *            the y coordinate of the destination
     * @param w2
     *            the width of the destination
     * @param h2
     *            the height of the destination
     */
    public void drawSVGReference(String uri, int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        Point trans = getTranslationOffset();
        x2 += trans.x;
        y2 += trans.y;

        checkState();
        ((CustomSVGGraphics2D) getGraphics2D()).drawSVGReference(uri, x2, y2, w2, h2);
    }

    /**
     * Draw SVG image in symbol tag.
     * 
     * @param uri
     *            String
     * @param uuid
     *            String
     */
    public void drawSymbolSVGImage(String uri, String uuid) {
        DOMTreeManager treeManager = getSVGGraphics2D().getDOMTreeManager();
        SVGGeneratorContext generatorContext = getSVGGraphics2D().getGeneratorContext();

        // create symbol tag
        Element symbolElement = generatorContext.getDOMFactory().createElementNS(SVGConstants.SVG_NAMESPACE_URI, SVGConstants.SVG_SYMBOL_TAG);
        symbolElement.setAttributeNS(null, XMLConstants.XML_ID_ATTRIBUTE, uuid);
        Optional<Element> referencedImage = SVGImageRegistry.getReferencedImage(uri);

        // batik bug: if imported node has def tag, document def tag is moved to imported node
        // save document def tag et re-add it after appendGroup
        List<Element> cloneDefElements = getDefElement(treeManager);

        // add svg tag in symbol
        if (referencedImage.isPresent()) {
            Element toAppend = (Element) ((AbstractDocument) doc).importNode(referencedImage.get(), true, true);
            symbolElement.appendChild(toAppend);
        }
        // add symbol tag to document
        treeManager.appendGroup(symbolElement, null);

        // batik bug: re-add def tag after import if necessary
        moveDefinitionSet(treeManager, cloneDefElements);
    }

    /**
     * If toplevelGroup lost its definition set, add cloneDefElements.
     * 
     * @param treeManager
     * @param cloneDefElements
     */
    protected void moveDefinitionSet(DOMTreeManager treeManager, List<Element> cloneDefElements) {
        if (!cloneDefElements.isEmpty()) {
            Optional<Object> toplevelGroup = ReflectionHelper.getFieldValueWithoutException(treeManager, "topLevelGroup"); //$NON-NLS-1$
            if (toplevelGroup.isPresent() && toplevelGroup.get() instanceof Element) {
                if (!hasDefinitionSet((Element) toplevelGroup.get())) {
                    cloneDefElements.forEach(def -> {
                        treeManager.appendGroup(def, null);
                    });
                }
            }
        }
    }

    /**
     * @param treeManager
     *            DOMTreeManager
     * @return cloned definition set
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected List<Element> getDefElement(DOMTreeManager treeManager) {
        List definitionSet = treeManager.getGraphicContextConverter().getDefinitionSet();
        List<Element> result = new ArrayList<>();
        definitionSet.forEach(def -> {
            if (def instanceof Element) {
                Node cloneNode = ((Element) def).cloneNode(true);
                if (cloneNode instanceof Element) {
                    result.add((Element) cloneNode);
                }

            }
        });
        if (!result.isEmpty()) {
            // remove definition set from parent
            treeManager.getDefinitionSet();
        }
        return result;
    }

    /**
     * @param topLevelGroup
     *            Element
     * @return topLevelGroup has definition set
     */
    protected boolean hasDefinitionSet(Element topLevelGroup) {
        for (int i = 0; i < topLevelGroup.getChildNodes().getLength(); i++) {
            org.w3c.dom.Node node = topLevelGroup.getChildNodes().item(i);
            if (node instanceof SVGOMDefsElement) {
                return true;
            }

        }
        return false;
    }

}
// CHECKSTYLE:ON
