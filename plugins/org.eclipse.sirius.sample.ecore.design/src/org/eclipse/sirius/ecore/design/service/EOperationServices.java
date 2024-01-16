/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ecore.design.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;

/**
 * Services dealing with EOperations usable from a VSM.
 */
public class EOperationServices {
    private static final String TYPE_SEPARATOR = ":";

    private static final String PARAMETERS_SEPARATOR = ",";

    /**
     * Computes the label of an EOperation.
     */
    public String render(EOperation op) {
        StringBuilder sb = new StringBuilder();
        renderName(op, sb);
        renderParameters(op, sb, true);
        renderType(op, sb);
        return sb.toString();
    }

    /**
     * Computes the tooltip of an EOperation (same as label but excluding the
     * parameters Types) .
     * 
     * @param op
     *            the operation to be rendered
     * @return the tooltip of the given EOperation (same as label but excluding
     *         the parameters Types)
     */
    public String renderTooltip(EOperation op) {
        StringBuilder sb = new StringBuilder();
        renderName(op, sb);
        renderParameters(op, sb, false);
        renderType(op, sb);
        return sb.toString();
    }

    /**
     * Performs a "direct edit" operation on an EOperation.
     */
    public EOperation performEdit(EOperation op, String editString) {
        editName(op, editString);
        editParameters(op, editString);
        editType(op, editString);
        return op;
    }

    private void renderName(EOperation op, StringBuilder sb) {
        if (op.getName() != null) {
            sb.append(op.getName());
        }
    }

    /**
     * Renders the parameters of the given EOperation.
     * 
     * @param op
     *            the operation to render
     * @param sb
     *            the string builder used to render the eOperation
     * @param includeParameterType
     *            indicates whether parameter's Types should be rendered or not
     */
    private void renderParameters(EOperation op, StringBuilder sb, boolean includeParameterType) {
        sb.append("(");
        boolean first = true;
        for (EParameter param : op.getEParameters()) {
            if (!first) {
                sb.append(PARAMETERS_SEPARATOR).append(" ");
            } else {
                first = false;
            }
            sb.append(renderParameter(param, includeParameterType));
        }
        sb.append(")");
    }

    /**
     * Renders the given Parameter.
     * 
     * @param parameter
     *            the parameter to render
     * @param includeParameterType
     *            indicates whether parameter's Type should be rendered or not
     */
    private String renderParameter(EParameter parameter, boolean includeParameterType) {
        String name = parameter.getName();
        EClassifier type = parameter.getEType();
        if (name != null && type != null && includeParameterType) {
            return parameter.getName() + " " + type.getName();
        } else if (name != null) {
            return parameter.getName();
        } else {
            return "";
        }
    }

    private void renderType(EOperation op, StringBuilder sb) {
        if (op.getEType() != null) {
            sb.append(" ").append(TYPE_SEPARATOR).append(" ");
            sb.append(op.getEType().getName());
        }
    }

    private void editName(EOperation op, String editString) {
        int nameEnd = editString.length();
        int typeStart = editString.indexOf(TYPE_SEPARATOR);
        if (typeStart != -1) {
            nameEnd = Math.min(nameEnd, typeStart);
        }
        int paramStart = editString.indexOf("(");
        if (paramStart != -1) {
            nameEnd = Math.min(nameEnd, paramStart);
        }

        String namePart = editString.substring(0, nameEnd);
        if (namePart.trim().length() > 0) {
            op.setName(namePart.trim());
        } else if (typeStart == -1 && paramStart == -1) {
            // Reset the name only if no other parts have been specified.
            op.setName(null);
        }
    }

    private void editParameters(EOperation op, String editString) {
        int paramStart = editString.indexOf("(");
        int paramEnd = editString.lastIndexOf(")");
        if (paramStart != -1 && paramEnd != -1 && paramStart < paramEnd) {
            List<String[]> spec = parseParameters(editString.substring(paramStart + 1, paramEnd));
            int nbParam = spec.size();
            // Remove parameters from the end if we have too many.
            for (int i = op.getEParameters().size() - 1; i >= nbParam; i--) {
                op.getEParameters().remove(i);
            }
            // Update existing parameters with new spec and append additional
            // ones if necessary
            for (int i = 0; i < nbParam; i++) {
                EParameter param = null;
                if (i < op.getEParameters().size()) {
                    param = op.getEParameters().get(i);
                } else {
                    param = EcoreFactory.eINSTANCE.createEParameter();
                    op.getEParameters().add(param);
                }
                updateParameter(op, param, spec.get(i));
            }
        }
    }

