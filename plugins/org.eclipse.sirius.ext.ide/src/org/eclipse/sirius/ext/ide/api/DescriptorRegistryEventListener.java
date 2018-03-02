/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.ide.api;

import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.ext.ide.internal.Messages;
import org.eclipse.sirius.ext.ide.internal.SiriusExtIdePlugin;

/**
 * Utility class used to retrieved the descriptors of extensions.
 *
 * @author adaussy
 * @author sbegaudeau
 *
 * @param <T>
 *            The type of the Object described
 */
public class DescriptorRegistryEventListener<T> extends AbstractRegistryEventListener {
	/** Id attribute of the extension point. */
	public static final String ID_DESCRIPTOR_ATTR = "id"; //$NON-NLS-1$

	/** Label attribute of extension point. */
	public static final String LABEL_DESCRIPTOR_ATTR = "label"; //$NON-NLS-1$

	/** Description attribute of extension point. */
	public static final String DESCRIPTION_DESCRIPTOR_ATTR = "description"; //$NON-NLS-1$

	/** Implementation class attribute of extension point. */
	public static final String IMPL_CLASS_DESCRIPTOR_ATTR = "class"; //$NON-NLS-1$

	/** Descriptor tag of extension point. */
	public static final String TAG_DESCRIPTOR = "descriptor"; //$NON-NLS-1$

	/**
	 * The item registry.
	 */
	private IItemRegistry<T> itemRegistry;

	/**
	 * The constructor.
	 *
	 * @param namespace
	 *            The namespace of the extension point
	 * @param extensionPointID
	 *            The identifier of the extension point
	 * @param itemRegistry
	 *            The {@link IItemRegistry}
	 */
	public DescriptorRegistryEventListener(String namespace, String extensionPointID, IItemRegistry<T> itemRegistry) {
		super(namespace, extensionPointID);
		this.itemRegistry = itemRegistry;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.api.extensions.AbstractRegistryEventListener#validateConfigurationElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected boolean validateConfigurationElement(IConfigurationElement configurationElement) {
		boolean isValid = false;

		if (TAG_DESCRIPTOR.equals(configurationElement.getName())) {
			if (!this.isValidAttribute(configurationElement, ID_DESCRIPTOR_ATTR)) {
				String message = MessageFormat.format(Messages.DescriptorRegistryEventListener_missingAttribute,
						configurationElement.getNamespaceIdentifier(), ID_DESCRIPTOR_ATTR);
				SiriusExtIdePlugin.getPlugin().log(new Status(IStatus.ERROR, SiriusExtIdePlugin.PLUGIN_ID, message));
			} else if (!this.isValidAttribute(configurationElement, IMPL_CLASS_DESCRIPTOR_ATTR)) {
				String message = MessageFormat.format(Messages.DescriptorRegistryEventListener_missingAttribute,
						configurationElement.getNamespaceIdentifier(), IMPL_CLASS_DESCRIPTOR_ATTR);
				SiriusExtIdePlugin.getPlugin().log(new Status(IStatus.ERROR, SiriusExtIdePlugin.PLUGIN_ID, message));
			} else {
				isValid = true;
			}

			if (!this.isValidAttribute(configurationElement, LABEL_DESCRIPTOR_ATTR)) {
				String message = MessageFormat.format(Messages.DescriptorRegistryEventListener_missingAttribute,
						configurationElement.getNamespaceIdentifier(), LABEL_DESCRIPTOR_ATTR);
				SiriusExtIdePlugin.getPlugin().log(new Status(IStatus.INFO, SiriusExtIdePlugin.PLUGIN_ID, message));
			}
			if (!this.isValidAttribute(configurationElement, DESCRIPTION_DESCRIPTOR_ATTR)) {
				String message = MessageFormat.format(Messages.DescriptorRegistryEventListener_missingAttribute,
						configurationElement.getNamespaceIdentifier(), DESCRIPTION_DESCRIPTOR_ATTR);
				SiriusExtIdePlugin.getPlugin().log(new Status(IStatus.INFO, SiriusExtIdePlugin.PLUGIN_ID, message));
			}
		}

		return isValid;
	}

	/**
	 * Indicates if an attribute of the configuration element is valid.
	 *
	 * @param configurationElement
	 *            The configuration element
	 * @param attributeName
	 *            The attribute name
	 * @return <code>true</code> if the attribute is valid, <code>false</code> otherwise
	 */
	private boolean isValidAttribute(IConfigurationElement configurationElement, String attributeName) {
		return configurationElement.getAttribute(attributeName) != null && !"".equals(configurationElement.getAttribute(attributeName)); //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.api.extensions.AbstractRegistryEventListener#processAddition(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected boolean processAddition(IConfigurationElement configurationElement) {
		String id = configurationElement.getAttribute(ID_DESCRIPTOR_ATTR);
		String label = configurationElement.getAttribute(LABEL_DESCRIPTOR_ATTR);
		String description = configurationElement.getAttribute(DESCRIPTION_DESCRIPTOR_ATTR);

		try {
			T instance = (T) configurationElement.createExecutableExtension(IMPL_CLASS_DESCRIPTOR_ATTR);

			ItemDescriptor<T> descriptor = new ItemDescriptor<T>(id, label, description, instance);
			IItemDescriptor<T> previous = this.itemRegistry.add(descriptor);
			if (previous != null) {
				String message = MessageFormat.format(Messages.DescriptorRegistryEventListener_extensionAlreadyContributed,
						configurationElement.getAttribute(ID_DESCRIPTOR_ATTR));
				SiriusExtIdePlugin.getPlugin().log(new Status(IStatus.ERROR, SiriusExtIdePlugin.PLUGIN_ID, message));
			}
		} catch (CoreException e) {
			String message = MessageFormat.format(Messages.DescriptorRegistryEventListener_cannotInstantiateExtension,
					configurationElement.getAttribute(IMPL_CLASS_DESCRIPTOR_ATTR));
			SiriusExtIdePlugin.getPlugin().log(new Status(IStatus.ERROR, SiriusExtIdePlugin.PLUGIN_ID, message, e));

			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.api.extensions.AbstractRegistryEventListener#processRemoval(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected boolean processRemoval(IConfigurationElement configurationElement) {
		return this.itemRegistry.remove(configurationElement.getAttribute(ID_DESCRIPTOR_ATTR)) != null;
	}

}
