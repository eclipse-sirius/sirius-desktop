/*******************************************************************************
 * Copyright (c) 2021 Obeo.
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.ui.tools.api.editor.ITableCellEditorFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;

/**
 * A {@link ITableCellEditorFactory} to select only Abstract class for eSuperTypes reference.
 * 
 * @author lredor
 */
public class AbstractClassCellEditorFactory implements ITableCellEditorFactory {
    /** The title of the opened selection dialog. */
    public final static String DIALOG_TITLE = "Select classes to use as eSuperTypes";

    /** A prefix added before classe name by the label providerThe title of the opened selection dialog. */
    public final static String CLASS_NAME_PREFIX = "Select classes to use as eSuperTypes";

    public static String getDisplayedName(String name) {
        return "SpecificLabel: " + name;
    }

    @Override
    public CellEditor getCellEditor(Tree tree, Map<String, Object> parameters) {
        CellEditor result = null;
        Object element = parameters.get(IInterpreterSiriusVariables.ELEMENT);
        if (element instanceof DCell) {
            DCell dCell = (DCell) element;
            if (dCell.getTarget() instanceof EClass) {
                EClass editedEClass = ((EClass) dCell.getTarget());
                ((EPackage) editedEClass.eContainer()).getEClassifiers().iterator();
                List<EClass> classesCandidates = new ArrayList<EClass>();
                for (Iterator<EClassifier> iterator = ((EPackage) editedEClass.eContainer()).getEClassifiers().iterator(); iterator.hasNext();) {
                    EClassifier eClassifier = iterator.next();
                    if (eClassifier instanceof EClass && ((EClass) eClassifier).isAbstract() && !(eClassifier.equals(editedEClass))) {
                        classesCandidates.add((EClass) eClassifier);
                    }
                }

                result = new ExtendedDialogCellEditor(tree, getLabelProvider()) {
                    @Override
                    protected Object openDialogBox(final Control cellEditorWindow) {

                        FeatureEditorDialog dialog = new FeatureEditorDialog(cellEditorWindow.getShell(), getLabelProvider(), editedEClass, EcorePackage.eINSTANCE.getEClass_ESuperTypes().getEType(),
                                (List<?>) doGetValue(), DIALOG_TITLE, classesCandidates, false, true, true);
                        dialog.open();
                        return dialog.getResult();
                    }
                };
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
                if (object instanceof EClass) {
                    return AbstractClassCellEditorFactory.getDisplayedName(((EClass) object).getName());
                } else if (object instanceof List<?>) {
                    return AbstractClassCellEditorFactory.getDisplayedName(((List<?>) object).stream().map(EClass.class::cast).map(eClass -> eClass.getName()).collect(Collectors.joining(",")));
                } else {
                    return AbstractClassCellEditorFactory.getDisplayedName(object.toString());
                }
            }

            @Override
            public Image getImage(final Object object) {
                return null;
            }
        };
    }
}
