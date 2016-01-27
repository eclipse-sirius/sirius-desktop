/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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

import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Provider able to return a list of MetamodelDescriptor from viewpoints.
 * Implementers can declare MetamodelDescriptors even if no Viewpoint is
 * enabled.
 * 
 * @author cbrun
 * 
 */
public interface MetamodelDescriptorProvider {
    /**
     * Return the list of metamodel descritor provided by the selected
     * viewpoints.
     * 
     * @param vps
     *            A list of selected Viewpoints. This list might be empty and
     *            yet the adopter might want to provide metamodel descriptors
     *            anyway.
     * @return the list of metamodel descritor provided by the viewpoint.
     */
    Collection<MetamodelDescriptor> provides(Collection<Viewpoint> vps);
}
