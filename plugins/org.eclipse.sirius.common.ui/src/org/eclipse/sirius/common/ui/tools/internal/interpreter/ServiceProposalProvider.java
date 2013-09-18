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
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.IMonomorphicService;
import org.eclipse.sirius.common.tools.internal.interpreter.IPolymorphicService;
import org.eclipse.sirius.common.tools.internal.interpreter.IService;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;

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
        return new ContentProposal(ServiceInterpreter.PREFIX, ServiceInterpreter.PREFIX, "New variable expression.", ServiceInterpreter.PREFIX.length());
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
            proposals = getProposals(context.getContents(), context.getPosition(), serviceInterpreter.getServices());
            // Reset the available services (in case of call for other VSM)
            serviceInterpreter.setProperty(IInterpreter.FILES, null);

            // for (String dependency :
            // context.getInterpreterContext().getDependencies()) {
            // serviceInterpreter.addImport(dependency);
            // }
            // TODO : when the ServiceInterpreter will support service use from
            // workspace we could propose service completion
        }
        return proposals;
    }

    /**
     * {@inheritDoc}
     */
    public List<ContentProposal> getProposals(IInterpreter interpreter, ContentInstanceContext context) {
        final List<ContentProposal> proposals;
        if (context == null || !(interpreter instanceof ServiceInterpreter)) {
            proposals = Collections.emptyList();
        } else if (context.getCurrentSelected() == null) {
            proposals = Collections.singletonList(getNewEmtpyExpression());
        } else {
            ServiceInterpreter serviceInterpreter = (ServiceInterpreter) interpreter;
            proposals = getProposals(context.getTextSoFar(), context.getCursorPosition(), serviceInterpreter.getServices());
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
            for (String serviceName : services.keySet()) {
                if (serviceName.startsWith(serviceNamePrefix) || isMatchingBeforeParameters(serviceName, serviceNamePrefix)) {
                    IService service = services.get(serviceName);
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


    private boolean isMatchingBeforeParameters(String serviceName, String serviceNamePrefix) {
        String[] result = serviceNamePrefix.split("\\(");
        return serviceName.equals(result[0]);
    }

    private void addAllImplementations(List<ContentProposal> proposals, IPolymorphicService service) {
        Set<String> serviceNames = new LinkedHashSet<String>();
        for (IMonomorphicService monomorphicService : service.getImplementers()) {
            String serviceName = monomorphicService.getName();
            List<String> parametersTypes = monomorphicService.getParametersTypes();
            Iterator<String> iterator = parametersTypes.iterator();
            if (iterator.hasNext()) {
                iterator.next();
                serviceName += "(";
                while (iterator.hasNext()) {
                    String parameter = iterator.next();
                    serviceName += parameter;
                    if (iterator.hasNext()) {
                        serviceName += ", ";
                    }
                }
                serviceName += ")";
            }

            serviceNames.add(serviceName);
        }

        for (String serviceName : serviceNames) {
            proposals.add(new ContentProposal(serviceName, serviceName, serviceName));
        }
    }
}
