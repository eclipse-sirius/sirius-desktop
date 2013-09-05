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
package org.eclipse.sirius.ui.business.api.session.factory;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;

/**
 * Factory to instantiate {@link IEditingSession}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface UISessionFactory {

    /**
     * Create a new {@link IEditingSession} from a specified {@link Session}.
     * 
     * @param session
     *            the {@link Session} for which create a {@link IEditingSession}
     * 
     * @return the newly created {@link IEditingSession}
     * 
     */
    IEditingSession createUISession(Session session);
}
