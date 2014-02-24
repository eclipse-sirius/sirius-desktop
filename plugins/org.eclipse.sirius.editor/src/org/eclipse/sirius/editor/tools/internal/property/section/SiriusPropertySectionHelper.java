/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.property.section;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;

/**
 * Helper for AbstractSiriusPropertySection generated class.
 * 
 * @author fbarbin
 * 
 */
public final class SiriusPropertySectionHelper {
    private SiriusPropertySectionHelper() {
        // Prevent instanciation.
    }

    /**
     * Check if the given eObject is a child of the representation description.
     * 
     * @param eObject
     *            the eObject.
     * @param representationDescription
     *            the representation description.
     * @return true if eObject is a child or is equals to representation
     *         description otherwise false.
     */
    public static boolean isChildOfRepresentationDescription(EObject eObject, RepresentationDescription representationDescription) {
        if (eObject.equals(representationDescription)) {
            return true;
        } else {
            Option<EObject> desc = new EObjectQuery(eObject).getFirstAncestorOfType(DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION);
            return desc.some() && desc.get().equals(representationDescription);
        }
    }

    /**
     * Provides the first RepresentationImportDescription found into given
     * selection.
     * 
     * @param selection
     *            the selection.
     * @return the representation import description or <code>null</code> if not
     *         found.
     */
    public static RepresentationImportDescription getRepresentationImportDescriptionInSelection(ISelection selection) {
        if (selection instanceof ITreeSelection) {
            TreePath treePath = ((ITreeSelection) selection).getPaths()[0];
            for (int i = 0; i < treePath.getSegmentCount(); i++) {
                if (treePath.getSegment(i) instanceof RepresentationImportDescription) {
                    return (RepresentationImportDescription) treePath.getSegment(i);
                }
            }
        }
        return null;
    }
}
