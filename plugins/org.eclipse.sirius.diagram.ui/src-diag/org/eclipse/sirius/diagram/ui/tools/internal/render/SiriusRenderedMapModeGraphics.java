/*******************************************************************************
 * Copyright (c) 2019 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.render;

import java.util.function.Supplier;

import org.eclipse.draw2d.Graphics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.graphics.RenderedMapModeGraphics;

/**
 * Extends org.eclipse.gmf.runtime.draw2d.ui.render.internal.graphics.RenderedMapModeGraphics to make it possible to
 * override the {@link SiriusGraphicsSVG} current id.
 * 
 * @author fbarbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusRenderedMapModeGraphics extends RenderedMapModeGraphics {
    /**
     * Default constructor.
     * 
     * @param g
     *            <code>Graphics</code> element to delegate render to
     * @param mm
     *            <code>IMapMode</code> to retrieve the scale factor from.
     */
    public SiriusRenderedMapModeGraphics(Graphics g, IMapMode mm) {
        super(g, mm);
    }

    /**
     * Set the current element ID of the {@link SiriusGraphicsSVG}. If the {@link Graphics} is not the Sirius SVG one,
     * we do nothing.
     * 
     * @param element
     *            the supplier to get the semantic element from which we retrieve the ID. The get method will be call
     *            only if the current {@link Graphics} is the one use for exporting SVG.
     */
    public void setGraphicsTraceabilityId(Supplier<EObject> element) {
        Graphics subGraphics = this.getGraphics();
        if (subGraphics instanceof SiriusGraphicsSVG) {
            if (element != null) {
                EObject elt = element.get();
                if (elt != null) {
                    String id = EcoreUtil.getID(elt);
                    if (id == null) {
                        id = EcoreUtil.getURI(elt).toString();
                    }
                    ((SiriusGraphicsSVG) subGraphics).setCurrentId(id);
                }
            } else {
                ((SiriusGraphicsSVG) subGraphics).setCurrentId("none"); //$NON-NLS-1$
            }
        }
    }

    @Override
    public Graphics getGraphics() {
        return super.getGraphics();
    }

    /**
     * Draw SVG image reference (use tag).
     * 
     * @param uri
     *            Image URI
     * 
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
    // CHECKSTYLE:OFF
    public void drawSVGReference(String uri, int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        Graphics subGraphics = this.getGraphics();
        if (subGraphics instanceof SiriusGraphicsSVG) {
            SiriusGraphicsSVG svgGraphics = (SiriusGraphicsSVG) subGraphics;
            svgGraphics.drawSVGReference(uri, x1, y1, w1, h1, x2, y2, w2, h2);
        } else {
            throw new RuntimeException();
        }
    }

    // CHECKSTYLE:ON
    /**
     * Draw SVG image in symbol tag.
     * 
     * @param uri
     *            String
     * @param uuid
     *            String
     */
    public void drawSymbolSVGImage(String uri, String uuid) {
        Graphics subGraphics = this.getGraphics();
        if (subGraphics instanceof SiriusGraphicsSVG) {
            SiriusGraphicsSVG svgGraphics = (SiriusGraphicsSVG) subGraphics;
            svgGraphics.drawSymbolSVGImage(uri, uuid);
        } else {
            throw new RuntimeException();
        }
    }

}
