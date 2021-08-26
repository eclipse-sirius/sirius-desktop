/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.util.DescriptionSwitch;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Switch to get the style description owned by a conditional style.
 * 
 * @author ymortier
 */
public class GetStyleDescription extends DescriptionSwitch<StyleDescription> {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseConditionalContainerStyleDescription(org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription)
     */
    @Override
    public StyleDescription caseConditionalContainerStyleDescription(final ConditionalContainerStyleDescription object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseConditionalEdgeStyleDescription(org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription)
     */
    @Override
    public StyleDescription caseConditionalEdgeStyleDescription(final ConditionalEdgeStyleDescription object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseConditionalNodeStyleDescription(org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription)
     */
    @Override
    public StyleDescription caseConditionalNodeStyleDescription(final ConditionalNodeStyleDescription object) {
        return object.getStyle();
    }

    @Override
    public StyleDescription defaultCase(final EObject object) {
        SiriusPlugin.getDefault().error(MessageFormat.format(Messages.GetStyleDescription_errorMsg, object), new RuntimeException());
        return null;
    }

}
