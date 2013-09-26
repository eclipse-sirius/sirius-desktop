/*******************************************************************************
 * Copyright (c) 2009, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.export;

import java.util.Collection;

import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * Action to export representations as images.
 * 
 * @author cbrun
 */
public class ExportRepresentationsAction extends AbstractExportRepresentationsAction {

    private final Session session;

    private Collection<DRepresentation> selectedRepresentations;

    /**
     * Build the action.
     * 
     * @param session
     *            the current {@link Session}.
     * @param selectedRepresentations
     *            the selected {@link DRepresentation}.
     */
    public ExportRepresentationsAction(final Session session, final Collection<DRepresentation> selectedRepresentations) {
        super("Export diagrams as images", AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/image.gif"));
        this.session = session;
        this.selectedRepresentations = selectedRepresentations;
    }

    @Override
    protected Collection<DDiagram> getDDiagramsToExport() {
        Collection<DDiagram> dDiagramsToExport = Lists.newArrayList(Iterables.filter(selectedRepresentations, DDiagram.class));
        return dDiagramsToExport;
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && !Iterables.isEmpty(Iterables.filter(selectedRepresentations, DDiagram.class));
    }

    @Override
    protected Session getSession(DDiagram diagram) {
        return session;
    }
}
