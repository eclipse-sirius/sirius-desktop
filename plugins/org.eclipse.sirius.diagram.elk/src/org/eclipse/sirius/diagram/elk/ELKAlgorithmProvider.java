/*******************************************************************************
 * Copyright (c) 2018, 2020 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.data.LayoutOptionData.Target;
import org.eclipse.elk.core.data.LayoutOptionData.Type;
import org.eclipse.elk.core.data.LayoutOptionData.Visibility;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOptionTarget;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithmProvider;
import org.eclipse.sirius.diagram.ui.api.layout.EnumChoice;
import org.eclipse.sirius.diagram.ui.api.layout.LayoutOptionFactory;

/**
 * Provides definition of available ELK algorithm as well as their options configurable by Sirius environment.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class ELKAlgorithmProvider implements CustomLayoutAlgorithmProvider {

    @Override
    public List<CustomLayoutAlgorithm> getCustomLayoutAlgorithms() {
        List<CustomLayoutAlgorithm> layoutAlgorithms = new ArrayList<>();
        // we fill the Sirius layout algorithm registry with all ELK algorithms.
        Collection<LayoutAlgorithmData> algorithmData = LayoutMetaDataService.getInstance().getAlgorithmData();
        for (LayoutAlgorithmData layoutAlgorithmData : algorithmData) {

            Map<String, LayoutOption> layoutOptions = new HashMap<>();
            LayoutOptionFactory layoutOptionFactory = new LayoutOptionFactory();
            handleOptionsData(LayoutMetaDataService.getInstance().getOptionData(layoutAlgorithmData, Target.PARENTS), layoutOptions, layoutOptionFactory);
            handleOptionsData(LayoutMetaDataService.getInstance().getOptionData(layoutAlgorithmData, Target.NODES), layoutOptions, layoutOptionFactory);
            handleOptionsData(LayoutMetaDataService.getInstance().getOptionData(layoutAlgorithmData, Target.EDGES), layoutOptions, layoutOptionFactory);
            handleOptionsData(LayoutMetaDataService.getInstance().getOptionData(layoutAlgorithmData, Target.PORTS), layoutOptions, layoutOptionFactory);
            handleOptionsData(LayoutMetaDataService.getInstance().getOptionData(layoutAlgorithmData, Target.LABELS), layoutOptions, layoutOptionFactory);
            //@formatter:off
            CustomLayoutAlgorithm customLayoutAlgorithm = CustomLayoutAlgorithm.newCustomLayoutAlgorithm(layoutAlgorithmData.getId())
                    .setLabel(layoutAlgorithmData.getName())
                    .setDescription(layoutAlgorithmData.getDescription())
                    .setLayoutSupplier(() -> new ELKLayoutNodeProvider())
                    .setLayoutOptions(layoutOptions)
                    .setLaunchSnapAfter(false)
                    .setStandardArrangeSelectionMechanism(false)
                    .reverseLayoutsOrder(true)
                    .build();
            //@formatter:on
            layoutAlgorithms.add(customLayoutAlgorithm);
        }
        return layoutAlgorithms;
    }

    private void handleOptionsData(List<LayoutOptionData> optionDatas, Map<String, LayoutOption> layoutOptions, LayoutOptionFactory layoutOptionFactory) {
        for (LayoutOptionData layoutOptionData : optionDatas) {
            if (!CoreOptions.ALGORITHM.getId().equals(layoutOptionData.getId()) && !layoutOptionData.getVisibility().equals(Visibility.HIDDEN) && !layoutOptions.containsKey(layoutOptionData.getId())) {

                switch (layoutOptionData.getType()) {
                case STRING:
                    layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createStringOption((String) layoutOptionData.getDefaultDefault(), layoutOptionData.getId(),
                            layoutOptionData.getDescription(), layoutOptionData.getName(), getOptionTargets(layoutOptionData.getTargets())));
                    break;
                case BOOLEAN:
                    layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createBooleanOption((Boolean) layoutOptionData.getDefaultDefault(), layoutOptionData.getId(),
                            layoutOptionData.getDescription(), layoutOptionData.getName(), getOptionTargets(layoutOptionData.getTargets())));
                    break;
                case INT:
                    layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createIntegerOption((Integer) layoutOptionData.getDefaultDefault(), layoutOptionData.getId(),
                            layoutOptionData.getDescription(), layoutOptionData.getName(), getOptionTargets(layoutOptionData.getTargets())));
                    break;
                case DOUBLE:
                    layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createDoubleOption((Double) layoutOptionData.getDefaultDefault(), layoutOptionData.getId(),
                            layoutOptionData.getDescription(), layoutOptionData.getName(), getOptionTargets(layoutOptionData.getTargets())));
                    break;
                case ENUMSET:
                case ENUM:

                    String[] choices = layoutOptionData.getChoices();
                    List<EnumChoice> choicesList = new ArrayList<>();
                    for (int i = 0; i < choices.length; i++) {
                        String choiceId = choices[i];
                        choicesList.add(new EnumChoice(choiceId, ""));

                    }
                    Object defaultObject = layoutOptionData.getDefaultDefault();
                    if (layoutOptionData.getType() == Type.ENUM) {
                        String defaultValue = null;
                        if (defaultObject instanceof Enum) {
                            defaultValue = ((Enum<?>) defaultObject).name();
                        }
                        layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createEnumOption(choicesList, layoutOptionData.getId(), layoutOptionData.getDescription(),
                                layoutOptionData.getName(), defaultValue, getOptionTargets(layoutOptionData.getTargets())));
                    } else {
                        List<String> enumValues = new ArrayList<>();
                        if (defaultObject instanceof EnumSet) {
                            EnumSet<?> enumSet = (EnumSet<?>) defaultObject;
                            if (enumSet.size() > 0) {
                                enumSet.forEach((enumValue) -> enumValues.add(enumValue.name()));
                            }
                        }
                        layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createEnumSetOption(choicesList, layoutOptionData.getId(), layoutOptionData.getDescription(),
                                layoutOptionData.getName(), enumValues, getOptionTargets(layoutOptionData.getTargets())));
                    }
                    break;
                default:
                    break;
                }
            }
        }
    }

    private Set<LayoutOptionTarget> getOptionTargets(Set<Target> targets) {
        Set<LayoutOptionTarget> result = new HashSet<>();
        for (Target target : targets) {

            switch (target) {
            case PARENTS:
                result.add(LayoutOptionTarget.PARENT);
                break;
            case NODES:
                result.add(LayoutOptionTarget.NODE);
                break;
            case EDGES:
                result.add(LayoutOptionTarget.EDGE);
                break;
            case PORTS:
                result.add(LayoutOptionTarget.PORTS);
                break;
            case LABELS:
                result.add(LayoutOptionTarget.LABEL);
                break;
            default:
                break;
            }
        }
        return result;
    }

}
