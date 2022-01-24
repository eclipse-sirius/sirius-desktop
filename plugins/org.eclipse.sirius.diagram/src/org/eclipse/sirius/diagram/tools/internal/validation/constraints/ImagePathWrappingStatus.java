/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.validation.constraints;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * ConstraintStatus holding attributes specific to image path constraint.
 * 
 * @author lfasani
 */
public class ImagePathWrappingStatus extends ConstraintStatus {

    private String notReachableImagePath;

    private EAttribute eAttribute;

    private ImagePathTarget imagePathTarget;

    /**
     * The object kind on which the wrong image path marker is associated to.
     */
    public enum ImagePathTarget {
        /**
         * WorksapceImage invalid path.
         */
        WORKSPACE_IMAGE,
        /**
         * Invalid path in representation descriptor rich text attribute.
         */
        DREPRESENTATION_DESCRIPTOR,
        /**
         * Invalid path in semantic element rich text attribute.
         */
        SEMANTIC_TARGET
    };

    /**
     * Build a new Wrapper.
     * 
     * @param emfStatus
     *            wrapped status.
     * @param imagePathTarget
     *            type of target element
     * @param notReachableImagePath
     *            the image path.
     */
    public ImagePathWrappingStatus(final ConstraintStatus emfStatus, ImagePathTarget imagePathTarget, final String notReachableImagePath) {
        super(emfStatus.getConstraint(), emfStatus.getTarget(), emfStatus.getMessage(), emfStatus.getResultLocus());
        this.imagePathTarget = imagePathTarget;
        this.notReachableImagePath = notReachableImagePath;
    }

    public String getNotReachableImagePath() {
        return notReachableImagePath;
    }

    public void setNotReachableImagePath(String notReachableImagePath) {
        this.notReachableImagePath = notReachableImagePath;
    }

    public EAttribute getEAttribute() {
        return eAttribute;
    }

    public void setEAttribute(EAttribute attr) {
        this.eAttribute = attr;
    }

    public ImagePathTarget getImagePathTarget() {
        return this.imagePathTarget;
    }

}
