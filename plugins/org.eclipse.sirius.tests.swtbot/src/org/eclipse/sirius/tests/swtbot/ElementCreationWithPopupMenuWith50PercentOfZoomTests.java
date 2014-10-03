/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;

/**
 * Same tests as in {@link ElementCreationWithPopupMenuTests} but with 50% of
 * zoom.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ElementCreationWithPopupMenuWith50PercentOfZoomTests extends ElementCreationWithPopupMenuTests {

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.zoom(ZoomLevel.ZOOM_50);
    }

    protected void tearDown() throws Exception {

        // set focus on diagram to allow zoom change
        diagramEditPartBot.select();

        editor.zoomDefault();

        super.tearDown();
    }

}
