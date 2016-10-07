/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
