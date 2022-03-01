/**
 * Copyright (c) 2008, 2022 Borland Software Corporation and others.
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 *    Obeo - Adaptations.
 */
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.WeakHashMap;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGOMDocument;
import org.apache.batik.util.MimeTypeConstants;
import org.apache.batik.util.ParsedURL;
import org.apache.batik.util.XMLResourceDescriptor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SimpleImageTranscoder;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SVGImageRegistry;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusDiagramSVGGenerator;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusGraphicsSVG;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusRenderedMapModeGraphics;
import org.eclipse.sirius.ext.draw2d.figure.ITransparentFigure;
import org.eclipse.sirius.ext.draw2d.figure.ImageFigureWithAlpha;
import org.eclipse.sirius.ext.draw2d.figure.StyledFigure;
import org.eclipse.sirius.ext.draw2d.figure.TransparentFigureGraphicsModifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;

//CHECKSTYLE:OFF
public class SVGFigure extends Figure implements StyledFigure, ITransparentFigure, ImageFigureWithAlpha {
    /**
     * Cache of pre-rendered images.
     */
    private static class ImageCache {
        /**
         * The maximum size of the cache, in bytes.
         */
        private static final int MAX_WEIGHT;

        static {
            String s = System.getProperty("org.eclipse.sirius.diagram.ui.svg.maxCacheSizeMB"); //$NON-NLS-1$
            int mb;
            try {
                mb = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                mb = 50;
            }
            MAX_WEIGHT = mb * 1024 * 1024;
        }

        /**
         * Computes the weight of a rendered image, as the number of bytes occupied by the raw raster data (assumes 4
         * 8-bit channels).
         */
        private static final class ImageWeigher implements Weigher<String, Image> {
            @Override
            public int weigh(String key, Image value) {
                if (value != null) {
                    synchronized (value) {
                        if (!value.isDisposed()) {
                            org.eclipse.swt.graphics.Rectangle bounds = value.getBounds();
                            return bounds.width * bounds.height * 4;
                        }
                    }
                }
                return 0;
            }
        }

        private final class ImageRemovalListener implements RemovalListener<String, Image> {
            @Override
            public void onRemoval(RemovalNotification<String, Image> notification) {
                Image img = notification.getValue();
                synchronized (img) {
                    img.dispose();
                }
            }
        }

        /**
         * The rendered bitmaps, organized by key..
         */
        private final Cache<String, Image> images = CacheBuilder.newBuilder().maximumWeight(ImageCache.MAX_WEIGHT).removalListener(new ImageRemovalListener()).weigher(new ImageWeigher()).build();

        /**
         * Get the image cached or create new one and cache it.
         *
         * @param key
         *            the key
         * @param clientArea
         *            the client area
         * @param graphics
         *            the graphical context
         * @return an image store in a cache
         */
        public synchronized Image getImage(SVGFigure fig, Rectangle clientArea, Graphics graphics) {
            String key = fig.getKey(graphics);
            Image result = images.getIfPresent(key);
            if (result == null || result.isDisposed()) {
                if (fig.transcoder != null) {
                    result = fig.transcoder.render(fig, clientArea, graphics, CACHE_SCALED_IMAGES);
                }
                if (result != null) {
                    images.put(key, result);
                }
            }
            return result;
        }

        /**
         * Remove all entries whose key begins with the given key. Remove from the document map, the entries with the
         * given keys to force to re-read the file.
         *
         * @param documentKey
         *            the document key.
         * @return true of something was removed.
         */
        public synchronized boolean doRemoveFromCache(String documentKey) {
            if (!StringUtil.isEmpty(documentKey)) {
                boolean remove = false;
                Collection<String> keyToRemove = new ArrayList<>();
                for (String key : images.asMap().keySet()) {
                    if (key.startsWith(documentKey)) {
                        keyToRemove.add(key);
                    }
                }

                for (String toRemove : keyToRemove) {
                    images.invalidate(toRemove);
                    remove = true;
                }
                return remove;
            }
            return false;
        }
    }

    private static final ImageCache CACHE = new ImageCache();

    private static final boolean CACHE_ENABLED = true;

