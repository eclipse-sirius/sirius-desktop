/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.dialect;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * A label provider for mappings to display their hierarchy.
 *
 * @author mchauvin
 */
public class HierarchyLabelProvider extends org.eclipse.sirius.ui.business.api.dialect.HierarchyLabelProvider {

    /**
     * Create a new instance with wrapped label provider as base type.
     *
     * @param wrappedLabelProvider
     *            the wrapped label provider
     */
    public HierarchyLabelProvider(final ILabelProvider wrappedLabelProvider) {
        super(wrappedLabelProvider);
    }

    @Override
    protected boolean handles(EObject element) {
        EClass eClass = element.eClass();
        boolean isClassOfDescriptionPackageOrSubPackage = false;
        EPackage ePackage = eClass.getEPackage();
        while (!isClassOfDescriptionPackageOrSubPackage && ePackage != null) {
            isClassOfDescriptionPackageOrSubPackage = ePackage == DescriptionPackage.eINSTANCE;
            ePackage = ePackage.getESuperPackage();
        }
        return isClassOfDescriptionPackageOrSubPackage;
    }
}
