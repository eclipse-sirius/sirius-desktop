/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * Utility class for managing DDiagram models.
 * 
 * @author cbrun
 */
public final class RepresentationHelper {

    /**
     * Avoid instantiation.
     */
    private RepresentationHelper() {
    }

    /**
     * Update the change id of the {@link DRepresentationDescriptor} with a new UUID.
     * 
     * @param representationDescriptor
     *            the {@link DRepresentationDescriptor} from which we want to update attached change id.
     */
    public static void updateChangeId(DRepresentationDescriptor representationDescriptor) {
        representationDescriptor.setChangeId(Long.valueOf(System.currentTimeMillis()).toString());
    }

    /**
     * Return true if content of given representation is not the same or if we cannot determined if the content have
     * diverged because of missing change id.
     * 
     * @param representationDescriptor1
     *            the first {@link DRepresentationDescriptor} to compare.
     * @param representationDescriptor2
     *            the second {@link DRepresentationDescriptor} to compare.
     * @return true if content of given representation is not the same or if we cannot determined if the content have
     *         diverged because of missing change id.
     */
    public static boolean areRepresentationsIdentical(DRepresentationDescriptor representationDescriptor1, DRepresentationDescriptor representationDescriptor2) {
        String changeId1 = representationDescriptor1.getChangeId();
        String changeId2 = representationDescriptor2.getChangeId();
        if (changeId1 == null || changeId2 == null || (!changeId1.equals(changeId2))) {
            return true;
        }
        return false;
    }

    /**
     * Get, or create if there is none, a migration annotation for a group.
     * 
     * @param source
     *            the source of the annotation
     * 
     * @param representation
     *            the DRepresentation
     * @param eObject
     *            the data of the annotation
     * @return the annotation entry
     */
    public static AnnotationEntry getOrCreateAnnotation(final String source, final DRepresentation representation, final EObject eObject) {
        AnnotationEntry annotation = new DRepresentationQuery(representation).getAnnotation(source, eObject).get();
        if (annotation == null && eObject != null) {
            annotation = DescriptionFactory.eINSTANCE.createAnnotationEntry();
            annotation.setSource(source);
            annotation.setData(eObject);
            representation.getOwnedAnnotationEntries().add(annotation);
        }
        return annotation;
    }
}
