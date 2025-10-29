/*******************************************************************************
 * Copyright (c) 2014,2015 Obeo.
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
package org.eclipse.sirius.tests.unit.common.interpreter;

import java.util.List;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.ui.tools.internal.contentassist.ContentProposalConverter;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import junit.framework.TestCase;

/**
 * Tests for {@link CompoundInterpreter}
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class CompoundInterpreterTestCase extends TestCase {
    /**
     * available interpreters empty expressions
     */
    private List<ContentProposal> availableEmptyExpressions;

    /**
     * Interpreter context
     */
    private IInterpreterContext interpreterContext;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Get available interpreters empty expressions
        availableEmptyExpressions = CompoundInterpreter.INSTANCE.getAllNewEmtpyExpressions();
        assertFalse("At least one interpreter should be declared", availableEmptyExpressions.isEmpty());

        // Get an interpreter context
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
    }

    @Override
    protected void tearDown() throws Exception {
        availableEmptyExpressions = null;
        interpreterContext = null;

        super.tearDown();
    }

    /**
     * Ensures that we get all available interpreters prefixes for an empty or
     * null expression. see https://bugs.eclipse.org/bugs/show_bug.cgi?id=428770
     */
    public void testCompletionForEmptyExpression() {
        List<ContentProposal> proposalsForNullExpression = getProposals(null);
        assertTrue("Proposals should contains all interpreters empty expressions for a null context",
                proposalsForNullExpression.containsAll(availableEmptyExpressions) && proposalsForNullExpression.size() == availableEmptyExpressions.size());

        List<ContentProposal> proposalsForEmptyExpression = getProposals("");
        assertTrue("Proposals should contains all interpreters empty expressions for an empty context", proposalsForEmptyExpression.containsAll(availableEmptyExpressions)
                && proposalsForEmptyExpression.size() == availableEmptyExpressions.size());
    }

    /**
     * Ensures that we get interpreters empty expressions that match a non empty
     * context. see https://bugs.eclipse.org/bugs/show_bug.cgi?id=428770
     */
    public void testCompletionPerPrefixFirstChar() {
        for (ContentProposal emptyExpression : availableEmptyExpressions) {
            String firstChar = emptyExpression.getProposal().substring(0, 1);
            List<ContentProposal> proposals = getProposals(firstChar);
            ContentProposalConverter converter = new ContentProposalConverter(firstChar);

            // The following assert is valid for: "aql:" "[/]" "var:"
            // "service:" and "feature:"
            assertEquals("Proposals should contains only one proposal", 1, proposals.size());

            // Proposals are interpreter prefixes
            assertTrue("Proposals should be contained by available empty expressions", availableEmptyExpressions.containsAll(proposals));

            ContentProposal proposal = proposals.get(0);
            IContentProposal jfaceProposal = converter.convertToJFaceContentProposal(proposal);

            // Check JFace proposal
            assertEquals("", proposal.getProposal(), firstChar + jfaceProposal.getContent());

            // Check JFace cursor position
            assertEquals("JFace proposal cursor position is not well computed", proposal.getCursorPosition() - (proposal.getProposal().length() - jfaceProposal.getContent().length()),
                    jfaceProposal.getCursorPosition());
        }
    }

    /**
     * Ensures that for a valid interpreter syntax, we get proposals for the
     * selected interpreter but not for interpreters empty expressions. See
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=428770
     */
    public void testCompletionForInterpreterEmptyExpression() {
        for (ContentProposal emptyExpression : availableEmptyExpressions) {
            final List<ContentProposal> proposals = getProposals(emptyExpression.getProposal(), emptyExpression.getCursorPosition());

            // Proposals must be part of the selected interpreter, so not in
            // available empty expressions
            assertFalse("Proposals must not contains interpreter empty expressions", Iterables.any(availableEmptyExpressions, new Predicate<ContentProposal>() {
                @Override
                public boolean apply(ContentProposal arg0) {
                    return proposals.contains(arg0);
                }
            }));
        }
    }

    /**
     * Get proposals for a given expression
     * 
     * @param expression
     *            given expression
     * @return proposals
     */
    private List<ContentProposal> getProposals(String expression) {
        return getProposals(expression, expression != null ? expression.length() : 0);
    }

    /**
     * Get proposals for a given expression
     * 
     * @param expression
     *            given expression
     * @param cursorPosition
     *            cursor position
     * @return proposals
     */
    private List<ContentProposal> getProposals(String expression, int cursorPosition) {
        ContentContext context = new ContentContext(expression, cursorPosition, interpreterContext);
        return CompoundInterpreter.INSTANCE.getProposals(CompoundInterpreter.INSTANCE, context);
    }

}
