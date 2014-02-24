/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.util.DescriptionSwitch;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
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
        SiriusPlugin.getDefault().error("Impossible to get the style description for object : " + object, new RuntimeException());
        return null;
    }

}
