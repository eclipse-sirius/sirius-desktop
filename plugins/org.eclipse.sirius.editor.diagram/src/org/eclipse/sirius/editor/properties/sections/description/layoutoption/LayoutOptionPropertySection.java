/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.layoutoption;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.description.BooleanLayoutOption;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DoubleLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.StringLayoutOption;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A custom property section displaying all layout options for a given {@link CustomLayoutConfiguration}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class LayoutOptionPropertySection extends AbstractViewpointPropertySection {

    /**
     * A list containing all layout option sections.
     */
    private List<AbstractPropertySection> allLayoutOptionSections;

    /**
     * A list containing all composites associated to layout option sections allowing to edit those options.
     */
    private List<Composite> allLayoutOptionComposites;

    /**
     * The widget allowing to add an option override
     */
    private AddOptionOverridePropertySection propertyOverridePropertySection;

    /**
     * The property section of the layout configuration description.
     */
    private AbstractViewpointPropertySection descriptionPropertySection;

    /**
     * THe page composite displaying all {@link CustomLayoutConfiguration} sections.
     */
    private Composite pageComposite;

    /**
     * A map associating a layout option section to its semantic element. Allow to keep section input when we rebuild
     * sections.
     */
    private Map<AbstractPropertySection, LayoutOption> optionSection2SemanticElement;

    @Override
    protected void makeReadonly() {
    }

    @Override
    protected void makeWrittable() {

    }

    @Override
    protected EStructuralFeature getFeature() {
        return DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_LayoutOptions();
    }

    @Override
    public void createControls(Composite sectionComposite, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        if (aTabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(sectionComposite, (ViewpointPropertySheetPage) aTabbedPropertySheetPage);
        else
            super.createControls(sectionComposite, aTabbedPropertySheetPage);

        pageComposite = sectionComposite.getParent();

        descriptionPropertySection = new CustomLayoutConfigurationDescriptionPropertySection();
        descriptionPropertySection.createControls(sectionComposite, aTabbedPropertySheetPage);

        propertyOverridePropertySection = new AddOptionOverridePropertySection();
        Composite addOverrideSectionComposite = createSectionComposite(pageComposite, aTabbedPropertySheetPage, propertyOverridePropertySection);
        propertyOverridePropertySection.createControls(addOverrideSectionComposite, aTabbedPropertySheetPage);

        allLayoutOptionSections = new ArrayList<>();
        allLayoutOptionComposites = new ArrayList<>();
        optionSection2SemanticElement = new HashMap<>();
        initializeLayoutOptionSections(propertySheetPage, false, getLayoutOptions());
    }

    /**
     * Initialize all UI Components allowing to edit given layout options.
     * 
     * @param aTabbedPropertySheetPage
     * @param setInput
     *            true if the input must be set again in created {@link AbstractPropertySection}. False otherwise.
     * @param layoutOptions
     *            the layout options from which UI components to edit those must be initialized.
     */
    private void initializeLayoutOptionSections(TabbedPropertySheetPage aTabbedPropertySheetPage, boolean setInput, EList<LayoutOption> layoutOptions) {
        ECollections.sort(layoutOptions, new Comparator<LayoutOption>() {
            @Override
            public int compare(LayoutOption o1, LayoutOption o2) {
                return o1.getLabel().compareTo(o2.getLabel());
            }
        });
        for (LayoutOption layoutOption : layoutOptions) {
            if (layoutOption instanceof StringLayoutOption) {
                StringLayoutOptionValuePropertySectionSpec stringLayoutOptionValuePropertySection = new StringLayoutOptionValuePropertySectionSpec(layoutOption);
                initLayoutOptionCompositeAndControls(aTabbedPropertySheetPage, layoutOption, stringLayoutOptionValuePropertySection);
                if (setInput) {
                    stringLayoutOptionValuePropertySection.setInput(getPart(), new StructuredSelection(layoutOption));
                }
            } else if (layoutOption instanceof BooleanLayoutOption) {
                BooleanLayoutOptionValuePropertySectionSpec booleanLayoutOptionValuePropertySection = new BooleanLayoutOptionValuePropertySectionSpec(layoutOption);
                initLayoutOptionCompositeAndControls(aTabbedPropertySheetPage, layoutOption, booleanLayoutOptionValuePropertySection);
                if (setInput) {
                    booleanLayoutOptionValuePropertySection.setInput(getPart(), new StructuredSelection(layoutOption));
                }
            } else if (layoutOption instanceof IntegerLayoutOption) {
                IntegerLayoutOptionValuePropertySectionSpec integerLayoutOptionValuePropertySection = new IntegerLayoutOptionValuePropertySectionSpec(layoutOption);
                initLayoutOptionCompositeAndControls(aTabbedPropertySheetPage, layoutOption, integerLayoutOptionValuePropertySection);
                if (setInput) {
                    integerLayoutOptionValuePropertySection.setInput(getPart(), new StructuredSelection(layoutOption));
                }
            } else if (layoutOption instanceof DoubleLayoutOption) {
                DoubleLayoutOptionValuePropertySectionSpec doubleLayoutOptionValuePropertySection = new DoubleLayoutOptionValuePropertySectionSpec(layoutOption);
                initLayoutOptionCompositeAndControls(aTabbedPropertySheetPage, layoutOption, doubleLayoutOptionValuePropertySection);
                if (setInput) {
                    doubleLayoutOptionValuePropertySection.setInput(getPart(), new StructuredSelection(layoutOption));
                }
            } else if (layoutOption instanceof EnumSetLayoutOption) {
                EnumSetLayoutOptionValuePropertySection enumSetLayoutOptionValuePropertySection = new EnumSetLayoutOptionValuePropertySection(layoutOption);
                initLayoutOptionCompositeAndControls(aTabbedPropertySheetPage, layoutOption, enumSetLayoutOptionValuePropertySection);
                if (setInput) {
                    enumSetLayoutOptionValuePropertySection.setInput(getPart(), new StructuredSelection(layoutOption));
                }
            } else if (layoutOption instanceof EnumLayoutOption) {
                EnumLayoutOptionValuePropertySection enumLayoutOptionValuePropertySection = new EnumLayoutOptionValuePropertySection(layoutOption);
                initLayoutOptionCompositeAndControls(aTabbedPropertySheetPage, layoutOption, enumLayoutOptionValuePropertySection);
                if (setInput) {
                    enumLayoutOptionValuePropertySection.setInput(getPart(), new StructuredSelection(layoutOption));
                }
            }

        }
    }

    /**
     * Initialize the UI components allowing to edit the given layout option.
     * 
     * @param aTabbedPropertySheetPage
     *            the current property sheet page where we display layout options.
     * @param layoutOption
     *            the layout option from which we initialize its UI components.
     * @param layoutOptionValuePropertySection
     *            the {@link AbstractPropertySection} of the layout option containing widgets for edition of the option.
     */
    private void initLayoutOptionCompositeAndControls(TabbedPropertySheetPage aTabbedPropertySheetPage, LayoutOption layoutOption, AbstractPropertySection layoutOptionValuePropertySection) {
        Composite stringOptionSectionComposite = createSectionComposite(pageComposite, aTabbedPropertySheetPage, layoutOptionValuePropertySection);
        allLayoutOptionComposites.add(stringOptionSectionComposite);
        layoutOptionValuePropertySection.createControls(stringOptionSectionComposite, aTabbedPropertySheetPage);
        allLayoutOptionSections.add(layoutOptionValuePropertySection);
        optionSection2SemanticElement.put(layoutOptionValuePropertySection, layoutOption);
        layoutOptionValuePropertySection.refresh();
    }

    /**
     * Returns the layout options associated to the {@link CustomLayoutConfiguration} from which we are showing layout
     * options.
     * 
     * @return the layout options.
     */
    private EList<LayoutOption> getLayoutOptions() {
        CustomLayoutConfiguration layoutConfiguration = (CustomLayoutConfiguration) eObject;
        EList<LayoutOption> layoutOptions = layoutConfiguration.getLayoutOptions();
        return layoutOptions;
    }

    /**
     * Creates the composite showing the layout option.
     * 
     * @param parent
     *            the composite parent where to put the new section composite.
     * @param aTabbedPropertySheetPage
     *            the sheet page from which we retrieve the widget factory.
     * @param propertySection
     *            the property section from which we retrieve information to build its composite container.
     * @return the newly created section composite that will contains layout option widgets.
     */
    private Composite createSectionComposite(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage, AbstractPropertySection propertySection) {
        Composite sectionComposite = aTabbedPropertySheetPage.getWidgetFactory().createComposite(parent, SWT.NO_FOCUS);
        sectionComposite.setLayout(new FillLayout());
        int style = (propertySection.shouldUseExtraSpace()) ? GridData.FILL_BOTH : GridData.FILL_HORIZONTAL;
        GridData data = new GridData(style);
        data.heightHint = propertySection.getMinimumHeight();
        sectionComposite.setLayoutData(data);
        return sectionComposite;
    }

    @Override
    public void dispose() {
        for (AbstractPropertySection propertySection : allLayoutOptionSections) {
            propertySection.dispose();
        }
        for (Composite composite : allLayoutOptionComposites) {
            composite.dispose();
        }
        descriptionPropertySection.dispose();
        propertyOverridePropertySection.dispose();
        optionSection2SemanticElement.clear();
        super.dispose();
    }

    @Override
    public void refresh() {
        List<LayoutOption> layoutOptions = getLayoutOptions();
        if (layoutOptions.size() != allLayoutOptionSections.size()) {

            // A layout option has been added or removed. We dispose all layout options and rebuild those with the new
            // or without the old one.
            for (AbstractPropertySection propertySection : allLayoutOptionSections) {
                propertySection.dispose();
            }
            for (Composite composite : allLayoutOptionComposites) {
                composite.dispose();
            }
            allLayoutOptionSections.clear();
            allLayoutOptionComposites.clear();
            optionSection2SemanticElement.clear();
            initializeLayoutOptionSections(propertySheetPage, true, getLayoutOptions());
            pageComposite.layout();

        }
        for (AbstractPropertySection propertySection : allLayoutOptionSections) {
            propertySection.refresh();
        }

    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        for (AbstractPropertySection propertySection : allLayoutOptionSections) {
            propertySection.setInput(part, new StructuredSelection(optionSection2SemanticElement.get(propertySection)));
        }
    }

}
