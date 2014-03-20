/******************************************************************************
 * Copyright (c) 2004, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;

/**
 * Copied from org.eclipse.gmf.runtime.diagram.ui.editpolicies.
 * CanonicalEditPolicy$CanonicalElementAdapter.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CanonicalElementAdapter extends EObjectAdapter {

    private String hint;

    /**
     * Default constructor.
     * 
     * @param element
     *            the {@link EObject} element of a future view.
     * @param hint
     *            the type of the view
     */
    public CanonicalElementAdapter(EObject element, String hint) {
        super(element);
        this.hint = hint;
    }

    /**
     * Adds <code>String.class</tt> adaptability.
     * 
     * @param adapter
     *            the type of the object to return
     * @return the adapted object
     */
    public Object getAdapter(@SuppressWarnings("rawtypes")
    Class adapter) {
        if (adapter.equals(String.class)) {
            return hint;
        }
        return super.getAdapter(adapter);
    }
}
