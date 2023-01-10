/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
package org.eclipse.sirius.ui.properties.internal.tabprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.EEFView;
import org.eclipse.eef.core.api.EEFViewFactory;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.ide.ui.properties.api.EEFTabDescriptor;
import org.eclipse.eef.properties.ui.api.IEEFTabDescriptor;
import org.eclipse.eef.properties.ui.api.IEEFTabDescriptorProvider;
import org.eclipse.eef.properties.ui.api.IEEFTabbedPropertySheetPageContributor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.common.interpreter.api.VariableManagerFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.OverridesProvider;
import org.eclipse.sirius.properties.core.api.SiriusDomainClassTester;
import org.eclipse.sirius.properties.core.api.SiriusInputDescriptor;
import org.eclipse.sirius.properties.core.api.SiriusInterpreter;
import org.eclipse.sirius.properties.core.api.ViewDescriptionConverter;
import org.eclipse.sirius.properties.core.api.ViewDescriptionPreprocessor;
import org.eclipse.sirius.properties.defaultrules.api.DefaultRulesProvider;
import org.eclipse.sirius.ui.properties.internal.SiriusUIPropertiesPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.ui.IWorkbenchPart;

/**
 * The {@link IEEFTabDescriptorProvider} for Eclipse Sirius.
 * 
 * @author sbegaudeau
 * @author pcdavid
 */
public class SiriusTabDescriptorProvider implements IEEFTabDescriptorProvider {

    @Override
    public Collection<IEEFTabDescriptor> get(IWorkbenchPart part, ISelection selection, IEEFTabbedPropertySheetPageContributor contributor) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            List<Object> objects = structuredSelection.toList();
            if (objects.size() > 0) {
                SiriusInputDescriptor sid = new SiriusInputDescriptor(objects.get(0), objects);
                if (sid.getSemanticElement() != null) {
                    // Let's find out the description of the view
                    return this.getTabDescriptors(sid);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Returns the {@link IEEFTabDescriptor} for the given semantic element.
     * 
     * @param semanticElement
     *            The semantic element
     * @return A collection of {@link IEEFTabDescriptor}
     */
    private Collection<IEEFTabDescriptor> getTabDescriptors(SiriusInputDescriptor input) {
        Option<Session> session = input.getFullContext().getSession();
        if (session.some()) {
            List<PageDescription> effectivePageDescriptions = computeEffectiveDescription(input, session.get());
            return getTabDescriptors(session.get(), input, effectivePageDescriptions);
        } else {
            return Collections.emptyList();
        }
    }

    private Collection<IEEFTabDescriptor> getTabDescriptors(Session session, SiriusInputDescriptor input, List<PageDescription> effectivePageDescriptions) {
        EEFViewDescription viewDescription = new ViewDescriptionConverter(effectivePageDescriptions).convert(input);
        EEFView eefView = createEEFView(session, input, viewDescription);

        return eefView.getPages().stream().map(EEFTabDescriptor::new).collect(Collectors.toList());
    }

    private EEFView createEEFView(final Session session, SiriusInputDescriptor input, EEFViewDescription viewDescription) {
        IVariableManager variableManager = new VariableManagerFactory().createVariableManager();
        variableManager.put(EEFExpressionUtils.SELF, input.getSemanticElement());
        variableManager.put(EEFExpressionUtils.INPUT, input);
        EditingContextAdapter editingContextAdapter = SiriusUIPropertiesPlugin.getPlugin().getEditingContextAdapter(session);
        EEFView eefView = new EEFViewFactory().createEEFView(viewDescription, variableManager, new SiriusInterpreter(session), editingContextAdapter, new SiriusDomainClassTester(session), input);
        return eefView;
    }

    /**
     * Computes the equivalent of:
     * 
     * <pre>
     * session.selectedViewpoints.eContainer(description::Group).eContents(properties::ViewExtensionDescription).pages
     * </pre>
     */
    private List<PageDescription> computeEffectiveDescription(SiriusInputDescriptor input, Session session) {
        Objects.requireNonNull(session);

        // @formatter:off
        Set<ViewExtensionDescription> viewExtensionDescriptions = session.getSelectedViewpoints(true).stream()
                .map(viewpoint -> new EObjectQuery(viewpoint).getFirstAncestorOfType(DescriptionPackage.Literals.GROUP))
                .filter(Option::some)
                .map(Option::get)
                .filter(Group.class::isInstance)
                .map(Group.class::cast)
                .flatMap(group -> group.getExtensions().stream())
                .filter(ViewExtensionDescription.class::isInstance)
                .map(ViewExtensionDescription.class::cast)
                .collect(Collectors.toSet());
        // @formatter:on

        List<PageDescription> effectivePages = new ArrayList<>();
        viewExtensionDescriptions.forEach(viewExtensionDescription -> {
            IVariableManager variableManager = new VariableManagerFactory().createVariableManager();
            variableManager.put(EEFExpressionUtils.SELF, viewExtensionDescription);

            new ViewDescriptionPreprocessor(viewExtensionDescription).convert(new SiriusInterpreter(session), variableManager, new OverridesProvider(session))
                    .ifPresent(processedViewExtensionDescription -> {
                processedViewExtensionDescription.getCategories().forEach(category -> effectivePages.addAll(category.getPages()));
            });
        });

        if (effectivePages.size() == 0) {
            ViewExtensionDescription viewExtensionDescription = DefaultRulesProvider.INSTANCE.getDefaultRules();
            if (viewExtensionDescription != null) {
                viewExtensionDescription.getCategories().forEach(category -> effectivePages.addAll(category.getPages()));
            }
        }

        return effectivePages;
    }
}
