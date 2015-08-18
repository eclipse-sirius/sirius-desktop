/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.extender;

import java.util.Collection;

import org.eclipse.sirius.business.internal.extender.MetamodelDescriptorManagerImpl;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * The metamodel descriptor manager.
 * 
 * @author cbrun
 */
public interface MetamodelDescriptorManager {
    /**
     * Singleton instance of the dialect manager.
     */
    MetamodelDescriptorManager INSTANCE = MetamodelDescriptorManagerImpl.init();

    /**
     * Metamodel manager extension point ID.
     */
    String ID = "org.eclipse.sirius.mmdescriptor"; //$NON-NLS-1$

    /**
     * Extension point attribute for the dialect class.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Return the list of provided MetamodelDescriptors from the selected
     * viewpoints.
     * 
     * @param enabledRepresentationDescriptions
     *            the enabled descriptions.
     * @return the list of provided MetamodelDescriptors from the selected
     *         viewpoints.
     */
    Collection<MetamodelDescriptor> provides(Collection<Viewpoint> enabledRepresentationDescriptions);

}
