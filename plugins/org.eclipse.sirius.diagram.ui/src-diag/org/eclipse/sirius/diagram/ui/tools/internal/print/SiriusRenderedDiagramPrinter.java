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
package org.eclipse.sirius.diagram.ui.tools.internal.print;

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.util.RenderedDiagramPrinter;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.swt.widgets.Shell;

/**
 * A specialized <code>RenderedDiagramPrinter</code> that supports Sirius
 * diagrams.
 * 
 * @author mporhel
 */
public class SiriusRenderedDiagramPrinter extends RenderedDiagramPrinter {

    /**
     * Creates a new instance.
     * 
     * @param preferencesHint
     *            the preferences hint to use for intiializing the preferences
     * @param mm
     *            <code>IMapMode</code> to do the coordinate mapping
     */
    public SiriusRenderedDiagramPrinter(final PreferencesHint preferencesHint, final IMapMode mm) {
        super(preferencesHint, mm);
    }

    /**
     * Creates a new instance.
     * 
     * @param preferencesHint
     *            the preferences hint to use for intiializing the preferences
     */
    public SiriusRenderedDiagramPrinter(final PreferencesHint preferencesHint) {
        super(preferencesHint);
    }

    /**
     * {@inheritDoc}
     * 
     * @Override
     */
    protected DiagramEditPart createDiagramEditPart(final Diagram diagram, final Shell shell) {
        final DiagramEditPartService tool = new DiagramEditPartService();
        return tool.createDiagramEditPart(diagram, shell, getPreferencesHint());
    }
}
