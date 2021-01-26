/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.table.celleditorfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.ui.tools.api.editor.ITableCellEditorFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;

/**
 * A {@link ITableCellEditorFactory} for a boolean field of an UML Class. The returned {@link CellEditor} depends on the
 * name of the edited classes:
 * <UL>
 * <LI>if the name starts with "Substitute", a combo box with "specific" string values is returned instead of classical
 * "true/false",</LI>
 * <LI>if the name starts with "Null", no {@link CellEditor} is returned (to verify fallback),</LI>
 * <LI>in other case, a combo box with True and False values is returned.</LI>
 * </UL>
 * 
 * @author lredor
 */
public class MyCellEditorFactory implements ITableCellEditorFactory {

    @Override
    public CellEditor getCellEditor(Tree tree, Map<String, Object> parameters) {
        CellEditor result = null;
        Object element = parameters.get(IInterpreterSiriusVariables.ELEMENT);
        if (element instanceof DCell) {
            DCell dCell = (DCell) element;
            if (dCell.getTarget() instanceof org.eclipse.uml2.uml.Class) {
                String umlClassName = "notDefined";
                umlClassName = ((org.eclipse.uml2.uml.Class) dCell.getTarget()).getName();
                if (umlClassName.startsWith("Substitute")) {
                    // List of string (then "translated" to boolean in the doSetValue)
                    List<String> values = new ArrayList<String>();
                    String trueValue = "My true value";
                    String falseValue = "My false value";
                    values.add(trueValue);
                    values.add(falseValue);
                    result = new ExtendedComboBoxCellEditor(tree, values, getLabelProvider(), true) {
                        @Override
                        public Object doGetValue() {
                            Object selectedValue = super.doGetValue();
                            Boolean result = null;
                            if (trueValue.equals(selectedValue))
                                result = Boolean.valueOf(true);
                            else if (falseValue.equals(selectedValue)) {
                                result = Boolean.valueOf(false);
                            }
                            return result;
                        }

                        @Override
                        public void doSetValue(Object value) {
                            if (value instanceof String) {
                                Boolean booleanValue = Boolean.valueOf((String) value);
                                if (booleanValue) {
                                    super.doSetValue(trueValue);
                                } else {
                                    super.doSetValue(falseValue);
                                }
                            } else {
                                super.doSetValue(value);
                            }
                        }
                    };
                } else if (umlClassName.startsWith("null")) {
                    result = null;
                } else {
                    // List of boolean
                    List<Boolean> values = new ArrayList<Boolean>();
                    values.add(Boolean.valueOf(true));
                    values.add(Boolean.valueOf(false));
                    result = new ExtendedComboBoxCellEditor(tree, values, getLabelProvider(), true);
                }
            }
        }
        return result;
    }

    /**
     * @return A label provider
     */
    private ILabelProvider getLabelProvider() {
        return new LabelProvider() {
            @Override
            public String getText(final Object object) {
                if (object instanceof Boolean) {
                    return ((Boolean) object).toString();
                } else {
                    return object.toString();
                }
            }

            @Override
            public Image getImage(final Object object) {
                return null;
            }
        };
    }
}
