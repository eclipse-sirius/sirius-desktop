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
package org.eclipse.sirius.business.internal.dialect.description;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.common.collect.Sets;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.business.internal.metamodel.helper.ComponentizationHelper;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramExtensionDescription;
import org.eclipse.sirius.viewpoint.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.EdgeMappingImport;
import org.eclipse.sirius.viewpoint.description.IEdgeMapping;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.OrderedTreeLayout;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch;

/**
 * A switch that will return the Target Types associated to a given element
 * (part of the {@link DescriptionPackage}) and feature corresponding to an
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
public class DescriptionInterpretedExpressionTargetSwitch extends DescriptionSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the
     * feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The ID of the feature containing the Interpreted expression.
     */
    protected int featureID;

    /**
     * The global switch to delegate the doSwitch method to.
     */
    protected IInterpretedExpressionTargetSwitch globalSwitch;

    private int lastFeatureID;

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression
     * @param theGlobalSwitch
     *            the global switch to use
     */
    public DescriptionInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch theGlobalSwitch) {
        super();
        this.featureID = feature != null ? feature.getFeatureID() : DO_NOT_CONSIDER_FEATURE;
        lastFeatureID = featureID;
        this.globalSwitch = theGlobalSwitch;
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

    /**
     * Changes the behavior of this switch : if true, then the feature will be
     * considered to calculate target types ; if false, then the feature will be
     * ignored.
     * 
     * @param considerFeature
     *            true if the feature should be considered, false otherwise
     */
    public void setConsiderFeature(boolean considerFeature) {
        if (considerFeature) {
            this.featureID = lastFeatureID;
        } else {
            lastFeatureID = this.featureID;
            this.featureID = DO_NOT_CONSIDER_FEATURE;
        }

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
        while ((!(container instanceof RepresentationDescription)) && (!(container instanceof RepresentationElementMapping)) && (!(container instanceof EdgeMappingImport))
                && (!(container instanceof DiagramExtensionDescription))) {
            container = container.eContainer();
        }
        if (container instanceof DiagramExtensionDescription) {
            container = ComponentizationHelper.getDiagramDescription((DiagramExtensionDescription) container, ViewpointRegistry.getInstance().getViewpoints());
        }

        if (container instanceof EdgeMappingImport) {
            IEdgeMappingQuery edgeMappingQuery = new IEdgeMappingQuery((IEdgeMapping) container);
            Option<EdgeMapping> option = edgeMappingQuery.getOriginalEdgeMapping();
            if (option.some()) {
                container = option.get();
            }
        }
        return container;
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
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseConditionalStyleDescription(org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription)
     */
    @Override
    public Option<Collection<String>> caseConditionalStyleDescription(ConditionalStyleDescription styleDescription) {
        Collection<String> target = Sets.newLinkedHashSet();
        Option<Collection<String>> result = Options.newSome(target);
        switch (featureID) {
        case DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(styleDescription), false);
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
     * @see org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch#caseOrderedTreeLayout(org.eclipse.sirius.viewpoint.description.OrderedTreeLayout)
     */
    @Override
    public Option<Collection<String>> caseOrderedTreeLayout(OrderedTreeLayout layout) {
        Collection<String> target = Sets.newLinkedHashSet();
        Option<Collection<String>> result = Options.newSome(target);
        switch (featureID) {
        case DescriptionPackage.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION:
            result = globalSwitch.doSwitch(getFirstRelevantContainer(layout), false);
            break;
        default:
            break;
        }
        return result;
    }

    @Override
    public Option<Collection<String>> caseColorDescription(ColorDescription object) {
        return Options.newNone();
    }

    @Override
    public Option<Collection<String>> caseColorStep(ColorStep object) {
        return Options.newNone();
    }

    @Override
    public Option<Collection<String>> caseInterpolatedColor(InterpolatedColor object) {
        return Options.newNone();
    }
}
