/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.quickfix;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;
import org.eclipse.ui.IEditorPart;

/**
 * QuickFix resolution executing a {@link ValidationFix} description.
 * 
 * @author cbrun
 * 
 */
public class ValidationFixResolution extends AbstractValidationFix {

    private ValidationFix fix;

    /**
     * Create a new {@link ValidationFixResolution} from a {@link ValidationFix} .
     * 
     * @param fix
     *            {@link ValidationFix} to execute.
     */
    public ValidationFixResolution(ValidationFix fix) {
        this.fix = fix;
    }

    @Override
    public String getLabel() {
        return fix.getName();
    }

    @Override
    protected void doExecuteFix(IMarker marker, IEditorPart editor, View markedView, TransactionalEditingDomain transactionalEditingDomain) {
        EObject fixTarget = getFixTarget(markedView);
        if (fixTarget != null) {
            Diagram diagram = markedView.getDiagram();
            executeFix(editor, (DDiagram) diagram.getElement(), fixTarget, transactionalEditingDomain);
            revalidate(editor, diagram);
        }
    }

    private EObject getFixTarget(View markedView) {
        EObject fixTarget = markedView.getElement();
        if (fixTarget instanceof DSemanticDecorator && !isViewValidationRule()) {
            fixTarget = ((DSemanticDecorator) fixTarget).getTarget();
        }
        return fixTarget;
    }

    private boolean isViewValidationRule() {
        return (fix.eContainer() instanceof ViewValidationRule);
    }

    private void executeFix(IEditorPart editor, DDiagram diagram, EObject fixTarget, TransactionalEditingDomain domain) {
        IDiagramCommandFactory commandFactory = getDiagramCommandFactory(editor, domain);

        if (commandFactory != null && fixTarget != null) {
            Command fixCommand = commandFactory.buildQuickFixOperation(fix, fixTarget, diagram);

            // Set the RefreshEditorsListener in forceRefresh mode
            EObject semanticTarget = getSemanticTarget(fixTarget);
            Session session = SessionManager.INSTANCE.getSession(semanticTarget);
            if (session != null) {
                session.getRefreshEditorsListener().setForceRefresh(true);
            }

            // Execute the quick fix command
            domain.getCommandStack().execute(fixCommand);
        }
    }

    private EObject getSemanticTarget(EObject fixTarget) {
        // The fix target could be the DDiagramElement or the semantic element
        // (a rule could be a ViewValidationRule or a SemanticValidationRule
        EObject semanticTarget = fixTarget;
        if (semanticTarget instanceof DSemanticDecorator) {
            semanticTarget = ((DSemanticDecorator) semanticTarget).getTarget();
        }
        return semanticTarget;
    }

    private IDiagramCommandFactory getDiagramCommandFactory(IEditorPart editor, TransactionalEditingDomain domain) {
        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider diagramCmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        return diagramCmdFactoryProvider.getCommandFactory(domain);
    }

}
