/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.convert;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.resource.strategy.AbstractResourceStrategyImpl;

/**
 * This class overrides AbstractResourceStrategyImpl and also ignores genmodel
 * files.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TestResourceStrategyToIgnoreGenModelFile extends AbstractResourceStrategyImpl {

    @Override
    public boolean isPotentialSemanticResource(URI uri) {
        boolean result = super.isPotentialSemanticResource(uri);
        if (result && uri != null) {
            result = !"genmodel".equals(uri.fileExtension());
        }
        return result;
    }

    @Override
    public boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType) {
        return ResourceStrategyType.SEMANTIC_RESOURCE.equals(resourceStrategyType);
    }
}
