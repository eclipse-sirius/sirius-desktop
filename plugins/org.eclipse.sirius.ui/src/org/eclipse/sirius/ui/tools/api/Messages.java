/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api;

import org.eclipse.osgi.util.NLS;

/**
 * Externalise String of UI.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class Messages extends NLS {
    // CHECKSTYLE:OFF
    /**
     * The String used to prefix the representation description name fore the
     * default name of the input dialog.
     */
    public static String createRepresentationInputDialog_NamePrefix;

    /**
     * The label of the new representation name.
     */
    public static String createRepresentationInputDialog_NewRepresentationNameLabel;

    /**
     * The label of the representation description documentation.
     */
    public static String createRepresentationInputDialog_RepresentationDescriptionLabel;

    /**
     * The title of the input dialog.
     */
    public static String createRepresentationInputDialog_Title;

    // CHECKSTYLE:ON
    private static final String BUNDLE_NAME = "org.eclipse.sirius.ui.tools.api.messages"; //$NON-NLS-1$

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
