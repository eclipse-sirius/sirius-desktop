/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.description.spec;

import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;

/**
 * Customization of {@link IdentifiedElementImpl}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class IdentifiedElementSpec extends IdentifiedElementImpl {

    @Override
    public String getLabel() {
        return MessageTranslator.INSTANCE.getMessage(super.getLabel());
    }

}
