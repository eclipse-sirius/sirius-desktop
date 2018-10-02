/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.editor.tools.internal.menu;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

// CHECKSTYLE:OFF
public class CustomChildTextAdapter extends AdapterImpl {
    private final String text;

    public CustomChildTextAdapter(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    
    @Override
    public boolean isAdapterForType(Object type) {
        return type == CustomChildTextAdapter.class;
    }
    
}
