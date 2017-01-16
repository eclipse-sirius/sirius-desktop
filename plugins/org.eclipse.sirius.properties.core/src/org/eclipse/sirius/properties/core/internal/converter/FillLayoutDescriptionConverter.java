/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal.converter;

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.eef.EEFFillLayoutDescription;
import org.eclipse.eef.EEF_FILL_LAYOUT_ORIENTATION;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.core.api.DescriptionCache;
import org.eclipse.sirius.properties.core.api.IDescriptionConverter;
import org.eclipse.sirius.properties.core.internal.Messages;

/**
 * This class is used to convert the fill layout of a container.
 * 
 * @author sbegaudeau
 */
public class FillLayoutDescriptionConverter implements IDescriptionConverter {

    @Override
    public boolean canHandle(EObject description) {
        return description instanceof FillLayoutDescription;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (description instanceof FillLayoutDescription) {
            FillLayoutDescription fillLayoutDescription = (FillLayoutDescription) description;

            EEFFillLayoutDescription eefFillLayoutDescription = EefFactory.eINSTANCE.createEEFFillLayoutDescription();
            if (fillLayoutDescription.getOrientation() == FILL_LAYOUT_ORIENTATION.HORIZONTAL) {
                eefFillLayoutDescription.setOrientation(EEF_FILL_LAYOUT_ORIENTATION.HORIZONTAL);
            } else if (fillLayoutDescription.getOrientation() == FILL_LAYOUT_ORIENTATION.VERTICAL) {
                eefFillLayoutDescription.setOrientation(EEF_FILL_LAYOUT_ORIENTATION.VERTICAL);
            }

            cache.put(description, eefFillLayoutDescription);

            return eefFillLayoutDescription;
        } else {
            throw new IllegalArgumentException(MessageFormat.format(Messages.IDescriptionConverter_InvalidDescriptionType, description.getClass().getName(), FillLayoutDescription.class.getName()));
        }
    }

}
