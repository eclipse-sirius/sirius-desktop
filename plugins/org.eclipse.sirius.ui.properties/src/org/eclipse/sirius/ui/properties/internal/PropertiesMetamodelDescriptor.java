/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.Collection;
import java.util.Set;

import org.eclipse.sirius.business.api.extender.MetamodelDescriptorProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Sets;

public class PropertiesMetamodelDescriptor implements MetamodelDescriptorProvider {

    @Override
    public Collection<MetamodelDescriptor> provides(Collection<Viewpoint> vps) {
        Set<MetamodelDescriptor> result = Sets.newLinkedHashSet();
        result.add(new EcoreMetamodelDescriptor(PropertiesPackage.eINSTANCE));
        return result;
    }

}
