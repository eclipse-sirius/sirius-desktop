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
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.IService;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;

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
        return new ContentProposal(ServiceInterpreter.PREFIX, ServiceInterpreter.PREFIX, "New variable expression.", 1);
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
            proposals = new ArrayList<ContentProposal>();
            // for (String dependency :
            // context.getInterpreterContext().getDependencies()) {
            // serviceInterpreter.addImport(dependency);
            // }
            // TODO : when the ServiceInterpreter will support service use from
            // workspace we could propose service completion
            for (Entry<String, IService> serviceEntry : serviceInterpreter.getServices().entrySet()) {
                String serviceName = serviceEntry.getKey();
                proposals.add(new ContentProposal(serviceName, serviceName, serviceName));
            }
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
            proposals = new ArrayList<ContentProposal>();
            for (String serviceName : serviceInterpreter.getServices().keySet()) {
                proposals.add(new ContentProposal(serviceName, serviceName, serviceName));
            }
        }
        return proposals;
    }

}
