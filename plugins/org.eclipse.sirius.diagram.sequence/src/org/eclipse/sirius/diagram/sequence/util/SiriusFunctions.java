/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.util;

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;

import org.eclipse.sirius.DRepresentationElement;

/**
 * Helper functions to use Sirius elements with Google Collections.
 * 
 * @author pcdavid
 */
public final class SiriusFunctions {
    private SiriusFunctions() {
        // Prevents instantiation.
    }

    /**
     * Returns a function to obtain the semantic target element from any
     * {@link DRepresentationElement}.
     * 
     * @return a function to obtain the semantic target element from any
     *         {@link DRepresentationElement}.
     */
    public static Function<DRepresentationElement, EObject> targetElementFunction() {
        return TargetElementFunction.INSTANCE;
    }

    /**
     * A function to obtain the semantic target element from any
     * {@link DRepresentationElement}.
     * 
     * @author pcdavid
     */
    private static enum TargetElementFunction implements Function<DRepresentationElement, EObject> {
        INSTANCE;

        public EObject apply(DRepresentationElement from) {
            if (from != null) {
                return from.getTarget();
            } else {
                return null;
            }
        }

        @Override
        public String toString() {
            return "targetElement";
        }
    };
}
