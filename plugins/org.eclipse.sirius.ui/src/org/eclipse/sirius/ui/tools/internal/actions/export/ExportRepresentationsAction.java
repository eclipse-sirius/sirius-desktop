/*******************************************************************************
 * Copyright (c) 2009, 2017 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.actions.export;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Action to export representations as images.
 *
 * @author cbrun
 */
public class ExportRepresentationsAction extends AbstractExportRepresentationsAction {

    private static Predicate<DRepresentationDescriptor> exportableRepDescriptorPredicate = new Predicate<DRepresentationDescriptor>() {
        @Override
        public boolean apply(DRepresentationDescriptor input) {
            return DialectUIManager.INSTANCE.canExport(input, new ExportFormat(ExportDocumentFormat.NONE, null));
        }
    };

    private final Session session;

    private Collection<EObject> selectedEObjects;

    private Collection<DRepresentationDescriptor> selectedRepDescriptors;

    /**
     * Build the action.
     *
     * @param session
     *            the current {@link Session}.
     * @param selectedEObjects
     *            the selected semantic elements.
     * @param selectedRepDescriptors
     *            the selected {@link DRepresentationDescriptor}.
     */
    public ExportRepresentationsAction(Session session, Collection<EObject> selectedEObjects, Collection<DRepresentationDescriptor> selectedRepDescriptors) {
        super(Messages.ExportRepresentationsAction_label, AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/image.gif")); //$NON-NLS-1$
        this.session = session;
        this.selectedEObjects = selectedEObjects;
        this.selectedRepDescriptors = selectedRepDescriptors;
    }

    @Override
    protected Collection<DRepresentationDescriptor> getRepresentationToExport() {
        Collection<DRepresentationDescriptor> dRepDescriptorsToExport = Sets.newLinkedHashSet(selectedRepDescriptors);
        if (dRepDescriptorsToExport.isEmpty()) {
            dRepDescriptorsToExport.addAll(computeAllRepresentationsUnderSemantic());
        }
        return Lists.newArrayList(Iterables.filter(dRepDescriptorsToExport, ExportRepresentationsAction.exportableRepDescriptorPredicate));
    }

    @Override
    protected Session getSession(DRepresentationDescriptor repDescriptor) {
        return session;
    }

    private Collection<DRepresentationDescriptor> computeAllRepresentationsUnderSemantic() {
        Set<DRepresentationDescriptor> result = new LinkedHashSet<DRepresentationDescriptor>();
        for (EObject eObject : selectedEObjects) {
            result.addAll(DialectManager.INSTANCE.getRepresentationDescriptors(eObject, session));
            Iterator<EObject> iter = eObject.eAllContents();
            while (iter.hasNext()) {
                EObject child = iter.next();
                result.addAll(DialectManager.INSTANCE.getRepresentationDescriptors(child, session));
            }
        }
        return result;
    }
}
