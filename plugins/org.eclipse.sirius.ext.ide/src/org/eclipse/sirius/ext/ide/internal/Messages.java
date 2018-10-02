/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
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
package org.eclipse.sirius.ext.ide.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Utility class used to hold the messages of the bundle.
 *
 * @author sbegaudeau
 */
public final class Messages {

	static {
		I18N.initializeMessages(Messages.class, SiriusExtIdePlugin.INSTANCE);
	}

	// CHECKSTYLE:OFF

	@TranslatableMessage
	public static String DescriptorRegistryEventListener_missingAttribute;

	@TranslatableMessage
	public static String DescriptorRegistryEventListener_cannotInstantiateExtension;

	@TranslatableMessage
	public static String DescriptorRegistryEventListener_extensionAlreadyContributed;

	// CHECKSTYLE:ON

	/**
	 * The constructor.
	 */
	private Messages() {
		// Prevents instantiation
	}
}
