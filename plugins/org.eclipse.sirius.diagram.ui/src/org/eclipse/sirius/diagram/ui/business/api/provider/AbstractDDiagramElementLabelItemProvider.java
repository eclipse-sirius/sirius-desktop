/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.api.provider;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A custom ItemProvider to add the label of DDiagramElement. This ItemProvider
 * "simulates" a new child for DDiagramElement.
 *
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 *
 */
public abstract class AbstractDDiagramElementLabelItemProvider extends ItemProviderAdapter
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

    /**
     * Label to compute when name of the DDiagramElement associated to this
     * label is empty.
     */
    protected static final String EMPTY_DDIAGRAMELEMENT_LABEL_LABEL = Messages.AbstractDDiagramElementLabelItemProvider_emptyLabel;

    /**
     * Default constructor.
     *
     * @param adapterFactory
     *            The factory is used as a key so that we always know which
     *            factory created this adapter.
     * @param parentDDiagramElement
     *            The parent of the label
     */
    public AbstractDDiagramElementLabelItemProvider(AdapterFactory adapterFactory, DDiagramElement parentDDiagramElement) {
        super(adapterFactory);
        parentDDiagramElement.eAdapters().add(this);
    }

    /**
     * <p>
     * Indicates if the given candidateDDiagramElement should have a
     * DDiagramElementLabelItem has children.
     * </p>
     * <p>
     * The given candidateDDiagramElement should have a DDiagramElementLabelItem
     * has children if all following predicates are verified:
     * <ul>
     * <li>It has a non-null and non-empty name</li>
     * </ul>
     * </p>
     *
     * @param dDiagramElement
     *            the DDiagramElement to determine if it can have a
     *            DDiagramElementLabelItem has children
     * @return true if the given candidate DDiagramElement should have a
     *         DDiagramElementLabelItem has children
     */
    // protected static abstract boolean hasRelevantLabelItem(DDiagramElement
    // dDiagramElement);

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
     */
    @Override
    public Object getParent(Object object) {
        return target;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object) {
        String label = ((DDiagramElement) target).getName();
        if (label == null || label.length() == 0) {
            label = EMPTY_DDIAGRAMELEMENT_LABEL_LABEL;
        } else {
            label = MessageFormat.format(Messages.AbstractDDiagramElementLabelItemProvider_label, label);
        }
        return label;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
     */
    @Override
    public Collection<?> getChildren(Object object) {
        return new ArrayList<Object>();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("label_obj")); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getResourceLocator()
     */
    @Override
    protected ResourceLocator getResourceLocator() {
        return DiagramUIPlugin.INSTANCE;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (getTarget() != null && obj instanceof AbstractDDiagramElementLabelItemProvider) {
            return getTarget().equals(((AbstractDDiagramElementLabelItemProvider) obj).getTarget());
        } else {
            return super.equals(obj);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (getTarget() != null) {
            return getTarget().hashCode();
        } else {
            return super.hashCode();
        }
    }

    /**
     * Return the target of kind DiagramElement or a null Option if any target
     * or of another kind.
     *
     * @return An option of DDiagramElement.
     */
    public Option<DDiagramElement> getDiagramElementTarget() {
        if (getTarget() instanceof DDiagramElement) {
            return Options.newSome((DDiagramElement) getTarget());
        }
        return Options.newNone();
    }
}
