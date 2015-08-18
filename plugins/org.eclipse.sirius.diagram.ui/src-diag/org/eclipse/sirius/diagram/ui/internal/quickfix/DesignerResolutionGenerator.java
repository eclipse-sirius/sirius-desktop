/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

/**
 * Marker resolution generator looking in .odesign files whether a user defined
 * quick fix is provided or not.
 * 
 * @author cbrun
 * 
 */
public class DesignerResolutionGenerator implements IMarkerResolutionGenerator {
    /**
     * {@inheritDoc}
     */
    public IMarkerResolution[] getResolutions(IMarker marker) {
        final String validationRuleURI = marker.getAttribute("rule", null); //$NON-NLS-1$
        if (validationRuleURI != null) {
            final ValidationRule rule = fetchRuleFromURI(validationRuleURI);
            if (rule != null) {
                IMarkerResolution[] resolutions = new IMarkerResolution[rule.getFixes().size()];
                for (int i = 0; i < rule.getFixes().size(); i++) {
                    resolutions[i] = new ValidationFixResolution(rule.getFixes().get(i));
                }
                return resolutions;
            }
        }
        return new IMarkerResolution[] {};
    }

    private ValidationRule fetchRuleFromURI(String validationRuleURI) {
        ResourceSet set = new ResourceSetImpl();
        EObject rule = set.getEObject(URI.createURI(validationRuleURI), true);
        if (rule != null && rule instanceof ValidationRule) {
            return (ValidationRule) rule;
        }
        return null;
    }

}
