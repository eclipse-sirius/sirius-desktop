/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
