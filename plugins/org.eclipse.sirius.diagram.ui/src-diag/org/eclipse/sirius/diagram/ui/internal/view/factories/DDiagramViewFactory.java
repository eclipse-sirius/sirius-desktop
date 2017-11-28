/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.view.factories.DiagramViewFactory;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @was-generated
 */
public class DDiagramViewFactory extends DiagramViewFactory {

    /**
     * @was-generated
     */
    protected List<?> createStyles(final View view) {
        final List<Style> styles = new ArrayList<>();
        styles.add(NotationFactory.eINSTANCE.createDiagramStyle());
        return styles;
    }

    /**
     * @was-generated
     */
    protected MeasurementUnit getMeasurementUnit() {
        return MeasurementUnit.PIXEL_LITERAL;
    }
}
