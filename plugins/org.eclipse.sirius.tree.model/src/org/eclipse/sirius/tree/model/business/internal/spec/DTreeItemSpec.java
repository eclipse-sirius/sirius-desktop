/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.model.business.internal.spec;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.tree.description.StyleUpdater;
import org.eclipse.sirius.tree.description.TreeItemUpdater;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.impl.DTreeItemImpl;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Implementation of DTreeItem.
 * 
 * @author nlepine
 */
public class DTreeItemSpec extends DTreeItemImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#getMapping()
     */
    @Override
    public RepresentationElementMapping getMapping() {
        return getActualMapping();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#basicGetTreeElementMapping()
     */
    @Override
    public TreeMapping basicGetTreeElementMapping() {
        return (TreeMapping) getMapping();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#getTreeElementMapping()
     */
    @Override
    public TreeMapping getTreeElementMapping() {
        TreeMapping treeElementMapping = basicGetTreeElementMapping();
        return treeElementMapping != null && treeElementMapping.eIsProxy() ? (TreeMapping) eResolveProxy((InternalEObject) treeElementMapping) : treeElementMapping;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#basicGetStyleUpdater()
     */
    @Override
    public StyleUpdater basicGetStyleUpdater() {
        return getActualMapping();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.impl.DTreeItemImpl#getStyleUpdater()
     */
    @Override
    public StyleUpdater getStyleUpdater() {
        StyleUpdater styleUpdater = basicGetStyleUpdater();
        return styleUpdater != null && styleUpdater.eIsProxy() ? (StyleUpdater) eResolveProxy((InternalEObject) styleUpdater) : styleUpdater;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public TreeItemUpdater getUpdater() {
        TreeItemUpdater updater = basicGetUpdater();
        return updater != null && updater.eIsProxy() ? (TreeItemUpdater) eResolveProxy((InternalEObject) updater) : updater;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public TreeItemUpdater basicGetUpdater() {
        return getActualMapping();
    }
}