    private void updateParameter(EOperation op, EParameter param, String[] paramSpec) {
        if (paramSpec[0] != null) {
            param.setName(paramSpec[0]);
        }
        if (paramSpec[1] != null) {
            EClassifier type = new EcoreService().findTypeByName(op, paramSpec[1]);
            if (type != null) {
                param.setEType(type);
            }
        }
    }

    private List<String[]> parseParameters(String spec) {
        List<String[]> result = new ArrayList<String[]>();
        if (spec.contains(PARAMETERS_SEPARATOR)) {
            String[] params = spec.split(PARAMETERS_SEPARATOR);
            for (String paramSpec : params) {
                result.add(parseParameter(paramSpec));
            }
        } else if (spec.trim().length() > 0) {
            result.add(parseParameter(spec));
        }
        return result;
    }

    /**
     * Parses a single parameter specification. Supports the following syntaxes:
     * 
     * <pre>
     * "name"
     * "name Type"
     * "name : Type"
     * " : Type"
     * </pre>
     * 
     * Returns an array of two strings, representing the parameter name and the
     * type name. Both can be <code>null</code> if the corresponding part is not
     * specified.
     * 
     * Note: this method is public only so that it can be visible from tests.
     */
    public String[] parseParameter(String paramSpec) {
        String[] type = null;
        // Try ":" as type separator
        int typeStart = paramSpec.indexOf(TYPE_SEPARATOR);
        if (typeStart != -1) {
            type = splitParameterSpec(paramSpec, typeStart);
        } else {
            // If there is no ":", assume whitespace separates the name from the
            // type
            paramSpec = paramSpec.trim();
            typeStart = paramSpec.lastIndexOf(" ");
            if (typeStart != -1) {
                type = splitParameterSpec(paramSpec, typeStart);
            } else {
                typeStart = paramSpec.lastIndexOf("\t");
                if (typeStart != -1) {
                    type = splitParameterSpec(paramSpec, typeStart);
                }
            }
        }
        // If there was neither ":" nor space, the spec can specify only the
        // name, or maybe nothing if it is blank.
        if (type == null) {
            String name = paramSpec.trim();
            if (name.length() == 0) {
                name = null;
            }
            type = new String[] { name, null };
        }
        return type;
    }
    
    public List<ENamedElement> getAllAssociatedElements(EOperation op) {
    	// [eParameters->including(self)->asSequence()->sortedBy(name)/]
    	List<ENamedElement> result = new ArrayList<>(1 + op.getEParameters().size());
    	result.add(op);
    	result.addAll(op.getEParameters());
    	Collections.sort(result, Comparator.comparing(ENamedElement::getName));
    	return result;
    }

    private String[] splitParameterSpec(String paramSpec, int typeStart) {
        String[] type;
        String name;
        String typeName;
        name = paramSpec.substring(0, typeStart).trim();
        if (typeStart != paramSpec.length() - 1) {
            typeName = paramSpec.substring(typeStart + 1).trim();
        } else {
            typeName = null;
        }
        if (name.length() == 0) {
            name = null;
        }
        if (typeName != null && typeName.length() == 0) {
            typeName = null;
        }
        type = new String[] { name, typeName };
        return type;
    }

    private void editType(EOperation op, String editString) {
        EClassifier type = getSpecifiedType(op, editString);
        if (type != null) {
            op.setEType(type);
        } else {
            // Only reset the type to null if the ":" is explicitly present with
            // no type specified
            int typeStart = editString.lastIndexOf(TYPE_SEPARATOR);
            if (typeStart != -1 && !editString.substring(typeStart).contains(")")) {
                op.setEType(null);
            }
        }
    }

    private EClassifier getSpecifiedType(ETypedElement receiver, String editString) {
        int typeStart = editString.lastIndexOf(TYPE_SEPARATOR);
        if (typeStart != -1 && editString.length() > typeStart + 1) {
            String typeName = editString.substring(typeStart + 1).trim();
            if (!typeName.contains(")")) {
                return new EcoreService().findTypeByName(receiver, typeName);
            }
        }
        return null;
    }
}
