/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.common.tools.internal.interpreter.IMonomorphicService;
import org.eclipse.sirius.common.tools.internal.interpreter.IPolymorphicService;
import org.eclipse.sirius.common.tools.internal.interpreter.IService;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.VariableInterpreter;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.Lists;

/**
 * A {@link IProposalProvider} to provide completion for the service
 * interpreter.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ServiceProposalProvider implements IProposalProvider {

    /**
     * {@inheritDoc}
     */
    public ContentProposal getNewEmtpyExpression() {
        return new ContentProposal(ServiceInterpreter.PREFIX, ServiceInterpreter.PREFIX, "New service calls expression.", ServiceInterpreter.PREFIX.length());
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof ServiceInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getContents() == null || context.getContents().length() == 0) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            ServiceInterpreter serviceInterpreter = (ServiceInterpreter) interpreter;
            // Compute the available services
            Resource vsmResource = context.getInterpreterContext().getElement().eResource();
            if (vsmResource != null) {
                serviceInterpreter.setProperty(IInterpreter.FILES, Lists.newArrayList(vsmResource.getURI().toPlatformString(true)));
            }
            for (String dependency : context.getInterpreterContext().getDependencies()) {
                serviceInterpreter.addImport(dependency);
            }
            // Get service proposals
            proposals = getProposals(context.getContents(), context.getPosition(), serviceInterpreter.getServices());
            // Add all variable proposals
            ContentContext varContext = new ContentContext(context.getContents().replaceFirst(ServiceInterpreter.PREFIX, VariableInterpreter.PREFIX), context.getPosition()
                    - ServiceInterpreter.PREFIX.length() + VariableInterpreter.PREFIX.length(), context.getInterpreterContext());
            proposals.addAll(getVariableProposals(context.getContents(), varContext));

        }
        return proposals;
    }

    @Override
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof ServiceInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getCurrentSelected() == null) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            ServiceInterpreter serviceInterpreter = (ServiceInterpreter) interpreter;
            // Get service proposals
            proposals = getProposals(context.getTextSoFar(), context.getCursorPosition(), serviceInterpreter.getServices());
            // Add all variable proposals
            ContentInstanceContext varContext = new ContentInstanceContext(context.getCurrentSelected(), context.getTextSoFar().replaceFirst(ServiceInterpreter.PREFIX, VariableInterpreter.PREFIX),
                    context.getCursorPosition() - ServiceInterpreter.PREFIX.length() + VariableInterpreter.PREFIX.length(), context.getEditingDomain());
            proposals.addAll(getVariableProposals(context.getTextSoFar(), varContext));
        }
        return proposals;
    }

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as a list.
     * 
     * @param writtenExpression
     *            The complete expression with feature: prefix.
     * @param cursorPosition
     *            The current cursor position to consider only characters before
     *            it.
     * @param services
     *            Map of services by name.
     * @return content proposal list.
     */
    private List<ContentProposal> getProposals(String writtenExpression, int cursorPosition, Map<String, IService> services) {
        final List<ContentProposal> proposals = new ArrayList<ContentProposal>();

        // Keep only characters before cursor
        String serviceNamePrefix = writtenExpression.substring(0, cursorPosition);
        // Remove not needed space characters.
        serviceNamePrefix = serviceNamePrefix.trim();
        // Remove "service:" prefix if the cursor position is after the prefix
        // If the cursor position is before the prefix, there is no proposal
        // returned.
        if (serviceNamePrefix.length() >= ServiceInterpreter.PREFIX.length()) {
            serviceNamePrefix = serviceNamePrefix.substring(ServiceInterpreter.PREFIX.length());

            // Remove the receiver name (and the receiver separator) if any
            Option<String> receiverVariableName = ServiceInterpreter.getReceiverVariableName(serviceNamePrefix);
            if (receiverVariableName.some()) {
                serviceNamePrefix = serviceNamePrefix.substring(receiverVariableName.get().length() + 1);
            }

            for (Entry<String, IService> entry : services.entrySet()) {
                String serviceName = entry.getKey();
                if (serviceName.startsWith(serviceNamePrefix)) {
                    IService service = entry.getValue();
                    if (service instanceof IPolymorphicService) {
                        addAllImplementations(proposals, (IPolymorphicService) service);
                    } else {
                        proposals.add(new ContentProposal(serviceName, serviceName, serviceName));
                    }
                }
            }
        }
        return proposals;
    }

    /**
     * Compute all variable proposals by using VariableProposalProvider and add
     * a "." to them before returning them.
     * 
     * @param writtenExpression
     *            The complete expression with feature: prefix.
     * @param context
     *            Should be a {@link ContentInstanceContext} or
     *            {@link ContentContext}.
     */
    private List<ContentProposal> getVariableProposals(String writtenExpression, Object context) {
        if (!(context instanceof ContentContext) && !(context instanceof ContentInstanceContext)) {
            throw new IllegalArgumentException("The context parameter should be a ContentContext or a ContentInstanceContext");
        }
        List<ContentProposal> proposals = new ArrayList<ContentProposal>();
        Option<String> receiverVariableName = ServiceInterpreter.getReceiverVariableName(writtenExpression);
        if (!receiverVariableName.some()) {
            // If there is no "." in the expression, we also add all
            // available variables by using the VariableProposalProvider
            VariableInterpreter variableInterpreter = (VariableInterpreter) CompoundInterpreter.INSTANCE.getInterpreterForExpression(VariableInterpreter.PREFIX);
            if (variableInterpreter != null) {
                final List<IProposalProvider> proposalProviders = ProposalProviderRegistry.getProvidersFor(variableInterpreter);
                for (IProposalProvider provider : proposalProviders) {
                    List<ContentProposal> variableProposals = null;
                    if (context instanceof ContentContext) {
                        variableProposals = provider.getProposals(variableInterpreter, (ContentContext) context);
                    } else if (context instanceof ContentInstanceContext) {
                        variableProposals = provider.getProposals(variableInterpreter, (ContentInstanceContext) context);
                    }
                    // Add a "." to each proposal
                    for (ContentProposal contentProposal : variableProposals) {
                        proposals.add(new ContentProposal(contentProposal.getProposal() + ServiceInterpreter.RECEIVER_SEPARATOR, contentProposal.getProposal() + ServiceInterpreter.RECEIVER_SEPARATOR
                                + ": " + contentProposal.getInformation(), contentProposal.getInformation(), contentProposal.getCursorPosition() + 1)); //$NON-NLS-1$
                    }
                }
            }
        }
        return proposals;
    }

    private void addAllImplementations(List<ContentProposal> proposals, IPolymorphicService service) {
        Set<String> serviceNames = new LinkedHashSet<String>();
        for (IMonomorphicService monomorphicService : service.getImplementers()) {
            String serviceName = monomorphicService.getName();
            List<String> parametersTypes = monomorphicService.getParametersTypes();
            Iterator<String> iterator = parametersTypes.iterator();
            if (iterator.hasNext()) {
                iterator.next();
                serviceName += "("; //$NON-NLS-1$
                while (iterator.hasNext()) {
                    String parameter = iterator.next();
                    serviceName += parameter;
                    if (iterator.hasNext()) {
                        serviceName += ", "; //$NON-NLS-1$
                    }
                }
                serviceName += ")"; //$NON-NLS-1$
            }

            serviceNames.add(serviceName);
        }

        for (String serviceName : serviceNames) {
            proposals.add(new ContentProposal(serviceName, serviceName, serviceName));
        }
    }
}