    private static final boolean CACHE_SCALED_IMAGES = true;

    /**
     * The uri of the image to display when the file has not been found.
     */
    protected static final String IMAGE_NOT_FOUND_URI = MessageFormat.format("platform:/plugin/{0}/images/NotFound.svg", DiagramUIPlugin.getPlugin().getSymbolicName()); //$NON-NLS-1$

    /**
     * Key separator.
     */
    protected static final String SEPARATOR = "|"; //$NON-NLS-1$

    private String uri;

    private int viewpointAlpha = ITransparentFigure.DEFAULT_ALPHA;

    private boolean transparent;

    private boolean failedToLoadDocument;

    private SimpleImageTranscoder transcoder;

    /** The image ratio (width/height); computed during a SVG change detected {@link #contentChanged()}. */
    private double initialAspectRatio = 1;

    /**
     * True if the SVG document contains an attribute "viewBox", false otherwise. This attribute is not handled by the
     * current version of batik used by Sirius (1.6). And it causes regression, shrinked image, since the fix of bug
     * 463051.<BR>
     * This field is set at each {@link #contentChanged()}.
     */
    protected boolean modeWithViewBox;

    protected static WeakHashMap<String, Document> documentsMap = new WeakHashMap<String, Document>();

    public SVGFigure() {
        this.setLayoutManager(new XYLayout());
    }

    @Override
    public int getSiriusAlpha() {
        return viewpointAlpha;
    }

    @Override
    public boolean isTransparent() {
        return transparent;
    }

    @Override
    public void setSiriusAlpha(int alpha) {
        this.viewpointAlpha = alpha;
    }

    @Override
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    @Override
    public int getImageHeight() {
        return (transcoder != null) ? transcoder.getImageHeight() : 0;
    }

    @Override
    public int getImageWidth() {
        return (transcoder != null) ? transcoder.getImageWidth() : 0;
    }

    /**
     * Get the original SVG image ratio (width/height).
     *
     * @return the original SVG image ratio (width/height).
     */
    public double getImageAspectRatio() {
        return initialAspectRatio;
    }

    @Override
    public int getImageAlphaValue(int x, int y) {
        return (transcoder != null) ? transcoder.getImageAlphaValue(x, y) : 255;
    }

    public final String getURI() {
        return uri;
    }

    public final void setURI(String uri) {
        setURI(uri, true);
    }

    public void setURI(String uri, boolean loadOnDemand) {
        this.uri = uri;
        transcoder = null;
        failedToLoadDocument = false;
        if (loadOnDemand) {
            loadDocument();
        }
    }

    private void loadDocument() {
        transcoder = null;
        failedToLoadDocument = true;
        if (uri == null) {
            return;
        }

        String documentKey = getDocumentKey();
        Document document;
        if (documentsMap.containsKey(documentKey)) {
            document = documentsMap.get(documentKey);
        } else {
            document = createDocument();
            documentsMap.put(documentKey, document);
        }

        if (document != null) {
            transcoder = initTranscoder(document);
            failedToLoadDocument = false;
        }
    }

