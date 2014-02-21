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

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Action to export representations as images.
 * 
 * @author cbrun
 */
public class ExportRepresentationsAction extends AbstractExportRepresentationsAction {

    private static Predicate<DRepresentation> exportableRepresentation = new Predicate<DRepresentation>() {
        @Override
        public boolean apply(DRepresentation input) {
            return DialectUIManager.INSTANCE.canExport(input, new ExportFormat(ExportDocumentFormat.NONE, null));
        }
    };

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
    protected Collection<DRepresentation> getDRepresentationToExport() {
        return Lists.newArrayList(Iterables.filter(selectedRepresentations, exportableRepresentation));
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && !Iterables.isEmpty(Iterables.filter(selectedRepresentations, exportableRepresentation));
    }

    @Override
    protected Session getSession(DRepresentation representation) {
        return session;
    }
}
