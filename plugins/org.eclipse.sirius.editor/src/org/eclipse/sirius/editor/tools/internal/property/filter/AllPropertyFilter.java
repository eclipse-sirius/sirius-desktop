/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.tools.internal.property.filter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.sirius.editor.tools.internal.editor.EditorCustomizationManager;

/**
 * Property filter for the "all" tab.
 * 
 * @author cbrun
 * 
 */
public class AllPropertyFilter implements IFilter {
    /**
     * {@inheritDoc}
     */
    public boolean select(Object arg0) {
        if (arg0 instanceof EObject) {
            return EditorCustomizationManager.getInstance().showTheAllTab();
        }
        return false;
    }

}
