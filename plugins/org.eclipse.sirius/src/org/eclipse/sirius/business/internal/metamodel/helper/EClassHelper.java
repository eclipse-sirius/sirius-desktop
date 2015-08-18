/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.helper;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * Class providing helpers for EClass object from the ecore metamodel.
 * 
 * @author bgrouhan
 *
 */
public final class EClassHelper {

    private EClassHelper() {
    }

    /**
     * Helper to get the path of an EClass from the ecore metamodel.
     * 
     * @param eClass
     *            the EClass.
     * @return the path of the EClass.
     */
    public static String getPath(EClass eClass) {
        return getParentPath(eClass);
    }

    private static String getParentPath(EObject eClass) {
        EObject container = eClass.eContainer();
        String name = ""; //$NON-NLS-1$
        if (eClass instanceof ENamedElement) {
            name = ((ENamedElement) eClass).getName();
        }
        if (container != null) {
            return getParentPath(container) + "." + name; //$NON-NLS-1$
        }
        return name;
    }
}
