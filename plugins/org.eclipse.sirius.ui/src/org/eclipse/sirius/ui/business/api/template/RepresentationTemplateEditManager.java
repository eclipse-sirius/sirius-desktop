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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.ui.business.internal.template.RepresentationTemplateEditManagerImpl;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;

/**
 * Class responsible for aggregating the RepresentationTemplate Edit's .
 * 
 * @author cbrun
 * 
 */
public interface RepresentationTemplateEditManager {

    /**
     * Representation Template edit manager extension point ID.
     */
    String ID = "org.eclipse.sirius.ui.representationTemplateEdit"; //$NON-NLS-1$

    /**
     * Extension point attribute for the representation template class.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Singleton manager corresponding to a given Eclipse instance.
     */
    RepresentationTemplateEditManager INSTANCE = RepresentationTemplateEditManagerImpl.init();

    /**
     * return the newChild descriptors to provide as child of a "Sirius"
     * type.
     * 
     * @return the newChild descriptors to provide as child of a "Sirius"
     *         type.
     */
    Collection<? extends Object> provideNewChildDescriptors();

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
     * This method is useful to identify features which are part of the
     * transformation and will be overriden.
     * 
     * @param eObject
     *            the current eObject
     * 
     * @param feature
     *            the current feature.
     * @return true if the current feature will be overriden by the
     *         transformationr.
     */
    boolean isOverriden(EObject eObject, EStructuralFeature feature);

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

}
