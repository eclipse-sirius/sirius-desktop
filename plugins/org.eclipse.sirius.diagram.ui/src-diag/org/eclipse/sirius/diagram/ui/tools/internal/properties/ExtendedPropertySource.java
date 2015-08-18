/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.ExtendedFeatureEditorDialog;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtensionFeatureDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Property source for the extension framework.
 * 
 * @author ymortier
 */
public class ExtendedPropertySource implements IPropertySource {

    /** Empty String array. */
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    /** Empty properties descriptors array. */
    private static final IPropertyDescriptor[] EMPTY = new IPropertyDescriptor[0];

    /**
     * The target.
     */
    private DSemanticDecorator target;

    /**
     * The adapterFactory of the caller
     */
    private AdapterFactory adapterFactory;

    /**
     * Create a new {@link ExtendedPropertySource} for the specified element.
     * 
     * @param target
     *            the element.
     * @param adapterFactory
     *            the adapterFactory of the caller
     */
    public ExtendedPropertySource(DSemanticDecorator target, AdapterFactory adapterFactory) {
        this.target = target;
        this.adapterFactory = adapterFactory;
    }

    /**
     * Returns the model accesor.
     * 
     * @return the model accesor.
     */
    protected ModelAccessor getModelAccessor() {
        return SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(target);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        return target.getTarget();
    }

    /**
     * Returns the properties descriptors. {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (this.target == null || this.target.getTarget() == null || this.getModelAccessor() == null) {
            return EMPTY;
        }
        final List<IPropertyDescriptor> propertyDescriptors = new ArrayList<IPropertyDescriptor>();
        for (Object featureDescription : getModelAccessor().getAllExtensionFeatureDescriptions(this.target.getTarget())) {
            if (featureDescription instanceof ExtensionFeatureDescription) {
                final boolean isReferenceContainment = ((ExtensionFeatureDescription) featureDescription).isReference() && ((ExtensionFeatureDescription) featureDescription).isContainment();
                if (!isReferenceContainment) {
                    propertyDescriptors.add(createPropertyDescriptor((ExtensionFeatureDescription) featureDescription));
                }
            }
        }
        return propertyDescriptors.toArray(new IPropertyDescriptor[propertyDescriptors.size()]);
    }

    private IPropertyDescriptor createPropertyDescriptor(final ExtensionFeatureDescription featureDescription) {
        final IPropertyDescriptor result;
        final IItemPropertyDescriptor emfPropertyDescriptor = featureDescription.getPropertyDescriptor(target.getTarget());
        if (emfPropertyDescriptor == null) {
            result = new ExtendedPropertyDescriptor(featureDescription);
        } else {
            //
            // the feature description provides a property descriptor. We can
            // use it to creates the UI.
            result = new PropertyDescriptor(target.getTarget(), emfPropertyDescriptor);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(final Object id) {
        Object object = null;
        if (id instanceof ExtensionFeatureDescription) {
            try {
                object = getModelAccessor().eGet(target.getTarget(), ExtendedPropertySource.getFeatureName((ExtensionFeatureDescription) id));
            } catch (final FeatureNotFoundException e) {
                // do nothing -> return null
            }
        } else {
            final IItemPropertySource ips = (IItemPropertySource) adapterFactory.adapt(target.getTarget(), IItemPropertySource.class);
            if (ips != null) {
                object = ips.getPropertyDescriptor(target.getTarget(), id).getPropertyValue(target.getTarget());
            }
            if (target.getTarget() instanceof IAdaptable) {
                final IPropertySource propertySource = (IPropertySource) ((IAdaptable) target.getTarget()).getAdapter(IPropertySource.class);
                object = propertySource.getPropertyValue(id);
            }

        }
        return object != null ? object : StringUtil.EMPTY_STRING;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(final Object id) {
        return this.getPropertyValue(id) != null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(final Object id) {
        // TODOYMO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    public void setPropertyValue(final Object id, final Object value) {
        if (id instanceof ExtensionFeatureDescription) {
            final ExtensionFeatureDescription desc = (ExtensionFeatureDescription) id;
            if (desc.isAttribute()) {
                try {
                    this.getModelAccessor().eSet(target.getTarget(), ExtendedPropertySource.getFeatureName((ExtensionFeatureDescription) id), value);
                } catch (final FeatureNotFoundException e) {
                    SiriusPlugin.getDefault().error("Error while setting the property value", e);
                }
            } else if (desc.isReference()) {
                this.getModelAccessor().eClear(target.getTarget(), ExtendedPropertySource.getFeatureName((ExtensionFeatureDescription) id));
                if (value instanceof Collection) {
                    final Iterator<?> iterValues = ((Collection<?>) value).iterator();
                    while (iterValues.hasNext()) {
                        try {
                            this.getModelAccessor().eAdd(target.getTarget(), ExtendedPropertySource.getFeatureName((ExtensionFeatureDescription) id), iterValues.next());
                        } catch (final FeatureNotFoundException e) {
                            // do nothing
                        }
                    }
                }
            }
        } else {
            final IItemPropertySource ips = (IItemPropertySource) adapterFactory.adapt(target.getTarget(), IItemPropertySource.class);
            if (ips != null) {
                ips.getPropertyDescriptor(target.getTarget(), id).setPropertyValue(target.getTarget(), value);
            }
        }
        // else throw new AssertionError?();
    }

    /**
     * The property source descriptor.
     * 
     * @author ymortier
     */
    private class ExtendedPropertyDescriptor extends BaseLabelProvider implements IPropertyDescriptor, ILabelProvider {

        /** The description of this extension. */
        private ExtensionFeatureDescription extensionDescription;

