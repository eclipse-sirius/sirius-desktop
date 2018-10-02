/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Class owning methods used for "service:" interpreter, aql interpreter or others. The service methods should be
 * prefixed by std to avoid conflict with other services.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StandardDiagramServices {
    /**
     * A green color for all fields representing a domain class (like in SiriusEditor.colorRegistry).
     */
    private static final RGB DOMAIN_CLASS_FIELD_COLOR = new RGB(204, 242, 166);

    /**
     * A yellow color for all fields representing an interpreted expression (like in SiriusEditor.colorRegistry).
     */
    private static final RGB INTERPRETED_EXPRESSION_FIELD_COLOR = new RGB(255, 245, 181);

    private Color domainClassFieldColor;

    private Color interpretedExpressionFieldColor;

    /**
     * Default constructor.
     */
    public StandardDiagramServices() {
    }

    /**
     * Return the list of DSemanticDecorator having the same mappings as <code>selectedViews</code> in the
     * <code>currentDiagram</code>.
     * 
     * @param currentDiagram
     *            The current {@link DSemanticDiagram}
     * @param selectedViews
     *            The selected views. If there are several selected views all mappings associated to this views will be
     *            considered.
     * @return A list of DSemanticDecorator having the same mappings as already selected views.
     */
    public List<DSemanticDecorator> stdGetViewsWithSameMapping(DSemanticDiagram currentDiagram, List<DSemanticDecorator> selectedViews) {
        List<DSemanticDecorator> result = new ArrayList<>();
        Set<DiagramElementMapping> mappingsToSelect = new HashSet<>();
        for (DSemanticDecorator dSemanticDecorator : selectedViews) {
            if (dSemanticDecorator instanceof DDiagramElement) {
                mappingsToSelect.add(((DDiagramElement) dSemanticDecorator).getDiagramElementMapping());
            }
        }
        for (DDiagramElement dde : new DDiagramQuery(currentDiagram).getAllDiagramElements()) {
            if (mappingsToSelect.contains(dde.getDiagramElementMapping())) {
                result.add(dde);
            }
        }
        return result;
    }

    /**
     * Return the list of DSemanticDecorator representing semantic element with same EType as <code>selectedViews</code>
     * in the <code>currentDiagram</code>.
     * 
     * @param currentDiagram
     *            The current {@link DSemanticDiagram}
     * @param selectedViews
     *            The selected views. If there are several selected views all EType of semantic element of this views
     *            will be considered.
     * @return A list of DSemanticDecorator having the same EType as already selected views.
     */
    public List<DSemanticDecorator> stdGetViewsRepresentingSameEType(DSemanticDiagram currentDiagram, List<DSemanticDecorator> selectedViews) {
        List<DSemanticDecorator> result = new ArrayList<>();
        Set<EClass> eClassToSelect = new HashSet<>();
        for (DSemanticDecorator dSemanticDecorator : selectedViews) {
            if (dSemanticDecorator instanceof DDiagramElement) {
                eClassToSelect.add(((DDiagramElement) dSemanticDecorator).getTarget().eClass());
            }
        }
        for (DDiagramElement dde : new DDiagramQuery(currentDiagram).getAllDiagramElements()) {
            if (eClassToSelect.contains(dde.getTarget().eClass())) {
                result.add(dde);
            }
        }
        return result;
    }

    /**
     * Return the list of DSemanticDecorator in the <code>currentDiagram</code> representing semantic element having the
     * EType provided by the end-user through a dialog box.
     * 
     * @param currentDiagram
     *            The current {@link DSemanticDiagram}
     * @return A list of DSemanticDecorator having the selected EType.
     */
    public List<DSemanticDecorator> stdGetViewsRepresentingSelectedType(DSemanticDiagram currentDiagram) {
        List<DSemanticDecorator> result = new ArrayList<>();
        // Open a dialog to select the EType
        InputDialog domainClassDialog = new InputDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.StandardDiagramServices_sameETypeTitle,
                Messages.StandardDiagramServices_sameETypeMessage, StringUtil.EMPTY_STRING, null) {
            @Override
            protected Control createDialogArea(Composite parent) {
                Control control = super.createDialogArea(parent);
                getText().setBackground(getDomainClassFieldColor(getShell().getDisplay()));
                getText().setToolTipText(Messages.StandardDiagramServices_sameETypeTooltip);
                // TODO: Improve this field to have completion and propose only EType of the metamodel known by the
                // current diagram.
                return control;
            }
        };
        if (Window.OK == domainClassDialog.open()) {
            Optional<Session> optionalSession = Session.of(currentDiagram);
            if (optionalSession.isPresent()) {
                String typeName = domainClassDialog.getValue();
                ModelAccessor modelAccessor = optionalSession.get().getModelAccessor();
                for (DDiagramElement dde : new DDiagramQuery(currentDiagram).getAllDiagramElements()) {
                    if (modelAccessor.eInstanceOf(dde.getTarget(), typeName.trim())) {
                        result.add(dde);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Return the list of DSemanticDecorator corresponding to the evaluation of an expression written by the end-user in
     * a dialog box.
     * 
     * @param currentDiagram
     *            The current {@link DSemanticDiagram}
     * @return A list of DSemanticDecorator.
     */
    public List<DSemanticDecorator> stdGetViewsOfExpression(DSemanticDiagram currentDiagram) {
        List<DSemanticDecorator> result = new ArrayList<>();
        Optional<Session> optionalSession = Session.of(currentDiagram);
        if (optionalSession.isPresent()) {
            // Open a dialog to select the EType
            InputDialog expressionDialog = new InputDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.StandardDiagramServices_expressionTitle,
                    Messages.StandardDiagramServices_expressionMessage, StringUtil.EMPTY_STRING, null) {
                @Override
                protected Control createDialogArea(Composite parent) {
                    Control control = super.createDialogArea(parent);
                    getText().setBackground(getInterpretedExpressionFieldColor(getShell().getDisplay()));
                    getText().setToolTipText(Messages.StandardDiagramServices_expressionTooltip);
                    // TODO: Improve this field to have completion and also validation, ideally like in Interpreter view
                    return control;
                }
            };
            if (Window.OK == expressionDialog.open()) {
                String expression = expressionDialog.getValue();
                try {
                    // TODO: Add variables to the interpreter
                    // * self --> DSemanticDiagram currentDiagram
                    // * ??? --> List<DSemanticDecorator> selectedViews,
                    Collection<EObject> expressionResult = optionalSession.get().getInterpreter().evaluateCollection(currentDiagram, expression);
                    for (EObject eObject : expressionResult) {
                        if (eObject instanceof DDiagramElement) {
                            result.add((DDiagramElement) eObject);
                        }
                    }
                } catch (EvaluationException e) {
                    // Do nothing (currently there is no feedback if the expression is wrong).
                }
            }
        }
        return result;
    }

    private Color getDomainClassFieldColor(Display display) {
        if (domainClassFieldColor == null) {
            domainClassFieldColor = new Color(display, DOMAIN_CLASS_FIELD_COLOR);
        }
        return domainClassFieldColor;
    }

    private Color getInterpretedExpressionFieldColor(Display display) {
        if (interpretedExpressionFieldColor == null) {
            interpretedExpressionFieldColor = new Color(display, INTERPRETED_EXPRESSION_FIELD_COLOR);
        }
        return interpretedExpressionFieldColor;
    }
}
