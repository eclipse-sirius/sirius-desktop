/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementSpecOperations;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.impl.DNodeListElementImpl;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of DNodeListElementImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier.
 */
public class DNodeListElementSpec extends DNodeListElementImpl {
    @Override
    public DiagramElementMapping getMapping() {
        return getActualMapping();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl#getParentDiagram()
     */
    @Override
    public DDiagram getParentDiagram() {
        return DDiagramElementSpecOperations.getParentDiagram(this);
    }

    /**
     * Get the first parent with semantic.
     * 
     * @return the first parent with semantic
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl#refresh()
     */
    @Override
    public void refresh() {
        if (this.getActualMapping() != null) {
            this.getActualMapping().updateListElement(this);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl#getStyle()
     */
    @Override
    public Style getStyle() {
        return getOwnedStyle();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "List element " + getName();
    }
}
