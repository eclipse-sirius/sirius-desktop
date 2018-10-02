/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.diagram.sequence.internal.description.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.diagram.sequence.description.provider.SequenceDiagramDescriptionItemProvider;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * Specific implementation of {@link SequenceDiagramDescriptionItemProvider}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class SequenceDiagramDescriptionItemProviderSpec extends SequenceDiagramDescriptionItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            current {@link AdapterFactory}
     */
    public SequenceDiagramDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
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
