/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.tools.internal.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.DefaultRulesProvider;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.ui.IEditorPart;

/**
 * Menu builder used to create the properties view description.
 * 
 * @author sbegaudeau
 */
public class PropertiesMenuBuilder extends AbstractTypeRestrictingMenuBuilder {

    /**
     * The constructor.
     */
    public PropertiesMenuBuilder() {
        this.addValidType(PropertiesPackage.Literals.VIEW_EXTENSION_DESCRIPTION);
    }

    @Override
    public String getLabel() {
        return "New Properties";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.EXTENSION;
    }

    @Override
    public void update(Collection<?> newChildDescriptors, ISelection selection, IEditorPart editor) {
        this.depopulate();
        this.advancedChildActions = new ArrayList<>();

        Optional<CommandParameter> optionalNewPropertyViewCommandParameter = newChildDescriptors.stream().filter(CommandParameter.class::isInstance).map(CommandParameter.class::cast)
                .filter(parameter -> DescriptionPackage.Literals.GROUP__EXTENSIONS.equals(parameter.getEStructuralFeature())).findFirst();

        if (selection instanceof IStructuredSelection && optionalNewPropertyViewCommandParameter.isPresent()) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;

            // Add the parameter to our descriptor to prevent its use somewhere
            // else which would create another "New" menu with this entry
            CommandParameter newPropertyViewCommandParameter = optionalNewPropertyViewCommandParameter.get();
            descriptors.add(newPropertyViewCommandParameter);

            Optional<Group> optionalGroup = Arrays.stream(structuredSelection.toArray()).filter(Group.class::isInstance).map(Group.class::cast).findFirst();
            optionalGroup.ifPresent(group -> {
                newPropertyViewCommandParameter.setOwner(group);

                CommandParameter importPropertyViewCommandParameter = this.getImportPropertyViewCommandParameter(group);
                CommandParameter extendsPropertyViewCommandParameter = this.getExtendsPropertyViewCommandParameter(group);

                Collection<CreateChildAction> actions = new ArrayList<>();
                actions.add(new PropertiesCreateChildAction(editor, structuredSelection, newPropertyViewCommandParameter, "New Properties View", 1000));
                actions.add(new PropertiesCreateChildAction(editor, structuredSelection, importPropertyViewCommandParameter, "Import Default Properties View", 2000));
                actions.add(new PropertiesCreateChildAction(editor, structuredSelection, extendsPropertyViewCommandParameter, "Extend Default Properties View", 3000));

                this.advancedChildActions = actions;
            });
        }
    }

    /**
     * Creates a command parameter used to import the default rules.
     * 
     * @param group
     *            The group in which we will create the properties view
     * @return The command parameter created
     */
    private CommandParameter getImportPropertyViewCommandParameter(Group group) {
        ViewExtensionDescription viewExtensionDescription = DefaultRulesProvider.INSTANCE.getDefaultRules();
        return new CommandParameter(group, DescriptionPackage.Literals.GROUP__EXTENSIONS, EcoreUtil.copy(viewExtensionDescription));
    }

    /**
     * Creates a command parameter used to extends the default rules.
     * 
     * @param group
     *            The group in which we will create the properties view
     * @return The command parameter created
     */
    private CommandParameter getExtendsPropertyViewCommandParameter(Group group) {
        ViewExtensionDescription viewExtensionDescription = PropertiesFactory.eINSTANCE.createViewExtensionDescription();

        PageDescription pageDescription = PropertiesFactory.eINSTANCE.createPageDescription();
        ViewExtensionDescription defaultRules = DefaultRulesProvider.INSTANCE.getDefaultRules(group.eResource().getResourceSet());

        Optional<PageDescription> pageDescriptionToExtend = defaultRules.getCategories().stream().findFirst().flatMap(category -> category.getPages().stream().findFirst());
        pageDescriptionToExtend.ifPresent(pageDescription::setExtends);

        Category category = PropertiesFactory.eINSTANCE.createCategory();
        category.getPages().add(pageDescription);
        viewExtensionDescription.getCategories().add(category);
        return new CommandParameter(group, DescriptionPackage.Literals.GROUP__EXTENSIONS, viewExtensionDescription);
    }

    @Override
    protected int getPriority(IAction action) {
        if (action instanceof PropertiesCreateChildAction) {
            return ((PropertiesCreateChildAction) action).getPriority();
        }
        return super.getPriority(action);
    }

}
