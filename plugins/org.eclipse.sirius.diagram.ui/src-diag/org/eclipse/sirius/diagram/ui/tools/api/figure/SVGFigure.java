/**
 * Copyright (c) 2008, 2015 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 *    Obeo - Adaptations.
 */
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.WeakHashMap;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.TransparentFigureGraphicsModifier;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SVGUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SimpleImageTranscoder;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;

//CHECKSTYLE:OFF
public class SVGFigure extends Figure implements StyledFigure, ITransparentFigure, ImageFigureWithAlpha {
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
            transcoder = new SimpleImageTranscoder(document);
            failedToLoadDocument = false;
        }
    }

    private Document createDocument() {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        return createDocument(factory, false);
    }

    private Document createDocument(SAXSVGDocumentFactory factory, boolean forceClassLoader) {
        if (forceClassLoader) {
            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        }
        try {
            return factory.createDocument(uri);
        } catch (IOException e) {
            boolean saxParserNotFound = e.getMessage() != null && e.getMessage().contains("SAX2 driver class org.apache.xerces.parsers.SAXParser not found"); //$NON-NLS-1$
            if (!forceClassLoader && saxParserNotFound && Thread.currentThread().getContextClassLoader() == null) {
                return createDocument(factory, true);
            } else {
                DiagramPlugin.getDefault().logError(Messages.SVGFigure_loadError, e);
            }
        } finally {
            if (forceClassLoader) {
                Thread.currentThread().setContextClassLoader(null);
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
     * Should be called when SVG document has been changed. It will be
     * re-rendered and figure will be repainted.
     */
    public void contentChanged() {
        getDocument();
        if (transcoder != null) {
            transcoder.contentChanged();
        }
        repaint();
    }

    protected SimpleImageTranscoder getTranscoder() {
        return transcoder;
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
     * Compute a key for this figure. This key is used to store in cache the
     * corresponding {@link org.eclipse.swt.graphics.Image}.
     *
     * The key must begin by the document key.
     *
     * @return The key corresponding to this BundleImageFigure.
     */
    protected String getKey() {
        StringBuffer result = new StringBuffer();
        result.append(getDocumentKey());
        result.append(AbstractCachedSVGFigure.SEPARATOR);
        result.append(getSiriusAlpha());
        result.append(AbstractCachedSVGFigure.SEPARATOR);
        return result.toString();
    }

    @Override
    protected void paintFigure(Graphics graphics) {
        TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, graphics);
        modifier.pushState();
        Rectangle r = getClientArea();
        Image image = getImage(r, graphics);
        if (image != null) {
            graphics.drawImage(image, r.x, r.y);
        }
        modifier.popState();
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
        return render(this, clientArea, graphics);
    }

    protected static Image render(SVGFigure fig, Rectangle clientArea, Graphics graphics) {
        Image result = null;
        if (fig.getDocument() != null) {
            fig.getTranscoder().setCanvasSize(clientArea.width, clientArea.height);
            fig.getTranscoder().updateRenderingHints(graphics);
            BufferedImage awtImage = fig.getTranscoder().getBufferedImage();
            if (awtImage != null) {
                result = SVGUtils.toSWT(Display.getCurrent(), awtImage);
            }
        }
        return result;
    }
    // CHECKSTYLE:ON
}