        /**
         * Creates a new <code>ExtendedPropertyDescriptor</code>.
         * 
         * @param extensionDescription
         *            the corresponding extension.
         */
        public ExtendedPropertyDescriptor(final ExtensionFeatureDescription extensionDescription) {
            this.extensionDescription = extensionDescription;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.ui.views.properties.IPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
         */
        public CellEditor createPropertyEditor(final Composite parent) {
            final EObject editableValue = (EObject) getEditableValue();
            CellEditor editor = null;

            if (!getPermissionAuthority(editableValue).canEditInstance(editableValue)) {
                // do nothing -> leave editor to null
            } else if (extensionDescription.isAttribute()) {
                editor = new TextCellEditor(parent);
            } else if (extensionDescription.isReference()) {
                List<EObject> referenceValues = Collections.emptyList();
                try {
                    referenceValues = (List<EObject>) getModelAccessor().eGet(editableValue, ExtendedPropertySource.getFeatureName(extensionDescription));
                } catch (final FeatureNotFoundException e) {
                    SiriusPlugin.getDefault().error("Error while retrieving reference values", e);
                }
                final List<EObject> ref = referenceValues == null ? Collections.EMPTY_LIST : referenceValues;

                editor = new ExtendedDialogCellEditor(parent, getLabelProvider()) {

                    @Override
                    protected Object openDialogBox(final Control cellEditorWindow) {
                        final ExtendedFeatureEditorDialog dialog = new ExtendedFeatureEditorDialog(parent.getShell(), getChoices(), ref, extensionDescription);
                        dialog.open();
                        return dialog.getResult();
                    }
                };
            } else {
                throw new UnsupportedOperationException("unknown extension");
            }
            return editor;
        }

        private IPermissionAuthority getPermissionAuthority(final EObject instance) {
            final ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(instance);
            return accessor.getPermissionAuthority();
        }

        /**
         * Returns the values that can be added into the reference description.
         * 
         * @return the values that can be added into the reference description.
         */
        private List<EObject> getChoices() {
            final List<EObject> result = new LinkedList<EObject>();
            if (this.extensionDescription.isReference()) {
                if (!extensionDescription.isContainment()) {
                    final EObject root = EcoreUtil.getRootContainer(target.getTarget());
                    result.addAll(getModelAccessor().eAllContents(root, extensionDescription.getTypeName()));
                }
            }
            return result;
        }

        /**
         * @see IPropertyDescriptor#getCategory()
         */
        public String getCategory() {
            return "Extended";
        }

        /**
         * @see IPropertyDescriptor#getDescription()
         */
        public String getDescription() {
            return "Property source for the extension framework";
        }

        /**
         * @see IPropertyDescriptor#getDisplayName()
         */
        public String getDisplayName() {
            return ExtendedPropertySource.getFeatureName(this.extensionDescription);
        }

        /**
         * @see IPropertyDescriptor#getFilterFlags()
         */
        public String[] getFilterFlags() {
            return EMPTY_STRING_ARRAY;
        }

        /**
         * @see IPropertyDescriptor#getHelpContextIds()
         */
        public Object getHelpContextIds() {
            return null;
        }

        /**
         * @see IPropertyDescriptor#getId()
         */
        public Object getId() {
            return this.extensionDescription;
        }

        /**
         * @see IPropertyDescriptor#getLabelProvider()
         */
        public ILabelProvider getLabelProvider() {
            return this;
        }

        /**
         * @see IPropertyDescriptor#isCompatibleWith(IPropertyDescriptor)
         */
        public boolean isCompatibleWith(final IPropertyDescriptor anotherProperty) {
            if (anotherProperty instanceof ExtendedPropertyDescriptor) {
                return this.extensionDescription.equals(((ExtendedPropertyDescriptor) anotherProperty).extensionDescription);
            }
            return false;
        }

        /**
         * @see ILabelProvider#getImage(Object)
         */
        public Image getImage(final Object element) {
            // No specific image by default
            return null;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
         */
        public String getText(final Object element) {

            String text = null;

            if (element instanceof EObject) {
                try {
                    text = String.valueOf(getModelAccessor().eGet((EObject) element, ExtendedPropertySource.getFeatureName(extensionDescription)));
                } catch (final FeatureNotFoundException e) {
                    SiriusPlugin.getDefault().error("Error while getting the property value", e);
                    text = e.getMessage();
                }
            } else if (element instanceof Collection) {
                final StringBuffer sb = new StringBuffer("["); //$NON-NLS-1$
                boolean first = true;
                final Iterator<?> iterCollection = ((Collection<?>) element).iterator();
                while (iterCollection.hasNext()) {
                    final Object next = iterCollection.next();
                    if (first) {
                        first = false;
                    } else {
                        sb.append(", "); //$NON-NLS-1$
                    }
                    if (next instanceof EObject) {
                        sb.append(EMFCoreUtil.getQualifiedName((EObject) next, true));
                    } else if (next instanceof Collection) {
                        sb.append(this.getText(next));
                    } else {
                        sb.append(String.valueOf(next));
                    }
                }
                sb.append("]"); //$NON-NLS-1$
                text = sb.toString();
            } else if (element == null) {
                text = ""; //$NON-NLS-1$
            } else if (element instanceof EEnumLiteral) {
                text = ((EEnumLiteral) element).getLiteral();
            } else {
                return String.valueOf(element);
            }
            return text;
        }
    }

    /**
     * 
     * @param extensionDescription
     * @return
     */
    private static String getFeatureName(final ExtensionFeatureDescription extensionDescription) {
        return extensionDescription.getName();

    }
}
