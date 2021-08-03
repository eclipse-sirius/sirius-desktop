/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.model.DDiagramElementSpecOperations;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.impl.DNodeImpl;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of DNodeImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public class DNodeSpec extends DNodeImpl {

    @Override
    public DiagramElementMapping getMapping() {
        return getActualMapping();
    }

    @Override
    public Style getStyle() {
        return getOwnedStyle();
    }

    @Override
    public DDiagram getParentDiagram() {
        return DDiagramElementSpecOperations.getParentDiagram(this);
    }

    /**
     * Return the first parent that is a {@link DSemanticDecorator}.
     * 
     * @return the first parent that is a {@link DSemanticDecorator}.
     */
    public DSemanticDecorator getFirstParentWithSemantic() {
        DSemanticDecorator result = null;
        EObject cur = this.eContainer();
        while (cur != null && result == null) {
            if (cur instanceof DSemanticDecorator) {
                result = (DSemanticDecorator) cur;
            }
            cur = cur.eContainer();
        }
        return result;
    }

    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return this.getActualMapping();
    }

    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer("Node"); //$NON-NLS-1$
        if (getName() != null && getName().length() > 0) {
            result.append(" "); //$NON-NLS-1$
            result.append(getName());
        }
        if (getTarget() != null) {
            result.append(" --> "); //$NON-NLS-1$
            result.append(getTarget().toString());
        }
        return result.toString();
    }

}
