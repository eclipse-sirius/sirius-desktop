/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.ui.resource;

/**
 * Common interface for Sirius messages.
 * 
 * @author mchauvin
 */
public interface ISiriusMessages {

    /** The given element is not a decorate semantic element. */
    String IS_NOT_A_DECORATE_SEMANTIC_ELEMENT = "The element is not a DSemanticDecorator";

    /** the warning message if a .desc file could not be loaded. */
    String DESC_FILE_NOT_VAILD_MSG = "The following description file is not valid : ";

    /** the warning message if a .aird file could not be loaded. */
    String AIRD_FILE_NOT_VAILD_MSG = "The following diagram file is not valid : ";
}
