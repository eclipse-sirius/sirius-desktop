/**
 * Copyright (c) 2015 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.diagram.business.internal.dialect;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.business.api.extender.MetamodelDescriptorProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Provides the descriptors for the diagram metamodels of Sirius.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 *
 */
public class DiagramMetamodelsProvider implements MetamodelDescriptorProvider {

    @Override
    public Collection<MetamodelDescriptor> provides(Collection<Viewpoint> vps) {
        Set<MetamodelDescriptor> result = new LinkedHashSet<>();
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.DiagramPackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.description.filter.FilterPackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.description.concern.ConcernPackage.eINSTANCE));
        return result;
    }

}
