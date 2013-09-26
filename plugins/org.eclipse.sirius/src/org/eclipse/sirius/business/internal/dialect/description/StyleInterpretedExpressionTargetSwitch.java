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
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.style.util.StyleSwitch;

/**
 * A switch that will return the Target Types associated to a given element
 * (part of the {@link org.eclipse.sirius.viewpoint.description.style.StylePackage})
 * and feature corresponding to an Interpreted Expression. For example, for a
 * NodeMapping :
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
public class StyleInterpretedExpressionTargetSwitch extends StyleSwitch<Option<Collection<String>>> {

    /**
     * Constant used in switches on feature id to consider the case when the
     * feature must not be considered.
     */
    private static final int DO_NOT_CONSIDER_FEATURE = -1;

    /**
     * The ID of the feature containing the Interpreted expression.
     */
    protected int featureID;

    private IInterpretedExpressionTargetSwitch globalSwitch;

    private int lastFeatureID;

    /**
     * Default constructor.
     * 
     * @param feature
     *            representationDescription
     * @param defaultInterpretedExpressionTargetSwitch
     *            the global switch to use
     */
    public StyleInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch defaultInterpretedExpressionTargetSwitch) {
        this.featureID = feature != null ? feature.getFeatureID() : DO_NOT_CONSIDER_FEATURE;
        this.lastFeatureID = featureID;
        this.globalSwitch = defaultInterpretedExpressionTargetSwitch;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.util.StyleSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Option<Collection<String>> doSwitch(EObject theEObject) {
        Option<Collection<String>> doSwitch = super.doSwitch(theEObject);
        if (doSwitch != null) {
            return doSwitch;
        }
        Collection<String> targets = Sets.newLinkedHashSet();
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
        while ((!(container instanceof RepresentationDescription)) && (!(container instanceof RepresentationElementMapping))) {
            container = container.eContainer();
        }
        return container;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.util.StyleSwitch#caseStyleDescription(org.eclipse.sirius.viewpoint.description.style.StyleDescription)
     */
    @Override
    public Option<Collection<String>> caseStyleDescription(StyleDescription object) {
        // BY DEFAULT, the target of all Interpreted expressions related to a
        // style is the RepresentationElementMapping that contains it
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.util.StyleSwitch#caseBasicLabelStyleDescription(org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription)
     */
    @Override
    public Option<Collection<String>> caseBasicLabelStyleDescription(BasicLabelStyleDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Option<Collection<String>> caseGaugeSectionDescription(GaugeSectionDescription object) {
        return globalSwitch.doSwitch(getFirstRelevantContainer(object), false);
    }
}
