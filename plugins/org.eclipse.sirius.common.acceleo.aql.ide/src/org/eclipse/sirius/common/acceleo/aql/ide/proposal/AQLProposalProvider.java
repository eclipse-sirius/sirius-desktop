/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.common.acceleo.aql.ide.proposal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.ICompletionProposal;
import org.eclipse.acceleo.query.runtime.ICompletionResult;
import org.eclipse.acceleo.query.runtime.IQueryCompletionEngine;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.QueryCompletion;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.acceleo.aql.business.api.AQLConstants;
import org.eclipse.sirius.common.acceleo.aql.business.api.ExpressionTrimmer;
import org.eclipse.sirius.common.acceleo.aql.business.api.TypesUtil;
import org.eclipse.sirius.common.acceleo.aql.business.internal.AQLSiriusInterpreter;
import org.eclipse.sirius.common.acceleo.aql.ide.Messages;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposalBuilder;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;

/**
 * This implementation of the {@link IProposalProvider} interface will be used in order to provide completion for
 * Acceleo Query expressions.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public class AQLProposalProvider implements IProposalProvider {

    @Override
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(AQLConstants.AQL_PREFIX, AQLConstants.AQL_PREFIX, Messages.AQL_newExpression, AQLConstants.AQL_PREFIX.length());
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        if (interpreter instanceof AQLSiriusInterpreter) {
            /*
             * the instance of interpreter has actually been created for the purpose of the completion and can be
             * modified at will with no risks.
             */
            ExpressionTrimmer trimer = new ExpressionTrimmer(context.getContents());
            if (trimer.positionIsWithinAQL(context.getPosition())) {
                AQLSiriusInterpreter aqlInterpreter = (AQLSiriusInterpreter) interpreter;
                setupInterpreter(context, aqlInterpreter);
                Map<String, Set<IType>> variableTypes = TypesUtil.createAQLVariableTypesFromInterpreterContext(context.getInterpreterContext(), aqlInterpreter.getQueryEnvironment());
                Set<ContentProposal> proposals = getProposals(trimer, context.getPosition(), aqlInterpreter.getQueryEnvironment(), variableTypes, ProposalAcceptanceStyle.PROPOSAL_INSERT);
                return Collections.unmodifiableList(new ArrayList<>(proposals));
            }
        }
        return Collections.<ContentProposal> emptyList();
    }

    private void setupInterpreter(ContentContext context, AQLSiriusInterpreter interpreter) {
        Collection<MetamodelDescriptor> metamodels = new ArrayList<MetamodelDescriptor>();
        for (EPackage pak : context.getInterpreterContext().getAvailableEPackages()) {
            if (pak != null) {
                metamodels.add(new EcoreMetamodelDescriptor(pak));
            }
        }
        interpreter.activateMetamodels(metamodels);
        if (context.getInterpreterContext().getElement() != null) {
            Resource vsmResource = context.getInterpreterContext().getElement().eResource();
            if (vsmResource != null) {
                interpreter.setProperty(IInterpreter.FILES, Arrays.asList(vsmResource.getURI().toPlatformString(true)));
            }
        }
        for (String imp : context.getInterpreterContext().getDependencies()) {
            interpreter.addImport(imp);
        }
    }

    /**
     * Evaluates the content proposals for a given expression and returns the result as a list.
     * 
     * @param trimmer
     *            the trimmer.
     * @param position
     *            position of the cursor.
     * @param queryEnvironment
     *            the IQueryEnvironment.
     * @param variableTypes
     *            the variable Types.
     * @param proposalAcceptanceStyle
     *            Integer that indicates how an accepted proposal should affect the global string.</br>
     *            The possible values are ContentProposalAdapter.PROPOSAL_INSERT or
     *            ContentProposalAdapter.PROPOSAL_REPLACE
     * @return content proposal list.
     */
    private Set<ContentProposal> getProposals(ExpressionTrimmer trimmer, int position, IQueryEnvironment queryEnvironment, Map<String, Set<IType>> variableTypes,
            ProposalAcceptanceStyle proposalAcceptanceStyle) {
        Set<ContentProposal> proposals = new LinkedHashSet<>();
        IQueryCompletionEngine engine = QueryCompletion.newEngine(queryEnvironment);
        final ICompletionResult completionResult = engine.getCompletion(trimmer.getExpression(), trimmer.getPositionWithinAQL(position), variableTypes);
        /*
         * completionResult.sort(new ProposalComparator());
         */
        final Set<ICompletionProposal> aqlProposals = new LinkedHashSet<>(completionResult.getProposals(QueryCompletion.createBasicFilter(completionResult)));

        for (ICompletionProposal propFromAQL : aqlProposals) {
            int offset = trimmer.getPositionInExpression(completionResult.getReplacementOffset());
            String proposal = ""; //$NON-NLS-1$
            ContentProposal contentProposal = null;
            if (proposalAcceptanceStyle.equals(ProposalAcceptanceStyle.PROPOSAL_INSERT)) {
                // as the TextContentProposalProvider uses the ContentProposalAdapter.PROPOSAL_INSERT style, we remove a
                // part of the proposal
                proposal = propFromAQL.getProposal().substring(completionResult.getReplacementLength() - completionResult.getRemaining().length());
                contentProposal = ContentProposalBuilder.proposal(proposal, propFromAQL.toString(), propFromAQL.getDescription(), propFromAQL.getCursorOffset()).build();
            } else if (proposalAcceptanceStyle.equals(ProposalAcceptanceStyle.PROPOSAL_REPLACE)) {
                int length = completionResult.getReplacementLength();
                proposal = propFromAQL.getProposal();
                contentProposal = ContentProposalBuilder.proposal(proposal, propFromAQL.toString(), propFromAQL.getDescription(), propFromAQL.getCursorOffset()).withReplacement(offset, length)
                        .build();
            }
            proposals.add(contentProposal);
        }
        return proposals;
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        if (interpreter instanceof AQLSiriusInterpreter) {
            IQueryEnvironment queryEnvironment = ((AQLSiriusInterpreter) interpreter).getQueryEnvironment();

            Map<String, Set<IType>> variableTypes = new LinkedHashMap<String, Set<IType>>();
            if (context.getCurrentSelected() != null) {
                queryEnvironment.registerEPackage(context.getCurrentSelected().eClass().getEPackage());
                final Set<IType> potentialTypes = new LinkedHashSet<IType>(1);
                potentialTypes.add(new EClassifierType(queryEnvironment, context.getCurrentSelected().eClass()));
                variableTypes.put("self", potentialTypes); //$NON-NLS-1$
            }

            ExpressionTrimmer trimer = new ExpressionTrimmer(context.getTextSoFar());
            if (trimer.positionIsWithinAQL(context.getCursorPosition())) {
                Set<ContentProposal> proposals = getProposals(trimer, context.getCursorPosition(), queryEnvironment, variableTypes, ProposalAcceptanceStyle.PROPOSAL_REPLACE);
                return Collections.unmodifiableList(new ArrayList<>(proposals));
            }

        }
        return Collections.<ContentProposal> emptyList();
    }
}
