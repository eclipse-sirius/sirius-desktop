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
package org.eclipse.sirius.tests.unit.api.refresh;

import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;

/**
 * Class used to create SWT colors from color definitions, using a visual
 * binding manager to keep track of everything.
 * 
 * @author cbrun
 * 
 */
public class ColorFactory {

    private VisualBindingManager manager;

    public ColorFactory(VisualBindingManager colorManager) {
        this.manager = colorManager;
    }

    public Color create(RGBValues values) {
        if (values != null)
            return manager.getColorFromRGBValues((values));
        else
            return black();
    }

    public Color black() {
        return manager.getColorFromName("black");
    }

    public Color gray() {
        return manager.getColorFromName("gray");
    }

    public Color white() {
        return manager.getColorFromName("white");
    }

    public Color light_gray() {
        return manager.getColorFromName("light_gray");
    }

    public Object yellow() {
        return manager.getColorFromName("yellow");
    }

    public Color blue() {
        return manager.getColorFromName("blue");
    }

    public Color purple() {
        return manager.getColorFromName("purple");
    }

    public Color orange() {
        return manager.getColorFromName("orange");
    }

    public Color green() {
        return manager.getColorFromName("green");
    }
}
