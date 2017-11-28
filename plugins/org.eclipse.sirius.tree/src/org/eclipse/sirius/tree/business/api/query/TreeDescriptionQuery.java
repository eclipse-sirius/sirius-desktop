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
package org.eclipse.sirius.tree.business.api.query;

import java.util.Collections;

import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Query for {@link TreeDescription}.
 * 
 * @author cbrun
 */
public class TreeDescriptionQuery {

    private TreeDescription description;

    /**
     * Creates a new TreeDescriptionQuery.
     * 
     * @param description
     *            the TreeDescription on which create this query
     */
    public TreeDescriptionQuery(TreeDescription description) {
        this.description = description;
    }

    /**
     * Returns all the descendant mappings of the TreeDescription.
     * 
     * @return all the descendant mappings of the TreeDescription
     */
    public Iterable<? extends TreeItemMapping> getAllDescendantMappings() {
        if (description != null) {
            return Lists.newArrayList(Iterators.filter(description.eAllContents(), TreeItemMapping.class));
        } else {
            return Collections.emptyList();
        }
    }
}
