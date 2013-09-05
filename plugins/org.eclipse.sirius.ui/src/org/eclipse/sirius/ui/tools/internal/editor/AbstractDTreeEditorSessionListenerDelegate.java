/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.sirius.business.api.session.SessionListener;

/**
 * Delegate the managment of {@link SessionListener} events.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class AbstractDTreeEditorSessionListenerDelegate implements Runnable {

    private AbstractDTreeEditor abstractDTreeEditor;

    private int changeKind;

    /**
     * Default constructor.
     * 
     * @param abstractDTreeEditor
     *            the {@link AbstractDTreeEditor} to update
     * @param changeKind
     *            the {@link SessionListener} event
     */
    public AbstractDTreeEditorSessionListenerDelegate(AbstractDTreeEditor abstractDTreeEditor, int changeKind) {
        this.abstractDTreeEditor = abstractDTreeEditor;
        this.changeKind = changeKind;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        switch (changeKind) {
        case SessionListener.DIRTY:
        case SessionListener.SYNC:
            abstractDTreeEditor.firePropertyChangeInUIThread(IEditorPart.PROP_DIRTY);
            break;
        case IWorkbenchPart.PROP_TITLE:
            abstractDTreeEditor.firePropertyChangeInUIThread(IEditorPart.PROP_TITLE);
            break;
        case SessionListener.REPLACED:
            /* session was reload, we need to reload the table */
            abstractDTreeEditor.launchRefresh();
            break;
        case SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED:
            if (!abstractDTreeEditor.getInitialImage().equals(abstractDTreeEditor.getTitleImage())) {
                abstractDTreeEditor.setTitleImage(abstractDTreeEditor.getInitialImage());
            }
            break;
        case SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY:
            if (!abstractDTreeEditor.getLockByMeImage().equals(abstractDTreeEditor.getTitleImage())) {
                abstractDTreeEditor.setTitleImage(abstractDTreeEditor.getLockByMeImage());
            }
            break;
        case SessionListener.REPRESENTATION_EDITION_PERMISSION_DENIED:
            if (!abstractDTreeEditor.getLockByOtherImage().equals(abstractDTreeEditor.getTitleImage())) {
                abstractDTreeEditor.setTitleImage(abstractDTreeEditor.getLockByOtherImage());
            }
            break;
        case SessionListener.REPRESENTATION_FROZEN:
            if (!abstractDTreeEditor.getFrozenRepresentationImage().equals(abstractDTreeEditor.getTitleImage())) {
                abstractDTreeEditor.setTitleImage(abstractDTreeEditor.getFrozenRepresentationImage());
            }
            break;
        case SessionListener.VSM_UPDATED:
            abstractDTreeEditor.modelerDescriptionFilesLoaded();
            break;
        default:
            break;
        }
    }

}
