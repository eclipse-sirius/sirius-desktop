/**
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Utility class used to store common operations for all the item providers.
 * 
 * @author sbegaudeau
 */
public final class Utils {

    /**
     * The constructor.
     */
    private Utils() {
        // prevent instantiation
    }

    /**
     * Add default "Begin" operations with a no-op navigation to the specific
     * element.
     *
     * @param child
     *            a newly created child.
     */
    public static void addNoopNavigationOperations(Object child) {
        if (child instanceof EObject) {
            EObject obj = (EObject) child;
            for (EReference ref : obj.eClass().getEAllReferences()) {
                if (ref.isContainment() && ref.getEReferenceType() == ToolPackage.Literals.INITIAL_OPERATION) {
                    InitialOperation begin = ToolFactory.eINSTANCE.createInitialOperation();
                    ChangeContext noop = ToolFactory.eINSTANCE.createChangeContext();
                    noop.setBrowseExpression("var:self"); //$NON-NLS-1$
                    begin.setFirstModelOperations(noop);
                    obj.eSet(ref, begin);
                }
            }
        }
    }
}
