/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.business.internal.dialect.common.tree;

import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.synchronizer.SemanticPartition;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.MappingBasedPartition;
import org.eclipse.sirius.tree.description.TreeItemMapping;

/**
 * A {@link SemanticPartitionProvider}.
 * 
 * @author cbrun
 */
class SemanticPartitionProvider {

    private GlobalContext ctx;

    /**
     * Default constructor.
     * 
     * @param ctx
     *            a {@link GlobalContext}
     */
    SemanticPartitionProvider(GlobalContext ctx) {
        this.ctx = ctx;
    }

    public SemanticPartition getSemanticPartition(TreeItemMapping nm) {
        return new MappingBasedPartition(ctx, nm.getDomainClass(), Options.newSome(nm.getSemanticCandidatesExpression()), Options.newSome(nm));
    }

}
