/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.dialect.description;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramComponentizationHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.description.util.DescriptionSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A switch that will return the Target Types associated to a given element (here all elements are diagram-specific) and
 * feature corresponding to an Interpreted Expression. For example, for a NodeMapping :
 * <p>
 * <li>if the feature is semantic candidate expression, we return the domain class of the first valid container
 * (representation element mapping or representation description).</li>
 * <li>if the feature is any other interpreted expression, we return the domain class associated to this mapping</li>
 * </p>
 * 
 * Can return {@link Options#newNone()} if the given expression does not require any target type (for example, a Popup
 * menu contribution only uses variables in its expressions).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DiagramInterpretedExpressionTargetSwitch extends DescriptionSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The feature containing the Interpreted expression.
     */
    protected EStructuralFeature feature;

    /**
     * Indicates if the feature must be considered.
     */
    protected boolean considerFeature;

    /**
     * The global switch to delegate the doSwitch method to.
     */
    protected IInterpretedExpressionTargetSwitch globalSwitch;

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression
     * @param theGlobalSwitch
     *            the global switch
     */
    public DiagramInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch theGlobalSwitch) {
        super();
        this.feature = feature;
        this.globalSwitch = theGlobalSwitch;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> defaultResult = new LinkedHashSet<>();
        return Options.newSome(defaultResult);
    }

    /**
     * Changes the behavior of this switch : if true, then the feature will be considered to calculate target types ; if
     * false, then the feature will be ignored.
     * 
     * @param considerFeature
     *            true if the feature should be considered, false otherwise
     */
    public void setConsiderFeature(boolean considerFeature) {
        this.considerFeature = considerFeature;
    }

    private int getFeatureId(EClass eClass) {
        int featureID = DO_NOT_CONSIDER_FEATURE;
        if (considerFeature && feature != null) {
            featureID = eClass.getFeatureID(feature);
        }
        return featureID;
    }

    /*
     * @see IInterpretedExpressionTargetSwitch#getFirstRelevantContainer()
     */
    private EObject getFirstRelevantContainer(EObject element) {
        return globalSwitch.getFirstRelevantContainer(element);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseDiagramDescription(org.eclipse.sirius.diagram.description.DiagramDescription)
     */
    @Override
    public Option<Collection<String>> caseDiagramDescription(DiagramDescription diagramDescription) {
        Option<Collection<String>> result = null;
        Collection<String> target = new LinkedHashSet<>();
        switch (getFeatureId(diagramDescription.eClass())) {
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION:
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION:
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            target.add(diagramDescription.getDomainClass());
            result = Options.newSome(target);
            break;
        default:
            break;
        }

        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseDiagramExtensionDescription(org.eclipse.sirius.diagram.description.DiagramExtensionDescription)
     */
    @Override
    public Option<Collection<String>> caseDiagramExtensionDescription(DiagramExtensionDescription object) {
        DiagramDescription diagramDescription = DiagramComponentizationHelper.getDiagramDescription(object, ViewpointRegistry.getInstance().getViewpoints());
        if (diagramDescription != null) {
            return doSwitch(diagramDescription);
        }
        return null;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseDiagramImportDescription(org.eclipse.sirius.diagram.description.DiagramImportDescription)
     */
    @Override
    public Option<Collection<String>> caseDiagramImportDescription(DiagramImportDescription object) {
        Option<Collection<String>> result = null;
        Collection<String> target = new LinkedHashSet<>();
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__TITLE_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            target.add(object.getDomainClass());
            result = Options.newSome(target);
            break;
        default:
            break;
        }

        return result;
    }

    @Override
    public Option<Collection<String>> caseMappingBasedDecoration(MappingBasedDecoration object) {
        Option<Collection<String>> result = null;
        Collection<String> target = new LinkedHashSet<>();
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.MAPPING_BASED_DECORATION__PRECONDITION_EXPRESSION:
        case org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION:
        case org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            for (DiagramElementMapping mapping : object.getMappings()) {
                Option<Collection<String>> mappingTargets = globalSwitch.doSwitch(mapping, false);
                if (mappingTargets.some()) {
                    target.addAll(mappingTargets.get());
                }
            }
            result = Options.newSome(target);
            break;
        default:
            break;
        }

        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseAbstractNodeMapping(org.eclipse.sirius.diagram.description.AbstractNodeMapping)
     */
    @Override
    public Option<Collection<String>> caseAbstractNodeMapping(AbstractNodeMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> target = new LinkedHashSet<>();
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
            break;
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__PRECONDITION_EXPRESSION:
        case DescriptionPackage.ABSTRACT_NODE_MAPPING__SEMANTIC_ELEMENTS:
        case DO_NOT_CONSIDER_FEATURE:
            target.add(object.getDomainClass());
            result = Options.newSome(target);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseEdgeMapping(org.eclipse.sirius.diagram.description.EdgeMapping)
     */
    @Override
    public Option<Collection<String>> caseEdgeMapping(EdgeMapping edgeMapping) {
        Option<Collection<String>> result = null;
        Collection<String> target = new LinkedHashSet<>();
        if (edgeMapping.isUseDomainElement()) {
            // DOMAIN-BASED EDGE MAPPING
            switch (getFeatureId(edgeMapping.eClass())) {
            case DescriptionPackage.EDGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
                result = globalSwitch.doSwitch(getFirstRelevantContainer(edgeMapping), false);
                break;
            case DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__PRECONDITION_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__SEMANTIC_ELEMENTS:
            case DO_NOT_CONSIDER_FEATURE:
                target.add(edgeMapping.getDomainClass());
                result = Options.newSome(target);
                break;
            default:
                break;
            }
        } else {
            // RELATION-BASED EDGE MAPPING
            switch (getFeatureId(edgeMapping.eClass())) {
            case DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
                result = Options.newNone();
                break;
            case DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__PRECONDITION_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION:
            case DescriptionPackage.EDGE_MAPPING__SEMANTIC_ELEMENTS:
            case DO_NOT_CONSIDER_FEATURE:
                for (DiagramElementMapping mapping : edgeMapping.getSourceMapping()) {
                    if (!(mapping.equals(edgeMapping))) {
                        Option<Collection<String>> sourceMappingTarget = globalSwitch.doSwitch(mapping, false);
                        if (sourceMappingTarget.some()) {
                            target.addAll(sourceMappingTarget.get());
                        }
                    }
                }
                result = Options.newSome(target);
                break;
            default:
                break;
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseEdgeMappingImport(org.eclipse.sirius.diagram.description.EdgeMappingImport)
     */
    @Override
    public Option<Collection<String>> caseEdgeMappingImport(EdgeMappingImport object) {
        IEdgeMappingQuery edgeMappingQuery = new IEdgeMappingQuery(object);
        Option<EdgeMapping> option = edgeMappingQuery.getOriginalEdgeMapping();
        if (option.some()) {
            return doSwitch(option.get());
        }

        return null;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.util.DescriptionSwitch#caseOrderedTreeLayout(OrderedTreeLayout)
     */
    @Override
    public Option<Collection<String>> caseOrderedTreeLayout(OrderedTreeLayout layout) {
        Collection<String> target = new LinkedHashSet<>();
        Option<Collection<String>> result = Options.newSome(target);
        switch (getFeatureId(DescriptionPackage.eINSTANCE.getOrderedTreeLayout())) {
        case DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(layout), false);
            break;
        default:
            break;
        }
        return result;
    }
}
