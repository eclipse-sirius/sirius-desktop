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
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.editor.properties.api.DefaultWidgetDescription;
import org.eclipse.sirius.editor.properties.api.IDefaultWidgetDescriptionFactory;
import org.eclipse.sirius.editor.properties.internal.Messages;
import org.eclipse.sirius.editor.properties.internal.SiriusEditorPropertiesPlugin;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Strings;

/**
 * Generates the appropriate widgets menu entries for the domain class of a
 * group, based on its structural features.
 * 
 * @author sbegaudeau
 * @author arichard
 */
public class CreateWidgetFromDomainClassMenuBuilder extends AbstractMenuBuilder {

    /**
     * The pattern used to match the separator used by both Sirius and AQL.
     */
    private static final Pattern SEPARATOR = Pattern.compile("(::?|\\.)"); //$NON-NLS-1$

    @Override
    public String getLabel() {
        return Messages.CreateWidgetFromDomainClassMenuBuilder_label;
    }

    @Override
    public int getPriority() {
        return PropertiesMenuBuilderConstants.WIDGETS_FROM_DOMAIN_CLASS;
    }

    @Override
    protected boolean isMine(final CommandParameter object) {
        // not relevant here.
        return false;
    }

    @Override
    public void update(Collection<?> newChildDescriptors, ISelection selection, IEditorPart editor) {
        this.depopulate();
        this.advancedChildActions = this.generateDomainClassWidgetsActions(selection, editor);
    }

    /**
     * Generate the {@link CreateWidgetForFeatureAction} for the selected
     * {@link GroupDescription}.
     * 
     * @param selection
     *            the current selection.
     * @param editor
     *            the current editor.
     * @return a list of the {@link CreateWidgetForFeatureAction} (can be
     *         empty).
     */
    private Collection<CreateChildAction> generateDomainClassWidgetsActions(ISelection selection, IEditorPart editor) {
        final Collection<CreateChildAction> actions = new ArrayList<>();

        GroupDescription groupDescription = this.getGroupDescription(selection);
        if (groupDescription != null) {
            ViewExtensionDescription viewExtensionDescription = this.getViewExtensionDescription(groupDescription);
            if (viewExtensionDescription != null) {
                EList<EPackage> metamodels = viewExtensionDescription.getMetamodels();
                String domainClassQualifiedName = groupDescription.getDomainClass();
                EClass domainClass = this.getDomainClass(metamodels, domainClassQualifiedName);
                if (domainClass != null) {
                    actions.add(new CreateWidgetForAllFeaturesAction(editor, selection, new CreateWidgetForAllFeaturesDescriptor(groupDescription, domainClass)));

                    actions.addAll(this.addActionsForStructuralFeatures(selection, editor, groupDescription, domainClass));
                }
            }
        }
        return actions;
    }

    /**
     * Adds all the actions used to create specific widgets for each structural
     * features.
     * 
     * @param selection
     *            The current selection
     * @param editor
     *            The editor
     * @param actions
     *            The actions
     * @param groupDescription
     *            The description of the group
     * @param domainClass
     *            The domain class
     * @return The list of actions to create
     */
    private List<CreateWidgetForFeatureAction> addActionsForStructuralFeatures(ISelection selection, IEditorPart editor, GroupDescription groupDescription, EClass domainClass) {
        List<CreateWidgetForFeatureAction> actions = new ArrayList<>();

        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

        EList<EStructuralFeature> structuralFeatures = domainClass.getEAllStructuralFeatures();
        for (EStructuralFeature eStructuralFeature : structuralFeatures) {
            List<IDefaultWidgetDescriptionFactory> factories = SiriusEditorPropertiesPlugin.getPlugin().getDefaultWidgetDescriptionFactory(domainClass, eStructuralFeature);
            for (IDefaultWidgetDescriptionFactory factory : factories) {
                DefaultWidgetDescription defaultWidgetDescription = factory.create(domainClass, eStructuralFeature);
                ImageDescriptor imageDescriptor = this.getCreateWidgetActionImageDescriptor(adapterFactory, defaultWidgetDescription.getWidgetDescription());

                CreateWidgetForFeatureDescriptor descriptor = new CreateWidgetForFeatureDescriptor(groupDescription, domainClass, imageDescriptor, defaultWidgetDescription);
                CreateWidgetForFeatureAction action = new CreateWidgetForFeatureAction(editor, selection, descriptor);
                actions.add(action);
            }
        }

        adapterFactory.dispose();
        return actions;
    }

    /**
     * Returns the image descriptor of the create widget action.
     * 
     * @param adapterFactory
     *            The adapter factory
     * @param widgetDescription
     *            The widget description
     * @return The image descriptor of the create widget action
     */
    private ImageDescriptor getCreateWidgetActionImageDescriptor(AdapterFactory adapterFactory, WidgetDescription widgetDescription) {
        ImageDescriptor imageDescriptor = null;
        Adapter adapter = adapterFactory.adapt(widgetDescription, IItemLabelProvider.class);
        if (adapter instanceof IItemLabelProvider) {
            imageDescriptor = ExtendedImageRegistry.INSTANCE.getImageDescriptor(((IItemLabelProvider) adapter).getImage(widgetDescription));
        }
        return imageDescriptor;
    }

    /**
     * Returns the view extension description containing the given group.
     * 
     * @param groupDescription
     *            The group description
     * @return The view extension description containing the given group or
     *         <code>null</code> otherwise
     */
    private ViewExtensionDescription getViewExtensionDescription(GroupDescription groupDescription) {
        EObject eContainer = groupDescription.eContainer();
        while (eContainer != null && !(eContainer instanceof ViewExtensionDescription)) {
            eContainer = eContainer.eContainer();
        }
        if (eContainer instanceof ViewExtensionDescription) {
            return (ViewExtensionDescription) eContainer;
        }
        return null;
    }

    /**
     * Returns the first group description selected.
     * 
     * @param selection
     *            the current selection.
     * @return the first group description selected, <code>null</code>
     *         otherwise.
     */
    private GroupDescription getGroupDescription(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object[] objectSelected = structuredSelection.toArray();
            for (Object object : objectSelected) {
                if (object instanceof GroupDescription) {
                    return (GroupDescription) object;
                }
            }
        }
        return null;
    }

    /**
     * Get the {@link EClass} from its qualified name.
     * 
     * @param metamodels
     *            the list of {@link EPackage}.
     * @param domainClassQualifiedName
     *            the qualified name of the domain class to search.
     * @return the {@link EClass} from its qualified name, <code>null</code> if
     *         not found.
     */
    private EClass getDomainClass(EList<EPackage> metamodels, String domainClassQualifiedName) {
        EClass domainClass = null;
        String packageName = null;
        String className = null;

        if (!Strings.isNullOrEmpty(domainClassQualifiedName)) {
            Matcher m = SEPARATOR.matcher(domainClassQualifiedName);
            if (m.find()) {
                packageName = domainClassQualifiedName.substring(0, m.start());
                className = domainClassQualifiedName.substring(m.end());
            } else {
                className = domainClassQualifiedName;
            }
        }

        for (EPackage ePackage : metamodels) {
            if (packageName != null && ePackage.getName().equals(packageName)) {
                EClassifier classifier = ePackage.getEClassifier(className);
                if (classifier instanceof EClass) {
                    domainClass = (EClass) classifier;
                    break;
                }
            }
            EClass clazz = getDomainClass(ePackage.getESubpackages(), domainClassQualifiedName);
            if (clazz != null) {
                domainClass = clazz;
                break;
            }
        }
        return domainClass;
    }
}
