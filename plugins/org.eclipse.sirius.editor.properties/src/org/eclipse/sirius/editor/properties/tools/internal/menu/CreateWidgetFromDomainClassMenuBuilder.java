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
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.editor.properties.api.DefaultWidgetDescription;
import org.eclipse.sirius.editor.properties.api.IDefaultWidgetDescriptionFactory;
import org.eclipse.sirius.editor.properties.internal.Messages;
import org.eclipse.sirius.editor.properties.internal.SiriusEditorPropertiesPlugin;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.core.internal.EditSupportSpec;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.IEditorPart;

/**
 * Generates the appropriate widgets menu entries for the domain class of a group, based on its structural features.
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
     * Generate the {@link CreateWidgetForFeatureAction} for the selected {@link GroupDescription}.
     * 
     * @param selection
     *            the current selection.
     * @param editor
     *            the current editor.
     * @return a list of the {@link CreateWidgetForFeatureAction} (can be empty).
     */
    private Collection<CreateChildAction> generateDomainClassWidgetsActions(ISelection selection, IEditorPart editor) {
        final Collection<CreateChildAction> actions = new ArrayList<>();

        Optional<EObject> optionalControlsContainerDescription = this.getControlsContainerDescription(selection);
        optionalControlsContainerDescription.ifPresent(controlsContainerDescription -> {
            List<EPackage> ePackages = this.getEPackages(controlsContainerDescription);
            String domainClassQualifiedName = this.getDomainClassQualifiedName(controlsContainerDescription);

            Optional<EClass> optionalDomainClass = this.getDomainClass(ePackages, domainClassQualifiedName);
            optionalDomainClass.ifPresent(domainClass -> {
                actions.add(new CreateWidgetForAllFeaturesAction(editor, selection, new CreateWidgetForAllFeaturesDescriptor(controlsContainerDescription, domainClass)));
                actions.addAll(this.addActionsForStructuralFeatures(selection, editor, controlsContainerDescription, domainClass));
            });
        });

        return actions;
    }

    /**
     * Adds all the actions used to create specific widgets for each structural features.
     * 
     * @param selection
     *            The current selection
     * @param editor
     *            The editor
     * @param actions
     *            The actions
     * @param controlsContainerDescription
     *            The description of the container of the controls
     * @param domainClass
     *            The domain class
     * @return The list of actions to create
     */
    private List<CreateWidgetForFeatureAction> addActionsForStructuralFeatures(ISelection selection, IEditorPart editor, EObject controlsContainerDescription, EClass domainClass) {
        List<CreateWidgetForFeatureAction> actions = new ArrayList<>();

        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

        domainClass.getEAllStructuralFeatures().stream().filter(EditSupportSpec::shouldAppearInPropertySheet).forEach(eStructuralFeature -> {
            List<IDefaultWidgetDescriptionFactory> factories = SiriusEditorPropertiesPlugin.getPlugin().getDefaultWidgetDescriptionFactory(domainClass, eStructuralFeature);
            for (IDefaultWidgetDescriptionFactory factory : factories) {
                DefaultWidgetDescription defaultWidgetDescription = factory.create(domainClass, eStructuralFeature);
                ImageDescriptor imageDescriptor = this.getCreateWidgetActionImageDescriptor(adapterFactory, defaultWidgetDescription.getWidgetDescription());

                CreateWidgetForFeatureDescriptor descriptor = new CreateWidgetForFeatureDescriptor(controlsContainerDescription, domainClass, imageDescriptor, defaultWidgetDescription);
                CreateWidgetForFeatureAction action = new CreateWidgetForFeatureAction(editor, selection, descriptor);
                actions.add(action);
            }
        });

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
     * Returns the first group description or container description selected.
     * 
     * @param selection
     *            the current selection.
     * @return An optional containing the first group description or container description selected, an empty optional
     *         otherwise.
     */
    private Optional<EObject> getControlsContainerDescription(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            return Arrays.stream(structuredSelection.toArray()).filter(object -> object instanceof GroupDescription || object instanceof ContainerDescription).map(EObject.class::cast).findFirst();
        }
        return Optional.empty();
    }

    /**
     * Returns the list of all the accessible EPackages of the given description. A description will have access to the
     * metamodels of its parent {@link ViewExtensionDescription} or {@link RepresentationDescription}.
     * 
     * @param controlsContainerDescription
     *            The description of the container of the controls
     * @return The list of all the accessible EPackages of the given description
     */
    private List<EPackage> getEPackages(EObject controlsContainerDescription) {
        List<EPackage> ePackages = new ArrayList<>();

        EObject eContainer = controlsContainerDescription.eContainer();
        while (eContainer != null && !(eContainer instanceof ViewExtensionDescription || eContainer instanceof RepresentationDescription)) {
            eContainer = eContainer.eContainer();
        }

        if (eContainer instanceof ViewExtensionDescription) {
            ePackages.addAll(((ViewExtensionDescription) eContainer).getMetamodels());
        } else if (eContainer instanceof RepresentationDescription) {
            ePackages.addAll(((RepresentationDescription) eContainer).getMetamodel());
        }

        return ePackages;
    }

    /**
     * Returns the qualified name of the domain class for the given controls container description. If the description
     * is a group, it will return the domain class of said group, otherwise if the description is a container it will
     * return the domain class of its containing group.
     * 
     * @param controlsContainerDescription
     *            The controls container description
     * @return The qualified name of the domain class
     */
    private String getDomainClassQualifiedName(EObject controlsContainerDescription) {
        String domainClassQualifiedName = "";

        if (controlsContainerDescription instanceof GroupDescription) {
            domainClassQualifiedName = ((GroupDescription) controlsContainerDescription).getDomainClass();
        } else if (controlsContainerDescription instanceof ContainerDescription) {
            EObject eContainer = controlsContainerDescription.eContainer();
            while (eContainer != null && !(eContainer instanceof GroupDescription)) {
                eContainer = eContainer.eContainer();
            }

            if (eContainer instanceof GroupDescription) {
                domainClassQualifiedName = ((GroupDescription) eContainer).getDomainClass();
            }
        }

        return domainClassQualifiedName;
    }

    /**
     * Get the {@link EClass} from its qualified name.
     * 
     * @param metamodels
     *            the list of {@link EPackage}.
     * @param domainClassQualifiedName
     *            the qualified name of the domain class to search.
     * @return An optional containing the {@link EClass} found from its qualified name, an empty optional if not found.
     */
    private Optional<EClass> getDomainClass(List<EPackage> metamodels, String domainClassQualifiedName) {
        Optional<EClass> optionalDomainClass = Optional.empty();

        String packageName = null;
        String className = null;
        if (!StringUtil.isEmpty(domainClassQualifiedName)) {
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
                    optionalDomainClass = Optional.of((EClass) classifier);
                    break;
                }
            }
            Optional<EClass> optionalClazz = getDomainClass(ePackage.getESubpackages(), domainClassQualifiedName);
            if (optionalClazz.isPresent()) {
                optionalDomainClass = optionalClazz;
                break;
            }
        }
        return optionalDomainClass;
    }
}
