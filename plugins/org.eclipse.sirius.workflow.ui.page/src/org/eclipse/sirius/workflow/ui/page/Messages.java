/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.workflow.ui.page;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Translatable messages used in this plug-in.
 * 
 * @author pcdavid
 *
 */
public class Messages {
    static {
        I18N.initializeMessages(Messages.class, WorkflowPagePlugin.INSTANCE);
    }
    
    @TranslatableMessage
    public static String WorkflowPage_tab_name;
    
    @TranslatableMessage
    public static String WorkflowPage_header_title;

}
