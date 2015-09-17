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

import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.WeakHashMap;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.InferringNamespaceContext;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SVGUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SimpleImageTranscoder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//CHECKSTYLE:OFF
public class SVGFigure extends Figure {

    private String uri;

    private boolean failedToLoadDocument, specifyCanvasWidth = true, specifyCanvasHeight = true;

    private SimpleImageTranscoder transcoder;

    protected static WeakHashMap<String, Document> documentsMap = new WeakHashMap<String, Document>();

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
     * The key used to store the document.
     * 
     * @return the key.
     */
    protected String getDocumentKey() {
        return uri;
    }

    /**
     * Returns true if document was loaded without errors; tries to load
     * document if needed.
     */
    public final boolean checkContentAvailable() {
        return getDocument() != null;
    }

    private XPath getXPath() {
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(new InferringNamespaceContext(getDocument().getDocumentElement()));
        return xpath;
    }

    /**
     * Executes XPath query over the SVG document.
     */
    protected final NodeList getNodes(String query) {
        Document document = getDocument();
        if (document != null) {
            try {
                return (NodeList) getXPath().evaluate(query, document, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    protected void paintFigure(Graphics graphics) {
        super.paintFigure(graphics);
        Document document = getDocument();
        if (document == null) {
            return;
        }
        Image image = null;
        try {
            Rectangle r = getClientArea();
            transcoder.setCanvasSize(specifyCanvasWidth ? r.width : -1, specifyCanvasHeight ? r.height : -1);
            updateRenderingHints(graphics);
            BufferedImage awtImage = transcoder.getBufferedImage();
            if (awtImage != null) {
                image = SVGUtils.toSWT(Display.getCurrent(), awtImage);
                graphics.drawImage(image, r.x, r.y);
            }
        } finally {
            if (image != null) {
                image.dispose();
            }
        }
    }

    protected void updateRenderingHints(Graphics graphics) {
        {
            int aa = SWT.DEFAULT;
            try {
                aa = graphics.getAntialias();
            } catch (Exception e) {
                // not supported
            }
            Object aaHint;
            if (aa == SWT.ON) {
                aaHint = RenderingHints.VALUE_ANTIALIAS_ON;
            } else if (aa == SWT.OFF) {
                aaHint = RenderingHints.VALUE_ANTIALIAS_OFF;
            } else {
                aaHint = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
            }
            if (transcoder != null && transcoder.getRenderingHints().get(RenderingHints.KEY_ANTIALIASING) != aaHint) {
                transcoder.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, aaHint);
                transcoder.contentChanged();
            }
        }
        {
            int aa = SWT.DEFAULT;
            try {
                aa = graphics.getTextAntialias();
            } catch (Exception e) {
                // not supported
            }
            Object aaHint;
            if (aa == SWT.ON) {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
            } else if (aa == SWT.OFF) {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
            } else {
                aaHint = RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT;
            }
            if (transcoder != null && transcoder.getRenderingHints().get(RenderingHints.KEY_TEXT_ANTIALIASING) != aaHint) {
                transcoder.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, aaHint);
                transcoder.contentChanged();
            }
        }
    }

    public final Rectangle2D getAreaOfInterest() {
        getDocument();
        return transcoder == null ? null : transcoder.getCanvasAreaOfInterest();
    }

    public void setAreaOfInterest(Rectangle2D value) {
        getDocument();
        if (transcoder != null) {
            transcoder.setCanvasAreaOfInterest(value);
        }
        repaint();
    }

    public final boolean isSpecifyCanvasWidth() {
        return specifyCanvasWidth;
    }

    public void setSpecifyCanvasWidth(boolean specifyCanvasWidth) {
        this.specifyCanvasWidth = specifyCanvasWidth;
        contentChanged();
    }

    public final boolean isSpecifyCanvasHeight() {
        return specifyCanvasHeight;
    }

    public void setSpecifyCanvasHeight(boolean specifyCanvasHeight) {
        this.specifyCanvasHeight = specifyCanvasHeight;
        contentChanged();
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

    protected boolean getSpecifyCanvasWidth() {
        return specifyCanvasWidth;
    }

    protected boolean getSpecifyCanvasHeight() {
        return specifyCanvasHeight;
    }
    // CHECKSTYLE:ON
}
