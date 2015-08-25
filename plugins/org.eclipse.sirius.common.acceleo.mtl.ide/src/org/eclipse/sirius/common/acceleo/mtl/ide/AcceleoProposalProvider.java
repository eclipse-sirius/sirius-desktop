/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.ide;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.acceleo.parser.interpreter.CompilationContext;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.AcceleoMTLInterpreter;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.DynamicAcceleoModule;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.DynamicAcceleoModule.QueryIdentifier;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * This implementation of the {@link IProposalProvider} interface will be used
 * in order to provide completion for Acceleo 3 expressions.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class AcceleoProposalProvider implements IProposalProvider {
    /**
     * This represents the prefix of an Acceleo 3 expression.
     * 
     * @see IAcceleoConstants#DEFAULT_BEGIN
     */
    private static final String ACCELEO_EXPRESSION_PREFIX = IAcceleoConstants.DEFAULT_BEGIN;

    /**
     * This represents the suffix of an Acceleo 3 expression.
     * 
     * @see IAcceleoConstants#INVOCATION_END
     */
    private static final String ACCELEO_EXPRESSION_SUFFIX = IAcceleoConstants.DEFAULT_END_BODY_CHAR + IAcceleoConstants.DEFAULT_END;


    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider#getNewEmtpyExpression()
     */
    @Override
    public ContentProposal getNewEmtpyExpression() {
        final String emptyAcceleoExpression = ACCELEO_EXPRESSION_PREFIX + ACCELEO_EXPRESSION_SUFFIX;
        return new ContentProposal(emptyAcceleoExpression, emptyAcceleoExpression, Messages.AcceleoProposalProvider_MTL_newExpression, 1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider#getProposals(org.eclipse.sirius.common.tools.api.interpreter.IInterpreter,
     *      org.eclipse.sirius.common.tools.api.contentassist.ContentContext)
     */
    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof AcceleoMTLInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getContents() == null || context.getContents().length() == 0) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            final AcceleoMTLInterpreter acceleoInterpreter = (AcceleoMTLInterpreter) interpreter;
            final IInterpreterContext interpreterContext = context.getInterpreterContext();

            final Map<String, String> validationVariables = Maps.newLinkedHashMap();
            for (Map.Entry<String, VariableType> contextVariable : interpreterContext.getVariables().entrySet()) {
                final String varName = contextVariable.getKey();
                final VariableType varType = contextVariable.getValue();
                if (varName != null && varName.length() > 0 && varType.hasDefinition()) {
                    if (varName.matches("[0-9]+")) { //$NON-NLS-1$
                        // Ignore old Acceleo 2 style numeric variables used for
                        // direct edit tools.
                        continue;
                    }
                    validationVariables.put(varName, varType.getCommonType(context.getInterpreterContext().getAvailableEPackages()).getCompleteName(IAcceleoConstants.NAMESPACE_SEPARATOR));
                }
            }

            final String targetType;
            VariableType selfType = interpreterContext.getTargetType();
            if (!interpreterContext.requiresTargetType()) {
                targetType = "ecore::EObject"; //$NON-NLS-1$
            } else if (selfType.hasDefinition()) {
                targetType = interpreterContext.getTargetType().getCommonType(context.getInterpreterContext().getAvailableEPackages()).getCompleteName(IAcceleoConstants.NAMESPACE_SEPARATOR);
            } else {
                targetType = null;
            }

            final CompilationContext acceleoContext = acceleoInterpreter.createCompilationContext(interpreterContext, context.getContents(), targetType, validationVariables);
            final DynamicAcceleoModule module = acceleoInterpreter.getModule();
            final QueryIdentifier identifier = module.ensureQueryExists(acceleoContext);
            final String moduleString = module.buildMTL(acceleoContext, identifier);

            final List<URI> dependencyURIs = Lists.newArrayList(acceleoContext.getDependencies().values());
            dependencyURIs.addAll(module.compileExtendedDependencies(acceleoContext, module.getResourceSet()));

            final AcceleoCompletionService completionService = new AcceleoCompletionService(moduleString, module, dependencyURIs);
            proposals = completionService.getProposals(acceleoContext.getExpression(), context.getPosition(), identifier.getQueryName());
        }
        return proposals;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider#getProposals(org.eclipse.sirius.common.tools.api.interpreter.IInterpreter,
     *      org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext)
     */
    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof AcceleoMTLInterpreter) || context.getCurrentSelected() == null) {
            proposals = Collections.emptyList();
        } else if (StringUtil.isEmpty(context.getTextSoFar())) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            final AcceleoMTLInterpreter acceleoInterpreter = (AcceleoMTLInterpreter) interpreter;

            final EObject target = context.getCurrentSelected();

            final CompilationContext acceleoContext = acceleoInterpreter.createCompilationContext(target, context.getTextSoFar());
            final DynamicAcceleoModule module = acceleoInterpreter.getModule();
            final QueryIdentifier identifier = module.ensureQueryExists(acceleoContext);
            final String moduleString = module.buildMTL(acceleoContext, identifier);

            final List<URI> dependencyURIs = Lists.newArrayList(acceleoContext.getDependencies().values());
            dependencyURIs.addAll(module.compileExtendedDependencies(acceleoContext, module.getResourceSet()));

            final AcceleoCompletionService completionService = new AcceleoCompletionService(moduleString, module, dependencyURIs);
            proposals = completionService.getProposals(acceleoContext.getExpression(), context.getCursorPosition(), identifier.getQueryName());
        }
        return proposals;
    }
}
