/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Validation constraint to check that
 * {@link RepresentationExtensionDescription} using regular expression for
 * "viewpoint URI" or "representation name" contains only {@link Customization}.
 * 
 * @author fbarbin
 * 
 */
public class RepresentationExtensionDescriptionRegexConstraint extends AbstractModelConstraint {
    /**
     * Error message for this constraint. The first argument is the name of the
     * representation extension description.
     */
    private static final String ERROR_MESSAGE = "The representation extension description \"{0}\" uses a regular expression for viewpoint URI or for representation name, but it does not contain only style customizations.";

    /**
     * Pattern used to detect if a String is a Regex. For that, we watching the
     * following characters : * [ ] ( ) ?
     */
    private static final Pattern REGEX_PATTERN = Pattern.compile("[^\\?\\*\\[\\]\\(\\)]*"); //$NON-NLS-1$

    @Override
    public IStatus validate(IValidationContext ctx) {

        IStatus status = null;
        EObject target = ctx.getTarget();
        if (target instanceof RepresentationExtensionDescription) {
            RepresentationExtensionDescription representationExtensionDescription = (RepresentationExtensionDescription) target;

            Matcher representationNameMatcher = REGEX_PATTERN.matcher(representationExtensionDescription.getRepresentationName());

            Matcher viewpointURIMatcher = REGEX_PATTERN.matcher(representationExtensionDescription.getViewpointURI());
            if (representationNameMatcher.matches() && viewpointURIMatcher.matches()) {
                status = ctx.createSuccessStatus();
            } else {
                // Check that the diagram extension contains only additional
                // layers
                if (!Iterables.all(representationExtensionDescription.eContents(), Predicates.instanceOf(AdditionalLayer.class))) {
                    status = ctx.createFailureStatus(MessageFormat.format(ERROR_MESSAGE, representationExtensionDescription.getName()));
                } else if (!(representationExtensionDescription instanceof DiagramExtensionDescription)) {
                    status = ctx.createFailureStatus(MessageFormat.format(ERROR_MESSAGE, representationExtensionDescription.getName()));
                } else {
                    // Check that all additional layers of diagram extension
                    // contains only style
                    // customizations.
                    for (AdditionalLayer additionalLayer : ((DiagramExtensionDescription) representationExtensionDescription).getLayers()) {
                        if (!Iterables.all(additionalLayer.eContents(), Predicates.instanceOf(Customization.class))) {
                            status = ctx.createFailureStatus(MessageFormat.format(ERROR_MESSAGE, representationExtensionDescription.getName()));
                        }
                    }
                }
            }
        }
        if (status == null) {
            status = ctx.createSuccessStatus();
        }
        return status;
    }
}
