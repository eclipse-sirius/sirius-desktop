/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.common;

/**
 * 
 * Interface for UI controllers able to handle their binding behavior.
 * 
 */
public interface ModelViewBinding {
    /**
     * Disable model updating when the view is changing.
     */
    void disableModelUpdating();

    /**
     * Enable model updating when the view is changing (default).
     */
    void enableModelUpdating();

}
