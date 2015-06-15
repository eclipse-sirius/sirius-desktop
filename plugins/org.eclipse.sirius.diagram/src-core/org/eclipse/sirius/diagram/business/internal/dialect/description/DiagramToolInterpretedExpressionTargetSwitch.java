/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
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
import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.diagram.business.api.query.MappingBasedToolDescriptionQuery;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.description.tool.DeleteHookParameter;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.util.ToolSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A switch that will return the Target Types associated to a given element
 * (part of the {@link ToolPackage}) and feature corresponding to an Interpreted
 * Expression. For example, for a NodeMapping :
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
 */
public class DiagramToolInterpretedExpressionTargetSwitch extends ToolSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the
     * feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The ID of the feature containing the Interpreted expression.
     */
    protected EStructuralFeature feature;

    /**
     * Indicates if the feature should be considered.
     */
    protected boolean considerFeature;

    private IInterpretedExpressionTargetSwitch globalSwitch;

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression
     * @param defaultInterpretedExpressionTargetSwitch
     *            the global switch to use
     */
    public DiagramToolInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch defaultInterpretedExpressionTargetSwitch) {
        super();
        this.feature = feature;
        this.globalSwitch = defaultInterpretedExpressionTargetSwitch;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> targets = Collections.emptySet();
        return Options.newSome(targets);
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

    private int getFeatureId(EClass eClass) {
        int featureID = DO_NOT_CONSIDER_FEATURE;
        if (considerFeature && feature != null) {
            featureID = eClass.getFeatureID(feature);
        }
        return featureID;
    }

    /**
     * Returns the first element that changes the context of expressions. For
     * example : for a given operation, will return the first ChangeContext or
     * AbstractTool that contains it.
     * 
     * @param element
     *            the element to get the container from
     * @return the first relevant for the given EObject, i.e. the first
     *         container from which a domain class can be determined
     */
    protected EObject getFirstContextChangingContainer(EObject element) {
        EObject container = element.eContainer();
        while (!isChangingContextElement(container)) {
            container = container.eContainer();
        }
        return container;
    }

    private boolean isChangingContextElement(EObject element) {
        boolean descCanChange = element instanceof RepresentationDescription || element instanceof RepresentationElementMapping;
        boolean operationCanChange = element instanceof CreateInstance || element instanceof ChangeContext;
        boolean toolCanChange = element instanceof AbstractToolDescription;
        return descCanChange || operationCanChange || toolCanChange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseRequestDescription(RequestDescription object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getRequestDescription())) {
        case ToolPackage.REQUEST_DESCRIPTION__PRECONDITION:
        case ToolPackage.REQUEST_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
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
    public Option<Collection<String>> caseBehaviorTool(BehaviorTool object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getBehaviorTool())) {
        case ToolPackage.BEHAVIOR_TOOL__PRECONDITION:
        case ToolPackage.BEHAVIOR_TOOL__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            result = Options.newNone();
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
    public Option<Collection<String>> caseEdgeCreationDescription(EdgeCreationDescription object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getEdgeCreationDescription())) {
        case ToolPackage.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION:
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> targets = Sets.newLinkedHashSet();
            for (RepresentationElementMapping correspondingMapping : Iterables.concat(object.getEdgeMappings(), object.getExtraSourceMappings())) {
                Option<Collection<String>> targetsFromMapping = globalSwitch.doSwitch(correspondingMapping, false);
                if (targetsFromMapping.some()) {
                    targets.addAll(targetsFromMapping.get());
                }
            }
            result = Options.newSome(targets);
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
    public Option<Collection<String>> caseDeleteHookParameter(DeleteHookParameter object) {
        // Default behavior for delete hooks parameters: returning the first
        // context
        // changing parent Model operation or the containing Tool
        return globalSwitch.doSwitch(getFirstContextChangingContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.tool.util.ToolSwitch#caseMappingBasedToolDescription(org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription)
     * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseMappingBasedToolDescription(org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription)
     */
    @Override
    public Option<Collection<String>> caseMappingBasedToolDescription(MappingBasedToolDescription tool) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.getMappingBasedToolDescription())) {
        case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION:
        case org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> targets = Sets.newLinkedHashSet();
            for (RepresentationElementMapping correspondingMapping : new MappingBasedToolDescriptionQuery(tool).getMappings()) {
                Option<Collection<String>> targetsFromMapping = globalSwitch.doSwitch(correspondingMapping, false);
                if (targetsFromMapping.some()) {
                    targets.addAll(targetsFromMapping.get());
                }
            }
            result = Options.newSome(targets);
            break;
        default:
            break;
        }
        return result;
    }
}
