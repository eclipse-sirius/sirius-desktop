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
package org.eclipse.sirius.table.business.internal.color;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.util.DescriptionSwitch;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;

/**
 * Class responsible for setting default color values on style descriptions.
 * 
 * @author mporhel
 * 
 */
public class DefaultColorStyleDescription extends DescriptionSwitch<EObject> {

    private static final String BLACK = "black"; //$NON-NLS-1$

    private static final String WHITE = "white"; //$NON-NLS-1$

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
    public EObject caseForegroundStyleDescription(ForegroundStyleDescription object) {
        object.setForeGroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseForegroundStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseBackgroundStyleDescription(BackgroundStyleDescription object) {
        object.setBackgroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(WHITE));
        return super.caseBackgroundStyleDescription(object);
    }
}
