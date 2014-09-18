/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Context for DTree/semantic model synchronization.
 * 
 * @author cbrun
 */
public class GlobalContext {

    private ModelAccessor accessor;

    private IInterpreter interpreter;

    private Collection<Resource> semanticResources;

    /**
     * Creates a new GlobalContext.
     * 
     * @param accessor
     *            the model accessor to use
     * @param interpreter
     *            the IInterpreter to use
     * @param semanticResources
     *            semantic {@link Resource} on which model synchronization must
     *            occurs
     */
    public GlobalContext(ModelAccessor accessor, IInterpreter interpreter, Collection<Resource> semanticResources) {
        super();
        this.accessor = accessor;
        this.interpreter = interpreter;
        this.semanticResources = semanticResources;
    }

    public ModelAccessor getModelAccessor() {
        return this.accessor;
    }

    public IInterpreter getInterpreter() {
        return this.interpreter;
    }

    public Collection<Resource> getSemanticResources() {
        return semanticResources;
    }

    public SpecifierMonitor getSpecifierFeedBack() {
        return new SpecifierMonitor();
    }

}
