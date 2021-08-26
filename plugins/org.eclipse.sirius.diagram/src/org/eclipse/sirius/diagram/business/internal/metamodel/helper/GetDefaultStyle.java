/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.util.DescriptionSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

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
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseNodeMappingImport(org.eclipse.sirius.diagram.description.NodeMappingImport)
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
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseNodeMapping(org.eclipse.sirius.diagram.description.NodeMapping)
     */
    @Override
    public StyleDescription caseNodeMapping(final NodeMapping object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseEdgeMapping(org.eclipse.sirius.diagram.description.EdgeMapping)
     */
    @Override
    public StyleDescription caseEdgeMapping(final EdgeMapping object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseEdgeMappingImport(org.eclipse.sirius.diagram.description.EdgeMappingImport)
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
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseContainerMappingImport(org.eclipse.sirius.diagram.description.ContainerMappingImport)
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
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseContainerMapping(org.eclipse.sirius.diagram.description.ContainerMapping)
     */
    @Override
    public StyleDescription caseContainerMapping(final ContainerMapping object) {
        return object.getStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public StyleDescription defaultCase(final EObject object) {
        return null;
    }
}
