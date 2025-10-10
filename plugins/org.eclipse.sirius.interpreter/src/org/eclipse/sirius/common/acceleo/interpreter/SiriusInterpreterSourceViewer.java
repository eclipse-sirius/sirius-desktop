/*******************************************************************************
 * Copyright (c) 2011, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.acceleo.interpreter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.acceleo.ui.interpreter.language.IInterpreterSourceViewer;
import org.eclipse.acceleo.ui.interpreter.language.InterpreterContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.swt.widgets.Composite;

/**
 * This will be provided to the interpreter view as the source viewer for the "viewpoint" language.
 * <p>
 * Though it will not be capable of displaying syntax highlighting, it will provide the completion for all languages
 * supported by viewpoint.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class SiriusInterpreterSourceViewer extends SourceViewer implements IInterpreterSourceViewer {
    /**
     * This will be computed every time {@link #showContentAssist(InterpreterContext)} is called.
     */
    private Collection<ContentProposal> computedProposals;

    /**
     * Delegates to the super constructor.
     * 
     * @param parent
     *            the parent of the viewer's control
     * @param ruler
     *            the vertical ruler used by this source viewer
     * @param styles
     *            the SWT style bits for the viewer's control, <em>if <code>SWT.WRAP</code> is set then a custom
     *            document adapter needs to be provided, see {@link #createDocumentAdapter()}
     */
    public SiriusInterpreterSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
        super(parent, ruler, styles);
    }

    /**
     * Returns the computed content proposals.
     * 
     * @return The computed content proposals.
     */
    public Collection<ContentProposal> getComputedProposals() {
        return computedProposals;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.acceleo.ui.interpreter.language.IInterpreterSourceViewer#showContentAssist(org.eclipse.acceleo.ui.interpreter.language.InterpreterContext)
     */
    public void showContentAssist(InterpreterContext context) {
        if (fContentAssistant == null) {
            return;
        }

        final String expression = context.getExpression();

        // Step 1: get the interpreter related to the given expression
        if (expression != null && expression.length() != 0) {
            final EObject target;
            if (context.getTargetEObjects().isEmpty()) {
                target = null;
            } else {
                target = context.getTargetEObjects().get(0);
            }
            Session curSession = SessionManager.INSTANCE.getSession(target);

            final IInterpreter vpInterpreter;
            if (curSession != null) {
                vpInterpreter = curSession.getInterpreter();
            } else {
                vpInterpreter = CompoundInterpreter.INSTANCE.getInterpreterForExpression(expression);
            }

            final ContentInstanceContext contentContext = new ContentInstanceContext(target, context.getExpression(), getSelectedRange().x);

            // Step 2: looking for an IProposalProvider for the given
            // interpreter
            // Case 2.1: can be the interpreter itself
            if (vpInterpreter instanceof IProposalProvider) {
                computedProposals = ((IProposalProvider) vpInterpreter).getProposals(vpInterpreter, contentContext);
            } else {
                // Case 2.2: can be provided by extension point
                computedProposals = new LinkedHashSet<>();
                final List<IProposalProvider> proposalProviders = ProposalProviderRegistry.getProvidersFor(vpInterpreter);
                for (IProposalProvider provider : proposalProviders) {
                    computedProposals.addAll(provider.getProposals(vpInterpreter, contentContext));
                }
            }
        } else {
            computedProposals = CompoundInterpreter.INSTANCE.getAllNewEmtpyExpressions();
        }

        fContentAssistant.showPossibleCompletions();
        computedProposals = null;
    }
}
