/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.ui.resource;

import org.eclipse.sirius.viewpoint.Messages;

/**
 * Common interface for Sirius messages.
 * 
 * @author mchauvin
 */
public interface ISiriusMessages {

    /** The given element is not a decorate semantic element. */
    String IS_NOT_A_DECORATE_SEMANTIC_ELEMENT = Messages.ISiriusMessages_notADecoratorErrorMsg;

    /** the warning message if a .desc file could not be loaded. */
    String DESC_FILE_NOT_VAILD_MSG = Messages.ISiriusMessages_invalidDescFileErrorMsg;

    /** the warning message if a .aird file could not be loaded. */
    String AIRD_FILE_NOT_VAILD_MSG = Messages.ISiriusMessages_invalidAirdFileErrorMsg;
}
