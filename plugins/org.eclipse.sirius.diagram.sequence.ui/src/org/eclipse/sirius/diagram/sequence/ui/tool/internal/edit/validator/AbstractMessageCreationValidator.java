/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;

/**
 * Abstract validator to check if a message creation request is valid.
 * 
 * @author edugueperoux
 */
public abstract class AbstractMessageCreationValidator {

    /**
     * {@link ISequenceElement} source.
     */
    protected ISequenceElement sequenceElementSource;

    /**
     * {@link ISequenceElement} target.
     */
    protected ISequenceElement sequenceElementTarget;

    /**
     * coordinate of the click on the {@link ISequenceElement} source.
     */
    protected Point firstClickLocation;

    /**
     * coordinate of the click on the {@link ISequenceElement} target.
     */
    protected Point secondClickLocation;

    /**
     * Check if a message creation request is valid.
     * 
     * @param request
     *            the {@link CreateConnectionRequest} of a message creation to
     *            validate
     * 
     * @return true if request is valid
     */
    public boolean isValid(CreateConnectionRequest request) {
        // Objects.requireNonNull(sequenceElementSource,
        // "validator must know on which ISequenceElement check the request validation");
        // Objects.requireNonNull(sequenceElementTarget,
        // "validator must know on which ISequenceElement check the request validation");
        // Objects.requireNonNull(firstClickLocation,
        // "validator must know the click on the first ISequenceElement");
        // Objects.requireNonNull(secondClickLocation,
        // "validator must know the click on the second ISequenceElement");
        boolean valid = true;

        valid = valid && sequenceElementSource != null;
        valid = valid && sequenceElementTarget != null;
        valid = valid && firstClickLocation != null;
        valid = valid && secondClickLocation != null;

        return valid;
    }

    /**
     * Setter for {@link ISequenceElement} source.
     * 
     * @param elementSource
     *            the {@link ISequenceElement} source
     */
    public void setSource(ISequenceElement elementSource) {
        this.sequenceElementSource = elementSource;
    }

    /**
     * Setter for {@link ISequenceElement} target.
     * 
     * @param elementTarget
     *            the {@link ISequenceElement} target
     */
    public void setTarget(ISequenceElement elementTarget) {
        this.sequenceElementTarget = elementTarget;
    }

    /**
     * {@inheritDoc}
     */
    public void setFirstClickLocation(Point firstClickLocation) {
        this.firstClickLocation = firstClickLocation;
    }

    /**
     * {@inheritDoc}
     */
    public void setSecondClickLocation(Point secondClickLocation) {
        this.secondClickLocation = secondClickLocation;
    }

}
