package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.eef.interpreter.api.IInterpreter;
import org.eclipse.eef.interpreter.api.IInterpreterProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterWithDiagnostic;

public class SiriusInterpreterProvider implements IInterpreterProvider {

    private Session session;

    public SiriusInterpreterProvider(Session session) {
        this.session = session;
    }

    @Override
    public boolean canHandle(String expression) {
        org.eclipse.sirius.common.tools.api.interpreter.IInterpreter interpreter = this.session.getInterpreter();
        return interpreter.provides(expression);
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
