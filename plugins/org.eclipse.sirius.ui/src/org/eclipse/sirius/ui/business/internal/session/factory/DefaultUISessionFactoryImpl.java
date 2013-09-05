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
package org.eclipse.sirius.ui.business.internal.session.factory;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.factory.UISessionFactory;
import org.eclipse.sirius.ui.business.internal.session.EditingSession;

/**
 * Default {@link UISessionFactory} implementation.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DefaultUISessionFactoryImpl implements UISessionFactory {

    /**
     * Overridden to create a {@link EditingSession}.
     * 
     * {@inheritDoc}
     */
    public IEditingSession createUISession(Session session) {
        return new EditingSession(session);
    }

}
