/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel;

import java.util.Collection;
import java.util.Set;

import org.eclipse.sirius.business.api.extender.MetamodelDescriptorProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Sets;

/**
 * Provides the descriptors for the sequence diagram metamodels of Sirius.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 *
 */
public class SequenceDiagramMetamodelsProvider implements MetamodelDescriptorProvider {

    @Override
    public Collection<MetamodelDescriptor> provides(Collection<Viewpoint> vp) {
        Set<MetamodelDescriptor> result = Sets.newLinkedHashSet();
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.sequence.SequencePackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage.eINSTANCE));
        result.add(new EcoreMetamodelDescriptor(org.eclipse.sirius.diagram.sequence.template.TemplatePackage.eINSTANCE));
        return result;
    }
    
}
