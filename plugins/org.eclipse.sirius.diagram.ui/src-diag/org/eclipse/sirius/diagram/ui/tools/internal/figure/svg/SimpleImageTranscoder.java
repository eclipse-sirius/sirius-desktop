/**
 * Copyright (c) 2008, 2016, 2021 Borland Software Corporation and others.
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
 *    Laurent Redor (Obeo) <laurent.redor@obeo.fr>  - Extract from plug-in org.eclipse.gmf.runtime.lite.svg
 */
package org.eclipse.sirius.diagram.ui.tools.internal.figure.svg;

import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.apache.batik.bridge.EmbededExternalResourceSecurity;
import org.apache.batik.bridge.ExternalResourceSecurity;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.gvt.renderer.StaticRenderer;
import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.ParsedURL;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGFigure;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.w3c.dom.Document;

//CHECKSTYLE:OFF
public class SimpleImageTranscoder extends SVGAbstractTranscoder {

    private BufferedImage bufferedImage;

    private Document document;

    private int canvasWidth = -1, canvasHeight = -1;

    private RenderingHints renderingHints;

    public SimpleImageTranscoder(Document document) {
        this.document = document;
        renderingHints = new RenderingHints(null);
    }
    
    @Override
    protected UserAgent createUserAgent() {
        return new UserAgentAdapter() {
            @Override
            public ExternalResourceSecurity getExternalResourceSecurity(ParsedURL resourceURL, ParsedURL docURL) {
                return new EmbededExternalResourceSecurity(resourceURL);
            }
        };
    }

    public final Document getDocument() {
        return document;
    }

    public int getImageHeight() {
        int height = 0;
        if (canvasHeight == -1) {
            height = getBufferedImage().getHeight();
        } else {
            height = canvasHeight;
        }
        return height;
    }

    public int getImageWidth() {
        int width = 0;
        if (canvasWidth == -1) {
            width = getBufferedImage().getWidth();
        } else {
            width = canvasWidth;
        }
        return width;
    }

    public int getImageAlphaValue(int x, int y) {
        BufferedImage bufferedImage = getBufferedImage();
        if (bufferedImage != null && bufferedImage.getWidth() >= x && bufferedImage.getHeight() >= y) {
            int[] result = bufferedImage.getAlphaRaster().getPixel(x, y, new int[1]);
            return result[0];
        }
        return 255;
    }

    public double getAspectRatio() {
        if (canvasHeight == -1 || canvasWidth == -1) {
            BufferedImage img = getBufferedImage();
            if (img != null) {
                int width = img.getWidth();
                int height = img.getHeight();
                return (double) width / (double) height;
            }
        }
        return (double) canvasWidth / (double) canvasHeight;
    }

    public void contentChanged() {
        bufferedImage = null;
    }

    @Override
    protected void transcode(Document document, String uri, TranscoderOutput output) throws TranscoderException {
        super.transcode(document, uri, output);
        int w = (int) (width + 0.5);
        int h = (int) (height + 0.5);
        ImageRenderer renderer = createImageRenderer();
        renderer.updateOffScreen(w, h);
        renderer.setTransform(curTxf);
        renderer.setTree(this.root);
        this.root = null;
        try {
            Shape raoi = new Rectangle2D.Float(0, 0, width, height);
            renderer.repaint(curTxf.createInverse().createTransformedShape(raoi));
            bufferedImage = renderer.getOffScreen();
        } catch (Exception ex) {
            throw new TranscoderException(ex);
        }
    }

    private ImageRenderer createImageRenderer() {
        StaticRenderer renderer = new StaticRenderer();
        renderer.getRenderingHints().add(renderingHints);
        return renderer;
    }

    public Image render(SVGFigure fig, Rectangle clientArea, Graphics graphics, boolean scaleImage) {
        Image result = null;
        if (document != null) {
            if (scaleImage && graphics != null) {
                PrecisionRectangle scaledArea = new PrecisionRectangle(clientArea);
                scaledArea.performScale(graphics.getAbsoluteScale());
                setCanvasSize(scaledArea.width(), scaledArea.height());
            } else {
                setCanvasSize(clientArea.width(), clientArea.height());
            }
            updateRenderingHints(graphics);
            BufferedImage awtImage = getBufferedImage();
            if (awtImage != null) {
                result = SVGUtils.toSWT(Display.getCurrent(), awtImage);
            }
        }
        return result;
    }

    private void updateImage() {
        if (document == null) {
            return;
        }
        try {
            if (canvasWidth > 0) {
                addTranscodingHint(ImageTranscoder.KEY_WIDTH, new Float(canvasWidth));
            } else {
                removeTranscodingHint(ImageTranscoder.KEY_WIDTH);
            }
            if (canvasHeight > 0) {
                addTranscodingHint(ImageTranscoder.KEY_HEIGHT, new Float(canvasHeight));
            } else {
                removeTranscodingHint(ImageTranscoder.KEY_HEIGHT);
            }
            removeTranscodingHint(ImageTranscoder.KEY_AOI);
            transcode(new TranscoderInput(document), new TranscoderOutput());
        } catch (TranscoderException e) {
            DiagramPlugin.getDefault().logError(Messages.SimpleImageTranscoder_svgImageTranscodingError, e);
        }
    }

    private void setCanvasSize(int width, int height) {
        if (this.canvasWidth == width && this.canvasHeight == height) {
            return;
        }
        this.canvasWidth = width;
        this.canvasHeight = height;
        contentChanged();
    }

    private BufferedImage getBufferedImage() {
        if (bufferedImage == null) {
            updateImage();
        }
        return bufferedImage;
    }

    private void updateRenderingHints(Graphics graphics) {
        Object antiAliasHint = SVGUtils.getAntialiasHint(graphics);
        if (renderingHints.get(RenderingHints.KEY_ANTIALIASING) != antiAliasHint) {
            renderingHints.put(RenderingHints.KEY_ANTIALIASING, antiAliasHint);
            contentChanged();
        }
        Object textAntiAliasHint = SVGUtils.getTextAntialiasHint(graphics);
        if (renderingHints.get(RenderingHints.KEY_TEXT_ANTIALIASING) != textAntiAliasHint) {
            renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, textAntiAliasHint);
            contentChanged();
        }
    }
}
// CHECKSTYLE:ON
