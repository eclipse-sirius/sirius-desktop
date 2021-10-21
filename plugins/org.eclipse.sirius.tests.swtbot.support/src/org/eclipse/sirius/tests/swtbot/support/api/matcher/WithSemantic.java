/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * This program and the accompanying materials
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

import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * A Matcher to get {@link EditPart} with semantic in parameter.
 * 
 * @author edugueperoux
 */
public class WithSemantic extends BaseMatcher<EditPart> {

    private final EObject semantic;

    /**
     * Default constructor.
     * 
     * @param semantic
     *            for which get a associated Bot
     */
    public WithSemantic(EObject semantic) {
        this.semantic = semantic;
    }

    /**
     * Get a default {@link WithSemantic}.
     * 
     * @param semantic
     *            for which get a associated Bot
     * 
     * @return a default {@link WithSemantic}
     */
    public static Matcher<EditPart> withSemantic(EObject semantic) {
        Objects.requireNonNull(semantic, "Can't execute this matcher on a null semantic elt");
        return new WithSemantic(semantic);
    }

    @Override
    public boolean matches(Object item) {
        boolean result = false;
        if (item instanceof EditPart) {
            EditPart editPart = (EditPart) item;
            Object model = editPart.getModel();
            if (model instanceof View) {
                View view = (View) model;
                EObject element = view.getElement();
                if (element instanceof DSemanticDecorator) {
                    DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) element;
                    EObject semanticTarget = dSemanticDecorator.getTarget();
                    result = semanticTarget != null && semanticTarget.equals(semantic);
                }
            }
        }
        return result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Matcher to get all EditParts referencing indirectly : " + semantic + ", which are child EditParts of contextual EditPart");
    }
}
