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

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Context for DTree/semantic model synchronization.
 * 
 * @author cbrun
 */
public class GlobalContext {

    private ModelAccessor accessor;

    private IInterpreter interpreter;

    private Option<Session> session = Options.newNone();

    /**
     * Creates a new GlobalContext.
     * 
     * @param accessor
     *            the model accessor to use
     * @param interpreter
     *            the IInterpreter to use
     */
    public GlobalContext(ModelAccessor accessor, IInterpreter interpreter) {
        super();
        this.accessor = accessor;
        this.interpreter = interpreter;
    }

    /**
     * Creates a new GlobalContext.java.
     * 
     * @param accessor
     *            the model accessor to use
     * @param session
     *            the session to use
     */
    public GlobalContext(ModelAccessor accessor, Session session) {
        super();
        this.accessor = accessor;
        this.session = Options.newSome(session);
        this.interpreter = session.getInterpreter();
    }

    public ModelAccessor getModelAccessor() {
        return this.accessor;
    }

    public IInterpreter getInterpreter() {
        return this.interpreter;
    }

    public Option<Session> getSession() {
        return session;
    }

    public SpecifierMonitor getSpecifierFeedBack() {
        return new SpecifierMonitor();
    }

}
