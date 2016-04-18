/******************************************************************************
 * Copyright (c) 2002, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramImageGenerator;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.graphics.RenderedMapModeGraphics;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class override to use a specific GraphicsToGraphics2DAdaptor that handles the
 * gradient.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusDiagramImageGenerator extends DiagramImageGenerator {

    /**
     * Default constructor.
     * 
     * @param dgrmEP
     *            the diagram editpart
     */
    public SiriusDiagramImageGenerator(DiagramEditPart dgrmEP) {
        super(dgrmEP);
    }

    /**
     * Same method as parent but using a
     * {@link SiriusGraphicsToGraphics2DAdaptor} instead of a
     * {@link org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.graphics.GraphicsToGraphics2DAdaptor}
     * .
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramImageGenerator#
     *      createAWTImageForParts(java.util.List,
     *      org.eclipse.swt.graphics.Rectangle)
     */
    @Override
    public java.awt.Image createAWTImageForParts(List selectedObjects, org.eclipse.swt.graphics.Rectangle sourceRect) {

        BufferedImage awtImage = null;
        IMapMode mm = getMapMode();
        PrecisionRectangle rect = new PrecisionRectangle();
        rect.setX(sourceRect.x);
        rect.setY(sourceRect.y);
        rect.setWidth(sourceRect.width);
        rect.setHeight(sourceRect.height);

        mm.LPtoDP(rect);

        awtImage = new BufferedImage((int) Math.round(rect.preciseWidth), (int) Math.rint(rect.preciseHeight), BufferedImage.TYPE_4BYTE_ABGR_PRE);

        Graphics2D g2d = awtImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, awtImage.getWidth(), awtImage.getHeight());

        // Check anti-aliasing preference
        IPreferenceStore preferenceStore = (IPreferenceStore) getDiagramEditPart().getDiagramPreferencesHint().getPreferenceStore();

        if (preferenceStore.getBoolean(IPreferenceConstants.PREF_ENABLE_ANTIALIAS)) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }

        g2d.clip(new java.awt.Rectangle(0, 0, awtImage.getWidth(), awtImage.getHeight()));

        Graphics graphics = new SiriusGraphicsToGraphics2DAdaptor(g2d, new Rectangle(0, 0, awtImage.getWidth(), awtImage.getHeight()));

        RenderedMapModeGraphics mapModeGraphics = new RenderedMapModeGraphics(graphics, mm);

        renderToGraphics(mapModeGraphics, new Point(sourceRect.x, sourceRect.y), selectedObjects);

        graphics.dispose();
        g2d.dispose();
        return awtImage;
    }
}
