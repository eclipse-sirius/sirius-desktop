/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.view;

/**
 * An abstract class for all Sirius LayoutData.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class AbstractLayoutData {
    private LayoutData parent;

    private boolean isConsume;

    /**
     * Indicates if this layoutData has already been consume.
     * 
     * @return true if this layoutData has already been consume, false otherwise
     */
    protected boolean isConsume() {
        return isConsume;
    }

    /**
     * Set the isConsume.
     * 
     * @param consumeState
     *            the consume to set
     */
    protected void setConsume(final boolean consumeState) {
        this.isConsume = consumeState;
    }

    /**
     * Set the parent of this layoutData.
     * 
     * @param parent
     *            the parent to set
     */
    public void setParent(final LayoutData parent) {
        this.parent = parent;
    }

    /**
     * Returns the parent <code>LayoutData</code>.
     * 
     * @return the parent
     */
    public LayoutData getParent() {
        return parent;
    }

    /**
     * Returns the {@link RootLayoutData}.
     * 
     * @return the <code>RootLayoutData</code>
     */
    public RootLayoutData getRoot() {
        return getParent().getRoot();
    }
}
