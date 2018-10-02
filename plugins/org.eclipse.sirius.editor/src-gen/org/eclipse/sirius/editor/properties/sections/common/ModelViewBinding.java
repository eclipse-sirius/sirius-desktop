/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
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
