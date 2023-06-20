/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.diagram;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker.DiagramEventBrokerFactory;

/**
 * A {@link DiagramEventBrokerFactory} to provide a Sirius DiagramEventBrokerThreadSafe.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusDiagramEventBrokerFactory implements DiagramEventBroker.DiagramEventBrokerFactory {

    @Override
    public DiagramEventBroker createDiagramEventBroker(TransactionalEditingDomain editingDomain) {
        return new SiriusDiagramEventBroker(editingDomain);
    }

}
