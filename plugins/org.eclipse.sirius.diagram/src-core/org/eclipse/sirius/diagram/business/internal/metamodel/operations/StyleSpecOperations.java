/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.operations;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of {@link Style}.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public final class StyleSpecOperations {

    /** Avoid instanciations */
    private StyleSpecOperations() {
        // empty.
    }

    /**
     * Refresh the specified style.
     * 
     * @param style
     *            the style to refresh.
     */
    public static void refresh(final Style style) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(style);
        new StyleHelper(interpreter).refreshStyle(style);
    }

}
