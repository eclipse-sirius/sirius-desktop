/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.session;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.progress.IJobRunnable;

/**
 * A {@link Saveable} implementation for the Editor that wrappers a session.
 * 
 * @author mporhel
 */
public class SessionSaveable extends Saveable {

    private Session session;

    /**
     * Creates a new DefaultSaveable.
     * 
     * @param session
     *            the session represented by this model
     */
    public SessionSaveable(final Session session) {
        this.session = session;
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        new SaveSessionRunnable(session).run(monitor);
    }
    
    @Override
    public IJobRunnable doSave(IProgressMonitor monitor, IShellProvider shellProvider) throws CoreException {
        return new SaveSessionRunnable(session);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.Saveable#getName()
     */
    @Override
    public String getName() {
        return SiriusEditPlugin.getPlugin().getUiCallback().getSessionNameToDisplayWhileSaving(session);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.Saveable#getImageDescriptor()
     */
    @Override
    public ImageDescriptor getImageDescriptor() {
        return SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/obj16/SessionResourceFile.gif"); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.Saveable#getToolTipText()
     */
    @Override
    public String getToolTipText() {
        String tooltip = null;
        if (session != null && session.getSessionResource() != null) {
            URI uri = session.getSessionResource().getURI();
            if (uri.isPlatform()) {
                tooltip = uri.toPlatformString(true);
            } else if (uri.isFile()) {
                tooltip = uri.toFileString();
            } else {
                tooltip = uri.toString();
            }
        }
        return tooltip;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.Saveable#isDirty()
     */
    @Override
    public boolean isDirty() {
        if (session != null) {
            return SessionStatus.DIRTY == session.getStatus();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.Saveable#hashCode()
     */
    @Override
    public int hashCode() {
        return session.hashCode();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.Saveable#equals(java.lang.Object)
     */
    // CHECKSTYLE:OFF
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionSaveable other = (SessionSaveable) obj;
        if (session == null) {
            if (other.session != null) {
                return false;
            }
        } else if (!session.equals(other.session)) {
            return false;
        }
        return true;
    }

    // CHECKSTYLE:ON

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.Saveable#show(org.eclipse.ui.IWorkbenchPage)
     */
    // CHECKSTYLE:OFF
    @Override
    public boolean show(final IWorkbenchPage page) {
        if (session != null && page != null) {
            IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
            if (uiSession != null && !uiSession.getEditors().isEmpty()) {
                DialectEditor dialectEditor = uiSession.getEditors().iterator().next();
                final IWorkbenchPartReference reference = page.getReference(dialectEditor);
                if (reference != null) {
                    page.activate(dialectEditor);
                    return true;
                }

                if (dialectEditor instanceof IViewPart) {
                    final IViewPart viewPart = (IViewPart) dialectEditor;
                    try {
                        page.showView(viewPart.getViewSite().getId(), viewPart.getViewSite().getSecondaryId(), IWorkbenchPage.VIEW_ACTIVATE);
                    } catch (final PartInitException e) {
                        return false;
                    }
                    return true;
                }
            }
        }

        return false;
    }

    // CHECKSTYLE:ON

    /**
     * Return the current session.
     * 
     * @return the current session.
     */
    public Session getSession() {
        return session;
    }

}
