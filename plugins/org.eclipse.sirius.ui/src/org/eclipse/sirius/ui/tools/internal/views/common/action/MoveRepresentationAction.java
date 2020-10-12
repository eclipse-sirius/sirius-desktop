/*******************************************************************************
 * Copyright (c) 2015, 2020 Obeo and others.
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
package org.eclipse.sirius.ui.tools.internal.views.common.action;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.dialect.command.MoveRepresentationCommand;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorService;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * An action to move selected representations.
 *
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class MoveRepresentationAction extends Action {
    private final Collection<DRepresentationDescriptor> repDescriptors;

    private final DAnalysis targetAnalysis;

    private final Session session;

    /**
     * Construct a new instance.
     *
     * @param session
     *            the current session
     * @param targetAnalysis
     *            target analysis for the selection
     * @param selection
     *            the selected representations to move
     */
    public MoveRepresentationAction(Session session, final DAnalysis targetAnalysis, Collection<DRepresentationDescriptor> selection) {
        super();
        this.targetAnalysis = targetAnalysis;
        this.session = session;
        this.repDescriptors = selection;
        Assert.isTrue(!repDescriptors.isEmpty());

        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/forward.gif"); //$NON-NLS-1$
        this.setImageDescriptor(descriptor);

        this.setText(MessageFormat.format(Messages.MoveRepresentationAction_text, targetAnalysis.eResource().getURI().toString()));

        // Disable the action if the selection is not valid
        if (!isValidSelection()) {
            this.setEnabled(false);
        }
    }
    
    /**
     * Construct a new instance.
     *
     * @param session
     *            the current session
     * @param selection
     *            the selected representations to move
     */
    public MoveRepresentationAction(Session session, Collection<DRepresentationDescriptor> selection) {
        super();
        this.targetAnalysis = null;
        this.session = session;
        this.repDescriptors = selection;
        Assert.isTrue(!repDescriptors.isEmpty());

        final ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditPlugin.ID, "/icons/full/others/forward.gif"); //$NON-NLS-1$
        this.setImageDescriptor(descriptor);

        this.setText(Messages.MoveRepresentationAction_label);

        // Disable the action if the selection is not valid
        if (!isValidSelection()) {
            this.setEnabled(false);
        }
    }

    @Override
    public void run() {
        if (targetAnalysis == null && session instanceof DAnalysisSession) {
            DAnalysisSelector dAnalysisSelector = DAnalysisSelectorService.getSelector((DAnalysisSession) session);
            for (DRepresentationDescriptor dRepresentationDescriptor : repDescriptors) {
                Option<EObject> dAnalysisOption = new EObjectQuery(dRepresentationDescriptor).getFirstAncestorOfType(ViewpointPackage.eINSTANCE.getDAnalysis());
                Collection<DAnalysis> analysesCandidates = ((DAnalysisSession) session).allAnalyses();
                analysesCandidates.remove(dAnalysisOption.get());
                DAnalysis selectedDAnalysis = dAnalysisSelector.selectSmartlyAnalysisForAddedRepresentation(dRepresentationDescriptor.getRepresentation(), analysesCandidates);
                if (selectedDAnalysis != dAnalysisOption.get()) {
                    session.getTransactionalEditingDomain().getCommandStack()
                            .execute(new MoveRepresentationCommand(session, selectedDAnalysis, Collections.<DRepresentationDescriptor> singleton(dRepresentationDescriptor)));
                }
            }
            
        } else {
            
            final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            if (uiSession != null) {
                for (final DRepresentationDescriptor repDescriptor : repDescriptors) {
                    final IEditorPart editor = uiSession.getEditor(repDescriptor.getRepresentation());
                    if (editor != null) {
                        editor.getEditorSite().getPage().closeEditor(editor, false);
                    }
                }
            }
            session.getTransactionalEditingDomain().getCommandStack().execute(new MoveRepresentationCommand(session, targetAnalysis, repDescriptors));
        }
    }

    /**
     * Test if the selection is valid.
     *
     * @return true if the selection is valid
     */
    private boolean isValidSelection() {
        if (targetAnalysis == null) {


            return session.getReferencedSessionResources().size() > 0;
        } else {

            boolean anyInvalidMove = Iterables.any(repDescriptors, new Predicate<DRepresentationDescriptor>() {
    
                @Override
                public boolean apply(DRepresentationDescriptor input) {
                    boolean invalid = false; // false is the default value
    
                    // Step 1: Check source representation container
                    EObject container = input.eContainer();
                    if (container instanceof DView) {
                        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(container);
                        if (permissionAuthority != null && !permissionAuthority.canDeleteInstance(input)) {
                            invalid = true;
                        }
                    }
    
                    // Step 2: Check target representation container
                    if (!invalid) {
                        DView targetContainer = DAnalysisSessionHelper.findDViewForAddedRepresentation(targetAnalysis, input.getDescription());
                        if (targetContainer != null) {
                            IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(targetContainer);
                            if (permissionAuthority != null && !permissionAuthority.canCreateIn(targetContainer)) {
                                invalid = true;
                            }
                        }
                    }
    
                    return invalid;
                }
            });
    
            return !anyInvalidMove;
        }
    }
}
