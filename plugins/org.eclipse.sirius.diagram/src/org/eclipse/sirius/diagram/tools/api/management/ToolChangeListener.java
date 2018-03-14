/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.management;

/**
 * Listen to any change regarding available tools and their status(visible, filtered).
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface ToolChangeListener {

    /**
     * Notifies that tool(s) have been updated.
     */
    void notifyToolChange();

    /**
     * Notifies that a VSM reload has been done leading to potential obsolete tools.
     */
    void notifyToolChangeAfterVSMReload();
}
