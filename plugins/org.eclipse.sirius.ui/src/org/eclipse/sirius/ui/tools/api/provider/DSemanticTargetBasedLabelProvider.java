/*******************************************************************************
 * Copyright (c) 2009, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.api.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for lines.
 * 
 * @author dlecan
 * @since 0.9.0
 */
public class DSemanticTargetBasedLabelProvider extends ColumnLabelProvider {

    /**
     * AdapterFactoryLabelProvider.
     */
    protected final AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    /**
     * Constructor.
     * 
     * @param adapterFactoryLabelProvider
     *            Provider which to delegate label and image resolution.
     */
    public DSemanticTargetBasedLabelProvider(final AdapterFactoryLabelProvider adapterFactoryLabelProvider) {
        super();
        this.adapterFactoryLabelProvider = adapterFactoryLabelProvider;
    }

    /**
     * Constructor.
     * 
     * @param adapterFactory
     *            Provider which to delegate label and image resolution.
     */
    public DSemanticTargetBasedLabelProvider(final AdapterFactory adapterFactory) {
        super();
        this.adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(final Object element) {
        final EObject target = getTarget(element);
        if (target == null) {
            return super.getText(element);
        } else {
            return adapterFactoryLabelProvider.getText(target);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(final Object element) {
        Image image = null;
        try {
            final EObject target = getTarget(element);
            if (target == null) {
                image = super.getImage(element);
            } else {
                image = adapterFactoryLabelProvider.getImage(target);
            }
        } catch (IllegalStateException e) {
            if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                // Do nothing, just return null
            } else {
                throw e;
            }
        }
        return image;
    }

    /**
     * Resolve target. Can be <code>null</code>.
     */
    private EObject getTarget(final Object object) {
        EObject result = null;
        if (object instanceof DSemanticDecorator) {
            result = ((DSemanticDecorator) object).getTarget();
        }
        return result;
    }
}
