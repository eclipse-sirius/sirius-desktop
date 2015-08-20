/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.aql.ide.proposal;

import java.util.ArrayList;
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
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * This implementation of the {@link IProposalProvider} interface will be used
 * in order to provide completion for Acceleo Query expressions.
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
             * the instance of interpreter has actually been created for the
             * purpose of the completion and can be modified at will with no
             * risks.
             */
            ExpressionTrimmer trimer = new ExpressionTrimmer(context.getContents());
            if (trimer.positionIsWithinAQL(context.getPosition())) {
                AQLSiriusInterpreter aqlInterpreter = (AQLSiriusInterpreter) interpreter;
                setupInterpreter(context, aqlInterpreter);
                Map<String, Set<IType>> variableTypes = TypesUtil.createAQLVariableTypesFromInterpreterContext(context.getInterpreterContext(), aqlInterpreter.getQueryEnvironment());

                return ImmutableList.copyOf(getProposals(trimer, context.getPosition(), aqlInterpreter.getQueryEnvironment(), variableTypes));
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
        Resource vsmResource = context.getInterpreterContext().getElement().eResource();
        if (vsmResource != null) {
            interpreter.setProperty(IInterpreter.FILES, Lists.newArrayList(vsmResource.getURI().toPlatformString(true)));
        }
        for (String imp : context.getInterpreterContext().getDependencies()) {
            interpreter.addImport(imp);
        }
    }

    private Set<ContentProposal> getProposals(ExpressionTrimmer trimmer, int position, IQueryEnvironment queryEnvironment, Map<String, Set<IType>> variableTypes) {
        Set<ContentProposal> proposals = Sets.newLinkedHashSet();
        IQueryCompletionEngine engine = QueryCompletion.newEngine(queryEnvironment);
        final ICompletionResult completionResult = engine.getCompletion(trimmer.getExpression(), trimmer.getPositionWithinAQL(position), variableTypes);
        /*
         * completionResult.sort(new ProposalComparator());
         */
        final Set<ICompletionProposal> proposal = Sets.newLinkedHashSet(completionResult.getProposals(QueryCompletion.createBasicFilter(completionResult)));

        for (ICompletionProposal propFromAQL : proposal) {
            ContentProposal propForSirius = new ContentProposal(propFromAQL.getProposal(), propFromAQL.getProposal(), propFromAQL.getDescription(), propFromAQL.getCursorOffset());
            proposals.add(propForSirius);
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
                return ImmutableList.copyOf(getProposals(trimer, context.getCursorPosition(), queryEnvironment, variableTypes));
            }

        }
        return Collections.<ContentProposal> emptyList();
    }
}
