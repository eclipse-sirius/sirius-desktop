/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.business.api.session;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * An API to manipulate user session easily.
 *
 * @provisional this class may be modified and renamed. API may change in future
 *              release without depreciation.
 * @author mchv
 * @since 0.9.0
 */
public class UserSession {

    private Session session;

    /**
     * Instantiate a new user session from a session.
     *
     * @param session
     *            the session to wrap
     */
    public UserSession(Session session) {
        this.session = session;
    }

    /**
     * Instantiate a new user session from a session.
     *
     * @param session
     *            the session to wrap
     * @return a {@link UserSession} instance
     */
    public static UserSession from(Session session) {
        return new UserSession(session);
    }

    /**
     * Instantiate a new user session from a model element (representation or
     * semantic one).
     *
     * @param eObject
     *            the model element
     * @return a {@link UserSession} instance
     */
    public static UserSession from(final EObject eObject) {
        EObject modelElement = eObject;
        if (eObject instanceof DSemanticDecorator) {
            modelElement = ((DSemanticDecorator) eObject).getTarget();
        }
        Session session = SessionManager.INSTANCE.getSession(modelElement);
        if (session != null) {
            return UserSession.from(session);
        }
        return null;
    }

    /**
     * Instantiate a new user session from selection.
     *
     * @param selection
     *            the current selection
     * @return a {@link UserSession} instance
     */
    public static UserSession from(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (!structuredSelection.isEmpty()) {
                Object o = structuredSelection.getFirstElement();
                if (o instanceof EObject) {
                    EObject eObject = (EObject) o;
                    return UserSession.from(eObject);
                }
            }
        }
        return null;
    }

    /**
     * Check if a user session is open.
     *
     * @return <code>true</code> if it is, <code>false</code> otherwise.
     */
    public boolean isOpen() {
        if (session != null) {
            return session.isOpen();
        } else {
            return false;
        }
    }

    /**
     * Save session.
     *
     * @param pm
     *            the progress monitor to use.
     *
     * @return the saved user session instance
     */
    public UserSession save(IProgressMonitor pm) {
        session.save(pm);
        return this;
    }

    /**
     * Close session.
     *
     * @param pm
     *            the progress monitor to use.
     *
     * @return the closed user session
     */
    public UserSession close(IProgressMonitor pm) {
        session.close(pm);
        return this;
    }

    /**
     * Open a representation from its name.
     *
     * @param name
     *            name of the representation to open
     * @return the user session
     */
    public UserSession openRepresentation(String name) {
        DRepresentation representation = findRepresentationByName(name);
        if (representation != null) {
            openEditor(representation, new NullProgressMonitor());
        } else {
            SiriusEditPlugin.getPlugin().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.UserSession_representationNotFound, name)));
        }
        return this;
    }

    private IEditorPart openEditor(DRepresentation representation, IProgressMonitor monitor) {
        final IEditorPart openEditorPart = DialectUIManager.INSTANCE.openEditor(session, representation, monitor);
        return openEditorPart;
    }

    /**
     * Select a viewpoint from its name.
     *
     * @param viewpointName
     *            the name of the viewpoint to select
     * @return the user session
     */
    public UserSession selectViewpoint(final String viewpointName) {
        return selectViewpoints(Arrays.asList(new String[] { viewpointName }));
    }

    /**
     * Select a viewpoint from its name and unselect all the others.
     *
     * @param viewpointName
     *            the name of the viewpoint
     * @return the user session
     */
    public UserSession selectOnlyViewpoint(final String viewpointName) {
        return selectViewpoints(Arrays.asList(new String[] { viewpointName }), true);
    }

    /**
     * Select viewpoints from their name.
     *
     * @param viewpointNames
     *            name of viewpoints to select
     * @return the user session
     */
    public UserSession selectViewpoints(final Iterable<String> viewpointNames) {
        return selectViewpoints(viewpointNames, false);
    }

    /**
     * Select viewpoints from their name and unselect all the others.
     *
     * @param viewpointNames
     *            name of viewpoints to select
     * @return the user session
     */
    public UserSession selectOnlyViewpoints(final Iterable<String> viewpointNames) {
        return selectViewpoints(viewpointNames, true);
    }

    private UserSession selectViewpoints(final Iterable<String> viewpointNames, final boolean onlyThisViewpoints) {
        try {
            PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {
                @Override
                public void run(IProgressMonitor monitor) {
                    Set<Viewpoint> viewpoints = new LinkedHashSet<>();
                    for (final String viewpointName : viewpointNames) {
                        Viewpoint viewpoint = findViewpointByName(viewpointName);
                        viewpoints.add(viewpoint);
                    }
                    selectViewpoints(viewpoints, onlyThisViewpoints);
                }
            });
        } catch (InvocationTargetException e) {
            SiriusEditPlugin.getPlugin().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.UserSession_viewpointSelectionFailed, viewpointNames), e));
        } catch (InterruptedException e) {
            SiriusEditPlugin.getPlugin().log(new Status(IStatus.ERROR, SiriusEditPlugin.ID, MessageFormat.format(Messages.UserSession_viewpointSelectionFailed, viewpointNames), e));
        }
        return this;
    }

    private void selectViewpoints(final Collection<Viewpoint> viewpoints, boolean deselectOtherViewpoints) {

        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

        Set<Viewpoint> viewpointsToDeselect = new HashSet<>();
        Set<Viewpoint> viewpointsToSelect = new HashSet<>();

        for (final Viewpoint viewpoint : viewpoints) {
            final Viewpoint vp = SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint);
            if (!selectedViewpoints.contains(vp)) {
                viewpointsToSelect.add(vp);
            }
        }

        if (deselectOtherViewpoints) {
            for (final Viewpoint candidate : selectedViewpoints) {
                if (!viewpointsToSelect.contains(candidate)) {
                    viewpointsToDeselect.add(candidate);
                }
            }
        }

        final ViewpointSelectionCallback selectionCallback = new ViewpointSelectionCallback();
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

        final Command command = new ChangeViewpointSelectionCommand(session, selectionCallback, viewpointsToSelect, viewpointsToDeselect, new NullProgressMonitor());
        domain.getCommandStack().execute(command);
    }

    /**
     * Get the semantic cross referencer.
     *
     * @return the semantic cross referencer.
     */
    public ECrossReferenceAdapter getSemanticCrossReferencer() {
        return session.getSemanticCrossReferencer();
    }

    private DRepresentation findRepresentationByName(String name) {
        Collection<DRepresentationDescriptor> allRepresentationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        for (DRepresentationDescriptor representationDescriptor : allRepresentationDescriptors) {
            if (representationDescriptor.getName().equals(name)) {
                return representationDescriptor.getRepresentation();
            }
        }
        return null;
    }

    private Viewpoint findViewpointByName(String vpName) {
        for (Viewpoint candidate : ViewpointRegistry.getInstance().getViewpoints()) {
            if (vpName.equals(candidate.getName())) {
                return candidate;
            }
        }
        return null;
    }

    /**
     * Open a representation from the selection. If selection contains an
     * instance of {@link DRepresentation} it will be open. If a selection
     * contains an {@link EObject} which is a target of a representation, this
     * representation will be open.
     *
     * @param selection
     *            the selection
     * @return the user session
     */
    public UserSession openRepresentation(final ISelection selection) {
        return openRepresentation(selection, new NullProgressMonitor());
    }

    /**
     * Open a representation from the selection and a progress monitor. If
     * selection contains an instance of representation it will be open. If a
     * selection contains an EObject which is a target of a representation it
     * will be open.
     *
     * @param selection
     *            the selection
     * @param monitor
     *            a progress monitor
     * @return the user session
     */
    public UserSession openRepresentation(final ISelection selection, final IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.UserSession_openRepresentationTask, 5);
            final EObject selected = getEObject(selection);
            monitor.worked(1);
            final DRepresentation representation = findRepresentation(selected);
            if (representation != null) {
                final IEditorPart part = openEditor(representation, new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN));
                monitor.worked(3);
                if (part != null) {
                    if (selected instanceof DSemanticDecorator) {
                        final EObject semantic = ((DSemanticDecorator) selected).getTarget();
                        updateUISession((DialectEditor) part, semantic);
                        monitor.worked(1);
                    }
                }
            }
        } finally {
            monitor.done();
        }
        return this;
    }

    private DRepresentation findRepresentation(final EObject element) {
        if (element instanceof DRepresentation) {
            return (DRepresentation) element;
        } else {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(element, session);
            return representations.isEmpty() ? null : (DRepresentation) representations.toArray()[0];
        }
    }

    private void updateUISession(final DialectEditor part, final EObject semantic) {
        if (session != null) {
            final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
            uiSession.open();
            uiSession.attachEditor(part);

        }
    }

    private EObject getEObject(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            final Object element = ((IStructuredSelection) selection).getFirstElement();
            if (element instanceof EObject) {
                return (EObject) element;
            }
        }
        return null;
    }

}
