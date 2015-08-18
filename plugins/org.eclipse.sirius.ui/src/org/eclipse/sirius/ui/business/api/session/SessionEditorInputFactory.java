/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInputFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IMemento;

/**
 * Factory for the {@link SessionEditorInput}.
 * 
 * @author cbrun
 * @since 0.9.0
 */
public class SessionEditorInputFactory extends URIEditorInputFactory {
    /**
     * ID of the factory.
     */
    public static final String ID = "org.eclipse.sirius.ui.business.api.session.SessionEditorInputFactory"; //$NON-NLS-1$

    /**
     * Create a new factory.
     */
    public SessionEditorInputFactory() {
        super();
    }

    /**
     * Create a new editor input from a .aird uri.
     * 
     * @param analysisURI
     *            the .aird uri.
     * @return the newly created input.
     */
    public SessionEditorInput create(final URI analysisURI) {
        return SessionEditorInput.create(analysisURI);
    }

    @Override
    public IAdaptable createElement(IMemento memento) {
        IAdaptable element = super.createElement(memento);

        if (element instanceof SessionEditorInput) {
            /*
             * If the session editor input is not able to retrieve a session, do
             * not let Eclipse try to restore the editor from the memento, it
             * might lead to a lot of StackOverflowError and
             * NullPointerException.
             */
            if (((SessionEditorInput) element).getSession() == null) {
                element = null;
            }
        } else {
            /*
             * The memento indicates that the expected restored editor input
             * should be an instance of SessionEditorInput. If it is not the
             * case, this indicates that some error occured during
             * org.eclipse.emf.common.ui.URIEditorInput.create(IMemento) and
             * that a default URIEditorInput has been created line 175. It will
             * not be compatible with Sirius. Do not let Eclipse try to restore
             * the editor from the memento, it might lead to a lot of
             * StackOverflowError and NullPointerException.
             */
            String bundleSymbolicName = memento.getString("bundle"); //$NON-NLS-1$
            String className = memento.getString("class"); //$NON-NLS-1$
            if (SessionEditorInput.class.getName().equals(className) && SiriusEditPlugin.ID.equals(bundleSymbolicName)) {
                element = null;
            }
        }

        return element;
    }
}
