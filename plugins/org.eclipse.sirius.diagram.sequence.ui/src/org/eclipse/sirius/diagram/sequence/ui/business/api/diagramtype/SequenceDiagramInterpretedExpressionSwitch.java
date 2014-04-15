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
package org.eclipse.sirius.diagram.sequence.ui.business.api.diagramtype;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;
import org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.sequence.description.util.DescriptionSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Sets;

/**
 * A switch that will return the Target Types associated to a given element
 * (here all elements are sequence diagram-specific) and feature corresponding
 * to an Interpreted Expression. For example, for a NodeMapping :
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
public class SequenceDiagramInterpretedExpressionSwitch extends DescriptionSwitch<Option<Collection<String>>> {

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
     * Indicates if the feature must be considered.
     */
    protected boolean considereFeature;

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
    public SequenceDiagramInterpretedExpressionSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch theGlobalSwitch) {
        super();
        this.feature = feature;
        this.globalSwitch = theGlobalSwitch;
    }

    /**
     * {@inheritDoc}
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
        this.considereFeature = considerFeature;
    }

    private int getFeatureId(EClass eClass) {
        int featureID = DO_NOT_CONSIDER_FEATURE;
        if (considereFeature && feature != null) {
            featureID = eClass.getFeatureID(feature);
        }
        return featureID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseSequenceDiagramDescription(SequenceDiagramDescription object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(DescriptionPackage.eINSTANCE.getSequenceDiagramDescription())) {
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING:
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING:
            Collection<String> target = Sets.newLinkedHashSet();
            target.add(object.getDomainClass());
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
    public Option<Collection<String>> caseDelimitedEventMapping(DelimitedEventMapping object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(DescriptionPackage.eINSTANCE.getDelimitedEventMapping())) {
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION:
        case DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION:
            Collection<String> target = Sets.newLinkedHashSet();
            // LEt the global swith return the same types than precondition.
            Option<Collection<String>> types = globalSwitch.doSwitch(object, false);
            if (types.some()) {
                target.addAll(types.get());
            }
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
    public Option<Collection<String>> caseFrameMapping(FrameMapping object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(DescriptionPackage.eINSTANCE.getFrameMapping())) {
        case DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION:
        case DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION:
        case DO_NOT_CONSIDER_FEATURE:
            Collection<String> target = Sets.newLinkedHashSet();
            target.add(object.getDomainClass());
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
    public Option<Collection<String>> caseMessageMapping(MessageMapping object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(DescriptionPackage.eINSTANCE.getMessageMapping())) {
        case DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
        case DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            Collection<String> target = Sets.newLinkedHashSet();
            // LEt the global swith return the same types than precondition.
            Option<Collection<String>> types = globalSwitch.doSwitch(object, false);
            if (types.some()) {
                target.addAll(types.get());
            }
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
    public Option<Collection<String>> caseReturnMessageMapping(ReturnMessageMapping object) {
        Option<Collection<String>> result = null;
        switch (getFeatureId(DescriptionPackage.eINSTANCE.getReturnMessageMapping())) {
        case DescriptionPackage.RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            Collection<String> target = Sets.newLinkedHashSet();
            // LEt the global switch return the same types than precondition.
            Option<Collection<String>> types = globalSwitch.doSwitch(object, false);
            if (types.some()) {
                target.addAll(types.get());
            }
            result = Options.newSome(target);
            break;
        default:
            break;
        }
        return result;
    }
}
