/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.matcher;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * A Matcher to get {@link EditPart} with a specified
 * {@link DRepresentationElement} type.
 * 
 * @param <T>
 *            the type of the {@link DRepresentationElement}
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WithDRepresentationElementType<T extends DRepresentationElement> extends BaseMatcher<EditPart> {

    private final Class<T> type;

    /**
     * Default constructor.
     * 
     * @param type
     *            the type of the {@link DRepresentationElement}
     */
    public WithDRepresentationElementType(Class<T> type) {
        this.type = type;
    }

    @Override
    public boolean matches(Object item) {
        boolean result = false;
        if (item instanceof EditPart && !(item instanceof AbstractDiagramNameEditPart) && !(item instanceof CompartmentEditPart)) {
            EditPart editPart = (EditPart) item;
            Object model = editPart.getModel();
            if (model instanceof View) {
                View view = (View) model;
                EObject element = view.getElement();
                result = type.isInstance(element);
            }
        }
        return result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Matcher to get all EditParts having a DRepresentationElement of the following type : " + type);
    }

}
