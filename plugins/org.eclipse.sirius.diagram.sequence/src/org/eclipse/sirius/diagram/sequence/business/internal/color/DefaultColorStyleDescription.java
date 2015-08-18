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
package org.eclipse.sirius.diagram.sequence.business.internal.color;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.template.TExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TLifelineStyle;
import org.eclipse.sirius.diagram.sequence.template.TMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.util.TemplateSwitch;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;

/**
 * Class responsible for setting default color values on style descriptions.
 * 
 * @author mporhel
 * 
 */
public class DefaultColorStyleDescription extends TemplateSwitch<EObject> {

    private static final String BLACK = "black"; //$NON-NLS-1$

    private static final String GRAY = "gray"; //$NON-NLS-1$

    /**
     * Set the default color descriptions on the given EObject.
     * 
     * @param theEObject
     *            the object to update.
     */
    public void setDefaultColors(final EObject theEObject) {
        doSwitch(theEObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseTLifelineStyle(TLifelineStyle object) {
        object.setLifelineColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseTLifelineStyle(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseTExecutionStyle(TExecutionStyle object) {
        object.setBorderColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        object.setBackgroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(GRAY));
        return super.caseTExecutionStyle(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseTMessageStyle(TMessageStyle object) {
        object.setStrokeColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseTMessageStyle(object);
    }
}
