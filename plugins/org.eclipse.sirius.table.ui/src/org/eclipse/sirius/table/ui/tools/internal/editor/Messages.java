/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor;

import org.eclipse.osgi.util.NLS;

/**
 * Messages for the Table plugins.
 * 
 * @author lredor
 */
// CHECKSTYLE:OFF : message class, no pb with that.
public final class Messages extends NLS {

    static {
        NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }

    /**
     * Table is not present in resource.
     */
    public static String dTableEditor_NoTableInResourceError;

    /**
     * Error loading table.
     */
    public static String dTableEditor_TableLoadingError;

    public static String dTableEditor_SavingDeletedFile;

    public static String dTableEditor_SaveAsErrorTitle;

    public static String dTableEditor_SaveAsErrorMessage;

    public static String dTableEditor_SaveErrorTitle;

    public static String dTableEditor_SaveErrorMessage;

    public static String dTableEditor_ErrorSaveDeletedTitle;

    public static String dTableEditor_ErrorSaveDeletedMessage;

    public static String dTableEditor_SaveTableTask;

    public static String dTableEditor_SaveNextResourceTask;

    public static String editingDomainContext;

    public static String dTableEditor_handleElementContentChanged;

    /**
     * Avoid instantiation.
     */
    private Messages() {
    }
}
// CHECKSTYLE:ON
