/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator.sorter;

import java.text.Collator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * Common sorter for representation descriptors.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 */
public class RepresentationInSemanticSorter extends ViewerSorter {

    /**
     * Instantiate a new instance.
     */
    public RepresentationInSemanticSorter() {
    }

    /**
     * Instantiate a new instance.
     * 
     * @param collator
     *            the collator
     */
    public RepresentationInSemanticSorter(Collator collator) {
        super(collator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int category(Object element) {
        int category = 3;

        if (element instanceof DRepresentationDescriptor) {
            category = 1;
        } else if (element instanceof EObject) {
            category = 2;
        }
        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        int result = 0;
        if (e1 instanceof DRepresentationDescriptor && e2 instanceof DRepresentationDescriptor) {
            result = compareRepresentations(viewer, (DRepresentationDescriptor) e1, (DRepresentationDescriptor) e2);
        } else if (e1 instanceof DRepresentationDescriptor) {
            result = -1;
        } else if (e2 instanceof DRepresentationDescriptor) {
            result = 1;
        }
        return result;
    }

    private int compareRepresentations(Viewer viewer, DRepresentationDescriptor e1, DRepresentationDescriptor e2) {
        String defaultName = ""; //$NON-NLS-1$
        int result = Collator.getInstance().compare(e1 != null ? e1.getName() : defaultName, e2 != null ? e2.getName() : defaultName);
        // if different representation types, compare class names to sort
        // navigator
        if (e1 != null && e2 != null && e1.eClass() != null) {
            if (!e1.eClass().equals(e2.eClass())) {
                result = Collator.getInstance().compare(e1.eClass().getName(), e2.eClass().getName());
            } else {
                RepresentationDescription d1 = e1.getDescription();
                RepresentationDescription d2 = e2.getDescription();

                if (d1 != null && d2 != null && d1.eClass() != null && !d1.eClass().equals(d2.eClass())) {
                    result = Collator.getInstance().compare(d1.eClass().getName(), d2.eClass().getName());
                }
            }
        }

        return result;
    }
}
