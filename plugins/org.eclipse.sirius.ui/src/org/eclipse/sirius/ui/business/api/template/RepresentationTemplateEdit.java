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
package org.eclipse.sirius.ui.business.api.template;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;

/**
 * You should implement this interface to provide edit support for a given
 * representation template.
 * 
 * @author cbrun
 * 
 */
public interface RepresentationTemplateEdit {
    /**
     * This method is useful to identify computed versus non-computed objects.
     * Implementation should be fast as it will be called quite often.
     * 
     * @param vsmObject
     *            any object from the Viewpoint Specification Model.
     * @return true if the given object is automatically computed based on
     *         another.
     */
    boolean isGenerated(EObject vsmObject);

    /**
     * This method is useful to identify features the user should not change as
     * their values will be overiden on the next refresh.
     * 
     * @param eObj
     *            current eObject.
     * 
     * @param feature
     *            feature to tell about being overiden or not
     * @return true if the given feature is overiden by the refresh process.
     */
    boolean isOverriden(EObject eObj, EStructuralFeature feature);

    /**
     * Provide traceability information between produced objects and original
     * ones.
     * 
     * @param produced
     *            an automatically generated object.
     * @return the original object leading to the production of this instance.
     */
    EObject getSourceElement(EObject produced);

    /**
     * Install the manager on the given template. Once installed it is
     * responsible for updating the RepresentationDescription instances.
     * 
     * @param template
     *            template to install on.
     */
    void update(RepresentationTemplate template);

    /**
     * return the commandparameter instance needed for the computation of the
     * new child descriptor menu.
     * 
     * @return the commandparameter instance needed for the computation of the
     *         new child descriptor menu.
     */
    Object getNewChildDescriptor();

}
