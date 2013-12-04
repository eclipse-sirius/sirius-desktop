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
package org.eclipse.sirius.ui.tools.api.views.common.item;

/**
 * An interface to get the wrapped object.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface ItemWrapper extends CommonSessionItem {
    /**
     * Get the wrapped object.
     * 
     * @return the wrapped object.
     */
    Object getWrappedObject();
}
