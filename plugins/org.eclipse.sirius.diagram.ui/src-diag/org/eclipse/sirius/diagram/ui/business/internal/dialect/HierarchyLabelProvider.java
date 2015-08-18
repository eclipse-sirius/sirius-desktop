/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.swt.graphics.Image;

/**
 * A label provider for mappings to display their hierarchy.
 * 
 * @author mchauvin
 */
public class HierarchyLabelProvider extends LabelProvider {

    private static final String DELIMITER = " > "; //$NON-NLS-1$

    private ILabelProvider wrappedProvider;

    /**
     * Create a new instance with wrapped label provider as base type.
     * 
     * @param wrappedLabelProvider
     *            the wrapped label provider
     */
    public HierarchyLabelProvider(final ILabelProvider wrappedLabelProvider) {
        this.wrappedProvider = wrappedLabelProvider;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        return wrappedProvider != null ? wrappedProvider.getImage(element) : super.getImage(element);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        if (element instanceof EObject && isClassOfDescriptionPackageOrSubPackage(((EObject) element).eClass())) {
            EObject eObject = (EObject) element;
            String text = getLabel(eObject);
            EObject container = eObject.eContainer();
            while (container != null) {
                text = getLabel(container) + DELIMITER + text;
                container = container.eContainer();
            }
            return text;
        }
        return wrappedProvider != null ? wrappedProvider.getText(element) : super.getText(element);
    }

    private boolean isClassOfDescriptionPackageOrSubPackage(EClass eClass) {
        boolean isClassOfDescriptionPackageOrSubPackage = false;
        EPackage ePackage = eClass.getEPackage();
        while (!isClassOfDescriptionPackageOrSubPackage && ePackage != null) {
            isClassOfDescriptionPackageOrSubPackage = ePackage == DescriptionPackage.eINSTANCE;
            ePackage = ePackage.getESuperPackage();
        }
        return isClassOfDescriptionPackageOrSubPackage;
    }

    private String getLabel(final EObject eObject) {
        String label = "Element whithout name";
        if (eObject instanceof IdentifiedElement) {
            label = new IdentifiedElementQuery((IdentifiedElement) eObject).getLabel();
        } else if (eObject instanceof RepresentationExtensionDescription) {
            label = ((RepresentationExtensionDescription) eObject).getName();
        } else {
            label = wrappedProvider.getText(eObject);
        }
        return label;
    }

}
