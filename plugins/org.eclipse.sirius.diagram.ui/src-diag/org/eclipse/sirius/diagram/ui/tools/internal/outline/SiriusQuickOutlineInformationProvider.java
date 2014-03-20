/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.outline;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.information.IInformationProviderExtension;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;

/**
 * This will provide the information for the quick outline dialog.
 * 
 * @author nlepine
 * 
 */
public class SiriusQuickOutlineInformationProvider implements IInformationProvider, IInformationProviderExtension {
    /**
     * We'll need the viewpoint editor to compute information at the time we
     * display the quick outline.
     */
    private final SiriusDiagramEditor editor;

    /**
     * Instantiates our information provider given the editor on which it is
     * called.
     * 
     * @param acceleoEditor
     *            Editor on which the quick outline will be displayed.
     */
    public SiriusQuickOutlineInformationProvider(SiriusDiagramEditor acceleoEditor) {
        editor = acceleoEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.information.IInformationProvider#getInformation(org.eclipse.jface.text.ITextViewer,
     *      org.eclipse.jface.text.IRegion)
     */
    public String getInformation(ITextViewer textViewer, IRegion subject) {
        // deprecated as we implement IInformationProviderExtension
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.information.IInformationProviderExtension#getInformation2(org.eclipse.jface.text.ITextViewer,
     *      org.eclipse.jface.text.IRegion)
     */
    public Object getInformation2(ITextViewer textViewer, IRegion subject) {
        return editor.getDiagram().getElement();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.text.information.IInformationProvider#getSubject(org.eclipse.jface.text.ITextViewer,
     *      int)
     */
    public IRegion getSubject(ITextViewer textViewer, int offset) {
        // Whatever the offset, we're in the AcceleoEditor : we don't need any
        // subject returned
        return new Region(offset, 0);
    }
}
