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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.internal.dialect.description.DescriptionInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.internal.dialect.description.StyleInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.internal.dialect.description.ToolInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.internal.dialect.description.ValidationInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

/**
 * A switch that will return the Target Types associated to a given element and
 * feature corresponding to an Interpreted Expression. This default switch will
 * treat all description elements expected representation-specific elements.
 * 
 * @since 0.9.0
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DefaultInterpretedExpressionTargetSwitch implements IInterpretedExpressionTargetSwitch {

    private DescriptionInterpretedExpressionTargetSwitch descriptionSwitch;

    private StyleInterpretedExpressionTargetSwitch styleSwitch;

    private ToolInterpretedExpressionTargetSwitch toolSwitch;

    private ValidationInterpretedExpressionTargetSwitch validationSwitch;

    private final Function<EObject, EObject> firstRelevantContainerFinder;

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression
     * @param globalSwitch
     *            the global switch to use
     */
    public DefaultInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch globalSwitch) {
        this(feature, globalSwitch, null);
    }

    /**
     * Default constructor.
     * 
     * @param feature
     *            the feature containing the Interpreted expression to determine
     *            the target from
     * @param globalSwitch
     *            the global switch to use
     * @param firstRelevantContainerFunction
     *            d
     */
    public DefaultInterpretedExpressionTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch globalSwitch, Function<EObject, EObject> firstRelevantContainerFunction) {
        IInterpretedExpressionTargetSwitch theGlobalSwitch = globalSwitch;
        if (theGlobalSwitch == null) {
            theGlobalSwitch = this;
        }

        // Init the relevant container finder
        if (firstRelevantContainerFunction != null) {
            this.firstRelevantContainerFinder = firstRelevantContainerFunction;
        } else {
            this.firstRelevantContainerFinder = new FirstRelevantContainerDefaultFunction();
        }

        this.descriptionSwitch = new DescriptionInterpretedExpressionTargetSwitch(feature, theGlobalSwitch);
        this.styleSwitch = new StyleInterpretedExpressionTargetSwitch(feature, theGlobalSwitch);
        this.toolSwitch = new ToolInterpretedExpressionTargetSwitch(feature, theGlobalSwitch);
        this.validationSwitch = new ValidationInterpretedExpressionTargetSwitch(feature, theGlobalSwitch);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch#doSwitch(org.eclipse.emf.ecore.EObject,
     *      boolean)
     */
    public Option<Collection<String>> doSwitch(EObject theEObject, boolean considerFeature) {
        Collection<String> targetTypes = Sets.newLinkedHashSet();
        Option<Collection<String>> expressionTarget = Options.newSome(targetTypes);
        if (theEObject != null) {
            descriptionSwitch.setConsiderFeature(considerFeature);
            expressionTarget = descriptionSwitch.doSwitch(theEObject);

            if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                styleSwitch.setConsiderFeature(considerFeature);
                expressionTarget = styleSwitch.doSwitch(theEObject);
            }

            if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                validationSwitch.setConsiderFeature(considerFeature);
                expressionTarget = validationSwitch.doSwitch(theEObject);
            }

            // Tool in last position -> tool will return a default EObject value
            // as domain class.
            if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                toolSwitch.setConsiderFeature(considerFeature);
                expressionTarget = toolSwitch.doSwitch(theEObject);
            }
        }
        return expressionTarget;
    }
    
    @Override
    public EObject getFirstRelevantContainer(EObject obj) {
        return firstRelevantContainerFinder.apply(obj);
    }

    /**
     * A function to compute the first relevant container for the given EObject,
     * i.e. the first container from which a domain class can be determined.
     * <p>
     * For example: for a given RepresentationElementMapping, it will return the
     * first parent RepresentationElementMapping or RepresentationDescription.
     * mapping.
     * </p>
     */
    private static class FirstRelevantContainerDefaultFunction implements Function<EObject, EObject> {
        @Override
        public EObject apply(EObject input) {
            if (input != null) {
                EObject container = input.eContainer();
                while (container != null && !isRelevant(container)) {
                    container = container.eContainer();
                }
                return container;
            } else {
                return null;
            }
        }

        protected boolean isRelevant(EObject container) {
            return container instanceof RepresentationDescription || container instanceof RepresentationElementMapping || container instanceof RepresentationExtensionDescription;
        }
    }
}
