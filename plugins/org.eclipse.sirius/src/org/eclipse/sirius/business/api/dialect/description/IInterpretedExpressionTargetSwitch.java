/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect.description;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;

/**
 * A switch that will return the Target Types associated to a given element and
 * feature corresponding to an Interpreted Expression. For example, for a
 * NodeMapping :
 * <p>
 * <li>if the feature is semantic candidate expression, we return the domain
 * class of the first valid container (representation element mapping or
 * representation description).</li>
 * <li>if the feature is any other interpreted expression, we return the domain
 * class associated to this mapping</li>
 * </p>
 * 
 * Can return {@link org.eclipse.sirius.ext.base.Options#newNone()} if the given
 * expression does not require any target type (for example, a Popup menu
 * contribution uses variables and clicked element in its expressions, and the
 * receiver is an EObject, there is no reference in the meta model to compute
 * the possible type.).
 * 
 * @since 0.9.0
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public interface IInterpretedExpressionTargetSwitch {

    /**
     * Returns all the possible target Types for the given target and feature.
     * Can return {@link org.eclipse.sirius.ext.base.Options#newNone()} if the
     * given expression does not require any target type (for example, a Popup
     * menu contribution uses variables and clicked element in its expressions,
     * and the receiver is an EObject, there is no reference in the meta model
     * to compute the possible type.).
     * 
     * @param target
     *            the target to analyse
     * @param considerFeature
     *            true if the feature must be considered (to differentiate
     *            semanticCandidateExpression and semanticElements for a
     *            NodeMapping for example)
     * @return all possible target types for the given target and feature,
     *         {@link org.eclipse.sirius.ext.base.Options#newNone()} if no
     *         target type is needed (see description)
     */
    Option<Collection<String>> doSwitch(EObject target, boolean considerFeature);

    /**
     * Compute the first relevant container for the given EObject, i.e. the
     * first container from which a domain class can be determined.
     * <p>
     * For example: for a given RepresentationElementMapping, it will return the
     * first parent RepresentationElementMapping or RepresentationDescription.
     * mapping.
     * </p>
     * 
     * @param obj
     *            the object for which to compute the container.
     * @return the first relevant container of obj.
     */
    EObject getFirstRelevantContainer(EObject obj);
}
