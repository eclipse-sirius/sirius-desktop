/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.find;

import org.eclipse.osgi.util.NLS;

/**
 * FindMessage NLS Class.
 * 
 * @author cbrun
 * 
 */
// CHECKSTYLE:OFF
public final class FindMessages extends NLS {
    /** NLS String. */
    public static String abstractFindLabelDialogBackwardRadio;

    /** NLS String. */
    public static String abstractFindLabelDialogCancelButton;

    /** NLS String. */
    public static String abstractFindLabelDialogDialogTitle;

    /** NLS String. */
    public static String abstractFindLabelDialogDirectionGroup;

    /** NLS String. */
    public static String abstractFindLabelDialogErrorDialogTitle;

    /** NLS String. */
    public static String abstractFindLabelDialogFindLabel;

    /** NLS String. */
    public static String abstractFindLabelDialogForwardRadio;

    /** NLS String. */
    public static String abstractFindLabelDialogNextButton;

    /** NLS String. */
    public static String abstractFindLabelDialogNoMatchingElementMessage;

    private static final String BUNDLE_NAME = "org.eclipse.sirius.common.tools.api.find.findMessages"; //$NON-NLS-1$

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, FindMessages.class);
    }

    private FindMessages() {
    }
} // CHECKSTYLE:ON
