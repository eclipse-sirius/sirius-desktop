/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
