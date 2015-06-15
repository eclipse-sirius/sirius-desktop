/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.business.internal.diagramtype;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.diagram.sequence.description.EventMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.description.tool.util.ToolSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;

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
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public class SequenceToolInterpretedExpressionSwitch extends ToolSwitch<Option<Collection<String>>> {

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
    public SequenceToolInterpretedExpressionSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch defaultInterpretedExpressionTargetSwitch) {
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
        while ((!(container instanceof RepresentationDescription)) && (!(container instanceof ChangeContext))
                && (!(container instanceof AbstractToolDescription) && (!(container instanceof RepresentationElementMapping)))) {
            container = container.eContainer();
        }
        return container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseMessageCreationTool(MessageCreationTool object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getMessageCreationTool())) {
        case ToolPackage.MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION:
        case ToolPackage.MESSAGE_CREATION_TOOL__PRECONDITION:
        case ToolPackage.MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT:
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
    public Option<Collection<String>> caseReorderTool(ReorderTool object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getReorderTool())) {
        case ToolPackage.REORDER_TOOL__PRECONDITION:
        case ToolPackage.REORDER_TOOL__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> targets = Sets.newLinkedHashSet();
            for (EventMapping correspondingMapping : object.getMappings()) {
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
    public Option<Collection<String>> caseInstanceRoleReorderTool(InstanceRoleReorderTool object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(ToolPackage.eINSTANCE.getInstanceRoleReorderTool())) {
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PRECONDITION:
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL__ELEMENTS_TO_SELECT:
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> targets = Sets.newLinkedHashSet();
            for (InstanceRoleMapping correspondingMapping : object.getMappings()) {
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
