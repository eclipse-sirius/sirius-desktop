/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.dialect.description;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.Sets;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.viewpoint.description.AbstractNodeMapping;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.DiagramImportDescription;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.MappingBasedDecoration;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch;

/**
 * A switch that will return the Target Types associated to a given element
 * (here all elements are diagram-specific) and feature corresponding to an
 * Interpreted Expression. For example, for a NodeMapping :
 * <p>
 * <li>if the feature is semantic candidate expression, we return the domain
 * class of the first valid container (representation element mapping or
 * representation description).</li>
 * <li>if the feature is any other interpreted expression, we return the domain
 * class associated to this mapping</li>
 * </p>
 * 
 * Can return {@link Options#newNone()} if the given expression does not require
 * any target type (for example, a Popup menu contribution only uses variables
 * in its expressions).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DiagramInterpretedExpressionTargetSwitch extends DescriptionSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the
     * feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The feature containing the Interpreted expression.
     */
    protected EStructuralFeature feature;

    /**
     * The global switch to delegate the doSwitch method to.
     */
    protected IInterpretedExpressionTargetSwitch globalSwitch;

    /**
     * Indicates if the feature should be considered.
     */
    protected boolean considerFeature;

    /**
     * Default constructor.
     * 
     * @param feature
     *            representationDescription
     * @param targetSwitch
     *            the global switch
     */
    public DiagramInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch targetSwitch) {
        super();
        this.feature = feature;
        this.globalSwitch = targetSwitch;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> defaultResult = Sets.newLinkedHashSet();
        return Options.newSome(defaultResult);
    }

    private int getFeatureId(EClass eClass) {
        int featureID = DO_NOT_CONSIDER_FEATURE;
        if (considerFeature && feature != null) {
            featureID = eClass.getFeatureID(feature);
        }
        return featureID;
    }

    /**
     * Returns the {@link RepresentationDescription} that contains the given
     * element.
     * 
     * @param element
     *            the element to get the {@link RepresentationDescription} from
     * @return the {@link RepresentationDescription} that contains the given
     *         element, null if none found
     */
    protected EObject getRepresentationDescription(EObject element) {
        EObject container = element.eContainer();
        while (!(container instanceof RepresentationDescription)) {
            container = container.eContainer();
        }
        return container;
    }

    /**
     * Returns the first relevant for the given EObject, i.e. the first
     * container from which a domain class can be determined.
     * <p>
     * For example, for a given NodeMapping will return the first
     * ContainerMapping or DiagramRepresentationDescription that contains this
     * mapping.
     * </p>
     * 
     * @param element
     *            the element to get the container from
     * @return the first relevant for the given EObject, i.e. the first
     *         container from which a domain class can be determined
     */
    protected EObject getFirstRelevantContainer(EObject element) {
        EObject container = element.eContainer();
        while (container != null && !(container instanceof RepresentationDescription || container instanceof RepresentationExtensionDescription || container instanceof RepresentationElementMapping)) {
            container = container.eContainer();
        }
        return container;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseDiagramDescription(org.eclipse.sirius.viewpoint.description.DiagramDescription)
     */
    @Override
    public Option<Collection<String>> caseDiagramDescription(DiagramDescription diagramDescription) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
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
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseDiagramImportDescription(DiagramImportDescription object) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
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
        Collection<String> target = Sets.newLinkedHashSet();
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.MAPPING_BASED_DECORATION__PRECONDITION_EXPRESSION:
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

    @Override
    public Option<Collection<String>> caseSemanticBasedDecoration(SemanticBasedDecoration object) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.SEMANTIC_BASED_DECORATION__PRECONDITION_EXPRESSION:
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
    public Option<Collection<String>> caseVSMElementCustomization(VSMElementCustomization object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
            break;
        default:
            break;
        }

        return result;
    }

    @Override
    public Option<Collection<String>> caseEAttributeCustomization(EAttributeCustomization object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION__VALUE:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
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
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseAbstractNodeMapping(org.eclipse.sirius.viewpoint.description.AbstractNodeMapping)
     */
    @Override
    public Option<Collection<String>> caseAbstractNodeMapping(AbstractNodeMapping object) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
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
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseEdgeMapping(org.eclipse.sirius.viewpoint.description.EdgeMapping)
     */
    @Override
    public Option<Collection<String>> caseEdgeMapping(EdgeMapping edgeMapping) {
        Option<Collection<String>> result = null;
        Collection<String> target = Sets.newLinkedHashSet();
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
                    Option<Collection<String>> sourceMappingTarget = globalSwitch.doSwitch(mapping, false);
                    if (sourceMappingTarget.some()) {
                        target.addAll(sourceMappingTarget.get());
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
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseSelectionDescription(org.eclipse.sirius.viewpoint.description.SelectionDescription)
     */
    @Override
    public Option<Collection<String>> caseSelectionDescription(SelectionDescription selectionDescription) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(selectionDescription.eClass())) {
        case DescriptionPackage.SELECTION_DESCRIPTION__CANDIDATES_EXPRESSION:
        case DescriptionPackage.SELECTION_DESCRIPTION__ROOT_EXPRESSION:
        case DescriptionPackage.SELECTION_DESCRIPTION__CHILDREN_EXPRESSION:
            result = globalSwitch.doSwitch(getRepresentationDescription(selectionDescription), false);
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
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseInterpolatedColor(org.eclipse.sirius.viewpoint.description.InterpolatedColor)
     */
    @Override
    public Option<Collection<String>> caseInterpolatedColor(InterpolatedColor object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.INTERPOLATED_COLOR__COLOR_VALUE_COMPUTATION_EXPRESSION:
        case DescriptionPackage.INTERPOLATED_COLOR__MIN_VALUE_COMPUTATION_EXPRESSION:
        case DescriptionPackage.INTERPOLATED_COLOR__MAX_VALUE_COMPUTATION_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
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
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseColorStep(org.eclipse.sirius.viewpoint.description.ColorStep)
     */
    @Override
    public Option<Collection<String>> caseColorStep(ColorStep object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_VALUE:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
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
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseComputedColor(org.eclipse.sirius.viewpoint.description.ComputedColor)
     */
    @Override
    public Option<Collection<String>> caseComputedColor(ComputedColor object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(object.eClass())) {
        case DescriptionPackage.COMPUTED_COLOR__RED:
        case DescriptionPackage.COMPUTED_COLOR__GREEN:
        case DescriptionPackage.COMPUTED_COLOR__BLUE:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * Changes the behavior of this switch : if true, then the feature will be
     * considered to calculate target types ; if false, then the feature will be
     * ignored.
     * 
     * @param considerFeature
     *            true if the feature should be considered, false otherwise
     */
    public void setConsiderFeature(boolean considerFeature) {
        this.considerFeature = considerFeature;
    }
}
