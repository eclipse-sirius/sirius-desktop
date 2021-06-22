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
package org.eclipse.sirius.table.business.internal.metamodel.color;

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
