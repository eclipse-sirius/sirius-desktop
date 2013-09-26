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
package org.eclipse.sirius.business.internal.metamodel.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch;

/**
 * Switch to get the style description owned by a conditional style.
 * 
 * @author ymortier
 */
public class GetStyleDescription extends DescriptionSwitch<StyleDescription> {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseConditionalContainerStyleDescription(org.eclipse.sirius.viewpoint.description.ConditionalContainerStyleDescription)
     */
    @Override
    public StyleDescription caseConditionalContainerStyleDescription(final ConditionalContainerStyleDescription object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseConditionalEdgeStyleDescription(org.eclipse.sirius.viewpoint.description.ConditionalEdgeStyleDescription)
     */
    @Override
    public StyleDescription caseConditionalEdgeStyleDescription(final ConditionalEdgeStyleDescription object) {
        return object.getStyle();
    }

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
