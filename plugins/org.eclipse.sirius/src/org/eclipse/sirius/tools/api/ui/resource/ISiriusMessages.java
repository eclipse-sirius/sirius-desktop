/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.ui.resource;

import org.eclipse.sirius.tools.api.Messages;

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
