package org.eclipse.sirius.ui.properties.internal;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterWithDiagnostic;

import com.google.common.collect.Lists;

public class SiriusInterpreter implements IInterpreter {

    private IInterpreterWithDiagnostic interpreter;

    public SiriusInterpreter(IInterpreterWithDiagnostic interpreterWithDiagnostic) {
        this.interpreter = interpreterWithDiagnostic;
    }

    @Override
    public IEvaluationResult evaluateExpression(Map<String, Object> variables, String expressionBody) {
        if (this.interpreter instanceof org.eclipse.sirius.common.tools.api.interpreter.IInterpreter) {
            org.eclipse.sirius.common.tools.api.interpreter.IInterpreter i = (org.eclipse.sirius.common.tools.api.interpreter.IInterpreter) this.interpreter;
            i.setProperty(org.eclipse.sirius.common.tools.api.interpreter.IInterpreter.FILES, Lists.newArrayList("org.eclipse.sirius.ui.properties"));
            i.addImport(org.eclipse.sirius.ui.properties.internal.SiriusToolServices.class.getName());
            Set<Entry<String, Object>> entries = variables.entrySet();
            for (Entry<String, Object> entry : entries) {
                i.setVariable(entry.getKey(), entry.getValue());
            }
        }

        IEvaluationResult result = new IEvaluationResult() {
            @Override
            public Object getValue() {
                return null;
            }

            @Override
            public Diagnostic getDiagnostic() {
                return Diagnostic.CANCEL_INSTANCE;
            }
        };

        Object object = variables.get("self");
        if (object instanceof EObject) {
            try {
                final org.eclipse.sirius.common.tools.api.interpreter.IInterpreterWithDiagnostic.IEvaluationResult evaluationResult = this.interpreter.evaluateExpression((EObject) object,
                        expressionBody);
                result = new IEvaluationResult() {

                    @Override
                    public Object getValue() {
                        return evaluationResult.getValue();
                    }

                    @Override
                    public Diagnostic getDiagnostic() {
                        return evaluationResult.getDiagnostic();
                    }
                };
            } catch (EvaluationException e) {
                e.printStackTrace();
            } finally {
                if (this.interpreter instanceof org.eclipse.sirius.common.tools.api.interpreter.IInterpreter) {
                    org.eclipse.sirius.common.tools.api.interpreter.IInterpreter i = (org.eclipse.sirius.common.tools.api.interpreter.IInterpreter) this.interpreter;
                    i.removeImport(org.eclipse.sirius.ui.properties.internal.SiriusToolServices.class.getName());
                    Set<Entry<String, Object>> entries = variables.entrySet();
                    for (Entry<String, Object> entry : entries) {
                        i.unSetVariable(entry.getKey());
                    }
                }
            }
        }

        return result;
    }
}
