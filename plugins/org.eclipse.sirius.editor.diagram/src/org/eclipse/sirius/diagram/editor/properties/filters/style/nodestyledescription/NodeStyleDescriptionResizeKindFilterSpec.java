/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.filters.style.nodestyledescription;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;

/**
 * A filter for the resizeKind property section. The generated class is overridden to avoid to have these properties
 * displayed for Container. Indeed, the {@link WorkspaceImageDescription} is a {@link NodeStyleDescription} and a
 * {@link ContainerStyleDescription}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class NodeStyleDescriptionResizeKindFilterSpec extends NodeStyleDescriptionResizeKindFilter {
    @Override
    protected boolean isRightInputType(Object input) {
        return super.isRightInputType(input) && input instanceof EObject && !(((EObject) input).eContainer() instanceof ContainerMapping);
    }
}
