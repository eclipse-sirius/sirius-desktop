/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
