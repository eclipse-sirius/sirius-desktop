/*******************************************************************************
 * Copyright (c) 2009-2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.eef.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.adapters.SemanticAdapter;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Helper to get an EEF {@link SemanticAdapter} from a Sirius element. If the
 * element given by Sirius is a {@link DSemanticDecorator}, we take the
 * semantic target instead of the decorator.
 * 
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class VPDecoratorHelper {
    private final EObject target;

    /**
     * Constructor.
     * 
     * @param target
     *            the element to adapt.
     */
    public VPDecoratorHelper(EObject target) {
        this.target = target;
    }

    /**
     * Tests whether this help can adapt the specified target.
     * 
     * @return <code>true</code> if we can adapt the specified target.
     */
    public boolean canAdapt() {
        return target != null;
    }

    /**
     * Returns a {@link SemanticAdapter} from the specified target, or
     * <code>null</code> if we can not adapt the target.
     * 
     * @return a {@link SemanticAdapter} from the specified target.
     */
    public SemanticAdapter createSemanticAdapterFromDSemanticDecorator() {
        SemanticAdapter result = null;
        if (canAdapt()) {
            if (ViewpointPackage.eINSTANCE.getDSemanticDecorator().isInstance(target)) {
                result = new SemanticAdapter() {
                    public EObject getEObject() {
                        return ((DSemanticDecorator) target).getTarget();
                    }
                };
            } else {
                result = new SemanticAdapter() {
                    public EObject getEObject() {
                        return target;
                    }
                };
            }
        }
        return result;
    }

}
