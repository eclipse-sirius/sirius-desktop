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

}
