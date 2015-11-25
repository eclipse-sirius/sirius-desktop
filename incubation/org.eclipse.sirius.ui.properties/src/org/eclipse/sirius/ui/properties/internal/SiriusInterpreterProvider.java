/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IInterpreterProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterWithDiagnostic;

public class SiriusInterpreterProvider implements IInterpreterProvider {

    private Session session;

    public SiriusInterpreterProvider(Session session) {
        this.session = session;
    }

    @Override
    public boolean canHandle(String expression) {
        org.eclipse.sirius.common.tools.api.interpreter.IInterpreter interpreter = this.session.getInterpreter();
        return interpreter != null && interpreter.provides(expression);
    }

    @Override
    public IInterpreter createInterpreter() {
        org.eclipse.sirius.common.tools.api.interpreter.IInterpreter interpreter = this.session.getInterpreter();
        if (interpreter instanceof IInterpreterWithDiagnostic) {
            IInterpreterWithDiagnostic interpreterWithDiagnostic = (IInterpreterWithDiagnostic) interpreter;
            return new SiriusInterpreter(interpreterWithDiagnostic);
        }
        return null;
    }

}
