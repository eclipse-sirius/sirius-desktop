/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.common.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLCommonPlugin;

/**
 * Utility class containing the messages.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLCommonMessages {

    static {
        I18N.initializeMessages(SiriusGraphQLCommonMessages.class, SiriusGraphQLCommonPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String SiriusGraphQLPaginationDataFetcher_invalidArguments;

    @TranslatableMessage
    public static String SiriusGraphQLContext_requestTooExpensive;

    // CHECKSTYLE:ON

    private SiriusGraphQLCommonMessages() {
        // Prevents instantiation.
    }
}
