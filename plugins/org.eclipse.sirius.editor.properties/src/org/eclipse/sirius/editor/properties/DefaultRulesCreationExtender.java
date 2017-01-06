/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.sirius.editor.tools.internal.menu.CustomChildTextAdapter;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.DefaultRulesProvider;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * Contributes the default rules model to the creation context menu in the VSM
 * editor.
 * 
 * @author pcdavid
 */
public class DefaultRulesCreationExtender implements IChildCreationExtender {

    @Override
    public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
        ArrayList<Object> result = new ArrayList<Object>();
        if (object instanceof Group) {
            ViewExtensionDescription rules = EcoreUtil.copy(DefaultRulesProvider.INSTANCE.getDefaultRules());
            rules.eAdapters().add(new CustomChildTextAdapter(Messages.ImportingDefaultPropertiesViewDescriptionCommand_text));
            result.add(new CommandParameter(null, DescriptionPackage.Literals.GROUP__EXTENSIONS, rules));
        }
        return result;
    }

    @Override
    public ResourceLocator getResourceLocator() {
        return SiriusEditorPropertiesPlugin.INSTANCE;
    }

}
