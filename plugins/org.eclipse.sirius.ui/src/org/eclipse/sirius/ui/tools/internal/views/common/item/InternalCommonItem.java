/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.item;

/**
 * An interface to give internal possibilities on wrappers.
 * 
 * @author mporhel
 * 
 */
public interface InternalCommonItem {

    /**
     * Sets a new parent.
     * 
     * @param newParent
     *            sets a new parent.
     */
    void setParent(Object newParent);
}
