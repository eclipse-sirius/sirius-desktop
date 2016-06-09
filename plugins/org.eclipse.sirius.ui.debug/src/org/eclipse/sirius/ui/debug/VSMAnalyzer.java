/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.ui.debug;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.viewpoint.description.Group;

import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

/**
 * Computes statistics on a VSM.
 * 
 * @author pcdavid
 *
 */
public class VSMAnalyzer {
    private final Group vsm;

    private final IInterpreter itr;

    public VSMAnalyzer(Group vsm, IInterpreter itr) {
        this.vsm = vsm;
        this.itr = itr;
    }

    public String computeStatistics() throws EvaluationException {
        StringBuilder result = new StringBuilder();
        addStat("* Total number of elements in the VSM", "aql:self.eAllContents()->size()", result);
        addStat("* Number of viewpoints defined", "aql:self.eAllContents(description::Viewpoint)->size()", result);
        addStat("* Number of representations", "aql:self.eAllContents(description::RepresentationDescription)->size()", result);
        addStat(" * Number of diagrams", "aql:self.eAllContents(description::DiagramDescription)->size()", result);
        addStat(" * Number of sequence diagrams", "aql:self.eAllContents(description::SequenceDiagramDescription)->size()", result);
        addStat(" * Number of tables", "aql:self.eAllContents(description::TableDescription)->size()", result);
        addStat(" * Number of trees", "aql:self.eAllContents(description::TreeDescription)->size()", result);
        result.append("\n");
        result.append(toolSizeDistribution());
        return result.toString();
    }

    public void addStat(String title, String expression, StringBuilder out) throws EvaluationException {
        out.append(MessageFormat.format("{0} (`{1}`): {2}\n", title, expression, itr.evaluate(vsm, expression)));
    }

    public String toolSizeDistribution() throws EvaluationException {
        Collection<EObject> initOperations = itr.evaluateCollection(vsm, "aql:self.eAllContents(tool::InitialOperation)");
        Map<Integer, Integer> distribution = Maps.newHashMap();
        for (EObject init : initOperations) {
            Integer size = itr.evaluateInteger(init, "aql:self.eAllContents()->size()");
            if (distribution.containsKey(size)) {
                distribution.put(size, distribution.get(size) + 1);
            } else {
                distribution.put(size, 1);
            }
        }
        StringBuilder result = new StringBuilder("Size distribution (over " + initOperations.size() + " tools):\n");
        result.append("| Size | Tools | Cumulative % |\n");
        result.append("|------|-------|--------------|\n");
        int acc = 0;
        for (int size : Ordering.natural().sortedCopy(distribution.keySet())) {
            acc += distribution.get(size);
            result.append("| ").append(size).append(" | ").append(distribution.get(size)).append(" | ").append(String.format("%.2f", ((float) acc) / (float) initOperations.size() * 100.0))
                    .append(" |\n");
        }
        return result.toString();
    }

}
