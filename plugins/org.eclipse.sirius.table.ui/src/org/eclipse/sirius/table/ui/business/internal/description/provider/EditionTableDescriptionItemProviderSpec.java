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
package org.eclipse.sirius.table.ui.business.internal.description.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.table.metamodel.table.description.provider.EditionTableDescriptionItemProvider;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * Specific implementation of {@link EditionTableDescriptionItemProvider}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class EditionTableDescriptionItemProviderSpec extends EditionTableDescriptionItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            current {@link AdapterFactory}
     */
    public EditionTableDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     * 
     * This method has been overridden in order to return a localized text if
     * available.
     */
    public String getText(Object object) {
        return MessageTranslator.INSTANCE.getMessage((IdentifiedElement) object, super.getText(object));
    }

}
