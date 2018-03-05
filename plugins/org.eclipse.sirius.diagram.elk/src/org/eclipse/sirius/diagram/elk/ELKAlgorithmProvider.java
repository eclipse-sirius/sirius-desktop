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
/**
* 
*/
package org.eclipse.sirius.diagram.elk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.data.LayoutAlgorithmData;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.elk.core.data.LayoutOptionData.Target;
import org.eclipse.elk.core.data.LayoutOptionData.Type;
import org.eclipse.elk.core.data.LayoutOptionData.Visibility;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithmProvider;
import org.eclipse.sirius.diagram.ui.api.layout.EnumChoice;
import org.eclipse.sirius.diagram.ui.api.layout.LayoutOptionFactory;

/**
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

            List<LayoutOptionData> optionDatas = LayoutMetaDataService.getInstance().getOptionData(layoutAlgorithmData, Target.PARENTS);
            Map<String, LayoutOption> layoutOptions = new HashMap<>();
            LayoutOptionFactory layoutOptionFactory = new LayoutOptionFactory();
            for (LayoutOptionData layoutOptionData : optionDatas) {
                if (!CoreOptions.ALGORITHM.getId().equals(layoutOptionData.getId()) && !layoutOptionData.getVisibility().equals(Visibility.HIDDEN)) {
                    switch (layoutOptionData.getType()) {
                    case STRING:
                        layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createStringOption((String) layoutOptionData.getDefault(), layoutOptionData.getId(),
                                layoutOptionData.getDescription(), layoutOptionData.getName()));
                        break;
                    case BOOLEAN:
                        layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createBooleanOption((Boolean) layoutOptionData.getDefault(), layoutOptionData.getId(),
                                layoutOptionData.getDescription(), layoutOptionData.getName()));
                        break;
                    case INT:
                        layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createIntegerOption((Integer) layoutOptionData.getDefault(), layoutOptionData.getId(),
                                layoutOptionData.getDescription(), layoutOptionData.getName()));
                        break;
                    case DOUBLE:
                        layoutOptions.put(layoutOptionData.getId(), layoutOptionFactory.createDoubleOption((Double) layoutOptionData.getDefault(), layoutOptionData.getId(),
                                layoutOptionData.getDescription(), layoutOptionData.getName()));
                        break;
                    case ENUMSET:
                    case ENUM:

                        String[] choices = layoutOptionData.getChoices();
                        List<EnumChoice> choicesList = new ArrayList<>();
                        for (int i = 0; i < choices.length; i++) {
                            String choiceId = choices[i];
                            choicesList.add(new EnumChoice(choiceId, ""));

                        }
                        String defaultValue = null;
                        Object defaultObject = layoutOptionData.getDefaultDefault();
                        if (defaultObject instanceof Enum) {
                            defaultValue = ((Enum<?>) defaultObject).name();
                        }
                        if (layoutOptionData.getType() == Type.ENUM) {
                            layoutOptions.put(layoutOptionData.getId(),
                                    layoutOptionFactory.createEnumOption(choicesList, layoutOptionData.getId(), layoutOptionData.getDescription(), layoutOptionData.getName(), defaultValue));
                        } else {
                            layoutOptions.put(layoutOptionData.getId(),
                                    layoutOptionFactory.createEnumSetOption(choicesList, layoutOptionData.getId(), layoutOptionData.getDescription(), layoutOptionData.getName()));
                        }

                        break;
                    default:
                        break;
                    }
                }
            }
            layoutAlgorithms.add(new CustomLayoutAlgorithm(layoutAlgorithmData.getId(), layoutAlgorithmData.getName(), () -> new ELKLayoutNodeProvider(), layoutOptions));
        }
        return layoutAlgorithms;
    }

}
