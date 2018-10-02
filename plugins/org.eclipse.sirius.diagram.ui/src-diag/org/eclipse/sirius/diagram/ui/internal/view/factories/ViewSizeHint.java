/*******************************************************************************
 * Copyright (c) 2007 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * Hint concerning the {@link org.eclipse.gmf.runtime.notation.View} size.
 * 
 * @author mchauvin
 */
public final class ViewSizeHint {

    /** the shared instance. */
    private static ViewSizeHint instance = new ViewSizeHint();

    /** the size. */
    private Dimension size;

    /**
     * Avoid instantiation.
     */
    private ViewSizeHint() {

    }

    /**
     * Define the size.
     * 
     * @param size
     *            the size.
     */
    public synchronized void setSize(final Dimension size) {
        this.size = size;
    }

    /**
     * Consume a location.
     * 
     * @return the location if it exists or <code>null</code> otherwise.
     */
    public synchronized Dimension consumeSize() {
        final Dimension s = this.size;
        this.size = null;
        return s;
    }

    /**
     * Return the shared instance.
     * 
     * @return the shared instance.
     */
    public static ViewSizeHint getInstance() {
        return instance;
    }

}
