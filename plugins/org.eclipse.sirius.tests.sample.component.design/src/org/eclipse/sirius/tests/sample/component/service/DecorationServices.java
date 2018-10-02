/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.component.service;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.tests.sample.component.Component;
import org.eclipse.swt.graphics.Image;

/**
 * Decoration services.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class DecorationServices {

    /**
     * Create a composite figure that contains a Label and an image
     * 
     * @param semanticObject
     * @return
     */
    public IFigure getDecorationFigure(Component semanticObject) {
        Figure composite = new Figure();
        GridLayout fl = new GridLayout(2, false);
        fl.horizontalSpacing = 1;
        fl.verticalSpacing = 1;
        composite.setLayoutManager(fl);
        // Need to set the size so that it can be correctly displayed
        composite.setSize(20 + 4 * semanticObject.getName().length(), 20);

        Label figureWithtext = new Label(semanticObject.getName());
        // figureWithtext.setSize(8, 20);
        figureWithtext.setBackgroundColor(ColorConstants.blue);
        figureWithtext.setForegroundColor(ColorConstants.red);
        composite.add(figureWithtext);

        WorkspaceImageFigure imageFigure = WorkspaceImageFigure.createImageFigure("/org.eclipse.sirius.tests.sample.component.design/images/double-arrow-icon-16x16.png");
        // imageFigure.setSize(16, 16);
        composite.add(imageFigure);

        return composite;
    }

    /**
     * Return an image used as decoration.
     * 
     * @param semanticObject
     * @return
     */
    public Image getDecorationImage(EObject semanticObject) {
        return WorkspaceImageFigure.flyWeightImage(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.HAS_DIAG_IMG));
    }

    /**
     * Create a composite figure that contains a Label and an image
     * 
     * @param semanticObject
     * @return
     */
    public IFigure getDecorationTooltipFigure(EObject semanticObject) {
        if (semanticObject instanceof Component) {
            return new Label("Tooltip_" + ((Component) semanticObject).getName());
        }
        return new Label("TooltipError");
    }
}
