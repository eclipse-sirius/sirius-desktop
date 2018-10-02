/*******************************************************************************
 * Copyright (c) 2014, 2016 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.tools.internal.validation.description.constraints.AbstractMandatoryDomainClassConstraint;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Validates mandatory DomainClass attributes.
 * 
 * @author ymortier
 */
public class DiagramMandatoryDomainClassConstraint extends AbstractMandatoryDomainClassConstraint {

    @Override
    protected EObject getParentDescription(final EObject instance) {
        String nsURI = instance.eClass().getEPackage().getNsURI();
        if (nsURI.startsWith(ViewpointPackage.eINSTANCE.getNsURI()) || nsURI.startsWith(DiagramPackage.eINSTANCE.getNsURI())) {
            EObject container = instance.eContainer();
            while (container != null) {
                if (container instanceof DiagramDescription || container instanceof DiagramExtensionDescription) {
                    return container;
                }
                container = container.eContainer();
            }
        }
        return null;
    }

    @Override
    protected boolean canHaveNullDomainClass(EObject instance) {
        return instance instanceof EdgeMapping && !((EdgeMapping) instance).isUseDomainElement();
    }
}
