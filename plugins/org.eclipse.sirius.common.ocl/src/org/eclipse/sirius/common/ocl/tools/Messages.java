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
package org.eclipse.sirius.common.ocl.tools;

import org.eclipse.sirius.common.ocl.DslOclPlugin;
import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, DslOclPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String OclInterpreter_NewOclExpression;

    @TranslatableMessage
    public static String OclInterpreter_OclNotANumber;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
