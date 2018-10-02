/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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
package org.eclipse.sirius.properties.core.internal;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.business.api.extender.MetamodelDescriptorProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * The descriptor of the Properties metamodel.
 * 
 * @author sbegaudeau
 */
public class PropertiesMetamodelDescriptorProvider implements MetamodelDescriptorProvider {
    @Override
    public Collection<MetamodelDescriptor> provides(Collection<Viewpoint> vps) {
        Set<MetamodelDescriptor> result = new LinkedHashSet<>();
        result.add(new EcoreMetamodelDescriptor(PropertiesPackage.eINSTANCE));
        return result;
    }
}
