/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.viewpoint.BeginLabelStyle;
import org.eclipse.sirius.viewpoint.EdgeStyle;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Begin Label Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class BeginLabelStyleImpl extends BasicLabelStyleImpl implements BeginLabelStyle {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BeginLabelStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.BEGIN_LABEL_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BasicLabelStyleDescription getDescription() {
        return ((EdgeStyleDescription) ((EdgeStyle) eContainer()).getDescription()).getBeginLabelStyleDescription();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public void setDescription(BasicLabelStyleDescription description) {
        ((EdgeStyleDescription) ((EdgeStyle) eContainer()).getDescription()).setBeginLabelStyleDescription((BeginLabelStyleDescription) description);
    }

} // BeginLabelStyleImpl
