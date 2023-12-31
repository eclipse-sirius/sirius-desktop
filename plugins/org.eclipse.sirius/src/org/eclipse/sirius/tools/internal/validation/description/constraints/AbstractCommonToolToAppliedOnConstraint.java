/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;

/**
 * A abstract {@link AbstractModelConstraint} common to constraint with the tool to recovery the path.
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 */
public abstract class AbstractCommonToolToAppliedOnConstraint extends AbstractModelConstraint {

    /**
     * Get a readable text representation of a vsm element.
     * 
     * @param element
     *            the vsm element
     * @return a readable text representation of a vsm element
     */
    protected String getPath(EObject element) {
        String text = getLabel(element);
        EObject container = element.eContainer();
        while (container != null) {
            text = getLabel(container) + " > " + text; //$NON-NLS-1$
            container = container.eContainer();
        }
        return "\"" + text + "\""; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Get the Label of a given object.
     * 
     * @param eObject
     *            the object to study
     * @return the label of the given object
     */
    protected String getLabel(final EObject eObject) {
        String label = Messages.AbstractCommonToolToAppliedOnConstraint_label;
        if (eObject instanceof IdentifiedElement) {
            label = new IdentifiedElementQuery((IdentifiedElement) eObject).getLabel();
        } else if (eObject instanceof RepresentationExtensionDescription) {
            label = ((RepresentationExtensionDescription) eObject).getName();
        } else {
            label = eObject.eClass().getName();
        }
        return label;
    }

}
