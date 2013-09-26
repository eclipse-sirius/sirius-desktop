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
package org.eclipse.sirius.business.internal.query;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.IVSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;

/**
 * A Query on {@link Customization}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CustomizationQuery {

    private Customization customization;

    /**
     * Default constructor.
     * 
     * @param customization
     *            the {@link Customization} to query
     */
    public CustomizationQuery(Customization customization) {
        this.customization = customization;
    }

    /**
     * Get the {@link VSMElementCustomization}s owned by this
     * {@link Customization}.
     * 
     * @return the {@link VSMElementCustomization}s owned by this
     *         {@link Customization}
     */
    public List<VSMElementCustomization> getVSMElementCustomizations() {
        List<VSMElementCustomization> vsmElementCustomizations = new ArrayList<VSMElementCustomization>();
        for (IVSMElementCustomization vsmElementCustomization : customization.getVsmElementCustomizations()) {
            if (vsmElementCustomization instanceof VSMElementCustomization) {
                vsmElementCustomizations.add((VSMElementCustomization) vsmElementCustomization);
            }
        }
        return vsmElementCustomizations;
    }

    /**
     * the {@link VSMElementCustomizationReuse}s owned by this
     * {@link Customization}.
     * 
     * @return the {@link VSMElementCustomizationReuse}s owned by this
     *         {@link Customization}
     */
    public List<VSMElementCustomizationReuse> getVSMElementCustomzationReuses() {
        List<VSMElementCustomizationReuse> vsmElementCustomizationReuses = new ArrayList<VSMElementCustomizationReuse>();
        for (IVSMElementCustomization vsmElementCustomization : customization.getVsmElementCustomizations()) {
            if (vsmElementCustomization instanceof VSMElementCustomizationReuse) {
                vsmElementCustomizationReuses.add((VSMElementCustomizationReuse) vsmElementCustomization);
            }
        }
        return vsmElementCustomizationReuses;
    }
}
