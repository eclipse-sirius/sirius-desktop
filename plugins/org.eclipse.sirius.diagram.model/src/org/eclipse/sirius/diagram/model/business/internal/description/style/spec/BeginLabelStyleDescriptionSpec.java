/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.model.business.internal.description.style.spec;

import org.eclipse.sirius.diagram.description.style.impl.BeginLabelStyleDescriptionImpl;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * A specific class to override the default labelExpression.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class BeginLabelStyleDescriptionSpec extends BeginLabelStyleDescriptionImpl {
    /**
     * The default value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @not-generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * Default constructor.
     */
    public BeginLabelStyleDescriptionSpec() {
        super();
        setLabelExpression(LABEL_EXPRESSION_EDEFAULT);
    }

    /**
     * Override to change the default value of LabelExpression.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean eIsSet(int featureID) {
        // CHECKSTYLE:OFF
        switch (featureID) {
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return LABEL_EXPRESSION_EDEFAULT == null ? super.getLabelExpression() != null : !LABEL_EXPRESSION_EDEFAULT.equals(super.getLabelExpression());
        }
        // CHECKSTYLE:ON
        return super.eIsSet(featureID);
    }

    /**
     * Override to change the default value of LabelExpression. {@inheritDoc}
     */
    @Override
    public void eUnset(int featureID) {
        // CHECKSTYLE:OFF
        switch (featureID) {
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(LABEL_EXPRESSION_EDEFAULT);
            return;
        }
        // CHECKSTYLE:ON
        super.eUnset(featureID);
    }
}
