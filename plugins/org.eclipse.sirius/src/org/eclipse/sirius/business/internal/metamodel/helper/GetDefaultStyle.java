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
package org.eclipse.sirius.business.internal.metamodel.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMappingImport;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMappingImport;
import org.eclipse.sirius.viewpoint.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch;

/**
 * Swith to get the default style description of a mapping.
 * 
 * @author ymortier
 */
public class GetDefaultStyle extends DescriptionSwitch<StyleDescription> {

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseNodeMappingImport(org.eclipse.sirius.viewpoint.description.NodeMappingImport)
     */
    @Override
    public StyleDescription caseNodeMappingImport(final NodeMappingImport object) {
        if (object.getStyle() == null && object.getImportedMapping() != null) {
            return doSwitch(object.getImportedMapping());
        }
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseNodeMapping(org.eclipse.sirius.viewpoint.description.NodeMapping)
     */
    @Override
    public StyleDescription caseNodeMapping(final NodeMapping object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseEdgeMapping(org.eclipse.sirius.viewpoint.description.EdgeMapping)
     */
    @Override
    public StyleDescription caseEdgeMapping(final EdgeMapping object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseEdgeMappingImport(org.eclipse.sirius.viewpoint.description.EdgeMappingImport)
     */
    @Override
    public StyleDescription caseEdgeMappingImport(final EdgeMappingImport object) {
        if (object.getImportedMapping() instanceof EdgeMappingImport) {
            return caseEdgeMappingImport((EdgeMappingImport) object.getImportedMapping());
        }
        Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(object.getImportedMapping()).getEdgeMapping();
        return edgeMapping.some() ? edgeMapping.get().getStyle() : null;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseContainerMappingImport(org.eclipse.sirius.viewpoint.description.ContainerMappingImport)
     */
    @Override
    public StyleDescription caseContainerMappingImport(final ContainerMappingImport object) {
        if (object.getStyle() == null && object.getImportedMapping() != null) {
            return doSwitch(object.getImportedMapping());
        } else {
            return object.getStyle();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseContainerMapping(org.eclipse.sirius.viewpoint.description.ContainerMapping)
     */
    @Override
    public StyleDescription caseContainerMapping(final ContainerMapping object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public StyleDescription defaultCase(final EObject object) {
        return null;
    }
}
