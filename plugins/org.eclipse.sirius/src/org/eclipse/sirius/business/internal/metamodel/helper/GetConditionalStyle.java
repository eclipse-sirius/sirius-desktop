/*******************************************************************************
 * Copyright (c) 2012, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.helper;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.ContainerMappingImport;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMappingImport;
import org.eclipse.sirius.viewpoint.description.NodeMapping;
import org.eclipse.sirius.viewpoint.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch;

/**
 * Switch to get the conditional styles of a mapping.
 * 
 * @author ymortier
 */
public class GetConditionalStyle extends DescriptionSwitch<List<? extends ConditionalStyleDescription>> {

    /** A empty list for the default case. */
    private static final EList<ConditionalStyleDescription> EMPTY = new BasicEList<ConditionalStyleDescription>();

    private DDiagram dDiagram;

    /**
     * Default constructor.
     * 
     * @param dDiagram
     *            the {@link DDiagram} for which get a
     *            {@link ConditionalStyleDescription}
     */
    public GetConditionalStyle(final DDiagram dDiagram) {
        this.dDiagram = dDiagram;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseNodeMapping(org.eclipse.sirius.viewpoint.description.NodeMapping)
     */
    @Override
    public EList<? extends ConditionalStyleDescription> caseNodeMapping(final NodeMapping object) {
        return object.getConditionnalStyles();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseEdgeMapping(org.eclipse.sirius.viewpoint.description.EdgeMapping)
     */
    @Override
    public EList<? extends ConditionalStyleDescription> caseEdgeMapping(final EdgeMapping object) {
        // We add in priority the conditional style of the edgeMappingImport
        final EList<ConditionalEdgeStyleDescription> result = new BasicEList<ConditionalEdgeStyleDescription>(object.getConditionnalStyles());
        // and then the conditional style of the imported mapping (if
        // activated)
        result.addAll(getConditionalStyleOfImportedMapping(object));
        return result;
        // return object.getConditionnalStyles();
    }

    @Override
    public EList<? extends ConditionalStyleDescription> caseEdgeMappingImport(final EdgeMappingImport object) {
        // We add in priority the conditional style of the edgeMappingImport
        final EList<ConditionalEdgeStyleDescription> result = new BasicEList<ConditionalEdgeStyleDescription>(object.getConditionnalStyles());
        // and then the conditional style of the imported mapping (if
        // activated)
        result.addAll(getConditionalStyleOfImportedMapping(object));
        return result;
    }

    /**
     * @param object
     * @return
     */
    private Collection<? extends ConditionalEdgeStyleDescription> getConditionalStyleOfImportedMapping(final EdgeMapping object) {
        final EList<ConditionalEdgeStyleDescription> result = new BasicEList<ConditionalEdgeStyleDescription>();
        if (object instanceof EdgeMappingImport) {
            result.addAll(getConditionalStyleOfImportedMapping((EdgeMappingImport) object));
        } else if (object instanceof EdgeMappingImportWrapper) {
            result.addAll(getConditionalStyleOfImportedMapping(((EdgeMappingImportWrapper) object).getWrappedEdgeMappingImport()));
        }
        return result;
    }

    /**
     * @param object
     * @return
     */
    private Collection<? extends ConditionalEdgeStyleDescription> getConditionalStyleOfImportedMapping(final EdgeMappingImport object) {
        final EList<ConditionalEdgeStyleDescription> result = new BasicEList<ConditionalEdgeStyleDescription>();
        if (dDiagram != null) {
            Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(object.getImportedMapping()).getEdgeMapping();
            if (edgeMapping.some()) {
                result.addAll(edgeMapping.get().getConditionnalStyles());
            }
        }
        if (object.getImportedMapping() instanceof EdgeMappingImport) {
            result.addAll(getConditionalStyleOfImportedMapping((EdgeMappingImport) object.getImportedMapping()));
        }
        return result;
    }

    @Override
    public List<? extends ConditionalStyleDescription> caseContainerMappingImport(final ContainerMappingImport object) {
        List<ConditionalContainerStyleDescription> result = Lists.newArrayList();
        result.addAll(object.getConditionnalStyles());
        if (object.getImportedMapping() != null) {
            List<? extends ConditionalStyleDescription> importedContionalStyles = doSwitch(object.getImportedMapping());
            Iterables.addAll(result, Iterables.filter(importedContionalStyles, ConditionalContainerStyleDescription.class));
        }
        return result;
    }

    @Override
    public List<? extends ConditionalStyleDescription> caseNodeMappingImport(final NodeMappingImport object) {
        List<ConditionalNodeStyleDescription> result = Lists.newArrayList();
        result.addAll(object.getConditionnalStyles());
        if (object.getImportedMapping() != null) {
            List<? extends ConditionalStyleDescription> importedContionalStyles = doSwitch(object.getImportedMapping());
            Iterables.addAll(result, Iterables.filter(importedContionalStyles, ConditionalNodeStyleDescription.class));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseContainerMapping(org.eclipse.sirius.viewpoint.description.ContainerMapping)
     */
    @Override
    public EList<? extends ConditionalStyleDescription> caseContainerMapping(final ContainerMapping object) {
        return object.getConditionnalStyles();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public EList<? extends ConditionalStyleDescription> defaultCase(final EObject object) {
        SiriusPlugin.getDefault().error("Impossible to get the conditional style descriptions for object : " + object, new RuntimeException());
        return EMPTY;
    }
}
