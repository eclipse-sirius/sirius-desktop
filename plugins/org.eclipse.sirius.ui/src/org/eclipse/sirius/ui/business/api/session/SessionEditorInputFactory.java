/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.session;

import org.eclipse.emf.common.ui.URIEditorInputFactory;
import org.eclipse.emf.common.util.URI;

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
    public static final String ID = "org.eclipse.sirius.ui.business.api.session.SessionEditorInputFactory";

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
}
