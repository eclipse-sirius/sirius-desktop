/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer.internal;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * This adapter wraps an Integer.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class IntegerAdapter extends AdapterImpl {

    protected Integer id;

    /**
     * Create the adapter.
     * 
     * @param id
     *            element ID.
     */
    public IntegerAdapter(Integer id) {
        this.id = id;
    }

    public Integer getIntegerValue() {
        return id;
    }

}