    private Document createDocument() {
        String parser = Optional.ofNullable(XMLResourceDescriptor.getXMLParserClassName()).orElse("org.apache.xerces.parsers.SAXParser"); //$NON-NLS-1$
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser) {
            /*
             * This method is exactly same method as @see
             * org.apache.batik.anim.dom.SAXSVGDocumentFactory#createDocument(java.lang.String) but with the workaround
             * proposed in https://issues.apache.org/jira/browse/BATIK-1143 by adding a try-with-resource to close the
             * stream. Without this, it is not possible to delete the file (at least under Windows, see test
             * org.eclipse.sirius.tests.swtbot.SetStyleToWorkspaceImageTests)
             */
            @Override
            public Document createDocument(String uri) throws IOException {
                ParsedURL purl = new ParsedURL(uri);

                try (InputStream is = purl.openStream(MimeTypeConstants.MIME_TYPES_SVG_LIST.iterator())) {
                    uri = purl.getPostConnectionURL();

                    InputSource isrc = new InputSource(is);

                    // now looking for a charset encoding in the content type such
                    // as "image/svg+xml; charset=iso8859-1" this is not official
                    // for image/svg+xml yet! only for text/xml and maybe
                    // for application/xml
                    String contentType = purl.getContentType();
                    int cindex = -1;
                    if (contentType != null) {
                        contentType = contentType.toLowerCase();
                        cindex = contentType.indexOf(HTTP_CHARSET);
                    }

                    String charset = null;
                    if (cindex != -1) {
                        int i = cindex + HTTP_CHARSET.length();
                        int eqIdx = contentType.indexOf('=', i);
                        if (eqIdx != -1) {
                            eqIdx++; // no one is interested in the equals sign...

                            // The patch had ',' as the terminator but I suspect
                            // that is the delimiter between possible charsets,
                            // but if another 'attribute' were in the accept header
                            // charset would be terminated by a ';'. So I look
                            // for both and take to closer of the two.
                            int idx = contentType.indexOf(',', eqIdx);
                            int semiIdx = contentType.indexOf(';', eqIdx);
                            if ((semiIdx != -1) && ((semiIdx < idx) || (idx == -1)))
                                idx = semiIdx;
                            if (idx != -1)
                                charset = contentType.substring(eqIdx, idx);
                            else
                                charset = contentType.substring(eqIdx);
                            charset = charset.trim();
                            isrc.setEncoding(charset);
                        }
                    }

                    isrc.setSystemId(uri);

                    SVGOMDocument doc = (SVGOMDocument) super.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", uri, isrc); //$NON-NLS-1$
                    doc.setParsedURL(new ParsedURL(uri));
                    doc.setDocumentInputEncoding(charset);
                    doc.setXmlStandalone(isStandalone);
                    doc.setXmlVersion(xmlVersion);

                    return doc;
                }
            }
        };
        return createDocument(factory, false);
    }

    private Document createDocument(SAXSVGDocumentFactory factory, boolean forceClassLoader) {
        if (Messages.BundledImageShape_idMissing.equals(uri)) {
            DiagramPlugin.getDefault().logError(Messages.SVGFigure_usingInvalidBundledImageShape);
        } else {
            if (forceClassLoader) {
                Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
            }
            try {
                return factory.createDocument(uri);
            } catch (IOException e) {
                boolean saxParserNotFound = e.getMessage() != null && e.getMessage().contains("SAX2 driver class org.apache.xerces.parsers.SAXParser not found"); //$NON-NLS-1$
                if (!forceClassLoader && saxParserNotFound) {
                    return createDocument(factory, true);
                } else {
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.SVGFigure_loadError, uri), e);
                }
            } finally {
                if (forceClassLoader) {
                    Thread.currentThread().setContextClassLoader(null);
                }
            }
        }
        return null;
    }

    protected final Document getDocument() {
        if (failedToLoadDocument) {
            return null;
        }
        if (transcoder == null) {
            loadDocument();
        }
        return transcoder == null ? null : transcoder.getDocument();
    }

    /**
     * Should be called when SVG document has been changed. It will be re-rendered and figure will be repainted.
     */
    public void contentChanged() {
        Document document = getDocument();
        if (document != null) {
            modeWithViewBox = false;
            for (int i = 0; i < document.getChildNodes().getLength(); i++) {
                org.w3c.dom.Node node = document.getChildNodes().item(i);
                if (node instanceof Element) {
                    String viewBoxValue = ((Element) node).getAttribute("viewBox"); //$NON-NLS-1$
                    if (!StringUtil.isEmpty(viewBoxValue)) {
                        // stretch the image is not supported as the current version of Batif used does not handled it
                        // (org.apache.batik.dom.svg.SVGOMSVGElement.getViewBox()).
                        modeWithViewBox = true;
                    }
                }

            }
        }
        if (transcoder != null) {
            transcoder.contentChanged();
            // Each time that SVG document is changed, we store the real ratio of the image (width/height); the
            // transcoder aspect ratio. Indeed, after the transcoder aspect ratio will be
            // the previous displayed ratio and not the real ratio of the image.
            initialAspectRatio = transcoder.getAspectRatio();
        }
        repaint();
    }

    protected SimpleImageTranscoder getTranscoder() {
        return transcoder;
    }

    protected SimpleImageTranscoder initTranscoder(Document document) {
        return new SimpleImageTranscoder(document);
    }

    /**
     * The key used to store the document.
     * 
     * @return the key.
     */
    protected String getDocumentKey() {
        return uri;
    }

    /**
     * Compute a key for this figure. This key is used to store in cache the corresponding
     * {@link org.eclipse.swt.graphics.Image}.
     *
     * The key must begin by the document key.
     *
     * @return The key corresponding to this BundleImageFigure.
     */
    protected String getKey(Graphics graphics) {
        int aaText = SWT.DEFAULT;
        try {
            if (graphics != null) {
                aaText = graphics.getTextAntialias();
            }
        } catch (Exception e) {
            // not supported
        }

        StringBuffer result = new StringBuffer();
        result.append(getDocumentKey());
        result.append(SVGFigure.SEPARATOR);
        result.append(getSiriusAlpha());
        result.append(SVGFigure.SEPARATOR);
        result.append(aaText);
        result.append(SVGFigure.SEPARATOR);
        Rectangle r = new PrecisionRectangle(getClientArea());
        if (CACHE_SCALED_IMAGES && graphics != null) {
            r.performScale(graphics.getAbsoluteScale());
        }
        result.append(r.width());
        result.append(SVGFigure.SEPARATOR);
        result.append(r.height());

        return result.toString();
    }

    @Override
    protected void paintFigure(Graphics graphics) {
        TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, graphics);
        modifier.pushState();
        Rectangle svgArea = getClientArea();
        if (CACHE_SCALED_IMAGES) {
            Rectangle scaledArea = new PrecisionRectangle(svgArea);
            scaledArea.performScale(graphics.getAbsoluteScale());
            // specific case for SVG export
            if (SiriusDiagramSVGGenerator.isEmbeddedSVGinSVGExportEnabled() && graphics instanceof SiriusRenderedMapModeGraphics
                    && ((SiriusRenderedMapModeGraphics) graphics).getGraphics() instanceof SiriusGraphicsSVG) {
                paintSVGReference(graphics, svgArea, scaledArea);
            } else {
                // paint rendered bitmap
                paintRenderedBitmap(graphics, svgArea, scaledArea);
            }
        } else {
            Image image = getImage(svgArea, graphics);
            if (image != null) {
                synchronized (image) {
                    if (!image.isDisposed()) {
                        graphics.drawImage(image, svgArea.x(), svgArea.y());
                    }
                }
            }
        }
        modifier.popState();
    }

    /**
     * Paint rendered bitmap.
     * 
     * @param graphics
     *            Graphics
     * @param svgArea
     *            Rectangle
     * @param scaledArea
     *            Rectangle
     */
    protected void paintRenderedBitmap(Graphics graphics, Rectangle svgArea, Rectangle scaledArea) {
        Image image = getImage(svgArea, graphics);
        if (image != null) {
            synchronized (image) {
                if (!image.isDisposed()) {
                    if (modeWithViewBox) {
                        graphics.drawImage(image, 0, 0, scaledArea.width(), scaledArea.height(), svgArea.x(), svgArea.y(), svgArea.width(), svgArea.height());
                    } else {
                        // The scaled width (and height) must not be greater than area width (and height). So
                        // depending
                        // on the initialAspectRatio and zoom factor, the reference can be the width or the height.
                        double scaledWidth = svgArea.width() * graphics.getAbsoluteScale();
                        double scaledHeight = scaledWidth / getImageAspectRatio();
                        if ((scaledHeight / graphics.getAbsoluteScale()) > svgArea.height()) {
                            // The height must be the reference to avoid an IllegalArgumentException later.
                            scaledHeight = svgArea.height() * graphics.getAbsoluteScale();
                            scaledWidth = scaledHeight * getImageAspectRatio();
                        }
                        graphics.drawImage(image, 0, 0, (int) scaledWidth, (int) scaledHeight, svgArea.x(), svgArea.y(), svgArea.width(), svgArea.height());
                    }
                }
            }
        }
    }

    /**
     * Paint SVG reference using use tag.
     * 
     * @param graphics
     *            Graphics
     * @param svgArea
     *            Rectangle
     * @param scaledArea
     *            Rectangle
     */
    protected void paintSVGReference(Graphics graphics, Rectangle svgArea, Rectangle scaledArea) {
        String imageRegistryURI = computeImageKey(svgArea.width, svgArea.height);
        registerSVGDocument(imageRegistryURI, getTranscoder().getDocument(), svgArea);
        paintSVGReference(graphics, imageRegistryURI, svgArea, scaledArea);
    }

    /**
     * @param graphics
     *            Graphics
     * @param imageRegistryURI
     *            String
     * @param svgArea
     *            Rectangle
     * @param scaledArea
     *            Rectangle
     */
    protected void paintSVGReference(Graphics graphics, String imageRegistryURI, Rectangle svgArea, Rectangle scaledArea) {
        if (modeWithViewBox) {
            ((SiriusRenderedMapModeGraphics) graphics).drawSVGReference(imageRegistryURI, 0, 0, scaledArea.width(), scaledArea.height(), svgArea.x(), svgArea.y(), svgArea.width(), svgArea.height());
        } else {
            double scaledWidth = svgArea.width() * graphics.getAbsoluteScale();
            double scaledHeight = scaledWidth / getImageAspectRatio();
            if ((scaledHeight / graphics.getAbsoluteScale()) > svgArea.height()) {
                // The height must be the reference to avoid an IllegalArgumentException later.
                scaledHeight = svgArea.height() * graphics.getAbsoluteScale();
                scaledWidth = scaledHeight * getImageAspectRatio();
            }
            ((SiriusGraphicsSVG) graphics).drawSVGReference(imageRegistryURI, 0, 0, (int) scaledWidth, (int) scaledHeight, svgArea.x(), svgArea.y(), svgArea.width(), svgArea.height());
        }
    }

    /**
     * Compute image key for registry.
     * 
     * @param params
     *            Obect
     * @return image key for registry.
     */
    protected String computeImageKey(Object... params) {
        StringBuffer imageRegistryURI = new StringBuffer();
        imageRegistryURI.append(this.getDocumentKey());
        imageRegistryURI.append(SVGFigure.SEPARATOR);
        // add width and height for viewbox
        if (params.length >= 2) {
            imageRegistryURI.append(params[0]);
            imageRegistryURI.append(SVGFigure.SEPARATOR);
            imageRegistryURI.append(params[1]);
        }
        return imageRegistryURI.toString();
    }

    /**
     * Add SVG document in registry.
     * 
     * @param imageRegistryKey
     *            String
     * @param document
     *            Document
     * @param params
     *            Object
     */
    protected void registerSVGDocument(String imageRegistryKey, Document document, Object... params) {
        if (params.length > 0 && params[0] instanceof Rectangle) {
            SVGImageRegistry.registerSVGDocument(imageRegistryKey.toString(), SVGImageRegistry.getSVGDocument(getTranscoder().getDocument(), (Rectangle) params[0]));
        }
    }

    /**
     * Get the image cached or create new one and cache it.
     *
     * @param clientArea
     *            the client area
     * @param graphics
     *            the graphical context
     * @return an image store in a cache
     */
    protected Image getImage(Rectangle clientArea, Graphics graphics) {
        if (CACHE_ENABLED) {
            return CACHE.getImage(this, clientArea, graphics);
        } else if (transcoder != null) {
            return transcoder.render(this, clientArea, graphics, CACHE_SCALED_IMAGES);
        } else {
            return null;
        }
    }

    /**
     * Remove all entries whose key begins with the given key. Remove from the document map, the entries with the given
     * keys to force to re-read the file.
     *
     * @param documentKey
     *            the document key.
     * @return true of something was removed.
     */
    protected static boolean doRemoveFromCache(final String documentKey) {
        if (!StringUtil.isEmpty(documentKey)) {
            return CACHE.doRemoveFromCache(documentKey) || SVGFigure.documentsMap.remove(documentKey) != null;
        }
        return false;
    }

    // CHECKSTYLE:ON
}
