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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * ConstraintStatus wrapping another ConstraintStatus but being able to provide information about the image path. Every
 * call will get delegated to the wrapped status.
 * 
 * @author lfasani
 */
public class ImagePathWrappingStatus extends ConstraintStatus {

    private IStatus wrapped;

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
        super(emfStatus.getConstraint(), emfStatus.getTarget());
        this.wrapped = emfStatus;
        this.imagePathTarget = imagePathTarget;
        this.notReachableImagePath = notReachableImagePath;
    }

    @Override
    public IStatus[] getChildren() {
        return wrapped.getChildren();
    }

    @Override
    public int getCode() {
        return wrapped.getCode();
    }

    @Override
    public Throwable getException() {
        return wrapped.getException();
    }

    @Override
    public String getMessage() {
        return wrapped.getMessage();
    }

    @Override
    public String getPlugin() {
        return wrapped.getPlugin();
    }

    @Override
    public int getSeverity() {
        return wrapped.getSeverity();
    }

    @Override
    public boolean isMultiStatus() {
        return wrapped.isMultiStatus();
    }

    @Override
    public boolean isOK() {
        return wrapped.isOK();
    }

    @Override
    public boolean matches(final int arg0) {
        return wrapped.matches(arg0);
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

    @Override
    public String toString() {
        return wrapped.toString();
    }
}
