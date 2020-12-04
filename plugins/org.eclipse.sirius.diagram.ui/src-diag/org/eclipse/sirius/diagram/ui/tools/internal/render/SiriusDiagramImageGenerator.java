/******************************************************************************
 * Copyright (c) 2002, 2020 IBM Corporation and others.
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
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.PartPositionInfo;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramImageGenerator;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.graphics.RenderedMapModeGraphics;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.SWT;

/**
 * Class override to use a specific GraphicsToGraphics2DAdaptor that handles the
 * gradient.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusDiagramImageGenerator extends DiagramImageGenerator {

    private double factor = 1;
    
    private IFigure overlayFigure;

    /**
     * Default constructor.
     * 
     * @param diagramEditPart
     *            the diagram editpart
     */
    public SiriusDiagramImageGenerator(DiagramEditPart diagramEditPart) {
        super(diagramEditPart);
        if (diagramEditPart instanceof DDiagramEditPart) {
            this.overlayFigure = ((DDiagramEditPart) diagramEditPart).getOverlayFigure();
        }
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

        awtImage = new BufferedImage((int) Math.round(rect.preciseWidth()), (int) Math.rint(rect.preciseHeight()), BufferedImage.TYPE_4BYTE_ABGR_PRE);

        Graphics2D g2d = awtImage.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, awtImage.getWidth(), awtImage.getHeight());
        g2d.scale(factor, factor);

        // Check anti-aliasing preference
        IPreferenceStore preferenceStore = (IPreferenceStore) getDiagramEditPart().getDiagramPreferencesHint().getPreferenceStore();

        if (preferenceStore.getBoolean(IPreferenceConstants.PREF_ENABLE_ANTIALIAS)) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        } else {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }

        g2d.clip(new java.awt.Rectangle(0, 0, awtImage.getWidth(), awtImage.getHeight()));

        // The sourceRect has already been scaled to the output canvas size, but we need to make sure all the figures are inside the clipping region
        Graphics graphics = new SiriusGraphicsToGraphics2DAdaptor(g2d, new PrecisionRectangle(0, 0, rect.width / factor, rect.height / factor));

        RenderedMapModeGraphics mapModeGraphics = new RenderedMapModeGraphics(graphics, mm);

        renderToGraphics(mapModeGraphics, new Point(sourceRect.x, sourceRect.y), selectedObjects);

        graphics.dispose();
        g2d.dispose();
        return awtImage;
    }
    
    @Override
    protected void renderToGraphics(Graphics graphics, Point translateOffset, List editparts) {
        graphics.translate(-translateOffset.x, -translateOffset.y);
        graphics.pushState();

        List<GraphicalEditPart> connectionsToPaint = new LinkedList<>();

        Map decorations = findDecorations(editparts);

        for (Iterator editPartsItr = editparts.listIterator(); editPartsItr.hasNext(); /**/) {
            IGraphicalEditPart editPart = (IGraphicalEditPart) editPartsItr.next();

            // do not paint selected connection part
            if (editPart instanceof ConnectionEditPart) {
                connectionsToPaint.add(editPart);
            } else {                
                connectionsToPaint.addAll(findConnectionsToPaint(editPart));
                // paint shape figure
                IFigure figure = editPart.getFigure();
                setCurrentId(graphics, editPart); // ADDED
                paintFigure(graphics, figure);

                paintDecorations(graphics, figure, decorations);
            }
        }
        
        // paint the connection parts after shape parts paint
        decorations = findDecorations(connectionsToPaint);

        for (Iterator<GraphicalEditPart> connItr = connectionsToPaint.iterator(); connItr.hasNext(); /**/) {
            GraphicalEditPart conn = connItr.next();
            setCurrentId(graphics, conn); // ADDED
            IFigure figure = conn.getFigure();
            paintFigure(graphics, figure);
            paintDecorations(graphics, figure, decorations);
        }
        
        if (this.overlayFigure != null) {
            paintFigure(graphics, overlayFigure);
        }
    }
    
    private void setCurrentId(Graphics gfx, GraphicalEditPart part) {
        if (gfx instanceof SiriusGraphicsSVG && part instanceof org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart) {
            SiriusGraphicsSVG sgfx = (SiriusGraphicsSVG) gfx;
            EObject o = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart) part).resolveSemanticElement();
            if (o instanceof DSemanticDecorator) {
                o = ((DSemanticDecorator) o).getTarget();
            }
            sgfx.setCurrentId(EcoreUtil.getURI(o).toString());
        }
    }

    @Override
    protected Graphics setUpGraphics(int width, int height) {
        SWTGraphics swtG = (SWTGraphics) super.setUpGraphics(width, height);
        swtG.scale(factor);
        swtG.setTextAntialias(SWT.ON);
        return swtG;
    }

    @Override
    public org.eclipse.swt.graphics.Rectangle calculateImageRectangle(List editparts) {
        org.eclipse.swt.graphics.Rectangle sourceRect = super.calculateImageRectangle(editparts);
        return new org.eclipse.swt.graphics.Rectangle(sourceRect.x, sourceRect.y, scaleAsInt(sourceRect.width), scaleAsInt(sourceRect.height));
    }

    @Override
    public List getDiagramPartInfo(DiagramEditPart diagramEditPart) {
        List superResult = super.getDiagramPartInfo(diagramEditPart);
        for (Object obj : superResult) {
            if (obj instanceof PartPositionInfo) {
                PartPositionInfo nonScaledInfo = (PartPositionInfo) obj;
                nonScaledInfo.setPartX(nonScaledInfo.getPartX());
                nonScaledInfo.setPartY(nonScaledInfo.getPartY());
                nonScaledInfo.setPartWidth(scaleAsInt(nonScaledInfo.getPartWidth()));
                nonScaledInfo.setPartHeight(scaleAsInt(nonScaledInfo.getPartHeight()));
            }
        }
        return superResult;
    }

    private int scaleAsInt(int value) {
        return Double.valueOf(value * factor).intValue();
    }

    public void setResolutionScale(double scale) {
        this.factor = scale;
    }
}
