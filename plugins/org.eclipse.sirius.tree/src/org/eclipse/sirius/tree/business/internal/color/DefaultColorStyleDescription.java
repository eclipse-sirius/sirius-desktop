/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.color;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.tree.description.util.DescriptionSwitch;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;

/**
 * Class responsible for setting default color values on style descriptions.
 * 
 * @author nlepine
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
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseLabelStyleDescription(org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription)
     */
    @Override
    public EObject caseLabelStyleDescription(final LabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseLabelStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.description.util.DescriptionSwitch#caseTreeItemStyleDescription(org.eclipse.sirius.tree.description.TreeItemStyleDescription)
     */
    @Override
    public EObject caseTreeItemStyleDescription(TreeItemStyleDescription object) {
        object.setBackgroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(WHITE));
        return super.caseTreeItemStyleDescription(object);
    }

}
