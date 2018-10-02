/*******************************************************************************
 * Copyright (c) 2016 Kiel University and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.Collection;

import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.ILayoutSetup;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * Layout setup for the Sirius connector allowing to inject
 * {@link ElkDiagramLayoutConnector} in the ELK layout engine.
 * 
 * Copied from org.eclipse.elk.conn.gmf.GmfLayoutSetup of commit
 * e99248e44c71a5a02fe45bc4cd5150cd7f50c559.
 * 
 * Adapted to use our custom connector.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ElkSiriusLayoutSetup implements ILayoutSetup {

    @Override
    public boolean supports(final Object object) {
        if (object instanceof Collection) {
            Collection<?> collection = (Collection<?>) object;
            for (Object o : collection) {
                if (o instanceof IGraphicalEditPart) {
                    return true;
                }
            }
            return false;
        }
        return object instanceof DiagramEditor || object instanceof IGraphicalEditPart;
    }

    @Override
    public Injector createInjector(final Module defaultModule) {
        return Guice.createInjector(Modules.override(defaultModule).with(new SiriusLayoutModule()));
    }

    /**
     * Guice module for the Sirius connector.
     */
    public static class SiriusLayoutModule implements Module {

        @Override
        public void configure(final Binder binder) {
            binder.bind(IDiagramLayoutConnector.class).to(ElkDiagramLayoutConnector.class);
        }

    }

}
