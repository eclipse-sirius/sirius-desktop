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

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ProjectDependenciesItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ResourcesFolderItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointsFolderItem;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * Common Sorter for item wrappers.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 */
public class CommonItemSorter extends ViewerSorter {

    /**
     * Instantiate a new instance.
     */
    public CommonItemSorter() {
        super();
    }

    /**
     * Instantiate a new instance.
     * 
     * @param collator
     *            the collator
     */
    public CommonItemSorter(Collator collator) {
        super(collator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int category(Object element) {
        int category = 9;

        if (element instanceof ProjectDependenciesItem) {
            category = 1;
        } else if (element instanceof ResourcesFolderItem) {
            category = 2;
        } else if (element instanceof ViewpointsFolderItem) {
            category = 3;
        } else if (element instanceof AnalysisResourceItem) {
            category = 4;
        } else if (element instanceof IResource || element instanceof Resource) {
            category = 5;
            if (element instanceof Resource) {
                Session session = SessionManager.INSTANCE.getSession((Resource) element);
                if (session instanceof DAnalysisSessionEObject && ((DAnalysisSessionEObject) session).getControlledResources().contains(element)) {
                    category = 6;
                }
            }
        } else if (element instanceof DRepresentation) {
            category = 7;
        } else if (element instanceof EObject) {
            category = 8;
        }

        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        int result = super.compare(viewer, e1, e2);
        if (e1 instanceof RepresentationDescriptionItem && e2 instanceof RepresentationDescriptionItem) {
            result = compareRepresentationDescriptions(viewer, (RepresentationDescriptionItem) e1, (RepresentationDescriptionItem) e2);
        }
        return result;
    }

    private int compareRepresentationDescriptions(Viewer viewer, RepresentationDescriptionItem e1, RepresentationDescriptionItem e2) {
        int result = super.compare(viewer, e1, e2);
        // if different representation types, compare class names to sort
        // navigator

        if (e1.getWrappedObject() instanceof RepresentationDescription && e2.getWrappedObject() instanceof RepresentationDescription) {

            RepresentationDescription desc1 = (RepresentationDescription) e1.getWrappedObject();
            RepresentationDescription desc2 = (RepresentationDescription) e2.getWrappedObject();

            result = compareRepresentationDescriptions(desc1, desc2);
        }

        return result;
    }

    /**
     * Compare two representation description : to sort them by representation
     * type, sub type and then by name.
     * 
     * @param e1
     *            the first representation description.
     * @param e2
     *            the second representation description.
     * @return Returns an integer value. Value is less than zero if source is
     *         less than target, value is zero if source and target are equal,
     *         value is greater than zero if source is greater than target.
     */
    public static int compareRepresentationDescriptions(RepresentationDescription e1, RepresentationDescription e2) {
        String defaultName = ""; //$NON-NLS-1$
        int result = Collator.getInstance().compare(e1 != null ? e1.getName() : defaultName, e2 != null ? e2.getName() : defaultName);
        // if different representation types, compare class names to sort
        // navigator
        if (e1 != null && e2 != null) {
            EClass e1Class = e1.eClass();
            EClass e2Class = e2.eClass();
            if (e1Class != null && !e1Class.equals(e2Class)) {
                result = Collator.getInstance().compare(e1Class.getName(), e2Class.getName());

                EClass e1TypeToCompare = getRepresentationType(e1);
                EClass e2TypeToCompare = getRepresentationType(e2);

                if (e1TypeToCompare != null && e2TypeToCompare != null && !e1TypeToCompare.equals(e2TypeToCompare)) {
                    result = Collator.getInstance().compare(e1TypeToCompare.getName(), e2TypeToCompare.getName());
                }
            }
        }
        return result;
    }

    /*
     * This method looks for the type which is a direct sub type of
     * RepresentationDescription.
     */
    private static EClass getRepresentationType(RepresentationDescription description) {
        return lookForRepresentationDescriptionType(description.eClass());
    }

    private static EClass lookForRepresentationDescriptionType(EClass eClass) {
        EClass repDescType = null;
        if (eClass.getESuperTypes().contains(DescriptionPackage.eINSTANCE.getRepresentationDescription())) {
            repDescType = eClass;
        } else if (eClass.getEAllSuperTypes().contains(DescriptionPackage.eINSTANCE.getRepresentationDescription())) {
            for (EClass superType : eClass.getESuperTypes()) {
                EClass t = lookForRepresentationDescriptionType(superType);
                if (t != null) {
                    repDescType = t;
                    break;
                }
            }
        }
        return repDescType;
    }
}
