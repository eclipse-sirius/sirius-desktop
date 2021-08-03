/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.util.DescriptionSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;

import com.google.common.collect.Iterables;

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
     *            the {@link DDiagram} for which get a {@link ConditionalStyleDescription}
     */
    public GetConditionalStyle(final DDiagram dDiagram) {
        this.dDiagram = dDiagram;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseNodeMapping(org.eclipse.sirius.diagram.description.NodeMapping)
     */
    @Override
    public EList<? extends ConditionalStyleDescription> caseNodeMapping(final NodeMapping object) {
        return object.getConditionnalStyles();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseEdgeMapping(org.eclipse.sirius.diagram.description.EdgeMapping)
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseEdgeMappingImport(org.eclipse.sirius.diagram.description.EdgeMapping)
     */
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseNodeMappingImport(org.eclipse.sirius.diagram.description.ContainerMappingImport)
     */
    @Override
    public List<? extends ConditionalStyleDescription> caseContainerMappingImport(final ContainerMappingImport object) {
        List<ConditionalContainerStyleDescription> result = new ArrayList<>();
        result.addAll(object.getConditionnalStyles());
        if (object.getImportedMapping() != null) {
            List<? extends ConditionalStyleDescription> importedContionalStyles = doSwitch(object.getImportedMapping());
            Iterables.addAll(result, Iterables.filter(importedContionalStyles, ConditionalContainerStyleDescription.class));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseNodeMappingImport(org.eclipse.sirius.diagram.description.NodeMappingImport)
     */
    @Override
    public List<? extends ConditionalStyleDescription> caseNodeMappingImport(final NodeMappingImport object) {
        List<ConditionalNodeStyleDescription> result = new ArrayList<>();
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
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseContainerMapping(org.eclipse.sirius.diagram.description.ContainerMapping)
     */
    @Override
    public EList<? extends ConditionalStyleDescription> caseContainerMapping(final ContainerMapping object) {
        return object.getConditionnalStyles();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public EList<? extends ConditionalStyleDescription> defaultCase(final EObject object) {
        SiriusPlugin.getDefault().error(MessageFormat.format(Messages.GetConditionalStyle_errorMsg, object), new RuntimeException());
        return EMPTY;
    }
}
