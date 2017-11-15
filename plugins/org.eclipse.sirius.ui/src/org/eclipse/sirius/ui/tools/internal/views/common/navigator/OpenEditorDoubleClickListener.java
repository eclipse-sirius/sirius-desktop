/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenRepresentationsAction;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A double click listener which opens either representations in Sirius editors or the session editor regarding the
 * selection content.
 * 
 * @author mchauvin
 */
public class OpenEditorDoubleClickListener implements IDoubleClickListener {
    private Session session;

    /**
     * Constructor.
     * 
     * @param theSession
     *            the session used for viewpoint activation.
     */
    public OpenEditorDoubleClickListener(Session theSession) {
        this.session = theSession;
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        if (event != null && event.getSelection() instanceof IStructuredSelection) {
            List<?> selection = ((IStructuredSelection) event.getSelection()).toList();
            Set<DRepresentationDescriptor> repDescriptorToOpen = getRepresentationDescriptorsToOpen(selection);
            if (!repDescriptorToOpen.isEmpty()) {
                activateViewpoints(repDescriptorToOpen);
                openRepresentations(repDescriptorToOpen);
            } else if (!selection.isEmpty()) {
                openSessionEditor(selection);
            }
        }
    }

    /**
     * Open representation from given {@link DRepresentationDescriptor}.
     * 
     * @param repDescriptorToOpen
     *            the set containing the {@link DRepresentationDescriptor} that should be opened with Sirius modelers.
     */
    protected void openRepresentations(Set<DRepresentationDescriptor> repDescriptorToOpen) {
        new OpenRepresentationsAction(repDescriptorToOpen).run();
    }

    /**
     * Activate all viewpoints associated to the given {@link DRepresentationDescriptor} if such elements exists and
     * only if they are not already activated.
     * 
     * @param repDescriptorToOpen
     *            the {@link DRepresentationDescriptor} from which referenced viewpoint should be activated.
     */
    private void activateViewpoints(Set<DRepresentationDescriptor> repDescriptorToOpen) {
        if (session != null) {
            Set<Viewpoint> viewpointsToActivate = new HashSet<>();
            repDescriptorToOpen.forEach(repDesc -> {
                Viewpoint parentViewpoint = ViewpointHelper.getViewpointInVSM(session, new RepresentationDescriptionQuery(repDesc.getDescription()).getParentViewpoint());
                if (parentViewpoint != null) {
                    boolean activateViewpoint = !ViewpointHelper.isViewpointEnabledInSession(session, parentViewpoint);
                    if (activateViewpoint) {
                        viewpointsToActivate.add(parentViewpoint);
                    }
                }
            });
            if (!viewpointsToActivate.isEmpty()) {
                ViewpointHelper.handleViewpointActivation(session, viewpointsToActivate, true, false);
            }
        }
    }

    /**
     * Open the session editor if the given selection contains an aird file.
     * 
     * @param selectionList
     *            the selection that could contains an aird file.
     */
    protected void openSessionEditor(List<?> selectionList) {
        Object element = selectionList.get(0);
        if (element instanceof IFile && new FileQuery((IFile) element).isSessionResourceFile()) {
            IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(((IFile) element).getName());
            FileEditorInput fileEditorInput = new FileEditorInput((IFile) element);
            if (fileEditorInput != null) {
                try {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(fileEditorInput, defaultEditor.getId());
                } catch (PartInitException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getLocalizedMessage(), e));
                }
            }
        }
    }

    /**
     * Return the representation descriptor to open from the selection.
     * 
     * @param selection
     *            the selection from which representation descriptor to open should be returned.
     * @return the representation descriptor to open from given selection if such element exists.
     */
    protected Set<DRepresentationDescriptor> getRepresentationDescriptorsToOpen(List<?> selection) {

        final Set<DRepresentationDescriptor> repDescriptors = new LinkedHashSet<DRepresentationDescriptor>();
        for (final Object obj : selection) {
            if (obj instanceof DRepresentationDescriptor)
                repDescriptors.add((DRepresentationDescriptor) obj);
            else {
                DRepresentationDescriptor adapted = adaptToDRepresentationDescriptor(obj);
                if (adapted != null)
                    repDescriptors.add(adapted);
            }
        }
        return repDescriptors;
    }

    private DRepresentationDescriptor adaptToDRepresentationDescriptor(Object input) {
        if (input instanceof IAdaptable) {
            Object adapter = ((IAdaptable) input).getAdapter(EObject.class);
            if (adapter instanceof DRepresentationDescriptor)
                return (DRepresentationDescriptor) adapter;
        }
        return null;
    }
}
