/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.matcher;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * A Matcher to get {@link NoteEditPart}'s bot.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class NoteEditPartMatcher extends BaseMatcher<EditPart> {
    @Override
    public boolean matches(Object item) {
        boolean result = false;
        if (item instanceof EditPart) {
            EditPart editPart = (EditPart) item;
            result = editPart instanceof NoteEditPart || editPart instanceof org.eclipse.sirius.diagram.ui.internal.edit.parts.NoteEditPart;
        }
        return result;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Matcher to get all NoteEditPart (gmf or viewpoint)");
    }
}
